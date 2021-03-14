package com.dyrga.silenteight.alg;

import com.dyrga.silenteight.alg.model.GenderAlgRequest;
import com.dyrga.silenteight.alg.model.GenderAlgResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderRecognizerAll implements GenderRecognizer {
    @Override
    public GenderAlgResponse getGender(String name, List<GenderAlgRequest> matchingTokens) {
        if(name == null || matchingTokens == null) {
            return GenderAlgResponse.INCONCLUSIVE;
        }

        final String[] names = name.split(" ");
        int maleCounter = 0;
        int femaleCounter = 0;

        for (GenderAlgRequest matchingToken : matchingTokens) {
            for (String nameTmp : names) {
                if (nameTmp.equalsIgnoreCase(matchingToken.getName())) {
                    if (matchingToken.getGender().equals(GenderAlgRequest.Gender.MALE)) {
                        maleCounter++;
                    } else {
                        femaleCounter++;
                    }
                }
            }
        }

        if ((maleCounter == femaleCounter)) {
            return GenderAlgResponse.INCONCLUSIVE;
        }

        return maleCounter > femaleCounter ? GenderAlgResponse.MALE : GenderAlgResponse.FEMALE;
    }
}
