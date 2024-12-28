package unieibar;

import java.io.*;
import java.net.*;

public class TCPZerbitzari_igarlea {
    private static final int PORT = 12345;
    private static final int MAX_CLIENTS = 10;
    private static Thread[] igarleak = new Thread[MAX_CLIENTS];

    public static void main(String[] args) throws IOException {
        InetAddress localHost = InetAddress.getLocalHost();
        ServerSocket zerbitzaria = new ServerSocket(PORT);
        System.out.println("'TCPZerbitzaria_igarlea' zain " 
        + localHost.getHostAddress() + ":" + PORT);
        
        int igarleZenb = 0;
        try {
            while (true) {
                Socket bezeroa = zerbitzaria.accept();
                System.out.println("Bezeroa konektatuta: " + bezeroa.getInetAddress());
                if (igarleZenb == MAX_CLIENTS) {
                    System.out.println("Kopuru maximoa lortu da. Ezin dira gehiago konektatu.");
                    bezeroa.close();
                    continue;
                }
                igarleak[igarleZenb] = new Thread(new HariZerbitzariaIgarlea(bezeroa));
                igarleak[igarleZenb].start();
                igarleZenb++;
            }
        } finally {
            zerbitzaria.close();
        }
    }
}