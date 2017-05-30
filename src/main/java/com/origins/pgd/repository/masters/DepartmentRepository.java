package com.origins.pgd.repository.masters;

import com.origins.pgd.domain.masters.Department;
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
public interface DepartmentRepository extends JpaRepository<Department, BigInteger> {
    @Query(value = "SELECT department FROM Department department WHERE department.code=:code")
    Department findOneByCode(@Param("code") String code);

    @Query(value = "SELECT department FROM Department department WHERE department.name LIKE :name")
    Page<Department> findByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT department FROM Department department")
    List<Department> findAllActive();

}
