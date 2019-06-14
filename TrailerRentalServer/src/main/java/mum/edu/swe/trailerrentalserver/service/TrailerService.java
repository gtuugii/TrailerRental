package mum.edu.swe.trailerrentalserver.service;

import mum.edu.swe.trailerrentalserver.domain.Trailer;

import java.util.List;

public interface TrailerService extends BaseService<Trailer> {
    public Trailer findAllByNumber(String number);
}
