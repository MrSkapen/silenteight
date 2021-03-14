package com.dyrga.silenteight.alg;

import com.dyrga.silenteight.alg.model.GenderAlgRequest;

class GenderAlgRequestImpl implements GenderAlgRequest {
    private final String name;
    private final Gender gender;

    public GenderAlgRequestImpl(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Gender getGender() {
        return gender;
    }
}
