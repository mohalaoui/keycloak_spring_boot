package com.keycloak.security.product.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.keycloak.security.product.core.Product;
import com.keycloak.security.product.core.ProductService;

@RestController
public class ProductControllerImpl {
	
	@Autowired
	private ProductService produitService;
	
	
	@GetMapping(path ="/products")
	public ModelAndView getProduits (@AuthenticationPrincipal Principal principal) {
		
		List<Product> products = produitService.getAllProducts();
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("products", products);
	    params.put("username", principal.getName());
		
		return new ModelAndView("products", params);
	}
	
	@GetMapping(value="/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView getProduit (@PathVariable String id) {
		Product product = produitService.getProduct(id); 
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("product", product);
		
		return new ModelAndView("product", params);
	}
	
	@PostMapping(value="/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> postProduit (@RequestBody Product produit) {
		 
		Product produitsDomain = produitService.addProduct(produit);
		return ResponseEntity.ok().body(produitsDomain);
	}
	
	@GetMapping(value="/logout")
	public ModelAndView logout (HttpServletRequest httpServletRequest) throws ServletException {
		
		httpServletRequest.logout();
		
		return new ModelAndView("index");
	}

}
