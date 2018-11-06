package com.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.beans.Abonne;
import com.dao.AbonneDAO;

@ServerEndpoint(value = "/ServerEndpointChat/{clientId}")
public class ServerEndpointChat {

	static Map<String, Session> sessions;
	static Map<Integer, Abonne> users;
	static {
		sessions = new HashMap<>();
		users = new HashMap<>();
	}

	@OnOpen
	public void handleOpen(@PathParam("clientId") String clientId, Session session) {

//			for (Session s : sessions) {
//				if (s.isOpen()) {
//					
//					s.getBasicRemote().sendText(text);
//				}
		// On new connexion send to all connected users th new connected users
		// verify if user is not already connected in case user change tab in browser, he doesn't send disconnect message, but sencond connection message
		if(users.containsKey(Integer.valueOf(clientId))) {
			// close old session
			handleClose(sessions.get(clientId));
		}
			Abonne abonne = AbonneDAO.getAbonneById(Integer.valueOf(clientId));
			JSONObject json = null;
			if (abonne != null) {
				users.put(abonne.getABONNE_id(), abonne);
				json = new JSONObject();
				json.put("type", "CONNEXION");
				json.put("content", abonne.getFirstname() + " " + abonne.getLastname() + " is connected");
				json.put("sender", "server");
				json.put("isBroadcast", false);
				json.put("connectedUserId", clientId);
				json.put("connectedUserName", abonne.getFirstname() + " " + abonne.getLastname());
			}
			synchronized (sessions) {
				for (Map.Entry<String, Session> entry : sessions.entrySet()) {
					Session s = entry.getValue();

					if (s.isOpen() && json != null) {
						try {
							JSONObject json2 = new JSONObject();
							json2.put("type", "CONNEXION");
						synchronized (users) {
							json2.put("content", users.get(Integer.valueOf(entry.getKey())).getFirstname() + " " + users.get(Integer.valueOf(entry.getKey())).getLastname()+ " is connected");
							json2.put("connectedUserName", users.get(Integer.valueOf(entry.getKey())).getFirstname() + " " + users.get(Integer.valueOf(entry.getKey())).getLastname());	
						}
							json2.put("sender", "server");
							json2.put("isBroadcast", false);
							json2.put("connectedUserId", entry.getKey());
							// send to the new connected user all connected users
							session.getBasicRemote().sendText(json2.toString());
							
							// send to connected user the new connected user
							s.getBasicRemote().sendText(json.toString());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				sessions.put(clientId, session);
			}
			System.out.println("opned from" + session.getId() + " id client " + clientId);
		
	}

	@OnMessage
	public void handleMessage(String message) {

		System.out.println(message);
		JSONObject json = new JSONObject(message);
		if (!json.getBoolean("isBroadcast")) {
			Session receiverSession = sessions.get(json.getString("receiver"));
			assert receiverSession != null && receiverSession.isOpen();
			synchronized (receiverSession) {
				try {
					receiverSession.getBasicRemote().sendText(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {// case broadcast
			for (Map.Entry<String, Session> entry : sessions.entrySet()) {
				Session session = entry.getValue();
				try {
					synchronized (session) {
						if (session.isOpen()) {
							session.getBasicRemote().sendText(message);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// return json.toString();
	}

	@OnClose
	public void handleClose(Session session) {
		System.out.println("closed " + session.getId());
	
		String id ="";
		for (Map.Entry<String, Session> entry : sessions.entrySet()) {
			Session s = entry.getValue();
			synchronized (s) {
				if (s.equals(session)) {
					id = entry.getKey();
					break;
				}
			}
		}
		// id_abonnee || user
		assert id != null && !id.isEmpty();
		Abonne abonne = AbonneDAO.getAbonneById(Integer.valueOf(id));
		JSONObject json = null;
		if (abonne != null) {
			json = new JSONObject();
			json.put("type", "DECONNEXION");
			json.put("content", abonne.getFirstname() + " " + abonne.getLastname() + " is deconnected");
			json.put("sender", "server");
			json.put("isBroadcast", false);
			json.put("deconnectedUserId", abonne.getFirstname() + " " + abonne.getLastname());
		}
		// remove the disconneted session
		synchronized (sessions) {

			sessions.entrySet().removeIf(entry -> entry.getValue().getId().equals(session.getId()));
		}
		// reomve the disconnected user
		synchronized (users) {
			users.remove(Integer.valueOf(id));
		}
		// send to others users the disconneted user 
		for (Map.Entry<String, Session> entry : sessions.entrySet()) {
			Session s = entry.getValue();
			synchronized (s) {
				if (s.isOpen()) {
					try {
					
						s.getBasicRemote().sendText(json.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();

	}
}
