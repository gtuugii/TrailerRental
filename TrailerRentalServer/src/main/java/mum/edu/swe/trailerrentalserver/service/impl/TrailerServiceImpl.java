package mum.edu.swe.trailerrentalserver.service.impl;

import mum.edu.swe.trailerrentalserver.domain.Trailer;
import mum.edu.swe.trailerrentalserver.repository.TrailerRepository;
import mum.edu.swe.trailerrentalserver.service.TrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional
public class TrailerServiceImpl implements TrailerService {

    @Autowired
    private TrailerRepository trailerRepository;

    @Override
    public Trailer save(Trailer trailer) {
        return trailerRepository.save(trailer);
    }

    @Override
    public boolean delete(Long id) {
        trailerRepository.deleteById(id);
        return true;
    }

    @Override
    public Trailer update(Trailer trailer) {
        return trailerRepository.save(trailer);
    }

    @Override
    public List<Trailer> findAll() {
        return (List<Trailer>) trailerRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Trailer::getTrailerId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Trailer findById(Long id) {
        return trailerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Trailer> findAllByStatus(Integer status) {
        return trailerRepository.findAllByStatus(status);
    }

    @Override
    public void updateTrailerStatus(Long trailer_id, Integer status) {
        System.out.println("trailer_id: " + trailer_id + ", status: " + status);
        trailerRepository.updateTrailerStatus(trailer_id, status);
        //return trailerRepository.findById(trailer_id).orElse(null);
    }

    @Override
    public Integer countAllByStatus(Integer status) {
        return trailerRepository.countAllByStatus(status);
    }

    @Override
    public List<Trailer> findByNumberContainsAndStatus(String number, Integer status) {
        return trailerRepository.findByNumberContainsAndStatus(number, status);
    }

    @Override
    public List<Trailer> findAllByNumberContains(String number) {
        return trailerRepository.findAllByNumberContains(number);
    }
}
