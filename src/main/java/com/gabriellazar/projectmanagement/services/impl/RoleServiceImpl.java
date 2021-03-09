package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.RoleDTO;
import com.gabriellazar.projectmanagement.entity.Role;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.RoleRepository;
import com.gabriellazar.projectmanagement.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private MapperUtil mapperUtil;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
       return roleRepository.findAll().stream().map((role) -> mapperUtil.convertToDTO(role,new RoleDTO())).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findRoleById(Long id) {
        return mapperUtil.convertToDTO(roleRepository.findById(id).get(),new RoleDTO());
    }
}
