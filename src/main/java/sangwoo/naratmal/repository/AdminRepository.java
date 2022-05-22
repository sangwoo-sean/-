package sangwoo.naratmal.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sangwoo.naratmal.model.domain.Admin;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final EntityManager em;

    public Admin findById(String id) {
        return em.find(Admin.class, id);
    }
}