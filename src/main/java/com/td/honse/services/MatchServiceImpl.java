package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.dtos.TraineeDTO;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.repositories.TraineeRepository;
import com.td.honse.repositories.MatchRepository;
import com.td.honse.repositories.ScoreRepository;

import java.util.HashSet;
import java.util.Set;

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
        Match newMatch = new Match();
        Set<TraineeDTO> Trainees= new HashSet();
        newMatch.setMatchScore(matchDetails.getMatchScore());


        newMatch.setCharacters();
    }
}
