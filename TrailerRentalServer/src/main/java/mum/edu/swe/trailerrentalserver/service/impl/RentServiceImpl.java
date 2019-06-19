package mum.edu.swe.trailerrentalserver.service.impl;

import mum.edu.swe.trailerrentalserver.domain.Rent;
import mum.edu.swe.trailerrentalserver.repository.RentRepository;
import mum.edu.swe.trailerrentalserver.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Override
    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public boolean delete(Long id) {
        rentRepository.deleteById(id);
        return true;
    }

    @Override
    public Rent update(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll(Sort.by("rentDate").descending());
        //return rentRepository.findAll();
    }

    @Override
    public Rent findById(Long id) {
        return rentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rent> findRentsByTrailerId(Long TrailerId) {
        return rentRepository.findRentsByTrailerId(TrailerId)
                .stream()
                .sorted(Comparator.comparing(Rent::getRentDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> findRentsByUserId(Long UserId) {
        return rentRepository.findRentsByUserId(UserId)
                .stream()
                .sorted(Comparator.comparing(Rent::getRentDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> findByNumberContainsAndStatus(String number, Integer status) {
        System.out.println("findByNumberContainsAndStatus");
        return rentRepository.findByNumberContainsAndStatus(number, status);
    }

    @Override
    public List<Rent> findAllByNumberContains(String number) {
        System.out.println("findAllByNumberContains");
        return rentRepository.findByNumberContains(number);
    }

    @Override
    public List<Rent> findAllByStatus(Integer status) {
        System.out.println("findAllByStatus");
        return rentRepository.findAllByStatus(status);
    }

    @Override
    public void updateRentStatus(Long rent_id, Integer status) {
        System.out.println("rent_id: " + rent_id + ", status: " + status);
        rentRepository.updateRentStatus(rent_id, status);
        //return rentRepository.findById(rent_id).get();
    }

    @Override
    public Integer countAllByStatus(Integer status) {
        return rentRepository.countAllByStatus(status);
    }


}
