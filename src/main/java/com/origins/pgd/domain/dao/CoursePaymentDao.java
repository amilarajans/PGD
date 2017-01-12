package com.origins.pgd.domain.dao;

import com.origins.pgd.domain.masters.PaymentType;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

/**
 * Created by Manoj Janaka on 10-01-2017.
 */
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

    public BigInteger getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(BigInteger paymentId) {
        this.paymentId = paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Map<Object, Object> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Map<Object, Object> paymentType) {
        this.paymentType = paymentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
