package com.akamai.socialmedia.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    protected  Long id;
    @CreationTimestamp
    protected LocalDateTime dataCreated;
    @UpdateTimestamp
    protected  LocalDateTime lastModified;
}
