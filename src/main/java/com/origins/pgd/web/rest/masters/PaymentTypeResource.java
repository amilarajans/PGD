package com.origins.pgd.web.rest.masters;

import com.codahale.metrics.annotation.Timed;
import com.origins.pgd.domain.masters.PaymentType;
import com.origins.pgd.repository.masters.PaymentTypeRepository;
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
@RequestMapping(value = "api/v1/payment_type")
public class PaymentTypeResource {
    private final Logger log = LoggerFactory.getLogger(PaymentTypeResource.class);

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private Environment env;

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public Page<PaymentType> getAllByPage(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        name = name == null ? "%" : name.replace("*", "%");
        return paymentTypeRepository.findByName(name, new PageRequest(page - 1, Integer.parseInt(env.getProperty("result.page.size")), new Sort(Sort.Direction.ASC, "id")));
    }

    @RequestMapping(value = {"/allActivePaymentTypes"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<PaymentType> getAll() {
        return paymentTypeRepository.findAllActive();
    }

    @RequestMapping(value = {"/allPaymentType"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<PaymentType> getAllActive() {
        return paymentTypeRepository.findAll();
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void save(@RequestBody PaymentType department) {
        if (paymentTypeRepository.findOneByCode(department.getType()) == null) {
            department.setCreatedBy(SecurityUtils.getCurrentLogin());
            department.setCreatedDate(new Date());
            paymentTypeRepository.save(department);
        } else {
            throw new ConflictException("PaymentType already exist with code " + department.getType());
        }
    }

    @RequestMapping(value = {"/count"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public long getCount() {
        return paymentTypeRepository.count();
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void update(@RequestBody PaymentType department) {
        department.setModifiedBy(SecurityUtils.getCurrentLogin());
        department.setModifiedDate(new Date());
        paymentTypeRepository.save(department);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"})
    @Timed
    public void delete(@PathVariable String id) {
        paymentTypeRepository.delete(Integer.valueOf(id));
    }

}