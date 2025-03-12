package basics;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HeadersTest {

    @Test
    public void withSingleHeader(){
        given()
                .when()
                .header("Content-Type", "application/json")
                .get("posts")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void withMultipleHeaders(){
        Map<String, String> headers  = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer ......");

        given()
                .when()
                .headers(headers)
                .get("posts")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void withHeader(){
        Header contentType = new Header("Content-Type", "application/json");
        Headers headers = Headers.headers(contentType);
        given()
                .when()
                .headers(headers)
                .get("posts")
                .then()
                .statusCode(200)
                .log().all();
    }

}
