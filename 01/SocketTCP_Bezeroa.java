package unieibar;


import java.net.*;

public class SocketTCP_Bezeroa {

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = 6000;

		//Socket-a ireki
		Socket bezeroa = new Socket(host, port);

		InetAddress inAddr = bezeroa.getInetAddress();
		System.out.println("bezeroa.getLocalPort() dela lokalekoa: " + bezeroa.getLocalPort());
		System.out.println("bezeroa.getPort() dela urrutikoa: " + bezeroa.getPort());
		System.out.println("inAddr.getHostName().toString() dela urrutiko zerbitzaria: " + inAddr.getHostName().toString());
		System.out.println("inAddr.getHostAddress().toString() dela urrutiko zerbitzariaren IP: " + inAddr.getHostAddress().toString()); // IP HOST REMOTO
		Thread.sleep(2000);
		bezeroa.close(); // socket-a itxi

	}

}
