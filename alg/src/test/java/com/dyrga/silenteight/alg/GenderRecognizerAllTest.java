package com.dyrga.silenteight.alg;

import com.dyrga.silenteight.alg.model.GenderAlgRequest;
import com.dyrga.silenteight.alg.model.GenderAlgResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GenderRecognizerAllTest {

    @InjectMocks
    private GenderRecognizerAll genderRecognizerAll;

    @Test
    public void whenPassedNullsThenInconclusive() {
        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender(null, null);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void whenPassedNameNullThenInconclusive() {
        final String name = "Jan";
        final List<GenderAlgRequest> genderAlgRequests = Collections.singletonList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.MALE));
        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender(null, genderAlgRequests);

        // then

    }

    @Test
    public void whenPassedTokensNullThenInconclusive() {
        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender("Jan", null);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void givenNameNoTokensThenInconclusive() {
        // given
        final String name = "Jan";

        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender(name, Collections.emptyList());

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void givenNameTokensMaleThenMale() {
        // given
        final String name = "Jan";
        final String name2 = "Zygmunt";
        final List<GenderAlgRequest> genderAlgRequests = Arrays.asList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.MALE),
                new GenderAlgRequestImpl(name2, GenderAlgRequest.Gender.MALE));

        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender(name, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.MALE);
    }

    @Test
    public void givenNameTokensFemaleThenFemale() {
        // given
        final String name = "Maria";
        final String name2 = "Anna";
        final List<GenderAlgRequest> genderAlgRequests = Arrays.asList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.FEMALE),
                new GenderAlgRequestImpl(name2, GenderAlgRequest.Gender.FEMALE));

        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender(name, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.FEMALE);
    }

    @Test
    public void givenNameTokensInconclusiveThenInconclusive() {
        // given
        final String name = "Maria";
        final String name2 = "Anna";
        final String name3 = "x";
        final List<GenderAlgRequest> genderAlgRequests = Arrays.asList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.FEMALE),
                new GenderAlgRequestImpl(name2, GenderAlgRequest.Gender.FEMALE));

        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender(name3, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void givenNameTokensInconclusiveAndFemaleThenFemale() {
        // given
        final String name = "Maria";
        final String name2 = "Anna";
        final String name3 = "x Maria";
        final List<GenderAlgRequest> genderAlgRequests = Arrays.asList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.FEMALE),
                new GenderAlgRequestImpl(name2, GenderAlgRequest.Gender.FEMALE));

        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender(name3, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.FEMALE);
    }

    @Test
    public void givenNameTokensMaleAndFemaleThenInconclusive() {
        // given
        final String name = "Maria";
        final String name2 = "Jan";
        final String name3 = "Jan Maria";
        final List<GenderAlgRequest> genderAlgRequests = Arrays.asList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.FEMALE),
                new GenderAlgRequestImpl(name2, GenderAlgRequest.Gender.MALE));

        // when
        final GenderAlgResponse result = genderRecognizerAll.getGender(name3, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

}
