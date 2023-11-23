package com.bookmyshow.model;

import com.bookmyshow.model.BaseModel;
import com.bookmyshow.model.Ticket;
import com.bookmyshow.model.constants.PaymentMethod;
import com.bookmyshow.model.constants.SeatStatus;
import com.bookmyshow.model.constants.ShowSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private LocalDateTime paymentTime;
    private double amount;
    private String referenceId;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSeatStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @ManyToOne
    private Ticket ticket;
}

// TODO : Create the models for multi-transaction payment

/* Multi-Transaction payment

Transaction class
    @OneToMany
    List<Transaction> inside payment class

    Ticket - Payment
       1   -    M
       1   -    1 */