package com.tagarena.creature.service;

import java.util.List;
import com.tagarena.creature.repository.CreatureEntityRepository;
import com.tagarena.creature.repository.CreatureSpeciesRepository;
import com.tagarena.creature.repository.model.CreatureElement;
import com.tagarena.creature.repository.model.CreatureEntity;
import com.tagarena.creature.repository.model.CreatureSpeciesEntity;
import com.tagarena.creature.rest.model.CreatureDto;
import com.tagarena.creature.service.model.exception.CreatureSpeciesNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

	public List<CreatureTraining> getNextTrainings(Long creatureId) {

	}

	public CreatureTraining createCreatureTraining(Long creatureId, CreatureTrainingCreation training) {
		Optional<TrainingEntity> latestTrainingOptional = this.trainingrepository.findByCreatureId();

		if (latestTrainingOptional.isPresent()){
			TrainingEntity latestTraining = latestTrainingOptional.get();
			if (latestTraining.getCreationdateTime() <= latestTraining.getNetTrainingAllowedAt()){
				throw new TrainingNotAllowedYetException()
			}
		}
		TrainingEntity lastTraining = getLatestTrainingEntity(creatureId);
		TrainingEntity trainingEntity = new TrainingEntity();
	}
}
