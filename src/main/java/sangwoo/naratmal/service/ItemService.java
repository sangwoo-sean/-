package sangwoo.naratmal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}