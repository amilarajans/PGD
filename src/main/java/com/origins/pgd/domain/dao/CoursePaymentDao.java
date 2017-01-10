package com.origins.pgd.domain.dao;

/**
 * Created by Manoj Janaka on 10-01-2017.
 */
public class CoursePaymentDao {

    private Double amount;

    public CoursePaymentDao(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
