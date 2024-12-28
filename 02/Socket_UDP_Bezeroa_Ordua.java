package unieibar;

import java.io.*;
import java.net.*;

public class Socket_UDP_Bezeroa_Ordua {
	
	private static String ipZerbitzaria = "localhost";
	private static int portZerbitzaria = 5555;

	public static void main(String[] args) {
		System.out.println("'Socket_UDP_Bezeroa_Ordua' MARTXAN!");
		try
		{
			System.out.println("DatagramSocket sortzen...");
			DatagramSocket datagramSocket = new DatagramSocket();
			
			System.out.println("Zerbitzariari eskakizuna bidaltzen...");
			String mezuaString = "ordua";
			byte[] mezua = mezuaString.getBytes();
			
			InetAddress zerbitzariaAddr = InetAddress.getByName(ipZerbitzaria);
			DatagramPacket dtgBidali = new DatagramPacket(mezua, mezua.length, zerbitzariaAddr, portZerbitzaria);
			datagramSocket.send(dtgBidali);
			System.out.println("Eskakizuna bidalita!");
			
			System.out.println("Erantzuna jasotzen...");		
			byte[] erantzuna = new byte[200];
			DatagramPacket dtgJaso = new DatagramPacket(erantzuna, erantzuna.length);
			datagramSocket.receive(dtgJaso);
			String erantzunaString = new String(erantzuna);
			System.out.println("Erantzuna jasota:" + erantzunaString);
			
			System.out.println("IP zerbitzaria: " + dtgBidali.getSocketAddress());
			System.out.println("Port zerbitzaria: " + dtgBidali.getPort());
			System.out.println("IP bezeroa (ni): " + datagramSocket.getLocalSocketAddress());
			System.out.println("Port bezeroa (ni): " + datagramSocket.getLocalPort());
			
			System.out.println("DatagramSocket isten...");
			datagramSocket.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		System.out.println("'Socket_UDP_Bezeroa_Ordua' BUKATUTA.");
	}

}
