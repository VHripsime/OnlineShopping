package com.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.shop.table.design.DBTablePrinter;

@Component
public class UserDAOImpl implements UserDAO {

	@Override
	public void userRegistration() {

		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		System.out.print("Enter your First name: ");
		String first = sc.nextLine();
		System.out.print("Enter your Last name: ");
		String last = sc.nextLine();
		System.out.print("Enter your Address lat: ");
		Double lat = sc.nextDouble();
		System.out.print("Enter your Address long: ");
		Double lon = sc.nextDouble();
		sc.nextLine();
		System.out.print("Enter your Email: ");
		String email = sc.nextLine();
		System.out.print("Enter your birth year: ");
		Integer birth_year = sc.nextInt();
		sc.nextLine();

		System.out.print("Select password: ");
		String pass1 = sc.nextLine();
		System.out.print("Confirm password: ");
		String pass2 = sc.nextLine();
		if (!pass1.equals(pass2))
			System.out.println("Passwords don't match.");
		else {

			System.out.println("Thank you for joining us!");
		}
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost/online_shop?" + "user=root&password=mysql.com");
			java.util.Date utilDate = new Date();
			java.sql.Date loginDate = new java.sql.Date(utilDate.getTime());

			String sqlQuery = "INSERT INTO user (First_Name, Last_Name, Address_lat, Address_long, Email, Birth_Year, Password, Last_Login_Date) VALUES (?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sqlQuery);

			statement.setString(1, first);
			statement.setString(2, last);
			statement.setDouble(3, lat);
			statement.setDouble(4, lon);
			statement.setString(5, email);
			statement.setInt(6, birth_year);
			statement.setString(7, pass2);
			statement.setDate(8, loginDate);
			statement.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		sc.close();
	}

	@Override
	public void userLogin() {

		Scanner sc = new Scanner(System.in).useLocale(Locale.US);

		System.out.print("Enter your Email: ");
		String email = sc.nextLine();

		System.out.print("Enter your Password: ");
		String password = sc.nextLine();
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost/online_shop?" + "user=root&password=mysql.com");

			String sqlQuery1 = "SELECT Password FROM user WHERE Email='" + email + "'";

			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery(sqlQuery1);

			while (rs1.next()) {
				String res1 = rs1.getString("Password");
				if (!res1.equals(password))
					System.out.println("Invalid credentials");
				else
					System.out.println("Successfully logged in");
			}

			java.util.Date utilDate = new Date();
			java.sql.Date loginDate = new java.sql.Date(utilDate.getTime());

			String sqlQuery2 = "UPDATE user SET Last_Login_Date = ? WHERE Email='" + email + "'";
			PreparedStatement statement2 = con.prepareStatement(sqlQuery2);
			statement2.setDate(1, loginDate);
			statement2.executeUpdate();

			boolean close = false;

			while (close == false) {
				System.out.println("Press 1 to: Do Shopping");
				System.out.println("Press 2 to: View Orders History");
				System.out.println("Press 3 to: View Products");
				System.out.println("Press 4 to: Sign out");
				int num2 = sc.nextInt();
				sc.nextLine();
				switch (num2) {
				case 1:
					num2 = 1;
					ProductDAOImpl product = new ProductDAOImpl();
					System.out.print("Choose product ID: ");
					Integer product_id = sc.nextInt();
					sc.nextLine();
					System.out.print("Choose product colour: ");
					String colour = sc.nextLine();
					System.out.print("Choose size: ");
					String size = sc.nextLine();
					System.out.print("Enter product quantity: ");
					Integer quantity = sc.nextInt();
					sc.nextLine();
					System.out.println();
					java.util.Date utilDate2 = new Date();
					java.sql.Date orderDate2 = new java.sql.Date(utilDate2.getTime());

					String sqlQuery5 = "SELECT User_ID FROM user WHERE Email='" + email + "'";

					Statement st4 = con.createStatement();
					ResultSet rs4 = st4.executeQuery(sqlQuery5);

					while (rs4.next()) {
						Integer res4 = rs4.getInt("User_ID");

						String sqlQuery4 = "INSERT INTO orders (Product_ID, Colour, Size, Quantity, Order_Date,User_ID) VALUES (?, ?, ?, ?,?,?)";

						PreparedStatement statement = con.prepareStatement(sqlQuery4);

						statement.setInt(1, product_id);
						statement.setString(2, colour);
						statement.setString(3, size);
						statement.setInt(4, quantity);
						statement.setDate(5, orderDate2);
						statement.setInt(6, res4);
						statement.executeUpdate();

					}
					System.out.println();
					System.out.println("Orders added to basket");
					System.out.println();
					break;
				case 2:
					num2 = 2;
					String sqlQuery9 = "SELECT User_ID FROM user WHERE Email='" + email + "'";

					Statement st6 = con.createStatement();
					ResultSet rs6 = st6.executeQuery(sqlQuery9);

					while (rs6.next()) {
						Integer i = rs6.getInt("User_ID");
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE User_ID='" + i + "'");

						DBTablePrinter.printResultSet(rs);
					}
					break;

				case 3:
					num2 = 3;
					ProductDAOImpl products=new ProductDAOImpl();
					products.showProducts();
					break;

				case 4:
					num2 = 4;
					close = true;
					System.out.println("You've Successfully Signed out");
					break;

				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		sc.close();

	}

	@Override
	public void viewOrderHistory() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost/online_shop?" + "user=root&password=mysql.com");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM orders INNER JOIN product ON orders.Product_ID=product.Product_ID");

			DBTablePrinter.printResultSet(rs);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
