package com.services;

import com.api.TheWeatherApiAccess;
import com.beans.WeatherModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class GetWeatherByCityService {
	
	public static WeatherModel getWeatherByCityName(String cityName) {
		StringBuffer response;
		try {
			response = TheWeatherApiAccess.GetResponseFromAPI(TheWeatherApiAccess.getDailyWeatherByCityName(cityName));
			if (response != null) {
				//System.out.println(response.toString());
			} else System.out.println("response null");
			Gson gson = new GsonBuilder().create();
			JsonParser parser = new JsonParser();
			JsonObject weatherObjectJson = parser.parse(response.toString()).getAsJsonObject();
			
			WeatherModel weatherModel = new WeatherModel();
			if (weatherObjectJson.has("weather") && weatherObjectJson.has("main")) {
				JsonArray weatherArray = weatherObjectJson.get("weather").getAsJsonArray();
				if (weatherArray.size() != 0) {
				JsonObject weather = weatherArray.get(0).getAsJsonObject();
				
				weatherModel.setDescription(weather.get("description").getAsString());
				weatherModel.setIcon(weather.get("icon").getAsString());
				weatherModel.setMain(weather.get("main").getAsString());
				weatherModel.setId(weather.get("id").getAsInt());
				weatherModel.setIcon(weather.get("icon").getAsString().replaceAll("n", "d"));
				weatherModel.setTemp(weatherObjectJson.get("main").getAsJsonObject().get("temp").getAsInt());
				}
				}
			

			return weatherModel;
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
