package sangwoo.naratmal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangwoo.naratmal.model.domain.Admin;
import sangwoo.naratmal.model.domain.AmendRequest;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.model.domain.NewRequest;
import sangwoo.naratmal.model.dto.AdminDto;
import sangwoo.naratmal.repository.AdminRepository;
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
    private final AdminRepository adminRepository;

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

    @PostMapping("/login")
    public String adminLogin(@Validated AdminDto adminDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/login";
        }

        Admin found_admin = adminRepository.findById(adminDto.getId());
        if (found_admin == null) {
            redirectAttributes.addFlashAttribute("error", "유효하지 않은 계정정보입니다.");
            return "redirect:/admin/login";
        }
        if (!found_admin.getPassword().equals(adminDto.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 틀렸습니다.");
            return "redirect:/admin/login";
        }
        return "redirect:/admin/items";
    }
}
