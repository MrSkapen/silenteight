package com.dyrga.silenteight.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderRequest {
    @Pattern(regexp = "^(([A-Za-z])+(\\s)*)*$", message = "Invalid Input")
    private String name;

    @Getter
    public enum Mode {
        FIRST_TOKEN("first"), ALL_TOKENS("all");

        private final String value;

        Mode(String value) {
            this.value = value;
        }
    }
}
