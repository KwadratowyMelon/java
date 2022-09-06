package main;

public class Main1  {
	
	public static void main(String[] args) throws Exception{
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
	 }
	
}
