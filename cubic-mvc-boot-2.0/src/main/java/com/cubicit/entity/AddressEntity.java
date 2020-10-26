package com.cubicit.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity //means this class or object can be saved into the database
@Table(name="address_tbl")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int aid;
	
	@Column(length=100)
	private String street;
	
	@Column(length=20)
	private String state;
	
	@Column(length=50)
	private String country;
	
	@Column(length=8)
	private int zipCode;
	
	private Timestamp doe;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customerEntity;
	

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	@Override
	public String toString() {
		return "AddressEntity [aid=" + aid + ", street=" + street + ", state=" + state + ", country=" + country
				+ ", zipCode=" + zipCode + ", doe=" + doe + ", customerEntity=" + customerEntity + "]";
	}

}
