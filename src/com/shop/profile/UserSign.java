package com.shop.profile;

import java.util.Scanner;

import com.shop.dao.UserDAOImpl;

public class UserSign {
	public void chooseUserSignType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 to: Sign up");
		System.out.println("Press 2 to: Sign in");

		int num = sc.nextInt();
		sc.nextLine();
		switch (num) {
		case 1:
			num = 1;
			UserDAOImpl userDAOImpl1 = new UserDAOImpl();
			userDAOImpl1.userRegistration();
			System.out.println();
			System.out.println("Successfully signed up");
			System.out.println();
			break;
		case 2:
			num = 2;
			UserDAOImpl userDAOImpl2 = new UserDAOImpl();
			userDAOImpl2.userLogin();

			break;

		}
	}
}
