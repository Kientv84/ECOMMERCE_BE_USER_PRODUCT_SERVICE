package com.ecommerce.kientv84.entites;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sub_category_entity")
@EntityListeners(AuditingEntityListener.class)
public class SubCategoryEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "sub_category_name", nullable = false, length = 255)
    private String subCategoryName; // Ví dụ: Men, Women, Accessories

    @Column(name = "sub_category_code", unique = true, length = 100)
    private String subCategoryCode; // Ví dụ: MEN, WOMEN, ACCESSORIES

    @Column(columnDefinition = "TEXT")
    private String description; // Mô tả ngắn

    @Column(name = "thumbnail_url")
    private String thumbnailUrl; // Ảnh đại diện danh mục

    @Column(name = "status", length = 50)
    private String status; // ACTIVE / INACTIVE


    // ===== Quan hệ n-1 với category =====
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    // ====== Quan hệ với Product ======
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ProductEntity> products;

    // ====== Metadata ======
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}

