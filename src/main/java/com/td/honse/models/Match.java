package com.td.honse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer matchId;
    private Integer matchScore;
    @ManyToMany
    @JsonIgnore
    private Set<Trainee> characters = new HashSet<>();
}
