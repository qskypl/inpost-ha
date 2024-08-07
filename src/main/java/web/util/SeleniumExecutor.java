package web.util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SeleniumExecutor extends WebDriverFactory {

    public SeleniumExecutor() {
        super();
    }

    public static WebDriverFactory getExecutor() {
        startExecutor();
        return executor;
    }

    public static void stop() {
        if (webDriver != null) {
            webDriver.quit();
        }
        executor = null;
    }

    public static String getTitle() {
        return webDriver.getTitle();
    }

    public static String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public static void openPage(String url) {
        try {
            webDriver.navigate().to(url);
        } catch (UnhandledAlertException e) {
            webDriver.switchTo().alert().accept();
            webDriver.navigate().to(url);
        }
    }

    public static void waitForPageLoad() {
        WebDriverWait wait = SeleniumExecutor.getWaitDriver();

        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        wait.until(pageLoadCondition);

        wait.until(jQueryAJAXCallsCompleted());
    }

    public static void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = WebDriverFactory.getWaitDriver();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeVisible(By by) {
        WebDriverWait wait = WebDriverFactory.getWaitDriver();
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = WebDriverFactory.getWaitDriver();
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void clickAnimatedButton(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot() throws IOException {
        return Files.readAllBytes(Paths.get("target/allure/screenshots"));
    }

    private static ExpectedCondition<Boolean> jQueryAJAXCallsCompleted() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            }
        };
    }

    private static void startExecutor() {
        if (executor == null) {
            executor = new SeleniumExecutor();
        }
    }
}
