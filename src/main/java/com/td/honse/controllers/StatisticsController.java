package com.td.honse.controllers;

import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.services.ScoreService;
import com.td.honse.services.TraineeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
public class StatisticsController {
    private final ScoreService scoreService;
    private final TraineeService traineeService;


    @GetMapping("/byTrainee/{id}/avg" )
    public Double getAverageScoreById(@PathVariable Integer id){
        return scoreService.getAverageScoreOfTraineeById(id);
    }

    @GetMapping("/{id}/matchCount")
    public Integer getTraineeMatchCount(@PathVariable Integer id){
        return traineeService.getTraineeMatchCountById(id);
    }

    @GetMapping("/{id}/avgScore")
    public Float getTraineeScoreAvg(@PathVariable Integer id){
        return traineeService.getTraineeScoreCountById(id);
    }
}
