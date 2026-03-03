package com.td.honse.repositories;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    Optional<List<Score>> findByMatchId(Integer matchId);
    Optional<List<Score>> findByTraineeId(Integer traineeId);
}
