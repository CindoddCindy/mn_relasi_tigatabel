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

import steamdom.master.model.Semester;

@Singleton
public class SemesterRepository implements SemesterRepositoryInf{
    @PersistenceContext
    private EntityManager entityManager;

    public SemesterRepository(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Semester> findAll(int page, int limit) {
        TypedQuery<Semester> query = entityManager
            .createQuery("from Semester", Semester.class)
            .setFirstResult(page > 1 ? page * limit - limit : 0 )
            .setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Long save(@NotNull Semester semester) {
        try {
            entityManager.persist(semester);
            return semester.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Long size() {
        return entityManager.createQuery("select count(*) from Semester", Long.class).getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public Semester findById(@NotNull Long id) {
        return entityManager.find(Semester.class, id);
    }

    @Transactional
    @Override
    public boolean update(@NotNull Long id, String code) {
        try {
            Semester semester = entityManager.find(Semester.class, id);
            if(code != null) semester.setCode(code);
            semester.setUpdated_at(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            Semester semester = entityManager.find(Semester.class, id);
            entityManager.remove(semester);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}