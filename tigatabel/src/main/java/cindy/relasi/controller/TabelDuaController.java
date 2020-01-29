package steamdom.master.controller;

import steamdom.master.model.Semester;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;
import io.micronaut.http.annotation.QueryValue;

import com.google.gson.Gson;
import steamdom.master.repository.*;

import java.util.HashMap;
import java.util.List;

@Validated
@Controller("/semester")
public class SemesterController {

    private SemesterRepository repository;

    public SemesterController(SemesterRepository repository) {
        this.repository = repository;
    }

    @Get("/")
    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            List<Semester> semester = repository.findAll(page, limit);
            data.put("status", "ok");
            data.put("message", "data semester");
            data.put("data", semester);
            return new Gson().toJson(data);
        } catch (Exception e) {
            data.put("status", "error");
            data.put("mesage", e.getMessage());
            return new Gson().toJson(data);
        }
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final Semester semester) {
        final HashMap<String, Object> data = new HashMap<>();
        Long result = repository.save(semester);
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
    public String show(@PathVariable @Nullable final Long id) {
        return (new Gson().toJson(repository.findById(id)));
    }

    @Put("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathVariable @Nullable final Long id, @Body final Semester semester) {
        final HashMap<String, Object> data = new HashMap<>();
        if (repository.update(id, semester.getCode())) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }

    @Delete("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable @Nullable final Long id) {
        final HashMap<String, Object> data = new HashMap<>();
        if (repository.destroy(id)) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }
}