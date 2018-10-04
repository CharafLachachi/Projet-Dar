package helpers.models;

public class SignupModel extends LoginModel{
	private String firstname;
	private String username;
	
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

	@Override
	public String toString() {
		return "SignupModel [firstname=" + firstname + ", username=" + username + super.toString()+  "]";
	}
	
	
	
}
