package com.beans;

public class WeatherModel {
	private Integer id;
	private String main;
	private String description;
	private String icon;
	private Integer temp;
	
	public WeatherModel() {
	}
	
	public WeatherModel(Integer id, String main, String description, String icon, Integer temp) {
		super();
		this.id = id;
		this.main = main;
		this.description = description;
		this.icon = icon;
		this.temp = temp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "WeatherModel [id=" + id + ", main=" + main + ", description=" + description + ", icon=" + icon
				+ ", temp=" + temp + "]";
	}
	
	
}
