package com.kilbas.security;

import com.kilbas.exception.NotFoundEntityException;
import com.kilbas.model.User;
import com.kilbas.repository.api.UserRepository;
import com.kilbas.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userService) {
        this.userRepository = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByField("email", username)
                .orElseThrow(() -> new NotFoundEntityException("user not found"));
        return JwtUserFactory.create(user);
    }
}