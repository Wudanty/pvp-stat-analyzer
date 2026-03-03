package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.models.dtos.TraineeDTO;
import com.td.honse.repositories.TraineeRepository;
import com.td.honse.repositories.MatchRepository;
import com.td.honse.repositories.ScoreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


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
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match findMatchById(Integer id) {
        return matchRepository.findById(id).orElseThrow();
    }

    public List<Match> getAllMatches(){
        return matchRepository.findAll();
    }

    @Override
    public List<Score> getMatchScores(Integer matchId) {
        return scoreRepository.findByMatchId(matchId).orElseThrow();
    }

    @Override
    public List<Match> findMatchesByParticipant(Trainee trainee) {
        return trainee.getMatches();

    }

    @Override
    public void deleteAllMatches() {
        matchRepository.deleteAll();
    }

    @Override
    public Match getMatchById(Integer id) {
        return matchRepository.findById(id).orElseThrow();
    }


}
