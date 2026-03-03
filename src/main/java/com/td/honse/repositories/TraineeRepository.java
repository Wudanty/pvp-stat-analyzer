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

        @Query(value = """
                SELECT count(m.id)
                FROM trainees t
                INNER JOIN trainee_matches tm ON t.id = tm.trainee_id
                INNER JOIN matches m ON m.id = tm.match_id
                where t.id = :traineeId
                """, nativeQuery = true)
        Integer matchCountForTrainee(@Param("traineeId") Integer traineeId);

        @Query(value = """
                SELECT round(avg(s.value),2)
                FROM trainees t
                LEFT JOIN scores s ON s.trainee_id = t.id
                where t.id = :traineeId
                """, nativeQuery = true)
        Float averageScoreForTrainee(@Param("traineeId") Integer traineeId);

        //@Query("SELECT m FROM matches WHERE m.trainee_id = :traineeId ORDER BY trainee_id DESC LIMIT :amount")
        //List<Match> findLastFew(@Param("traineeId")Integer traineeId, @Param("amount") Integer amount);
}
