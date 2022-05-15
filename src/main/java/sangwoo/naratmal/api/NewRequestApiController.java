package sangwoo.naratmal.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sangwoo.naratmal.model.domain.NewRequest;
import sangwoo.naratmal.model.dto.ApiResult;
import sangwoo.naratmal.repository.NewRequestRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@RestController
@RequiredArgsConstructor
public class NewRequestApiController {

    private final NewRequestRepository newRequestRepository;

    @PutMapping("/api/new-request")
    @Transactional
    public ApiResult<Boolean> saveNewRequest(@RequestBody @Valid CreateNewRequestRequest request) {
        NewRequest newRequest = NewRequest.create(request.getTitle(), request.getContent());
        newRequestRepository.save(newRequest);
        return new ApiResult<>(true);
    }

    @Data
    private static class CreateNewRequestRequest {
        @NotEmpty(message = "제목을 입력해주세요.")
        @Size(max = 20, message = "제목은 최대 20자 까지 입력 가능합니다.")
        private String title;

        @NotEmpty(message = "설명을 입력해주세요.")
        @Size(max = 2000, message = "설명은 최대 2000자 까지 입력 가능합니다.")
        private String content;
    }
}