package com.tagarena.creature.service;

import com.tagarena.creature.repository.TrainingEntityRepository;
import com.tagarena.creature.repository.model.CreatureEntity;
import com.tagarena.creature.repository.model.TrainingEntity;
import com.tagarena.creature.rest.model.CreatureTrainingCreationDto;
import com.tagarena.creature.rest.model.CreatureTrainingDto;
import com.tagarena.creature.service.model.exception.TrainingNotAllowedYetException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

	private final CreatureService creatureService;

	private final TrainingEntityRepository trainingEntityRepository;

	private final ModelMapper modelMapper;

	public CreatureTrainingDto createCreatureTraining(Long creatureId, CreatureTrainingCreationDto training) {
		Optional<TrainingEntity> latestTrainingOptional = trainingEntityRepository.findFirstByCreature_IdOrderByNextTrainingFromDesc(creatureId);

		if (latestTrainingOptional.isPresent()) {
			TrainingEntity latestTraining = latestTrainingOptional.get();
			if (OffsetDateTime.now().isBefore(latestTraining.getNextTrainingFrom())) {
				throw new TrainingNotAllowedYetException("Training is not allowed yet");
			}
		}
		TrainingEntity trainingEntity = createTrainingEntity(creatureId, training);
		TrainingEntity savedTraining = trainingEntityRepository.save(trainingEntity);
		return modelMapper.map(savedTraining, CreatureTrainingDto.class);

	}

	private TrainingEntity createTrainingEntity(Long creatureId, CreatureTrainingCreationDto training) {
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
        return switch (trainingType) {
            case "EASY" -> creature.getLevel();
            case "MEDIUM" -> 2 * creature.getLevel();
            case "HARD" -> 3 * creature.getLevel();
            default -> 0L;
        };
	}

	private static OffsetDateTime getNextTrainingFrom(CreatureTrainingCreationDto training) {
		return switch (training.getType()) {
            case "EASY" -> OffsetDateTime.now().plusMinutes(1);
            case "MEDIUM" -> OffsetDateTime.now().plusMinutes(2);
            case "HARD" -> OffsetDateTime.now().plusMinutes(3);
            default -> OffsetDateTime.now();
        };
	}
}
