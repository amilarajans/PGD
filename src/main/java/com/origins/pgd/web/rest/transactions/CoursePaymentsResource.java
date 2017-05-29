package com.origins.pgd.web.rest.transactions;

import com.codahale.metrics.annotation.Timed;
import com.origins.pgd.domain.dao.CoursePaymentDao;
import com.origins.pgd.domain.masters.Student;
import com.origins.pgd.domain.transactions.CoursePayment;
import com.origins.pgd.repository.masters.CourseWisePaymentRepository;
import com.origins.pgd.repository.masters.StudentRepository;
import com.origins.pgd.repository.transactions.CoursePaymentRepository;
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
import java.util.Map;

/**
 * Created by Manoj Janaka on 20-11-2016.
 */
@RestController
@RequestMapping(value = "api/v1/coursePayments")
public class CoursePaymentsResource {
    private final Logger log = LoggerFactory.getLogger(CoursePaymentsResource.class);

    private CoursePaymentRepository coursePaymentsResource;

    private CourseWisePaymentRepository courseWisePaymentRepository;

    private Environment env;

    @Autowired
    public CoursePaymentsResource(CoursePaymentRepository coursePaymentsResource, CourseWisePaymentRepository courseWisePaymentRepository, Environment env) {
        this.coursePaymentsResource = coursePaymentsResource;
        this.courseWisePaymentRepository = courseWisePaymentRepository;
        this.env = env;
    }

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public  List getAllByPage(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        name = name == null ? "%" : name.replace("*", "%");
        return coursePaymentsResource.findAllA();
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void save(@RequestBody CoursePayment coursePayment) {
            coursePayment.setCreatedBy(SecurityUtils.getCurrentLogin());
            coursePayment.setCreatedDate(new Date());
            coursePaymentsResource.save(coursePayment);
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void update(@RequestBody CoursePayment coursePayment) {
        coursePayment.setCoursePayment(courseWisePaymentRepository.findOne(coursePayment.getCoursePayment().getId()));
        coursePayment.setModifiedBy(SecurityUtils.getCurrentLogin());
        coursePayment.setModifiedDate(new Date());
        coursePaymentsResource.save(coursePayment);
    }

}