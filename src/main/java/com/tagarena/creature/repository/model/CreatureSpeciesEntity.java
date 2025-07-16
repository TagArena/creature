package com.tagarena.creature.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "creature_species")
public class CreatureSpeciesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "Creature_species_creaturetypes",
            joinColumns = @JoinColumn(name = "creature_species_id"),
            inverseJoinColumns = @JoinColumn(name = "creaturetypes_id"))
    private Set<CreatureElement> creatureElements = new LinkedHashSet<>();

}
