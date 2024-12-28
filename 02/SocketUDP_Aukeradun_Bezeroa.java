package unieibar;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketUDP_Aukeradun_Bezeroa { 
    public static void main(String[] args) {         
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");//Zerbitzaria lokalki balego
            int serverPort = 12345;

            System.out.println("'Socket_UDP_Aukeradun_Bezeroa' MARTXAN!");
            System.out.println("(0) PROGRAMATIK IRTEN ETA ZERBITZARIA ITZALI");
            System.out.println("(1) ORDUA GALDETU ZERBITZARIARI");
            System.out.println("(2) DATA GALDETU ZERBITZARIARI");
            System.out.println("(3) KAIXO ESAN ZERBITZARIARI"); 

            int agindua;
            Scanner scanner = new Scanner(System.in);
            do { // while ordez do: behin gutxienez sartu dadin eta zerbitzariari bidaltzeko 0 kasua   
                agindua = scanner.nextInt();

                if (agindua < 0 || agindua > 3){
                    System.out.println("Sartutako aukeraren balioa okerra da. Saiatu berriz.");
                } else {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream dataOutput = new DataOutputStream(byteStream);//Enkapsulatuta
                    // Irteera fluxua idatzi ZENBAKIA BIDALTZEKO
                    dataOutput.writeInt(agindua);
                    
                    byte[] data = byteStream.toByteArray();
                    
                    DatagramPacket bidaliDatagrama = new DatagramPacket(data, data.length, serverAddress, serverPort);
                    socket.send(bidaliDatagrama);
            
                    // Zerbitzairaren erantzuna jasotzea (0 bada erantzun gabe itzali da)
                    if (agindua != 0){
                        byte[] buffer = new byte[1024];
                        DatagramPacket jasoDatagrama = new DatagramPacket(buffer, buffer.length);
                        socket.receive(jasoDatagrama);
                
                        String erantzuna = new String(jasoDatagrama.getData(), 0, jasoDatagrama.getLength());
                        System.out.println("Zerbitzariak dio: " + erantzuna);
                    }
                }

            } while(agindua != 0);
            
            System.out.println("'SocketUDP_Aukeradun_Bezeroa' ITZALTZEN.");  

            scanner.close();
            // Itxi socketa bukatzean
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}