package mum.edu.swe.trailerrentalserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name="trailer")
@Getter @Setter
public class Trailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trailer_id")
    private Long trailer_id;

    @NotNull
    private int type_id;

    @NotEmpty
    private String number;
    
    private String location;

    @Positive
    private float amount;

    // 0-ACTIVE, 1-PENDING, 2-RENTED, 3-MAINTENANCE
    private int status;

    private String feature;

    private String imagePath;
}
