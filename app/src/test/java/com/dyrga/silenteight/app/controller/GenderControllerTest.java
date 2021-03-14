package com.dyrga.silenteight.app.controller;


import com.dyrga.silenteight.alg.model.GenderAlgRequest;
import com.dyrga.silenteight.app.model.db.NameGender;
import com.dyrga.silenteight.app.service.GenderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GenderController.class)
class GenderControllerTest {

    public static final String PATH = "/tokens";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GenderService genderService;

    @Test
    public void whenGetTokensNullThenStatus200() throws Exception {
        // given
        final Answer<Page<NameGender>> answer = (invocation) -> Page.empty();
        given(genderService.getAllTokens(anyInt(), anyInt())).willAnswer(answer);

        mvc.perform(MockMvcRequestBuilders.get(PATH))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetTokensEmptyThenStatus200() throws Exception {
        // given
        final Answer<Page<NameGender>> answer = (invocation) -> Page.empty();
        given(genderService.getAllTokens(anyInt(), anyInt())).willAnswer(answer);

        // when & then
        mvc.perform(MockMvcRequestBuilders.get(PATH))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetTokensFullThenTokensInResponse() throws Exception {
        // given
        final List<NameGender> tokens = Arrays.asList(
                new NameGender("Jan", GenderAlgRequest.Gender.MALE),
                new NameGender("Maria", GenderAlgRequest.Gender.FEMALE)
        );
        final Page<NameGender> controllerRs = new PageImpl<>(tokens);
        final Answer<Page<NameGender>> answer = (invocation) -> controllerRs;
        given(genderService.getAllTokens(anyInt(), anyInt())).willAnswer(answer);

        // when & then
        final MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andReturn();

        assertThat(result.getResponse()
                .getContentAsString())
                .isEqualToIgnoringWhitespace(objectMapper
                        .writeValueAsString(controllerRs));
    }
}
