package com.origins.pgd.domain.masters;

import com.origins.pgd.domain.User;
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
@Table(name = "mst_course_payments")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class CourseWisePayments implements Serializable {
    private static final long serialVersionUID =  1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "cp_amount")
    private Double amount;

    @Size(max = 255)
    @Column(name = "cp_description")
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
    @JoinColumn(name = "fk_payment_type")
    private PaymentType paymentType;

}
