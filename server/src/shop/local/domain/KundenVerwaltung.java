package shop.local.domain;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

//import shop.local.exceptions.ArtikelExistiertBereitsException;
import shop.local.exceptions.ProduktNichtGefundenException;
import shop.local.exceptions.KundeExistiertBereitsException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
//import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Produkt;
import shop.local.valueobjects.GekauftesProdukt;
import shop.local.valueobjects.GewaehltesProdukt;
import shop.local.valueobjects.Kunde;

/**
 * 
 * @author Mert, Chunhawit
 * Methode zum Verwalten der Kunden
 * Bietet Methoden zum Einfügen, Suchen, und Bearbeiten von Kunden und Kundendaten
 */
public class KundenVerwaltung {

	
	private Vector<Kunde> kundenListe = new Vector<Kunde>();
	
	private PersistenceManager pm = new FilePersistenceManager(); 
	
	private Kunde KundeBesitzer = new Kunde();
	private Vector KunlogFile = new Vector();
	
	/**
	 * Methode zum Aufrufen der vorhandenen Kunden
	 * Wirft Exception, wenn einzufügender Produkt bereits vorhanden
	 * @param datei
	 * @throws IOException
	 */
	public void liesDaten(String datei) throws IOException{
		pm.openForReading(datei);
		Kunde einKunde;
		do{
			// Kunde-Objekt einlesen...
			einKunde = pm.ladeKunde();
			if(einKunde!=null){
				// Kunde hinzufuegen...
				try{
				einfuegen(einKunde);
				}catch(KundeExistiertBereitsException e){
					
				}
			}
		}while(einKunde!=null);
		pm.close();
	}
	
	/**
	 * Methode zum Schreiben und Speichern von angelegten Kunden
	 * @param datei
	 * @throws IOException
	 */
	public void schreibeKunden(String datei) throws IOException  {
		// PersistenzManager fuer Schreibvorgaenge oeffnen
		pm.openForWriting(datei);

		if (!kundenListe.isEmpty()) {
			Iterator<Kunde> iter = kundenListe.iterator();
			while (iter.hasNext()) {
				Kunde k = (Kunde) iter.next();
				pm.speichereKunde(k);				
			}
		}			
		
		// Persistenz-Schnittstelle wieder schließen
		pm.close();
	}
	
	/**
	 * Methode zum Anlegen von neuen Kunden
	 * Wirft Exception, wenn anzulegender Kunde bereits existend
	 * @param einKunde
	 * @throws KundeExistiertBereitsException
	 */
	public void einfuegen(Kunde einKunde) throws KundeExistiertBereitsException {
		if (kundenListe.contains(einKunde))
			throw new KundeExistiertBereitsException(einKunde, " - in 'einfuegen()'");
		else
		kundenListe.add(einKunde);
	}
	
	//Kundensuche
	/**
	 * Methode zum Suchen nach vorhandenen Kunden in Kundenliste
	 * @param titel, Kundenname
	 * @return ergebnis, gesuchter Kunde
	 */
	public Vector<Kunde> sucheKunde(String titel) {
		Vector<Kunde> ergebnis = new Vector<Kunde>();
		
		Iterator<Kunde> iter = kundenListe.iterator();
		while (iter.hasNext()) {
			Kunde kunde = (Kunde) iter.next();
			if ( kunde.getVorname().equals(titel) || kunde.getNachname().equals(titel) || kunde.getBenutzername().equals(titel)) {
				ergebnis.add(kunde);
			}
		}
		
		return ergebnis;
	}
	
	//GUI-Kunden bearbeiten:
	/**
	 * Methoden zum Arufrufen aktualisierter Kunden mit Kundendaten
	 * @param jeweils alt und neu: Kunde, Benutzername, Password, Vorname, Nachname, E-Mail, Wohnort, PLZ, Straße und Hausnummer
	 */
	public void aktualisiereKundenDaten(Kunde k, String benutzername, String password, String vorname, String nachname, String mail, String wohnort, int plz, String strasseUndNr, 
									String benutzernameAlt,String passwordAlt,String emailAlt,String nachnameAlt,String vornameAlt,String wohnortAlt,int plzalt, String strasseUndNrAlt)
	{
		Iterator<Kunde> iter = kundenListe.iterator();
		while (iter.hasNext()) {
			k = (Kunde) iter.next();
			if ( k.getBenutzername().equals(benutzername)) {
				k.setVorname(vorname);
				k.setNachname(nachname);
				k.setPassword(password);
				k.setEmail(mail);
				k.setStrasseUndHausnummer(strasseUndNr);
				k.setPLZ(plz);
				k.setWohnort(wohnort);
			}

		}
	}
	
