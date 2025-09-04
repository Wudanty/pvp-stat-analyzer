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
    private Integer value;
    private String additionDate;
    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

}


