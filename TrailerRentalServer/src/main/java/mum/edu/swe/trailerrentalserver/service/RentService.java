package mum.edu.swe.trailerrentalserver.service;

import mum.edu.swe.trailerrentalserver.domain.Rent;

import java.util.List;

public interface RentService extends BaseService<Rent> {
    public  List<Rent> findRentsByTrailerId(Long TrailerId);
    public List<Rent> findRentsByUserId(Long TrailerId);

    List<Rent> findByNumberContainsAndStatus(String number, Integer status);
    List<Rent> findAllByNumberContains(String number);

    public List<Rent> findAllByStatus(Integer status);
    public void updateRentStatus(Long rent_id, Integer status);
    public Integer countAllByStatus(Integer status);

    //public Integer countAll();
}
