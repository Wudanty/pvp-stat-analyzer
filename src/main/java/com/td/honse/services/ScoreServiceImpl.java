package com.td.honse.services;

import com.td.honse.repositories.TraineeRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService{

    TraineeRepository traineeRepository;

    public ScoreServiceImpl(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }


}
