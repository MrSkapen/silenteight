package com.dyrga.silenteight.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dyrga.silenteight")
public class SilenteightApplication {
    public static void main(String[] args) {
        SpringApplication.run(SilenteightApplication.class, args);
    }
}
