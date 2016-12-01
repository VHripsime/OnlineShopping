package com.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.shop.distribution.algorithm.TSPDistribution;
import com.shop.table.design.DBTablePrinter;

@Component
public class AdminDAOImpl implements AdminDAO {

		@Override
		public void adminRegistration() {
			Scanner sc = new Scanner(System.in).useLocale(Locale.US);

			System.out.print("Enter your Email: ");
			String email = sc.nextLine();

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

				String sqlQuery = "INSERT INTO admin (Email,Password) VALUES (?, ?)";
				PreparedStatement statement = con.prepareStatement(sqlQuery);

				statement.setString(1, email);
				statement.setString(2, pass2);
				statement.executeUpdate();

				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			sc.close();

		}

		@Override
		public void adminLogin() {

			Scanner sc = new Scanner(System.in).useLocale(Locale.US);

			System.out.print("Enter your Email: ");
			String email = sc.nextLine();

			System.out.print("Enter your Password: ");
			String password = sc.nextLine();
			try {

				Class.forName("com.mysql.jdbc.Driver").newInstance();

				Connection con = DriverManager
						.getConnection("jdbc:mysql://localhost/online_shop?" + "user=root&password=mysql.com");

				String sqlQuery1 = "SELECT Password FROM admin WHERE Email='" + email + "'";

				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery(sqlQuery1);

				while (rs1.next()) {
					String res1 = rs1.getString("Password");
					if (!res1.equals(password))
						System.out.println("Invalid credentials");
					else
						System.out.println("Successfully logged in");
				}

				boolean close1 = false;
				while (close1 == false) {
					System.out.println("Press 1 to: Get Users Database");
					System.out.println("Press 2 to: Get All Orders");
					System.out.println("Press 3 to: Distribute Orders");
					System.out.println("Press 4 to: Sign out");
					int num2 = sc.nextInt();
					sc.nextLine();
					switch (num2) {
					case 1:
						num2 = 1;

						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM user");
						DBTablePrinter.printResultSet(rs);

						break;
					case 2:
						num2 = 2;
						Statement stmt2 = con.createStatement();
						ResultSet rs2 = stmt2.executeQuery("SELECT * FROM orders");
						DBTablePrinter.printResultSet(rs2);
						break;
					case 3:
						num2 = 3;
						distributeOrders();
						System.out.println();
						break;

					case 4:
						num2 = 4;
						close1 = true;
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
		public void distributeOrders() {
			TSPDistribution distribute=new TSPDistribution();
			distribute.getPath();
			
		}
}
