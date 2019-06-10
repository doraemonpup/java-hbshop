package shop.local.server;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import shop.local.domain.ShopVerwaltung;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.interfaces.EShopInterface;



public class ShopServer { 					
	
	public final static int DEFAULT_PORT = 6789;

	protected int port;
	protected ServerSocket serverSocket;
	private EShopInterface esi; 

	
	/**
	 * Konstruktor zur Erzeugung des Bibliotheksservers.
	 * 
	 * @param port Portnummer, auf der auf Verbindungen gewartet werden soll
	 * (wenn 0, wird Default-Port verwendet)
	 * @throws MitarbeiterExistiertBereitsException 
	 * @throws ProduktExistiertBereitsException 
	 */
	public ShopServer(int port) throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException {
		
		esi = new ShopVerwaltung("myshop"); 
		
		if (port == 0)
			port = DEFAULT_PORT;
		this.port = port;
		
		try {
			// Server-Socket anlegen
			serverSocket = new ServerSocket(port);
			
			// Serverdaten ausgeben
			InetAddress ia = InetAddress.getLocalHost();
			System.out.println("Host: " + ia.getHostName());
			System.out.println("Server: " + ia.getHostAddress()	+ " Port: " + port);
			System.out.println("Waiting for connections...\n");
		} catch (IOException e) {
			fail(e, "Eine Ausnahme trat beim Anlegen des Server-Sockets auf");
		}

		acceptClientConnectRequests();
	}

	/**
	 * Methode zur Entgegennahme von Verbindungsw�nschen durch Clients.
	 * Die Methode fragt wiederholt ab, ob Verbindungsanfragen vorliegen
	 * und erzeugt dann jeweils ein ShopClientRequestProcessor-Objekt mit dem 
	 * fuer diese Verbindung erzeugten Client-Socket.
	 */
	public void acceptClientConnectRequests() {

		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				ShopClientRequestProcessor c = new ShopClientRequestProcessor(clientSocket, esi);
				Thread t = new Thread(c);
				t.start();
			}
		} catch (IOException e) {
			fail(e, "Fehler w�hrend des Lauschens auf Verbindungen");
		}
	}


	/**
	 * main()-Methode zum Starten des Servers
	 * 
	 * @param args kann optional Portnummer enthalten, auf der Verbindungen entgegengenommen werden sollen
	 * @throws MitarbeiterExistiertBereitsException 
	 * @throws ProduktExistiertBereitsException 
	 */
	public static void main(String[] args) throws ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException {
		int port = 0;
		if (args.length == 1) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				port = 0;
			}
		}
		try {
			new ShopServer(port);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e, " - ShopServer-Erzeugung");
		}
	}
	
	// Standard-Exit im Fehlerfall:
	private static void fail(Exception e, String msg) {
		System.err.println(msg + ": " + e);
		System.exit(1);
	}
}


