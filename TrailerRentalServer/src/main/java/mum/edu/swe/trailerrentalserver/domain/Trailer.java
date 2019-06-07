package mum.edu.swe.trailerrentalserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="trailer")
@Getter
@Setter
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

    private float amount;

    private Status.TRAILER status;

    //private int status;

    private String feature;
}
