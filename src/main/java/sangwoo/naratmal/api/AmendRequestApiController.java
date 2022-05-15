package sangwoo.naratmal.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sangwoo.naratmal.model.domain.AmendRequest;
import sangwoo.naratmal.model.dto.ApiResult;
import sangwoo.naratmal.repository.AmendRequestRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@RestController
@RequiredArgsConstructor
public class AmendRequestApiController {

    private final AmendRequestRepository amendRequestRepository;

    @PutMapping("/api/amend-request")
    @Transactional
    public ApiResult<Boolean> saveAmendRequest(@RequestBody @Valid CreateAmendRequestRequest request) {
        AmendRequest amendRequest = AmendRequest.create(request.getItemId(), request.getContent());
        amendRequestRepository.save(amendRequest);
        return new ApiResult<>(true);
    }

    @Data
    private static class CreateAmendRequestRequest {
        private Long itemId;

        @NotEmpty(message = "설명을 입력해주세요.")
        @Size(max = 2000, message = "설명은 최대 2000자 까지 입력 가능합니다.")
        private String content;
    }
}