package api.spec;

import io.restassured.specification.RequestSpecification;

public interface SpecStrategy {

    RequestSpecification buildRequestSpecification();
}
