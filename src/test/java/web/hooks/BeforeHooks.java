package web.hooks;

import io.cucumber.java.Before;
import web.util.SeleniumExecutor;

public class BeforeHooks {

    @Before(order = 1)
    public void initializeProperties() {
        SeleniumExecutor.getExecutor();
    }
}

