package config;

import config.converter.BrowserTypeConverter;
import config.converter.DriverTypeConverter;
import org.aeonbits.owner.Config;
import web.enums.BrowserType;
import web.enums.DriverType;

import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "system:properties",
        "system:env",
        "classpath:local.properties"
})
public interface FrameworkConfig extends Config {

    @DefaultValue("local")
    String environment();

    @Key("webUrl")
    String webURL();

    @Key("apiUrl")
    String apiUrl();

    @Key("browser")
    @ConverterClass(BrowserTypeConverter.class)
    BrowserType browser();

    @Key("driverType")
    @ConverterClass(DriverTypeConverter.class)
    DriverType driverType();

    @Key("gridUrl")
    String gridUrl();
}
