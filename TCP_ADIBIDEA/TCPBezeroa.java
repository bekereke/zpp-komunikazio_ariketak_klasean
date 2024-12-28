package unieibar;
import java.io.*;
import java.net.*;

public class TCPBezeroa {
    public static void main(String[] args) {
        final String serverAddress = "localhost";
        final int port = 12345;
        //InetAddress serverAddress = InetAddress.getLocalHost();
        try (Socket socket = new Socket(serverAddress, port)) { //socket() + connect()
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true); 
            // Auto-flushing enabled

            // Send a message to the server
            writer.println("Hello, server!");

            // Receive and print the response from the server
            String serverResponse = reader.readLine();
            System.out.println("Server says: " + serverResponse); 
            socket.close();   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}