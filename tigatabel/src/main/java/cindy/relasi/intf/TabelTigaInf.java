package steamdom.master.repository;

import steamdom.master.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface LevelRepositoryInf {
    List<Level> findAll(int page, int limit);
    Long save(@NotNull Level level);
    Long size();
    Level findById(@NotNull Long id);
    boolean update(@NotNull Long id, String name);
    boolean destroy(@NotNull Long id);
} 