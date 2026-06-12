package com.labcorp.employee.domain;

public sealed class SalarizedEmployee extends Employee permits ManagerEmployee {
    public SalarizedEmployee(Long employeeId) {
        super(employeeId);
    }
}
