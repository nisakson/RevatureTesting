package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

@Table(name="hibernate_employee")
public class Employee {

	@Id
	@Column
	@GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "employee_id_seq", sequenceName = "employee_id_seq")
	private int employee_id;
	@Column
	private String employee_name;
	@Column
	private String employee_username;
	@Column
	private String employee_password;
	@Column
	private String employee_role;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employee_id, String employee_name, String employee_username, String employee_password, String employee_role) {
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.employee_username = employee_username;
		this.employee_password = employee_password;
		this.employee_role = employee_role;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
	public String getEmployee_username() {
		return employee_username;
	}

	public void setEmployee_username(String employee_username) {
		this.employee_username = employee_username;
	}
	
	public String getEmployee_password() {
		return employee_password;
	}

	public void setEmployee_password(String employee_password) {
		this.employee_password = employee_password;
	}
	
	public String getEmployee_role() {
		return employee_role;
	}

	public void setEmployee_role(String employee_role) {
		this.employee_role = employee_role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employee_id;
		result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
		result = prime * result + ((employee_username == null) ? 0 : employee_username.hashCode());
		result = prime * result + ((employee_password == null) ? 0 : employee_password.hashCode());
		result = prime * result + ((employee_role == null) ? 0 : employee_role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employee_id != other.employee_id)
			return false;
		if (employee_name == null) {
			if (other.employee_name != null)
				return false;
		} else if (!employee_name.equals(other.employee_name))
			return false;
		if (employee_username == null) {
			if (other.employee_username != null)
				return false;
		} else if (!employee_username.equals(other.employee_username))
			return false;
		if (employee_password == null) {
			if (other.employee_password != null)
				return false;
		} else if (!employee_password.equals(other.employee_password))
			return false;
		if (employee_role == null) {
			if (other.employee_role != null)
				return false;
		} else if (!employee_role.equals(other.employee_role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", employee_name=" + employee_name + ", employee_username=" + employee_username + ", employee_password=" + employee_password + ", employee_role=" + employee_role + "]";
	}

}
