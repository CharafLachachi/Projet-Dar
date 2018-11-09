package com.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.utils.ArrQueue;

public  class UnsplashApiAccess {
	private ArrQueue<String> imageUrls;
	
	public UnsplashApiAccess() {
		this.imageUrls = new ArrQueue<>(100);
		parseStringBuffer();
		System.out.println(imageUrls.size());
	}
	public  StringBuffer GetResponseFromAPI(String string_url) throws Exception {
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
	
	public static String getApiLink() {
		return "https://api.unsplash.com/search/photos"
				+ "?query=wonderlust"
				+ "&count=100"
				+ "&client_id=d0362b01cac6c3d3a77008660ab5c15341cd84d3d3428dd223c1bc29091ed6c9";
	}
	
	public void parseStringBuffer(){
		Gson gson = new Gson();
		try {
			JsonObject json = gson.fromJson(GetResponseFromAPI(getApiLink()).toString(),JsonObject.class);
			JsonArray results = json.get("results").getAsJsonArray();
			for (JsonElement jsonElement : results) {
			JsonObject element = jsonElement.getAsJsonObject();
			imageUrls.enqueue(element.get("urls").getAsJsonObject().get("small").getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public String getImage() {
		String img = imageUrls.dequeue();
		imageUrls.enqueue(new String(img));
		return img;
	}
	
	
	
}
