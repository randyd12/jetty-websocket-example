package com.javacodegeeks.snippets.enterprise.jettywebsocket.jsr356.server;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/jsr356toUpper")
public class ToUpper356Socket {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("WebSocket opened: " + session.getId());
	}

	@OnMessage
	public void onMessage(String txt, Session session) throws IOException {
		System.out.println("Message received x: " + txt);
		session.getBasicRemote().sendText(txt.toUpperCase());

		//session.getAsyncRemote().sendText(txt.toUpperCase());
		//session.getBasicRemote().sendText("echoe" + txt.toUpperCase());
		try {
			Thread.sleep(1000);
			System.out.println("times up: " + txt);
			session.getBasicRemote().sendText("echoe" + txt.toUpperCase());
		}
		catch (Exception ex)
		{
			System.out.println("ex: " + ex.getMessage());
		}
	}

	@OnClose
	public void onClose(CloseReason reason, Session session) {
		System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());

	}
}
