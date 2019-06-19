package mum.edu.swe.trailerrentalserver.repository;

import mum.edu.swe.trailerrentalserver.domain.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long> {

    List<Trailer> findByNumberContainsAndStatus(String number, Integer status);
    List<Trailer> findAllByNumberContains(String number);

    //  put(0, "MAINTENANCE");
    //  put(1, "ACTIVE");
    //  put(2, "PENDING");
    //  put(3, "RENTED");
    List<Trailer> findAllByStatus(Integer status);

    @Modifying
    @Query(value="UPDATE trailer SET status=:status WHERE trailer_id=:trailer_id", nativeQuery = true)
    void updateTrailerStatus(Long trailer_id, Integer status);

    Integer countAllByStatus(Integer status);

}
