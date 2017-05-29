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
@Table(name = "mst_student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class Student implements Serializable {
    private static final long serialVersionUID =  1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Size(max = 75)
    @Column(name = "s_id")
    private String code;

    @Size(max = 50)
    @Column(name = "s_title")
    private String title;

    @Size(max = 255)
    @Column(name = "s_full_name")
    private String fullName;

    @Size(max = 255)
    @Column(name = "s_p_address")
    private String permenantAddtess;

    @Size(max = 255)
    @Column(name = "s_c_address")
    private String contactAddress;

    @Size(max = 20)
    @Column(name = "s_phone")
    private String contactNumber;

    @Size(max = 20)
    @Column(name = "s_fax")
    private String faxNumber;

    @Size(max = 150)
    @Column(name = "s_email")
    private String email;

    @Column(name = "s_dob")
    private Date dateOfBirth;

    @Size(max = 10)
    @Column(name = "s_gender")
    private String gender;

    @Size(max = 150)
    @Column(name = "s_citizenship")
    private String citizenship;

    @Size(max = 20)
    @Column(name = "s_nic")
    private String nic;

    @Size(max = 150)
    @Column(name = "s_designation")
    private String designation;

    @Size(max = 255)
    @Column(name = "s_duties")
    private String duties;

    @Size(max = 255)
    @Column(name = "s_office_address")
    private String officeAddress;

    @Size(max = 20)
    @Column(name = "s_office_phone")
    private String officeContactNumber;

    @Size(max = 20)
    @Column(name = "s_office_fax")
    private String officeFaxNumber;

    @Size(max = 150)
    @Column(name = "s_office_email")
    private String officeEmail;

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

    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
