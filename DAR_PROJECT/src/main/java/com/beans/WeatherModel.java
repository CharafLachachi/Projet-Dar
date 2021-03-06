package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table (name = "Weather")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class WeatherModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "WETHER_ID")
	private Integer id;
	
	@Column(name = "MAIN")
	private String main;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ICON")
	private String icon;
	
	@Column(name = "TEMP")
	private Integer temp;
	
	/*relation avec publication */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PUB_ID")
	private Publication PUB_ID;
	
	@JsonIgnore
	public Publication getPub() {
		return PUB_ID;
	}
	
	public WeatherModel() {
	}
	
	public WeatherModel(Integer id, String main, String description, String icon, Integer temp) {
		super();
		this.id = id;
		this.main = main;
		this.description = description;
		this.icon = icon;
		this.temp = temp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "WeatherModel [id=" + id + ", main=" + main + ", description=" + description + ", icon=" + icon
				+ ", temp=" + temp + "]";
	}


	public void setPub(Publication pub) {
		this.PUB_ID = pub;
	}
	
	
}
