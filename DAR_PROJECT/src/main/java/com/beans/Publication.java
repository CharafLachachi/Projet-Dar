package com.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * 
 * @author thamazgha
 *
 */
@Entity
@Table (name = "PUBLICATION")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Publication {
	public Publication() {
		this.comments = new ArrayList<Commentaire>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PUB_ID")
	private int Pub_id;

	@Column(name = "OWNER")
	private int owner;
	
	@Column(name = "PICTURE_URL")
	private String picture;
	
	//y aura d'autres attributs 
	@Column(name = "ROOMPRICE")
	private float roomPrice;
	
	@Column(name = "NBPERS")
	private int nbPers;
	
	@Column(name = "RADIUS")
	private int radius;
	
	@Column(name = "CHECKOUTDATE")
	private String checkOutDate;
	
	@Column(name = "CHECKINDATE")
	private String chekInDate;
	
	@Column(name = "HOTELNAME")
	private String hotelName;
	
	@Column(name = "CITY")
	private String city;
	
	
	@OneToOne(mappedBy = "PUB_ID", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = false)
	private WeatherModel weather;
	
	
	  // TODO relation to many 
	@ManyToOne
	@JoinColumn(name="CONTACT_ID")
	private HotelContactModel hotelContacts;
	


    // TODO relation to many 
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private AddressModel address;
	
	
	@ManyToMany(mappedBy="publications")
	private Set<Abonne> abonnes;

	@OneToMany(mappedBy="publication")
	private List<Commentaire> comments;

	public List<Commentaire> getComments(){
		return this.comments;
	}

	@Override
	public String toString() {
		return "Publication [Pub_id=" + Pub_id + ", owner=" + owner + ", roomPrice=" + roomPrice + ", nbPers=" + nbPers
				+ ", radius=" + radius + ", checkOutDate=" + checkOutDate + ", chekInDate=" + chekInDate + ", weather="
				+ weather + ", city=" + city + ", hotelName=" + hotelName + ", address=" + address + ", abonnes="
				+ abonnes + ", comments=" + comments + "]";
	}

	public int getPub_id() {
		return Pub_id;
	}

	public void setPub_id(int pub_id) {
		Pub_id = pub_id;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public float getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(float roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getNbPers() {
		return nbPers;
	}

	public void setNbPers(int nbPers) {
		this.nbPers = nbPers;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getChekInDate() {
		return chekInDate;
	}

	public void setChekInDate(String chekInDate) {
		this.chekInDate = chekInDate;
	}

	public WeatherModel getWeather() {
		return weather;
	}

	public void setWeather(WeatherModel weather) {
		this.weather = weather;
	}

	

	public HotelContactModel getHotelContacts() {
		return hotelContacts;
	}

	public void setHotelContacts(HotelContactModel hotelContacts) {
		this.hotelContacts = hotelContacts;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public Set<Abonne> getAbonnes() {
		return abonnes;
	}

	public void setAbonnes(Set<Abonne> abonnes) {
		this.abonnes = abonnes;
	}

	public void setComments(List<Commentaire> comments) {
		this.comments = comments;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
