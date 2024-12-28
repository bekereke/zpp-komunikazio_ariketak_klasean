package unieibar;

import java.io.*;
import java.net.*;
import java.util.Random;

public class UDP_Zerbitzari_zenbaki {
    public static void main(String[] args) {
        final int port = 12345;

        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Zerbitzaria zain " + port + " portuan");

            byte[] buffer = new byte[1024];

            //while (true) {
                DatagramPacket dtgJaso = new DatagramPacket(buffer, buffer.length);
                socket.receive(dtgJaso);

                int balioa;

                try (DataInputStream dataInput = new DataInputStream(new ByteArrayInputStream(dtgJaso.getData()))) {
                    balioa = dataInput.readInt();
                }
                System.out.println("Jasotakoa: " + balioa);

                // Process the received data, if needed
                Random ausaz = new Random();
                balioa += ausaz.nextInt(100);

                // Respond to the client

                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                DataOutputStream dataOutput = new DataOutputStream(byteStream);//Enkapsulatuta
                dataOutput.writeInt(balioa);

                byte[] datuak = byteStream.toByteArray();
                DatagramPacket responsePacket = new DatagramPacket(datuak, datuak.length,
                    dtgJaso.getAddress(), dtgJaso.getPort());
                socket.send(responsePacket);
                System.out.println("Itzulitakoa: " + balioa);
            //}
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}