package helpers.models;

public class SearchRequestModel {
	private Integer price;
	private Integer radius;
	private Integer nbPers;
	private String chekInDate;
	private String checkOutDate;
	private String[] cities;
	public SearchRequestModel(Integer price, Integer radius, Integer nbPers, String chekInDate, String checkOutDate,
			String[] cities) {
		super();
		this.price = price;
		this.radius = radius;
		this.nbPers = nbPers;
		this.chekInDate = chekInDate;
		this.checkOutDate = checkOutDate;
		this.cities = cities;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
	public String[] getCities() {
		return cities;
	}
	public void setCities(String[] cities) {
		this.cities = cities;
	}
	public String getCityName() {
		if (getCities().length > 0) return getCities()[0];
		return null;
	}
	
	
	
}
