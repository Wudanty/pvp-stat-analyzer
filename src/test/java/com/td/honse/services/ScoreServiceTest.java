package com.td.honse.services;

import lombok.extern.log4j.Log4j2;
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
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({MatchServiceImpl.class, TraineeServiceImpl.class, ScoreServiceImpl.class, RegisterServiceImpl.class})
@Log4j2
@DirtiesContext
@Sql(scripts = {"/match_init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class ScoreServiceTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    ScoreService scoreService;

    @Test
    public void connectionEstablished() {
        postgres.start();
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void returnsExpectedAverage(){
        Double avgScore = scoreService.getAverageScoreOfTraineeById(1);
        assertThat(avgScore).isEqualTo(30984);
    }

}
