package sangwoo.naratmal.model.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "item_id")
    private Long id;

    private String title;
    @Column(length = 5000)
    private String description;
    @ColumnDefault("0")
    private Long visited;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<AmendRequest> amendRequests = new ArrayList<>();

    protected Item() {
    }

    public Item(Long id) {
        this.id = id;
    }

    public static Item createItem(String title, String description) {
        Item item = new Item();
        item.title = title;
        item.description = description;
        item.visited = 0L;
        item.createdDate = LocalDateTime.now();
        item.updatedDate = LocalDateTime.now();
        return item;
    }

    public void update(String description) {
        this.description = description;
    }

    public void visited() {
        this.visited += 1;
    }
}