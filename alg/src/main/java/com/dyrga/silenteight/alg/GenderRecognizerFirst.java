package com.dyrga.silenteight.alg;

import com.dyrga.silenteight.alg.model.GenderAlgRequest;
import com.dyrga.silenteight.alg.model.GenderAlgResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderRecognizerFirst implements GenderRecognizer {
    @Override
    public GenderAlgResponse getGender(String name, List<GenderAlgRequest> matchingTokens) {
        if(name == null || matchingTokens == null) {
            return GenderAlgResponse.INCONCLUSIVE;
        }

        final String firstName = name.split(" ")[0];
        int maleCounter = 0;
        int femaleCounter = 0;

        for (GenderAlgRequest matchingToken : matchingTokens) {
            if (firstName.equalsIgnoreCase(matchingToken.getName())) {
                if (matchingToken.getGender().equals(GenderAlgRequest.Gender.MALE)) {
                    maleCounter++;
                } else {
                    femaleCounter++;
                }
            }
        }

        if ((maleCounter > 0 && femaleCounter > 0)
                || (maleCounter == 0 && femaleCounter == 0)) {
            return GenderAlgResponse.INCONCLUSIVE;
        }

        return maleCounter > 0 ? GenderAlgResponse.MALE : GenderAlgResponse.FEMALE;
    }
}
