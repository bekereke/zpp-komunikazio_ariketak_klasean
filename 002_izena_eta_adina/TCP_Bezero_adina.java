package unieibar;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCP_Bezero_adina {
    public static void main(String[] args) {
        final String serverAddress = "localhost";
        final int port = 12345;
        //InetAddress serverAddress = InetAddress.getLocalHost();
        try (Socket socket = new Socket(serverAddress, port)) { //socket() + connect()
            System.out.println("'TCP_Bezero_adina' MARTXAN!");
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            System.out.println("Mesedez, sakatu izena: ");
            Scanner scanner = new Scanner(System.in);
            String izena = scanner.nextLine();
            System.out.println("Mesedez, sakatu adina: ");
            int adina = scanner.nextInt();

            // Send a message to the server
            DataOutputStream dataOutput = new DataOutputStream(outputStream);
            dataOutput.writeUTF(izena); //BIDALITAKO ORDENAN JASO DATUAK
            dataOutput.flush();
            dataOutput.writeInt(adina); 
            dataOutput.flush(); 

            System.out.println("Eskaera: " + izena + " dut izena eta " + adina + " urte ditut");

            // Receive and print the response from the server
            DataInputStream dataInput = new DataInputStream(inputStream);
            String erantzuna = dataInput.readUTF();
    
            System.out.println("Erantzuna: " + erantzuna);

            socket.close();   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}