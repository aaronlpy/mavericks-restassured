package basics;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredBasicsTest {


    @Test
    public void getRequest() {
        given()
                .when()
                .get()
                .then()
                .log().all();
    }

    @Test
    public void postRequest() {
        given()
                .when()
                .post()
                .then();
    }

    @Test
    public void putRequest() {
        given()
                .when()
                .put()
                .then();
    }

    @Test
    public void patchRequest() {
        given()
                .when()
                .patch()
                .then();
    }

    @Test
    public void deleteRequest() {
        given()
                .when()
                .delete()
                .then();
    }


}
