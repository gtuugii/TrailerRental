package mum.edu.swe.trailerrentalserver.service.impl;

import mum.edu.swe.trailerrentalserver.domain.Rent;
import mum.edu.swe.trailerrentalserver.domain.RentList;
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
        return rentRepository.findAll(Sort.by("rent_date").descending());
    }

    @Override
    public Rent findById(Long id) {
        return rentRepository.findById(id).orElse(null);
    }

    @Override
    public List<RentList> findRentList(Long TrailerId) {
        return rentRepository.findRentList(TrailerId)
                .stream()
                .sorted(Comparator.comparing(RentList::getRentDate).reversed())
                .collect(Collectors.toList());
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
}
