package mum.edu.swe.trailerrentalserver.repository;

import mum.edu.swe.trailerrentalserver.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);

}
