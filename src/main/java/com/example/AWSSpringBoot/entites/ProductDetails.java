package com.example.AWSSpringBoot.entites;

import java.io.Serializable;

public class ProductDetails implements Serializable {


public String productName;

public String pid;

public String timeRemaining;

public String highestAmount;

	public ProductDetails() {
	}
	
	public ProductDetails(String productName, String pid, String timeRemianing, String highestAmount) {
		this.productName = productName;
		this.pid = pid;
		this.timeRemaining = timeRemianing;
		this.highestAmount = highestAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(String timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public String getHighestAmount() {
		return highestAmount;
	}

	public void setHighestAmount(String highestAmount) {
		this.highestAmount = highestAmount;
	}

}