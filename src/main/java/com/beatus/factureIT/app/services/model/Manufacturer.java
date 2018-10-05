package com.beatus.factureIT.app.services.model;

import java.util.List;

public class Manufacturer extends User {
	
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
