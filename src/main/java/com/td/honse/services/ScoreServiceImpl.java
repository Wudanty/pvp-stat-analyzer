package com.td.honse.services;

import com.td.honse.models.Score;
import com.td.honse.models.dtos.ScoreDTO;
import com.td.honse.repositories.TraineeRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScoreServiceImpl implements ScoreService{

    TraineeRepository traineeRepository;

    public ScoreServiceImpl(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public void addScore(ScoreDTO score) {

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Score scoreToSave = new Score();
        scoreToSave.setValue(score.getValue());
        scoreToSave.setAdditionDate(today.format(formatter));
        scoreToSave.setTrainee(traineeRepository.findByNameAndScore());

    }
}
