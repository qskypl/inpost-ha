package api.spec;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class DefaultSpecStrategy extends BaseSpec implements SpecStrategy {

    public RequestSpecification buildRequestSpecification() {
        return getRequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
}
