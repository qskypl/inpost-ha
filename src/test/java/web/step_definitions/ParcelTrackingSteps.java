package web.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import web.page.ParcelTrackingPage;

import java.util.ArrayList;
import java.util.List;

public class ParcelTrackingSteps {

    private final ParcelTrackingPage parcelTrackingPage;

    public ParcelTrackingSteps(ParcelTrackingPage parcelTrackingPage) {
        this.parcelTrackingPage = parcelTrackingPage;
    }

    @Given("^I open Parcel Tracking page$")
    public void iOpenParcelTrackingPage() {
        parcelTrackingPage
                .openPage();
    }

    @When("I search for parcel using tracking number: {}")
    public void iSearchForParcelUsingTrackingNumber(String number) {
        parcelTrackingPage
                .setParcelNumber(number)
                .clickSearch();
    }

    @Then("Parcel status is: {}")
    public void parcelStatusIs(String expectedStatus) {
        String actualStatus = parcelTrackingPage
                .getParcelLatestStatus();
        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Then("Parcel statuses are:")
    public void parcelStatusesAre(DataTable dataTable) {
        var expectedStatusesList = dataTable.asLists(String.class).stream()
                .flatMap(List::stream)
                .toList();

        var regularStatusesList = parcelTrackingPage.
                getParcelRegularStatues();

        var latestStatus = parcelTrackingPage
                .getParcelLatestStatus();

        var actualStatusList = new ArrayList<>(regularStatusesList);
        actualStatusList.add(latestStatus);

        for (String expectedStatus : expectedStatusesList) {
            if (!actualStatusList.contains(expectedStatus)) {
                throw new AssertionError("Expected status not found: " + expectedStatus);
            }
        }
    }
}
