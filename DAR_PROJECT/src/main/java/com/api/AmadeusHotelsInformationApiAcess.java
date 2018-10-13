package com.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class AmadeusHotelsInformationApiAcess {
	private static final String API_KEY = "UAGApAXeJN2K4Exq2GsKwDDGwxnIYGxT" ;
	
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
	
	public static String getHotelInfoById(String hotelId, String checkInDate, String checkOutDate) {
		return "https://api.sandbox.amadeus.com/v1.2/hotels/"+hotelId
				+ "?apikey="+API_KEY
				+ "&check_in="+checkInDate
				+ "&check_out="+checkOutDate;
	}
}
