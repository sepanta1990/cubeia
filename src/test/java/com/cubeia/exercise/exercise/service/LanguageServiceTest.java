package com.cubeia.exercise.exercise.service;


import com.cubeia.exercise.exercise.entity.Language;
import com.cubeia.exercise.exercise.repository.LanguageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LanguageServiceTest {
    @MockBean
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageService languageService;

    @Test
    public void addLanguageTest() {
        Language languageToSave = new Language(null, "en", "english", null);
        Language savedLanguage = new Language(1, "en", "english", null);

        when(languageRepository.save(languageToSave)).thenReturn(savedLanguage);
        when(languageRepository.findByCode("en")).thenReturn(null);

        assertEquals(languageService.addLanguage(new com.cubeia.exercise.exercise.dto.Language(null, "en", "english")), savedLanguage);


        when(languageRepository.findByCode("en")).thenReturn(new Language());
        Assert.assertNull(languageService.addLanguage(new com.cubeia.exercise.exercise.dto.Language(null, "en", "english")));


    }
}
