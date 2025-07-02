package com.ecommerce.kientv84.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "System_Role")
public class SystemRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "System_Role_Name", nullable = false)
    private String systemRoleName;

    @Column(name= "System_Role_Description")
    private String systemRoleDescription;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedBy", length = 500)
    private String createBy;

    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP) // Đánh dấu anotation temporal ánh xạ dữ liệu kiểu date (dd/mm/yy)
    private Date createDate;

    @Column(name = "UpdatedBy", length = 500)
    private String updateBy;

    @Column(name = "UpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
