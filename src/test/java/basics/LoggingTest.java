package basics;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingTest {

    @Test
    public void logAllRequest(){
        given()
                .log().all()
                .when()
                .get("/posts");
    }

    @Test
    public void logRequestHeaders(){
        given()
                .log().headers()
                .when()
                .get("/posts");
    }

    @Test
    public void logRequestBody(){
        given()
                .body("{\"id\": 8}")
                .log().body()
                .when()
                .get("/posts");
    }

    @Test
    public void logRequestParams(){
        given()
                .queryParam("postId", 1)
                .log().body()
                .when()
                .get("/posts");
    }

    @Test
    public void logResponseAll(){
        given()
                .when()
                .get("/posts")
                .then()
                .log()
                .all();
    }

    public void logResponseStatus(){
        given()
                .when()
                .get("/posts")
                .then()
                .log()
                .ifValidationFails();
    }


}
