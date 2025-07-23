package com.tagarena.creature.repository;

import com.tagarena.creature.repository.model.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingEntityRepository extends JpaRepository<TrainingEntity, Long> {
    Optional<TrainingEntity> findFirstByCreature_IdOrderByNextTrainingFromDesc(Long id);
}