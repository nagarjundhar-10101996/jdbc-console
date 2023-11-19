package com.service;

import com.Repository.ProductRepository;
import com.exception.InvalidIdException;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.models.Product;

public class ProductService {

	ProductRepository productRepository=new ProductRepository();
	public void insertProduct(Product product) {
		productRepository.insertProduct(product);
	}
	public List<Product> fetchAllProduct() {		
			return productRepository.fetchAllProducts();
		
	}
	public Product getOneProduct(int id) throws InvalidIdException{
		Product Product= productRepository.getOneProduct(id);
		if(Product.getId()==0) {
			throw new InvalidIdException("Invalid Id given, try again");
		}
		return Product;
	}
	public void deleteProduct(int id) throws InvalidIdException {
		getOneProduct(id);
		
		productRepository.deleteProduct(id);
	}
	public List<Product> filterProduct(String city) {
		List<Product> list =fetchAllProduct();
		list =list.stream()
				.filter(e-> e.getCatagory().equalsIgnoreCase(city))
				.collect(Collectors.toList());
		return list;
	}
	

}
