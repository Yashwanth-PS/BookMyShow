package com.bookmyshow.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "User_Table")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
}
