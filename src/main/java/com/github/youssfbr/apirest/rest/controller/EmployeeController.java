package com.github.youssfbr.apirest.rest.controller;

import com.github.youssfbr.apirest.entity.Employee;
import com.github.youssfbr.apirest.service.interfaces.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping
    public List<Employee> listAllEmployees() {
        return employeeService.listAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee newEmployee(@RequestBody Employee employee) {
        return employeeService.newEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee replaceEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.replaceEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void rdeleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}
