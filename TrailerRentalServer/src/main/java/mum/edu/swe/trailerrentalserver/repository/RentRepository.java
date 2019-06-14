package mum.edu.swe.trailerrentalserver.repository;

import mum.edu.swe.trailerrentalserver.domain.Rent;
import mum.edu.swe.trailerrentalserver.domain.RentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query(value = "SELECT u.* " +
            "FROM rent r" +
            "LEFT JOIN trailer t on t.trailer_id=r.trailer_id" +
            "LEFT JOIN user u on u.user_id=r.user_id " +
            "WHERE t.trailer_id LIKE :TrailerId%",
            nativeQuery = true)
    List<RentView> findRentList(@Param("TrailerId") Long TrailerId);

    List<Rent> findRentsByTrailerId(Long TrailerId);

    List<Rent> findRentsByUserId(Long UserId);
}
