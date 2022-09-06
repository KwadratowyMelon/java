package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.MainFrame;

public class UserChoiceListener extends MouseAdapter {
	int id;
	static int selectedId = -1;
	public UserChoiceListener(int id){
		this.id = id;
	}

	public void mouseClicked(MouseEvent e) {
		ChatUsers.setChatUser(ChatUsers.get(id));
		selectedId = id;
		MainFrame.clearMessages();
	}
}
