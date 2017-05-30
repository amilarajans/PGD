package com.origins.pgd.repository.masters;

import com.origins.pgd.domain.masters.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;


/**
 * Created by Manoj Janaka on 14-11-2016.
 */
public interface CourseRepository extends JpaRepository<Course, BigInteger> {
    @Query(value = "SELECT course FROM Course course WHERE course.code=:code")
    Course findOneByCode(@Param("code") String code);

    @Query(value = "SELECT course FROM Course course WHERE course.name LIKE :name")
    Page<Course> findCourseByName(@Param("name") String name, Pageable pageable);

}
