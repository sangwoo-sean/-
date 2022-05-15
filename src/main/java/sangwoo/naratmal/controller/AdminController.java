package sangwoo.naratmal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/items")
    public String adminHome() {
        return "admin/data-table";
    }

    @GetMapping("/new-requests")
    public String newRequestList() {
        return "admin/data-table";
    }

    @GetMapping("/amend-requests")
    public String amendRequestList() {
        return "admin/data-table";
    }

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login";
    }
}
