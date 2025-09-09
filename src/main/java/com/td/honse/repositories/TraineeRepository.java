package com.td.honse.repositories;

import com.td.honse.models.Match;
import com.td.honse.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Integer> {
        Optional<Trainee> findByNameAndCareerScore(String name, Integer Score);

        //@Query("SELECT m FROM matches WHERE m.trainee_id = :traineeId ORDER BY trainee_id DESC LIMIT :amount")
        //List<Match> findLastFew(@Param("traineeId")Integer traineeId, @Param("amount") Integer amount);
}
