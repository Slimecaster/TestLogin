package com.example.testlogin.Service;

import com.example.testlogin.DBcontroller.Repository;
import com.example.testlogin.Model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Repository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsService(Repository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findUserByEmail(email);
        if (user.isPresent()) {
            MyUser userObj = user.get();
            return User.builder()
                    .username(userObj.getEmail())  // Use email as the username
                    .password(passwordEncoder.encode(userObj.getPassword()))
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


    private String[] getRoles(MyUser user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}