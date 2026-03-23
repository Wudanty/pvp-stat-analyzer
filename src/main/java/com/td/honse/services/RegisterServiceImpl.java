package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.models.dtos.TraineeDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final MatchService matchService;
    private final ScoreService scoreService;
    private final TraineeService traineeService;

    @Override
    public void registerNewMatch(NewMatchRequest matchRequest) {
        Match match = new Match();
        match.setMatchScore(matchRequest.getMatchScore());
        matchService.saveMatch(match);

        matchRequest.getMatchTraineesWithScores().forEach((bundle) -> {
            registryLogic(bundle.getTrainee(), bundle.getScore(), match);
        });

    }

    private void registryLogic(TraineeDTO dtoTrainee, Integer scoreValue, Match match) {
        Score score = new Score();
        score.setValue(scoreValue);
        scoreService.saveScore(score);

        Trainee targetTrainee = new Trainee();
        Trainee existingTrainee = traineeService.findTraineeByNameAndCareerScore(dtoTrainee.getName(), dtoTrainee.getCareerScore());
        if (existingTrainee == null) {

            targetTrainee.setName(dtoTrainee.getName());
            targetTrainee.setCareerScore(dtoTrainee.getCareerScore());
        } else {
            targetTrainee = existingTrainee;
        }

        targetTrainee.getMatches().add(match);
        targetTrainee.getScores().add(score);
        traineeService.saveTrainee(targetTrainee);

        match.getTrainees().add(targetTrainee);
        score.setMatch(match);
        traineeService.saveTrainee(targetTrainee);
        scoreService.saveScore(score);

    }
}
