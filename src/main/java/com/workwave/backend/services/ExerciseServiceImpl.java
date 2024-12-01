package com.workwave.backend.services;

import com.workwave.backend.dtos.ExerciseDto;
import com.workwave.backend.dtos.mapper.ExerciseMapper;
import com.workwave.backend.persistence.Exercise;
import com.workwave.backend.persistence.ExerciseRepository;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;
    private final ResourceLoader resourceLoader;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper, ResourceLoader resourceLoader) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
        this.resourceLoader = resourceLoader;
    }


    @Override
    public List<ExerciseDto> findRandomExercises() {
        final Iterable<Exercise> exercises = exerciseRepository.findAll();
        final List<Exercise> exerciseList = new ArrayList<>();
        exercises.forEach(exerciseList::add);
        final List<ExerciseDto> randomExercises = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int random = (int) getRandom(0, exerciseList.size());
            Exercise randomExercise = exerciseList.get(random);
            try {
                final String convertedImage = convertToBase64(randomExercise.getImage());
                randomExercise.setImage(convertedImage);
            }
            catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
            ExerciseDto convertedEx = exerciseMapper.exerciseToExerciseDTO(randomExercise);
            randomExercises.add(convertedEx);
        }
        return randomExercises;
    }

    private double getRandom(int min, int max){
        return Math.floor(Math.random() * (max - min)) + min;
    }

    private String convertToBase64(String img_path) throws IOException {
        try {
            // Resolve the file's absolute path dynamically
            System.out.println();
            Path path = Paths.get(getClass().getClassLoader().getResource("static/images/" + img_path).toURI());
            // Create a File object from the resolved path
            File file = path.toFile();

            // Read the file into a byte array
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] imageBytes = fileInputStream.readAllBytes();

                // Convert byte array to Base64
                return Base64.getEncoder().encodeToString(imageBytes);
            }
        } catch (Exception e) {
            // Handle errors
            System.err.println("Error converting file to Base64: " + e.getMessage());
            return "Error: Unable to convert image to Base64 for " + img_path;
        }
    }
}
