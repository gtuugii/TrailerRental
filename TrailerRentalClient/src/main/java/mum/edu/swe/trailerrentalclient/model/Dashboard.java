package mum.edu.swe.trailerrentalclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard {

    private Long availTrailers;
    private Long underTrailers;
    private Long totalTrailers;
    private Long totalTenants;
    private Long totalPayments;
    private Long totalRents;

}
