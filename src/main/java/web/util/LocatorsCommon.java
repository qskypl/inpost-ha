package web.util;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class LocatorsCommon {

    private WebDriver webDriver;

    public LocatorsCommon() {
        webDriver = SeleniumExecutor.getWebDriver();
    }

}
