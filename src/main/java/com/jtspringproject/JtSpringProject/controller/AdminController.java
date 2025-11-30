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

    /* 🔥 Admin Dashboard */
    @GetMapping({"/", "/index", "/Dashboard"})
    public ModelAndView adminHome() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView mv = new ModelAndView("adminHome");
        mv.addObject("admin", authentication.getName());
        return mv;
    }

    /* 🔥 Admin Login Page */
    @GetMapping("/login")
    public ModelAndView adminlogin(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("adminlogin");
        if ("true".equals(error)) {
            mv.addObject("msg", "Invalid username or password. Please try again.");
        }
        return mv;
    }

    /* ------------- CATEGORY SECTION ------------- */
    @GetMapping("/categories")
    public ModelAndView getcategory() {
        ModelAndView mView = new ModelAndView("categories");
        mView.addObject("categories", this.categoryService.getCategories());
        return mView;
    }

    @PostMapping("/categories")
    public String addCategory(@RequestParam("categoryname") String category_name) {
        this.categoryService.addCategory(category_name);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete")
    public String removeCategoryDb(@RequestParam("id") int id) {
        this.categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update")
    public String updateCategory(@RequestParam("categoryid") int id, @RequestParam("categoryname") String categoryname) {
        this.categoryService.updateCategory(id, categoryname);
        return "redirect:/admin/categories";
    }

    /* ------------- PRODUCT SECTION ------------- */
    @GetMapping("/products")
    public ModelAndView getproduct() {
        ModelAndView mView = new ModelAndView("products");
        List<Product> products = this.productService.getProducts();
        if (products.isEmpty()) mView.addObject("msg", "No products are available");
        else mView.addObject("products", products);
        return mView;
    }

    @GetMapping("/products/add")
    public ModelAndView addProduct() {
        ModelAndView mView = new ModelAndView("productsAdd");
        mView.addObject("categories", this.categoryService.getCategories());
        return mView;
    }

    @PostMapping("/products/add")
    public String addProduct(
            @RequestParam("name") String name,
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
    public ModelAndView updateproduct(@PathVariable("id") int id) {
        ModelAndView mView = new ModelAndView("productsUpdate");
        mView.addObject("product", this.productService.getProduct(id));
        mView.addObject("categories", this.categoryService.getCategories());
        return mView;
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("categoryid") int categoryId,
            @RequestParam("price") int price,
            @RequestParam("weight") int weight,
            @RequestParam("quantity") int quantity,
            @RequestParam("description") String description,
            @RequestParam("productImage") String productImage) {

        Category category = this.categoryService.getCategory(categoryId);
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(productImage);
        product.setWeight(weight);
        product.setQuantity(quantity);

        this.productService.updateProduct(id, product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete")
    public String removeProduct(@RequestParam("id") int id) {
        this.productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    /* ------------- CUSTOMER SECTION ------------- */
    @GetMapping("/customers")
    public ModelAndView getCustomerDetail() {
        ModelAndView mView = new ModelAndView("displayCustomers");
        mView.addObject("customers", this.userService.getUsers());
        return mView;
    }
}
