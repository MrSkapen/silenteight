package com.dyrga.silenteight.app.controller;

import com.dyrga.silenteight.alg.GenderAlgFactory;
import com.dyrga.silenteight.app.model.db.NameGender;
import com.dyrga.silenteight.app.model.dto.GenderRequest;
import com.dyrga.silenteight.app.model.dto.GenderResponse;
import com.dyrga.silenteight.app.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class GenderController {

    private final GenderService service;

    @Autowired
    public GenderController(GenderService service) {
        this.service = service;
    }

    @GetMapping("/tokens")
    public Page<NameGender> getAllTokens(
            @RequestParam(name = "limit", defaultValue = "1000") final Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") final Integer offset) {

        return service.getAllTokens(limit, offset);
    }

    @PostMapping("/gender")
    public GenderResponse guessGender(@Valid @RequestBody final GenderRequest genderRequest,
                                      @RequestParam(name = "mode", required = false, defaultValue = "first") final GenderAlgFactory.AlgMode mode) {
        return service.getGender(genderRequest, mode);
    }
}
