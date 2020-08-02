package com.cubeia.exercise.exercise.service;

import com.cubeia.exercise.exercise.entity.Translation;
import com.cubeia.exercise.exercise.repository.LanguageRepository;
import com.cubeia.exercise.exercise.repository.TranslationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TranslationServiceTest {

    @MockBean
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationService translationService;

    @Test
    public void addTranslationTest() {
        Translation translationToBeSaved = new Translation(null, "buy-chips", "buy chips", null);
        Translation translationSaved = new Translation(1, "buy-chips", "buy chips", null);


        when(translationRepository.findByKeyAndLanguageCode("buy-chips", "en")).thenReturn(null);
        when(translationRepository.save(translationToBeSaved)).thenReturn(translationSaved);

        assertEquals(translationSaved, translationService.addTranslation(new com.cubeia.exercise.exercise.dto.Translation(null, "buy-chips", "buy chips"), "en"));


        when(translationRepository.findByKeyAndLanguageCode("buy-chips", "en")).thenReturn(new Translation());
        assertNull(translationService.addTranslation(new com.cubeia.exercise.exercise.dto.Translation(null, "buy-chips", "buy chips"), "en"));

    }

    @Test
    public void updateTranslationTest() {
        Translation translationToBeUpdated = new Translation(1, "buy-chips", "buy chips", null);
        Translation translationUpdated = new Translation(1, "dont-buy-chips", "dont buy chips", null);


        when(translationRepository.save(translationToBeUpdated)).thenReturn(translationUpdated);
        when(translationRepository.findOne(1)).thenReturn(translationToBeUpdated);

        assertEquals(Optional.of(translationUpdated), translationService.updateTranslation(1, new com.cubeia.exercise.exercise.dto.Translation(1, "dont-buy-chips", "dont buy chips")));

        when(translationRepository.findOne(1)).thenReturn(null);
        assertEquals(Optional.empty(), translationService.updateTranslation(1, new com.cubeia.exercise.exercise.dto.Translation(1, "dont-buy-chips", "dont buy chips")));

    }

}
