package com.tagarena.creature.service;

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
@RequiredArgsConstructor
public class CreatureServiceImpl implements CreatureService {

    private final CreatureSpeciesRepository creatureSpeciesRepository;

    private final ModelMapper modelMapper;
    private final CreatureEntityRepository creatureEntityRepository;

    @Override
    public List<CreatureDto> getStarterCreatures(Long trainerId) {
        List<CreatureDto> starterCreatures = new ArrayList<>();

        long lastStarterCreatureId = isLiquorNumber(trainerId) ? 4 : 3;
        for (long i = 1; i <= lastStarterCreatureId; i++) {
            Optional<CreatureSpeciesEntity> starterCreatureTemplateOptional = creatureSpeciesRepository.findById(i);
            if (starterCreatureTemplateOptional.isEmpty()) {
                continue;
            }
            CreatureSpeciesEntity creatureTemplateEntity = starterCreatureTemplateOptional.get();
            CreatureDto starterCreatureDto = createCreatureDtoFromTemplate(creatureTemplateEntity, trainerId, 1L);
            starterCreatures.add(starterCreatureDto);
        }

        return starterCreatures;
    }

    @Override
    public CreatureDto createCreature(CreatureDto creature) {
        CreatureSpeciesEntity creatureSpecies = getCreatureSpecies(creature.getSpecies());
        CreatureEntity creatureEntity = convertToCreatureEntity(creature, creatureSpecies);
        creatureEntity.setActive(true);
        Collection<CreatureEntity> activeTrainerCreatures = creatureEntityRepository.findByTraineridAndActive(creatureEntity.getTrainerid(), true);
        if (!activeTrainerCreatures.isEmpty()) {
            activeTrainerCreatures.forEach(existingCreature -> existingCreature.setActive(false));
            creatureEntityRepository.saveAll(activeTrainerCreatures);
        }
        CreatureEntity savedCreature = creatureEntityRepository.save(creatureEntity);

        return convertToCreatureDto(savedCreature);

    }

    @Override
    public List<CreatureDto> getCreatures(Long trainerId, Boolean active) {
        Collection<CreatureEntity> foundCreatures;

        if (active != null) {
            foundCreatures = creatureEntityRepository.findByTraineridAndActive(trainerId, active);
        } else {
            foundCreatures = creatureEntityRepository.findByTrainerid(trainerId);
        }
        return foundCreatures.stream().map(this::convertToCreatureDto).toList();
    }

    private CreatureDto convertToCreatureDto(CreatureEntity creatureEntity) {
        CreatureDto creatureDto = modelMapper.map(creatureEntity, CreatureDto.class);
        CreatureSpeciesEntity creatureSpeciesEntity = creatureEntity.getCreatureSpeciesEntity();
        creatureDto.setSpecies(creatureSpeciesEntity.getName());
        List<String> elements = creatureSpeciesEntity.getCreatureElements().stream().map(CreatureElement::getId).toList();
        creatureDto.setElements(elements);
        return creatureDto;
    }

    private CreatureEntity convertToCreatureEntity(CreatureDto creature, CreatureSpeciesEntity creatureSpecies) {
        CreatureEntity creatureEntity = modelMapper.map(creature, CreatureEntity.class);
        creatureEntity.setCreatureSpeciesEntity(creatureSpecies);
        creatureEntity.setId(null);
        return creatureEntity;
    }

    private CreatureSpeciesEntity getCreatureSpecies(String species) {
        Optional<CreatureSpeciesEntity> creatureSpeciesEntityOptional = creatureSpeciesRepository.findByName(species);
        if (creatureSpeciesEntityOptional.isEmpty()) {
            throw new CreatureSpeciesNotFoundException("The Species " + species + " was not found");
        }
        return creatureSpeciesEntityOptional.get();
    }

    private boolean isLiquorNumber(Long trainerId) {
        if (trainerId < 11) {
            return false;
        }
        String stringNumber = Long.toString(trainerId);
        char firstChar = stringNumber.charAt(0);
        for (int i = 0; i < stringNumber.length(); i++) {
            if (firstChar != stringNumber.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private CreatureDto createCreatureDtoFromTemplate(CreatureSpeciesEntity creatureTemplateEntity, Long trainerid, Long level) {
        CreatureDto creatureDto = new CreatureDto();
        creatureDto.setName(creatureTemplateEntity.getName());
        creatureDto.setSpecies(creatureTemplateEntity.getName());
        List<String> creatureTypes = creatureTemplateEntity.getCreatureElements().stream().map(CreatureElement::getId).toList();
        creatureDto.setElements(creatureTypes);
        creatureDto.setLevel(level);
        creatureDto.setTrainerId(trainerid);
        return creatureDto;
    }
}
