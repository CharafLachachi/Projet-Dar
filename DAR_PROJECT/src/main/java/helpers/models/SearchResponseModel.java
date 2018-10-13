package helpers.models;

import com.amadeus.resources.HotelOffer.HotelTax;

public class SearchResponseModel {
	private String roomPrice;
	private Integer radius;
	private Integer nbPers;
	private String chekInDate;
	private String checkOutDate;
	private AddressModel address;
	private String weather;
	private String picture;
	private String city;
	private String hotelName;
	private HotelContactModel hotelContacts;
	
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

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
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

	@Override
	public String toString() {
		return "SearchResponseModel [roomPrice=" + roomPrice + ", radius=" + radius + ", nbPers=" + nbPers
				+ ", chekInDate=" + chekInDate + ", checkOutDate=" + checkOutDate + ", address=" + address
				+ ", weather=" + weather + ", picture=" + picture + ", city=" + city + ", hotelName=" + hotelName
				+ ", hotelContacts=" + hotelContacts + "]";
	}

	
	
	
}
