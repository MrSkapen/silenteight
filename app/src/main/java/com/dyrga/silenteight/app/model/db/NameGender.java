package com.dyrga.silenteight.app.model.db;

import com.dyrga.silenteight.alg.model.GenderAlgRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NameGender implements GenderAlgRequest {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private Gender gender;

    public NameGender(final String name, final Gender gender) {
        this.name = name;
        this.gender = gender;
    }
}
