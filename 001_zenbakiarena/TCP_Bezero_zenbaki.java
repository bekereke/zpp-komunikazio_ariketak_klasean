package unieibar;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCP_Bezero_zenbaki {
    public static void main(String[] args) {
        final String serverAddress = "localhost";
        final int port = 12345;
        //InetAddress serverAddress = InetAddress.getLocalHost();
        try (Socket socket = new Socket(serverAddress, port)) { //socket() + connect()
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            
            // Send a message to the server
            DataOutputStream dataOutput = new DataOutputStream(outputStream);
            Random ausaz = new Random();
            int zenbakia = ausaz.nextInt(100);
            dataOutput.writeInt(zenbakia); // eskaera_bete + write()
            dataOutput.flush();
            System.out.println("Bidalitakoa: " + zenbakia);

            // Receive and print the response from the server
            DataInputStream dataInput = new DataInputStream(inputStream);
            int balioa = dataInput.readInt();
    
            System.out.println("Jasotakoa: " + balioa);

            socket.close();   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}