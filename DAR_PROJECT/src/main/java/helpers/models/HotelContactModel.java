package helpers.models;

public class HotelContactModel {
	private String tel;
	private String email;
	private String url;
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
