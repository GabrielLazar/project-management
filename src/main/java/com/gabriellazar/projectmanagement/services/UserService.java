package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import com.gabriellazar.projectmanagement.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO findByUserName(String username);
    void saveUser(UserDTO userDTO);
    void deleteUserById(Long id);
}
