package helpers.models;

public class SearchResponseModel {
	private String roompPrice;
	private Integer radius;
	private Integer nbPers;
	private String chekInDate;
	private String checkOutDate;
	private String address;
	private String weather;
	private String picture;
	private String city;
	private String hotelName;
	private String hotelContacts;
	
	public SearchResponseModel() {}

	public String getRoompPrice() {
		return roompPrice;
	}

	public void setRoompPrice(String roompPrice) {
		this.roompPrice = roompPrice;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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

	public String getHotelContacts() {
		return hotelContacts;
	}

	public void setHotelContacts(String hotelContacts) {
		this.hotelContacts = hotelContacts;
	}

	
	
	
}
