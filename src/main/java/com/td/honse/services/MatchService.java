package com.td.honse.services;

import com.td.honse.models.dtos.NewMatchRequest;

public interface MatchService {
    public void registerNewMatch(NewMatchRequest matchDetails);
}
