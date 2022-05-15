package sangwoo.naratmal.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sangwoo.naratmal.model.domain.NewRequest;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewRequestRepository {

    private final EntityManager em;

    public Long save(NewRequest newRequest) {
        em.persist(newRequest);
        return newRequest.getId();
    }

    public void delete(NewRequest newRequest) {
        em.remove(newRequest);
    }

    public NewRequest findById(Long id) {
        return em.find(NewRequest.class, id);
    }

    public List<NewRequest> findAll() {
        return em.createQuery("SELECT nr FROM NewRequest nr", NewRequest.class)
                .getResultList();
    }
}