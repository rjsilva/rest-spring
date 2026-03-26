package br.com.rjs.rest_spring.integrationtests;

import br.com.rjs.rest_spring.config.TestConfigs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CorsIntegrationTest extends AbstractIntegrationTest {

    private static final String ALLOWED_ORIGIN = "http://localhost:8080";
    private static final String DISALLOWED_ORIGIN = "http://evil.example.com";

    @Test
    void shouldReturnCorsHeadersForAllowedOriginOnPreflightRequest() {
        given()
            .port(TestConfigs.SERVER_PORTS)
            .header("Origin", ALLOWED_ORIGIN)
            .header("Access-Control-Request-Method", "GET")
        .when()
            .options("/person/all")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", ALLOWED_ORIGIN)
            .header("Access-Control-Allow-Methods", notNullValue());
    }

    @Test
    void shouldReturnCorsHeadersForAllowedOriginOnActualRequest() {
        given()
            .port(TestConfigs.SERVER_PORTS)
            .header("Origin", ALLOWED_ORIGIN)
        .when()
            .get("/person/all")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
    }

    @Test
    void shouldNotReturnCorsHeadersForDisallowedOrigin() {
        given()
            .port(TestConfigs.SERVER_PORTS)
            .header("Origin", DISALLOWED_ORIGIN)
            .header("Access-Control-Request-Method", "GET")
        .when()
            .options("/person/all")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test
    void shouldAllowCredentialsForAllowedOrigin() {
        given()
            .port(TestConfigs.SERVER_PORTS)
            .header("Origin", ALLOWED_ORIGIN)
            .header("Access-Control-Request-Method", "POST")
        .when()
            .options("/person/add")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", ALLOWED_ORIGIN)
            .header("Access-Control-Allow-Credentials", "true");
    }

    @Test
    void shouldAllowAllMethodsInPreflightForAllowedOrigin() {
        given()
            .port(TestConfigs.SERVER_PORTS)
            .header("Origin", ALLOWED_ORIGIN)
            .header("Access-Control-Request-Method", "DELETE")
        .when()
            .options("/person/delete/1")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", ALLOWED_ORIGIN)
            .header("Access-Control-Allow-Methods", notNullValue());
    }
}