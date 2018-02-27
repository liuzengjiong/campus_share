package com.campus.share.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${login.valid.seconds}")
    private Integer validSeconds;

    @Value("${config.cache.seconds}")
    private Integer configCacheSeconds;

    public Integer getValidSeconds() {
        return validSeconds;
    }

    public Integer getConfigCacheSeconds() {
        return configCacheSeconds;
    }
}
