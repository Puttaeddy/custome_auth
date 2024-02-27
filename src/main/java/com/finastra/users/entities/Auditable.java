package com.finastra.users.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateAt;



}