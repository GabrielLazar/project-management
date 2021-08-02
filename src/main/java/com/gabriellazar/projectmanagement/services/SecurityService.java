package com.gabriellazar.projectmanagement.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService extends UserDetailsService {

    UserDetails loadUserByUsername(String s);
}
