package com.origins.pgd.web.rest.masters;

import com.codahale.metrics.annotation.Timed;
import com.origins.pgd.domain.masters.CourseWisePayments;
import com.origins.pgd.repository.masters.CourseWisePaymentRepository;
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
@RequestMapping(value = "api/v1/coursePayment")
public class CourseWisePaymentResource {
    private final Logger log = LoggerFactory.getLogger(CourseWisePaymentResource.class);

    @Autowired
    private CourseWisePaymentRepository courseWisePaymentRepository;
    @Autowired
    private Environment env;

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public Page<CourseWisePayments> getAllByPage(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        name = name == null ? "%" : name.replace("*", "%");
        return courseWisePaymentRepository.findAll(new PageRequest(page - 1, Integer.parseInt(env.getProperty("result.page.size")), new Sort(Sort.Direction.ASC, "id")));
    }

    @RequestMapping(value = {"/allCourseWisePayment"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<CourseWisePayments> getAll() {
        return courseWisePaymentRepository.findAll();
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void save(@RequestBody CourseWisePayments exam) {
//        if (courseWisePaymentRepository.findOneByCode(exam.getCode()) == null) {
            exam.setCreatedBy(SecurityUtils.getCurrentLogin());
            exam.setCreatedDate(new Date());
            courseWisePaymentRepository.save(exam);
//        } else {
//            throw new ConflictException("CourseWisePayments already exist with code " + exam.getCode());
//        }
    }

    @RequestMapping(value = {"/count"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public long getCount() {
        return courseWisePaymentRepository.count();
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void update(@RequestBody CourseWisePayments exam) {
        exam.setModifiedBy(SecurityUtils.getCurrentLogin());
        exam.setModifiedDate(new Date());
        courseWisePaymentRepository.save(exam);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"})
    @Timed
    public void delete(@PathVariable String id) {
        courseWisePaymentRepository.delete(new BigInteger(id));
    }

}