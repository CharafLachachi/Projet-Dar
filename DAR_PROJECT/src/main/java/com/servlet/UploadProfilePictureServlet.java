package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.result.internal.OutputsImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Base64;
import java.lang.Object;
import org.apache.commons.lang3.ArrayUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.services.ShowProfileService;
import com.utils.PasrseJsonUtility;

/**
 * Servlet implementation class UploadProfilePictureServlet
 */
@WebServlet("/UploadProfilePicture")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class UploadProfilePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadProfilePictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	/*	System.out.println("Upload Pic request");
		String user_id = request.getParameter("user_id");
		System.out.println("User id : "+user_id);
		
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		
		System.out.println("available"+is.available());
	//	byte b[] = new byte[is.available()];
		int x;
		
		File image=  new File(user_id+".jpg");
	    FileOutputStream fos = new FileOutputStream(image);
	    List<Byte> img = new ArrayList<Byte>();
		while((x=is.read())!=-1) {
			fos.write(x);
			os.write(x);
		}
		
        fos.flush();
        fos.close();
        os.flush();
        System.out.println("done writing file");
    	//response.getWriter().append("Served at: ").append(request.getContextPath());
    */	//philippe tribuché
		
		InputStream  is ;
		OutputStream os ;
		
		System.out.println("Upload Pic request");
		String user_id = request.getParameter("user_id");
		System.out.println("User id : "+user_id);
		
		if(user_id == null) {
			
			StringBuffer jb = PasrseJsonUtility.getRequestJson(request);
			Gson gson = new Gson();
			System.out.println("json => "+jb.toString());
			JsonObject jsonObject = gson.fromJson(jb.toString(), JsonObject.class);
			user_id = jsonObject.get("user_id").getAsString();
			
			String img = jsonObject.get("image").getAsString();
			//byte[] img_ = android.util.Base64.decode(img, 0);
		    byte[] img_ = Base64.getDecoder().decode(img); 
			ShowProfileService.uploadProfilePicture(user_id, img_);
			byte[] my_image = ShowProfileService.getProfilePicture(user_id);
			os = response.getOutputStream();
			os.write(my_image);
			os.flush();
			return;
		}
		
		is = request.getInputStream();
		os = response.getOutputStream();
		
		byte b[] = new byte[1];
	    List<Byte> img = new ArrayList<Byte>();
		while(is.read(b)!= -1) {
			img.add(b[0]);
		}
		Byte[] bytes = img.toArray(new Byte[img.size()]);
		byte[] pic = new byte[bytes.length];
		for(int i=0;i <bytes.length;i++)
			pic[i] = bytes[i].byteValue();
		
		ShowProfileService.uploadProfilePicture(user_id, pic);
		byte[] my_image = ShowProfileService.getProfilePicture(user_id);
        os.write(my_image);
		os.flush();
        System.out.println("done writing file");
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
