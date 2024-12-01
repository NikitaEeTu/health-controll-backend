package com.workwave.backend.services;

import com.workwave.backend.dtos.ExerciseDto;

import java.util.List;

public interface ExerciseService {
    List<ExerciseDto> findRandomExercises();
}
