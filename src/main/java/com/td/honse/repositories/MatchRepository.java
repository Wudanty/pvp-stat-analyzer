package com.td.honse.repositories;

import com.td.honse.models.Match;
import com.td.honse.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

}
