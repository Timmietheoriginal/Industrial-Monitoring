package com.example.Industrial_monitoring.service;

import com.example.Industrial_monitoring.model.UserDetailsImpl;
import com.example.Industrial_monitoring.model.Users;
import com.example.Industrial_monitoring.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepo;

    public MyUserDetailService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(user);
    }
}
