package com.td.honse.services;

import com.td.honse.models.Score;
import com.td.honse.models.Trainee;
import com.td.honse.models.dtos.NewMatchRequest;
import com.td.honse.models.dtos.TraineeDTO;
import com.td.honse.repositories.ScoreRepository;
import com.td.honse.repositories.TraineeRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TraineeServiceImpl implements TraineeService {


    TraineeRepository traineeRepository;
    ScoreRepository scoreRepository;

    public TraineeServiceImpl(TraineeRepository traineeRepository, ScoreRepository scoreRepository) {
        this.traineeRepository = traineeRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void addTrainees(NewMatchRequest newMatchRequest) {

        //trainees that took part in the added match
        List<TraineeDTO> trainees = newMatchRequest.getMatchCharacters();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for(TraineeDTO trainee:trainees){
            Optional<Trainee> databaseRecord = traineeRepository.findByNameAndScore(trainee.getTraineeName(), trainee.getCareerScore());


            //checking if the trainee that took part in the match is already present in the database, if exists only add a new score record
            if(databaseRecord.isPresent()){


                scoreRepository.save(scoreMapper(
                        trainee.getMatchScore(),
                        databaseRecord.get(),
                        LocalDate.now().format(formatter)
                ));
                continue;
            }

                Trainee traineeToSave = new Trainee();
                traineeToSave.setTraineeName(trainee.getTraineeName());
                traineeToSave.setCareerScore(trainee.getCareerScore());

                traineeRepository.save(traineeToSave);

                traineeRepository.flush();

                if(databaseRecord.isPresent()) {

                    scoreRepository.save(scoreMapper(
                            trainee.getMatchScore(),
                            databaseRecord.get(),
                            LocalDate.now().format(formatter)
                    ));
                }

        }
        scoreRepository.flush();
    }

    @Override
    public Trainee getTrainee(String Name, String careerScore) {
        return null;
    }

    public Optional<Trainee> getTraineeByNameAndScore(String name, Integer careerScore) {
        return traineeRepository.findByNameAndScore(name,careerScore);
    }


    @Override
    public List<Trainee> getTraineesFromMatch() {
        return List.of();
    }

    @Override
    public void updateStatistics() {

    }

    private Score scoreMapper(Integer value, Trainee trainee, String additionDate){
        Score mappedScore = new Score();
        mappedScore.setValue(value);
        mappedScore.setTrainee(trainee);
        mappedScore.setAdditionDate(additionDate);
        return mappedScore;
    }
}
