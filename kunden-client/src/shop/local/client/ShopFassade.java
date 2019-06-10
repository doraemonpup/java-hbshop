package shop.local.client;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.interfaces.EShopInterface;
import shop.local.ui.gui.KundenClient;
import shop.local.valueobjects.Produkt;
import shop.local.valueobjects.GewaehltesProdukt;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.valueobjects.Rechnung;



/**
 * Klasse mit Fassade der Bibliothek auf Client-Seite.
 * Die Klasse stellt die von der GUI erwarteten Methoden zur Verfügung
 * und realisiert (transparent für die GUI) die Kommunikation mit dem 
 * Server.
 * Anmerkung: Auf dem Server wird dann die eigentliche, von der lokalen
 * Bibliotheksversion bekannte Funktionalität implementiert (z.B. Bücher 
 * einfügen und suchen)
 * 
 * @author teschke
 */
public class  ShopFassade implements EShopInterface {

	// Datenstrukturen für die Kommunikation
	private Socket socket = null;
	private BufferedReader in; // server-input stream
	private PrintStream out; // server-output stream
	private Component popup;
	
	
	/**
	 * Konstruktor, der die Verbindung zum Server aufbaut (Socket) und dieser
	 * Grundlage Eingabe- und Ausgabestreams für die Kommunikation mit dem
	 * Server erzeugt.
	 * 
	 * @param host Rechner, auf dem der Server läuft
	 * @param port Port, auf dem der Server auf Verbindungsanfragen warten
	 * @throws IOException
	 */
	public ShopFassade(String host, int port) throws IOException {
		try {
			// Socket-Objekt fuer die Kommunikation mit Host/Port erstellen
			socket = new Socket(host, port);

			// Stream-Objekt fuer Text-I/O ueber Socket erzeugen
			InputStream is = socket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			out = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			JOptionPane successMessage = new JOptionPane();
	    	successMessage.setSize(400,300);
	    	JOptionPane.showMessageDialog(popup, "Verbindung zum Server fehlgeschlagen!","Error", JOptionPane.ERROR_MESSAGE);
						// Wenn im "try"-Block Fehler auftreten, dann Socket schließen:
			if (socket != null)
				socket.close();
//			System.err.println("Socket geschlossen");
			System.exit(0);
		}
		
		// Verbindung erfolgreich hergestellt: IP-Adresse und Port ausgeben
		System.out.println("Connection established!");
		KundenClient.status=1;
    	
		String message = in.readLine();
		System.out.println(message);
	}

	/**
	 * Methode, die eine Liste aller im Bestand befindlichen Bücher zurückgibt.
	 * 
	 * @return Liste aller Bücher im Bestand der Bibliothek
	 */
	
