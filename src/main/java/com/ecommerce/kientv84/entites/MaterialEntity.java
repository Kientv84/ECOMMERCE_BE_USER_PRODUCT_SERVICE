package com.ecommerce.kientv84.entites;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "material_entity")
@EntityListeners(AuditingEntityListener.class)
public class MaterialEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "material_name", nullable = false, length = 255)
    private String materialName;

    @Column(name = "material_code", unique = true, length = 100)
    private String materialCode;


    @Column(columnDefinition = "TEXT")
    private String description; // Mô tả ngắn

    @Column(name = "status", length = 50)
    private String status; // ACTIVE / INACTIVE

    // ====== Quan hệ với Product ======
    @OneToOne(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = false)
    private ProductEntity product;

    // ====== Metadata ======
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_date")
    private Date updatedDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
