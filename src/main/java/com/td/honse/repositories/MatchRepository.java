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
public interface MatchRepository extends JpaRepository<Match, Integer> {

    /*


     */
    @Query(value = """
                SELECT t.id, t.name, t.career_score FROM trainees t
                INNER JOIN trainee_matches tm on t.id = tm.trainee_id
                INNER JOIN matches m on m.id = tm.match_id
                WHERE m.id = :matchId
                """, nativeQuery = true)
    List<Trainee> getMatchParticipants(@Param("matchId") Integer matchId);
}
