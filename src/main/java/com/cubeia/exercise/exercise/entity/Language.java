package com.cubeia.exercise.exercise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "translations")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String code;
    @Column
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "languages")
    private Set<Translation> translations = new HashSet<>();
}
