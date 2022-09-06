package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChatUsers {
	public static JPanel userList;
	private static ArrayList<User> chatUsers=new ArrayList <User> ();
	private static User user;
	private static User chatUser;
	private static boolean finish=false;
	private static MulticastSocket socket;
	static {
		try {
			user=new User(InetAddress.getLocalHost().getHostAddress(),Config.PORT,"");
		}catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	
	static {
		try {
			socket=new MulticastSocket(Config.PORT);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User userPar) {
		user = userPar;
	}

	public static User getChatUser() {
		return chatUser;
	}

	public static void setChatUser(User chatUserPar) {
		chatUser = chatUserPar;
	}

	
	
	public static boolean isFinish() {
		return finish;
	}

	public static void setFinish(boolean finish) {
		ChatUsers.finish = finish;
	}

	public static void  add(User user)
	{
		chatUsers.add(user);
	}
	
	public static User get(int userNo)
	{
		return chatUsers.get(userNo);
	}
	public static boolean isNew(String login) {
		if(user.getLogin().equals(login))
			return false;
		for(User chatUser : chatUsers){
			if(chatUser.getLogin().equals(login))
				return false;
		}
		return true;
	}
	
	public static MulticastSocket getSocket() {
		return socket;
	}

	public static void setSocket(MulticastSocket socket) {
		ChatUsers.socket = socket;
	}

	public static void clear()
	{
		chatUsers.clear();
	}
	
	public static int size()
	{
		return chatUsers.size();
	}
	
	public static void printList()
	{
		userList.removeAll();
		for (int i=0;i<chatUsers.size();i++)
		{
			JLabel userButton = new JLabel(chatUsers.get(i).getLogin());
			userButton.setAlignmentX(0.5f);
			userButton.addMouseListener(new UserChoiceListener(i));
			userList.add(userButton);
			userList.add(Box.createRigidArea(new Dimension(0,5)));
			if(UserChoiceListener.selectedId == i)
				userButton.setForeground(Color.BLUE);
		}
		userList.revalidate();
	}
	
}
