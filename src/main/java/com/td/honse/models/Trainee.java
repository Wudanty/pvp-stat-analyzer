package com.td.honse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainees")
@Data
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer traineeId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer careerScore;
    @Column(nullable = false)
    private String matchType;
    private Integer matchCount = 0;
    private Float averageScore;
    @OneToMany(mappedBy = "scoreId")
    @JsonManagedReference
    private List<Score> traineeMatchScores = new ArrayList<>();
    @ManyToMany(mappedBy = "trainees")
    @JsonIgnore
    private List<Match> matches = new ArrayList<>();

    public Trainee(String traineeName, Integer careerScore, String matchType, Integer matchCount) {
        this.name = traineeName;
        this.careerScore = careerScore;
        this.matchType = matchType;
        this.matchCount = matchCount;
    }
}


