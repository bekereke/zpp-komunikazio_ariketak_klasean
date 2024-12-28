package unieibar;

import java.io.*;
import java.net.*;

public class TCP_Zerbitzari_adina {
  public static void main(String[] args) {
    final int port = 12345;
        
    try {
      InetAddress localHost = InetAddress.getLocalHost(); 
      System.out.println("'TCP_Zerbitzari_adina' zain " 
        + localHost.getHostAddress() + ":" + port);
      ServerSocket serverSocket = new ServerSocket(port); //socket() + bind()
      while (true) {  
        Socket clientSocket = serverSocket.accept(); //listen() + accept()
        System.out.println("Bezeroa konektatuta: " 
            + clientSocket.getInetAddress());
      
        // Create input and output streams for the client
        InputStream inputStream = clientSocket.getInputStream();
        DataInputStream dataInput = new DataInputStream(inputStream);
        String izena = dataInput.readUTF(); //BIDALITAKO ORDENAN JASO DATUAK
        int adina = dataInput.readInt();

        System.out.println("Eskaria: " + izena + " eta " + adina);

        // Send a response to the client
        OutputStream outputStream = clientSocket.getOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(outputStream);
        String erantzuna = izena + "(e)k " + adina + " urte ditu,";
        if (adina < 18){
          erantzuna += " adingabea da.";
        } else {
          erantzuna += " adinez nagusia da." ;
        }
        
        dataOutput.writeUTF(erantzuna); // eskaera_bete + write()
        dataOutput.flush();
        System.out.println("Erantzuna: " + erantzuna);

        // Close the client connection
        clientSocket.close();
        
      }
      //serverSocket.close();
      
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}