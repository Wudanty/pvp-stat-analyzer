package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Trainee;

import java.util.List;

public interface TraineeService {
    public Trainee findTraineeByNameAndCareerScore(String Name, int careerScore);
    public List<Match> findTraineeMatches(Trainee trainee);
    public void updateStatistics();
    public Trainee saveTrainee(Trainee trainee);
    public Integer findTraineeMatchCountById(Integer traineeId);
    public Float findTraineeScoreCountById(Integer traineeId);
}
