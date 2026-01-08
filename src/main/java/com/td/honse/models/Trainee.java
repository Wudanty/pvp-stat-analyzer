package com.td.honse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "trainees")
@Data
@NoArgsConstructor
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer careerScore;
    private Float averageScore = 0f;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "trainee_matches",
            joinColumns = {@JoinColumn(name = "trainee_id" )},
            inverseJoinColumns = {@JoinColumn(name = "match_id")}
    )
    @JsonBackReference
    List<Match> matches = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "traineeId")
    List<Score> scores = new ArrayList<>();
    public Trainee(String traineeName, Integer careerScore) {
        this.name = traineeName;
        this.careerScore = careerScore;

    }
}


