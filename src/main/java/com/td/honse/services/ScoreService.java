package com.td.honse.services;


import com.td.honse.models.Score;

import java.util.List;

public interface ScoreService {
    public List<Score> findAllScoresOfTraineeById(Integer traineeId);
    public Double findAverageScoreOfTraineeById(Integer traineeId);
    public Score saveScore(Score score);
}
