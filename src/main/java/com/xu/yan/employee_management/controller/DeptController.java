package com.xu.yan.employee_management.controller;

import com.xu.yan.employee_management.model.Dept;
import com.xu.yan.employee_management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 查询所有部门
    @GetMapping
    public List<Dept> getAllDepts() {
        return deptService.getAllDepts();
    }

    // 根据ID查询部门
    @GetMapping("/{id}")
    public ResponseEntity<Dept> getDeptById(@PathVariable int id) {
        Optional<Dept> dept = deptService.getDeptById(id);
        return dept.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 创建部门
    @PostMapping
    public ResponseEntity<Dept> createDept(@RequestBody Dept dept) {
        Dept createdDept = deptService.createDept(dept);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDept);
    }

    // 更新部门信息
    @PutMapping("/{id}")
    public ResponseEntity<Dept> updateDept(@PathVariable int id, @RequestBody Dept deptDetails) {
        Dept updatedDept = deptService.updateDept(id, deptDetails);
        return updatedDept != null ? ResponseEntity.ok(updatedDept)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // 删除部门
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDept(@PathVariable int id) {
        return deptService.deleteDept(id) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
