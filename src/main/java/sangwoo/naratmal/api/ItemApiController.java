package sangwoo.naratmal.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.model.dto.ApiResult;
import sangwoo.naratmal.repository.ItemRepository;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemRepository itemRepository;

    @GetMapping("/api/item/{itemId}")
    public ApiResult selectItem(@PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId);
        return new ApiResult(new SelectItemResponse(item));
    }

    @PostMapping("/api/item")
    @Transactional
    public ApiResult createItem(@RequestBody CreateItemRequest request) {
        Item item = Item.createItem(request.getTitle(), request.getDescription());
        Long savedId = itemRepository.save(item);
        return new ApiResult(savedId);
    }

    @Data
    private static class SelectItemResponse {
        private String title;
        private String description;
        private Long likes;

        public SelectItemResponse(Item item) {
            title = item.getTitle();
            description = item.getDescription();
        }
    }

    @Data
    private static class CreateItemRequest {
        private String title;
        private String description;
    }
}
