package com.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass// Marks this class as the superclass of all the classes
public abstract class BaseModel {
    @Id // Tells JPA that the attribute is the primary key of the table
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto Increment ID
    @Column(name = "ID")
    private long id;

    /* @CreatedDate // Mark as a Audit Column
    @Temporal(value = TemporalType.TIMESTAMP) // Time of Creation and Update should be Automated
    private Date createdAt;

    @LastModifiedDate // Mark as a Audit Column
    @Temporal(value = TemporalType.TIMESTAMP) // Time of Creation and Update should be Automated
    private Date updatedAt; */
}
