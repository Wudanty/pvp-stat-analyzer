package com.td.honse.services;

import com.td.honse.models.dtos.NewMatchRequest;
import org.springframework.stereotype.Service;


public interface RegisterService {
    public void registerNewMatch(NewMatchRequest matchRequest);
}
