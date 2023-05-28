import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Quiz2Test {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";

        requestSpecification = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .that().log().all();
    }

    @Test(priority = 1, enabled = false)
    public void firstTest() {
        String userName = "nika" + RandomStringUtils.randomAlphabetic(10);
        String password = "!123Aa123";

        RegisterViewModel requestBody = new RegisterViewModel(userName, password);

        given(requestSpecification)
                .body(requestBody)
                .post("/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat().body("username", equalTo(userName));
    }

    @Test(priority = 2, alwaysRun = true, dependsOnMethods = "thirdTest")
    public void secondTest() {
        String userName = "nika";

        RegisterViewModel requestBody = new RegisterViewModel(userName, "");

        given(requestSpecification)
                .body(requestBody)
                .post("/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("UserName and Password required."));
    }

    @Test(priority = 3, invocationCount = 3)
    public void thirdTest() {
        RegisterViewModel requestBody = new RegisterViewModel(123, 231);

        given(requestSpecification)
                .body(requestBody)
                .post("/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(504)
                .and()
                .body("html.head.title", equalTo("504 Gateway Time-out"));
    }
}
