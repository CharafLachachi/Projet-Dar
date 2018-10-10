package com.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class AmadeusHotelsApiAccess {
	public static final String API_KEY = "UAGApAXeJN2K4Exq2GsKwDDGwxnIYGxT";

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
	
		public static String getHotels(String checkin, String checkout, Integer radius,Integer maxPrice, String latitude, String longitude ) {
			return "http://api.sandbox.amadeus.com/v1.2/hotels/search-circle"
					+ "?latitude="+latitude
					+ "&longitude="+longitude
					+ "&radius="+radius
					+ "&check_in="+"2018-10-12"
					+ "&check_out="+"2018-10-20"
					+ "&cy=EUR"
					+ "&number_of_results=20"
					+ "&max_rate="+maxPrice
					+ "&apikey="+API_KEY;
		}
	}





