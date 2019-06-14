package mum.edu.swe.trailerrentalclient.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class Trailer {

    private Long trailerId;

    private int typeId;

    @NotEmpty
    private String number;

    @NotEmpty
    private String location;

    private float amount;

    // 0-MAINTENANCE 1-ACTIVE, 2-PENDING, 3-RENTED
    private int status;

    @NotEmpty
    private String feature;


    private String imagePath;
}