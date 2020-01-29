package steamdom.master.repository;

import steamdom.master.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SemesterRepositoryInf {
    List<Semester> findAll(int page, int limit);
    Long save(@NotNull Semester semester);
    Long size();
    Semester findById(@NotNull Long id);
    boolean update(@NotNull Long id, String code);
    boolean destroy(@NotNull Long id);
} 