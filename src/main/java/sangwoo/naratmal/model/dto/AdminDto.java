package sangwoo.naratmal.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminDto {

    @NotEmpty(message = "아이디를 입력하세요.")
    private String id;
    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;
}
