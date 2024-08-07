package config.factory;

import config.FrameworkConfig;
import org.aeonbits.owner.ConfigCache;

public class ConfigFactory {

    private ConfigFactory() {
    }

    public static FrameworkConfig config() {
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }
}
