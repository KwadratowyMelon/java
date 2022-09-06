package util;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Config {
	//Klasa konfiguracyjna
    public static final int PORT = 9000;
    public static final int BUFFER_SIZE = 1024;
    public static final String BROADCAST_ADDRESS;
    static {
    	try {
    		InetAddress localHost=InetAddress.getLocalHost();
    		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
    		InterfaceAddress address = networkInterface.getInterfaceAddresses().get(0);
    		BROADCAST_ADDRESS = address.getBroadcast().getHostAddress();
    	} catch (UnknownHostException | SocketException e) {
    		throw new RuntimeException(e);
    	}
    }
    
}
