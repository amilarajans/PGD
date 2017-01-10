package com.origins.pgd.repository.transactions;

import com.origins.pgd.domain.dao.CoursePaymentDao;
import com.origins.pgd.domain.masters.PaymentMode;
import com.origins.pgd.domain.transactions.CoursePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface CoursePaymentRepository extends JpaRepository<CoursePayment, Integer> {

//    @Query(value = "SELECT P FROM CoursePayment P left join CourseWisePayments C ON C.id=P.coursePayment ")
//    Page<CoursePayment> findAll(Pageable pageable);


//    @Query(value = "SELECT P,C.id FROM CourseWisePayments C left join CoursePayment P  ON C.id=P.coursePayment ")
    @Query(value = "SELECT new com.origins.pgd.domain.dao.CoursePaymentDao(C.amount) FROM CourseWisePayments C left join CoursePayment P  ON C.id=P.coursePayment ")
    Page<CoursePaymentDao> findAllA(Pageable pageable);
}
