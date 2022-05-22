package sangwoo.naratmal.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.model.dto.ApiResult;
import sangwoo.naratmal.repository.ItemRepository;
import sangwoo.naratmal.repository.LikeRepository;
import sangwoo.naratmal.service.ItemService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemRepository itemRepository;
    private final LikeRepository likeRepository;

    @GetMapping("/api/item/{itemId}")
    @Transactional
    public ApiResult selectItem(HttpServletRequest request, @PathVariable Long itemId) {
        String sessionId = request.getSession().getId();
        Item item = itemRepository.findById(itemId); // item 조회
        item.visited();
        boolean liked = likeRepository.existBySessionIdAndItemId(sessionId, itemId); // 해당 세션이 해당 아이템을 liked 했는지 조회
        Long count = likeRepository.countLikeByItemId(itemId); // 좋아요 개수
        return new ApiResult(new SelectItemResponse(item, liked, count));
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
    private static class SelectItemResponse {
        private String title;
        private String description;
        private boolean liked;
        private long count;

        public SelectItemResponse(Item item, boolean liked, Long count) {
            title = item.getTitle();
            description = item.getDescription();
            this.liked = liked;
            this.count = count;
        }
    }

    @Data
    private static class CreateItemRequest {
        private String title;
        private String description;
    }
}
