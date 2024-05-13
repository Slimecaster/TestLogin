package com.example.testlogin.Controller;

import com.example.testlogin.AuthSuccHandler;
import com.example.testlogin.DBcontroller.Repository;
import com.example.testlogin.Model.MyUser;
import com.example.testlogin.Service.CustomUserDetailsService;
import com.example.testlogin.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;
import java.util.Optional;

@Controller
public class WebController {
    private final CustomUserDetailsService userDetailsService;
    private final Service service;
    private final Repository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebController(CustomUserDetailsService userDetailsService, Service service, Repository repository, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.service = service;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }
    @GetMapping("/admin/homepage")
    public String adminHome() {
        return "homepage_admin";
    }

    @GetMapping("/user/homepage")
    public String userHome() {
        return "homepage_user";
    }

    @GetMapping("/login/register/user")
    public String registerUser(Model model){
        model.addAttribute("MyUser", new MyUser());
        return "registerUser";
    }

    @PostMapping("/login/register/user")
    public String registerUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.createUpdateUser(user);
        return "redirect:/login/register/user/userCreatedSuccess";

    }
    @GetMapping("/login/register/user/userCreatedSuccess")
    public String userCreatedSuccess() {
        return "userCreatedSuccess";
    }




}
