package mum.edu.swe.trailerrentalserver.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RentView {

    private Long rentId;

    private Long trailerId;

    private String trailerNumber;

    private Long userId;

    private String customerName;

    private String email;

    private LocalDate rentDate;

    private LocalDate dueDate;

    private LocalDate payDate;

    private float amount;

    private int status;

//    public LocalDate getRentDate() {
//        return rentDate;
//    }
//
//    public void setRentDate(LocalDate rentDate) {
//        this.rentDate = rentDate;
//    }
}
