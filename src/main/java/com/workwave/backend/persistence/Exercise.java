package com.workwave.backend.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name="exercises")
public class Exercise {
    @Id
    private Long id;
    private String name;
    private String description;
    private String type;
    private String image;
}