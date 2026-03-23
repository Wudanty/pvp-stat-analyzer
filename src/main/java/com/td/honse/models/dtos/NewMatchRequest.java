package com.td.honse.models.dtos;

import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMatchRequest {
    private List<TraineeScoreBundleDTO> matchTraineesWithScores;
    private Integer matchScore;
}
