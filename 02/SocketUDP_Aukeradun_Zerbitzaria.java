
package unieibar;

import java.io.*;
import java.net.*;
import java.time.*;

public class SocketUDP_Aukeradun_Zerbitzaria {
    public static void main(String[] args) {
        final int portua = 12345;
        
        System.out.println("'Socket_UDP_Aukeradun_Zerbitzaria' MARTXAN!");
        try {
			DatagramSocket datagramSocket = new DatagramSocket(portua);
            System.out.println("Zerbitzaria zain " + portua + " portuan...");

            byte[] buffer = new byte[1024];

            boolean itxi = false;
            while (!itxi) {
                DatagramPacket dtgJaso = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(dtgJaso);

                InetAddress bezeroaAddr = dtgJaso.getAddress();
				int bezeroaPort = dtgJaso.getPort();

                int agindua;
                try (DataInputStream dataInput = new DataInputStream(new ByteArrayInputStream(dtgJaso.getData()))) {
                    agindua = dataInput.readInt();
                }
                System.out.println("Bezerotik jasotakoa: " + agindua);

                // Process the received data, if needed
                String erantzunaString;
                byte[] erantzuna;
                DatagramPacket dtgBidali = null;
                switch(agindua) {
                    case 0:
                        itxi = true;
                        System.out.println("'SocketUDP_Aukeradun_Zerbitzaria' ITZALTZEN.");
                        datagramSocket.close();
                        break;
                    case 1:
                        // Respond to the client
                        //Date ordua = new Date(System.currentTimeMillis());
                        LocalTime ordua = LocalTime.now(); 
                        erantzunaString =  ordua.toString(); 
                        erantzuna = erantzunaString.getBytes();
                        System.out.println("Erantzuna = " + erantzunaString);
                        dtgBidali = new DatagramPacket(erantzuna, erantzuna.length, 
                                bezeroaAddr, bezeroaPort);
                        datagramSocket.send(dtgBidali);
                        break;
                    case 2: 
                        // Respond to the client
                        LocalDate eguna = LocalDate.now();  
                        erantzunaString =  eguna.toString();
                        erantzuna = erantzunaString.getBytes();
                        System.out.println("Erantzuna = " + erantzunaString);
                        dtgBidali = new DatagramPacket(erantzuna, erantzuna.length, 
                                bezeroaAddr, bezeroaPort);
                        datagramSocket.send(dtgBidali);
                        break;
                    case 3:
                        // Respond to the client
                        erantzunaString = "Kaixo, bezero!";
                        erantzuna = erantzunaString.getBytes();
                        System.out.println("Erantzuna = " + erantzunaString);
                        dtgBidali = new DatagramPacket(erantzuna, erantzuna.length,
                                bezeroaAddr, bezeroaPort);
                        datagramSocket.send(dtgBidali);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}