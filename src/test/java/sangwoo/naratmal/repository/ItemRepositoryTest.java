package sangwoo.naratmal.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sangwoo.naratmal.model.domain.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void 아이템_생성() {
        //given
        Item item = Item.createItem("test", "test");
        Long saved_id = itemRepository.save(item);

        //when
        Item found_item = itemRepository.findById(saved_id);

        //then
        assertEquals(saved_id, found_item.getId()); // id
        assertEquals("test", found_item.getTitle()); // title
        assertEquals("test", found_item.getDescription()); // description
    }

    @Test
    void 아이템_목록_조회() {
        //given
        Item item1 = Item.createItem("test1", "test1");
        itemRepository.save(item1);
        Item item2 = Item.createItem("test2", "test2");
        itemRepository.save(item2);

        //when
        List<Item> itemList = itemRepository.findAll();

        //then
        assertEquals(2, itemList.size());
    }
}