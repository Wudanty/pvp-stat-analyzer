package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.repositories.ScoreRepository;
import com.td.honse.repositories.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {


    TraineeRepository traineeRepository;
    ScoreRepository scoreRepository;

    public TraineeServiceImpl(TraineeRepository traineeRepository, ScoreRepository scoreRepository) {
        this.traineeRepository = traineeRepository;
        this.scoreRepository = scoreRepository;
    }


    @Override
    public Trainee findTraineeByNameAndCareerScore(String name, int careerScore) {
        return traineeRepository.findByNameAndCareerScore(name,careerScore).orElse(null);
    }

    @Override
    public List<Match> findTraineeMatches(Trainee trainee) {
        return null;
    }

    public Optional<Trainee> getTraineeByNameAndScore(String name, Integer careerScore) {
        return traineeRepository.findByNameAndCareerScore(name,careerScore);
    }


    @Override
    public void updateStatistics() {

    }

    @Override
    public Trainee saveTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }

    @Override
    public Integer findTraineeMatchCountById(Integer traineeId) {
        return traineeRepository.matchCountForTrainee(traineeId);
    }

    @Override
    public Float findTraineeScoreCountById(Integer traineeId) {
        return traineeRepository.averageScoreForTrainee(traineeId);
    }
}
