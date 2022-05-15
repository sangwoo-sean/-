package sangwoo.naratmal.model.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "likes")
public class Like {

    @Id @GeneratedValue @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    private String sessionId;
    private LocalDateTime createdDate;
}
