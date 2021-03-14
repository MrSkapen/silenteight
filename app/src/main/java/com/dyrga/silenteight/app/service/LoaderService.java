package com.dyrga.silenteight.app.service;

import com.dyrga.silenteight.app.configuration.EnvironmentProperties;
import com.dyrga.silenteight.app.model.db.NameGender;
import com.dyrga.silenteight.app.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class LoaderService {
    private final EnvironmentProperties properties;
    private final GenderRepository repository;

    @Autowired
    public LoaderService(EnvironmentProperties properties, GenderRepository repository) {
        this.properties = properties;
        this.repository = repository;
    }

    @PostConstruct
    public void init() throws IOException {

        InputStream maleTokensResource = Optional.ofNullable(properties.getMaleTokensPath())
                .map(ClassPathResource::new)
                .orElseThrow(() -> new FileNotFoundException("Male tokens list missing"))
                .getInputStream();

        InputStream femaleTokensResource = Optional.ofNullable(properties.getFemaleTokensPath())
                .map(ClassPathResource::new)
                .orElseThrow(() -> new FileNotFoundException("Female tokens list missing"))
                .getInputStream();

        try (
                BufferedReader maleReader = new BufferedReader(new InputStreamReader(maleTokensResource));
                BufferedReader femaleReader = new BufferedReader(new InputStreamReader(femaleTokensResource))) {

            maleReader.lines()
                    .filter(isCorrectData())
                    .map(line -> new NameGender(line, NameGender.Gender.MALE))
                    .forEach(repository::save);

            femaleReader.lines()
                    .filter(isCorrectData())
                    .map(line -> new NameGender(line, NameGender.Gender.FEMALE))
                    .forEach(repository::save);
        }
    }

    private Predicate<String> isCorrectData() {
        return line -> !line.startsWith("#") && !line.isBlank();
    }
}
