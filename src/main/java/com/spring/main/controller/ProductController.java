package com.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "pages/admin/dashboard";
    }

    @GetMapping("/admin/product")
    public String product() {
        return "pages/admin/product/index";
    }
}
