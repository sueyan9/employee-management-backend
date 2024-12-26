package com.xu.yan.employee_management.service;

import com.xu.yan.employee_management.model.Emp;

import java.util.List;
import java.util.Optional;

public interface EmpService {
    List<Emp> getAllEmps();
    Optional<Emp> getEmpById(int id);
    Emp createEmp(Emp emp);
    Emp updateEmp(int id, Emp empDetails);
    boolean deleteEmp(int id);
}
