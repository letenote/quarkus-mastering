package letenote;

import io.quarkus.arc.impl.BeanManagerProvider;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import java.beans.beancontext.BeanContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {
    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void addHelloEndpoint(){
        String myName1 = "John";
        given()
                .pathParam("your-name", myName1)
                .when().get("/hello/greeting/{your-name}")
                .then()
                .statusCode(200)
                .body(is("Hello " + myName1));
        String myName2 = "John-Doe";
        given()
                .pathParam("your-name", myName2)
                .when().get("/hello/greeting/{your-name}")
                .then()
                .statusCode(200)
                .body(is("Hello " + myName2));
        String myName3 = "";
        given()
                .pathParam("your-name", myName3)
                .when().get("/hello/greeting/{your-name}")
                .then()
                .statusCode(404);
    }

}