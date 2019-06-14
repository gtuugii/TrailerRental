package mum.edu.swe.trailerrentalserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name="rent")
@Getter @Setter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rent_id")
    private Long rentId;

    //0-CLOSE, 1-PENDING, 2-OPEN
    @Column(name="status")
    private int status;

    @Column(name="rent_date")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate rentDate;

    @Column(name="due_date")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull
    private float amount;

    @Column(name="pay_date")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate payDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="trailer_id")
    private Trailer trailerId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User userId;

}
