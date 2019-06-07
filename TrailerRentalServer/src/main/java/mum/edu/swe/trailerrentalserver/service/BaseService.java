package mum.edu.swe.trailerrentalserver.service;

import java.util.ArrayList;
import java.util.List;

public interface BaseService<T> {

    T save(T t);

    boolean delete(Long id);

    T update(T t);

    List<T> findAll();

    T findById(Long id);

}
