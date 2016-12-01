package com.shop.profile;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class ShopProfile {
	@Autowired
	AdminSign adminSign;

	@Autowired
	UserSign userSign;

	public void profile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 to: Sign as Admin");
		System.out.println("Press 2 to: Sign as User");

		int num = sc.nextInt();
		sc.nextLine();
		switch (num) {
		case 1:
			adminSign.chooseAdminSignType();
			break;
		case 2:
			num = 2;
			userSign.chooseUserSignType();
			break;
		}
		sc.close();
	}
}
