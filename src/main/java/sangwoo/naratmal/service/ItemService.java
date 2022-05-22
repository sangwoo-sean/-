package sangwoo.naratmal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.model.dto.SelectItemResponse;
import sangwoo.naratmal.repository.ItemRepository;
import sangwoo.naratmal.repository.LikeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final LikeRepository likeRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public SelectItemResponse requestItem(String sessionId, Long itemId) {
        Item item = itemRepository.findById(itemId); // item 조회
        boolean liked = likeRepository.existBySessionIdAndItemId(sessionId, itemId); // 해당 세션이 해당 아이템을 liked 했는지 조회
        Long count = likeRepository.countLikeByItemId(itemId); // 좋아요 개수
        item.visited(); // 조회수 +1
        return new SelectItemResponse(item, liked, count);
    }
}