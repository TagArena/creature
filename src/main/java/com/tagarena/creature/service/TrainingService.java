package com.tagarena.creature.service;

import java.util.List;

import com.tagarena.creature.rest.model.CreatureDto;

public interface TrainingService {

    List<CreatureTraining> getNextTrainings(Long creatureId);

    CreatureTraining createCreatureTraining(Long creatureId, CreatureTrainingCreation training);
}
