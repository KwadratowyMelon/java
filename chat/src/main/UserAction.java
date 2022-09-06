package main;

import java.util.Scanner;

import util.ChatUsers;
import util.Config;
import util.User;
import util.Util;

public class UserAction implements Runnable {

	@Override
	public void run() {	

		connect();

		System.out.println("End of user action thread");
	}
	
	static void login(String login)
	{
		ChatUsers.getUser().setLogin(login);
	}
	
	static void connect() 
	{
		
		String message="";
		try {
			ChatUsers.clear();
			do {
				Util.sent(Config.BROADCAST_ADDRESS, Config.PORT,"Broadcast: "+ChatUsers.getUser().getLogin());
				Thread.sleep(400);
				if (ChatUsers.size()==0) {
					System.out.println("No connected users.");				
				}
				ChatUsers.printList();
			
			}while(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
		
}
