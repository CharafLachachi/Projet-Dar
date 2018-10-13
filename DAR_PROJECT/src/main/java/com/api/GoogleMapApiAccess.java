package com.api;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public abstract class GoogleMapApiAccess {
	public static final String API_KEY = "AIzaSyBSnGKpO7hUSjsXgxF6ikkweAuNPNcAj-8";
	
	
	public static LatLng getCityGeoCodeByCityName(String cityName) {
		try {
			GeoApiContext context = new GeoApiContext.Builder()
				    .apiKey("AIzaSyBSnGKpO7hUSjsXgxF6ikkweAuNPNcAj-8")
				    .build();
			
			GeocodingResult[] results =  GeocodingApi.geocode(context,
				    cityName).await();
				return results[0].geometry.location ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
