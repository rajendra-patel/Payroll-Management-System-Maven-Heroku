package com.management.model;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {
	protected int emp_id;
	protected int manager;
	protected String dept_id;
	protected String first_name;
	protected String last_name;
	protected LocalDate dob;
	protected String gender;
	protected String country;
	protected String state;
	protected String city;
	protected String address;
	protected String pincode;
	protected String email;
	protected String mobile_no;
	protected LocalDate doj;

	public Employee() {}
	public Employee(Manager man) {
		this.emp_id = man.emp_id;
		this.manager = man.manager;
		this.dept_id = man.dept_id;
		this.first_name = man.first_name;
		this.last_name = man.last_name;
		this.dob = man.dob;
		this.gender = man.gender;
		this.country = man.country;
		this.state = man.state;
		this.city = man.city;
		this.address = man.address;
		this.pincode = man.pincode;
		this.email = man.email;
		this.mobile_no = man.mobile_no;
		this.doj = man.doj;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [emp_id=").append(emp_id).append(", dept_id=").append(dept_id).append(", manager=")
				.append(manager).append(", first_name=").append(first_name).append(", last_name=").append(last_name)
				.append(", dob=").append(dob).append(", gender=").append(gender).append(", country=").append(country)
				.append(", state=").append(state).append(", city=").append(city).append(", address=").append(address)
				.append(", pincode=").append(pincode).append(", email=").append(email).append(", mobile_no=")
				.append(mobile_no).append(", doj=").append(doj).append("]");
		return builder.toString();
	}
}