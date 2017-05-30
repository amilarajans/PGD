package com.origins.pgd.web.rest.masters;

import com.codahale.metrics.annotation.Timed;
import com.origins.pgd.domain.masters.Department;
import com.origins.pgd.repository.masters.DepartmentRepository;
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
@RequestMapping(value = "api/v1/department")
public class DepartmentResource {
    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    private final DepartmentRepository departmentRepository;
    private final Environment env;

    @Autowired
    public DepartmentResource(DepartmentRepository departmentRepository, Environment env) {
        this.departmentRepository = departmentRepository;
        this.env = env;
    }

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public Page<Department> getAllByPage(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        name = name == null ? "%" : name.replace("*", "%");
        return departmentRepository.findByName(name, new PageRequest(page - 1, Integer.parseInt(env.getProperty("result.page.size")), new Sort(Sort.Direction.ASC, "id")));
    }

    @RequestMapping(value = {"/allActiveDepartments"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<Department> getAll() {
        return departmentRepository.findAllActive();
    }

    @RequestMapping(value = {"/allDepartment"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<Department> getAllActive() {
        return departmentRepository.findAll();
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void save(@RequestBody Department department) {
        if (departmentRepository.findOneByCode(department.getCode()) == null) {
            department.setCreatedBy(SecurityUtils.getCurrentLogin());
            department.setCreatedDate(new Date());
            departmentRepository.save(department);
        } else {
            throw new ConflictException("Department already exist with code " + department.getCode());
        }
    }

    @RequestMapping(value = {"/count"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public long getCount() {
        return departmentRepository.count();
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void update(@RequestBody Department department) {
        department.setModifiedBy(SecurityUtils.getCurrentLogin());
        department.setModifiedDate(new Date());
        departmentRepository.save(department);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"})
    @Timed
    public void delete(@PathVariable String id) {
        departmentRepository.delete(new BigInteger(id));
    }

}