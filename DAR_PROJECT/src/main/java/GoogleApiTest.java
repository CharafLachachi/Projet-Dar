import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

public class GoogleApiTest {

	public static void main(String[] args) {
//		Gson gson = new  Gson();
//		
//		StringBuffer googleResponse;
	try {
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
		
		Calendar c = javax.xml.bind.DatatypeConverter.parseDateTime("2018-10-12T22:00:00.000Z") ;
		System.err.println(c.getTime());
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		System.err.println(formatter.format(c.getTime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
