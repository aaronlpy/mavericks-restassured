package basics;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class FluentAPIStyleTest {

    @Test
    public void getPostRequest(){
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
        response.then().log().all();
    }

    @Test
    public void PostRequest(){
        Response response = RestAssured
                .given()
                .when().post("https://jsonplaceholder.typicode.com/posts");

        response.then().log().all();
    }

    @Test
    public void getRequest() {
        Response response = RestAssured.get();
        response.then();
    }

    @Test
    public void postRequest() {
        Response response = RestAssured
                .given()
                .body("A")
                .post();

        response.then();
    }

    @Test
    public void putRequest() {
        Response response = RestAssured.put();
        response.then();
    }

    @Test
    public void deleteRequest() {
        Response response = RestAssured.delete();
        response.then();
    }

    @Test
    public void patchRequest() {
        Response response = RestAssured.patch();
        response.then();
    }

}
