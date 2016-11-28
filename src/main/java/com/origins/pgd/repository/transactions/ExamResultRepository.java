package com.origins.pgd.repository.transactions;

import com.origins.pgd.domain.transactions.ExamResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface ExamResultRepository extends JpaRepository<ExamResult, Integer> {

    @Query(value = "SELECT P FROM ExamResult P")
    Page<ExamResult> findAll(Pageable pageable);
}
