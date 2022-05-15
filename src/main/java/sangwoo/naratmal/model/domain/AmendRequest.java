package sangwoo.naratmal.model.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class AmendRequest {
    @Id @GeneratedValue @Column(name = "amend_request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    private String content;
    private LocalDateTime createdDate;
}
