package com.td.honse.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matches")
@ToString(exclude = {"scores", "trainees"})
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer matchScore;
    @OneToMany(mappedBy = "match")
    private List<Score> scores = new ArrayList<>();
    @ManyToMany(mappedBy = "matches")
    private List<Trainee> trainees = new ArrayList<>();
}
