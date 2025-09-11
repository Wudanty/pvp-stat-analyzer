package com.td.honse.models.dtos;

import com.td.honse.models.Match;
import com.td.honse.models.Trainee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreDTO {
    private Integer value;
    private String additionDate;
}
