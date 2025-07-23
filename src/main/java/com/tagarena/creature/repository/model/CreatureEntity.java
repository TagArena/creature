package com.tagarena.creature.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "creature")
public class CreatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creature_species_entity_name", nullable = false)
    private CreatureSpeciesEntity creatureSpeciesEntity;

    @Column(name = "level", nullable = false)
    private Long level;

    @Column(name = "trainerid", nullable = false)
    private Long trainerid;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "xp", nullable = false)
    private Long xp;

}
