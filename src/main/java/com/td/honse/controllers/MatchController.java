package com.td.honse.controllers;


import com.td.honse.models.Match;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.services.MatchService;
import com.td.honse.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
public class MatchController {
    private final RegisterService registerService;
    private final MatchService matchService;

    @PostMapping(value = "/register", consumes = {"application/json", "application/json;charset=UTF-8"})
    public void recordNewMatch(@RequestBody NewMatchRequest newMatch){
        registerService.registerNewMatch(newMatch);
    }

    @GetMapping("/get/{id}")
    public Match getMatchById(@PathVariable Integer id){
        return matchService.getMatchById(id);
    }

    @GetMapping("/get/all")
    public List<Match> getAllMatches(){
        return matchService.getAllMatches();
    }

    @DeleteMapping("/delete/all")
    public void deleteAllMatches(){
        matchService.deleteAllMatches();
    }
}
