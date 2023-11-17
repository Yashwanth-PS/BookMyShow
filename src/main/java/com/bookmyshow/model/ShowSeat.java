package com.bookmyshow.model;

import com.bookmyshow.model.constants.ShowSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    private int price;
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.STRING) // Create Table for the Enum
    private ShowSeatStatus showSeatStatus;
}
/* ShowSeat : Show
       1    :  1
       M    :  1
   ShowSeat : Show -> M : 1

    Seat : ShowSeat
      1  :    M
      1  :    1
ShowSeat : Seat -> M : 1 */