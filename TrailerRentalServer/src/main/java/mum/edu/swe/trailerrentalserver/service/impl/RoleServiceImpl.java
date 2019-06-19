package mum.edu.swe.trailerrentalserver.service.impl;

import mum.edu.swe.trailerrentalserver.domain.Role;
import mum.edu.swe.trailerrentalserver.repository.RoleRepository;
import mum.edu.swe.trailerrentalserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean delete(Long id) {
        roleRepository.deleteById(id);
        return true;
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
