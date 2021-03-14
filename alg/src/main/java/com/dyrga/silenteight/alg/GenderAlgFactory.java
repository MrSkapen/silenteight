package com.dyrga.silenteight.alg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenderAlgFactory {
    private final GenderRecognizerFirst genderRecognizerFirst;
    private final GenderRecognizerAll genderRecognizerAll;

    @Autowired
    public GenderAlgFactory(final GenderRecognizerFirst genderRecognizerFirst,
                            final GenderRecognizerAll genderRecognizerAll) {
        this.genderRecognizerFirst = genderRecognizerFirst;
        this.genderRecognizerAll = genderRecognizerAll;
    }

    public com.dyrga.silenteight.alg.GenderRecognizer getGenderRecognizer(AlgMode mode) {
        if (mode.equals(AlgMode.ALL)) {
            return genderRecognizerAll;
        } else {
            return genderRecognizerFirst;
        }
    }

    public enum AlgMode {
        FIRST, ALL
    }
}
