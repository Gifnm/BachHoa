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

    @GetMapping("/admin/employee")
    public String employee() {
        return "pages/admin/employee/index";
    }

    @GetMapping("/admin/bill-history")
    public String bills() {
        return "pages/admin/bill/index";
    }

    @GetMapping("/admin/payment-history")
    public String payments() {
        return "pages/admin/pay/index";
    }

    @GetMapping("/admin/discount")
    public String discount() {
        return "pages/admin/discount/index";
    }

    @GetMapping("/admin/inventory-history")
    public String inventoryHistory() {
        return "pages/admin/inventory_history/index";
    }

}
