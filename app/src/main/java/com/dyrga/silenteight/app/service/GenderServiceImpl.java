package com.dyrga.silenteight.app.service;

import com.dyrga.silenteight.alg.GenderAlgFactory;
import com.dyrga.silenteight.alg.GenderRecognizer;
import com.dyrga.silenteight.alg.model.GenderAlgRequest;
import com.dyrga.silenteight.alg.model.GenderAlgResponse;
import com.dyrga.silenteight.app.model.db.NameGender;
import com.dyrga.silenteight.app.model.dto.GenderRequest;
import com.dyrga.silenteight.app.model.dto.GenderResponse;
import com.dyrga.silenteight.app.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;
    private final GenderAlgFactory genderAlgFactory;

    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository, GenderAlgFactory genderAlgFactory) {
        this.genderRepository = genderRepository;
        this.genderAlgFactory = genderAlgFactory;
    }

    @Override
    public Page<NameGender> getAllTokens(final int limit, final int offset) {
        return genderRepository.findAll(PageRequest.of(offset, limit, Sort.by("name")
                .ascending()));
    }

    @Override
    public GenderResponse getGender(final GenderRequest genderRequest, final GenderAlgFactory.AlgMode mode) {
        List<GenderAlgRequest> collect = genderRepository.getMatchingTokens(genderRequest.getName()
                .split(" "));

        final GenderRecognizer genderRecognizer = genderAlgFactory.getGenderRecognizer(mode);

        GenderAlgResponse gender = genderRecognizer.getGender(genderRequest.getName(), collect);
        return new GenderResponse(gender);
    }
}
