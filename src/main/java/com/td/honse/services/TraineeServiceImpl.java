package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.repositories.ScoreRepository;
import com.td.honse.repositories.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<Trainee> getTrainee(String name, int careerScore) {
        return traineeRepository.findByNameAndCareerScore(name,careerScore);
    }

    @Override
    public List<Match> getTraineeMatches(Trainee trainee) {
        return trainee.getMatches();
    }

    public Optional<Trainee> getTraineeByNameAndScore(String name, Integer careerScore) {
        return traineeRepository.findByNameAndCareerScore(name,careerScore);
    }


    @Override
    public void updateStatistics() {

    }

    @Override
    public List<Score> getTraineeScores(Trainee trainee) {
        return trainee.getTraineeMatchScores();
    }
}
