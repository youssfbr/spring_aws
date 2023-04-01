package com.github.youssfbr.apirest.repository;

import com.github.youssfbr.apirest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}
