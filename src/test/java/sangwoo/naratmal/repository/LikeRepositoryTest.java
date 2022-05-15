package sangwoo.naratmal.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sangwoo.naratmal.model.domain.Item;
import sangwoo.naratmal.model.domain.Like;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LikeRepositoryTest {

    @Autowired
    LikeRepository likeRepository;

    @Test
    void 생성_테스트() {
        //given
        Item item = Item.createItem("test", "test");
        Like like = Like.createLike(item.getId(), "sessionId");
        Long savedId = likeRepository.save(like);

        //when
        Like foundLike = likeRepository.findById(savedId);

        //then
        assertEquals(savedId, foundLike.getId());
    }

    @Test
    void 삭제_테스트() {
        //given
        Item item = Item.createItem("test", "test");
        Like like = Like.createLike(item.getId(), "sessionId");
        Long savedId = likeRepository.save(like);

        //when
        Like foundLike = likeRepository.findById(savedId);
        likeRepository.delete(foundLike);

        //then
        Like deleted_like = likeRepository.findById(savedId);
        assertNull(deleted_like);
    }
}