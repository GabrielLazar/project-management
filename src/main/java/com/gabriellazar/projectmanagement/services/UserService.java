package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface UserService {

    List<UserDTO> findAllUsers();
    UserDTO findUserById(Long id);
    UserDTO findUserByUsername(String username);
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(Long id,UserDTO userDTO);
    void deleteUserById(Long id);
    Page<UserDTO> findPageableUser(int pageNo, int pageSize);
    List<UserDTO> findAllUsersByRole(String roleDescription);


}
