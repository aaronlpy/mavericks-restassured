package trello;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class TrelloTest {

    private RequestSpecification spec;

    @BeforeClass
    public void setup() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com/1")
                .addQueryParam("key", "")
                .addQueryParam("token", "")
                .setContentType("application/json")
                .build();
    }

    @Test(description = "Create a new organization")
    public void createOrganizationTest(ITestContext context) {
        Response response = RestAssured
                .given()
                .spec(spec)
                .queryParam("displayName", "Mavericks")
                .queryParam("desc", "This is random description")
                .post("/organizations");


        response
                .then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue());


        String orgId = response.then().extract().path("id");
        if (!orgId.isEmpty()) {
            System.out.println("Saving OrganizationId to context: " + orgId);
            context.setAttribute("organizationId", orgId);
        }
    }

    @Test(description = "Create a new board", dependsOnMethods = "createOrganizationTest")
    public void createBoardTest(ITestContext context) {

        String orgId = (String) context.getAttribute("organizationId");

        Response response = RestAssured
                .given()
                .spec(spec)
                .queryParam("name", "RestAssured")
                .queryParam("idOrganization", orgId)
                .post("/boards");

        response.then().log().all().statusCode(200);

        String boardId = response.then().extract().path("id");
        if (!orgId.isEmpty()) {
            System.out.println("Saving boardId to context: " + boardId);
            context.setAttribute("boardId", boardId);
        }

    }

    @Test(dependsOnMethods = "createBoardTest")
    public void getBoardListTest(ITestContext context) {
        Response response = RestAssured
                .given()
                .spec(spec)
                .pathParam("boardId", context.getAttribute("boardId"))
                .get("/board/{boardId}/lists");


        JSONArray array = new JSONArray(response.body().asString());
        array.forEach(item -> {
            JSONObject json = (JSONObject) item;
            context.setAttribute(json.get("name").toString().replace(" ", "") + "Id", json.get("id"));
        });
    }

    @Test(dependsOnMethods = "getBoardListTest")
    public void createCardTest(ITestContext context) {
        Response response = RestAssured
                .given()
                .spec(spec)
                .queryParam("name", "Sample Card")
                .queryParam("desc", "this is sample descriptions")
                .queryParam("idList", context.getAttribute("ToDoId").toString())
                .post("/cards");

        response.then().log().all().statusCode(200);
    }

}
