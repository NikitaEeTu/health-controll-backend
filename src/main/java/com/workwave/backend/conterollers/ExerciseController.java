package com.workwave.backend.conterollers;

import com.workwave.backend.dtos.ExerciseDto;
import com.workwave.backend.services.ExerciseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/random")
    public List<ExerciseDto> getRandomExercises(){
       return exerciseService.findRandomExercises();
    }
}
