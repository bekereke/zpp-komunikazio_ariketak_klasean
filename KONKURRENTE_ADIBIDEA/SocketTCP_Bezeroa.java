package unieibar;

import java.io.*;
import java.net.*;

public class SocketTCP_Bezeroa {

	public static void main(String[] args) throws Exception {
		String host = "localhost";  //ordezteko kideak zerbitzaritik emandako IP sartuta
		int port = 6000;
		
		System.out.println("Socket TCP BEZEROA martxan... ");
		
		//Socketa ireki
		Socket bezeroa = new Socket(host, port);
		
		// Bezeroaren irteera fluxua zerbitzariari mezuak bidaltzeko
	
		// Bezeroaren sarrera fluxua zerbitzaritik mezuak irakurtzeko
		BufferedReader br = new BufferedReader(new InputStreamReader(bezeroa.getInputStream()));
		
		String jasotakoTestua = br.readLine(); // Zerbitzariaren erantzuna jaso
		System.out.println("Erantzuna: " + jasotakoTestua);
		
		//Fluxuak eta socketak itxi
		System.out.println("Konexioak isten...");
		br.close();		
		bezeroa.close();
		System.out.println("Socket TCP BEZEROA itzalita. Agur!");		
	}

}
