package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface MatchService {
    public Match saveMatch(Match match);
    public Optional<Match> findMatchById(Integer id);
    public List<Match> getAllMatches();
    Optional<List<Score>> getMatchScores(Match match);
    List<Match> findMatchesByParticipant(Trainee trainee);
}
