package unieibar;

import java.io.*;
import java.net.*;
import java.util.Random;

public class TCP_Zerbitzari_zenbaki {
  public static void main(String[] args) {
    final int port = 12345;
        
    try {
      InetAddress localHost = InetAddress.getLocalHost(); 
      System.out.println("Zerbitzaria zain " 
        + localHost.getHostAddress() + ":" + port);
      ServerSocket serverSocket = new ServerSocket(port); //socket() + bind()
      //while (true) {  
        Socket clientSocket = serverSocket.accept(); //listen() + accept()
        System.out.println("Bezeroa konektatuta: " 
            + clientSocket.getInetAddress());
      
        // Create input and output streams for the client
        InputStream inputStream = clientSocket.getInputStream();
        DataInputStream dataInput = new DataInputStream(inputStream);
        int balioa = dataInput.readInt();

        System.out.println("Jasotakoa: " + balioa);
        Random ausaz = new Random();
        balioa += ausaz.nextInt(100);

        // Send a response to the client
        OutputStream outputStream = clientSocket.getOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(outputStream);
        dataOutput.writeInt(balioa); // eskaera_bete + write()
        dataOutput.flush();
        System.out.println("Itzulitakoa: " + balioa);

        // Close the client connection
        clientSocket.close();
        
      //}
      serverSocket.close();
      
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}