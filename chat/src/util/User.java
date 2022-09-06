package util;

public class User {
	String adress;
	int port;
	String login;
	
	public User(String adress, int port, String login) {
		super();
		this.adress = adress;
		this.port = port;
		this.login = login;
	}
	
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "User [adress=" + adress + ", port=" + port + ", login=" + login + "]";
	}
	
	
	
}
