package com.github.youssfbr.apirest.service.interfaces;

import com.github.youssfbr.apirest.entity.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> listAllEmployees();
    Employee findEmployeeById(Long id);
    Employee newEmployee(Employee employee);
    Employee replaceEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);

}
