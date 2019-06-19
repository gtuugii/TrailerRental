package mum.edu.swe.trailerrentalserver.repository;

import mum.edu.swe.trailerrentalserver.domain.Payment;
import mum.edu.swe.trailerrentalserver.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByRentId(Long RentId);

    List<Payment> findAllByUserId(Long UserId);

    @Query(value = " SELECT p.* " +
            " FROM payment p " +
            " LEFT JOIN rent r on r.rent_id=p.rent_id " +
            " LEFT JOIN trailer t on t.trailer_id=r.trailer_id " +
            " WHERE t.number LIKE %:number%  ",
            nativeQuery = true)
    List<Payment> findAllByNumberContains(String number);

}
