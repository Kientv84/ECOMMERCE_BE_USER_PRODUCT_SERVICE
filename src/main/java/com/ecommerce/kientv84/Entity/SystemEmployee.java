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
@Table(name = "System_Employee")
public class SystemEmployee {
    @Id
    private Long id;  // Id này chính là FK tới SystemUser

    @OneToOne
    @MapsId // dùng cùng giá trị với primary key (id)
    @JoinColumn(name = "Id")
    @JsonBackReference
    private SystemUser systemUser;

    @Column(name = "System_Employee_Display")
    private String systemEmployeeDisplay;

    @Column(name = "System_Employee_Salary")
    private Long systemEmployeeSalary;

    @Column(name = "Status")
    private String status;

    @Column(name= "CreatedBy")
    private String createBy;

    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP) // Định dạng date theo kiểu dd/mm/yy
    private Date createDate;

    @Column(name = "UpdateBy")
    private String updateBy;

    @Column(name = "UpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatDate;
}
