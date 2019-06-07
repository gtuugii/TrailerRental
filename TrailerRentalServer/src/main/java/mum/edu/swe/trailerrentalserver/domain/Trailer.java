package mum.edu.swe.trailerrentalserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Entity
@Table(name="trailer")
@Getter @Setter
public class Trailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trailer_id")
    private Long Id;

    @OneToOne
    private TrailerType trailerType;

    @NotEmpty
    private String number;

    private String location;

    @Positive
    private float amount;

    //private Status.TRAILER status;

    private int status;

    private String feature;

    private String imagePath;
}
