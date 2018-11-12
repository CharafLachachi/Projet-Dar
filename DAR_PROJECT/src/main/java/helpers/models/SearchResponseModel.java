package helpers.models;

import com.beans.AddressModel;
import com.beans.HotelContactModel;
import com.beans.WeatherModel;

public class SearchResponseModel {
	private String roomPrice;
	private Integer radius;
	private Integer nbPers;
	private String chekInDate;
	private String checkOutDate;
	private AddressModel address;
	private WeatherModel weather;
	private String picture;
	private String city;
	private String hotelName;
	private HotelContactModel hotelContacts;
	private String currency;
	private String pub_id;
	
	public SearchResponseModel() {}

	public String getRoompPrice() {
		return roomPrice;
	}

	public void setRoomPrice(String roompPrice) {
		this.roomPrice = roompPrice;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Integer getNbPers() {
		return nbPers;
	}

	public void setNbPers(Integer nbPers) {
		this.nbPers = nbPers;
	}

	public String getChekInDate() {
		return chekInDate;
	}

	public void setChekInDate(String chekInDate) {
		this.chekInDate = chekInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public WeatherModel getWeather() {
		return weather;
	}

	public void setWeather(WeatherModel weather) {
		this.weather = weather;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	public HotelContactModel getHotelContacts() {
		return hotelContacts;
	}

	public void setHotelContacts(HotelContactModel hotelContacts) {
		this.hotelContacts = hotelContacts;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "SearchResponseModel [roomPrice=" + roomPrice + ", radius=" + radius + ", nbPers=" + nbPers
				+ ", chekInDate=" + chekInDate + ", checkOutDate=" + checkOutDate + ", address=" + address
				+ ", weather=" + weather + ", picture=" + picture + ", city=" + city + ", hotelName=" + hotelName
				+ ", hotelContacts=" + hotelContacts + "]";
	}

	public String getPub_id() {
		return pub_id;
	}

	public void setPub_id(String pub_id) {
		this.pub_id = pub_id;
	}

	public String getRoomPrice() {
		return roomPrice;
	}

	
	
	
}
