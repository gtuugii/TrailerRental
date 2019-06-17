package mum.edu.swe.trailerrentalclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class Rent {

    private Long rentId;

    //0-CLOSE, 1-PENDING, 2-OPEN
    private int status;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate rentDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull
    private float amount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate payDate;

    //private Long trailerId;
    private Trailer trailerId;

    //private Long userId;
    private User userId;
}
