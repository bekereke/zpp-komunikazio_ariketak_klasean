package unieibar;

import java.io.*;
import java.net.*;

public class HariZerbitzaria extends Thread {
	PrintWriter irteeraFluxua;
	Socket socket = null;
	
	public HariZerbitzaria(Socket s)
	{
		this.socket = s;
		
		try {
			// Sarrera eta irteerako fluxuak sortzen dira
			irteeraFluxua = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		String bidaltzekoTestua = "Kaixo, bezero!";

		System.out.println("HariZerbitzari-" + Thread.currentThread().getName() + ": eskaera prozesatzen...");

		//eskaera bete
		irteeraFluxua.println(bidaltzekoTestua); 
		irteeraFluxua.flush();

		//prozesatzen denbora luzea behar lukeenarena antzezten
		//BAINA ALDIBEREAN HAINBAT BEZERORI ERANTZUNGO DIO MAIN ZERBITZARIAK
		try {
			Thread.sleep(20000); 
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		System.out.println("HariZerbitzari-" + Thread.currentThread().getName() + ": bezeroarekin komunikazioa bukatu da: " + this.socket.toString());
		try {
			irteeraFluxua.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
