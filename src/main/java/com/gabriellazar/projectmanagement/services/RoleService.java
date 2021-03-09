package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.RoleDTO;


import java.util.*;

public interface RoleService {

    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Long id);
}
