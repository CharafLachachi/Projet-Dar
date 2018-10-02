package com.services;

import com.api.TheWeatherApiAccess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetWeatherByCityService {
	
	public JsonObject getWeatherByCityName(String cityName) {
		StringBuffer response;
		try {
			response = TheWeatherApiAccess.GetResponseFromAPI(TheWeatherApiAccess.getWeatherByCityName(cityName));
			if (response != null) {
				System.out.println(response.toString());
			} else System.out.println("response null");
			Gson gson = new GsonBuilder().create();
			JsonParser parser = new JsonParser();
			JsonObject o = parser.parse(response.toString()).getAsJsonObject();

			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("je retourne null");
		return null;

//		JSONObject json_weather_city_name = null;
//		try {
//			StringBuffer response = TheWeatherApiAccess
//					.GetResponseFromAPI(TheWeatherApiAccess.getWeatherByCityName(cityName));
//			JSONObject j = new JSONObject(response.toString());
//			JSONArray popular_movies = j.getJSONArray("results");
//			json_weather_city_name = new JSONObject();
//			json_weather_city_name.put("city_weather_name", popular_movies);
//			json_weather_city_name.put("response", 200);
//			return json_weather_city_name;
//		} catch (  Exception e) {
//			e.printStackTrace();
//		}
//		return json_weather_city_name;
	}
}
