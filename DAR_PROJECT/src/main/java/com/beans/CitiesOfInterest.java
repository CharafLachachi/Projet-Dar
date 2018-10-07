package com.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
/**
 * 
 * @author thamazgha
 *
 */
public class CitiesOfInterest {
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
	
}
