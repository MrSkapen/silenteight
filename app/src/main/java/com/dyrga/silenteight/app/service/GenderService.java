package com.dyrga.silenteight.app.service;

import com.dyrga.silenteight.alg.GenderAlgFactory;
import com.dyrga.silenteight.app.model.db.NameGender;
import com.dyrga.silenteight.app.model.dto.GenderRequest;
import com.dyrga.silenteight.app.model.dto.GenderResponse;
import org.springframework.data.domain.Page;

public interface GenderService {
    Page<NameGender> getAllTokens(int limit, int offset);

    GenderResponse getGender(GenderRequest genderRequest, GenderAlgFactory.AlgMode mode);
}
