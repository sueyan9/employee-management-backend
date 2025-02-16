package com.xu.yan.employee_management.controller;

import com.xu.yan.employee_management.model.Emp;
import com.xu.yan.employee_management.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emps")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public List<Emp> getAllEmps() {
        return empService.getAllEmps();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emp> getEmpById(@PathVariable int id) {
        Optional<Emp> emp = empService.getEmpById(id);
        System.out.println("Returned Employee: " + emp);
        return emp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Emp> createEmp(@RequestBody Emp emp) {
        Emp createdEmp = empService.createEmp(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emp> updateEmp(@PathVariable int id, @RequestBody Emp empDetails) {
        Emp updatedEmp = empService.updateEmp(id, empDetails);
        return updatedEmp != null ? ResponseEntity.ok(updatedEmp)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable int id) {
        return empService.deleteEmp(id) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
