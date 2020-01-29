package steamdom.master.controller;

import steamdom.master.model.Level;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;

import com.google.gson.Gson;
import steamdom.master.repository.*;

import java.util.HashMap;
import java.util.List;


@Validated
@Controller("/level")
public class LevelController {

    private LevelRepository repository;

    public LevelController(LevelRepository repository) {
        this.repository = repository;
    }

    @Get(processes = MediaType.APPLICATION_JSON)
    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit){
        final HashMap<String, Object> data = new HashMap<>();
        try {
            final List<Level> levels = repository.findAll(page, limit);
            data.put("page", Math.ceil(repository.size()/limit));
            data.put("status", "ok");
            data.put("message", "Data Level");
            data.put("data", levels);
            return (new Gson().toJson(data));  
        } catch (Exception e) {
            data.put("status", "error");
            data.put("message", e.getMessage());
            return (new Gson().toJson(data));
        }
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final Level level){
        final HashMap<String, Object> data = new HashMap<>();
        Long result = repository.save(level);
        if (result != null) {
            data.put("status", "ok");
            data.put("id", result);
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

    @Get("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable @Nullable final Long id){
        return (new Gson().toJson(repository.findById(id)));
    }

    @Put("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathVariable @Nullable final Long id, @Body final Level level){
        final HashMap<String, Object> data = new HashMap<>();
        if (repository.update(id, level.getName())) {
            data.put("status", "ok");
        }else{
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }

    @Delete("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable @Nullable final Long id){
        final HashMap<String, Object> data = new HashMap<>();
        if (repository.destroy(id)) {
            data.put("status", "ok");
        }else{
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }
}