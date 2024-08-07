package web.module.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.util.LocatorsCommon;

public class CookiesPopupLocators extends LocatorsCommon {

    private final By rejectAllButton = By.cssSelector("#onetrust-reject-all-handler");

    public WebElement getRejectAllButton() {
        return getWebDriver().findElement(rejectAllButton);
    }


}
