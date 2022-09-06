package util;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Util {
	
	//adres-String,port-int,wiadomoœæ-String
	//Metoda do wysy³ania 
	static public void sent(String addres,int port,String message)  throws Exception {
		MulticastSocket s = ChatUsers.getSocket();	
	    DatagramPacket packet = new DatagramPacket(message.getBytes("utf8"), message.length() );
	    packet.setPort(port);
	    packet.setAddress(InetAddress.getByName(addres));
	    
	    s.send(packet);
	   // s.close();
	}
}
