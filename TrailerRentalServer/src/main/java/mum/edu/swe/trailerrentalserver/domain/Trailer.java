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
    private Long trailerId;

    @NotNull
    @Column(name="type_id")
    private int typeId;

    @NotEmpty
    private String number;

    private String location;

    @Positive
    private float amount;

    // 0-MAINTENANCE 1-ACTIVE, 2-PENDING, 3-RENTED
    private int status;

    private String feature;

    @Column(name="image_path")
    private String imagePath;

    public Trailer(@NotNull int typeId, @NotEmpty String number, String location, @Positive float amount, int status, String feature, String imagePath) {
        this.typeId = typeId;
        this.number = number;
        this.location = location;
        this.amount = amount;
        this.status = status;
        this.feature = feature;
        this.imagePath = imagePath;
    }
}
