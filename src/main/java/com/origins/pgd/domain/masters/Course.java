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
@Table(name = "mst_course")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Size(max = 15)
    @Column(name = "batch")
    private String batch;

    @Size(max = 75)
    @Column(name = "c_code")
    private String code;

    @Size(max = 150)
    @Column(name = "c_name")
    private String name;

    @Size(max = 255)
    @Column(name = "c_description")
    private String description;

    @Size(max = 10)
    @Column(name = "c_year")
    private String year;

    @Column(name = "c_from_date")
    private Date fromDate;

    @Column(name = "c_to_date")
    private Date toDate;

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
    @JoinColumn(name = "fk_supervisor")
    private User supervisor;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
