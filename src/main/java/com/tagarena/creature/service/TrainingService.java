package com.tagarena.creature.service;

import com.tagarena.creature.rest.model.CreatureTrainingCreationDto;
import com.tagarena.creature.rest.model.CreatureTrainingDto;

public interface TrainingService {

    CreatureTrainingDto createCreatureTraining(Long creatureId, CreatureTrainingCreationDto training);
}
