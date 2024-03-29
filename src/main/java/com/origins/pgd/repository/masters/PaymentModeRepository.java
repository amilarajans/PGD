package com.origins.pgd.repository.masters;

import com.origins.pgd.domain.masters.PaymentMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface PaymentModeRepository extends JpaRepository<PaymentMode, BigInteger> {
    @Query(value = "SELECT P FROM PaymentMode P WHERE P.type=:code")
    PaymentMode findOneByCode(@Param("code") String code);

    @Query(value = "SELECT P FROM PaymentMode P WHERE P.type=:name")
    Page<PaymentMode> findByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT P FROM PaymentMode P")
    List<PaymentMode> findAllActive();

}
