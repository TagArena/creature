package com.tagarena.creature.service;

import com.tagarena.creature.rest.model.CreatureDto;

import java.util.List;

public interface CreatureService {

    List<CreatureDto> getStarterCreatures(Long trainerId);
}
