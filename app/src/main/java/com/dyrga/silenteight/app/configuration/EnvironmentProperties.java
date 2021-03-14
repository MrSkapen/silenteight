package com.dyrga.silenteight.app.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class EnvironmentProperties {
    private String apiVersion;
    private String maleTokensPath;
    private String femaleTokensPath;
}
