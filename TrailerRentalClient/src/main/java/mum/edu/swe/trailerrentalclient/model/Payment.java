package mum.edu.swe.trailerrentalclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class Payment {

    private Long paymentId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate payDate;

    @NotNull
    private float paidAmount;

    private Rent rentId;

    //private User userId;

}
