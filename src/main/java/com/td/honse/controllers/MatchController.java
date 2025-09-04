package com.td.honse.controllers;


import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.services.MatchService;
import com.td.honse.services.ScoreService;
import com.td.honse.services.TraineeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
public class MatchController {
    private final MatchService matchService;


    @PostMapping("/register")
    void createMatchRecord(@RequestBody NewMatchRequest newMatch){
        matchService.registerNewMatch(newMatch);
    }
}
