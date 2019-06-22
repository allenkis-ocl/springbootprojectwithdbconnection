package com.example.AWSSpringBoot.entites;

import java.io.Serializable;
import java.util.List;

public class ProductList implements Serializable {

	public List<ProductDetails> products;

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}

}
