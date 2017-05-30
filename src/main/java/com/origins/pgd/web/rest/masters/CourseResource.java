package com.origins.pgd.web.rest.masters;

import com.codahale.metrics.annotation.Timed;
import com.origins.pgd.domain.masters.Course;
import com.origins.pgd.repository.masters.CourseRepository;
import com.origins.pgd.util.SecurityUtils;
import com.origins.pgd.web.rest.exception.ConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Manoj Janaka on 14-11-2016.
 */
@RestController
@RequestMapping(value = "api/v1/course")
public class CourseResource {
    private final Logger log = LoggerFactory.getLogger(CourseResource.class);
    private final CourseRepository CourseRepository;
    private final Environment env;

    @Autowired
    public CourseResource(com.origins.pgd.repository.masters.CourseRepository courseRepository, Environment env) {
        CourseRepository = courseRepository;
        this.env = env;
    }

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public Page<Course> getAllByPage(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        name = name == null ? "%" : name.replace("*", "%");
        return CourseRepository.findCourseByName(name, new PageRequest(page - 1, Integer.parseInt(env.getProperty("result.page.size")), new Sort(Sort.Direction.ASC, "id")));
    }

    @RequestMapping(value = {"/allCourse"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<Course> getAll() {
        return CourseRepository.findAll();
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void save(@RequestBody Course course) {
        if (CourseRepository.findOneByCode(course.getCode()) == null) {
            course.setCreatedBy(SecurityUtils.getCurrentLogin());
            course.setCreatedDate(new Date());
            CourseRepository.save(course);
        } else {
            throw new ConflictException("Course already exist with code " + course.getCode());
        }
    }

    @RequestMapping(value = {"/count"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public long getCount() {
        return CourseRepository.count();
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void update(@RequestBody Course course) {
        course.setModifiedBy(SecurityUtils.getCurrentLogin());
        course.setModifiedDate(new Date());
        CourseRepository.save(course);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"})
    @Timed
    public void delete(@PathVariable String id) {
        CourseRepository.delete(new BigInteger(id));
    }

}