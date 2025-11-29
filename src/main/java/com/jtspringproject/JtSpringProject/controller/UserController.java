package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public UserController(userService userService, productService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    // 🔥 ROOT URL → Auto Redirect to login
    @GetMapping("/")
    public String redirectHomeToLogin() {
        return "redirect:/login";
    }

    // 🔥 User Login Page
    @GetMapping("/login")
    public ModelAndView userLogin(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("userLogin");
        if ("true".equals(error)) {
            mv.addObject("msg", "Please enter correct email and password");
        }
        return mv;
    }

    // 🔥 User Registration
    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    @PostMapping("/newuserregister")
    public ModelAndView newUserRegister(@ModelAttribute User user) {
        boolean exists = this.userService.checkUserExists(user.getUsername());

        if (!exists) {
            user.setRole("ROLE_NORMAL");
            this.userService.addUser(user);
            return new ModelAndView("userLogin");
        } else {
            ModelAndView mv = new ModelAndView("register");
            mv.addObject("msg", user.getUsername() + " is taken. Please choose a different username.");
            return mv;
        }
    }

    // 🔥 User Dashboard (after login)
    @GetMapping("/home")
    public ModelAndView userHome() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("username", username);

        List<Product> products = this.productService.getProducts();
        if (products.isEmpty()) {
            mv.addObject("msg", "No products are available");
        } else {
            mv.addObject("products", products);
        }
        return mv;
    }

    // 🔥 Product Page
    @GetMapping("/user/products")
    public ModelAndView getProducts() {
        ModelAndView mv = new ModelAndView("uproduct");
        List<Product> products = this.productService.getProducts();

        if (products.isEmpty()) {
            mv.addObject("msg", "No products are available");
        } else {
            mv.addObject("products", products);
        }
        return mv;
    }

    // 🔥 Buy Page
    @GetMapping("/buy")
    public String buy() {
        return "buy";
    }

    // 🔥 Profile View
    @GetMapping("/profileDisplay")
    public String profileDisplay(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);

        if (user != null) {
            model.addAttribute("userid", user.getId());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("address", user.getAddress());
        } else {
            model.addAttribute("msg", "User not found");
        }
        return "updateProfile";
    }
}
