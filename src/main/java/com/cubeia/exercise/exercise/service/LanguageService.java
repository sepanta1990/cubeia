package com.cubeia.exercise.exercise.service;

import com.cubeia.exercise.exercise.entity.Language;
import com.cubeia.exercise.exercise.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Service
@Transactional
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language addLanguage(com.cubeia.exercise.exercise.dto.Language language) {
        if (languageRepository.findByCode(language.getCode()) != null) {
            return null;
        }
        Language languageEnt = new Language();
        languageEnt.setName(language.getName());
        languageEnt.setCode(language.getCode());
        return languageRepository.save(languageEnt);
    }

}
