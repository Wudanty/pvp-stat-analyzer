package com.td.honse.services;


import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.models.dtos.TraineeDTO;
import com.td.honse.models.dtos.TraineeScoreBundleDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({MatchServiceImpl.class, TraineeServiceImpl.class, ScoreServiceImpl.class, RegisterServiceImpl.class})
@Log4j2
@DirtiesContext
//@Sql(scripts = {"/match_init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class MatchRegistryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    RegisterService registerService;
    @Autowired
    MatchService matchService;
    @Autowired
    TraineeService traineeService;
    @Autowired
    ScoreService scoreService;


    @Test
    public void connectionEstablished() {
        postgres.start();
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void shouldRegisterMatchAndRelatedEntities() {

        List<TraineeScoreBundleDTO> matchParticipants = List.of(
                //sprint
                new TraineeScoreBundleDTO(new TraineeDTO("Sprinter A", 10311), 27504),
                new TraineeScoreBundleDTO(new TraineeDTO("Sprinter B", 10455), 9687),
                new TraineeScoreBundleDTO(new TraineeDTO("Sprinter C", 11271), 23829),

                //mile
                new TraineeScoreBundleDTO(new TraineeDTO("Mile A", 10311), 34838),
                new TraineeScoreBundleDTO(new TraineeDTO("Mile B", 10455), 16661),
                new TraineeScoreBundleDTO(new TraineeDTO("Mile C", 11271), 3312),

                //medium
                new TraineeScoreBundleDTO(new TraineeDTO("Medium A", 10311), 25263),
                new TraineeScoreBundleDTO(new TraineeDTO("Medium B", 10455), 19954),
                new TraineeScoreBundleDTO(new TraineeDTO("Medium C", 11271), 29254),

                //long
                new TraineeScoreBundleDTO(new TraineeDTO("Long A", 10311), 22614),
                new TraineeScoreBundleDTO(new TraineeDTO("Long B", 10455), 24798),
                new TraineeScoreBundleDTO(new TraineeDTO("Long C", 11271), 29254),

                //dirt
                new TraineeScoreBundleDTO(new TraineeDTO("Dirt A", 10311), 25581),
                new TraineeScoreBundleDTO(new TraineeDTO("Dirt C", 11271), 17824),
                new TraineeScoreBundleDTO(new TraineeDTO("Dirt B", 10455), 25379)
        );
        registerService.registerNewMatch(new NewMatchRequest(matchParticipants, 400000));

        Trainee trainee = traineeService.findTraineeByNameAndCareerScore("Sprinter A", 10311);
        List<Match> traineeMatches = trainee.getMatches();
        Match match = traineeMatches.getFirst();
        Score traineeScore = trainee.getScores().getFirst();

        assertThat(trainee).isNotNull();
        assertThat(trainee.getName()).isEqualTo("Sprinter A");
        assertThat(trainee.getMatches().contains(match));

        assertThat(traineeMatches.size()).isEqualTo(1);

        assertThat(match.getTrainees()).contains(trainee);
        assertThat(match.getMatchScore()).isEqualTo(400000);
        assertThat(match.getTrainees().size()).isEqualTo(15);
        assertThat(matchService.getMatchScores(match.getId()).size()).isEqualTo(15);

        assertThat(traineeScore.getValue()).isEqualTo(27504);
    }

}
