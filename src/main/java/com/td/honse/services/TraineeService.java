package com.td.honse.services;

import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.TraineeDTO;

import java.util.List;

public interface TraineeService {
    public Trainee addTrainee(TraineeDTO requestTrainee);
    public Trainee getTrainee(String Name, String careerScore);
    public List<Trainee> getTraineesFromMatch();
    public void updateStatistics();
}
