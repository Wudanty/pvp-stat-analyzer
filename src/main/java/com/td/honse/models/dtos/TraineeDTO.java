package com.td.honse.models.dtos;

import com.td.honse.models.Score;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDTO {
    private String name;
    private Integer careerScore;


}
