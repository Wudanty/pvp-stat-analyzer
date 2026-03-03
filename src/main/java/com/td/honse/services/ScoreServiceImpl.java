package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.repositories.ScoreRepository;
import com.td.honse.repositories.TraineeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService{


    ScoreRepository scoreRepository;

    @Override
    public List<Score> findAllScoresOfTraineeById(Integer traineeId) {
        return scoreRepository.findByTraineeId(traineeId).orElseThrow();
    }

    @Override
    public Double getAverageScoreOfTraineeById(Integer traineeId) {
        return findAllScoresOfTraineeById(traineeId).stream().mapToDouble(Score::getValue).average().orElseThrow();
    }

    @Override
    public Score saveScore(Score score) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");
        score.setAdditionDate(formatter.format(LocalDateTime.now()));
        return scoreRepository.save(score);
    }

}
