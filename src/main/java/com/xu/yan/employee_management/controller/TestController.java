package com.xu.yan.employee_management.controller;

import com.xu.yan.employee_management.mapper.DeptMapper;
import com.xu.yan.employee_management.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private DeptMapper deptMapper;

    @GetMapping("/test-db-connection")
    public List<Dept> testDbConnection() {
        return deptMapper.getAllDepts();  // 调用数据库查询方法
    }
}