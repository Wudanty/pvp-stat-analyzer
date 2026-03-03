package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface MatchService {
    Match saveMatch(Match match);
    Match findMatchById(Integer id);
    List<Match> getAllMatches();
    List<Score> getMatchScores(Integer matchId);
    List<Match> findMatchesByParticipant(Trainee trainee);
    void deleteAllMatches();
    Match getMatchById(Integer id);
}
