package com.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.services.SearchHotelsService;
import com.utils.PasrseJsonUtility;

import helpers.models.SearchRequestModel;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "search", urlPatterns = { "/search" })
public class SearchHotelsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected SearchHotelsService searchHotelService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchHotelsServlet() {
        super();
        searchHotelService = new SearchHotelsService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
//		System.err.println(jb.toString());
//		SearchRequestModel searchObject = (new Gson().fromJson(jb.toString(), SearchRequestModel.class));
//		
//		
//		System.out.println(searchObject.getCities().toString());
//		String hoteslOffersResponse = searchHotelService.getHotels(searchObject);
		
		response.getWriter().print("[{\"roomPrice\":\"160.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"Quai Des Belges 35\",\"city\":\"Marseille\",\"postal_code\":\"13001\"},\"city\":\"Marseille, France\",\"hotelName\":\"Hotel Alize Marseille Vieux Port\",\"hotelContacts\":{\"tel\":\"(33) 491336697\",\"email\":\"info@alize-hotel.com\"}},{\"roomPrice\":\"252.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"1 Rue Neuve Saint Martin\",\"city\":\"Marseille\",\"postal_code\":\"13001\"},\"city\":\"Marseille, France\",\"hotelName\":\"Mecure Marseille Centre Vieux Port Htl\",\"hotelContacts\":{\"tel\":\"33/4/96172222\",\"email\":\"H1148@accor.com\",\"url\":\"N\"}},{\"roomPrice\":\"327.35\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"Quai Du Port 18\",\"city\":\"Marseille\",\"postal_code\":\"13002\"},\"city\":\"Marseille, France\",\"hotelName\":\"Residence Du Vieux Port\",\"hotelContacts\":{\"tel\":\"33491919122\",\"email\":\"hotel.residence@wanadoo.fr\"}},{\"roomPrice\":\"295.59\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"49 Avenue Robert Schuman\",\"city\":\"Marseille\",\"postal_code\":\"13002\"},\"city\":\"Marseille, France\",\"hotelName\":\"Best Western Plus Hotel La Joliette\",\"hotelContacts\":{\"tel\":\"33-4-96114949\"}},{\"roomPrice\":\"217.45\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"22 Rue Mazenod\",\"city\":\"Marseille\",\"postal_code\":\"13002\"},\"city\":\"Marseille, France\",\"hotelName\":\"Best Western Hotel Du Mucem\",\"hotelContacts\":{\"tel\":\"4-91-931313\"}},{\"roomPrice\":\"243.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"2, Rue Des Recolettes\",\"city\":\"Marseille\",\"postal_code\":\"13001\"},\"city\":\"Marseille, France\",\"hotelName\":\"Saint Louis\",\"hotelContacts\":{\"tel\":\"(33) 491540274\",\"email\":\"info@hotel-st-louis.com\"}},{\"roomPrice\":\"212.85\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"Avenue Elsa Triolet\",\"city\":\"Marseille\",\"postal_code\":\"13008\"},\"city\":\"Marseille, France\",\"hotelName\":\"Best Western Bonneveine Prado\",\"hotelContacts\":{\"tel\":\"33491229600\",\"email\":\"reservation@marseillehotelprovence.com\"}},{\"roomPrice\":\"300.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"36 Boulevard Charles Livon\",\"city\":\"Marseille\",\"postal_code\":\"13007\"},\"city\":\"Marseille, France\",\"hotelName\":\"Novotel Marseille Vieux Port\",\"hotelContacts\":{\"tel\":\"33/4/96114211\",\"email\":\"H0911@accor.com\",\"url\":\"http://www.novotel.com\"}},{\"roomPrice\":\"318.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"33 Boulevard De Dunkerque\",\"city\":\"Marseille\",\"postal_code\":\"13002\"},\"city\":\"Marseille, France\",\"hotelName\":\"Novotel Suites Marseille Centre\",\"hotelContacts\":{\"tel\":\"33/4/91015650\",\"email\":\"h6189@accor.com\",\"url\":\"https://www.accorhotels.com\"}},{\"roomPrice\":\"474.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"Place Pierre Bertas 4\",\"city\":\"Marseille\",\"postal_code\":\"13001\"},\"city\":\"Marseille, France\",\"hotelName\":\"Citadines Marseille Centre\",\"hotelContacts\":{\"tel\":\"+33496171200\",\"email\":\"MCENTRE@CITADINES.COM\"}},{\"roomPrice\":\"255.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"1, Place Des Marseillaises\",\"city\":\"Marseille\",\"postal_code\":\"13001\"},\"city\":\"Marseille, France\",\"hotelName\":\"Terminus St Charles\",\"hotelContacts\":{\"tel\":\"(33) 491907059\",\"email\":\"contact@hotelterminusmarseille.fr\"}},{\"roomPrice\":\"378.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"28, Boulevard D\\u0027athenes\",\"city\":\"Marseille\",\"postal_code\":\"13001\"},\"city\":\"Marseille, France\",\"hotelName\":\"Balladins Marseille Saint Charles\",\"hotelContacts\":{\"tel\":\"+33 (0)4 96 21 10 01\",\"email\":\"marseille.saintcharles@balladins.com\"}},{\"roomPrice\":\"174.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"Rue Du Theatre Francais 12\",\"city\":\"Marseille\",\"postal_code\":\"13001\"},\"city\":\"Marseille, France\",\"hotelName\":\"Ariana\",\"hotelContacts\":{\"tel\":\"(33) 491479164\",\"email\":\"hotelariana@yahoo.fr\"}},{\"roomPrice\":\"239.97\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"15 Boulevard Maurice Bourdet\",\"city\":\"Marseille\",\"postal_code\":\"13001\"},\"city\":\"Marseille, France\",\"hotelName\":\"Holiday Inn Exp Saint Charles\",\"hotelContacts\":{\"tel\":\"33-0-491995990\"}},{\"roomPrice\":\"262.08\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"15-17 Boulevard De Dunkerque\",\"city\":\"Marseille\",\"postal_code\":\"13002\"},\"city\":\"Marseille, France\",\"hotelName\":\"Ibis Marseille Centre Euromed\",\"hotelContacts\":{\"tel\":\"33/4/91992520\",\"email\":\"H6190@accor.com\",\"url\":\"www.accorhotels.com\"}},{\"roomPrice\":\"62.64\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"31 Rue Du Rouet\",\"city\":\"Marseille\",\"postal_code\":\"13006\"},\"city\":\"Marseille, France\",\"hotelName\":\"Ibis Styles Marseille Castella\",\"hotelContacts\":{\"tel\":\"33/4/91795666\",\"email\":\"H7974@accor.com\"}},{\"roomPrice\":\"239.97\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"103 Avenue Du Prado\",\"city\":\"Marseille\",\"postal_code\":\"13008\"},\"city\":\"Marseille, France\",\"hotelName\":\"Holiday Inn Marseille Ave Du P\",\"hotelContacts\":{\"tel\":\"33-4-91831010\"}},{\"roomPrice\":\"252.00\",\"radius\":15,\"nbPers\":2,\"chekInDate\":\"2018-10-28\",\"checkOutDate\":\"2018-10-31\",\"address\":{\"line1\":\"6, Rue De Cassis\",\"city\":\"Marseille\",\"postal_code\":\"13008\"},\"city\":\"Marseille, France\",\"hotelName\":\"Ibis Marseille Prado Velodrome\",\"hotelContacts\":{\"tel\":\"33/4/91257373\",\"email\":\"H0619@accor.com\",\"url\":\"N\"}}]");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
