package com.origins.pgd.domain.transactions;

import com.origins.pgd.domain.User;
import com.origins.pgd.domain.masters.*;
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
@Table(name = "trn_course_payments")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class CoursePayment implements Serializable {
    private static final long serialVersionUID =  1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Size(max = 255)
    @Column(name = "refference")
    private String refference;

    @Size(max = 255)
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "paymentDate")
    private Date paymentDate;

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
    @JoinColumn(name = "fk_payment")
    private CourseWisePayments coursePayment;

    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "fk_pay_mode")
    private PaymentMode paymentMode;

    @Override
    public String toString() {
        return "CoursePayments{" +
                "refference='" + refference + '\'' +
                ", student=" + student +
                '}';
    }
}
