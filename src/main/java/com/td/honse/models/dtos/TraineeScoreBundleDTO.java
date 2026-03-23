package com.td.honse.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeScoreBundleDTO {
    private TraineeDTO trainee;
    private Integer score;
}
