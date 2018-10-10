import javax.print.attribute.standard.Media;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.HotelOffer.MediaURI;
import com.amadeus.resources.HotelOffer.Offer;
import com.amadeus.shopping.Hotel;

public class GoogleApiTest {

	public static void main(String[] args) {
//		Gson gson = new  Gson();
//		
//		StringBuffer googleResponse;
//	try {
//			System.err.println(GoogleMapApiAccess.getCityGeoCodeByCityName("Paris, France"));
//			googleResponse = GoogleMapApiAccess.GetResponseFromAPI(
//					GoogleMapApiAccess.
//					getCityGeoCodeByCityName("Paris, France"));
//			
//			JsonObject jsonObject = gson.fromJson(googleResponse.toString(), JsonObject.class);
//			JsonObject result = jsonObject.get("results").getAsJsonObject();
//			JsonObject geometry =  result.getAsJsonObject("geometry");
//			System.out.println(geometry.get("location").getAsString());
//			
//			System.out.println();

//		Calendar c = javax.xml.bind.DatatypeConverter.parseDateTime("2018-10-12T22:00:00.000Z") ;
//		System.err.println(c.getTime());
//		
//		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Amadeus amadeus = Amadeus.builder("YhkwZHS3fGykKY95hO4xX1SEF9NGL18u", "74EAgWWFpZt9ZcFm").build();
		try {
			HotelOffer[] offers = amadeus.shopping.hotelOffers
					.get(Params.with("latitude", "48.864716").and("longitude", "2.349014").and("priceRange=0-", "500")
							.and("checkInDate", "2018-10-12").and("checkOutDate", "2018-10-22").and("radius", "20")
							.and("adults","3"));
			
			for (HotelOffer hotelOffer : offers) {
				
				System.out.println(hotelOffer.getHotel().getHotelId());
				for (Offer offer: hotelOffer.getOffers()) {
					System.out.println(offer.getPrice());
				}

			}
			
			
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
