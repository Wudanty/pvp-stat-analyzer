package com.td.honse.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "scores")
@Data
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer scoreId;
    @Column(nullable = false)
    private Integer value;
    @Column(nullable = false)
    private String additionDate;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Trainee character;


    public Score(Integer scoreId, Integer value) {
        this.scoreId = scoreId;
        this.value = value;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.additionDate = today.format(formatter);
    }
}


