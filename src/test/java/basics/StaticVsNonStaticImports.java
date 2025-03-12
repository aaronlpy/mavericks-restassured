package basics;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StaticVsNonStaticImports {

    @Test
    public void staticTest() {
        given()
                .when()
                .then();
    }

    @Test
    public void nonStaticTest() {
        RestAssured.given()
                .when()
                .then();
    }

}
