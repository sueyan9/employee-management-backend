package com.xu.yan.employee_management.controller;

import com.xu.yan.employee_management.model.Dept;
import com.xu.yan.employee_management.response.ApiResponse;
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
@RequestMapping("/depts")
public class DeptController {

    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    @GetMapping
    public ApiResponse<List<Dept>> getAllDepts() {
        logger.info("Fetching all departments");
        List<Dept> deptList = deptService.getAllDepts();
        return ApiResponse.success(deptList);
    }

    @GetMapping("/{id}")
    public ApiResponse<Dept> getDeptById(@PathVariable int id) {
        logger.info("Fetching department with id: {}", id);
        Optional<Dept> dept = deptService.getDeptById(id);
        return dept.map(ApiResponse::success)
                .orElseGet(() -> {
                    logger.warn("Department with id {} not found", id);
                    return ApiResponse.error(404,"Department not found");
                });
    }

    @PostMapping
    public ApiResponse<Dept> createDept(@RequestBody Dept dept) {
        logger.info("Creating new department: {}", dept);
        Dept createdDept = deptService.createDept(dept);
        if (createdDept != null) {
            logger.debug("Department created successfully: {}", createdDept);
            return ApiResponse.of(201,"Department created successfully", createdDept);
        } else {
            logger.error("Failed to create department: {}", dept);
            return ApiResponse.error(500,"Failed to creat department ");
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Dept> updateDept(@PathVariable int id, @RequestBody Dept deptDetails) {
        logger.info("Updating department with id: {}", id);
        Dept updatedDept = deptService.updateDept(id, deptDetails);
        if (updatedDept != null) {
            logger.debug("Department updated successfully: {}", updatedDept);
            return ApiResponse.success(updatedDept);
        } else {
            logger.warn("Department with id {} not found for update", id);
            return ApiResponse.error(404,"Department not found");
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDept(@PathVariable int id) {
        logger.info("Deleting department with id: {}", id);
        boolean isDeleted = deptService.deleteDept(id);
        if (isDeleted) {
            logger.debug("Department with id {} deleted successfully", id);
            return ApiResponse.success();
        } else {
            logger.warn("Department with id {} not found for deletion", id);
            return ApiResponse.error(404,"Department not found");
        }
    }
}
