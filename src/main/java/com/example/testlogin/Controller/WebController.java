package com.example.testlogin.Controller;

import com.example.testlogin.AuthSuccHandler;
import com.example.testlogin.DBcontroller.Repository;
import com.example.testlogin.Model.MyUser;
import com.example.testlogin.Service.Service;
import com.example.testlogin.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserDetailsService userDetailsService;
    private final Service service;
    private final Repository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebController(UserDetailsService userDetailsService, Service service, Repository repository, PasswordEncoder passwordEncoder) {
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
