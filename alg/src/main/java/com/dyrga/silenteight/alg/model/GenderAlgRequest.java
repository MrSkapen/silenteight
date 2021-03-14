package com.dyrga.silenteight.alg.model;

public interface GenderAlgRequest {
    String getName();

    Gender getGender();

    enum Gender {
        MALE, FEMALE
    }
}
