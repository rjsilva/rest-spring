package br.com.rjs.rest_spring.integrationtests;

import br.com.rjs.rest_spring.config.TestConfigs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest{

    @Test
    void shouldDisplaySwaggerUIPage(){
        var response = given().basePath("swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORTS)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .asString();

        Assertions.assertTrue(response.contains("Swagger UI"));
    }

}
