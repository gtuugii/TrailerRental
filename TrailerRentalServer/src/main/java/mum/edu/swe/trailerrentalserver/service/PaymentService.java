package mum.edu.swe.trailerrentalserver.service;

import mum.edu.swe.trailerrentalserver.domain.Payment;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentService extends BaseService<Payment> {

    public List<Payment> findAllByRentId(Long RentId);

    public List<Payment> findAllByUserId(Long UserId);

}
