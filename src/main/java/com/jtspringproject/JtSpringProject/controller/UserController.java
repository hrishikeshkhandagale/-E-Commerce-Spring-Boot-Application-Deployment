package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.productService;

@Controller
public class UserController {

    private final userService userService;
    private final productService productService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(userService userService, productService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    /* ---------------- ROOT ---------------- */
    @GetMapping("/")
    public String redirectHomeToLogin() {
        return "redirect:/login";
    }

    /* ---------------- LOGIN PAGE ---------------- */
    @GetMapping("/login")
    public ModelAndView userLogin(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("userLogin");
        if ("true".equals(error)) {
            mv.addObject("msg", "Please enter correct email and password");
        }
        return mv;
    }

    /* ---------------- REGISTER PAGE ---------------- */
    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    /* ---------------- USER REGISTRATION ---------------- */
    @PostMapping("/newuserregister")
    public ModelAndView newUserRegister(@ModelAttribute User user) {
        boolean exists = this.userService.checkUserExists(user.getUsername());

        if (!exists) {
            user.setRole("ROLE_NORMAL");
            user.setPassword(passwordEncoder.encode(user.getPassword())); // encode password
            this.userService.addUser(user);
            return new ModelAndView("userLogin");
        } else {
            ModelAndView mv = new ModelAndView("register");
            mv.addObject("msg", user.getUsername() + " is taken. Please choose a different username.");
            return mv;
        }
    }

    /* ---------------- USER DASHBOARD ---------------- */
    @GetMapping("/home")
    public ModelAndView userHome() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("username", username);

        List<Product> products = this.productService.getProducts();
        mv.addObject("products", products != null ? products : List.of());

        if (products == null || products.isEmpty())
            mv.addObject("msg", "No products are available");

        return mv;
    }

    /* ---------------- ALL PRODUCTS PAGE ---------------- */
    @GetMapping("/user/products")
    public ModelAndView getProducts() {
        ModelAndView mv = new ModelAndView("uproduct");
        List<Product> products = this.productService.getProducts();

        mv.addObject("products", products != null ? products : List.of());

        if (products == null || products.isEmpty())
            mv.addObject("msg", "No products are available");

        return mv;
    }

    /* ---------------- BUY PAGE ---------------- */
    @GetMapping("/buy")
    public String buy() {
        retu