	/**
	 * Methode zum Aufrufen eines Kunden
	 * @return Kunde in Kundenliste
	 */
	public Vector<Kunde> getKunde() {
		// Gib Kundenliste in Vector zurueck.
		return kundenListe;
	}
	
	/**
	 * @param neuerVorname, neuerNachname, neuesPasswort
	 * Methoden zum Neusetzen genannter Kundeneigenschaften
	 */
	public void setVorname(String neuerVorname) {
		KundeBesitzer.setVorname(neuerVorname);
		
	}
	
	public void setNachname(String neuerNachname) {
		KundeBesitzer.setNachname(neuerNachname);
		
	}

	public void setPassword(String neuesPasswort) {
		KundeBesitzer.setPassword(neuesPasswort);
		
	}

	public void setStrasseUndHausnummer(String neueAddresse) {
		KundeBesitzer.setStrasseUndHausnummer(neueAddresse);
		
	}

	// eine Kunde,der sich erfolgreich eingeloggt hat.
	/**
	 * Methode zum Festlegen des aktuellen eigeloggten Kaufers
	 * @param Kunde
	 */
	public void besitzKunde(Kunde k) {
		KundeBesitzer = k;
	}
	
	/**
	 * Methde zum Aufrufen des eigeloggten Kunden
	 * @return true, wenn Kunde eingeloggt
	 */
	public boolean getKundenBesitzer(){
		if(KundeBesitzer==null){
			return false;
		}
		else return true;
	}

	/**
	 * Methode zum Speichern von Kundenaktivitäten in Logdatei
	 * @param datei
	 * @throws IOException
	 */
	public void schreibeKunLogFile(String datei) throws IOException {
		// PersistenzManager für Schreibvorgänge öffnen
		pm.openForWriting(datei);

		if (!KunlogFile.isEmpty()) {
			Iterator<String> iter = KunlogFile.iterator();
			while (iter.hasNext()) {
				String s = (String) iter.next();
				pm.speichereKunLogFile(s);				
			}
		}			
	
		// Persistenz-Schnittstelle wieder schließen
		pm.close();
		
	}
	
	/**
	 * Methode zum Einlesen von Kundenaktivitäten in Logdatei
	 * @param datei
	 * @throws IOException
	 */
	public void liesKunLogFile(String datei) throws IOException{
		pm.openForReading(datei);
		
		String einLogFile;
		do{
			// Mitarbeiter-Objekt einlesen...
			einLogFile = pm.ladeMitLogFile();
			if(einLogFile!=null){
				// ein Mitarbeiter einfuegen...
				try{
				einfuegen(einLogFile);
				}catch(Exception e){
					
				}
			}
		}while(einLogFile!=null);
		pm.close();
		
	}

	/**
	 * Methode zum Einfügen von Kundenaktivitäten in Logdatei
	 * @param einLogFile
	 */
	private void einfuegen(String einLogFile) {
		KunlogFile.add(einLogFile);
	}

	// fuege Log file in Vector
	/**
	 * Methode zum Auflisten von Kundenlogdaten als Vektor
	 * @param warenkorb
	 */
	public void addKunLogfile(Vector<GewaehltesProdukt> warenkorb) {
		
		Iterator<GewaehltesProdukt> iter = warenkorb.iterator();
		while (iter.hasNext()) {
			GewaehltesProdukt a = (GewaehltesProdukt) iter.next();
			KunlogFile.add(now("MM/dd/yy 'at' hh:mm:ss  ")+" "+KundeBesitzer.getVorname()+" "+KundeBesitzer.getNachname() +" kaufte "+a.toString());
		}
	}
	
	/**
	 * Methode zum Angeben der aktullen Zeit inkl. Datum
	 * @param dateFormat
	 * @return Tag und Uhrzeit
	 */
	public static String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());
	}

	
	public void setKundenDaten(String benutzername,String vorname, String nachname, String password, String email, String strundhausnummer,int plz, String wohnort){
		Iterator<Kunde> iter = kundenListe.iterator();
		while (iter.hasNext()) {
			Kunde k = (Kunde) iter.next();
			if ( k.getBenutzername().equals(benutzername)) {
				k.setVorname(vorname);
				k.setNachname(nachname);
				k.setPassword(password);
				k.setEmail(email);
				k.setStrasseUndHausnummer(strundhausnummer);
				k.setPLZ(plz);
				k.setWohnort(wohnort);
			}
		}
	}

	public boolean setUmsatz(Kunde k, float gesamteSumme) {
		
			Iterator<Kunde> iter = kundenListe.iterator();
			while (iter.hasNext()) {
				Kunde kunde = (Kunde) iter.next();
				if ( k.getBenutzername().equals(kunde.getBenutzername())) {
					kunde.setUmsatz(gesamteSumme);
					return true;
				}
			}
		return false;
		
	}

}
