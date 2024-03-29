package mum.edu.swe.trailerrentalclient.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.Transient;

@Getter
@Setter
public class Trailer {

    private Long trailerId;

    @NotNull
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

    @Transient
    public String getTrailerAsText() {
        return trailerId + "-" + number;
    }
}
