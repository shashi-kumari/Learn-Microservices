package com.eazybytes.acounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    @Column( updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(updatable = false)
    private String createdBy = "System";

    @Column(insertable = false)
    private Date updatedAt;

    @Column(insertable = false)
    private String updatedBy;
}
