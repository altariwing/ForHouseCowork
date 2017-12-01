package com.msg.controller;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

class ChatRoom{
	
	public static Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
	
	public static Map<String, Msg> room = Collections.synchronizedMap(new HashMap<>());
}	

@ServerEndpoint("/Msg/{myName}/{myRoom}")
public class Msg {
	private String userName;
	private String roomNumber;
	private Session session;
		
	@OnOpen
	public void onOpen(@PathParam("myName") String myName, @PathParam("myRoom") String myRoom, Session userSession) throws IOException {
		System.out.print(userSession.getId() + ": 已連線");
		System.out.print(myRoom + ": 房號");
		System.out.println(myName + ": 已連線");
		
		
		this.roomNumber = myRoom;
		this.session = userSession;
		ChatRoom.map.put(myName, myRoom);
		ChatRoom.room.put(myName, this);
		
//		String str="";
//		Gson gson = new Gson();
//		Message msg = new Message("123","???");
//		str = gson.toJson(msg);
////		System.out.println(msg.userName);
//		userSession.getAsyncRemote().sendText(str);
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) throws IOException {
		
//		Gson gson = new Gson();
//		Message msg = gson.fromJson(message, Message.class);
//		System.out.println(msg.userName);
		
		
		for(String one : ChatRoom.map.keySet()){
			
			if(ChatRoom.map.get(one).equals(roomNumber)){
				
				
				if(ChatRoom.room.get(one)!= null && ChatRoom.room.get(one).session.isOpen()){
					
					ChatRoom.room.get(one).session.getAsyncRemote().sendText(message);
				}
				
				
			}			
		}
		
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		System.out.println(e.getMessage());
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		ChatRoom.room.remove(userName);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}
}


class Message{
	String userName;
	String message;
	Message(String userName, String message){
		this.userName = userName;
		this.message = message;
	}
}