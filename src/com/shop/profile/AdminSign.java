package com.shop.profile;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.shop.dao.AdminDAOImpl;

@Component
public class AdminSign {
	
	public void chooseAdminSignType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 to: Sign up");
		System.out.println("Press 2 to: Sign in");

		int num = sc.nextInt();
		sc.nextLine();
		switch (num) {
		case 1:
			num = 1;
			AdminDAOImpl adminDAOImpl1 = new AdminDAOImpl();
			adminDAOImpl1.adminRegistration();
			System.out.println();
			System.out.println("Successfully signed up");
			System.out.println();
			break;
		case 2:
			num = 2;
			AdminDAOImpl adminDAOImpl2 = new AdminDAOImpl();
			adminDAOImpl2.adminLogin();
			break;

		}
	}

}
