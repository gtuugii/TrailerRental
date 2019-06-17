package mum.edu.swe.trailerrentalserver.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RentView {

    private Long rentId;

    private Trailer trailerId;

    private String trailerNumber;

    private User userId;

    private String customerName;

    private String email;

    private LocalDate rentDate;

    private LocalDate dueDate;

    private LocalDate payDate;

    private float amount;

    private int status;
    
}
