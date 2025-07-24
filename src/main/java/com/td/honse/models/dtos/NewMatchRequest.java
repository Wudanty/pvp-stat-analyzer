package com.td.honse.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class NewMatchRequest {
    List<TraineeDTO> matchCharacters;
    private Integer matchScore;
}
