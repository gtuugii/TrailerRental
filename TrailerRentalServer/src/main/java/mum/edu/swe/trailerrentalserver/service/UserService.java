package mum.edu.swe.trailerrentalserver.service;

import mum.edu.swe.trailerrentalserver.domain.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    public List<User> findUsersByEmail(String email);
    public List<User> findUsersByName(String name);

}
