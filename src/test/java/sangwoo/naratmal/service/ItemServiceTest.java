package sangwoo.naratmal.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.model.dto.SelectItemResponse;
import sangwoo.naratmal.repository.ItemRepository;
import sangwoo.naratmal.repository.LikeRepository;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {ItemService.class})
class ItemServiceTest {

    @MockBean private ItemRepository itemRepository;
    @MockBean private LikeRepository likeRepository;

    @Autowired
    ItemService itemService;

    @Test
    void requestTest() {
        Mockito.when(itemRepository.findById(1L)).thenReturn(Item.createItem("test", "test"));
        Mockito.when(likeRepository.existBySessionIdAndItemId("testRequest", 1L)).thenReturn(false);
        Mockito.when(likeRepository.countLikeByItemId(1L)).thenReturn(2L);

        SelectItemResponse testRequest = itemService.requestItem("testRequest", 1L);

        Assertions.assertEquals("test", testRequest.getTitle());
        Assertions.assertEquals("test", testRequest.getDescription());
        Assertions.assertEquals(2L, testRequest.getCount());

        verify(itemRepository).findById(1L);
        verify(likeRepository).existBySessionIdAndItemId("testRequest", 1L);
        verify(likeRepository).countLikeByItemId(1L);
    }

}