package com.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "pages/admin/dashboard/index";
    }

    @GetMapping("/admin/product")
    public String product() {
        return "pages/admin/product/index";
    }

    @GetMapping("/admin/import-product")
    public String importInventory() {
        return "pages/admin/import_product/index";
    }

}
