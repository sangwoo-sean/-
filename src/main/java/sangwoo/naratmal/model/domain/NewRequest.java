package sangwoo.naratmal.model.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class NewRequest {

    @Id @GeneratedValue @Column(name = "new_request_id")
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdDate;
}
