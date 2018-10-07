package com.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * 
 * @author thamazgha
 *
 */
@Entity
@Table (name = "CITIESOFINTEREST")
public  class CitiesOfInterest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_ID")
	private int city_id;
	
	@Column(name ="CITY_NAME")
	private String city_name;
	
	@Column(name ="CITY_LON")
	private double city_longitude;
	
	@Column(name ="CITY_LAT")
	private double city_latitude;
	
	
	
	@ManyToMany(mappedBy="cities")
	private Set<Abonne> abonnes;



	public String getCity_name() {
		return city_name;
	}



	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}



	public Set<Abonne> getAbonnes() {
		return abonnes;
	}



	public void setAbonnes(Set<Abonne> abonnes) {
		this.abonnes = abonnes;
	}
	
	public CitiesOfInterest() {
		abonnes = new HashSet<Abonne>();
	}



	public int getCity_id() {
		return city_id;
	}



	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	
	
	
}
