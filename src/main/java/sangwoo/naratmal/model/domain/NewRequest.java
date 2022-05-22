package sangwoo.naratmal.model.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class NewRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "new_request_id")
    private Long id;

    private String title;
    @Column(length = 5000)
    private String content;
    private LocalDateTime createdDate;

    public static NewRequest create(String title, String content) {
        NewRequest newRequest = new NewRequest();
        newRequest.title = title;
        newRequest.content = content;
        newRequest.createdDate = LocalDateTime.now();
        return newRequest;
    }
}
