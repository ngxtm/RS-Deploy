package com.rs.retailstore.service;

import com.rs.retailstore.entity.UserProfile;
import com.rs.retailstore.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserProfile> user = userProfileRepository.findByUsername(username);
        List<GrantedAuthority> role = null;
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        username = user.getFirst().getUsername();
        String password = user.getFirst().getPassword();
        role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(user.getFirst().getRole()));
        return new User(username, password, role);
    }
}
