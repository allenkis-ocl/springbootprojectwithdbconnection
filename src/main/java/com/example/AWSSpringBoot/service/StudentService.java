package com.example.AWSSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AWSSpringBoot.dao.StudentDAO;
import com.example.AWSSpringBoot.entites.BidUpdate;
import com.example.AWSSpringBoot.entites.Customer;
import com.example.AWSSpringBoot.entites.ProductDetails;
import com.example.AWSSpringBoot.entites.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDAO studentdao;
	
	public List<Student> getStudents(){
		return studentdao.getStudents();
	}
	
	public String updateStudent(int id){
		return studentdao.updateStudent(id);
	}
	
	public String addStudent(Customer s){
		
		return studentdao.addStudent(s);
	}
	
	public String loginCustomer(Customer cust){
		
		String userId = null;
		String password = null;
		
		if(cust != null){
			password  = cust.getPassword();
			userId = cust.getUserId();
			if(password == null || password.equalsIgnoreCase(" ") || password.equalsIgnoreCase("")){
				return "Invalid Request";
			}
			
		}
		return studentdao.loginCustomer(cust); 
		
		
	}

	public List<ProductDetails> getProductList() {
		
		return studentdao.getProductList();
	}

	public void updateBiddingDetails(BidUpdate bup) {
		 studentdao.updateBiddingDetails(bup);
		
	}
}
