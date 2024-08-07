package api.action;

import api.request.PointsRequest;
import api.spec.DefaultSpecStrategy;
import io.restassured.response.Response;

public class PointsAction {

    private final PointsRequest request = new PointsRequest(new DefaultSpecStrategy());

    public Response getAllPoints() {
        return request.getAllPoints();
    }

    public Response getPointsForCity(String city) {
        return request.getPoints(city);
    }
}
