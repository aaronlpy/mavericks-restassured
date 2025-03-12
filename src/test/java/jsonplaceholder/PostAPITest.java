package jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PostAPITest {

    public RequestSpecification spec;

    @BeforeMethod
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    @Test(priority = 1, description = "Verify the creation of a new post")
    public void createNewPost(){
        String post = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"title\": \"sample title\",\n" +
                "    \"body\": \"sample text\n" +
                "  }";

        Response response = RestAssured
                .given()
                .spec(spec)
                .when()
                .body(post)
                .post("/posts");

        response.then()
                .log()
                .all()
                .statusCode(201);
    }

    @Test(priority = 2, description = "Get post by id")
    public void getPost(){
        String id = "1";
        Response response = RestAssured
                .given()
                .spec(spec)
                .when()
                .get("/posts/" + id);

        response.then().log().all();

    }

    @Test(priority = 3, description = "Get post by using path parameters")
    public void getPostById(){
        Response response = RestAssured
                .given()
                .spec(spec)
                .when()
                .pathParam("postId", 1)
                .get("/posts/{postId}");

        response.then().log().all().statusCode(200);
    }

    @Test(priority = 4, description = "Get post by using query parameters")
    public void getPostByUsingQueryParams(){

        Response response = RestAssured
                .given()
                .spec(spec)
                .when()
                .queryParam("id", 1)
                .get("/posts");

        response.then().log().all().statusCode(200);
    }


    @Test(priority = 5, description = "Update an specific post")
    public void updatePost(){

        String post = "{\"userId\": 1,\"title\": \"sample title UPDATE\",\"body\": \"sample text UPDATE\"}";


        Response response = RestAssured
                .given()
                .spec(spec)
                .when()
                .pathParam("postId", 8)
                .body(post)
                .put("/posts/{postId}");

        response.then().log().all().statusCode(200);
    }

    @Test(priority = 6, description = "Patch an specific post")
    public void patchPost(){
        String post = "{\"title\": \"sample title UPDATE using patch\"\n}";


        Response response = RestAssured
                .given()
                .spec(spec)
                .when()
                .pathParam("postId", 8)
                .body(post)
                .patch("/posts/{postId}");

        response.then().log().all().statusCode(200);
    }

    @Test(priority = 7, description = "Delete an specific post")
    public void deletePost(){
        Response response = RestAssured
                .given()
                .spec(spec)
                .when()
                .pathParam("postId", 8)
                .delete("/posts/{postId}");

        response.then().log().all().statusCode(200);
    }


}
