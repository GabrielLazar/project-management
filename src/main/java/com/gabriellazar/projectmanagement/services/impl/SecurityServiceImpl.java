package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.entity.User;
import com.gabriellazar.projectmanagement.entity.UserPrincipal;
import com.gabriellazar.projectmanagement.repository.UserRepository;
import com.gabriellazar.projectmanagement.services.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
      User user =   userRepository.findByUserNameIgnoreCase(s).orElse(null);
      if(user == null){
          throw new UsernameNotFoundException("This user does not exists");
      }
        return new UserPrincipal(user);
    }
}
