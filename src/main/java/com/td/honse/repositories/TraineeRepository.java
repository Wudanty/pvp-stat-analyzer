package com.td.honse.repositories;

import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Integer> {
        Optional<Trainee> findByNameAndScore(String name, Integer Score);
}
