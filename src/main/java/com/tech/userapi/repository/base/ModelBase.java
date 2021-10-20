package com.tech.userapi.repository.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class ModelBase {
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "is_active")
    private Boolean isActive;
}
