package com.td.honse;

import com.td.honse.models.Match;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.services.MatchService;
import com.td.honse.services.MatchServiceImpl;
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
@Import(MatchServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MatchRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    MatchService matchService;

    @Test
    public void connectionEstablished(){
        postgres.start();
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void sanityCheck() {
        System.out.println(">>> MatchService bean = " + matchService);
    }

    @BeforeEach
    void setup(){
        Map<Trainee, Integer> matchParticipants = Map.ofEntries(
                //sprint
                Map.entry(new Trainee("King Halo", 10311, "Sprint", 0),27504),
                Map.entry(new Trainee("Sakura Bakushin O", 10455, "Sprint", 0),9687),
                Map.entry(new Trainee("Maruzensky", 11271, "Sprint", 0),23829),

                //mile
                Map.entry(new Trainee("Vodka", 10311, "Mile", 0),34838),
                Map.entry(new Trainee("Silence Suzuka", 10455, "Mile", 0),16661),
                Map.entry(new Trainee("Grass Wonder", 11271, "Mile", 0),33129),

                //medium
                Map.entry(new Trainee("Winning Ticket", 10311, "Medium", 0),25263),
                Map.entry(new Trainee("Tokai Teio", 10455, "Medium", 0),19954),
                Map.entry(new Trainee("Nice Nature", 11271, "Medium", 0),29254),

                //long
                Map.entry(new Trainee("Rice Shower", 10311, "Long", 0),22614),
                Map.entry(new Trainee("Mejiro McQueen", 10455, "Long", 0),24798),
                Map.entry(new Trainee("Opera O", 11271, "Long", 0),29254),

                //dirt
                Map.entry(new Trainee("Haru Urara", 10311, "Dirt", 0),25581),
                Map.entry(new Trainee("El Condor Pasa", 10455, "Dirt", 0),25379),
                Map.entry(new Trainee("Oguri Cap", 11271, "Dirt", 0),17824)
        );
        matchService.registerNewMatch(new NewMatchRequest(matchParticipants, 38816));
    }

    @Test
    public void shouldReturnMatchById(){

        List<Match> match = matchService.getAllMatches();
        assertThat(match).isNotNull();
        System.out.println(match.getFirst().getMatchId() + " " + match.getFirst().getMatchScore());
    }
}
