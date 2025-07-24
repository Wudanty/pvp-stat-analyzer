package com.td.honse.services;

import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.models.dtos.TraineeDTO;
import com.td.honse.repositories.TraineeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TraineeServiceImpl implements TraineeService {


    TraineeRepository traineeRepository;

    public TraineeServiceImpl(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public Trainee addTrainees(NewMatchRequest newMatchRequest) {
        List<TraineeDTO> trainees = newMatchRequest.getMatchCharacters();
        for(TraineeDTO trainee:trainees){
            Optional<Trainee> currentTrainee = Optional.of(traineeRepository.findByNameAndScore(trainee.getTraineeName(), trainee.getCareerScore()).get());
            if(currentTrainee.isPresent()){
                //TODO implement this shit by updating an existing record or adding a new one if not existing
            }
        }
    }

    public Optional<Trainee> getTraineeByNameAndScore(String name, Integer careerScore) {
        return traineeRepository.findByNameAndScore(name,careerScore);
    }

    @Override
    public List<Trainee> getTraineesFromMatch() {
        return List.of();
    }

    @Override
    public void updateStatistics() {

    }
}
