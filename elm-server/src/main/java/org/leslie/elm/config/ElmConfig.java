package org.leslie.elm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhang
 * date created in 2023/3/17 02:06
 */
@Data
@Component
@ConfigurationProperties(prefix = "elm")
public class ElmConfig {

    private String amapKey;
    private String amapUrl;
    private String defaultCityName;
}
