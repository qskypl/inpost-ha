package api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class ResponseUtil {

    private ResponseUtil() {
    }

    public static List<Map<String, Object>> extractPaczkomaty(Response response) {
        Map<String, Object> items = response
                .jsonPath().getMap("");

        return (List<Map<String, Object>>) items.get("items");
    }

    public static JsonArray extractDetails(List<Map<String, Object>> paczkomaty) {
        var jsonArray = new JsonArray();

        for (Map<String, Object> paczkomat : paczkomaty) {
            var jsonObject = new JsonObject();
            jsonObject.addProperty("name", (String) paczkomat.get("name"));
            jsonObject.addProperty("post_code", (String) ((Map<String, Object>) paczkomat.get("address_details")).get("post_code"));

            var location = (Map<String, Object>) paczkomat.get("location");
            var locationJson = new JsonObject();
            locationJson.addProperty("longitude", (Float) location.get("longitude"));
            locationJson.addProperty("latitude", (Float) location.get("latitude"));


            jsonObject.add("location", locationJson);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    public static String extractJsonToString(JsonArray array) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(array);
    }

}
