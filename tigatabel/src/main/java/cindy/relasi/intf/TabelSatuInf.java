package steamdom.master.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import steamdom.master.model.Course;

public interface CourseRepositoryInf {
    List<Course> findAll(int page, int limit);
    Long save(@NotNull Course course);
    Long size();
    Course findById(@NotNull Long id);
    boolean update(@NotNull Long id, String title, String content);
    boolean destroy(@NotNull Long id);
}