package unieibar;
import java.net.*;
public class UDPBezeroa { 
    public static void main(String[] args) { 
        final int serverPort = 12345;    
        try {
            DatagramSocket socket = new DatagramSocket();
    
            InetAddress serverAddress = InetAddress.getByName("localhost");//Zerbitzaria lokalki balego
            String message = "Hello, server!";
            byte[] data = message.getBytes();
    
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
            socket.send(packet);
    
            // Zerbitzairaren erantzuna jasotzea
            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);
    
            String serverResponse = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server says: " + serverResponse);
    
            // Itxi socketa bukatzean
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}