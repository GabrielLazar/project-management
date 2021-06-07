package com.gabriellazar.projectmanagement.repository;

import com.gabriellazar.projectmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role,Long> {
}
