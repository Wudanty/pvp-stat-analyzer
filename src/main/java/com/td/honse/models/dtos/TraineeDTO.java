package com.td.honse.models.dtos;

import com.td.honse.models.Score;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TraineeDTO {
    private String name;
    private Integer careerScore;


}
