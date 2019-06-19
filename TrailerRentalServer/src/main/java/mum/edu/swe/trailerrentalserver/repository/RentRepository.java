package mum.edu.swe.trailerrentalserver.repository;

import mum.edu.swe.trailerrentalserver.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

//    @Query(value = "SELECT r.* " +
//            "FROM rent r" +
//            "LEFT JOIN trailer t on t.trailer_id=r.trailer_id" +
//            "LEFT JOIN user u on u.user_id=r.user_id " +
//            "WHERE t.trailer_id LIKE :TrailerId%",
//            nativeQuery = true)
//    List<RentView> findRentList(@Param("TrailerId") Long TrailerId);

    List<Rent> findRentsByTrailerId(Long TrailerId);
    List<Rent> findRentsByUserId(Long UserId);

    @Query(value = " SELECT r.* " +
            " FROM rent r " +
            " LEFT JOIN trailer t on t.trailer_id=r.trailer_id " +
            " WHERE t.number LIKE %:number% and r.status = :status ",
            nativeQuery = true)
    List<Rent> findByNumberContainsAndStatus(String number, Integer status);

    @Query(value = " SELECT r.* " +
            " FROM rent r " +
            " LEFT JOIN trailer t on t.trailer_id=r.trailer_id " +
            " WHERE t.number LIKE %:number%  ",
            nativeQuery = true)
    List<Rent> findByNumberContains(String number);

    List<Rent> findAllByStatus(Integer status);

    @Modifying
    @Query(value="UPDATE rent SET status=:status WHERE rent_id=:rent_id", nativeQuery = true)
    void updateRentStatus(Long rent_id, Integer status);

    Integer countAllByStatus(Integer status);

    //Long count
}
