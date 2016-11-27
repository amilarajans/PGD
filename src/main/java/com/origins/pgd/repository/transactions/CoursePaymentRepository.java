package com.origins.pgd.repository.transactions;

import com.origins.pgd.domain.masters.PaymentMode;
import com.origins.pgd.domain.transactions.CoursePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface CoursePaymentRepository extends JpaRepository<CoursePayment, Integer> {

    @Query(value = "SELECT P FROM CoursePayment P")
    Page<CoursePayment> findAll(Pageable pageable);
}
