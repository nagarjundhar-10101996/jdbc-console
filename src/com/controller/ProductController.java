package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exception.InvalidIdException;
import com.models.Product;
import com.service.ProductService;

public class ProductController {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		ProductService productService=new ProductService();
		while(true) {
			System.out.println("******product DB Ops********");
			System.out.println("1. Insert product");
			System.out.println("2. Fetch All product");
			System.out.println("3. Fetch product by Id");
			System.out.println("4. Delete product by Id");
			System.out.println("5. Filter the Salary");
			System.out.println("6. Filter the City");
			System.out.println("0. to Exit");
			System.out.println("*******************\n");
			
			int input=sc.nextInt();//1 2 3 4 5 6 0
			if(input==0) {
				System.out.println("Exiting Bye...");
				break;//Exiting while loop
			}
			switch (input) {
			case 1:
				Product product=new Product();
				System.out.println("Enter the Name");
				product.setName(sc.next());
				System.out.println("Enter the Price");
				product.setPrice(sc.nextDouble());
				System.out.println("Enter the Catagory");
				product.setCatagory(sc.next());
				productService.insertProduct(product);
				System.out.println("product Inserted in DB");
				break;
			case 2:
				List<Product> list= productService.fetchAllProduct();
				list.stream().forEach(e -> System.out.println(e));
				break;
			case 3:
				System.out.println("Enter product Id");
				int id= sc.nextInt();
				try {
					product=productService.getOneProduct(id);
					System.out.println(product);
				} catch (InvalidIdException e1) {
					System.out.println(e1.getMessage());
				}
				
				
				break;
			case 4:
				System.out.println("Enter product Id");
				id= sc.nextInt();
				try {
					productService.deleteProduct(id);;
					System.out.println("Employe Deleted");
				} catch (InvalidIdException e1) {
					System.out.println(e1.getMessage());
				}
				
				break;
			case 5:
				
				break;
			case 6:
				System.out.println("Enter the city to filter the records");
				String city= sc.next();
				list =productService.filterProduct(city);
				if(list.size() == 0) {
					System.out.println("City not exist in DB");
				}
				list.stream().forEach(e -> System.out.println(e));
				break;
			default:
				System.out.println("Invalid Input, Try Again!");
				break;
			}
		}
		sc.close();
	}
}
