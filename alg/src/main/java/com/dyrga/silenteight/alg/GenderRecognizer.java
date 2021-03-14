package com.dyrga.silenteight.alg;

import com.dyrga.silenteight.alg.model.GenderAlgRequest;
import com.dyrga.silenteight.alg.model.GenderAlgResponse;

import java.util.List;

public interface GenderRecognizer {
    GenderAlgResponse getGender(String name, List<GenderAlgRequest> matchingTokens);
}
