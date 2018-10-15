package com.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TheWeatherApiAccess {
	public static String API_KEY = "ccb2eb6b449c527bf5b1fa7c7db9117d";
	public static String API_KEY2 = "7545538332092e099f6445285ca4109c";
	
	public static StringBuffer GetResponseFromAPI(String string_url) throws Exception {
		String inputLine;
		URL url = new URL(string_url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response;
	}
	
	public static String getDailyWeatherByCityName(String cityName) {
		return "https://api.openweathermap.org/data/2.5/weather?q="+cityName +
				"&appid=" + API_KEY + "&units=metric" ;
	}
	
	public static String getXDayWeatherByCityName(String cityName, int days) {
		return 	
		"https://api.openweathermap.org/data/2.5/forecast/daily?"
		+ "q="+cityName
		+"&mode=xml"
		+"&units=metric"
		+"&cnt="+days
		+"&appid=" + API_KEY2;
	}
}
