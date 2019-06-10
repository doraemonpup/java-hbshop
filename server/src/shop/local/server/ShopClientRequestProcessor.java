package shop.local.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;
import java.util.Map.Entry;

import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.ProduktNichtGefundenException;
import shop.local.interfaces.EShopInterface;
import shop.local.valueobjects.Produkt;
import shop.local.valueobjects.GewaehltesProdukt;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.valueobjects.Rechnung;

class ShopClientRequestProcessor implements Runnable {

	// Bibliotheksverwaltungsobjekt, das die eigentliche Arbeit machen soll
	private EShopInterface esi; 

	// Datenstrukturen für die Kommunikation
	private Socket clientSocket;
	private BufferedReader in;
	private PrintStream out;

	
	public ShopClientRequestProcessor(Socket socket, EShopInterface shop) {

		esi = shop;
		clientSocket = socket;

		// I/O-Streams initialisieren und ShopClientRequestProcessor-Objekt als Thread starten:
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			try {
				clientSocket.close();
			} catch (IOException e2) {
			}
			System.err.println("Ausnahme bei Bereitstellung des Streams: " + e);
			return;
		}
		
		System.out.println("Verbunden mit " + clientSocket.getInetAddress()
				+ ":" + clientSocket.getPort());
	}

	/**
	 * Methode zur Abwicklung der Kommunikation mit dem Client gemäß dem
	 * vorgebenen Kommunikationsprotokoll.
	 */
	public void run() {

		String input = "";

		// Begrüßungsnachricht an den Client senden
		out.println("Verbindung erfolgreich hergestellt!");

		// Hauptschleife zur wiederholten Abwicklung der Kommunikation
		do {
			try {
				input = in.readLine();
			} catch (Exception e) {
				System.out.println("--->Fehler beim Lesen vom Client (Aktion): ");
				System.out.println(e.getMessage());
				continue;
			}

			// Eingabe bearbeiten:
			if (input == null) {
				// input wird von readLine() auf null gesetzt, wenn Client Verbindung abbricht
				// Einfach behandeln wie ein "quit"
				input = "q";
			}
			//Mitarbeiter-Login:
			else if (input.equals("ml")) {
				try {
					String id = in.readLine();
					String pw = in.readLine();
					Mitarbeiter m = esi.mitarbeiterLogin(id, pw);
					if(m!=null){
						System.out.println("Mitarbeiter-Login: "+m.getMitarbeiterID()+" ("+m.getNachname()+", "+m.getVorname()+")");
						out.println("true");
						out.println(m.getMitarbeiterID());
						out.println(m.getVorname());
						out.println(m.getNachname());
						out.println(m.getEmail());
					}
					else out.println("false");

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			//Produkt auf verfügbarkeit prüfen:
			else if (input.equals("pruefeArtikel")) {/////////
				try {
					String stringID = in.readLine();
					if(esi.pruefeProdukt(stringID)==true){
						out.println("true");
					}
					else out.println("false");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ProduktExistiertBereitsException e) {
					e.printStackTrace();
				}
			} 
			//Produkt einfügen:
			else if (input.equals("fuegeArtikelEin")) {//////
				try {
					String id = in.readLine();
					String name = in.readLine();
					String preis = in.readLine();
					String menge = in.readLine();
					String produktInhalt = in.readLine();
					String type = in.readLine();
					int ID = Integer.parseInt(id);
					float PREIS = Float.parseFloat(preis);
					int MENGE = Integer.parseInt(menge);
					int INHALT = Integer.parseInt(produktInhalt);

					if(esi.fuegeProduktEin(ID, name, PREIS, MENGE, INHALT, type)==true){
						out.println("true");
						esi.schreibeProdukt();
						esi.schreibeProduktLogfile(ID, name, MENGE);
					}
					else out.println("false");

				} catch (IOException e) {
					e.printStackTrace();
				} catch (ProduktExistiertBereitsException e) {
					e.printStackTrace();
				}
			}
			//Mitarbeiter einfügen:
			else if (input.equals("fuegeMitarbeiterEin")) {
				try {
					String id = in.readLine();
					String password = in.readLine();
					String mail = in.readLine();
					String vorname = in.readLine();
					String nachname = in.readLine();
					String wohnort = in.readLine();
					String plz = in.readLine();
					String strasseNr = in.readLine();
					
					int PLZ = Integer.parseInt(plz);			
					if(esi.fuegeMitarbeiterEin(id, password, mail, vorname, nachname, wohnort, PLZ, strasseNr)==true){
						out.println("true");
						esi.schreibeMitarbeiter();
					}
					else out.println("false");

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (input.equals("gibAlleKunden")) {
				Vector<Kunde> k = esi.gibAlleKunden();
				int size = k.size();
				out.println(String.valueOf(size));
				Iterator<Kunde> iter = k.iterator();
				while (iter.hasNext()) {
					Kunde kun = (Kunde) iter.next();
					out.println(String.valueOf( kun.getBenutzername() ));
					out.println(String.valueOf( kun.getVorname() ));
					out.println(String.valueOf( kun.getNachname() ));
					out.println(String.valueOf( kun.getEmail() ));
					out.println(String.valueOf( kun.getWohnort() ));
					out.println(String.valueOf( kun.getPLZ() ));
					out.println(String.valueOf( kun.getStrasseUndHausnummer() ));
					out.println(String.valueOf( kun.getUmsatz() ));
					out.println(String.valueOf( kun.getPassword() ));	
				}
			}

			//Alle Artikel ausgeben:
			else if (input.equals("gibAlleArtikel")) {////////////////
				Vector<Produkt> a = esi.gibAlleProdukt();
				int size = a.size();
				out.println(String.valueOf(size));
				Iterator<Produkt> iter = a.iterator();
				while (iter.hasNext()) {
					Produkt art = (Produkt) iter.next();
					out.println(String.valueOf(art.getID()));
					out.println(art.getProduktName());
					out.println(String.valueOf(art.getProduktPreis()));
					out.println(String.valueOf(art.getProduktMenge()));
					out.println(String.valueOf(art.getProduktinhalt()));
					out.println(art.getType());
				}			
			}
			else if (input.equals("gibAlleMitarbeiter")) {
				Vector<Mitarbeiter> m = esi.gibAlleMitarbeiter();
				int size = m.size();
				out.println(String.valueOf(size));
				Iterator<Mitarbeiter> iter = m.iterator();
				while (iter.hasNext()) {
					Mitarbeiter mit = (Mitarbeiter) iter.next();
					out.println(mit.getMitarbeiterID());
					out.println(mit.getPassword());
					out.println(mit.getEmail());
					out.println(mit.getVorname());
					out.println(mit.getNachname());
					out.println(mit.getWohnort());
					out.println(String.valueOf(mit.getPLZ()));
					out.println(mit.getStrasseUndHausnummer());
				}	
			}

			
			else if (input.equals("aktualisiereArtikelDaten")) {
				try {
					//Bekomme den Produkt
					String produktID = in.readLine();
					String produktName = in.readLine();
					String produktPreis = in.readLine();
					String produktMenge = in.readLine();
					String produktinhalt = in.readLine();
					String type = in.readLine();
					
					int ID = Integer.parseInt(produktID);
					float PREIS = Float.parseFloat(produktPreis);
					int MENGE = Integer.parseInt(produktMenge);
					int INHALT = Integer.parseInt(produktinhalt);
					//Erzeuge das Produkt
					Produkt a = new Produkt(ID, produktName, PREIS, MENGE, INHALT, type);
					//Bekomme alte Werte:
					int altID = Integer.parseInt(in.readLine());
					String altName = in.readLine();
					float altPreis = Float.parseFloat(in.readLine());
					int altLager = Integer.parseInt(in.readLine());
					int altStueck = Integer.parseInt(in.readLine());
					String altTyp = in.readLine();
					//Bekomme neue Werte:
					int neuID = Integer.parseInt(in.readLine());
					String neuName = in.readLine();
					float neuPreis = Float.parseFloat(in.readLine());
					int neuLager = Integer.parseInt(in.readLine());
					int neuStueck = Integer.parseInt(in.readLine());
					String neuTyp = in.readLine();
					//Aktualisiere Produkt
					esi.aktualisiereProduktDaten(a, altID, altName, altPreis, altLager, altStueck, altTyp, neuID, neuName, neuPreis, neuLager, neuStueck, neuTyp);
					if(!(altID==neuID)){
						esi.renameLogfile(altID, neuID);
						esi.aktualisiereLogfile(neuID, altName, neuLager);
					}
					else{
						esi.aktualisiereLogfile(altID, altName, neuLager);
					}
					esi.schreibeProdukt();
	
				} catch (IOException e) {
					e.printStackTrace();
				};
			}
			//Mitarbeiter aktualisieren:
			else if (input.equals("aktualisierenMitarbeiter")) {
				try {
					String id = in.readLine();
					String pw = in.readLine();
					String mail = in.readLine();
					String vor = in.readLine();
					String nach = in.readLine();
					String wohn = in.readLine();
					int plz = Integer.parseInt(in.readLine());
					String strasseNr = in.readLine();
					Mitarbeiter m = new Mitarbeiter(id, pw, mail, vor, nach, wohn, plz, strasseNr);
					//Bekomme alte Werte:
					String altid = in.readLine();
					String altpw = in.readLine();
					String altmail = in.readLine();
					String altvor = in.readLine();
					String altnach = in.readLine();
					String altwohn = in.readLine();
					int altplz = Integer.parseInt(in.readLine());
					String altstrassenr = in.readLine();
					//Bekomme neue Werte:
					String neuid = in.readLine();
					String neupw = in.readLine();
					String neumail = in.readLine();
					String neuvor = in.readLine();
					String neunach = in.readLine();
					String neuwohn = in.readLine();
					int neuplz = Integer.parseInt(in.readLine());
					String neustrassenr = in.readLine();
					//Aktualisiere Artikel
					esi.aktualisiereMitarbeiterDaten(m, neuid, neupw, neuvor, neunach, neumail, neuwohn, neuplz, neustrassenr, altid, altpw, altmail, altnach, altvor, altwohn, altplz, altstrassenr);
					esi.schreibeMitarbeiter();
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
			else if (input.equals("aktualisiereKundenDaten")) {
				String vor;
				try {
					vor = in.readLine();
					String nach = in.readLine();
					String id = in.readLine();
					String pw = in.readLine();
					String mail = in.readLine();
					String wohn = in.readLine();
					int plz = Integer.parseInt(in.readLine());
					String strasseNr = in.readLine();

					Kunde k = new Kunde(id,pw,mail,nach,vor,wohn,plz,strasseNr);

					//alten Werte
					String altid = in.readLine();
					String altpw = in.readLine();
					String altvor = in.readLine();
					String altnach = in.readLine();
					String altmail = in.readLine();
					String altwohn = in.readLine();
					int altplz = Integer.parseInt(in.readLine());
					String altstrassenr = in.readLine();
					
					// neue Werte:
					String neuid = in.readLine();
					String neupw = in.readLine();
					String neuvor = in.readLine();
					String neunach = in.readLine();
					String neumail = in.readLine();
					String neuwohn = in.readLine();
					int neuplz = Integer.parseInt(in.readLine());
					String neustrassenr = in.readLine();
					
					esi.aktualisiereKundenDaten(k, neuid, neupw, neuvor, neunach, neumail, neuwohn, neuplz, neustrassenr, altid, altpw, altmail, altnach, altvor, altwohn, altplz, altstrassenr);
					esi.schreibeKunde();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}


			//Artikel suchen:
			else if (input.equals("sucheArtikel")) {////////////////////
				try {
					String id = in.readLine();
					Produkt a = esi.sucheProdukt(id);
					if(a!=null){
						out.println("true");
						out.println(String.valueOf(a.getID()));
						out.println(a.getProduktName());
						out.println(String.valueOf(a.getProduktPreis()));
						out.println(String.valueOf(a.getProduktMenge()));
						out.println(String.valueOf(a.getProduktinhalt()));
						out.println(a.getType());
					}
					else out.println("false");

				} catch (IOException e) {
					e.printStackTrace();
				} catch (ProduktNichtGefundenException e) {
					e.printStackTrace();
				}
			}
			//Artikel entfernen:
			else if (input.equals("entfernenArtikel")) {////////////////
				//Bekomme Produkt übergeben:
				try {
					String produktID = in.readLine();
					String produktName = in.readLine();
					String produktPreis = in.readLine();
					String produktMenge = in.readLine();
					String produktinhalt = in.readLine();
					String type = in.readLine();
					
					int ID = Integer.parseInt(produktID);
					float PREIS = Float.parseFloat(produktPreis);
					int MENGE = Integer.parseInt(produktMenge);
					int INHALT = Integer.parseInt(produktinhalt);
					//Erzeuge das Produkt
					Produkt a = new Produkt(ID, produktName, PREIS, MENGE, INHALT, type);
					if(	esi.entfernenProdukt(a)==true){
						esi.schreibeProdukt();
						esi.loescheLogfile(ID);
						out.println("true");
					}
					else out.println("false");

				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
			else if (input.equals("sucheMitarbeiter")) {
				try {
					String suchbegriff = in.readLine();
					Mitarbeiter m = esi.sucheMitarbeiter(suchbegriff);
					if(m!=null){
						out.println("true");
						out.println(m.getMitarbeiterID());
						out.println(m.getPassword());
						out.println(m.getEmail());
						out.println(m.getVorname());
						out.println(m.getNachname());
						out.println(m.getWohnort());
						out.println(String.valueOf(m.getPLZ()));
						out.println(m.getStrasseUndHausnummer());

					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			else if (input.equals("entfernenMitarbeiter")) {
				try {
					String id = in.readLine();
					String pw = in.readLine();
					String email = in.readLine();
					String vorname = in.readLine();
					String nachname = in.readLine();
					String wohnort = in.readLine();
					int plz = Integer.parseInt(in.readLine());
					String strasseNr = in.readLine();
					Mitarbeiter m = new Mitarbeiter(id, pw, email, vorname, nachname, wohnort, plz, strasseNr);
				
					boolean b = esi.entferneMitarbeiter(m);
					esi.schreibeMitarbeiter();

					if(b==true){
						out.println("true");
					}
					else out.println("false");

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
			else if (input.equals("sucheNachName")) {
				try {
					String name = in.readLine();
					Vector<Produkt> p = esi.sucheNachName(name);
					if(p !=null){
						out.println("true");
						out.println(p.elementAt(0).getID());
					}
					else out.println("false");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ProduktNichtGefundenException e) {
					e.printStackTrace();
				}	
			}
			else if (input.equals("getDatenAusLogfile")) {
				int produktID;
				try {
					produktID = Integer.parseInt(in.readLine());
					
					LinkedHashMap<String, String> lhm = esi.getDatenAusLogfile(produktID);
					
					if(lhm!=null){
						
						out.println("true");
						out.println(String.valueOf(lhm.size()));
		     		    for ( Entry<String, String> e : lhm.entrySet() ){
		     		    	try {
		     		    		out.println(e.getKey());
		     		    		out.println(e.getValue());
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							}
		     		    }
					}
					else out.println("false");

 
	     		    
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
			
			else if(input.equals("kundenLogin")){
				try {
					String bn = in.readLine();
					String pw = in.readLine();
					Kunde k = esi.KundenLogin(bn, pw);
					if(k!=null){
						out.println("true");
						out.println(k.getBenutzername());
						out.println(k.getPassword());
						out.println(k.getEmail());
						out.println(k.getVorname());
						out.println(k.getNachname());
						out.println(k.getWohnort());
						out.println(k.getPLZ());
						out.println(k.getStrasseUndHausnummer());
						out.println(k.getUmsatz());
					}
					else{
						out.println("false");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			
			
			
			
			
			else if(input.equals("sucheArtikelNachName")){///////////////
				try {
					String suchBegriff = in.readLine();
					
					Vector<Produkt> p = esi.sucheNachName(suchBegriff);
					if(p==null){
						out.println("false");
					}
					else{
						out.println("true");
						int size = p.size();
						out.println(String.valueOf(size));
						Iterator<Produkt> iter = p.iterator();
						while (iter.hasNext()) {
							Produkt art = (Produkt) iter.next();
							out.println(String.valueOf(art.getID()));
							out.println(art.getProduktName());
							out.println(String.valueOf(art.getProduktPreis()));
							out.println(String.valueOf(art.getProduktMenge()));
							out.println(String.valueOf(art.getProduktinhalt()));
							out.println(art.getType());
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ProduktNichtGefundenException e) {
					e.printStackTrace();
				}
				
			}
			
			
			else if(input.equals("fuegeKundeEin")){
				try {
					String bn = in.readLine();
					String pw = in.readLine();
					String email = in.readLine();
					String vorname = in.readLine();
					String nachname = in.readLine();
					
					boolean ok = esi.fuegeKundeEin(bn, pw, email, vorname, nachname);
					if(ok){
						out.println("true");
					}
					else{
						out.println("false");
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			
			else if(input.equals("setKundendaten")){
				try {
					String bn = in.readLine();
					String vorname = in.readLine();
					String nachname = in.readLine();
					String pw = in.readLine();
					String email = in.readLine();
					String strasseNr = in.readLine();
					String plz = in.readLine();
					String wohnort = in.readLine();
					
					
					esi.setKundendaten(bn, vorname, nachname, pw, email, strasseNr, plz,wohnort);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			else if(input.equals("setArtikelMenge")){/////////////////
				try {
					Vector<GewaehltesProdukt> warenkorb = new Vector<GewaehltesProdukt>();
					int size = Integer.parseInt(in.readLine());
					for(int i=0;i<size;i++){
						int id = Integer.parseInt(in.readLine());
						String name = in.readLine();
						float preis = Float.parseFloat(in.readLine());
						int menge = Integer.parseInt(in.readLine());
						int gekaufteanzahl = Integer.parseInt(in.readLine());
						
						warenkorb.add(new GewaehltesProdukt(id,name,preis,menge,gekaufteanzahl));
					}
					try {
						esi.setProduktMenge(warenkorb);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			
			else if(input.equals("berechnenSumme")){
				try {
					Vector<GewaehltesProdukt> warenkorb = new Vector<GewaehltesProdukt>();
					int size = Integer.parseInt(in.readLine());
					float summe = 0;
					for(int i=0;i<size;i++){
						int id = Integer.parseInt(in.readLine());
						String name = in.readLine();
						float preis = Float.parseFloat(in.readLine());
						int menge = Integer.parseInt(in.readLine());
						int gekaufteanzahl = Integer.parseInt(in.readLine());
						
						warenkorb.add(new GewaehltesProdukt(id,name,preis,menge,gekaufteanzahl));
					}
					try {
						summe = esi.berechnenSumme(warenkorb);
					
					} catch (Exception e) {
						e.printStackTrace();
					}
					out.println(summe);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			else if(input.equals("erstellenNeueRechnung")){
				try {
					Vector<GewaehltesProdukt> korb = new Vector<GewaehltesProdukt>();
					int size = Integer.parseInt(in.readLine());
					for(int i=0;i<size;i++){
						int id = Integer.parseInt(in.readLine());
						String name = in.readLine();
						float preis = Float.parseFloat(in.readLine());
						int menge = Integer.parseInt(in.readLine());
						int gekaufteanzahl = Integer.parseInt(in.readLine());
						
						korb.add(new GewaehltesProdukt(id,name,preis,menge,gekaufteanzahl));
					}
					float gesamteSumme = Float.parseFloat(in.readLine());
					
					String bn = in.readLine();
					String pw = in.readLine();
					String email = in.readLine();
					String vorname = in.readLine();
					String nachname = in.readLine();
					String wohnort = in.readLine();
					int plz = Integer.parseInt(in.readLine());
					String strasseNr = in.readLine();
					
					String lvorname = in.readLine();
					String lnachname = in.readLine();
					String lstrasseNr = in.readLine();
					int lplz = Integer.parseInt(in.readLine());
					String lwohnort = in.readLine();
					
					Kunde k = new Kunde(bn,pw,email,vorname,nachname,wohnort,plz,strasseNr,lvorname,lnachname,lstrasseNr,lplz,lwohnort);
					Rechnung rechnung = esi.erstellenNeueRechnung(korb,gesamteSumme,k);
					esi.setUmsatz(k,gesamteSumme);
					
					
				
					out.println(size);
					Iterator<GewaehltesProdukt> iter =  rechnung.getWarenkorb().iterator();
					while (iter.hasNext()) {
						GewaehltesProdukt gp = iter.next();
						esi.aktualisiereLogfile(gp.getID(), gp.getProduktName(), gp.getProduktMenge()-gp.getGekaufteAnzahl());
						out.println(gp.getID());
						out.println(gp.getProduktName());
						out.println(gp.getProduktPreis());
						out.println(gp.getProduktMenge());
						out.println(gp.getGekaufteAnzahl());
					}
					
					 	out.println(rechnung.getSumme());
					    
					    out.println(rechnung.getKundeBesitzer().getBenutzername());
					    out.println(rechnung.getKundeBesitzer().getPassword());
					    out.println(rechnung.getKundeBesitzer().getEmail());
					    out.println(rechnung.getKundeBesitzer().getVorname());
					    out.println(rechnung.getKundeBesitzer().getNachname());
					    out.println(rechnung.getKundeBesitzer().getWohnort());
					    out.println(rechnung.getKundeBesitzer().getPLZ());
					    out.println(rechnung.getKundeBesitzer().getStrasseUndHausnummer());
					    out.println(rechnung.getKundeBesitzer().getLieferungVorname());
					    out.println(rechnung.getKundeBesitzer().getLieferungNachname());
					    out.println(rechnung.getKundeBesitzer().getLieferungStrasseUndHausnummer());
					    out.println(rechnung.getKundeBesitzer().getLieferungPLZ());
					    out.println(rechnung.getKundeBesitzer().getLieferungWohnort());
					
					    out.println(rechnung.getZeit());
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					esi.schreibeProdukt();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}



		} while (!(input.equals("q")));

		// Verbindung wurde vom Client abgebrochen:
		disconnect();		
	}


	
	private void disconnect() {
		try {
			out.println("Tschuess!");
			clientSocket.close();

			System.out.println("Verbindung zu " + clientSocket.getInetAddress()
					+ ":" + clientSocket.getPort() + " durch Client abgebrochen");
		} catch (Exception e) {
			System.out.println("--->Fehler beim Beenden der Verbindung: ");
			System.out.println(e.getMessage());
			out.println("Fehler");
		}
	}
}
