package com.tagarena.creature.repository;

import com.tagarena.creature.repository.model.CreatureSpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreatureSpeciesRepository extends JpaRepository<CreatureSpeciesEntity, Long> {

    Optional<CreatureSpeciesEntity> findByName(String name);
}