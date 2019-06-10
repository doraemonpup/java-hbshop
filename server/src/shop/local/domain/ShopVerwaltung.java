package shop.local.domain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Produkt;
import shop.local.valueobjects.GewaehltesProdukt;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.valueobjects.Rechnung;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.ProduktNichtGefundenException;
import shop.local.exceptions.KundeExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
//import shop.local.domain.MitarbeiterVerwaltung;
import shop.local.domain.LogInVerwaltung;
import shop.local.interfaces.EShopInterface;

/**
 *
 */

public class ShopVerwaltung implements EShopInterface {
	
	private BufferedReader in;
	private ProduktVerwaltung produktverwaltung;
	private KundenVerwaltung kundenverwaltung;
	private MitarbeiterVerwaltung mitarbeiterverwaltung;
	private PersistenceManager pm;
	private LogInVerwaltung lgv;
	private String datei = "";
	

	public ShopVerwaltung(String datei) throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException{
		this.datei = datei;
		in = new BufferedReader(new InputStreamReader(System.in));
		produktverwaltung = new ProduktVerwaltung();
		produktverwaltung.liesDaten(datei+"_A.txt");
		
		mitarbeiterverwaltung = new MitarbeiterVerwaltung();
		mitarbeiterverwaltung.liesDaten("mitarbeiter_list.txt");
		mitarbeiterverwaltung.liesMitLogFile("mitarbeiter_log.txt");
		
		kundenverwaltung = new KundenVerwaltung();
		kundenverwaltung.liesDaten("kunden_list.txt");
		lgv = new LogInVerwaltung();
		pm = new FilePersistenceManager();
	}
	

