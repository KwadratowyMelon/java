package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import util.Config;
import util.Util;

//import util;

public class Main  {
	
	

	public static void main(String[] args) throws Exception{
		//Tworzymy obiekt klasy userAction
		UserAction userAction=new UserAction();
		//w�tek korzystaj�cy na tym co  utworzyli�my 
		Thread userActionThread=new Thread(userAction);
		
		//analogicznie
		Receive receive=new Receive();
		Thread receiveThread=new Thread(receive);
		
		//2 w�tki,w�tek userAction i odbierania
		userActionThread.start();
		receiveThread.start();
	 }
	
}