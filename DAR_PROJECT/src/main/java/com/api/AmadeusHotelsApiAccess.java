package com.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOffer;

public abstract class AmadeusHotelsApiAccess {
	public static final String API_KEY = "UAGApAXeJN2K4Exq2GsKwDDGwxnIYGxT";
	public static final 	Amadeus amadeus = Amadeus
            .builder("YhkwZHS3fGykKY95hO4xX1SEF9NGL18u", "74EAgWWFpZt9ZcFm")
            .build();

		public static HotelOffer[] getHotelOffers(String checkin, String checkout, Integer radius,Integer maxPrice, String latitude, String longitude ) {
//			return "http://api.sandbox.amadeus.com/v1.2/hotels/search-circle"
//					+ "?latitude="+latitude
//					+ "&longitude="+longitude
//					+ "&radius="+radius
//					+ "&check_in="+"2018-10-12"
//					+ "&check_out="+"2018-10-20"
//					+ "&cy=EUR"
//					+ "&number_of_results=20"
//					+ "&max_rate="+maxPrice
//					+ "&apikey="+API_KEY;
			
		
			try {
				HotelOffer[] offers = amadeus.shopping.hotelOffers.get(Params
						  .with("latitude", latitude).
						  and("longitude", longitude).
//						  and("priceRange=0-", maxPrice).
						  and("checkInDate", checkin).
						  and("checkOutDate", checkout).
						  and("radius", radius)
						  );
				return offers;		
			} catch (ResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
		
	}





