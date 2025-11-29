package com.jtspringproject.JtSpringProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final userService userService;
    private final categoryService categoryService;
    private final productService productService;

    @Autowired
    public AdminController(userService userService, categoryService categoryService, productService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    // 🔥 Admin Login Page
    @GetMapping("/login")
    public ModelAndView adminLogin(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("adminlogin");
        if ("true".equals(error)) {
            mv.addObject("msg", "Invalid username or password. Please try again.");
        }
        return mv;
    }

    // 🔥 Dashboard after login
    @GetMapping("/home")
    public ModelAndView adminHome() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView mv = new ModelAndView("adminHome");
        mv.addObject("admin", authentication.getName());
        return mv;
    }

    // 🔥 Redirect root → Dashboard
    @GetMapping("/")
    public String redirectToDashboard() {
        return "redirect:/admin/home";
    }

    // 🔥 Category list page
    @GetMapping("/categories")
    public ModelAndView getCategories() {
        ModelAndView mView = new ModelAndView("categories");
        List<Category> categories = this.categoryService.getCategories();
        mView.addObject("categories", categories);
        return mView;
    }

    @PostMapping("/categories")
    public String addCategory(@RequestParam("categoryname") String categoryName) {
        categoryService.addCategory(categoryName);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete")
    public String deleteCategory(@RequestParam("id") int id) {
        this.categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update")
    public String updateCategory(@RequestParam("categoryid") int id, @RequestParam("categoryname") String categoryName) {
        this.categoryService.updateCategory(id, categoryName);
        return "redirect:/admin/categories";
    }

    // 🔥 Product Management
    @GetMapping("/products")
    public ModelAndView getProducts() {
        ModelAndView mView = new ModelAndView("products");
        List<Product> products = this.productService.getProducts();
        mView.addObject("products", products);
        return mView;
    }

    @GetMapping("/products/add")
    public ModelAndView addProductForm() {
        ModelAndView mView = new ModelAndView("productsAdd");
        mView.addObject("categories", categoryService.getCategories());
        return mView;
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("categoryid") int categoryId,
                             @RequestParam("price") int price,
                             @RequestParam("weight") int weight,
                             @RequestParam("quantity") int quantity,
                             @RequestParam("description") String description,
                             @RequestParam("productImage") String productImage) {

        Category category = this.categoryService.getCategory(categoryId);
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(productImage);
        product.setWeight(weight);
        product.setQuantity(quantity);

        this.productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public ModelAndView updateProductForm(@PathVariable("id") int id) {
        ModelAndView mView = new ModelAndView("productsUpdate");
        mView.addObject("categories", categoryService.getCategories());
        mView.addObject("product", productService.getProduct(id));
        return mView;
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") int id,
                                @RequestParam("name") String name,
                                @RequestParam("categoryid") int categoryId,
                                @RequestParam("price") int price,
                                @RequestParam("weight") int weight,
                                @RequestParam("quantity") int quantity,
                                @RequestParam("description") String description,
                                @RequestParam("productImage") String productImage) {

        Category category = categoryService.getCategory(categoryId);
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(productImage);
        product.setWeight(weight);
        product.setQuantity(quantity);

        productService.updateProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete")
    public String deleteProduct(@RequestParam("id") int id) {
        this.productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    // 🔥 Display all customers
    @GetMapping("/customers")
    public ModelAndView getCustomers() {
        ModelAndView mView = new ModelAndView("displayCustomers");
        mView.addObject("customers", userService.getUsers());
        return mView;
    }
}
