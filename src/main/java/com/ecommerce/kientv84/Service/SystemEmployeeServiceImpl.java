package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Commons.CKConstant.CkResults;
import com.ecommerce.kientv84.Entity.SystemEmployee;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Responsitory.SystemEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemEmployeeServiceImpl implements SystemEmployeeService{

    @Autowired private SystemEmployeeRepository systemEmployeeRepository;

    public SystemEmployeeServiceImpl(SystemEmployeeRepository systemEmployeeRepository) {
        this.systemEmployeeRepository = systemEmployeeRepository;
    }

    @Override
    public ResponeResult getAllEmployee() {
        try {
            List<SystemEmployee> data = systemEmployeeRepository.findAll();

            if (!data.isEmpty()) {
                return new ResponeResult(CkResults.SUCCESS, "get all sucessfully!!", data);
            } else {
                return new ResponeResult(CkResults.SUCCESS, "Not found any employee!", data);
            }
        } catch (Exception e) {
            return new ResponeResult(CkResults.ERROR, "Have err: " + e.getMessage().toString());
        }
    }

    @Override
    public ResponeResult getById(Long id) {
        return null;
    }

    @Override
    public ResponeResult createEmployee(SystemEmployee employeeData) {
        return null;
    }

    @Override
    public ResponeResult updateEmployee(Long id, SystemEmployee updateData) {
        return null;
    }

    @Override
    public ResponeResult delteEmployee(List<Long> ids) {
        return null;
    }
}

