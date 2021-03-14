package com.dyrga.silenteight.alg;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GenderAlgFactoryTest {
    @InjectMocks
    private GenderAlgFactory genderAlgFactory;

    @Mock
    private GenderRecognizerAll genderRecognizerAll;
    @Mock
    private GenderRecognizerFirst genderRecognizerFirst;

    @Test
    public void givenModeAllThenRecognizerAll() {
        // given
        final GenderAlgFactory.AlgMode all = GenderAlgFactory.AlgMode.ALL;

        // when
        final GenderRecognizer result = genderAlgFactory.getGenderRecognizer(all);

        // then
        assertThat(result).isEqualTo(genderRecognizerAll);
    }

    @Test
    public void givenModeFirstThenRecognizerFirst() {
        // given
        final GenderAlgFactory.AlgMode first = GenderAlgFactory.AlgMode.FIRST;

        // when
        final GenderRecognizer result = genderAlgFactory.getGenderRecognizer(first);

        // then
        assertThat(result).isEqualTo(genderRecognizerFirst);
    }
}
