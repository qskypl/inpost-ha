package web.page;

import lombok.Data;
import web.module.CookiesPopupModule;
import web.util.SeleniumExecutor;

@Data
public abstract class BasePage {

    protected String pageUrlPath = "";
    private CookiesPopupModule cookiesPopupModule;

    protected BasePage() {
        SeleniumExecutor.openPage(SeleniumExecutor.defaultUrl + pageUrlPath);
        SeleniumExecutor.waitForPageLoad();
        this.cookiesPopupModule = new CookiesPopupModule();
        handleCookiePopup();
    }

    private void handleCookiePopup() {
        cookiesPopupModule.rejectCookies();
    }

    public void openPage() {
        SeleniumExecutor.openPage(SeleniumExecutor.defaultUrl + pageUrlPath);
        SeleniumExecutor.waitForPageLoad();
        handleCookiePopup();
    }
}
