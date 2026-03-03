package com.td.honse.services;


import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;

import java.util.List;

public interface ScoreService {
    public List<Score> findAllScoresOfTraineeById(Integer traineeId);
    public Double getAverageScoreOfTraineeById(Integer traineeId);
    public Score saveScore(Score score);
}
