package com.bridgelabz;

import java.time.LocalDate;

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
}
