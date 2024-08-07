package web.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import web.page.locators.ParcelTrackingLocators;
import web.util.SeleniumExecutor;

import java.util.List;

import static web.util.SeleniumExecutor.clickAnimatedButton;
import static web.util.SeleniumExecutor.waitForElementToBeVisible;

public class ParcelTrackingPage extends BasePage {

    private ParcelTrackingLocators locators;

    public ParcelTrackingPage() {
        super();
        pageUrlPath = "/sledzenie-przesylek";
        locators = new ParcelTrackingLocators();
        PageFactory.initElements(SeleniumExecutor.getWebDriver(), locators);
    }

    public ParcelTrackingPage setParcelNumber(String text) {
        waitForElementToBeVisible(locators.getParcelInputText());
        locators.getParcelInputText().sendKeys(text);
        return this;
    }

    public void clickSearch() {
        clickAnimatedButton(locators.getParcelSearchButton());
    }

    public String getParcelLatestStatus() {
        waitForElementToBeVisible(locators.getStatusContainerLocator());
        return locators.getParcelFinalStatus().getText();
    }

    public List<String> getParcelRegularStatues() {
        waitForElementToBeVisible(locators.getStatusContainerLocator());
        return locators.getParcelRegularStatuses().stream()
                .map(WebElement::getText)
                .map(text -> text.split("\n")[0].trim())
                .toList();
    }
}
