package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.RoleDTO;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.RoleRepository;
import com.gabriellazar.projectmanagement.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        List<RoleDTO> roleDTOS = null;
        try {
            roleDTOS = roleRepository.findAll().stream().map((role) -> mapperUtil.convertToDTO(role, new RoleDTO())).collect(Collectors.toList());
            log.info("Getting all roles :: {}", roleDTOS);
        } catch (Exception e) {
            log.error("Exception in getting all roles :: {}", e);
            return Collections.emptyList();
        }
        return roleDTOS;
    }

    @Override
    public RoleDTO findRoleById(Long id) {
        RoleDTO roleDTO = null;
        try {
            roleDTO = mapperUtil.convertToDTO(roleRepository.findById(id).get(), new RoleDTO());
            log.info("Getting role by id {}", roleDTO.getId());
        } catch (Exception e) {
            log.error("Exception in finding role by id {} :: {}", id, e);
        }
        return roleDTO;
    }
}
