package api.spec;

import config.FrameworkConfig;
import config.factory.ConfigFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.mapper.ObjectMapperType;

public abstract class BaseSpec {

    private static final FrameworkConfig CONFIG = ConfigFactory.config();
    private static final String URI = CONFIG.apiUrl();

    public RestAssuredConfig getBaseConfig() {
        return RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));
    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        return new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())

                .setBaseUri(URI)
                .setConfig(getBaseConfig());
    }
}
