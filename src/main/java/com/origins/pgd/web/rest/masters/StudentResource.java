package com.origins.pgd.web.rest.masters;

import com.codahale.metrics.annotation.Timed;
import com.origins.pgd.domain.masters.Student;
import com.origins.pgd.repository.masters.StudentRepository;
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
 * Created by Manoj Janaka on 20-11-2016.
 */
@RestController
@RequestMapping(value = "api/v1/student")
public class StudentResource {
    private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private final StudentRepository StudentRepository;
    private final Environment env;

    @Autowired
    public StudentResource(com.origins.pgd.repository.masters.StudentRepository studentRepository, Environment env) {
        StudentRepository = studentRepository;
        this.env = env;
    }

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public Page<Student> getAllByPage(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        name = name == null ? "%" : name.replace("*", "%");
        return StudentRepository.findByName(name, new PageRequest(page - 1, Integer.parseInt(env.getProperty("result.page.size")), new Sort(Sort.Direction.ASC, "id")));
    }

    @RequestMapping(value = {"/allStudent"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<Student> getAll() {
        return StudentRepository.findAllActive();
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void save(@RequestBody Student student) {
        if (StudentRepository.findOneByCode(student.getCode()) == null) {
            student.setCreatedBy(SecurityUtils.getCurrentLogin());
            student.setCreatedDate(new Date());
            StudentRepository.save(student);
        } else {
            throw new ConflictException("Student already exist with code " + student.getCode());
        }
    }

    @RequestMapping(value = {"/count"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public long getCount() {
        return StudentRepository.count();
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void update(@RequestBody Student student) {
        student.setModifiedBy(SecurityUtils.getCurrentLogin());
        student.setModifiedDate(new Date());
        StudentRepository.save(student);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"})
    @Timed
    public void delete(@PathVariable String id) {
        StudentRepository.delete(new BigInteger(id));
    }

}