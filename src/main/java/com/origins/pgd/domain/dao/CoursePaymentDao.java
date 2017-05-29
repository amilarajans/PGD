package com.origins.pgd.domain.dao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

/**
 * Created by Manoj Janaka on 10-01-2017.
 */
@Getter
@Setter
public class CoursePaymentDao {

    private BigInteger paymentId;
    private Double amount;
    private Map<Object,Object> paymentType;
    private String description;
    private Date paymentDate;
    private String student;

    public CoursePaymentDao(BigInteger paymentId, Double amount,String description,Date paymentDate) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.description = description;
        this.paymentDate = paymentDate;
    }
}
