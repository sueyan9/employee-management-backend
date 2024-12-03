package com.itheima.tlias_web_management.service.impl;

import com.itheima.tlias_web_management.mapper.EmpMapper;
import com.itheima.tlias_web_management.pojo.Emp;
import com.itheima.tlias_web_management.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> getAllEmployees() {
        return empMapper.findAll();
    }

    @Override
    public Emp getEmployeeById(Integer id) {
        return empMapper.findById(id);
    }

    @Override
    public void addEmployee(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public void updateEmployee(Emp emp) {
        empMapper.update(emp);
    }

    @Override
    public void deleteEmployee(Integer id) {
        empMapper.delete(id);
    }
}
