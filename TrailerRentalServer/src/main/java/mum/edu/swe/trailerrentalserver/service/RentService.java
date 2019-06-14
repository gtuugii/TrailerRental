package mum.edu.swe.trailerrentalserver.service;


import mum.edu.swe.trailerrentalserver.domain.Rent;
import mum.edu.swe.trailerrentalserver.domain.RentList;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentService extends BaseService<Rent> {
    public List<RentList> findRentList(Long TrailerId);

    public  List<Rent> findRentsByTrailerId(Long TrailerId);

    public List<Rent> findRentsByUserId(Long TrailerId);
}
