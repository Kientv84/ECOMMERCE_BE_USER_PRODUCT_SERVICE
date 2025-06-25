package com.ecommerce.kientv84.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "System_User")
public class SystemUser {
    @Id
    @Column(name = "System_User_Code", length = 50)
    private String systemUserCode;

    @Column(name = "System_User_Name", nullable = false, length = 100)
    private String systemUserName;

    @Column(name = "System_User_BirthDay")
    @Temporal(TemporalType.DATE)
    private Date systemUserBirthDay;

    @Column(name = "System_User_Gender", length = 20)
    private String systemUserGender;

    @Column(name = "System_User_PhoneNumber")
    private Long systemUserPhoneNumber;

    @Column(name = "System_User_Address", length = 255)
    private String systemUserAddress;

    @Column(name = "System_User_Email", length = 100)
    private String systemUserEmail;

    @Column(name = "System_User_Roles")
    private Long systemUserRoles;

    @Column(name = "System_User_Password", nullable = false, length = 255)
    private String systemUserPassword;

    @Column(name = "System_User_Permission", length = 100)
    private String systemUserPermission;

    @Column(name = "Status", length = 50)
    private String status;

    @Lob
    @Column(name = "System_User_Avatar")
    private byte[] systemUserAvatar;

    @Column(name = "System_User_ChangePassword")
    private Boolean systemUserChangePassword = false;

    @Column(name = "CreateBy", length = 100)
    private String createBy;

    @Column(name = "CreateDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UpdateBy", length = 100)
    private String updateBy;

    @Column(name = "UpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
