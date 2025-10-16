package com.ecommerce.kientv84.entites;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_entity")
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    // ====== Thông tin chung ======
    @Column(name ="product_name", nullable = false, length = 255)
    private String productName;

//    @Column(name ="product_code", nullable = false, length = 255)

    @Column(name ="product_code", length = 255)
    private String productCode;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String brand; // Gymshark, Nike, Adidas...

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryEntity category; // Men / Women / Accessories


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategoryEntity subCategory; // T-shirt / Shorts / Sports Bra...

    @Column(length = 100)
    private String collection; // Apex / Vital / Legacy...

    @Column(name ="base_price")
    private BigDecimal basePrice; // Giá gốc + float và double là kiểu số dấu phẩy động (floating-point) vd 0.30000000000000004 != 0.3

    @Column(name ="discount_percent")
    private Float discountPercent; // % giảm giá

    private String origin; // Xuất xứ (UK, Vietnam...)

    private String material; // Polyester, Cotton, Nylon...

    @Column(name ="fit_type")
    private String fitType; // Slim fit, Regular fit...

    @Column(name ="care_instruction")
    private String careInstruction; // "Machine wash cold", ...

    private String status; // ACTIVE, INACTIVE, OUT_OF_STOCK

    @Column(name ="thumbnail_url")
    private String thumbnailUrl; // Ảnh chính

    @Column(name ="rating_average")
    private Double ratingAverage; // Trung bình đánh giá

    @Column(name ="rating_count")
    private Integer ratingCount; // Số lượng review

    // ====== Metadata ======
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="create_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="update_date")
    private Date updatedDate;

    @Column(name ="created_by")
    private String createdBy;

    @Column(name ="updated_by")
    private String updatedBy;

//    // ====== Quan hệ ======
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductVariantEntity> variants;
//
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductImageEntity> images;
}