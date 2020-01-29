package steamdom.master.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import steamdom.master.model.Course;

/**
 * CourseRepository
 */
@Singleton
public class CourseRepository implements CourseRepositoryInf{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public CourseRepository(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll(int page, int limit) {
        TypedQuery<Course> query = entityManager.createQuery("from Course", Course.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0).setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Long save(@NotNull Course course) {
        try {
            entityManager.persist(course);
            return course.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Long size() {
        return entityManager.createQuery("select count(*) from Course", Long.class).getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public Course findById(@NotNull Long id) {
        return entityManager.find(Course.class, id);
    }

    @Transactional
    @Override
    public boolean update(@NotNull Long id, String title, String content) {
        try {
            Course course = entityManager.find(Course.class, id);
            if (title != null) course.setTitle(title);
            if (content != null) course.setContent(content);
            course.setUpdated_at(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            Course course = entityManager.find(Course.class, id);
            entityManager.remove(course);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}