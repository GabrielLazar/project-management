package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import java.util.*;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
}
