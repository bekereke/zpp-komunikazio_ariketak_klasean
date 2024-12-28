package unieibar;

import java.io.*;
import java.net.*;
import java.util.Random;

public class UDP_Bezero_zenbaki { 
    public static void main(String[] args) { 
        final int serverPort = 12345;    
        try {
            DatagramSocket socket = new DatagramSocket();
    
            InetAddress serverAddress = InetAddress.getByName("localhost");//Zerbitzaria lokalki balego
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream dataOutput = new DataOutputStream(byteStream);//Enkapsulatuta
            
            // Irteera fluxua idatzi
            Random ausaz = new Random();
            int zenbakia = ausaz.nextInt(100);
            dataOutput.writeInt(zenbakia);

            byte[] datuak = byteStream.toByteArray();
            
            DatagramPacket dtgBidali = new DatagramPacket(datuak, datuak.length, serverAddress, serverPort);
            socket.send(dtgBidali);
            System.out.println("Bidalitakoa: " + zenbakia);
            
            // Zerbitzairaren erantzuna jasotzea
            byte[] buffer = new byte[1024];
            DatagramPacket dtgJaso = new DatagramPacket(buffer, buffer.length);
            socket.receive(dtgJaso);
            int balioa;
            try (DataInputStream dataInput = new DataInputStream(new ByteArrayInputStream(dtgJaso.getData()))) {
                balioa = dataInput.readInt();
            }
            
            System.out.println("Jasotakoa: " + balioa);
    
            // Itxi socketa bukatzean
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}