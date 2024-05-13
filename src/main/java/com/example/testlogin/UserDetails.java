package com.example.testlogin;

import com.example.testlogin.Model.MyUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override

    public String getPassword() {
        return this.getPassword();  // Antager at 'this.password' indeholder brugerens hashede password

    }



    @Override

    public String getUsername() {

        return this.getUsername();  // Antager at du bruger e-mail som brugernavn

    }



    @Override

    public boolean isAccountNonExpired() {

        return true;

    }



    @Override

    public boolean isAccountNonLocked() {

        return true;

    }



    @Override

    public boolean isCredentialsNonExpired() {

        return true;

    }



    @Override

    public boolean isEnabled() {

        return true;

    }
}
