package com.revature.service;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.ReimbursementRepository;

// This is the service layer of my application. It is reserved for business logic (e.g.
// filtering data, transforming data.
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeService() {
		this.employeeRepository = new EmployeeRepository();
	}
	
	public Employee getByUsername(String username) {
		return this.employeeRepository.getByUsername(username);
	}
}
