package com.td.honse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchId;
    private Integer matchScore;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "match_trainees",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id")
    )
    private List<Trainee> trainees = new ArrayList<>();
    @OneToMany
    private List<Score> matchScores;

    public void addTrainees(Trainee trainee) {
        trainee.getMatches().add(this);
        this.getTrainees().add(trainee);
    }
}
