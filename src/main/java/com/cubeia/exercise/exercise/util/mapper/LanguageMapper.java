package com.cubeia.exercise.exercise.util.mapper;

import com.cubeia.exercise.exercise.entity.Language;
import org.mapstruct.Mapper;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Mapper
public abstract class LanguageMapper {

    public abstract com.cubeia.exercise.exercise.dto.Language toLanguageDto(Language language);

}
