package com.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.Product;



public class ProductRepository {

	private String url = "jdbc:mysql://localhost:3306/jdbcapp";
	private String userdb="root";
	private String password="Nagarjun@123#";
	private String driver = "com.mysql.cj.jdbc.Driver";
	Connection con;
	public void dbConnect() {
		try {
			Class.forName(driver);
			//			System.out.println("driver loaded...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, userdb, password);
			//			System.out.println("conn established");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void dbClose() {
		try {
			con.close();
			//			System.out.println("DB closed...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertProduct(Product product) {

		dbConnect();
		String sql="insert into product(name,  price, category) values(?,?,?)";

		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setString(4, product.getCatagory());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		dbClose();
	}
	public List<Product> fetchAllProducts() {
		dbConnect();
		String sql="select * from product";
		List<Product> list =new ArrayList<>();
		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			ResultSet rst=preparedStatement.executeQuery();
			while(rst.next()) {
				Product product=new Product();
				//fetch each column from DB and save it in object
				product.setId(rst.getInt("id"));
				product.setName(rst.getString("name"));
				product.setPrice(rst.getDouble("salary"));
				product.setCatagory(rst.getString("city"));
				//save obj in list
				list.add(product);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dbClose();
		return list;
	}
	public Product getOneProduct(int id) {
		dbConnect();
		String sql="select * from product where id=?";
		Product product=new Product();
		try {
			PreparedStatement preparedStatement= con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rst=preparedStatement.executeQuery();
			if(rst.next()) {

				product.setId(rst.getInt("id"));
				product.setName(rst.getString("name"));
				product.setPrice(rst.getDouble("salary"));
				product.setCatagory(rst.getString("city"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dbClose();
		return product;
	}
	public Product deleteProduct(int id) {
		dbConnect();
		String sql="delete from product where id=?";

		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbClose();
		return null;
	}
	public Product filterEmployeeSalary(String Function) {
		dbConnect();
		String sql="select * from product where catagory=?";
		Product product=new Product(); 
		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, Function);
			ResultSet rst= preparedStatement.executeQuery();
			if(rst.next()) {

				product.setId(rst.getInt("id"));
				product.setName(rst.getString("name"));
				product.setPrice(rst.getDouble("salary"));
				product.setCatagory(rst.getString("city"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbClose();
		return null;
	}


	//delete update insert - executeUpdate
	//fetch or select - executeQuery

}
