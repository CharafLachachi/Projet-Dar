package helpers.models;

import java.util.Arrays;

public class SignupModel extends LoginModel{
	private String firstname;
	private String username;
	private String[] cities;
	
	public SignupModel() {
		super();
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getCities() {
		return cities;
	}

	public void setCities(String[] cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "SignupModel [firstname=" + firstname + ", username=" + username + ", cities=" + Arrays.toString(cities)
				+ super.toString()+ "]";
	}

	
	
	
}
