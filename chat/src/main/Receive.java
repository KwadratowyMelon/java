package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import util.ChatUsers;
import util.Config;
import util.User;
import util.Util;

public class Receive implements Runnable {

	@Override
	public void run() {
		MulticastSocket r=ChatUsers.getSocket();
		
		try {
				r.setSoTimeout(200);
			do {
				Receive.receive(r);
			} while(!ChatUsers.isFinish());
		} catch (Exception e) {
			e.printStackTrace();
		}		    
		System.out.println("End of receive thread");
	}
	
	
	static void receive(DatagramSocket r ) throws Exception {		
	    // r.setSoTimeout(1000);
	    DatagramPacket response = new DatagramPacket(new byte[Config.BUFFER_SIZE], Config.BUFFER_SIZE);
	    try{
	        r.receive(response);
	        String responseMessage=new String(response.getData(), 0,  response.getLength(), "utf8");	        
	        String address=response.getAddress().getHostAddress();
	        // System.out.println("<"+address+">"+"<"+responseMessage+">");
	        // if(!address.equals(ChatUsers.getUser().getAdress())) {
		        if (responseMessage.contains("Broadcast")) {
		        	String login = responseMessage.substring(("Broadcast" + ": ").length());
		        	String userLogin = ChatUsers.getUser().getLogin();
		        	if(!userLogin.contentEquals("") && !userLogin.equals(login)) {
		        		Util.sent(Config.BROADCAST_ADDRESS, Config.PORT,"Connected: " + ChatUsers.getUser().getLogin());
		        	}
		        } else if (responseMessage.contains("Connected")){
		        	String login=responseMessage.substring("Connected: ".length());
		        	if(ChatUsers.isNew(login)) {
		        		User user=new User(address,Config.PORT,login);
		        		ChatUsers.add(user);
		        	}
		        } else if (!ChatUsers.getUser().getLogin().equals("") && !responseMessage.contains(ChatUsers.getUser().getLogin()+":")) {
		        	// *** Integracja z GUI, wyswietlenie wiadomoœci od odbiorcy
		        	MainFrame.printMessage(responseMessage);
		        }
	        // }
	        
	    }catch (SocketTimeoutException e){
	       // System.out.println("Nie otrzyma³em odpowiedzi");
	    }
	}

	
}
