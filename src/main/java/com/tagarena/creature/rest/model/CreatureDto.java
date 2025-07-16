package com.tagarena.creature.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CreatureDto {

    private Long id;

    private String name;

    private String species;

    private List<String> elements;

    private Long level;

}
