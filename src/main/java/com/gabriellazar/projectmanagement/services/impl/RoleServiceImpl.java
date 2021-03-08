package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.RoleDTO;
import com.gabriellazar.projectmanagement.repository.RoleRepository;
import com.gabriellazar.projectmanagement.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return null;
    }
}
