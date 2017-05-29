package com.origins.pgd.domain.transactions;

import com.origins.pgd.domain.User;
import com.origins.pgd.domain.masters.Course;
import com.origins.pgd.domain.masters.Department;
import com.origins.pgd.domain.masters.Exam;
import com.origins.pgd.domain.masters.PaymentMode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Manoj Janaka on 14-11-2016.
 */
@Entity
@Table(name = "trn_exam_result")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class ExamResult implements Serializable {
    private static final long serialVersionUID =  1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Size(max = 75)
    @Column(name = "e_result")
    private String result;

    @Size(max = 255)
    @Column(name = "e_description")
    private String description;

    @Column(name = "is_active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "modified_by")
    private User modifiedBy;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "fk_department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "fk_course")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "fk_student")
    private User student;

    @ManyToOne
    @JoinColumn(name = "fk_pay_mode")
    private PaymentMode paymentMode;

    @ManyToOne
    @JoinColumn(name = "fk_exam")
    private Exam exam;

    @Override
    public String toString() {
        return "ExamResults{" +
                "result='" + result + '\'' +
                ", student=" + student +
                ", exam=" + exam +
                '}';
    }
}
