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
	static {
		sessions = new HashMap<>();
	}

	@OnOpen
	public void handleOpen(@PathParam("clientId") String clientId, Session session) {
		
//			for (Session s : sessions) {
//				if (s.isOpen()) {
//					
//					s.getBasicRemote().sendText(text);
//				}
			Abonne abonne = AbonneDAO.getAbonneById(Integer.valueOf(clientId));
			JSONObject json = null;
			if (abonne != null) {
				json = new JSONObject();
				json.put("type", "CONNEXION");
				json.put("content", "");
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
		}else {// case broadcast
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
	//	return json.toString();
	}

	@OnClose
	public void handleClose() {

	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();

	}
}
