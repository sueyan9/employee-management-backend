package com.xu.yan.employee_management.service.impl;

import com.xu.yan.employee_management.model.Emp;
import com.xu.yan.employee_management.mapper.EmpMapper;
import com.xu.yan.employee_management.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> getAllEmps() {
        return empMapper.findAll();
    }

    @Override
    public Optional<Emp> getEmpById(int id) {
        Emp emp = empMapper.findById(id);
        return Optional.ofNullable(emp);
    }

    @Override
    public Emp createEmp(Emp emp) {
        empMapper.insert(emp); // Change 'save' to 'insert'
        return emp;
    }

    @Override
    public Emp updateEmp(int id, Emp empDetails) {
        Optional<Emp> existingEmp = Optional.ofNullable(empMapper.findById(id));
        if (existingEmp.isPresent()) {
            empDetails.setId(id);
            empMapper.update(empDetails);
            return empDetails;
        }
        return null; // You can throw an exception here if needed
    }

    @Override
    public boolean deleteEmp(int id) {
        Optional<Emp> emp = Optional.ofNullable(empMapper.findById(id));
        if (emp.isPresent()) {
            empMapper.delete(id);
            return true;
        }
        return false; // You can throw an exception here if needed
    }
}