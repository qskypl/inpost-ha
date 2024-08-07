package config.converter;

import org.aeonbits.owner.Converter;
import web.enums.BrowserType;

import java.lang.reflect.Method;

public class BrowserTypeConverter implements Converter<BrowserType> {


    @Override
    public BrowserType convert(Method method, String input) {
        return BrowserType.valueOf(input.toUpperCase());
    }
}
