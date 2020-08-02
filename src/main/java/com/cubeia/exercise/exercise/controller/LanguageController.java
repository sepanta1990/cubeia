package com.cubeia.exercise.exercise.controller;

import com.cubeia.exercise.exercise.exception.exception.RecordAlreadyExistsException;
import com.cubeia.exercise.exercise.service.LanguageService;
import com.cubeia.exercise.exercise.util.mapper.LanguageMapper;
import com.cubeia.exercise.exercise.dto.Language;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Api(tags = {"Languages"})
@SwaggerDefinition(tags = {
        @Tag(name = "Languages", description = "Operations related to languages")
})
@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService languageService;
    private final LanguageMapper languageMapper = Mappers.getMapper(LanguageMapper.class);

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @ApiOperation(value = "Create a new language", response = Language.class)
    @PostMapping("/")
    public ResponseEntity<Language> addLanguage(@RequestBody Language language) {
        return Optional.ofNullable(languageService.addLanguage(language)).
                map(lg -> ResponseEntity.ok(languageMapper.toLanguageDto(lg))).orElseThrow(() ->
                new RecordAlreadyExistsException("Language already exists with code: " + language.getCode())
        );
    }
}
