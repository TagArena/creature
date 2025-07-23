package com.tagarena.creature.service;

import java.time.OffsetDateTime;
import java.util.List;

import com.tagarena.creature.repository.model.CreatureEntity;
import com.tagarena.creature.repository.model.TrainingEntity;
import com.tagarena.creature.service.model.exception.TrainingNotAllowedYetException;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

	private CreatureService creatureService;

	public List<CreatureTraining> getNextTrainings(Long creatureId) {

	}

	public CreatureTraining createCreatureTraining(Long creatureId, CreatureTrainingCreation training) {
		Optional<TrainingEntity> latestTrainingOptional = this.trainingrepository.findByCreatureId();

		if (latestTrainingOptional.isPresent()) {
			TrainingEntity latestTraining = latestTrainingOptional.get();
			if (latestTraining.getCreatedAt() > latestTraining.getNextTrainingFrom()) {
				throw new TrainingNotAllowedYetException();
			}
		}
		TrainingEntity trainingEntity = createTrainingEntity(creatureId, training);

	}

	private TrainingEntity createTrainingEntity(Long creatureId, CreatureTrainingCreation training) {
		TrainingEntity trainingEntity = new TrainingEntity();
		CreatureEntity creature = creatureService.getCreature(creatureId);
		creature.setXp(creature.getXp() + getXpForTraining(training.getType(), creature));
		levelUpCreature(creature);
		trainingEntity.setCreature(creature);
		trainingEntity.setType(training.getType());
		trainingEntity.setCreatedAt(OffsetDateTime.now());
		OffsetDateTime nextTrainingFrom = getNextTrainingFrom(training);
		trainingEntity.setNextTrainingFrom(nextTrainingFrom);
		return trainingEntity;
	}

	private void levelUpCreature(CreatureEntity creature) {
		while (creature.getXp() >= (creature.getLevel() * 2)){
			creature.setXp(creature.getXp() - creature.getLevel() * 2);
			creature.setLevel(creature.getLevel() + 1);
		}
	}

	private Long getXpForTraining(String trainingType, CreatureEntity creature) {
		switch (trainingType) {
			case "EASY":
				return 1 * creature.getLevel();
			case "MEDIUM":
				return 2 * creature.getLevel();
			case "HARD":
				return 3 * creature.getLevel();
			default:
				return 0L;
		}
	}

	private static OffsetDateTime getNextTrainingFrom(CreatureTrainingCreation training) {
		OffsetDateTime nextTrainingFrom;
		switch (training.getType()) {
			case "EASY":
				nextTrainingFrom = OffsetDateTime.now().plusMinutes(1);
				break;
			case "MEDIUM":
				nextTrainingFrom = OffsetDateTime.now().plusMinutes(2);
				break;
			case "HARD":
				nextTrainingFrom = OffsetDateTime.now().plusMinutes(3);
				break;
			default:
				nextTrainingFrom = OffsetDateTime.now();
				break;
		}
		return nextTrainingFrom;
	}
}
