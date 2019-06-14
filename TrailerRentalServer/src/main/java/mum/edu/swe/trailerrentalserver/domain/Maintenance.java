package mum.edu.swe.trailerrentalserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="Maintenance")
@Getter
@Setter
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="main_id")
    private Long mainId;

    @Column(name="remind_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate remindDate;

    @Column(name="create_date")
    private LocalDate createDate;

    //0-Closed, 1-Open 2-
    @NotNull
    @Column(name="status")
    private int status;

    @NotEmpty
    @Column(name="comment")
    private String comment;

    //@NotEmpty
    @Column(name="description")
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="trailer_id")
    private Trailer trailerId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User userId;

}
