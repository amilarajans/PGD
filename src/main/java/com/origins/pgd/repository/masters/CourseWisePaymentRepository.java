package com.origins.pgd.repository.masters;

import com.origins.pgd.domain.masters.CourseWisePayments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface CourseWisePaymentRepository extends JpaRepository<CourseWisePayments, Integer> {
//    @Query(value = "SELECT exam FROM CourseWisePayments c WHERE c.code=:code")
//    CourseWisePayments findOneByCode(@Param("code") String code);
//
    @Query(value = "SELECT c FROM CourseWisePayments c ")
    Page<CourseWisePayments> findAll(Pageable pageable);

}
