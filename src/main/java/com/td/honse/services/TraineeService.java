package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;

import java.util.List;
import java.util.Optional;

public interface TraineeService {
    public Optional<Trainee> getTrainee(String Name, int careerScore);
    public List<Match> getTraineeMatches(Trainee trainee);
    public void updateStatistics();
    public List<Score> getTraineeScores(Trainee trainee);
}
