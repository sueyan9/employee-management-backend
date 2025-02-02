package com.xu.yan.employee_management.controller;

import com.xu.yan.employee_management.model.Dept;
import com.xu.yan.employee_management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/depts")
public class DeptController {

    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    // 查询所有部门
    @GetMapping
    public List<Dept> getAllDepts() {
        logger.info("Fetching all departments");
        return deptService.getAllDepts();
    }

    // 根据ID查询部门
    @GetMapping("/{id}")
    public ResponseEntity<Dept> getDeptById(@PathVariable int id) {
        logger.info("Fetching department with id: {}", id);
        Optional<Dept> dept = deptService.getDeptById(id);
        return dept.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Department with id {} not found", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }

    // 创建部门
    @PostMapping
    public ResponseEntity<Dept> createDept(@RequestBody Dept dept) {
        logger.info("Creating new department: {}", dept);
        Dept createdDept = deptService.createDept(dept);
        if (createdDept != null) {
            logger.debug("Department created successfully: {}", createdDept);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDept);
        } else {
            logger.error("Failed to create department: {}", dept);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 更新部门信息
    @PutMapping("/{id}")
    public ResponseEntity<Dept> updateDept(@PathVariable int id, @RequestBody Dept deptDetails) {
        logger.info("Updating department with id: {}", id);
        Dept updatedDept = deptService.updateDept(id, deptDetails);
        if (updatedDept != null) {
            logger.debug("Department updated successfully: {}", updatedDept);
            return ResponseEntity.ok(updatedDept);
        } else {
            logger.warn("Department with id {} not found for update", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 删除部门
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDept(@PathVariable int id) {
        logger.info("Deleting department with id: {}", id);
        boolean isDeleted = deptService.deleteDept(id);
        if (isDeleted) {
            logger.debug("Department with id {} deleted successfully", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            logger.warn("Department with id {} not found for deletion", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
