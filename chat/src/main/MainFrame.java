package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import util.ChatUsers;
import util.Config;
import util.UserChoiceListener;
import util.Util;

//import util;

public class MainFrame extends JFrame  {
	JTextField msgField;
	static JPanel messages;
	public MainFrame() {
		setTitle("Messenger");
		setLayout(new BorderLayout());
		setSize(800, 500);

		JPanel panelLeft = new JPanel(new BorderLayout());
		panelLeft.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		JPanel users = new JPanel(new BorderLayout());
		JLabel usersLabel = new JLabel("Dostêpni u¿ytkownicy:");
		usersLabel.setBorder(new EmptyBorder(new Insets(5, 10, 10, 10)));
		users.add(usersLabel, BorderLayout.NORTH);
		ChatUsers.userList = new JPanel();
		ChatUsers.userList.setLayout(new BoxLayout(ChatUsers.userList, BoxLayout.Y_AXIS));
		users.add(ChatUsers.userList);
		panelLeft.add(users, BorderLayout.NORTH);
		this.add(panelLeft, BorderLayout.LINE_START);
		
		
		JPanel panelMid = new JPanel();
		panelMid.setLayout(new BorderLayout());
		
		messages = new JPanel();
		messages.setLayout(new BoxLayout(messages, BoxLayout.Y_AXIS));
		JScrollPane messagesHolder = new JScrollPane(messages);
		messagesHolder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelMid.add(messagesHolder, BorderLayout.CENTER);
		JPanel msgPanel = new JPanel();
		msgPanel.setLayout(new BorderLayout());
		msgField = new JTextField();
		JButton msgButton = new JButton("Send");
		msgPanel.add(msgField);
		msgPanel.add(msgButton, BorderLayout.LINE_END);
		panelMid.add(msgPanel, BorderLayout.PAGE_END);
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		JLabel userLabel = new JLabel(" ");
		panelTop.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelTop.add(userLabel, BorderLayout.CENTER);
		panelMid.add(panelTop, BorderLayout.PAGE_START);
		this.add(panelMid, BorderLayout.CENTER);
		
		
		ActionListener Send = new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				String message = msgField.getText();
				printOwnMessage(message);
				try {
					Util.sent(Config.BROADCAST_ADDRESS, Config.PORT,ChatUsers.getUser().getLogin() + ": " + message);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		msgButton.addActionListener(Send);

		
	}
	
	public static void printMessage(String text) {
		JLabel message = new JLabel(text);
		messages.add(message);		
		message.setAlignmentX(0);
		messages.add(message);
		messages.revalidate();
	}
	public static void printOwnMessage(String text) {
		JLabel message = new JLabel("Ty: "+text);
		message.setForeground(Color.blue);
		messages.add(message);		
		message.setAlignmentX(0);
		messages.add(message);
		messages.revalidate();
	}
	public static void clearMessages() {
		messages.removeAll();
		messages.revalidate();
	}
	
	public static void main(String[] args) throws Exception{		
		MainFrame frame = new MainFrame();
		String login;
		do {
			login = JOptionPane.showInputDialog(frame, "Podaj login:");
		} while (login.equals(""));
		
		UserAction.login(login);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//Tworzymy obiekt klasy userAction
		UserAction userAction=new UserAction();
		//w¹tek korzystaj¹cy na tym co  utworzyliœmy 
		Thread userActionThread=new Thread(userAction);
		
		//analogicznie
		Receive receive=new Receive();
		Thread receiveThread=new Thread(receive);
		
		//2 w¹tki,w¹tek userAction i odbierania
		userActionThread.start();
		receiveThread.start();
		//ChatUsers.setFinish(true);
	 }
	
}