	public boolean fuegeProduktEin(int id, String name, float preis, int menge,int produktinhalt, String type) throws ProduktExistiertBereitsException {
		Produkt a = new Produkt(id,name,preis,menge,produktinhalt,type);
		try{
			produktverwaltung.einfuegen(a);
			String log = "f";
			mitarbeiterverwaltung.addLogFile(a,log);
		}catch(ProduktExistiertBereitsException e){
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public Vector<Produkt> gibAlleProdukt() {
		return produktverwaltung.getProduktBestand();
	}
	
	public Vector<Kunde> gibAlleKunden() {
		return kundenverwaltung.getKunde();
	}
	
	public Vector<Mitarbeiter> gibAlleMitarbeiter() {
		return mitarbeiterverwaltung.getMitarbeiter();
	}

	public boolean entfernenProdukt(Produkt p) {
		try{
			produktverwaltung.entfernenProdukt(p);
			String log = "l";
			mitarbeiterverwaltung.addLogFile(p, log);
		}catch(ProduktNichtGefundenException e){
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
			return true;
	}
	
	public boolean entferneMitarbeiter(Mitarbeiter m) {
		try{
		mitarbeiterverwaltung.entferneMitarbeiter(m);
		}catch(Exception e){
			return false;
		}
			return true;
	}
	
	
	
	
	public void schreibeProdukt() throws IOException {
		produktverwaltung.schreibeDaten(datei+"_A.txt");
	}
	
	// schreibe Mitarbeiter in Dateiqeulle
	public void schreibeMitarbeiter() throws IOException {
		mitarbeiterverwaltung.schreibeMitarbeiter("mitarbeiter_list.txt");
	}
	
	// schreibe Kunde in Dateiqeulle
	public void schreibeKunde() throws IOException {
		kundenverwaltung.schreibeKunden("kunden_list.txt");
	}

	// CUI-Methode zum Einfuegen ein Mitarbeiter
	public boolean fuegeCUIMitarbeiterEin(String mitarbeiterID,String password,String email, String vorname, String nachname) {

		return true;
	}
	
	// GUI-Methode zum Einfuegen ein Mitarbeiter
	public boolean fuegeMitarbeiterEin(String mitarbeiterID,String password,String email, String vorname, String nachname, String wohnort,int plz, String strasseNr) throws IOException {
		Mitarbeiter m = new Mitarbeiter(mitarbeiterID,password,email,vorname,nachname, wohnort,plz, strasseNr);
		try{
			mitarbeiterverwaltung.einfuegen(m);
			lgv.einfuegen(m);
		}catch(MitarbeiterExistiertBereitsException e){
			return false;
		}
		mitarbeiterverwaltung.mLogfile(m, 1);
		return true;
	}
	
	//Methode zum Einfuegen eine Kunde
	public boolean fuegeKundeEin(String benutzername,String password,String email, String vorname, String nachname) throws IOException {
		Kunde k = new Kunde(benutzername,password,email,vorname,nachname);
		try{
			kundenverwaltung.einfuegen(k);
			lgv.einfuegen(k);
			
		}catch(KundeExistiertBereitsException e){
			return false;
		}
		try {
			schreibeKunde();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
    // Methode zum Login von einem Kunde
	public Kunde KundenLogin(String b, String p){
		Kunde k = null;
		try {
			k = lgv.KundenLogin(b, p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(k==null){
			return null;
		}
		else{
			return k;
		}
	}
	

	public Mitarbeiter mitarbeiterLogin(String id, String pw) throws Exception{
		
		Mitarbeiter m = lgv.MitarbeiterLogin(id, pw);
			return m;		
	}
	
	//Frage nach notwendige Info beim registieren
	public void fuegeNeueKundeEin() throws IOException {
		System.out.print("Geben Sie den Benutzername ein > ");
		String benutzername = liesEingabe();
		System.out.print("Geben Sie Passwort ein > ");
		String passwort = liesEingabe();
		System.out.print("Geben Sie Ihre E-Mail-Adresse ein > ");
		String email = liesEingabe();
		System.out.print("Geben Sie Ihren Vorname ein > ");
		String vorname = liesEingabe();
		System.out.print("Geben Sie Ihren Nachname ein > ");
		String nachname = liesEingabe();
		
		fuegeKundeEin(benutzername,passwort,email,vorname,nachname);
		
	}
	
	//Methode zum aktualisiern von Kunden-Daten
	public void aktualisiereKundenDaten(Kunde k, String benutzername, String password, String vorname, String nachname, String mail, String wohnort, int plz, String strasseUndNr, String benutzernameAlt,String passwortAlt,String emailAlt,String nachnameAlt,String vornameAlt,String wohnortAlt,int plzalt, String strasseUndNrAlt){
		kundenverwaltung.aktualisiereKundenDaten(k, benutzername, password, vorname, nachname, mail, wohnort,plz, strasseUndNr, benutzernameAlt,passwortAlt,emailAlt,nachnameAlt,vornameAlt,wohnortAlt,plzalt,strasseUndNrAlt);
	}

	//Methode zum aktualisiern von Produkt-Daten
	public void aktualisiereProduktDaten(Produkt p, int altID,String altName,float altPreis,int altLager,int altStueck,String altTyp,int neuID,String neuName,float neuPreis,int neuLager,int neuStueck,String neuTyp){
		produktverwaltung.aktualisiereArtikelDaten( p,  altID, altName, altPreis, altLager, altStueck, altTyp, neuID, neuName, neuPreis, neuLager, neuStueck, neuTyp);
	}
	
	//Methode zum aktualisieren der Mitarbeiter-Daten
	public void aktualisiereMitarbeiterDaten(Mitarbeiter m, String benutzername, String password, String vorname, String nachname, String mail, String wohnort, int plz, String strasseUndNr, String benutzernameAlt,String passwortAlt,String emailAlt,String nachnameAlt,String vornameAlt,String wohnortAlt,int zalt, String strasseUndNrAlt){
		mitarbeiterverwaltung.aktualisiereMitarbeiterDaten(m, benutzername, password, vorname, nachname, mail, wohnort,plz, strasseUndNr, benutzernameAlt,passwortAlt,emailAlt,nachnameAlt,vornameAlt,wohnortAlt,zalt,strasseUndNrAlt);
	}
	
	public void setzeBesitzer(Kunde k){
		kundenverwaltung.besitzKunde(k);
	}
	public void setzeMitarbeiter(Mitarbeiter m){
		mitarbeiterverwaltung.setzeBesitzer(m);
	}
	
	// Methode zum Suchen eines Produkt nach ID
	public Vector<Produkt> sucheNachID(String id) {
		Vector<Produkt>ergebnisVonId = new Vector<Produkt>();
		try {
			 ergebnisVonId = produktverwaltung.sucheProduktNachID(id);
		} catch (ProduktNichtGefundenException e) {
			e.printStackTrace();
			return null;
		} 
		return ergebnisVonId;
	}

	// Methode zum Suchen eines Produkt nach Name
	public Vector<Produkt> sucheNachName(String name){
	
		Vector<Produkt> ergebnisVonName = new Vector<Produkt>();
		
		try{
			ergebnisVonName = produktverwaltung.sucheProduktNachName(name);
		} catch (ProduktNichtGefundenException e) {
			return null;
		}
		return ergebnisVonName;
	}
	
	private String liesEingabe() throws IOException {
		return in.readLine();
	}


	public Produkt sucheProdukt(String id) {
		Produkt produkt;
		try {
			produkt = produktverwaltung.suchEineProdukt(id);
		} catch (ProduktNichtGefundenException e) {
			e.printStackTrace();
			return null;
		}
		return produkt;
	}
	
	public Mitarbeiter sucheMitarbeiter(String suchbegriff){
		Mitarbeiter mitarbeiter;
		
		mitarbeiter = mitarbeiterverwaltung.sucheMitarbeiter(suchbegriff);
		return mitarbeiter;
	}


	public void printRechnung(Vector<GewaehltesProdukt> warenkorb,float summe) {
		Iterator iter = warenkorb.iterator();
		int i = 1;
		while(iter.hasNext()){
			GewaehltesProdukt gewaehltesprodukt = (GewaehltesProdukt)iter.next();
			System.out.print(i+". ");
			System.out.print(gewaehltesprodukt.toString()+"\n");
			i++;
		}	
	}
	
	

	// Sortieren Produkt nach ID
	public void produktSortierenNachID() throws IOException {
		
		boolean ok = produktverwaltung.sortierenNachID();
		if(ok){
			schreibeProdukt();
		}
		else{
			System.out.println("Error beim Sortieren");
		}
	}

	// Schreibe Log file in externe Datei
	public void schreibeLogFile(String s) throws IOException {
		if(s.equals("m")){
			mitarbeiterverwaltung.schreibeMitLogFile("mitarbeiter_log.txt");
		}
		else{
			kundenverwaltung.schreibeKunLogFile("kunden_log.txt");
		}
	}

	// Set Menge von Produkt
	public void setProduktMenge(Produkt suchProdukt, int i) throws IOException {
		suchProdukt.setProduktMenge(i);
		String log = "b";
		mitarbeiterverwaltung.addLogFile(suchProdukt, log);
	}
	
	// Set Menge von Produkt nach dem Einkauf
	public void setProduktMenge(Vector<GewaehltesProdukt> warenkorb) throws Exception {
		
		Iterator iter = warenkorb.iterator();
		while(iter.hasNext()){
			GewaehltesProdukt gewaehltesprodukt = (GewaehltesProdukt)iter.next();
			// suche zuerst Produkt nach id und dann set neue Menge
			Produkt a = sucheProdukt(Integer.toString(gewaehltesprodukt.getID()));
			a.setProduktMenge(a.getProduktMenge()- gewaehltesprodukt.getGekaufteAnzahl());
		}
	}


	public void addKunLogfile(Vector<GewaehltesProdukt> warenkorb) {
		kundenverwaltung.addKunLogfile(warenkorb);
	}


	// Pruefe,ob Id schon existiert oder nicht.
	public boolean pruefeProdukt(String stringId) {
		boolean a = false;
			try {
				a = produktverwaltung.pruefeProdukt(stringId);
			} catch (ProduktExistiertBereitsException e) {
			}
		
		return a;
	}
	// Regular Expression zum pr�fen der Mail:
	public boolean validateEmail(String eMail) {
		 Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		 Matcher m = p.matcher(eMail);
		 if(m.matches()) return true;
		        else return false;
	}
	
	public boolean validateFloat(String floatString) {
		 Pattern p = Pattern.compile("d+(,d+)?");
		 Matcher m = p.matcher(floatString);
		 if(m.matches()) return true;
		        else return false;
	}
	
	//ArtikelLogfileMethoden
	public void schreibeProduktLogfile(int proID, String produktName, int anz){
		pm.schreibeProduktLog(proID, produktName, anz);
	}
	public void aktualisiereLogfile(int produktID, String produktName, int neueAnzahl){
		pm.aktualisiereLogfile(produktID, produktName, neueAnzahl);
	}
	public void renameLogfile(int alteID, int neueID){
		pm.renameLogfile(alteID, neueID);
	}
	public LinkedHashMap<String, String> getDatenAusLogfile(int produktID){
		return pm.getDatenAusLogfile(produktID);
	}
	public void loescheLogfile(int produktID){
		pm.loescheLogfile(produktID);
	}
	
	public String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());
	}

	public void setKundendaten(String benutzername, String vorname, String nachname,
			String password, String email, String strundhausnummer, String plz,
			String wohnort) {
		 int postleitzahl = Integer.parseInt(plz);
		kundenverwaltung.setKundenDaten(benutzername, vorname, nachname, password, email, strundhausnummer, postleitzahl, wohnort);
		
		try {
			schreibeKunde();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Rechnung erstellenNeueRechnung(Vector warenkorb, float gesamteSumme,Kunde benutzer) {
		
		Rechnung r;
		try{
			 r = new Rechnung(warenkorb,gesamteSumme,benutzer,now("MM/dd/yy 'at' hh:mm:ss  "));
		}catch(Exception e){
			return null;
		}
		return r;
	}


	@Override
	public Vector<Produkt> sucheProduktNachName(String suchbegriff) {
		
		return null;
	}


	@Override
	public float berechnenSumme(Vector<GewaehltesProdukt> warenkorb) {
		
		float summe = produktverwaltung.berechnenSumme(warenkorb);
		return summe;
	}


	@Override
	public void setUmsatz(Kunde k, float gesamteSumme) {
		
		boolean ok = kundenverwaltung.setUmsatz(k,gesamteSumme);
		System.out.println(ok);
		if(ok){
			try {
				schreibeKunde();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
