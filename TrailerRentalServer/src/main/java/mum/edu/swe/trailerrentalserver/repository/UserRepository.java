package mum.edu.swe.trailerrentalserver.repository;

import mum.edu.swe.trailerrentalserver.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findUsersByEmailContains(String email);

    @Query(value = "SELECT u.* FROM user u WHERE u.first_name LIKE %:name% or u.last_name LIKE %:name%", nativeQuery = true)
    List<User> findUsersByName(@Param("name") String name);
}
