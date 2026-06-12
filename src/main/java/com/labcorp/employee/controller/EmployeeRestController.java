package com.labcorp.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.labcorp.employee.domain.Employee;
import com.labcorp.employee.service.EmployeeService;

/**
 * Employee rest controller to return list of employees 
 * and to perform worked and vacation days
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {
    
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Get list of employees
     * @return List of employees
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * Updates number of worked days based on employee id
     * @param employeeId Employee id
     * @param daysWorked Number of worked days
     * @return Updated Employee response entity
     */
    @PostMapping("/{employeeId}/work")
    public ResponseEntity<Employee> workEmployee(@PathVariable Long employeeId, @RequestParam int daysWorked) {
        try {
            var updatedEmployee = employeeService.work(employeeId, daysWorked);
            return ResponseEntity.ok(updatedEmployee);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Updates number of vacation days based on employee id
     * @param employeeId Employee id
     * @param daysUsed Number of vacation days used
     * @return Updated Employee response entity
     */
    @PostMapping("/{employeeId}/vacation")
    public ResponseEntity<Employee> takeVacation(@PathVariable Long employeeId, @RequestParam float daysUsed) {
        try {
            var updatedEmployee = employeeService.takeVacation(employeeId, daysUsed);
            return ResponseEntity.ok(updatedEmployee);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
