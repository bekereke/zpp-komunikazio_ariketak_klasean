package unieibar;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Socket_UDP_Zerbitzaria_Ordua {
	
	private static String ipZerbitzaria = "localhost";
	private static int portZerbitzaria = 5555;

	public static void main(String[] args) {
		System.out.println("'Socket_UDP_Zerbitzaria_Ordua' MARTXAN!");
		
		DatagramSocket datagramSocket = null;
		
		try
		{
			InetSocketAddress addr = new InetSocketAddress(ipZerbitzaria, portZerbitzaria);
			datagramSocket = new DatagramSocket(addr);
		}
		catch (SocketException ex)
		{
			ex.printStackTrace();
		}
		
		while (null != datagramSocket)
		{
			try
			{
				System.out.println("Mezuaren zai...");
				
				byte[] buffer = new byte[5];
				DatagramPacket dtgJaso = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(dtgJaso);
				
				String mezua = new String(dtgJaso.getData());
				
				InetAddress bezeroaAddr = dtgJaso.getAddress();
				int bezeroaPort = dtgJaso.getPort();
								
				System.out.println("Mezua jasotako IP = " + bezeroaAddr);
				System.out.println("Mezua jasotako Portua = " + bezeroaPort);
				System.out.println("Mezua = " + mezua);
				
				if (0 == mezua.compareToIgnoreCase("ordua"))
				{
					System.out.println("Erantzuna bidaltzen bezeroari...");
					
					Date d = new Date(System.currentTimeMillis());
					String erantzunaString =  d.toString();
					byte[] erantzuna = erantzunaString.getBytes();
					System.out.println("Erantzuna = " + erantzunaString);
					DatagramPacket dtgBidali = new DatagramPacket(erantzuna, erantzuna.length, bezeroaAddr, bezeroaPort);
					datagramSocket.send(dtgBidali);
					
					System.out.println("Erantzuna bidalita!");
				}
				else
				{
					System.out.println("Jasotako mezua ez dut ulertzen (?)");
				}
				
				// Kontsola garbitzeko bakarrik 
				for (int i=0; i<3; i++)
				{
					System.out.println("");
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		
		System.out.println("'Socket_UDP_Zerbitzaria_Ordua' BUKATUTA.");
	}

}
