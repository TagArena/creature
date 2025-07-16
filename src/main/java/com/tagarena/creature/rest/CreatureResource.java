package com.tagarena.creature.rest;

import com.tagarena.creature.rest.model.CreatureDto;
import com.tagarena.creature.service.CreatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CreatureResource {

    private final CreatureService creatureService;

    // @formatter:off
	@Operation(summary = "Retrieves the starter creatures for the given trainer", description = "Retrieves a trainer by their trainerId")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Starter creatures retrieved successfully")
	})
	@GetMapping("/starterCreatures")
	// @formatter:on
    List<CreatureDto> getStarterCreatures(@NotNull @RequestParam Long trainerId) {

        log.info("getStarterCreatures called with trainerId={}", trainerId);
        List<CreatureDto> starterCreatures = creatureService.getStarterCreatures(trainerId);
        log.info("getStarterCreatures finished with starterCreatures={}", starterCreatures);

        return starterCreatures;
    }

    // @formatter:off
    @Operation(summary = "Retrieves the creatures for the given parameters", description = "Retrieves the creatures for the given parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creatures retrieved successfully")
    })
    @GetMapping("/creatures")
    // @formatter:on
    List<CreatureDto> getCreatures(@RequestParam Long trainerId, @RequestParam(required = false) Boolean active) {

        log.info("getCreatures called with trainerId={}, active={}", trainerId, active);
        List<CreatureDto> creatures = creatureService.getCreatures(trainerId, active);
        log.info("getCreatures finished with creatures={}", creatures);

        return creatures;
    }

    // @formatter:off
    @Operation(summary = "Creates a creature with the given parameters", description = "Creates a creature with the given parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creature created successfully")
    })
    @PostMapping("/creatures")
    // @formatter:on
    CreatureDto createCreature(@NotNull @RequestBody CreatureDto creature) {

        log.info("createCreature called with creature={}", creature);
        CreatureDto createdCreature = creatureService.createCreature(creature);
        log.info("getStarterCreatures finished with createdCreature={}", createdCreature);

        return createdCreature;
    }
}
