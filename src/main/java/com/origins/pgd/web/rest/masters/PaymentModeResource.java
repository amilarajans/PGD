package com.origins.pgd.web.rest.masters;

import com.codahale.metrics.annotation.Timed;
import com.origins.pgd.domain.masters.PaymentMode;
import com.origins.pgd.repository.masters.PaymentModeRepository;
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
@RequestMapping(value = "api/v1/payment_mode")
public class PaymentModeResource {
    private final Logger log = LoggerFactory.getLogger(PaymentModeResource.class);

    private final PaymentModeRepository paymentModeRepository;
    private final Environment env;

    @Autowired
    public PaymentModeResource(PaymentModeRepository paymentModeRepository, Environment env) {
        this.paymentModeRepository = paymentModeRepository;
        this.env = env;
    }

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public Page<PaymentMode> getAllByPage(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        name = name == null ? "%" : name.replace("*", "%");
        return paymentModeRepository.findByName(name, new PageRequest(page - 1, Integer.parseInt(env.getProperty("result.page.size")), new Sort(Sort.Direction.ASC, "id")));
    }

    @RequestMapping(value = {"/allActivePaymentModes"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<PaymentMode> getAll() {
        return paymentModeRepository.findAllActive();
    }

    @RequestMapping(value = {"/allPaymentMode"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public List<PaymentMode> getAllActive() {
        return paymentModeRepository.findAll();
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void save(@RequestBody PaymentMode department) {
        if (paymentModeRepository.findOneByCode(department.getType()) == null) {
            department.setCreatedBy(SecurityUtils.getCurrentLogin());
            department.setCreatedDate(new Date());
            paymentModeRepository.save(department);
        } else {
            throw new ConflictException("PaymentMode already exist with code " + department.getType());
        }
    }

    @RequestMapping(value = {"/count"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @Timed
    public long getCount() {
        return paymentModeRepository.count();
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST}, produces = {"application/json"})
    @Timed
    public void update(@RequestBody PaymentMode department) {
        department.setModifiedBy(SecurityUtils.getCurrentLogin());
        department.setModifiedDate(new Date());
        paymentModeRepository.save(department);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"})
    @Timed
    public void delete(@PathVariable String id) {
        paymentModeRepository.delete(Integer.valueOf(id));
    }

}