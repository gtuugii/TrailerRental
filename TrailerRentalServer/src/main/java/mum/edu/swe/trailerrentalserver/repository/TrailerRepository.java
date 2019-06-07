package mum.edu.swe.trailerrentalserver.repository;

import mum.edu.swe.trailerrentalserver.domain.Trailer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrailerRepository extends CrudRepository<Trailer, Long> {

}
