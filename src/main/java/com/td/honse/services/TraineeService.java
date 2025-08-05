package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.models.dtos.TraineeDTO;

import java.util.List;

public interface TraineeService {
    public void addTrainees(NewMatchRequest match);
    public Trainee getTrainee(String Name, String careerScore);
    public List<Trainee> getTraineesFromMatch();
    public void updateStatistics();
}
