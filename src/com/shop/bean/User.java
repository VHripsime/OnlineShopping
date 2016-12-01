package com.shop.bean;

public class User {
	private int userId;
	private String firstName;

	private String lastName;
	private Double addressLat;
	private Double addressLong;
	private int birthYear;
	private String email;
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getAddressLat() {
		return addressLat;
	}

	public void setAddressLat(Double addressLat) {
		this.addressLat = addressLat;
	}

	public Double getAddressLong() {
		return addressLong;
	}

	public void setAddressLong(Double addressLong) {
		this.addressLong = addressLong;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", addressLat="
				+ addressLat + ", addressLong=" + addressLong + ", birthYear=" + birthYear + ", email=" + email
				+ ", password=" + password + "]";
	}

}
