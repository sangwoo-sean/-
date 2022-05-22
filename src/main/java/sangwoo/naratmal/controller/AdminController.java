package sangwoo.naratmal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sangwoo.naratmal.model.domain.AmendRequest;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.model.domain.NewRequest;
import sangwoo.naratmal.repository.AmendRequestRepository;
import sangwoo.naratmal.repository.NewRequestRepository;
import sangwoo.naratmal.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ItemService itemService;
    private final NewRequestRepository newRequestRepository;
    private final AmendRequestRepository amendRequestRepository;

    @GetMapping("/items")
    public String adminHome(Model model) {
        List<Item> itemList = itemService.findAll();
        model.addAttribute("itemList", itemList);
        return "admin/item-list";
    }

    @GetMapping("/new-requests")
    public String newRequestList(Model model) {
        List<NewRequest> requestList = newRequestRepository.findAll();
        model.addAttribute("requestList", requestList);
        return "admin/new-request-list";
    }

    @GetMapping("/amend-requests")
    public String amendRequestList(Model model) {
        List<AmendRequest> requestList = amendRequestRepository.findAll();
        model.addAttribute("requestList", requestList);
        return "admin/amend-request-list";
    }

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login";
    }
}
