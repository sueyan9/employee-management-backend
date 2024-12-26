package com.xu.yan.employee_management.service.impl;

import com.xu.yan.employee_management.model.Dept;
import com.xu.yan.employee_management.mapper.DeptMapper;
import com.xu.yan.employee_management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getAllDepts() {
        return deptMapper.getAllDepts();
    }

    @Override
    public Optional<Dept> getDeptById(int id) {
        return Optional.ofNullable(deptMapper.getDeptById(id)); // Wrap the result in Optional
    }

    @Override
    public Dept createDept(Dept dept) {
        deptMapper.createDept(dept);
        return dept;
    }

    @Override
    public Dept updateDept(int id, Dept deptDetails) {
        Optional<Dept> existingDept = Optional.ofNullable(deptMapper.getDeptById(id));
        if (existingDept.isPresent()) {
            deptDetails.setId(id);
            deptMapper.updateDept(deptDetails);
            return deptDetails;
        }
        return null; // You can throw an exception here if needed
    }

    @Override
    public boolean deleteDept(int id) {
        Optional<Dept> dept = Optional.ofNullable(deptMapper.getDeptById(id));
        if (dept.isPresent()) {
            deptMapper.deleteDept(id);
            return true;
        }
        return false; // You can throw an exception here if needed
    }
}