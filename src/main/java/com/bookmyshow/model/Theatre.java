package com.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;
    private String address;
    @ManyToOne
    private City city;
}
