package shop.local.domain;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Produkt;
import shop.local.valueobjects.Mitarbeiter;

/**
 * Klasse zur Verwaltung der vorhandenen Mitarbeiter(Verk�ufer).
 * Alle Mitarbeiter k�nnen eingesehen und bearbeitet werden.
 * Neue Mitarbeiter k�nnen �ber die GUI neu angelegt werden!!
 * @param ....
 * @exception MitarbeiterExistiertBereitsException - Fehler auffangen, sei ein anzulegen versuchter Mitarbeiter bereits vorhanden
 */

	public class MitarbeiterVerwaltung {

	// Verwaltung des Mitarbeiterbestands in einem Vector.
	private Vector<Mitarbeiter> mitarbeiterBestand = new Vector<Mitarbeiter>();
	// Persistenz-Schnittstelle, die f�r die Details des Dateizugriffs verantwortlich ist.
	private PersistenceManager pm = new FilePersistenceManager(); 
	// welcher Mitarbeiter ist immoment
	private static Mitarbeiter mitarbeiterBesitzer = new Mitarbeiter();
	// LogFile in Vector, speichern alle Ereignise, wer, macht was..und wann
	private Vector MitlogFile = new Vector();
	
	/**
	 * Methode zum Einlesen der Mitarbeiter-Daten
	 * Wirft Exception, wenn Einlesen fehlgeschlagen
	 * @param datei
	 * @throws IOException
	 */
	public void liesDaten(String datei) throws IOException{
		pm.openForReading(datei);
		
		Mitarbeiter einMitarbeiter;
		do{
			// Mitarbeiter-Objekt einlesen...
			einMitarbeiter = pm.ladeMitarbeiter();
			if(einMitarbeiter!=null){
				// ein Mitarbeiter einfuegen...
				try{
				einfuegen(einMitarbeiter);
				}catch(MitarbeiterExistiertBereitsException e){
					
				}
			}
		}while(einMitarbeiter!=null);
		pm.close();
		
	}
	
	/**
	 * Methode zum persistenten Speichern der Mitarbeiter-Daten
	 * Die eigentliche Speicherung wird mit pm.speichereMitarbeiter(m) an den PersistenceManager weiterdelegiert.
	 * Schmei�t eine IOException, wenn es zu Komplikationen mit dem Input-Output-Stream kommt.
	 * @param datei
	 * @throws IOException
	 */
	public void schreibeMitarbeiter(String datei) throws IOException  {
		// PersistenzManager f�r Schreibvorg�nge �ffnen
		pm.openForWriting(datei);

		if (!mitarbeiterBestand.isEmpty()) {
			Iterator<Mitarbeiter> iter = mitarbeiterBestand.iterator();
			while (iter.hasNext()) {
				Mitarbeiter m = (Mitarbeiter) iter.next();
				pm.speichereMitarbeiter(m);				
			}
		}				
		// Persistenz-Schnittstelle wieder schlie�en
		pm.close();
	}
	/**
	 * Methode zum einfuegen eines Mitarbeiters m zum Mitarbeiterbestand
	 * Wirft Exception, wenn gesuchter Artikel nicht gefunden
	 * @param einMitarbeiter
	 * @throws MitarbeiterExistiertBereitsException, wenn Mitarbeiter bereits existiert
	 */
	public void einfuegen(Mitarbeiter einMitarbeiter)  throws  MitarbeiterExistiertBereitsException {
		if (mitarbeiterBestand.contains(einMitarbeiter)){
			throw new MitarbeiterExistiertBereitsException(einMitarbeiter, " - in 'einfuegen()' ");
		}
		mitarbeiterBestand.add(einMitarbeiter);
	}
	
	/**
	 * Methode zum Ausgeben des kompletten Mitarbeiterbestands in Form eines Vectors
	 * @return Vector<Mitarbeiter>
	 */
	public Vector<Mitarbeiter> getMitarbeiter() {
		// Gib Mitarbeiterliste in Vector zurueck.
		return mitarbeiterBestand;
	}

	/**
	 * Methode zum setzen des aktuell-eingeloggten Mitarbeiter
	 */	
	public void setzeBesitzer(Mitarbeiter m) {
		mitarbeiterBesitzer = m;
	}

	/**
	 * Methode zum hinzuf�gen eines Logs
	 * Wenn String i gleich "f" ist: Mitarbeiter xy legte Artikel z an!
	 * Wenn String i gleich "l" ist: Mitarbeiter xy l�schte den Artikel z!
	 * Wenn String i gleich "b" ist: Mitarbeiter xy aenderte die Menge des Artikels z!
	 * @param Produkt a, String i
	 * @throws IOException 
	 */
	public void addLogFile(Produkt a,String i) throws IOException {
		if(i.equals("f")){
			MitlogFile.add(now("MM/dd/yy 'at' hh:mm:ss  ")+" "+mitarbeiterBesitzer.getVorname()+" "+mitarbeiterBesitzer.getNachname() +" legte einen Artikel "+a.toString()+" an");
		}
		else if(i.equals("l")){
			MitlogFile.add(now("MM/dd/yy 'at' hh:mm:ss  ")+" "+mitarbeiterBesitzer.getVorname()+" "+mitarbeiterBesitzer.getNachname() +" entfernte den Artikel "+a.toString()+" an");
		}
		else if(i.equals("b")){
		MitlogFile.add(now("MM/dd/yy 'at' hh:mm:ss  ")+" "+mitarbeiterBesitzer.getVorname()+" "+mitarbeiterBesitzer.getNachname() +" aenderte die Menge von Artikel "+a.toString()+" auf "+a.getProduktMenge());
		}
		
		schreibeMitLogFile("mitarbeiter_log.txt");

	}
	/**
	 * Methode zum hinzuf�gen eines Logs
	 * Wenn int i gleich 1 ist: Mitarbeiter xy legte einen neuen Mitarbeiter z an!
	 * Wenn int i gleich 2 ist: Mitarbeiter xy l�schte einen neuen Mitarbeiter z an!
	 * @param Mitarbeiter m, int i
	 * @throws IOException 
	 */
	// add eine Situation in LogFile: ein Mitarbeiter hat einen neuen Mitarbeiter eingefuegt.
	public void mLogfile(Mitarbeiter neuMitarbeiter,int i) throws IOException {
		if(i==1){
			MitlogFile.add(now("MM/dd/yy 'at' hh:mm:ss  ")+" "+mitarbeiterBesitzer.getVorname()+" "+mitarbeiterBesitzer.getNachname() +" legte den Mitarbeiter "+neuMitarbeiter.toString()+" an");
		}
		else if(i==2){
			MitlogFile.add(now("MM/dd/yy 'at' hh:mm:ss  ")+" "+mitarbeiterBesitzer.getVorname()+" "+mitarbeiterBesitzer.getNachname() +" entfernte den Mitarbeiter "+neuMitarbeiter.toString());
		}
		schreibeMitLogFile("mitarbeiter_log.txt");
	}
	

	/**
	 * Methode zum laden der MitarbeiterLogFile
	 * @param datei
	 * @throws IOException 
	 */	
	public void liesMitLogFile(String datei) throws IOException {
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
	 * Methode zum einf�gen eines Logs zur MitarbeiterLogFile
	 * @param einLogFile
	 */	
	private void einfuegen(String einLogFile) {
		MitlogFile.add(einLogFile);
		
	}
	/**
	 * Methode zum schreiben der LogFile in eine externe Quelle
	 * Wird weiter delegiert an den PersistenceManager
	 * @param datei
	 * @throws IOException
	 */	
	public void schreibeMitLogFile(String datei) throws IOException  {
		// PersistenzManager f�r Schreibvorg�nge �ffnen
		pm.openForWriting(datei);

		if (!MitlogFile.isEmpty()) {
			Iterator<String> iter = MitlogFile.iterator();
			while (iter.hasNext()) {
				String s = (String) iter.next();
				pm.speichereMitLogFile(s);				
			}
		}			
	
		// Persistenz-Schnittstelle wieder schlie�en
		pm.close();
	}

	/**
	 * Methode zum ermitteln der aktuellen Zeit und des aktuellen Datums
	 * @param dateFormat
	 * @return Kalender-Instanz
	 */	
	public static String now(String dateFormat) {
		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		    return sdf.format(cal.getTime());
	}
	
	/**
	 * Methode zum aktualisieren eines Mitarbeiters.
	 * Die Methode iteriert duch den Vektor mitarbeiterBestand, vergleicht die alten mit den neuen Werten und ersetzt dabei den alten mit dem neuen Wert.
	 * 
	 * @param Mitarbeiter m, String benutzername, String passwort, String vorname, String nachname, String mail, String wohnort, int plz, String strasseUndNr, String benutzernameAlt,String passwortAlt,String emailAlt,String nachnameAlt,String vornameAlt,String wohnortAlt,int zalt, String strasseUndNrAlt
	 */		
	public void aktualisiereMitarbeiterDaten(Mitarbeiter m, String benutzername, String password, String vorname, String nachname, String mail, String wohnort, int plz, String strasseUndNr, 
									String benutzernameAlt,String passwordAlt,String emailAlt,String nachnameAlt,String vornameAlt,String wohnortAlt,int zalt, String strasseUndNrAlt)
	{
		Iterator<Mitarbeiter> iter = mitarbeiterBestand.iterator();
		while (iter.hasNext()) {
			m = (Mitarbeiter) iter.next();
			if ( (m.getMitarbeiterID().equals(benutzernameAlt))&&( (m.getMitarbeiterID().equals(benutzername))||(m.getMitarbeiterID().equals(benutzernameAlt)) ) ){
				m.setMitarbeiterID(benutzername);
			}
			if ( (m.getVorname().equals(vornameAlt))&&( (m.getMitarbeiterID().equals(benutzername))||(m.getMitarbeiterID().equals(benutzernameAlt)) ) ){
				m.setVorname(vorname);
			}
			if ( (m.getNachname().equals(nachnameAlt))&&( (m.getMitarbeiterID().equals(benutzername))||(m.getMitarbeiterID().equals(benutzernameAlt)) ) ){
				m.setNachname(nachname);
			}
			if ( (m.getEmail().equals(emailAlt))&&( (m.getMitarbeiterID().equals(benutzername))||(m.getMitarbeiterID().equals(benutzernameAlt)) ) ){
				m.setEmail(mail);
			}
			if ( (m.getWohnort().equals(wohnortAlt) ) &&( (m.getMitarbeiterID().equals(benutzername))||(m.getMitarbeiterID().equals(benutzernameAlt)) ) ){
				m.setWohnort(wohnort);
			}
			if ( (m.getPLZ()==(zalt) ) &&( (m.getMitarbeiterID().equals(benutzername))||(m.getMitarbeiterID().equals(benutzernameAlt)) ) ){
				m.setPLZ(plz);
			}
			if ( (m.getStrasseUndHausnummer().equals(strasseUndNrAlt)) && ( (m.getMitarbeiterID().equals(benutzername))||(m.getMitarbeiterID().equals(benutzernameAlt)) ) ){
				m.setStrasseUndHausnummer(strasseUndNr);
			}
			if ( (m.getPassword().equals(passwordAlt)) && ( (m.getMitarbeiterID().equals(benutzername))||(m.getMitarbeiterID().equals(benutzernameAlt)) ) ){
				m.setPassword(password);
			}

		}
	}
	
	/**
	 * Methode zum suchen eines Mitarbeiters. 
	 * @param suchwort
	 * @return gibt bei Erfolg ein Mitarbeiter-Objekt zur�ck, sonst null
	 */		
	public Mitarbeiter sucheMitarbeiter(String suchwort) {
		
		Iterator<Mitarbeiter> iter = mitarbeiterBestand.iterator();
		while (iter.hasNext()) {
			Mitarbeiter mitarbeiter = (Mitarbeiter) iter.next();
			String Id = mitarbeiter.getMitarbeiterID();
			if ( Id.equals(suchwort)) {
				return mitarbeiter;
			}
		}
		return null; 
	}	
	/**
	 * Methode zum loeschen eines Mitarbeiters. 
	 * @param Mitarbeiter m
	 * @return gibt true zur�ck, wenn loeschen erfolgreich war, sonst false
	 */	
	public boolean entferneMitarbeiter(Mitarbeiter mitarbeiter) throws java.util.ConcurrentModificationException  {
		try{
			Iterator<Mitarbeiter> iter = mitarbeiterBestand.iterator();
			while (iter.hasNext()) {
				Mitarbeiter mit = (Mitarbeiter) iter.next();
				if ( mit.equals(mitarbeiter)) {
					mitarbeiterBestand.remove(mit);
					break;
				}
			}
			schreibeMitarbeiter("mitarbeiter_list.txt");
			return true;
		}catch(Exception e){
			return false;
		}
	}
}	

