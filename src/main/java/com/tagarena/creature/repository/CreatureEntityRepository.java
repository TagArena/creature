package com.tagarena.creature.repository;

import com.tagarena.creature.repository.model.CreatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CreatureEntityRepository extends JpaRepository<CreatureEntity, Long> {
  Collection<CreatureEntity> findByTrainerid(Long trainerid);

  List<CreatureEntity> findByTraineridAndActive(Long trainerid, Boolean active);
}