package com.bridgelabz;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeePayrollData {
	public int employeeId; // col
	public String employeeName; // col
	public double employeeSalary; //col
	public LocalDate startDate; //col
	
	public EmployeePayrollData(Integer id, String name, Double salary, LocalDate date) {
		
		this.employeeId = id;
		this.employeeName = name;
		this.employeeSalary = salary;
		this.startDate = date;
	}
	
	@Override
	public String toString() {
		
		return "EmployeeId: "+employeeId+", EmployeeName: "+employeeName+", EmployeeSalary: "+employeeSalary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmployeePayrollData that = (EmployeePayrollData) o;
		return employeeId == that.employeeId && Double.compare(that.employeeSalary, employeeSalary) == 0 && Objects.equals(employeeName, that.employeeName) && Objects.equals(startDate, that.startDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId, employeeName, employeeSalary, startDate);
	}
}
