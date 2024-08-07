package api.request;

import api.spec.SpecStrategy;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseRequest {

    protected final RequestSpecification specification;

    protected BaseRequest(SpecStrategy specStrategy) {
        this.specification = specStrategy.buildRequestSpecification();
    }

    protected Response get(String path) {
        return getRequestSpecification()
                .when()
                .get(path);
    }

    protected RequestSpecification getRequestSpecification() {
        return given()
                .spec(specification);
    }
}
