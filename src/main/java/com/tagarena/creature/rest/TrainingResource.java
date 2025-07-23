package com.tagarena.creature.rest;

import com.tagarena.creature.rest.model.CreatureTrainingCreationDto;
import com.tagarena.creature.rest.model.CreatureTrainingDto;
import com.tagarena.creature.service.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TrainingResource {

    private final TrainingService trainingService;

    // @formatter:off
    @Operation(summary = "Creates the given training for the given creature", description = "Creates the given training for the given creature")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training created successfully")
    })
    @PostMapping("/creatures/{creatureId}/trainings")
    // @formatter:on
    CreatureTrainingDto createTraining(@PathVariable Long creatureId, @NotNull @RequestBody CreatureTrainingCreationDto training) {

        log.info("createTraining called with training={}", training);
        CreatureTrainingDto createdTraining = trainingService.createCreatureTraining(creatureId, training);
        log.info("createTraining finished with createdCreature={}", createdTraining);

        return createdTraining;
    }
}
