package com.td.honse.services;

import com.td.honse.models.Match;
import com.td.honse.models.dtos.NewMatchRequest;

import java.util.List;
import java.util.Optional;


public interface MatchService {
    public void registerNewMatch(NewMatchRequest matchDetails);
    public Optional<Match> findMatchById(Integer id);
    public List<Match> getAllMatches();
}
