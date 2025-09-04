package com.td.honse;

import com.td.honse.models.Match;
import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

;

@Testcontainers
@DataJpaTest
@Import({TraineeServiceImpl.class, MatchServiceImpl.class, ScoreServiceImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TraineeRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    TraineeService traineeService;
    @Autowired
    MatchService matchService;
    @Autowired
    ScoreService scoreService;

    @Test
    public void connectionEstablished(){
        postgres.start();
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void sanityCheck() {
        System.out.println(">>> MatchService bean = " + traineeService);
    }

    @BeforeEach
    void setup(){
        Map<Trainee, Integer> matchParticipants = Map.ofEntries(
                //sprint
                Map.entry(new Trainee("Sprinter A", 10311, "Sprint", 0),27504),
                Map.entry(new Trainee("Sprinter B", 10455, "Sprint", 0),9687),
                Map.entry(new Trainee("Sprinter C", 11271, "Sprint", 0),23829),

                //mile
                Map.entry(new Trainee("Mile A", 10311, "Mile", 0),34838),
                Map.entry(new Trainee("Mile B", 10455, "Mile", 0),16661),
                Map.entry(new Trainee("Mile C", 11271, "Mile", 0),33129),

                //medium
                Map.entry(new Trainee("Medium A", 10311, "Medium", 0),25263),
                Map.entry(new Trainee("Medium B", 10455, "Medium", 0),19954),
                Map.entry(new Trainee("Medium C", 11271, "Medium", 0),29254),

                //long
                Map.entry(new Trainee("Long A", 10311, "Long", 0),22614),
                Map.entry(new Trainee("Long B", 10455, "Long", 0),24798),
                Map.entry(new Trainee("Long C", 11271, "Long", 0),29254),

                //dirt
                Map.entry(new Trainee("Dirt A", 10311, "Dirt", 0),25581),
                Map.entry(new Trainee("Dirt B", 10455, "Dirt", 0),25379),
                Map.entry(new Trainee("Dirt C", 11271, "Dirt", 0),17824)
        );
        matchService.registerNewMatch(new NewMatchRequest(matchParticipants, 38816));
    }

    @Test
    public void shouldReturnTraineeMatches(){
        List<Match> match = traineeService.getTrainee("Medium A", 10311).orElseThrow().getMatches();
        assertThat(match).isNotNull();
        System.out.println(match.getFirst().getMatchId() + " " + match.getFirst().getMatchScore());
    }

    @Test
    public void shouldReturnTrainee(){
        Optional<Trainee> trainee = traineeService.getTrainee("Medium A", 10311);
        assertThat(trainee).isPresent();
        System.out.println(trainee.get().getName() + " " + trainee.get().getCareerScore());
    }

    @Test
    public void shouldReturnTraineeScores(){
        List<Score> traineeScores = traineeService.getTrainee("Medium A", 10311).orElseThrow().getTraineeMatchScores();
        assertThat(traineeScores).isNotNull();
        Integer traineeMatchScore = traineeScores.getFirst().getValue();
        System.out.println();
        assertThat(traineeScores.getFirst().getValue()).isEqualTo(25263);
    }
}
