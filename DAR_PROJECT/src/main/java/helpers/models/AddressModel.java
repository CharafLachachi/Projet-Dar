package helpers.models;

public class AddressModel {
	private String line1;
	private String city;
	private String postal_code;
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
	
	
}
