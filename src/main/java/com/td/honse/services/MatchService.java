package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;

import java.util.List;


public interface MatchService {
    Match saveMatch(Match match);
    Match findMatchById(Integer id);
    List<Match> findAllMatches();
    List<Score> findMatchScores(Integer matchId);
    List<Match> findMatchesByParticipant(Trainee trainee);
    void deleteAllMatches();
}
