package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.repositories.TraineeRepository;
import com.td.honse.repositories.MatchRepository;
import com.td.honse.repositories.ScoreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Log4j2
public class MatchServiceImpl implements MatchService{

    MatchRepository matchRepository;
    TraineeRepository traineeRepository;
    ScoreRepository scoreRepository;

    public MatchServiceImpl(MatchRepository matchRepository, TraineeRepository traineeRepository, ScoreRepository scoreRepository) {
        this.matchRepository = matchRepository;
        this.traineeRepository = traineeRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void registerNewMatch(NewMatchRequest matchDetails) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");
        StringBuilder stringBuilder = new StringBuilder();
        Map<Trainee,Integer> traineesScoresMap = matchDetails.getMatchTraineesWithScores();

        Match newMatch = new Match();
        newMatch.setMatchScore(matchDetails.getMatchScore());

        for(Trainee trainee:traineesScoresMap.keySet()){

            Optional<Trainee> existing = traineeRepository.findByNameAndCareerScore(trainee.getName(), trainee.getCareerScore());

            if(existing.isPresent()){
                log.debug(stringBuilder.append(String.format(
                        "Trainee exists, Name: %s CareerScore: %d Id: %d",
                        trainee.getName(),
                        trainee.getCareerScore(),
                        trainee.getTraineeId()
                )).toString());
            }
            else{
                stringBuilder = new StringBuilder();

                Score score = new Score();;
                score.setValue(traineesScoresMap.get(trainee));
                score.setAdditionDate(formatter.format(LocalDateTime.now()));
                score.setTrainee(trainee);
                Integer savedScoreId = scoreRepository.save(score).getScoreId();

                log.debug(stringBuilder.append(String.format(
                        "Score of value %d added to trainee %s with id %d",
                        score.getValue(),
                        score.getTrainee().getName(),
                        savedScoreId)));
                newMatch.addTrainees(trainee);
                trainee.getTraineeMatchScores().add(score);
                traineeRepository.save(trainee);

                log.debug(stringBuilder.append(String.format(
                        "New trainee added, Name: %s CareerScore: %d",
                        trainee.getName(),
                        trainee.getCareerScore()
                )).toString());
            }


        }

        newMatch.setMatchScore(matchDetails.getMatchScore());
        matchRepository.saveAndFlush(newMatch);
        log.debug("New match has been saved");


    }

    @Override
    public Optional<Match> findMatchById(Integer id) {
        return matchRepository.findById(id);
    }

    public List<Match> getAllMatches(){
        return matchRepository.findAll();
    }


}
