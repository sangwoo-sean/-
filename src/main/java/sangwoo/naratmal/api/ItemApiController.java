package sangwoo.naratmal.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.model.dto.ApiResult;
import sangwoo.naratmal.model.dto.SelectItemResponse;
import sangwoo.naratmal.repository.ItemRepository;
import sangwoo.naratmal.service.ItemService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("/api/item/{itemId}")
    public ApiResult selectItem(HttpServletRequest request, @PathVariable Long itemId) {
        String sessionId = request.getSession().getId();
        SelectItemResponse response = itemService.requestItem(sessionId, itemId);
        return new ApiResult(response);
    }

    @PostMapping("/api/item")
    @Transactional
    public ApiResult createItem(@RequestBody CreateItemRequest request) {
        Item item = Item.createItem(request.getTitle(), request.getDescription());
        Long savedId = itemRepository.save(item);
        return new ApiResult(savedId);
    }

    @PatchMapping("/api/item/{itemId}")
    @Transactional
    public ApiResult updateItem(@PathVariable Long itemId, @RequestBody CreateItemRequest request) {
        Item item = itemRepository.findById(itemId);
        item.update(request.getDescription());
        return new ApiResult(item.getId());
    }

    @DeleteMapping("/api/item/{itemId}")
    @Transactional
    public ApiResult deleteItem(@PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId);
        itemRepository.remove(item);
        return new ApiResult(true);
    }

    @Data
    private static class CreateItemRequest {
        private String title;
        private String description;
    }
}
