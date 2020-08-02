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
@EqualsAndHashCode(exclude = "languages")
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String key;
    @Column
    private String meaning;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "language_translation", joinColumns = {
            @JoinColumn(name = "language_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "translation_id",
                    nullable = false, updatable = false)})
    private Set<Language> languages = new HashSet<>();
}
