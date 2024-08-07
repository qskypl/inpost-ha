package web;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber.json", "pretty", "html:target/cucumber.html"},
        features = "src/test/resources/web/features",
        glue = {"web.step_definitions", "web.hooks"}
)
public class WebTestRunner {
}
