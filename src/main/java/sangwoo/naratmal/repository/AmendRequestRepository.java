package sangwoo.naratmal.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sangwoo.naratmal.model.domain.AmendRequest;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AmendRequestRepository {

    private final EntityManager em;

    public Long save(AmendRequest amendRequest) {
        em.persist(amendRequest);
        return amendRequest.getId();
    }

    public void delete(AmendRequest amendRequest) {
        em.remove(amendRequest);
    }

    public AmendRequest findById(Long id) {
        return em.find(AmendRequest.class, id);
    }

    public List<AmendRequest> findAll() {
        return em.createQuery("SELECT ar FROM AmendRequest ar", AmendRequest.class)
                .getResultList();
    }
}