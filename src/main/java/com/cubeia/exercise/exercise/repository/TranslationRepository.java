package com.cubeia.exercise.exercise.repository;

import com.cubeia.exercise.exercise.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Mohammad Fathizadeh 2020-08-02
 */
@Repository
public interface TranslationRepository extends JpaRepository<Translation, Integer> {

    @Query(value = "SELECT t FROM Translation t INNER JOIN t.languages l WHERE l.code= ?2 AND t.key=?1")
    Translation findByKeyAndLanguageCode(String key, String languageCode);

}
