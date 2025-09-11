package com.td.honse.repositories;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Log4j2
@DirtiesContext
@Sql(scripts = {"/match_init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class TraineeRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    TraineeRepository traineeRepository;

    @Test
    public void connectionEstablished(){
        postgres.start();
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void sanityCheck() {
        System.out.println(">>> TraineeRepository bean = " + traineeRepository);
    }

    @Test
    public void shouldReturnTrainee(){
        Trainee trainee = traineeRepository.findByNameAndCareerScore("Nice Nature",11272).orElseThrow();
        assertThat(trainee).isNotNull();
        assertThat(trainee.getName()).isEqualTo("Nice Nature");
        assertThat(trainee.getCareerScore()).isEqualTo(11272);
        log.debug("Trainee Id: {}, Name: {}, Career score: {}, Match Count: {}, averageScore: {}",
                trainee.getId(),
                trainee.getName(),
                trainee.getCareerScore(),
                trainee.getMatchCount(),
                trainee.getAverageScore()
        );
    }

    @Test
    public void shouldReturnTraineeMatches(){
        List<Match> matches = traineeRepository.findByNameAndCareerScore("TM Opera O",11119).orElseThrow().getMatches();
        assertThat(matches).isNotNull();
        matches.forEach(m -> log.debug("Match Id: {}, Match Score: {}", m.getId(), m.getMatchScore()));
    }



    @Test
    public void shouldReturnTraineeScores(){
        List<Score> traineeScores = traineeRepository.findByNameAndCareerScore("TM Opera O",11119).orElseThrow().getScores();
        assertThat(traineeScores.size()).isEqualTo(2);
        assertThat(traineeScores.getFirst().getValue()).isEqualTo(21904);
    }

}
