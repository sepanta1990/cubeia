package com.cubeia.exercise.exercise.controller;

import com.cubeia.exercise.exercise.exception.exception.RecordAlreadyExistsException;
import com.cubeia.exercise.exercise.service.TranslationService;
import com.cubeia.exercise.exercise.util.mapper.TranslationMapper;
import com.cubeia.exercise.exercise.dto.Translation;
import com.cubeia.exercise.exercise.exception.exception.RecordNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.mapstruct.factory.Mappers;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Mohammad Fathizadeh 2020-08-03
 */
@RestController
@RequestMapping("/translations")
public class TranslationController {

    private final TranslationService translationService;
    private final TranslationMapper translationMapper = Mappers.getMapper(TranslationMapper.class);

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @ApiOperation(value = "Search translation for a specific key", response = Translation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the tournament object"),
            @ApiResponse(code = 404, message = "No translation found for the key and language code")
    })
    @GetMapping("/search")
    public ResponseEntity<Translation> getTranslation(@RequestParam("languageCode") String languageCode, @RequestParam("key") String key) {
        return translationService.getTranslationByKeyAndLanguageCode(key, languageCode).map(translation -> ResponseEntity.ok(translationMapper.toTranslationDto(translation)))
                .orElseThrow(() -> new RecordNotFoundException("Key not found with language code: " + languageCode));
    }


}
