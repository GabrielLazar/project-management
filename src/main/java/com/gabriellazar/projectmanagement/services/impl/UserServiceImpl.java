package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import com.gabriellazar.projectmanagement.entity.User;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.UserRepository;
import com.gabriellazar.projectmanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private MapperUtil mapperUtil;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<UserDTO> userDTOS = null;
        try {
            userDTOS = userRepository.findAll().stream()
                    .map(user -> mapperUtil.convertToDTO(user, new UserDTO()))
                    .sorted((u1, u2) -> u2.getId().compareTo(u1.getId()))
                    .collect(Collectors.toList());
            log.info("Getting all users: {}", userDTOS);
        } catch (Exception e) {
            log.error("Exception in getting all users :: {}", e);
            return Collections.emptyList();
        }
        return userDTOS;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUserNameIgnoreCase(username);
        return user.map(value -> mapperUtil.convertToDTO(value, new UserDTO())).orElse(null);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        try {
            User user = mapperUtil.convertToEntity(userDTO,new User());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Exception in saving user :: {}", userDTO);
            log.error("Exception was :: {}", e);
        }
        log.info("User was saved :: {}", userDTO);
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Exception when deleting user by id {} :: Exception {}", id, e);
        }
        log.info("Successfully deleted user with id :: {}", id);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        try {
            log.info("Start updating user with id {} :: {}", id, userDTO);
            User user = userRepository.findById(id).get();
            User updatedUser = mapperUtil.convertToEntity(userDTO, new User());
            updatedUser.setId(user.getId());
            userRepository.save(updatedUser);
            log.info("Successfully updated user :: {}", updatedUser);
        } catch (Exception e) {
            log.error("Exception in updating the project with id :: {} :: {}", id, userDTO);
            log.error("Exception was :: {}", e);
        }
        return findUserByUsername(userDTO.getUserName());
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserDTO userDTO = null;
        try{
           userDTO = mapperUtil.convertToDTO(userRepository.findById(id).get(), new UserDTO());
        } catch (Exception e){
            log.error("Exception in finding user by id :: {}",id);
        }
        return userDTO;
    }

    @Override
    public Page<UserDTO> findPageableUser(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<UserDTO> userDTOPage = null;
        try{
            userDTOPage = userRepository.findAllByOrderByLastUpdateDateTimeDesc(pageable).map(user -> mapperUtil.convertToDTO(user, new UserDTO()));
        } catch (Exception e){
            log.error("Exception in finding all pageable users :: {}",e);
        }
        return userDTOPage;
    }

    @Override
    public List<UserDTO> findAllUsersByRole(String roleDescription) {
        List<UserDTO> userDTOS = null;
        try{
            userDTOS = this.findAllUsers().stream()
                    .filter(u -> u.getRole().getDescription().equals(roleDescription))
                    .collect(Collectors.toList());
        } catch (Exception e){
            log.error("Exception in finding all users by role :: {}",e);
            return Collections.emptyList();
        }
        return userDTOS;
    }
}
