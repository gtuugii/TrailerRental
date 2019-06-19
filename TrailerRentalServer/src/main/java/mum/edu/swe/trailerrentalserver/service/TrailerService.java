package mum.edu.swe.trailerrentalserver.service;

import mum.edu.swe.trailerrentalserver.domain.Trailer;

import java.util.List;

public interface TrailerService extends BaseService<Trailer> {
    List<Trailer> findByNumberContainsAndStatus(String number, Integer status);
    List<Trailer> findAllByNumberContains(String number);

    public List<Trailer> findAllByStatus(Integer status);
    public void updateTrailerStatus(Long trailer_id, Integer status);
    public Integer countAllByStatus(Integer status);
}
