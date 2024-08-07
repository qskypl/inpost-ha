package api.step_definitions;

import api.action.PointsAction;
import api.storage.SharedStorage;
import api.util.FileUtil;
import api.util.ResponseUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import static api.util.ResponseUtil.extractDetails;
import static api.util.ResponseUtil.extractJsonToString;

@Slf4j
public class ExportPaczkomatySteps {

    private final PointsAction pointsAction;
    private final SharedStorage storage;

    public ExportPaczkomatySteps(PointsAction pointsAction,
                                 SharedStorage storage) {
        this.pointsAction = pointsAction;
        this.storage = storage;
    }

    @When("I request for a list of Paczkomaty in {} city")
    public void iRequestForAListOfPaczkomatyInCity(String city) {
        storage.setResponse(
                pointsAction
                        .getPointsForCity(city));
    }

    @Then("Response status code is {int}")
    public void responseStatusCodeIs(int statusCode) {
        Assert.assertEquals(statusCode,
                storage.getResponse().statusCode());
    }

    @Then("I export the Paczkomaty list to the {} file")
    public void iExportThePaczkomatyListToTheFile(String fileName) {
        var paczkomatyList = ResponseUtil.extractPaczkomaty(storage.getResponse());
        var extractedJsonArray = extractDetails(paczkomatyList);
        var fileContent = extractJsonToString(extractedJsonArray);
        FileUtil.saveFile(fileContent, fileName);
    }
}
