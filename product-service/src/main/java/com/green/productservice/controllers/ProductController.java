package com.green.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products/public")
    public String publicProducts() {
        return "Public Product Information";
    }

    @GetMapping("/products/user")
    public String userProducts() {
        return "User Product Information";
    }

    @GetMapping("/products/admin")
    public String adminProducts() {
        return "Admin Product Information";
    }
}

