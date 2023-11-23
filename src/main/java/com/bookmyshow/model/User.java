package com.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "User_Table")
public class User extends BaseModel{
    private String name;
    private String email;
}
