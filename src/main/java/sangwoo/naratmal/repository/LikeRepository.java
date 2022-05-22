package sangwoo.naratmal.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sangwoo.naratmal.model.domain.Like;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    public Long save(Like like) {
        em.persist(like);
        return like.getId();
    }

    public void delete(Like like) {
        em.remove(like);
    }

    public Like findById(Long id) {
        return em.find(Like.class, id);
    }

    public boolean existBySessionId(String sessionId) {
        return !em.createQuery("select l from Like l" +
                        " where l.sessionId=:sessionId", Like.class)
                .setParameter("sessionId", sessionId)
                .getResultList()
                .isEmpty();
    }

    public void removeBySessionId(String sessionId) {
        em.createQuery("delete from Like l where l.sessionId=:sessionId")
                .setParameter("sessionId", sessionId)
                .executeUpdate();
    }
}