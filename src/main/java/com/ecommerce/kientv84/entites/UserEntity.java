package com.ecommerce.kientv84.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    // Nhiều người dùng (SystemUser) có thể thuộc 1 vai trò (SystemRole).
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference // đánh dấu đây là phần "bị bỏ qua"
    // FetchType.LAZY: chỉ truy vấn vai trò khi gọi getSystemRole()
    @JoinColumn(name = "role_id") // foreign key đến bảng System_Role
    private RoleEntity role;

    @Column(name = "user_password", nullable = false, length = 255)
    private String userPassword;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

//    // Khi thao tác user dưới DB (vd: xóa) thì employee cũng bị ảnh hưởng theo cascade
//    @OneToOne(mappedBy = "systemUser", cascade = CascadeType.ALL)
//    @JsonBackReference
//    private Employee employee;
}