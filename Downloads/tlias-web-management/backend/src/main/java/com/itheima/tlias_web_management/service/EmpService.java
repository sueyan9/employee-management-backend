package com.itheima.tlias_web_management.service;

import com.itheima.tlias_web_management.pojo.Emp;

import java.util.List;
/**员工管理*/
public interface EmpService {
    List<Emp> getAllEmployees();
    Emp getEmployeeById(Integer id);
    void addEmployee(Emp emp);
    void updateEmployee(Emp emp);
    void deleteEmployee(Integer id);
}
