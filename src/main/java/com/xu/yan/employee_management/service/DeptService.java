package com.xu.yan.employee_management.service;

import com.xu.yan.employee_management.model.Dept;

import java.util.List;
import java.util.Optional;

public interface DeptService {
    List<Dept> getAllDepts();
    Optional<Dept> getDeptById(int id);
    Dept createDept(Dept dept);
    Dept updateDept(int id, Dept deptDetails);
    boolean deleteDept(int id);
}
