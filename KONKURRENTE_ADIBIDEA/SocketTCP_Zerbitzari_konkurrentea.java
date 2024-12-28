package unieibar;

import java.io.IOException;
import java.net.*;

public class SocketTCP_Zerbitzari_konkurrentea {

	public static void main(String[] args) {
		ServerSocket zerbitzaria;
		Socket bezeroa;
		HariZerbitzaria hariZerbitzaria;
		int port = 6000;
		try {
			zerbitzaria = new ServerSocket(port);
			InetAddress host = InetAddress.getLocalHost(); //zerbitzariaren IP ezagutzeko eta kideari emateko
			System.out.println("MAIN Zerbitzaria martxan " + host.getHostAddress() + ":" + port);
			
			while(true)
			{
				bezeroa = new Socket();
				bezeroa = zerbitzaria.accept(); // bezeroaren zain
				hariZerbitzaria = new HariZerbitzaria(bezeroa);
				hariZerbitzaria.start(); // haria martxan jarri eta 'while true' begiztan jarraitu
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
