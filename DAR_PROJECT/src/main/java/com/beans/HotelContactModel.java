package com.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class HotelContactModel {

	@Column(name = "TEL")
	private String tel;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "URL")
	private String url;


	/*relation avec publication */

	@OneToMany(mappedBy="contactHotel")
	private List<Publication> publications;

	public List<Publication> getPublications(){
		return this.publications;
	}



	public HotelContactModel() {

	}

	public HotelContactModel(String tel, String email, String url) {
		super();
		this.tel = tel;
		this.email = email;
		this.url = url;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "HotelContactModel [tel=" + tel + ", email=" + email + ", url=" + url + "]";
	}


}
