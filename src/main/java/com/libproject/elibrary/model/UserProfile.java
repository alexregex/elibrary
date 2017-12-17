package com.libproject.elibrary.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", unique = true, nullable = false)
    private String type = UserProfileType.USER.getUserProfileType();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, that.id)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .append(type)
                .toHashCode();
    }
}
