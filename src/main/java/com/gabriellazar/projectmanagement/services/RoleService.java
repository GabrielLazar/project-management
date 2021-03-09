package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface RoleService {

    List<RoleDTO> getAllRoles();
    RoleDTO findRoleById(Long id);
}
