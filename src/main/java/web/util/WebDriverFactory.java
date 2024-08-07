package web.util;

import config.FrameworkConfig;
import config.factory.ConfigFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.enums.BrowserType;
import web.enums.DriverType;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

@Slf4j
abstract class WebDriverFactory {

    public static final int TIMEOUT = 5;
    public static final int PAGE_LOAD_TIMEOUT = 60;

    @Getter
    protected static WebDriverFactory executor;
    @Getter
    protected static WebDriver webDriver;
    @Getter
    private static WebDriverWait waitDriver;

    private static final FrameworkConfig CONFIG = ConfigFactory.config();
    private static final String URL = CONFIG.webURL();
    private static final String GRID_URL = CONFIG.gridUrl();
    private static final BrowserType BROWSER = CONFIG.browser();
    private static final DriverType DRIVER_TYPE = CONFIG.driverType();
    public static String defaultUrl;

    protected WebDriverFactory() {
        webDriver = createWebDriver();
        defaultUrl = URL;
    }

    private static ChromeOptions getChromeOptions() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-print-preview");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--no-default-browser-check");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");

        chromeOptions.addArguments("--remote-allow-origins=*", "--start-maximized", "--disable-default-apps",
                "--no-first-run", "--disable-notifications",
                "--use-fake-ui-for-media-stream", "use-fake-device-for-media-stream");
        return chromeOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel,application/pdf");
        firefoxProfile.setPreference("pdfjs.disabled", true);
        firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
        firefoxProfile.setPreference("plugin.scan.plid.all", false);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);

        return firefoxOptions;
    }

    private static WebDriver createWebDriver() {
        switch (BROWSER) {
            case CHROME -> {
                switch (DRIVER_TYPE) {
                    case LOCAL -> webDriver = new ChromeDriver(getChromeOptions());
                    case REMOTE -> {
                        try {
                            webDriver = new RemoteWebDriver(new URL(GRID_URL), getChromeOptions());
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            case FIREFOX -> {
                switch (DRIVER_TYPE) {
                    case LOCAL -> webDriver = new FirefoxDriver(getFirefoxOptions());
                    case REMOTE -> {
                        try {
                            webDriver = new RemoteWebDriver(new URL(GRID_URL), getFirefoxOptions());
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        webDriver.manage().window().maximize();
        waitDriver = new WebDriverWait(webDriver, Duration.ofSeconds(TIMEOUT));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));

        return webDriver;
    }
}
