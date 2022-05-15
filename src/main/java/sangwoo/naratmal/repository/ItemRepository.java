package sangwoo.naratmal.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sangwoo.naratmal.model.domain.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public List<Item> findAll() {
        return em.createQuery("SELECT i FROM Item i ORDER BY i.updatedDate DESC", Item.class)
                .getResultList();
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }
}
