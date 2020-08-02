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

}
