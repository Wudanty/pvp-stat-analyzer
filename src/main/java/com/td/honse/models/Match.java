package com.td.honse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matches")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
