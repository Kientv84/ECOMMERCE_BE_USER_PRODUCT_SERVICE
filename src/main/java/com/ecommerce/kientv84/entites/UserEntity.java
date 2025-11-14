package com.ecommerce.kientv84.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_entity")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "user_code", unique = true, length = 50)
    private String userCode;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "user_birthday")
    @Temporal(TemporalType.DATE)
    private Date userBirthday;

    @Column(name = "user_gender", length = 20)
    private String userGender;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name = "user_address", length = 255)
    private String userAddress;

    @Column(name = "user_email", unique = true, length = 100)
    private String userEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id") // foreign key đến bảng System_Role
    private RoleEntity role;

    @JsonIgnore
    @Column(name = "user_password", nullable = false, length = 255)
    private String userPassword;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "user_avatar")
    private String userAvatar;

    // ====== Metadata ======
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

//    // Khi thao tác user dưới DB (vd: xóa) thì employee cũng bị ảnh hưởng theo cascade
//    @OneToOne(mappedBy = "systemUser", cascade = CascadeType.ALL)
//    @JsonBackReference
//    private Employee employee;
}