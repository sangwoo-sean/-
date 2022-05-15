package sangwoo.naratmal.model.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue @Column(name = "item_id")
    private Long id;

    private String title;
    private String description;
    private Long visited;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<AmendRequest> amendRequests = new ArrayList<>();

    public static Item createItem(String title, String description) {
        Item item = new Item();
        item.title = title;
        item.description = description;
        item.visited = 0L;
        item.createdDate = LocalDateTime.now();
        item.updatedDate = LocalDateTime.now();
        return item;
    }
}