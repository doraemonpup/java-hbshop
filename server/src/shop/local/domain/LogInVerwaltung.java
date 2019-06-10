package shop.local.domain;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Vector;
import shop.local.domain.MitarbeiterVerwaltung;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;


public class LogInVerwaltung {
	
	private Vector MitListe = new Vector();
	private Vector KunListe = new Vector();
	private MitarbeiterVerwaltung mvt = new MitarbeiterVerwaltung();
	private KundenVerwaltung kvt = new KundenVerwaltung();
	private PersistenceManager logInPM = new FilePersistenceManager();
	private BufferedReader in;
	
	public LogInVerwaltung() throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		mvt.liesDaten("mitarbeiter_list.txt");
		kvt.liesDaten("kunden_list.txt");
		
		
		MitListe = mvt.getMitarbeiter();
		KunListe = kvt.getKunde();
	}

	
	
	//Fuege die Mitarbeiter in dem Vector ein.
	/**
	 * Methode zum Angeben der vorhandenen Mitarbeiter als Vektor
	 * @param Mitarbeiter
	 * @IOException
	 */
	public void einfuegen(Mitarbeiter einMitarbeiter) throws IOException {
		MitListe.add(einMitarbeiter);
	}
	
	public void einfuegen(Kunde einKunde) throws IOException {
		KunListe.add(einKunde);
	}
	
	/**
	 * Methode zum Betreuen der Mitarbeiterloginverfahrens
	 * @return true, ruft passende Mitarbeiterdaten auf
	 * @throws IOException
	 */
	public boolean MitlogIn() throws IOException{
		String MitId = "";
		String password = "";
		System.out.print("Mitarbeiter ID : ");
		MitId = liesEingabe();
		System.out.print("Password : ");
		password = liesEingabe();
		
//		MitListe = mvt.getMitarbeiter();
		Iterator iter = MitListe.iterator();
		while(iter.hasNext()){
			Mitarbeiter m = (Mitarbeiter) iter.next();
			if(m.getMitarbeiterID().equals(MitId)&&m.getPassword().equals(password)){
				mvt.setzeBesitzer(m);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Methode zum Betreuen des Kundenloginverfahrens
	 * @return true, ruft passende Kundendaten auf
	 * @throws IOException
	 */
	public boolean KunlogIn() throws IOException{
		String benutzerName = "";
		String password = "";
		System.out.print("Benutzer : ");
		benutzerName = liesEingabe();
		System.out.print("Password : ");
		password = liesEingabe();
		
//		KunListe = kvt.getKunde();
		Iterator iter = KunListe.iterator();
		while(iter.hasNext()){
			Kunde k = (Kunde) iter.next();
			if(k.getBenutzername().equals(benutzerName)&& k.getPassword().equals(password)){
				// Setze eine Kunde ein, um zu wissen, wer jetzt benutzt
				kvt.besitzKunde(k);
				return true;
			}
		}
		return false;
	}
	
	//GUI-Kunden-Login
	//@SuppressWarnings("deprecation")
	/**
	 * Methode zum Einloggen als Kunde
	 * @param Benutzername und Passwort
	 * @IOExcpetion
	 * @return Kunde ist eingeloggt
	 */
	public Kunde KundenLogin(String benutzername, String password) throws IOException{

//		KunListe = kvt.getKunde();
		Iterator iter = KunListe.iterator();
		while(iter.hasNext()){
			Kunde k = (Kunde) iter.next();
			if(k.getBenutzername().equals(benutzername)&& k.getPassword().equals(password)){
				return k;
			}
		}
		return null;
	}
	
	//GUI-Mitarbeiter-Login
	/**
	 * Methode zum Eonloggen als Mitarbeiter
	 * @param Mitarbeiter-ID, Passwort
	 * @IOExcpetion
	 * @return Mitarbeiter ist eingeloggt
	 */
	public Mitarbeiter MitarbeiterLogin(String ID, String pw) throws IOException{

		MitListe = mvt.getMitarbeiter();
		Iterator iter = MitListe.iterator();
		while(iter.hasNext()){
			Mitarbeiter m = (Mitarbeiter) iter.next();
			if(m.getMitarbeiterID().equals(ID)&&m.getPassword().equals(pw)){
				mvt.setzeBesitzer(m);
				return m;
			}
		}
		return null;
	}
	
	
	protected String liesEingabe() throws IOException {
		// einlesen von Konsole
		return in.readLine();
	}
}
