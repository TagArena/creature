package com.tagarena.creature.service;

import com.tagarena.creature.repository.CreatureEntityRepository;
import com.tagarena.creature.repository.model.CreatureElement;
import com.tagarena.creature.repository.model.CreatureSpeciesEntity;
import com.tagarena.creature.rest.model.CreatureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreatureServiceImpl implements CreatureService {

    private final CreatureEntityRepository creatureEntityRepository;

    @Override
    public List<CreatureDto> getStarterCreatures(Long trainerId) {
        List<CreatureDto> starterCreatures = new ArrayList<>();

        long lastStarterCreatureId = isLiquorNumber(trainerId) ? 4 : 3;
        for (long i = 1; i <= lastStarterCreatureId; i++) {
            Optional<CreatureSpeciesEntity> starterCreatureTemplateOptional = creatureEntityRepository.findById(i);
            if (starterCreatureTemplateOptional.isEmpty()) {
                continue;
            }
            CreatureSpeciesEntity creatureTemplateEntity = starterCreatureTemplateOptional.get();
            CreatureDto starterCreatureDto = createCreatureDtoFromTemplate(creatureTemplateEntity, 1L);
            starterCreatures.add(starterCreatureDto);
        }

        return starterCreatures;
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

    private CreatureDto createCreatureDtoFromTemplate(CreatureSpeciesEntity creatureTemplateEntity, Long level) {
        CreatureDto creatureDto = new CreatureDto();
        creatureDto.setName(creatureTemplateEntity.getName());
        creatureDto.setSpecies(creatureTemplateEntity.getName());
        List<String> creatureTypes = creatureTemplateEntity.getCreatureElements().stream().map(CreatureElement::getId).toList();
        creatureDto.setElements(creatureTypes);
        creatureDto.setLevel(level);
        return creatureDto;
    }
}
