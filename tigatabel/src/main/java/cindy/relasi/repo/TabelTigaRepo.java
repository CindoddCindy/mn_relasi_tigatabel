package steamdom.master.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import steamdom.master.model.Level;

@Singleton
public class LevelRepository implements LevelRepositoryInf{
    @PersistenceContext
    private EntityManager entityManager;

    public LevelRepository(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Level> findAll(int page, int limit) {
        TypedQuery<Level> query = entityManager
            .createQuery("from Level", Level.class)
            .setFirstResult(page > 1 ? page * limit - limit : 0 )
            .setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Long save(@NotNull Level level) {
        try {
            entityManager.persist(level);
            return level.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Long size() {
        return entityManager.createQuery("select count(*) from Level", Long.class).getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public Level findById(@NotNull Long id) {
        return entityManager.find(Level.class, id);
    }

    @Transactional
    @Override
    public boolean update(@NotNull Long id, String name) {
        try {
            Level level = entityManager.find(Level.class, id);
            if(name != null) level.setName(name);
            level.setUpdated_at(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            Level level = entityManager.find(Level.class, id);
            entityManager.remove(level);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}