package com.dyrga.silenteight.app.handler;

import com.dyrga.silenteight.alg.GenderAlgFactory;
import com.dyrga.silenteight.app.model.dto.GenderRequest;
import org.springframework.core.convert.converter.Converter;

public class RequestGenderModeConverter implements Converter<String, GenderAlgFactory.AlgMode> {
    @Override
    public GenderAlgFactory.AlgMode convert(String s) {
        return s.equalsIgnoreCase(GenderRequest.Mode.ALL_TOKENS
                .getValue()) ? GenderAlgFactory.AlgMode.ALL : GenderAlgFactory.AlgMode.FIRST;
    }
}
