package com.td.honse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

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
    private String traineeName;
    @Column(nullable = false)
    private Integer careerScore;
    private String surface;
    private String distance;
    private Integer matchCount;
    private Float averageScore;
    @OneToMany(mappedBy = "score")
    @JsonManagedReference
    private List<Score> traineeScores;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "trainee_match",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id")
    )
    private Set<Match> matches = new HashSet<>();

}


