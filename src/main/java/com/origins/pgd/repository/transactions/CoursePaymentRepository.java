package com.origins.pgd.repository.transactions;

import com.origins.pgd.domain.dao.CoursePaymentDao;
import com.origins.pgd.domain.masters.PaymentMode;
import com.origins.pgd.domain.transactions.CoursePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Map;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface CoursePaymentRepository extends JpaRepository<CoursePayment, Integer> {

//    @Query(value = "SELECT P FROM CoursePayment P left join CourseWisePayments C ON C.id=P.coursePayment ")
//    Page<CoursePayment> findAll(Pageable pageable);


//    @Query(value = "SELECT P,C.id FROM CourseWisePayments C left join CoursePayment P  ON C.id=P.coursePayment ")
    @Query(value = "SELECT\n" +
            "mst_course_payments.id,\n" +
            "mst_payment_types.p_type,\n" +
            "mst_course_payments.cp_description,\n" +
            "mst_course_payments.cp_amount,\n" +
            "trn_course_payments.paymentDate,\n" +
            "trn_course_payments.refference\n" +
            "FROM\n" +
            "mst_course_payments\n" +
            "LEFT JOIN trn_course_payments ON trn_course_payments.fk_payment = mst_course_payments.id\n" +
            "LEFT JOIN mst_payment_types ON mst_course_payments.fk_payment_type = mst_payment_types.id",nativeQuery = true)
    List findAllA();
}
