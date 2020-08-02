package com.cubeia.exercise.exercise.service;

import com.cubeia.exercise.exercise.entity.Language;
import com.cubeia.exercise.exercise.entity.Translation;
import com.cubeia.exercise.exercise.repository.LanguageRepository;
import com.cubeia.exercise.exercise.repository.TranslationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Service
@Transactional
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final LanguageRepository languageRepository;

    public TranslationService(TranslationRepository translationRepository, LanguageRepository languageRepository) {
        this.translationRepository = translationRepository;
        this.languageRepository = languageRepository;
    }

    public Translation addTranslation(com.cubeia.exercise.exercise.dto.Translation translation, String languageCode) {
        if (translationRepository.findByKeyAndLanguageCode(translation.getKey(), languageCode) != null)
            return null;

        Translation newTranslation = new Translation();
        newTranslation.setKey(translation.getKey());
        newTranslation.setMeaning(translation.getMeaning());

        Language language = languageRepository.findByCode(languageCode);

        newTranslation.getLanguages().add(language);

        return translationRepository.save(newTranslation);
    }

    public Optional<Translation> updateTranslation(Integer id, com.cubeia.exercise.exercise.dto.Translation translation) {
        return Optional.ofNullable(translationRepository.findOne(id)).map(translationEnt -> {
            translationEnt.setKey(translation.getKey());
            translationEnt.setMeaning(translation.getMeaning());
            return translationRepository.save(translationEnt);
        });
    }

}
