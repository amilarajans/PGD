package com.origins.pgd.domain;

/**
 * Created by Manoj Janaka on 14-11-2016.
 */
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "AUTHORITY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    @NotNull
    @Size(max = 50)
    @Id
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Authority authority = (Authority) o;
        if (this.name != null ? !this.name.equals(authority.name) : authority.name != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.name != null ? this.name.hashCode() : 0;
    }

    public String toString() {
        return String.format("Authority{name='%s'}", this.name);
    }
}