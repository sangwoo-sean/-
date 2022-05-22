package sangwoo.naratmal.model.dto;

import lombok.Data;
import sangwoo.naratmal.model.domain.Item;

@Data
public class SelectItemResponse {
    private String title;
    private String description;
    private boolean liked;
    private long count;

    public SelectItemResponse(Item item, boolean liked, Long count) {
        title = item.getTitle();
        description = item.getDescription();
        this.liked = liked;
        this.count = count;
    }
}