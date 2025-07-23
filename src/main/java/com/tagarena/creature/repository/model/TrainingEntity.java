package com.tagarena.creature.repository.model;

import java.time.OffsetDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "training")
public class TrainingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creature_id", nullable = false)
    private CreatureEntity creature;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "next_training_from", nullable = false)
    private OffsetDateTime nextTrainingFrom;

}
