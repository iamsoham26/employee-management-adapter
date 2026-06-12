package com.labcorp.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.labcorp.employee.domain.Employee;
import com.labcorp.employee.domain.HourlyEmployee;
import com.labcorp.employee.domain.ManagerEmployee;
import com.labcorp.employee.domain.SalarizedEmployee;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class, taking care of it as a middle layer between controller and data objects
 */
@Service
@Slf4j
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    /**
     * Initialize 10 employee of each type
     * Hourly, Salarized and Manager
     */
    @PostConstruct
    public void init() {
        log.trace("init() - initializing employees");
        var employeeIdCounter = 1L;

        for (int i = 0; i < 10; i++) {
            employees.add(new HourlyEmployee(employeeIdCounter++));
            employees.add(new SalarizedEmployee(employeeIdCounter++));
            employees.add(new ManagerEmployee(employeeIdCounter++));
        }
    }

    public List<Employee> getAllEmployees() {
        log.trace("getAllEmployees() - retreiving all employees");
        return employees;
    }

    public Employee getEmployeeById(Long employeeId) {
        log.trace("getEmployeeById() - retrieving employee by employee id");
        return employees.stream()
            .filter(emp -> emp.getEmployeeId().equals(employeeId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeId));
    }

    public Employee work(Long id, int daysWorked) {
        log.trace("work() - update worked days");
        var employee = getEmployeeById(id);
        employee.work(daysWorked);
        return employee;
    }

    public Employee takeVacation(Long id, float daysUsed) {
        log.trace("takeVacation() - update vacation days");
        var employee = getEmployeeById(id);
        employee.takeVacation(daysUsed);
        return employee;
    }
    
}
