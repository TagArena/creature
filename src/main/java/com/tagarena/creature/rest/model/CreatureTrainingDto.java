package com.tagarena.creature.rest.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CreatureTrainingDto {

    @NotNull
    private Long id;

    @NotNull
    private String type;

    @NotNull
    @FutureOrPresent
    private OffsetDateTime nextTrainingFrom;
}
