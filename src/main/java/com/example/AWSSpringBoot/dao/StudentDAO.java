package com.example.AWSSpringBoot.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.AWSSpringBoot.entites.BidUpdate;
import com.example.AWSSpringBoot.entites.Customer;
import com.example.AWSSpringBoot.entites.ProductDetails;
import com.example.AWSSpringBoot.entites.Student;
import com.example.AWSSpringBoot.entites.UserProductBidId;

@Repository
public class StudentDAO {

	@Autowired
	JdbcTemplate template;
	
	String amt1 = "100";
	String amt2 = "200";
	String amt3 = "300";
	String amt4 = "400";
	String amt5 = "500";
	
	private static Map<Integer,Student> students;
	
	static{
		students = new HashMap<>();
		students.put(1, new Student(1,"sai","Computers"));
		students.put(2, new Student(2,"chandu","science"));
		students.put(3, new Student(3,"allenki","engineering"));
		students.put(4, new Student(4,"allen","CSE"));
		
	}
	
	public List<Student> getStudents(){
		
		List<Student> studentsList =   new ArrayList<>();
		for(Map.Entry<Integer, Student> entry : students.entrySet()){
			studentsList.add(entry.getValue());
		}
		return studentsList;
	}
	
	public String updateStudent(int id){
		Student student = students.get(id);
		student.setCourse("newCourse");
		students.put(id, student);
		return "success";
	}
	
	public String addStudent(Customer s){
		//students.put(s.getId(), s);
		int update = template.update(
			    "INSERT INTO user(username,password) VALUES (?, ?)",
			    s.getUserId(), s.getPassword()
			);
		return update > 0 ? "true" : "false";
	}
	
	public String loginCustomer(Customer cust){
		
		final String SQL = "select * from user";
		List<Map<String, Object>> rows = template.queryForList(SQL);
		/*final String SQL = "select * from user where username = ? and password = ?";
		template.query(
				   SQL, new PreparedStatementSetter() {
				   
				   public void setValues(PreparedStatement preparedStatement) throws SQLException {
				      preparedStatement.setInt(1, id);
				   }
				},
				new StudentMapper());
		return "Login Successfull";*/
		for (Map<String, Object> row : rows) 
        {
			Customer customer = new Customer();
             customer.setUserId(row.get("username").toString());
             customer.setPassword(row.get("password").toString());
             if(cust.getPassword().equalsIgnoreCase(customer.getPassword()) && cust.getUserId().equalsIgnoreCase(customer.getUserId())){
            	 return "Login Successfull";
             }
         }
		return "Login Successfull";
	}

	public List<ProductDetails> getProductList() {
				final String SQL = "select * from products";
				final String SQL1 = "select * from user_product_bid";
		List<ProductDetails> pdetails = new ArrayList<>();
		List<UserProductBidId> pdetailsbid = new ArrayList<>();
		List<Map<String, Object>> rows = template.queryForList(SQL);
		List<Map<String, Object>> rows1 = template.queryForList(SQL1);
		
		for (Map<String, Object> row : rows) 
        {
			ProductDetails customer = new ProductDetails();
             customer.setPid(row.get("productid").toString());
             customer.setProductName(row.get("productname").toString());
             customer.setTimeRemaining(row.get("expirydate").toString());
             customer.setHighestAmount("0");
             pdetails.add(customer);
         }
		for (Map<String, Object> row : rows1) 
        {
			UserProductBidId customer = new UserProductBidId();
             customer.setAmount(row.get("amount").toString());
             customer.setCreateDate(row.get("createdate").toString());
             customer.setProductId(row.get("productid").toString());
             customer.setUserId(row.get("userid").toString());
             pdetailsbid.add(customer);
         }
		
		for(ProductDetails prodetails : pdetails){
			
			for(UserProductBidId biddetails : pdetailsbid){
				if(prodetails.getPid().equalsIgnoreCase(biddetails.getProductId()) && 
						Integer.parseInt(biddetails.getAmount()) > Integer.parseInt(prodetails.getHighestAmount())){
					prodetails.setHighestAmount(biddetails.getAmount());
				}
			}
		}
		return pdetails;
	}

	public void updateBiddingDetails(BidUpdate bup) {
		
		/*int update = template.update(
			    "UPDATE  user_product_bid set amount = ?   where userid = ? and productid = ?",
			    bup.getAmount(), bup.getUser(), bup.getPid()
			);*/
		//return update > 0 ? "true" : "false";
		
		String updateQuery = "UPDATE  user_product_bid set amount = ?   where userid = ? and productid = ?";
		template.update(updateQuery, bup.getAmount(), bup.getUser(), bup.getPid());
		
		/*final String SQL = "select * from products where productid = ?";
		final String SQL1 = "select * from user_product_bid where productid = ?";
		List<ProductDetails> pdetails = new ArrayList<>();
		List<UserProductBidId> pdetailsbid = new ArrayList<>();
		
		List<Map<String, Object>> rows = template.queryForList("select * from products where productid = ?", bup.getPid());
		List<Map<String, Object>> rows1 = template.queryForList("select * from user_product_bid where productid = ?", bup.getPid());
		
		
		        Object[] params = { name, id};
		        int[] types = {Types.VARCHAR, Types.BIGINT};
		  int rows = template.update(updateSql, params, types);
		
		 

		return pdetails.get(0);*/

	}
}
