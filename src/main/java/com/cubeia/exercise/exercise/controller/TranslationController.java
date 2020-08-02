package com.cubeia.exercise.exercise.controller;

import com.cubeia.exercise.exercise.dto.Translation;
import com.cubeia.exercise.exercise.exception.exception.RecordAlreadyExistsException;
import com.cubeia.exercise.exercise.exception.exception.RecordNotFoundException;
import com.cubeia.exercise.exercise.service.TranslationService;
import com.cubeia.exercise.exercise.util.mapper.TranslationMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.mapstruct.factory.Mappers;
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

    @ApiOperation(value = "Create a new translation", response = Translation.class)
    @PostMapping("/{languageCode}")
    public ResponseEntity<Translation> addTranslation(@PathVariable("languageCode") String languageCode, @RequestBody Translation translation) {
        return Optional.ofNullable(translationService.addTranslation(translation, languageCode)).
                map(tr -> ResponseEntity.ok(translationMapper.toTranslationDto(tr)))
                .orElseThrow(() -> new RecordAlreadyExistsException("Translation already exists with key: " + translation.getKey()));

    }

    @ApiOperation(value = "Update a particular translation by id", response = Translation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the translation object"),
            @ApiResponse(code = 404, message = "You have entered an invalid translation id")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Translation> updateTranslation(@PathVariable("id") Integer id, @RequestBody Translation translation) {
        return translationService.updateTranslation(id, translation).map(tr -> ResponseEntity.ok(translationMapper.toTranslationDto(tr)))
                .orElseThrow(() -> new RecordNotFoundException("Translation not found with id: " + id));
    }

    @ApiOperation(value = "Delete a translation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the translation"),
            @ApiResponse(code = 404, message = "You have entered an invalid translation id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTranslation(@PathVariable("id") Integer id) {
        if (!translationService.deleteTranslationById(id))
            throw new RecordNotFoundException("Translation not found with id: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
