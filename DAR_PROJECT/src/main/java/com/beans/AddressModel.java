package com.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table (name = "Address")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class AddressModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID")
	private Integer address_id;
	
	@Column(name = "LIGNE1")
	private String line1;
	
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "POSTAL_CODE")
	private String postal_code;
	
	
	/*relation avec publication */
	@OneToMany(mappedBy="address")
	private List<Publication> publications;

	@JsonIgnore
	public List<Publication> getPublications(){
		return this.publications;
	}
	
	
	/*getters & seters */
	
	public String getLine1() {
		return line1;
	}
	
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public AddressModel() {
		super();
	}
	@Override
	public String toString() {
		return "AddressModel [line1=" + line1 + ", city=" + city + ", postal_code=" + postal_code + "]";
	}

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	
	
}
