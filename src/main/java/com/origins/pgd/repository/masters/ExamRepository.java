package com.origins.pgd.repository.masters;

import com.origins.pgd.domain.masters.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    @Query(value = "SELECT exam FROM Exam exam WHERE exam.code=:code")
    Exam findOneByCode(@Param("code") String code);

    @Query(value = "SELECT exam FROM Exam exam WHERE exam.name LIKE :name")
    Page<Exam> findByName(@Param("name") String name, Pageable pageable);

}
