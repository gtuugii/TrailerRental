package mum.edu.swe.trailerrentalserver.service.impl;

import mum.edu.swe.trailerrentalserver.domain.Trailer;
import mum.edu.swe.trailerrentalserver.repository.TrailerRepository;
import mum.edu.swe.trailerrentalserver.service.TrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
        return (List<Trailer>) trailerRepository.findAll();
    }

    @Override
    public Trailer findById(Long id) {
        return trailerRepository.findById(id).get();
    }
}
