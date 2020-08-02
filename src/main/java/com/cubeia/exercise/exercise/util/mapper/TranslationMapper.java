package com.cubeia.exercise.exercise.util.mapper;

import com.cubeia.exercise.exercise.entity.Translation;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Mapper
public abstract class TranslationMapper {
    public abstract List<com.cubeia.exercise.exercise.dto.Translation> toTranslationDto(List<Translation> titleList);

    public abstract com.cubeia.exercise.exercise.dto.Translation toTranslationDto(Translation translation);

}
