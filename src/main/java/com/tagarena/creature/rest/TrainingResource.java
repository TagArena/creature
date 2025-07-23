package com.tagarena.creature.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.tagarena.creature.rest.model.CreatureDto;
import com.tagarena.creature.service.TrainingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TrainingResource {

    private final TrainingService trainingService;

    // @formatter:off
    @Operation(summary = "Retrieves the next Training for the given creature", description = "Retrieves the next Training for the given creature")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training retrieved successfully")
    })
    @GetMapping("/creatures/{creatureId}/nextTrainings")
    // @formatter:on
    List<CreatureTraining> getNextTraining(@RequestParam Long creatureId) {

        log.info("getNextTraining called with creatureId={}", creatureId);
        List<CreatureTraining> trainings = trainingService.getNextTrainings(creatureId);
        log.info("getNextTraining finished with creatures={}", creatures);

        return trainings;
    }

    // @formatter:off
    @Operation(summary = "Creates the given training for the given creature", description = "Creates the given training for the given creature")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training created successfully")
    })
    @PostMapping("/creatures/{creatureId}/trainings")
    // @formatter:on
    CreatureDto createTraining(@RequestParam Long creatureId, @NotNull @RequestBody CreatureTrainingCreation training) {

        log.info("createTraining called with training={}", training);
        CreatureTraining createdTraining = trainingService.createCreatureTraining(creatureId, training);
        log.info("createTraining finished with createdCreature={}", createdTraining);

        return createdCreature;
    }
}
