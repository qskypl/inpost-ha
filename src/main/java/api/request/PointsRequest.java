package api.request;

import api.spec.SpecStrategy;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class PointsRequest extends BaseRequest {

    private static final String PATH = "v1/points";

    public PointsRequest(SpecStrategy specStrategy) {
        super(specStrategy);
    }

    public Response getAllPoints() {
        return get(PATH);
    }

    public Response getPoints(String city) {
        return getRequestSpecification()
                .queryParams(getLimitedFields())
                .queryParam("city", city)
                .queryParams(getFilters())
                .when()
                .get(PATH);
    }

    private Map<String, Object> getLimitedFields() {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("fields", "name,address_details,location");
        return pathParams;
    }

    private Map<String, Object> getFilters() {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("type", "parcel_locker,parcel_locker_superpop");
        pathParams.put("per_page", "500");
        return pathParams;
    }
}
