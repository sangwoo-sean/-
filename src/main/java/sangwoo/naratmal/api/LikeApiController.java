package sangwoo.naratmal.api;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sangwoo.naratmal.model.domain.Like;
import sangwoo.naratmal.model.dto.ApiResult;
import sangwoo.naratmal.repository.LikeRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LikeApiController {
    private final LikeRepository likeRepository;

    @PostMapping("/api/item/{itemId}/like")
    @Transactional
    public ApiResult<Boolean> likeItem(HttpServletRequest request, @PathVariable Long itemId) {
        String sessionId = request.getSession().getId();
        boolean exist = likeRepository.existBySessionId(sessionId);
        if (exist) {
            likeRepository.removeBySessionId(sessionId);
        } else {
            Long save = likeRepository.save(Like.createLike(itemId, sessionId));
        }
        return new ApiResult<>(!exist);
    }
}
