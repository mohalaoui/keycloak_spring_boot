package com.keycloak.security.product.core;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/*
 * business logic class
 * 
 */

@Service
public class ProductServiceImpl implements ProductService {
	
	
	List<Product> productList;
	

	@PostConstruct
	void init() {
		productList = Lists.newArrayList(new Product("1", "nom1", 100L),
										 new Product("2", "nom2", 200L),
										 new Product("3", "nom3", 300L),
										 new Product("4", "nom4", 400L));
	}
	
	@Override
	public List<Product> getAllProducts() {
		
		return productList;
	}

	@Override
	public Product getProduct(String id) {
		
		return productList.stream().filter(product -> product.getId().equals(id)).findFirst().get();
	}

	@Override
	public Product addProduct(Product product) {
		productList.add(product);
		return product;
	}

}
