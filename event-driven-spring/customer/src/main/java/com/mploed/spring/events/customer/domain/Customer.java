package com.mploed.spring.events.customer.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long id;
	private String applicationNumber;
	private String firstName;
	private String lastName;
	private String street;
	private String postCode;
	private String city;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", applicationNumber='" + applicationNumber + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", street='" + street + '\'' +
				", postCode='" + postCode + '\'' +
				", city='" + city + '\'' +
				'}';
	}

}
