package main;

public class Main1  {
	
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
