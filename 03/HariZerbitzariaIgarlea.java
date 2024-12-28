package unieibar;

import java.io.*;
import java.net.*;
import java.util.Random;

public class HariZerbitzariaIgarlea implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private Random random;

    public HariZerbitzariaIgarlea(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int asmatzekoa = random.nextInt(100) + 1;
                int saiakerak = 6;
                boolean asmatuta = false;

                out.println("Ongietorri ezetz zenbakia igarri jolasera! 6 saiakera dituzu 1 eta 100 bitarteko zenbakia asmatzeko. ");

                while (saiakerak > 0 && !asmatuta) {
                    out.println("Ezetz igarri zenbakia: ");
                    int apustua = Integer.parseInt(in.readLine());

                    if (apustua < asmatzekoa) {
                        out.println("handiagoa");
                    } else if (apustua > asmatzekoa) {
                        out.println("txikiagoa");
                    } else {
                        out.println("Zuzen! Asmatu duzu zenbakia.");
                        asmatuta = true;
                    }
                    saiakerak--;
                }

                if (!asmatuta) {
                    out.println("Tamalez saiakera guztiak huts egin dituzu. Zenbakia " + asmatzekoa + " zen.");
                }

                out.println("Berriz jolastu nahi al duzu? (bai/ez)");
                String erantzuna = in.readLine();
                if (!erantzuna.equalsIgnoreCase("bai")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                System.out.println("Error closing client connection: " + e.getMessage());
            }
        }
    }
}