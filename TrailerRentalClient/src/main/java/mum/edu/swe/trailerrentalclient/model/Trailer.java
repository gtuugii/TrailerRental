package mum.edu.swe.trailerrentalclient.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class Trailer {

    private Long trailerId;

    @NotNull
    private int typeId;

    @NotEmpty
    private String number;

    private String location;

    @Positive
    private float amount;

    // 0-MAINTENANCE 1-ACTIVE, 2-PENDING, 3-RENTED
    private int status;

    private String feature;

    private String imagePath;
}
