package mum.edu.swe.trailerrentalserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="payment")
@Getter @Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long paymentId;

    @Column(name="pay_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate payDate;

    @NotNull
    @Column(name="paid_amount")
    private float paidAmount;

    @ManyToOne
    @JsonIgnore
    private Rent rentId;

    @ManyToOne
    @JsonIgnore
    private User userId;

}
