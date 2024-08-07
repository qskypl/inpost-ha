package config.converter;

import org.aeonbits.owner.Converter;
import web.enums.DriverType;

import java.lang.reflect.Method;

public class DriverTypeConverter implements Converter<DriverType> {


    @Override
    public DriverType convert(Method method, String input) {
        return DriverType.valueOf(input.toUpperCase());
    }
}
