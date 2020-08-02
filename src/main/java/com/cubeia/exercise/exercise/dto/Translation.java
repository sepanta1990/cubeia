package com.cubeia.exercise.exercise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Translation {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private String key;
    private String meaning;
}
