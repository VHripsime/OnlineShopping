package com.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.shop.table.design.DBTablePrinter;

@Component
public class ProductDAOImpl implements ProductDAO{
	
	public void showProducts() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost/online_shop?" + "user=root&password=mysql.com");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product");

			DBTablePrinter.printResultSet(rs);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
