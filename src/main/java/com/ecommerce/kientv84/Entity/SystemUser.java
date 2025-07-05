package com.ecommerce.kientv84.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "System_User_Code", unique = true, length = 50)
    private String systemUserCode;

    @Column(name = "System_User_Name", nullable = false, length = 100)
    private String systemUserName;

    @Column(name = "System_User_BirthDay")
    @Temporal(TemporalType.DATE)
    private Date systemUserBirthDay;

    @Column(name = "System_User_Gender", length = 20)
    private String systemUserGender;

    @Column(name = "System_User_PhoneNumber")
    private String systemUserPhoneNumber;

    @Column(name = "System_User_Address", length = 255)
    private String systemUserAddress;

    @Column(name = "System_User_Email",  unique = true, length = 100)
    private String systemUserEmail;

    @ManyToOne(fetch = FetchType.LAZY) //Nhiều người dùng (SystemUser) có thể thuộc 1 vai trò (SystemRole).
    @JsonBackReference //đánh dấu đây là phần "bị bỏ qua"
    //FetchType.LAZY JPA sẽ không tự động truy vấn vai trò mỗi khi load SystemUser, mà chỉ lấy khi gọi getSystemRole()
    @JoinColumn(name = "System_User_Roles") //Ánh xạ System_User_Roles của bảng System_User là foreign key đến primary key của System_Role.
    private SystemRole systemRole;

    @Column(name = "System_User_Password", nullable = false, length = 255)
    private String systemUserPassword;

    @Column(name = "Status", length = 50)
    private String status;

    @Lob
    @Column(name = "System_User_Avatar")
    private byte[] systemUserAvatar;

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

    @OneToOne(mappedBy = "systemUser", cascade = CascadeType.ALL) // cascade = CascadeType.ALL
    @JsonBackReference
    // Khi thao tác user dưới db vd xóa thì employee cũng đc xóa
    //cấu hình cascade = CascadeType.ALL, nên khi save user ==> JPA sẽ tự động gọi persist() cho employee.
    private SystemEmployee systemEmployee;
}
