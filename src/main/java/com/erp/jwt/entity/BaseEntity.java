package com.erp.jwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false, name = "CREATED")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false, name = "UPDATED")
    private LocalDateTime updated;

    @Column(name = "DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted = false;

    @Column(name = "ACTIVE", columnDefinition = "boolean default true", nullable = false)
    private boolean active = true;

    @Column(name = "ENABLED", columnDefinition = "boolean default true", nullable = false)
    private boolean enabled = true;

    @Column(name = "CREATED_BY", updatable = false,nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_BY_NAME", updatable = false, nullable = false)
    private String createdByName = "NA";

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_BY_NAME")
    private String updatedByName;

    @Column(name = "REGION")
    private String region;



    @Version
    private Integer version;

    @Column(name = "TIMEZONE")
    private String timezone;
}
