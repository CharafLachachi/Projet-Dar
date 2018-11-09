package com.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.time.Duration;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.HotelOffer.AddressType;
import com.amadeus.resources.HotelOffer.HotelContact;
import com.amadeus.resources.HotelOffer.Offer;
import com.api.AmadeusHotelsApiAccess;
import com.api.AmadeusHotelsInformationApiAcess;
import com.api.GoogleMapApiAccess;
import com.api.UnsplashApiAccess;
import com.beans.AddressModel;
import com.beans.HotelContactModel;
import com.beans.WeatherModel;
import com.google.gson.Gson;
import com.google.maps.model.LatLng;

import helpers.models.SearchRequestModel;
import helpers.models.SearchResponseModel;

/*
 * 
 * @author Lachachi charaf
 * 
 * 
 * */
public class SearchHotelsService {

	
	public String getHotels(SearchRequestModel searchObject) {
		// to get pictures
		UnsplashApiAccess api = new UnsplashApiAccess();

		String checkInDate;
		String checkOutDate;
		Calendar calendarIn;
		Calendar calendarOut;
		LatLng googleGeocode;
		HotelOffer[] amadeusResponse;
		Gson gson = new Gson();

		// Required format by amadeus api
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		// To format iso date sent by the client as 2018-10-12T22:00:00.000Z
		calendarIn = javax.xml.bind.DatatypeConverter.parseDateTime(searchObject.getChekInDate());
		calendarOut = javax.xml.bind.DatatypeConverter.parseDateTime(searchObject.getCheckOutDate());
		checkInDate = formatter.format(calendarIn.getTime());
		checkOutDate = formatter.format(calendarOut.getTime());

		try {
			// Get City Geocode Json from google APi
			googleGeocode = getCityGeoCodeByName(searchObject.getCityName());
			
			Instant start = Instant.now();
			// Get Hotels results
			amadeusResponse = AmadeusHotelsApiAccess.getHotelOffers(checkInDate, checkOutDate, searchObject.getRadius(),
					searchObject.getPrice(), String.valueOf(googleGeocode.lat), String.valueOf(googleGeocode.lng));
			System.out.println(Duration.between(Instant.now(),start).getSeconds());

			// Get Offers from Hotels results
			List<SearchResponseModel> searchResponseModels = new ArrayList<SearchResponseModel>();

			for (HotelOffer hotelOffer : amadeusResponse) {
				HotelOffer.Hotel hotel = hotelOffer.getHotel();
				
				String hotelName = hotel.getName();
			
				AddressType hotelAddress = hotel.getAddress();
				
				HotelContact hotelContacts = hotel.getContact();
				if (hotelAddress == null || hotelContacts == null || hotelName.isEmpty() || hotelName == null) {
					continue;
				}
				for (Offer offer : hotelOffer.getOffers()) {
					// Ignore offer if price is empty or null
					if (offer.getPrice().getTotal() == null || offer.getPrice().getTotal().isEmpty() )  
						continue;
					
					// Create response model which will be send to the client
					SearchResponseModel offerResponse = new SearchResponseModel();
					offerResponse.setRoomPrice(offer.getPrice().getTotal());
					offerResponse.setChekInDate(checkInDate);
					offerResponse.setCheckOutDate(checkOutDate);
					offerResponse.setRadius(searchObject.getRadius());
					offerResponse.setNbPers(searchObject.getNbPers());
					offerResponse.setCity(searchObject.getCityName());
					offerResponse.setCurrency(offer.getPrice().getCurrency());
					AddressModel hotelAdr = new AddressModel();
					if (hotelAddress.getCityName()!= null && !hotelAddress.getCityName().isEmpty()
							&& hotelAddress.getPostalCode() != null	&& !hotelAddress.getPostalCode().isEmpty()
							&& hotelAddress.getLines()!=null && hotelAddress.getLines().length > 0) {
						
						hotelAdr.setCity(hotelAddress.getCityName());
						hotelAdr.setPostal_code(hotelAddress.getPostalCode());
						hotelAdr.setLine1(hotelAddress.getLines()[0]);
					}
					offerResponse.setAddress(hotelAdr);
					offerResponse.setHotelName(hotelName);

					HotelContactModel hotelContact = new HotelContactModel();
					
					hotelContact.setTel(hotelContacts.getPhone());
	
					if (hotelAdr.getCity() != null && !hotelAdr.getCity().isEmpty()) {
						WeatherModel weatherHotel = GetWeatherByCityService.getWeatherByCityName(hotelAdr.getCity());
						offerResponse.setWeather(weatherHotel);
					}
				
					offerResponse.setHotelContacts(hotelContact);
					offerResponse.setPicture(api.getImage());
					//System.out.println(offerResponse.toString());
					searchResponseModels.add(offerResponse);

				}

			}
			// Return List of offers Models ready to be sent to the client
			return gson.toJson(searchResponseModels);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return null;
	}

	// Get City Geocode Json from google APi
	private LatLng getCityGeoCodeByName(String cityName) {
		return GoogleMapApiAccess.getCityGeoCodeByCityName(cityName);
	}

	private StringBuffer getHotelInformationsById(String hotelId, String checkInDate, String checkOutDate) {
		try {
			return AmadeusHotelsInformationApiAcess.GetResponseFromAPI(
					AmadeusHotelsInformationApiAcess.getHotelInfoById(hotelId, checkInDate, checkOutDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
