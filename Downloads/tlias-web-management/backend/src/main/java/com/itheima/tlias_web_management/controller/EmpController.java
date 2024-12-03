package com.itheima.tlias_web_management.controller;
import com.itheima.tlias_web_management.pojo.Emp;
import com.itheima.tlias_web_management.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
//查询所有用户
    @GetMapping
    public List<Emp> getAllEmployees() {
        return empService.getAllEmployees();
    }
//根据ID查询用户
    @GetMapping("/{id}")
    public Emp getEmployeeById(@PathVariable Integer id) {
        return empService.getEmployeeById(id);
    }
//添加新用户
    @PostMapping
    public String addEmployee(@RequestBody Emp emp) {
        empService.addEmployee(emp);
        return "Employee added successfully!";
    }
//更新
    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Integer id, @RequestBody Emp emp) {
        emp.setId(id);
        empService.updateEmployee(emp);
        return "Employee updated successfully!";
    }
//删除用户
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        empService.deleteEmployee(id);
        return "Employee deleted successfully!";
    }
}
