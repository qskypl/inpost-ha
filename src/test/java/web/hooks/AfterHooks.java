package web.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import web.util.SeleniumExecutor;

public class AfterHooks {

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            attachScreenshotPNG();
        }

        SeleniumExecutor.stop();
    }

    @AfterAll()
    public static void cleanUp() {
        SeleniumExecutor.stop();
    }

    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
    public byte[] attachScreenshotPNG() {
        return ((TakesScreenshot) SeleniumExecutor.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
