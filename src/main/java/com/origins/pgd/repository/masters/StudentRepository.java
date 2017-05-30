package com.origins.pgd.repository.masters;

import com.origins.pgd.domain.masters.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;


/**
 * Created by Manoj Janaka on 20-11-2016.
 */
public interface StudentRepository extends JpaRepository<Student, BigInteger> {
    @Query(value = "SELECT s FROM Student s WHERE s.code=:code")
    Student findOneByCode(@Param("code") String code);

    @Query(value = "SELECT s FROM Student s WHERE s.fullName LIKE :name")
    Page<Student> findByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT P FROM Student P")
    List<Student> findAllActive();

}