	public void disconnect() throws IOException {
		out.println("q");
		String antwort = "Fehler";
		try {
			antwort = in.readLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
		System.out.println(antwort);
	}



	@Override
	public void aktualisiereArtikelDaten(Produkt a, int altID, String altName,
			float altPreis, int altLager, int altStueck, String altTyp,
			int neuID, String neuName, float neuPreis, int neuLager,
			int neuStueck, String neuTyp) {
		
		out.println("aktualisiereArtikelDaten");
		//Übertrage Produkt p:
		out.println(String.valueOf(a.getID()));
		out.println(String.valueOf(a.getProduktName()));
		out.println(String.valueOf(a.getProduktPreis()));
		out.println(String.valueOf(a.getProduktMenge()));
		out.println(String.valueOf(a.getArtikelinhalt()));
		out.println(String.valueOf(a.getType()));
		//Übertrage alte Werte:
		out.println(String.valueOf(altID));
		out.println(String.valueOf(altName));
		out.println(String.valueOf(altPreis));
		out.println(String.valueOf(altLager));
		out.println(String.valueOf(altStueck));
		out.println(String.valueOf(altTyp));
		//Übertrage neue Werte:
		out.println(String.valueOf(neuID));
		out.println(String.valueOf(neuName));
		out.println(String.valueOf(neuPreis));
		out.println(String.valueOf(neuLager));
		out.println(String.valueOf(neuStueck));
		out.println(String.valueOf(neuTyp));
	}

	@Override
	public void aktualisiereKundenDaten(Kunde k, String benutzername,
			String passwort, String vorname, String nachname, String mail,
			String wohnort, int plz, String strasseUndNr,
			String benutzernameAlt, String passwortAlt, String emailAlt,
			String nachnameAlt, String vornameAlt, String wohnortAlt,
			int plzalt, String strasseUndNrAlt) {
		out.println("aktualisiereKundenDaten");
		//Kunden-Objekt senden
		out.println(String.valueOf(k.getVorname()));
		out.println(String.valueOf(k.getNachname()));
		out.println(String.valueOf(k.getBenutzername()));
		out.println(String.valueOf(k.getPassword()));
		out.println(String.valueOf(k.getEmail()));
		out.println(String.valueOf(k.getWohnort()));
		out.println(String.valueOf(k.getPLZ()));
		out.println(String.valueOf(k.getStrasseUndHausnummer()));
		//Übertragen der alten Werte
		out.println(String.valueOf(benutzername));
		out.println(String.valueOf(passwort));
		out.println(String.valueOf(vorname));
		out.println(String.valueOf(nachname));
		out.println(String.valueOf(mail));
		out.println(String.valueOf(wohnort));
		out.println(String.valueOf(plz));
		out.println(String.valueOf(strasseUndNr));
		//Übertrage neue Werte:
		out.println(String.valueOf(benutzernameAlt));
		out.println(String.valueOf(passwortAlt));
		out.println(String.valueOf(vornameAlt));
		out.println(String.valueOf(nachnameAlt));
		out.println(String.valueOf(emailAlt));
		out.println(String.valueOf(wohnortAlt));
		out.println(String.valueOf(plzalt));
		out.println(String.valueOf(strasseUndNrAlt));
	}



	@Override
	public void aktualisiereMitarbeiterDaten(Mitarbeiter m,
			String benutzername, String passwort, String vorname,
			String nachname, String mail, String wohnort, int plz,
			String strasseUndNr, String benutzernameAlt, String passwortAlt,
			String emailAlt, String nachnameAlt, String vornameAlt,
			String wohnortAlt, int zalt, String strasseUndNrAlt) {
		out.println("aktualisierenMitarbeiter");
		//Mitarbeiter-Objekt senden
		out.println(String.valueOf(m.getMitarbeiterID()));
		out.println(String.valueOf(m.getPassword()));
		out.println(String.valueOf(m.getEmail()));
		out.println(String.valueOf(m.getVorname()));
		out.println(String.valueOf(m.getNachname()));
		out.println(String.valueOf(m.getWohnort()));
		out.println(String.valueOf(m.getPLZ()));
		out.println(String.valueOf(m.getStrasseUndHausnummer()));
		//Übertragen der alten Werte
		out.println(String.valueOf(benutzername));
		out.println(String.valueOf(passwort));
		out.println(String.valueOf(vorname));
		out.println(String.valueOf(nachname));
		out.println(String.valueOf(mail));
		out.println(String.valueOf(wohnort));
		out.println(String.valueOf(plz));
		out.println(String.valueOf(strasseUndNr));
		//Übertrage neue Werte:
		out.println(String.valueOf(benutzernameAlt));
		out.println(String.valueOf(passwortAlt));
		out.println(String.valueOf(vornameAlt));
		out.println(String.valueOf(nachnameAlt));
		out.println(String.valueOf(emailAlt));
		out.println(String.valueOf(wohnortAlt));
		out.println(String.valueOf(zalt));
		out.println(String.valueOf(strasseUndNrAlt));

	}



	@Override
	public boolean entferneMitarbeiter(Mitarbeiter m) throws IOException {
		boolean b = false;
		out.println("entfernenMitarbeiter");
		
		out.println(m.getMitarbeiterID());
		out.println(m.getPassword());
		out.println(m.getEmail());
		out.println(m.getVorname());
		out.println(m.getNachname());
		out.println(m.getWohnort());
		out.println(String.valueOf(m.getPLZ()));
		out.println(m.getStrasseUndHausnummer());
		
		String antwort = in.readLine();
		if(antwort.equals("true")){
			b= true;
		}
		else if(antwort.equals("false")){
			b= false;
		}
		return b;
		
	}

	@Override
	public boolean entfernenProdukt(Produkt a) throws IOException {
		out.println("entfernenArtikel");
		
		out.println(String.valueOf(a.getID()));
		out.println(String.valueOf(a.getProduktName()));
		out.println(String.valueOf(a.getProduktPreis()));
		out.println(String.valueOf(a.getProduktMenge()));
		out.println(String.valueOf(a.getArtikelinhalt()));
		out.println(String.valueOf(a.getType()));

		String antwort = in.readLine();
		if(antwort.equals("true")){
			return true;
		}
		else return false;
	}

	@Override
	public boolean fuegeArtikelEin(int id, String name, float preis, int menge,int artikelinhalt, String type)throws ProduktExistiertBereitsException, IOException {
		out.println("fuegeArtikelEin");
		
		out.println(String.valueOf(id));
		out.println(name);
		out.println(String.valueOf(preis));
		out.println(String.valueOf(menge));
		out.println(String.valueOf(artikelinhalt));
		out.println(type);
		String antwort = in.readLine();
		if(antwort.equals("true")){
			return true;
		}
		else return false;
	}



	@Override
	public boolean fuegeMitarbeiterEin(String mitarbeiterID, String passwort,String email, String vorname, String nachname, String wohnort,int plz, String strasseNr) throws IOException {
		out.println("fuegeMitarbeiterEin");
		
		out.println(mitarbeiterID);
		out.println(passwort);
		out.println(email);
		out.println(vorname);
		out.println(nachname);
		out.println(wohnort);
		out.println(String.valueOf(plz));
		out.println(strasseNr);
		
		String antwort = in.readLine();
		if(antwort.equals("true")){
			return true;
		}
		else return false;
	}



	@Override
	public LinkedHashMap<String, String> getDatenAusLogfile(int artikelID) throws IOException {
		LinkedHashMap<String, String> daten = new LinkedHashMap<String, String>();
		out.println("getDatenAusLogfile");
		out.println(String.valueOf(artikelID));
		
		String antwort = in.readLine();
		if(antwort.equals("true")){
			int l = Integer.parseInt(in.readLine());
			for(int i=0; i!=l; i++){
				String a = in.readLine();
				String b = in.readLine();
				daten.put(a, b);
			}
		}
		else if(antwort.equals("false")){
			
		}
		return daten;
	}

	@Override
	public Vector<Produkt> gibAlleProdukt() throws IOException {
		out.println("gibAlleArtikel");
		Vector<Produkt> artikel = new Vector<Produkt>();
		String l = in.readLine();
		int laenge = Integer.parseInt(l);
		for(int i=0; i<laenge; i++){	
			Vector<Produkt> v = new Vector<Produkt>();
			String id = in.readLine();
			String name = in.readLine();
			String preis = in.readLine();
			String menge = in.readLine();
			String inhalt = in.readLine();
			String typ = in.readLine();
			
			int ID = Integer.parseInt(id);
			float PREIS = Float.parseFloat(preis);
			int MENGE = Integer.parseInt(menge);
			int ANZ = Integer.parseInt(inhalt);
			
			Produkt a = new Produkt(ID, name, PREIS, MENGE, ANZ, typ);
			v.add(a);
			artikel.add(a);
		}
		return artikel;
	}

	@Override
	public Vector<Kunde> gibAlleKunden()  {
		out.println("gibAlleKunden");
		Vector<Kunde> kunde = new Vector<Kunde>();
		String l;
		try {
			l = in.readLine();
			int laenge = Integer.parseInt(l);
			for(int i=0; i<laenge; i++){	
				Vector<Kunde> kun = new Vector<Kunde>();
				 String benutzername = in.readLine();	
				 String vorname = in.readLine();
				 String nachname = in.readLine();
				 String email = in.readLine();
				 String wohnort = in.readLine();
				 int plz = Integer.parseInt(in.readLine());
				 String strasseUndHausnummer = in.readLine();
				 float umsatz = Float.parseFloat(in.readLine());
				 String passwort = in.readLine();	
				 
				 Kunde k = new Kunde( benutzername, passwort, email, nachname, vorname, wohnort, plz, strasseUndHausnummer, umsatz);
				 kun.add(k);
				 kunde.addAll(kun);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return kunde;
	}

	@Override
	public Vector<Mitarbeiter> gibAlleMitarbeiter() throws IOException {
		out.println("gibAlleMitarbeiter");
		Vector<Mitarbeiter> mitarbeiter= new Vector<Mitarbeiter>();
		String l = in.readLine();
		int laenge = Integer.parseInt(l);
		for(int i=0; i<laenge; i++){	
			
			String id = in.readLine();
			String pw = in.readLine();
			String email = in.readLine();
			String vorname = in.readLine();
			String nachname = in.readLine();
			String wohnort = in.readLine();
			int plz = Integer.parseInt(in.readLine());
			String strasseNr = in.readLine();
			
			Mitarbeiter m = new Mitarbeiter(id, pw, email, vorname, nachname, wohnort, plz, strasseNr);
			mitarbeiter.add(m);
		}
		return mitarbeiter;
	}



	@Override
	public Mitarbeiter mitarbeiterLogin(String id, String pw) throws Exception {
		Mitarbeiter m = null;
		out.println("ml");
		out.println(id);
		out.println(pw);

		// Antwort vom Server:
		String antwort = in.readLine();
		if(antwort.equals("true")){
			String mid = in.readLine();
			String vorname = in.readLine();
			String nachname = in.readLine();
			String mail = in.readLine();
//			m = new Mitarbeiter(mid, vorname, nachname, mail);
		}
		else if(antwort.equals("false")){
		}

		return m;
	}



	@Override
	public boolean pruefeProdukt(String stringId) throws IOException {
		out.println("pruefeArtikel");
		out.println(stringId);
		String antwort = in.readLine();
		if(antwort.equals("true")){
			return true;
		}
		else return false;
	}



	@Override
	public Produkt sucheProdukt(String id) throws IOException {
		Produkt a = null;
		out.println("sucheArtikel");
		out.println(id);
		String antwort = in.readLine();
		if(antwort.equals("false")){
			return a;
		}
		else if(antwort.equals("true")){
			//Bekomme Artikel übergeben:
			int artikelID = Integer.parseInt(in.readLine());
			String artikelName = in.readLine();
			float artikelPreis = Float.parseFloat(in.readLine());
			int artikelMenge = Integer.parseInt(in.readLine());
			int artikelinhalt = Integer.parseInt(in.readLine());
			String type = in.readLine();
			//Erzeuge den Artikel
			a = new Produkt(artikelID, artikelName, artikelPreis, artikelMenge, artikelinhalt, type);
		}
		return a;
	}

	@Override
	public Mitarbeiter sucheMitarbeiter(String suchbegriff) throws IOException {
		Mitarbeiter m = null;
		out.println("sucheMitarbeiter");
		out.println(suchbegriff);
		String antwort = in.readLine();
		if(antwort.equals("true")){
			String id = in.readLine();
			String pw = in.readLine();
			String mail = in.readLine();
			String vor = in.readLine();
			String nach = in.readLine();
			String wohn = in.readLine();
			int plz = Integer.parseInt(in.readLine());
			String strassenr = in.readLine();
			m = new Mitarbeiter(id,pw,mail,vor,nach,wohn,plz,strassenr);
			return m;
		}
		else return m;
	}

	@Override
	public int sucheNachName(String name) throws IOException {
		int i = 0;
		out.println("sucheNachName");
		out.println(name);
		String antwort = in.readLine();
		if(antwort.equals("true")){
			i = Integer.parseInt(in.readLine());
		}
		return i;
	}

	
	
	
	@Override
	public boolean validateEmail(String mail) {
		return false;
	}
	@Override
	public boolean validateFloat(String floatString) {
		return false;
	}
	@Override
	public void verkaeuferLogin() throws Exception {
	}
	@Override
	public void aktualisiereLogfile(int artikelID, String artikelName,
			int neueAnzahl) {		
	}
	@Override
	public Kunde KundenLogin(String b, String p) throws IOException {
	//	System.out.println("Komm");
		out.println("kundenLogin");
		out.println(b);
		out.println(p);
		
		String ans = in.readLine();
		if(ans.equals("true")){
			String bn = in.readLine();
			String pw = in.readLine();
			String email = in.readLine();
			String vorname = in.readLine();
			String nachname = in.readLine();
			String wohnort = in.readLine();
			int plz = Integer.parseInt(in.readLine());
			String strasseNr = in.readLine();
			float umsatz = Float.parseFloat(in.readLine());
			
			return new Kunde(bn,pw,email,vorname,nachname,wohnort,plz,strasseNr,umsatz);
		}
		else{
			return null;
		}
	}
	@Override
	public void addKunLogfile(Vector<GewaehltesProdukt> warenkorb) {		
	}
	@Override
	public Vector<Produkt> sucheNachID(String id) {
		return null;
	}
	@Override
	public void renameLogfile(int alteID, int neueID) {		
	}
	@Override
	public void schreibeProdukt() throws IOException {		
	}
	@Override
	public void schreibeProduktLogfile(int artID, String artikelName, int anz) {		
	}
	@Override
	public void schreibeKunde() throws IOException {	
	}
	@Override
	public void schreibeLogFile(String s) throws IOException {		
	}
	@Override
	public void schreibeMitarbeiter() throws IOException {		
	}
	@Override
	public void setProduktMenge(Vector<GewaehltesProdukt> warenkorb)throws Exception {	
		out.println("setArtikelMenge");
		int size = warenkorb.size();
		out.println(size);
		
		Iterator<GewaehltesProdukt> iter =  warenkorb.iterator();
		while (iter.hasNext()) {
			GewaehltesProdukt ga = iter.next();
			out.println(ga.getID());
			out.println(ga.getProduktName());
			out.println(ga.getProduktPreis());
			out.println(ga.getProduktMenge());
			out.println(ga.getGekaufteAnzahl());
		}
		
		
		
		
	}
	@Override
	public void setProduktMenge(Produkt suchArtikel, int i) throws IOException {		
	}
	@Override
	public void setzeBesitzer(Kunde k) {		
	}
	@Override
	public void setzeMitarbeiter(Mitarbeiter m) {		
	}
	@Override
	public void neuerProduktMitarbeiterLog(Produkt a) throws IOException {		
	}
	@Override
	public void printRechnung(Vector<GewaehltesProdukt> warenkorb, float summe) {
	}
	@Override
	public void kaeuferLogin() throws Exception {		
	}
	@Override
	public void loescheLogfile(int artikelID) {		
	}
	@Override
	public void fuegeNeueKundeEin() throws IOException {		
	}
	@Override
	public boolean fuegeCUIMitarbeiterEin(String mitarbeiterID,
			String passwort, String email, String vorname, String nachname) {
		return false;
	}
	@Override
	public boolean fuegeKundeEin(String benutzername, String passwort,
			String email, String vorname, String nachname) throws IOException {
		
		out.println("fuegeKundeEin");
		out.println(benutzername);
		out.println(passwort);
		out.println(email);
		out.println(vorname);
		out.println(nachname);
		
		String ok = in.readLine();
		if(ok.equals("true")){
			return true;
		}
		return false;
	}
	@Override
	public void produktBearbeitetMitarbeiterLog(Produkt a) {		
	}
	@Override
	public void produktEntferntMitarbeiterLog(Produkt a) {		
	}
	@Override
	public void artikelSortierenNachID() throws IOException {		
	}

	@Override
	public float berechnenSumme(Vector<GewaehltesProdukt> warenkorb) {
		out.println("berechnenSumme");
		int size = warenkorb.size();
		out.println(size);
		float summe = 0;
		
		Iterator<GewaehltesProdukt> iter =  warenkorb.iterator();
		while (iter.hasNext()) {
			GewaehltesProdukt ga = iter.next();
			out.println(ga.getID());
			out.println(ga.getProduktName());
			out.println(ga.getProduktPreis());
			out.println(ga.getProduktMenge());
			out.println(ga.getGekaufteAnzahl());
		}
		try {
			summe = Float.parseFloat(in.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return summe;
	}

	@Override
	public Vector<Produkt> sucheProduktNachName(String suchbegriff) throws IOException {
		out.println("sucheArtikelNachName");
		out.println(suchbegriff);
		
		String b = in.readLine();
		if(b.equals("false")){
		}
		else{
			Vector<Produkt> artikel = new Vector<Produkt>();
			String l = in.readLine();
			int laenge = Integer.parseInt(l);
			for(int i=0; i<laenge; i++){	
				String id = in.readLine();
				String name = in.readLine();
				String preis = in.readLine();
				String menge = in.readLine();
				String inhalt = in.readLine();
				String typ = in.readLine();
				
				int ID = Integer.parseInt(id);
				float PREIS = Float.parseFloat(preis);
				int MENGE = Integer.parseInt(menge);
				int ANZ = Integer.parseInt(inhalt);
				
				Produkt a = new Produkt(ID, name, PREIS, MENGE, ANZ, typ);
				artikel.add(a);
			}
			
			return artikel;
		}
		return null;
	}

	@Override
	public void setKundendaten(String benutzername, String vorname, String nachname,
			String password, String email, String strundhausnummer, String plz,
			String wohnort) {
		// TODO Auto-generated method stub
		out.println("setKundendaten");
		out.println(benutzername);
		out.println(vorname);
		out.println(nachname);
		out.println(password);
		out.println(email);
		out.println(strundhausnummer);
		out.println(plz);
		out.println(wohnort);
	}

	@Override
	public Rechnung erstellenNeueRechnung(Vector<GewaehltesProdukt> warenkorb,
			float gesamteSumme, Kunde benutzer) throws IOException {
		// TODO Auto-generated method stub
		out.println("erstellenNeueRechnung");
		int size = warenkorb.size();
		out.println(size);
		
		Iterator<GewaehltesProdukt> iter =  warenkorb.iterator();
		while (iter.hasNext()) {
			GewaehltesProdukt ga = iter.next();
			out.println(ga.getID());
			out.println(ga.getProduktName());
			out.println(ga.getProduktPreis());
			out.println(ga.getProduktMenge());
			out.println(ga.getGekaufteAnzahl());
		}
		
		    out.println(gesamteSumme);
		    
		    out.println(benutzer.getBenutzername());
		    out.println(benutzer.getPassword());
		    out.println(benutzer.getEmail());
		    out.println(benutzer.getVorname());
		    out.println(benutzer.getNachname());
		    out.println(benutzer.getWohnort());
		    out.println(benutzer.getPLZ());
		    out.println(benutzer.getStrasseUndHausnummer());
		    out.println(benutzer.getLieferungVorname());
		    out.println(benutzer.getLieferungNachname());
		    out.println(benutzer.getLieferungStrasseUndHausnummer());
		    out.println(benutzer.getLieferungPLZ());
		    out.println(benutzer.getLieferungWohnort());
		    
		    
			Vector<GewaehltesProdukt> korb = new Vector<GewaehltesProdukt>();
			int size2 = Integer.parseInt(in.readLine());
			for(int i=0;i<size2;i++){
				int id = Integer.parseInt(in.readLine());
				String name = in.readLine();
				float preis = Float.parseFloat(in.readLine());
				int menge = Integer.parseInt(in.readLine());
				int gekaufteanzahl = Integer.parseInt(in.readLine());
				
				korb.add(new GewaehltesProdukt(id,name,preis,menge,gekaufteanzahl));
			}
			float gesamteSumme2 = Float.parseFloat(in.readLine());
			
			
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
			
			String zeit = in.readLine();
			
			Kunde k2 = new Kunde(bn,pw,email,vorname,nachname,wohnort,plz,strasseNr,lvorname,lnachname,lstrasseNr,lplz,lwohnort);
		    Rechnung rechnung= new Rechnung(korb,gesamteSumme2,k2,zeit);
		    if(rechnung!=null){
		    	return rechnung;
		    }
		    
		
		return null;
	}
}
