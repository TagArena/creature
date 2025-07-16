package com.tagarena.creature.repository;

import com.tagarena.creature.repository.model.CreatureElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreaturetypeRepository extends JpaRepository<CreatureElement, String> {
}