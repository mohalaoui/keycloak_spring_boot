package com.keycloak.security.product.core;

import java.util.List;

public interface ProductService {

		List<Product> getAllProducts();

		Product getProduct(String id);

		Product addProduct(Product product);

}
