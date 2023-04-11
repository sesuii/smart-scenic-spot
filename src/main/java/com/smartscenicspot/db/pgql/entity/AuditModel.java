package com.smartscenicspot.db.pgql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/17 20:18
 **/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"gmtCreate", "gmtModified"},
        allowGetters = true
)
@Getter
@Setter
@ToString
public abstract class AuditModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmt_create", updatable = false)
    @Nullable
    private Date gmtCreate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmt_modified")
    @Nullable
    private Date gmtModified;
}
