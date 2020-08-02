package com.cubeia.exercise.exercise.repository;

import com.cubeia.exercise.exercise.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    Language findByCode(String code);
}
