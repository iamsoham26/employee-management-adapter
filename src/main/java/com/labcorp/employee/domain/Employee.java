package com.labcorp.employee.domain;

public abstract sealed class Employee permits HourlyEmployee, SalarizedEmployee {

    private final Long employeeId;
    private float vacationDays = 0.0f;
    private int daysWorked = 0;

    private static final int MAX_WORKING_DAYS = 260;

    protected Employee(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public float getVacationDays() {
        return vacationDays;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void work() {}

    public void takeVacation() {}
    
}
