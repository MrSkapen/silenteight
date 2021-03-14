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
class GenderRecognizerFirstTest {

    @InjectMocks
    private GenderRecognizerFirst genderRecognizerFirst;

    @Test
    public void whenPassedNullsThenInconclusive() {
        // when
        final GenderAlgResponse result = genderRecognizerFirst.getGender(null, null);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void whenPassedNameNullThenInconclusive() {
        final String name = "Jan";
        final List<GenderAlgRequest> genderAlgRequests = Collections.singletonList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.MALE));
        // when
        final GenderAlgResponse result = genderRecognizerFirst.getGender(null, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void whenPassedTokensNullThenInconclusive() {
        // when
        final GenderAlgResponse result = genderRecognizerFirst.getGender("Jan", null);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void givenNameNoTokensThenInconclusive() {
        // given
        final String name = "Jan";

        // when
        final GenderAlgResponse result = genderRecognizerFirst.getGender(name, Collections.emptyList());

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void givenNameTokenMaleThenMale() {
        // given
        final String name = "Jan";
        final List<GenderAlgRequest> genderAlgRequests = Collections.singletonList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.MALE));

        // when
        final GenderAlgResponse result = genderRecognizerFirst.getGender(name, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.MALE);
    }

    @Test
    public void givenNameTokenMaleAndFemaleThenInconclusive() {
        // given
        final String name = "Jan";
        final List<GenderAlgRequest> genderAlgRequests = Arrays.asList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.MALE),
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.FEMALE));

        // when
        final GenderAlgResponse result = genderRecognizerFirst.getGender(name, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.INCONCLUSIVE);
    }

    @Test
    public void givenNameTokenFemaleThenFemale() {
        // given
        final String name = "Maria";
        final List<GenderAlgRequest> genderAlgRequests = Collections.singletonList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.FEMALE));

        // when
        final GenderAlgResponse result = genderRecognizerFirst.getGender(name, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.FEMALE);
    }


    @Test
    public void givenNameTokenMaleAndMaleThenMale() {
        // given
        final String name = "Jan";
        final String name2 = "Maria";
        final List<GenderAlgRequest> genderAlgRequests = Arrays.asList(
                new GenderAlgRequestImpl(name, GenderAlgRequest.Gender.MALE),
                new GenderAlgRequestImpl(name2, GenderAlgRequest.Gender.MALE));

        // when
        final GenderAlgResponse result = genderRecognizerFirst.getGender(name, genderAlgRequests);

        // then
        assertThat(result).isEqualTo(GenderAlgResponse.MALE);
    }

}
