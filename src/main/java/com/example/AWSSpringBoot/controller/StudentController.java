package com.example.AWSSpringBoot.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.AWSSpringBoot.entites.BidUpdate;
import com.example.AWSSpringBoot.entites.Customer;
import com.example.AWSSpringBoot.entites.ProductDetails;
import com.example.AWSSpringBoot.entites.ProductList;
import com.example.AWSSpringBoot.entites.Student;
import com.example.AWSSpringBoot.service.StudentService;

@RestController
@RequestMapping("/dbs") 
public class StudentController {

	private static int amount = 200;
	StudentController(){
		System.out.println("In controller");
	}
	@Autowired
	private StudentService studentservice;
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Student> getStudents(){
		return studentservice.getStudents();
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.PUT)
	public String updateStudent(@PathVariable("id") int id){
		return studentservice.updateStudent(id);
	}
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public String loginCustomer(@RequestBody Customer student){
		 
		String message = "{\"success\" : \"true\"}";
		/*if(student.getUserId().startsWith("arif")){
			message = "{\"success\" : \"true\"}";
		}
		else{
			message = "{\"success\" : \"false\"}"; 
		}*/
		 /*if(studentservice.loginCustomer(student).equalsIgnoreCase("Login Successfull"))
			 message =  "{\"success\" : \"true\"}";
		 else
			 message =  "{\"success\" : \"false\"}";*/
		return message;
	}
	
	@RequestMapping(path="/register",method=RequestMethod.POST)
	public String registerCustomer(@RequestBody Customer student){
			 String addStudent = studentservice.addStudent(student);
			 if("true".equalsIgnoreCase(addStudent))
				 	return "{\"success\" : \"true\"}";
			 else
				 return "{\"success\" : \"false\"}";
		
	}
	
	//@RequestBody BiddingDetails student
	@RequestMapping(path="/products",method=RequestMethod.GET)
	public ProductList getBiddingDetails(){
		List<ProductDetails> plist =  studentservice.getProductList();
		ProductList list = new ProductList();
		list.setProducts(plist);
		return list;
		//return "{\"success\" : \"true\"}";
		
	}
	
	@RequestMapping(path="/bid",method=RequestMethod.POST)
	public ProductDetails updateBiddingDetails(@RequestBody BidUpdate bup){
		
		Integer finalamt = amount+1;
		ProductDetails p = new ProductDetails("Mobile", "1000", "2018-10-06 15:00:00:00", finalamt.toString());
		studentservice.updateBiddingDetails(bup);
		return p;
	}
	
}
