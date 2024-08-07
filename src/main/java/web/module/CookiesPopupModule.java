package web.module;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;
import web.module.locators.CookiesPopupLocators;
import web.util.SeleniumExecutor;

import static web.util.SeleniumExecutor.waitForElementToBeVisible;

@Slf4j
public class CookiesPopupModule {

    private CookiesPopupLocators locators;

    public CookiesPopupModule() {
        locators = new CookiesPopupLocators();
        PageFactory.initElements(SeleniumExecutor.getWebDriver(), locators);
    }

    public void rejectCookies() {
        try {
            waitForElementToBeVisible(locators.getRejectAllButton());
            locators.getRejectAllButton().click();
            log.info("Cookie Popup - Reject button clicked.");
        } catch (Exception e) {
            log.debug("Cookie popup not found or already handled: " + e.getMessage());
        }
    }
}
