package com.origins.pgd.repository.masters;

import com.origins.pgd.domain.masters.PaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
    @Query(value = "SELECT P FROM PaymentType P WHERE P.type=:code")
    PaymentType findOneByCode(@Param("code") String code);

    @Query(value = "SELECT P FROM PaymentType P")
    Page<PaymentType> findByName(Pageable pageable);

    @Query(value = "SELECT P FROM PaymentType P")
    List<PaymentType> findAllActive();

}
