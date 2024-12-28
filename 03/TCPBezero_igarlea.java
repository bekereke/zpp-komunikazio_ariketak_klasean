package unieibar;

import java.io.*;
import java.net.*;

public class TCPBezero_igarlea {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 12345;

        System.out.println("TCP BEZERO IGARLEA martxan... ");

        // Socket-a ireki
        Socket bezeroa = new Socket(host, port);

        // Bezeroaren irteera fluxua zerbitzariari mezuak bidaltzeko
        OutputStream os = bezeroa.getOutputStream();
        PrintWriter pwos = new PrintWriter(os, true);
        // Bezeroaren sarrera fluxua zerbitzaritik mezuak irakurtzeko
        InputStream is = bezeroa.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String zerbitzaritik;
        String bezerotik;

        while ((zerbitzaritik = br.readLine()) != null) {
            System.out.println("Jasotakoa: " + zerbitzaritik);
            if (zerbitzaritik.equals("Berriz jolastu nahi al duzu? (bai/ez)")) {
                bezerotik = stdIn.readLine();
                if (bezerotik != null) {
                    System.out.println("Bidaltzekoa: " + bezerotik);
                    pwos.println(bezerotik);
                    if (bezerotik.equalsIgnoreCase("no")) {
                        break;
                    }
                }
            } else if (zerbitzaritik.equals("Ezetz igarri zenbakia: ")) {
                bezerotik = stdIn.readLine();
                if (bezerotik != null) {
                    System.out.println("Bidaltzekoa: " + bezerotik);
                    pwos.println(bezerotik);
                }
            }
        }

        System.out.println("TCP BEZERO IGARLEA itzalita. Agur!");
        pwos.close();
        br.close();
        bezeroa.close();
    }
}