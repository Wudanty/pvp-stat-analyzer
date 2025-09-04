package com.td.honse.models.dtos;

import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class NewMatchRequest {
    Map<Trainee, Integer> matchTraineesWithScores;
    private Integer matchScore;
}
