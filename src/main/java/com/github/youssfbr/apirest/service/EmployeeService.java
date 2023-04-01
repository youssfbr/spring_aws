package com.github.youssfbr.apirest.service;

import com.github.youssfbr.apirest.entity.Employee;
import com.github.youssfbr.apirest.repository.IEmployeeRepository;
import com.github.youssfbr.apirest.service.exceptions.ResourceNotFoundException;
import com.github.youssfbr.apirest.service.interfaces.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> listAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trabalhador com ID " + id + " não encontrado."));
    }

    @Override
    @Transactional
    public Employee newEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee replaceEmployee(Long id, Employee newEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setAddress(newEmployee.getAddress());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Trabalhador com ID " + id + " não encontrado."));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
