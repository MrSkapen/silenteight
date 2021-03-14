package com.dyrga.silenteight.app.model.dto;

import com.dyrga.silenteight.alg.model.GenderAlgResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderResponse {
    private GenderAlgResponse gender;
}
