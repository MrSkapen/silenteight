package com.dyrga.silenteight.app.configuration;

import com.dyrga.silenteight.app.handler.RequestGenderModeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new RequestGenderModeConverter());
    }
}
