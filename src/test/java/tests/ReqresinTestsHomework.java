package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresinTestsHomework {

    @Test
    void listUsers() {
        String body = "{ \"page\": 2, \"per_page\": 6, \"total\": 12, " +
                "\"total_pages\": 2, \"data\": [ { \"id\": 7, \"email\": " +
                "\"michael.lawson@reqres.in\", \"first_name\": \"Michael\", " +
                "\"last_name\": \"Lawson\", \"avatar\": " +
                "\"https://reqres.in/img/faces/7-image.jpg\" }, " +
                "{ \"id\": 8, \"email\": \"lindsay.ferguson@reqres.in\", " +
                "\"first_name\": \"Lindsay\", \"last_name\": \"Ferguson\", " +
                "\"avatar\": \"https://reqres.in/img/faces/8-image.jpg\" }, " +
                "{ \"id\": 9, \"email\": \"tobias.funke@reqres.in\", \"first_name\": " +
                "\"Tobias\", \"last_name\": \"Funke\", \"avatar\": " +
                "\"https://reqres.in/img/faces/9-image.jpg\" }, " +
                "{ \"id\": 10, \"email\": \"byron.fields@reqres.in\", " +
                "\"first_name\": \"Byron\", \"last_name\": \"Fields\", " +
                "\"avatar\": \"https://reqres.in/img/faces/10-image.jpg\" }, " +
                "{ \"id\": 11, \"email\": \"george.edwards@reqres.in\", " +
                "\"first_name\": \"George\", \"last_name\": \"Edwards\", " +
                "\"avatar\": \"https://reqres.in/img/faces/11-image.jpg\" }, " +
                "{ \"id\": 12, \"email\": \"rachel.howell@reqres.in\", \"first_name\": " +
                "\"Rachel\", \"last_name\": \"Howell\", \"avatar\": " +
                "\"https://reqres.in/img/faces/12-image.jpg\" } ], \"support\": " +
                "{ \"url\": \"https://reqres.in/#support-heading\", \"text\": " +
                "\"To keep ReqRes free, contributions towards server costs are appreciated!\" } }";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    void createUser() {
        String body = "{\"name\": \"morpheus\",     \"job\": \"leader\" }";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void updateUser() {
        String body = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name",is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void deleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    void registerSuccesfull() {
        String body = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id",is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

}
