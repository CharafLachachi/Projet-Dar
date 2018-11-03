package com.services;

import com.dao.JoinDAO;

public abstract class JoinService {
	public static JoinDAO dao = new JoinDAO();
	
	public static boolean joinPublicationByIds(int PublicationId, int userId) {
		JoinDAO.JoinPublication(PublicationId, userId);
		return true;
	}
}
