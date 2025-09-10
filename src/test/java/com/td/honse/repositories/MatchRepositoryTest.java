package com.td.honse;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.repositories.MatchRepository;
import com.td.honse.services.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
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
@Slf4j
@DirtiesContext
@Sql(scripts = {"/match_init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class MatchRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    MatchRepository matchRepository;

    @Test
    public void connectionEstablished(){
        postgres.start();
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void sanityCheck() {
        System.out.println(">>> MatchRepository bean = " + matchRepository);
    }


    @Test
    public void shouldReturnMatchById(){

        List<Match> matches = matchRepository.findAll();
        assertThat(matches).isNotNull();
        System.out.println(matches.getFirst().getId() + " " + matches.getFirst().getId());
    }

    @Test
    public void shouldReturnMatchTrainees(){
        List<Trainee> trainees = matchRepository.findAll().getFirst().getTrainees();
        assertThat(trainees.size()).isEqualTo(15);
        assertThat(trainees.getLast().getId()).isEqualTo(15);
        trainees.forEach(t -> log.debug("Trainee Id: {}, Trainee Name: {}, Trainee Career Score: {}", t.getId(), t.getName(), t.getCareerScore()));
    }

    @Test
    public void shouldReturnMatchScores() {
        List<Score> matchScores = matchRepository.findAll().getLast().getScores();
        assertThat(matchScores.size()).isEqualTo(15);
        assertThat(matchScores.getLast().getId()).isEqualTo(30);

    }
}
