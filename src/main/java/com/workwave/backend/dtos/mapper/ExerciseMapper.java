package com.workwave.backend.dtos.mapper;

import com.workwave.backend.dtos.ExerciseDto;
import com.workwave.backend.persistence.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    ExerciseDto exerciseToExerciseDTO(Exercise exercise);
}
