package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * Remember that Hibernate is designed to help us fix the paradigm mismatch between SQL and Java, which
 * is an OOP language. As such, we don't have to write any SQL in order to persist records. We can instead
 * let Hibernate take care of generating the queries. That said, in order for this to happen, we have to provide
 * some "mappings" from Hibernate so that the ORM frameworks knows how a Java model correlates to a table in
 * our DB. In fact, we have to tell Hibernate whether or not a model should even represent a table in our DB.
 * 
 * We can do this via several annotations.
 */

// Entity makes this class as an entity for us, meaning that we intend to map this class to a table in our DB.
@Entity

// The Table annotations allow us to specify information about the table we want our associated with our model.
// You can, for instance, specify the table's name (the DB table). That said, you don't have to specify the table
// name as Hibernate will just use the model's name as the table name if you specify no name.
@Table(name = "hibernate_reimbursement")
public class Reimbursement {

	
	/*
	 * The Id annotation denotes that we wish to use this field as a primary key on this table.
	 */
	@Id
	/*
	 * The Column annotation allows us to specify that this field should be a column on my table.
	 */
	@Column(name="reimbursement_id")
	/*
	 * The GeneratedValue annotation specifies that the column's value is auto-generated (in other words, you're using
	 * some sort of sequence or auto-incrementing data type).
	 */
	@GeneratedValue(generator = "reimbursement_id_seq", strategy = GenerationType.AUTO)
	/*
	 * We also have to generate the sequence here
	 */
	@SequenceGenerator(allocationSize = 1, name = "reimbursement_id_seq", sequenceName = "reimbursement_id_seq")
	private int reimbursement_id;
	@Column
	private double reimbursement_amount;
	@Column
	private String reimbursement_description;
	@Column
	private String employee_name;
	@Column
	private String reimbursement_status;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int reimbursement_id, double reimbursement_amount, String reimbursement_description, String employee_name, String reimbursement_status) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.reimbursement_amount = reimbursement_amount;
		this.reimbursement_description = reimbursement_description;
		this.employee_name = employee_name;
		this.reimbursement_status = reimbursement_status;
	}

	public int getReimbursement_id() {
		return reimbursement_id;
	}

	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}

	public double getReimbursement_amount() {
		return reimbursement_amount;
	}

	public void setReimbursement_amount(double reimbursement_amount) {
		this.reimbursement_amount = reimbursement_amount;
	}

	public String getReimbursement_description() {
		return reimbursement_description;
	}

	public void setReimbursement_description(String reimbursement_description) {
		this.reimbursement_description = reimbursement_description;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
	public String getReimbursement_status() {
		return reimbursement_status;
	}

	public void setReimbursement_status(String reimbursement_status) {
		this.reimbursement_status = reimbursement_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		Double reimbursement_amounts = (Double)reimbursement_amount;
		result = prime * result + ((reimbursement_description == null) ? 0 : reimbursement_description.hashCode());
		result = prime * result + reimbursement_id;
		result = prime * result + (((Double)reimbursement_amount == null) ? 0 : reimbursement_amounts.hashCode());
		result = prime * result + ((employee_name == null) ? 0 : employee_name.hashCode());
		result = prime * result + ((reimbursement_status == null) ? 0 : reimbursement_status.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (reimbursement_description == null) {
			if (other.reimbursement_description != null)
				return false;
		} else if (!reimbursement_description.equals(other.reimbursement_description))
			return false;
		if (reimbursement_id != other.reimbursement_id)
			return false;
		if ((Double)reimbursement_amount == null)
			return false;
		if (employee_name == null) {
			if (other.employee_name != null)
				return false;
		} else if (!employee_name.equals(other.employee_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursement_id=" + reimbursement_id + ", reimbursement_amount=" + reimbursement_amount + ", reimbursement_description="
				+ reimbursement_description + ", employee_name=" + employee_name + ", reimbursement_status=" + reimbursement_status + "]";
	}

}
