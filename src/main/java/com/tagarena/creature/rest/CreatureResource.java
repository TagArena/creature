package com.tagarena.creature.rest;

import com.tagarena.creature.rest.model.CreatureDto;
import com.tagarena.creature.service.CreatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
