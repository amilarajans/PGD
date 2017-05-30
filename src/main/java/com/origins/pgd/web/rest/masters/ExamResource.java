package com.origins.pgd.web.rest.masters;

import com.codahale.metrics.annotation.Timed;
import com.origins.pgd.domain.masters.Exam;
import com.origins.pgd.repository.masters.ExamRepository;
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

import java.util.Date;
import java.util.List;

/**
 * Created by Manoj Janaka on 14-11-2016.
 */
@RestController
@RequestMapping(value = "api/v1/exam")
public class ExamResource {
    private final Logger log = LoggerFactory.getLogger(ExamResource.class);

    private final ExamRepository ExamRepository;
    private final Environment env;

    @Autowired
    public ExamResource(com.origins.pgd.repository.masters.ExamRepository examRepository, Environment env) {
        ExamRepository = examRepository;
        this.env = env;
    }

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public Page<Exam> getAllByPage(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        name = name == null ? "%" : name.replace("*", "%");
        return ExamRepository.findByName(name, new PageRequest(page - 1, Integer.parseInt(env.getProperty("result.page.size")), new Sort(Sort.Direction.ASC, "id")));
    }

    @RequestMapping(value = {"/allExam"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<Exam> getAll() {
        return ExamRepository.findAll();
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void save(@RequestBody Exam exam) {
        if (ExamRepository.findOneByCode(exam.getCode()) == null) {
            exam.setCreatedBy(SecurityUtils.getCurrentLogin());
            exam.setCreatedDate(new Date());
            ExamRepository.save(exam);
        } else {
            throw new ConflictException("Exam already exist with code " + exam.getCode());
        }
    }

    @RequestMapping(value = {"/count"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public long getCount() {
        return ExamRepository.count();
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void update(@RequestBody Exam exam) {
        exam.setModifiedBy(SecurityUtils.getCurrentLogin());
        exam.setModifiedDate(new Date());
        ExamRepository.save(exam);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"})
    @Timed
    public void delete(@PathVariable String id) {
        ExamRepository.delete(Integer.valueOf(id));
    }

}