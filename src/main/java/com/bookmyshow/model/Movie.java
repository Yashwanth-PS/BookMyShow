package com.bookmyshow.model;

import com.bookmyshow.model.constants.MovieFeature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String name;
    private String description;
    private String language;
    @ManyToMany
    private List<Actor> actors;
    @ElementCollection // Create mapping table for Movie and MovieFeatures
    @Enumerated(EnumType.STRING) // Create Table for the Enum
    private List<MovieFeature> movieFeatures;
}
