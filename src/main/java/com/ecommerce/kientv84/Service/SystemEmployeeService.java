package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Entity.SystemEmployee;
import com.ecommerce.kientv84.Respone.ResponeResult;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SystemEmployeeService {
    ResponeResult getAllEmployee();

    ResponeResult getById(Long id);

    ResponeResult createEmployee(SystemEmployee employeeData);

    ResponeResult updateEmployee(Long id, SystemEmployee updateData);

    ResponeResult delteEmployee(List<Long> ids);
}
