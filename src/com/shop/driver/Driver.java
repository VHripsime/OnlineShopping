package com.shop.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shop.profile.ShopProfile;

public class Driver {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		ShopProfile shopProfile = (ShopProfile) context.getBean("shopProfile");

		shopProfile.profile();

	}
}
