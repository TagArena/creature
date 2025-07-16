package com.tagarena.creature.repository;

import com.tagarena.creature.repository.model.CreatureSpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureEntityRepository extends JpaRepository<CreatureSpeciesEntity, Long> {

}