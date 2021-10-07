package com.revature.service;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementRepository;

// This is the service layer of my application. It is reserved for business logic (e.g.
// filtering data, transforming data.
public class ReimbursementService {
	
	private ReimbursementRepository reimbursementRepository;
	
	public ReimbursementService() {
		this.reimbursementRepository = new ReimbursementRepository();
	}
	
	public List<Reimbursement> findAll(){
		return this.reimbursementRepository.findAll();
	}
	
	public List<Reimbursement> findName(String name){
		return this.reimbursementRepository.findName(name);
	}
	
	public void save(Reimbursement reimbursement) {
		this.reimbursementRepository.save(reimbursement);
	}
	

	public void approve(int id) {
		this.reimbursementRepository.approve(id);
	}
	
	public void deny(int id) {
		this.reimbursementRepository.deny(id);
	}
	
	//additional methods used only in testing
	public void delete(Reimbursement reimbursement) {
		this.reimbursementRepository.delete(reimbursement);
	}
	public void pending(int id) {
		this.reimbursementRepository.pending(id);
	}
}
