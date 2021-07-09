package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import com.gabriellazar.projectmanagement.entity.User;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.UserRepository;
import com.gabriellazar.projectmanagement.services.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private MapperUtil mapperUtil;

    public UserServiceImpl(@Lazy UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> findAllUsers() {
       return userRepository.findAll().stream()
               .map(user -> mapperUtil.convertToDTO(user,new UserDTO()))
               .sorted((u1,u2) -> u2.getId().compareTo(u1.getId()))
               .collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUserNameIgnoreCase(username);
        return user.map(value -> mapperUtil.convertToDTO(value, new UserDTO())).orElse(null);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        userRepository.save(mapperUtil.convertToEntity(userDTO,new User()));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(Long id,UserDTO userDTO) {
       User user = userRepository.findById(id).get();
       User updatedUser = mapperUtil.convertToEntity(userDTO,new User());
       updatedUser.setId(user.getId());
       userRepository.save(updatedUser);
       return findUserByUsername(updatedUser.getUserName());
    }

    @Override
    public UserDTO findUserById(Long id) {
        return mapperUtil.convertToDTO(userRepository.findById(id).get(),new UserDTO());
    }

    @Override
    public Page<UserDTO> findPageableUser(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return userRepository.findAllByOrderByLastUpdateDateTimeDesc(pageable).map( user -> mapperUtil.convertToDTO(user,new UserDTO()));
    }

    @Override
    public List<UserDTO> findAllUsersByRole(String roleDescription) {
        return this.findAllUsers().stream()
                .filter( u -> u.getRole().getDescription().equals(roleDescription))
                .collect(Collectors.toList());
    }
}
