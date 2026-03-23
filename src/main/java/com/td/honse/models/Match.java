package com.td.honse.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matches")
@ToString(exclude = {"trainees"})
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer matchScore;
    @OneToMany(mappedBy = "matches", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Trainee> trainees = new ArrayList<>();
}
