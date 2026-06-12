package com.labcorp.employee.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    /**
     * Update working days
     * @param daysWorked Number of days employee worked
     */
    public void work(int daysWorked) {
        log.trace("work() - update number of working days.");
        if (daysWorked < 0 || daysWorked > MAX_WORKING_DAYS) {
            throw new IllegalArgumentException("Days worked must be between 0 and 260.");
        }

        if (this.daysWorked + daysWorked > MAX_WORKING_DAYS) {
            throw new IllegalArgumentException("Cannot work more than 260 days a year.");
        }

        this.daysWorked += daysWorked;
        
        float annualAllocation = switch (this) {
            case HourlyEmployee h -> 10.0f;
            case ManagerEmployee m -> 30.0f;
            case SalarizedEmployee s -> 15.0f;
        };

        this.vacationDays += ((float) daysWorked / MAX_WORKING_DAYS) * annualAllocation;
    }

    /**
     * Update vacation days for an employee
     * @param daysUsed Vacation days used
     */
    public void takeVacation(float daysUsed) {
        log.trace("takeVacation() - update number of used vacation days.");
        if (daysUsed < 0 || daysUsed > this.vacationDays) {
            throw new IllegalArgumentException("Invalid vacation days requested.");
        }
        this.vacationDays -= daysUsed;
    }
    
}
