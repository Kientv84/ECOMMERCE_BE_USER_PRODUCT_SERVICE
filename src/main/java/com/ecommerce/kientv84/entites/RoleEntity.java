package com.ecommerce.kientv84.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_entity")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name= "role_description")
    private String roleDescription;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by", length = 500)
    private String createBy;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP) // Đánh dấu anotation temporal ánh xạ dữ liệu kiểu date (dd/mm/yy)
    private Date createDate;

    @Column(name = "update_by", length = 500)
    private String updateBy;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)  //ascade = CascadeType.ALL cho phép thao tác trên SystemRole sẽ tự lan sang SystemUser
    @JsonManagedReference //là phần "được giữ lại".
    //, dùng để xử lý quan hệ hai chiều (bi-directional) trong JSON serialization bằng thư viện Jackson (dùng trong Spring Boot).

    // @JsonIgnore: sd để thư viện serialize JSON mặc định trong Spring Boot) không serialize trường users trong SystemRole.
    private List<UserEntity> users;
}

// 7/5 have bugs:
//Lý do bạn gặp tình trạng JSON bị lặp vô hạn (infinite recursion) trong Postman là do
// quan hệ hai chiều (bidirectional relationship) giữa các entity JPA của bạn — cụ thể là giữa SystemUser và SystemRole.
//@OneToMany(mappedBy = "systemRole")
//private List<SystemUser> users;
//Vì vậy khi bạn trả về một SystemUser, nó sẽ serialize toàn bộ SystemRole kèm theo,
// nhưng SystemRole lại có danh sách users, trong đó lại có SystemUser, rồi lại SystemRole, rồi lại users, cứ thế lặp vô hạn.

//@JsonBackendReference/ @JsonManagedReference dùng để tránh việc lập Json vô hạn (Infinite recursion) (Lập lại đệ quy)
// ==> Chỉ ảnh hưởng khi serialize thành JSON, không ảnh hưởng đến việc truy cập bằng code Java.