package unieibar;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

public class SocketTCP_Zerbitzaria_Zenbatzen {

	public static void main(String[] args) throws IOException {
		int port = 6000;
		int n = 0;

		if(args.length == 0)
		{
        	System.out.println("Sartu bezero kopurua, mesedez.");
        	System.exit(0);
    	}
		int n_MAX = Integer.parseInt(args[0]);

		ServerSocket zerbitzaria = new ServerSocket(port);
		System.out.println("Socket TCP ZERBITZARIA " + zerbitzaria.getLocalPort() + " portutik entzuten.");

		for (n = 1; n <= n_MAX; n++)
		{
			System.out.println("\n\nZerbitzaria: bezero bat konektatzeko zain...");
			Socket bezeroa_n = zerbitzaria.accept(); // N. bezeroa
			System.out.println("Zerbitzaria: " + n + ". bezeroa konektatu da! Idatzi egingo diot...");

			// Bezeroari erantzuteko irteera fluxua
			OutputStream os = bezeroa_n.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			// Bezeroari erantzuten diogu
			System.out.println("Zerbitzaria: bezeroari zenbatgarrena den adierazi diot");
			dos.writeInt(n);
			//dos.writeUTF(Integer.toString(n));
			dos.flush();

			System.out.println("Zerbitzaria: bezeroa.IP: " + bezeroa_n.getInetAddress().getHostAddress());
			System.out.println("Zerbitzaria: bezeroa.PORTUA: " + bezeroa_n.getPort());
			System.out.println("Zerbitzaria: konexioa isten...");

			dos.close();
			os.close();
			bezeroa_n.close();
		}

		System.out.println("Socket TCP ZERBITZARIA itzalita. Agur!");
		zerbitzaria.close();
	}

}
