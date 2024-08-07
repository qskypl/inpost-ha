package web.page.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.util.LocatorsCommon;

import java.util.List;

public class ParcelTrackingLocators extends LocatorsCommon {

    private final By trackingForm = By.className("tracking-form");
    private final By parcelInputText = By.cssSelector("input[name='number']");
    private final By parcelSearchButton = By.cssSelector("button[type='submit']");
    private final By statusContainer = By.cssSelector(".singleParcelStatusesList");
    private final By parcelFinalStatusText = By.cssSelector(".-big.-secondary");
    private final By parcelRegularStatusText = By.cssSelector(".main-info");

    public WebElement getParcelInputText() {
        return getTrackingForm().findElement(parcelInputText);
    }

    public WebElement getParcelSearchButton() {
        return getTrackingForm().findElement(parcelSearchButton);
    }

    private WebElement getTrackingForm() {
        return getWebDriver().findElement(trackingForm);
    }

    public By getStatusContainerLocator() {
        return statusContainer;
    }

    public WebElement getParcelFinalStatus() {
        return getWebDriver().findElement(parcelFinalStatusText);
    }

    public List<WebElement> getParcelRegularStatuses() {
        return getWebDriver().findElement(statusContainer).findElements(parcelRegularStatusText);
    }
}
