package steamdom.master.controller;

import steamdom.master.model.Course;
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


import com.google.gson.Gson;
import steamdom.master.repository.*;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

@Validated
@Controller("/course")
public class CourseController {

    private CourseRepositoryInf repository;

    public CourseController(CourseRepositoryInf repository) {
        this.repository = repository;
    }

    @Get(processes = MediaType.APPLICATION_JSON)
    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            final List<Course> courses = repository.findAll(page, limit);
            data.put("page", Math.ceil(repository.size()/limit));
            data.put("status", "ok");
            data.put("message", "Data Course");
            data.put("data", courses);
            return (new Gson().toJson(data));
        } catch (Exception e) {
            data.put("status", "error");
            data.put("message", e.getMessage());
            return (new Gson().toJson(data));
        }
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final Course course){
        final HashMap<String, Object> data = new HashMap<>();
        Long result = repository.save(course);
        if (result != null) {
            data.put("status", "ok");
            data.put("id", result);
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable @Nullable final Long id) {
        return (new Gson().toJson(repository.findById(id)));
    }

    @Put(consumes = MediaType.APPLICATION_JSON)
    public String update(@Body final Course course) {
        final HashMap<String, Object> data = new HashMap<>();
        if (repository.update(course.getId(), course.getTitle(), course.getContent())) {
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