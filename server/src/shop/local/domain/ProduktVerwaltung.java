package shop.local.domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.Collections;
import java.util.Comparator;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.ProduktNichtGefundenException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Produkt;
import shop.local.valueobjects.GekauftesProdukt;
import shop.local.valueobjects.GewaehltesProdukt;
import shop.local.valueobjects.Kunde;
/**
 *
 *
 */
public class ProduktVerwaltung    {
	
	
	private Vector<Produkt> produktBestand = new Vector();
	
	private PersistenceManager pm = new FilePersistenceManager(); 
	

	
	/**
	 * 
	 */
	public void liesDaten(String datei) throws IOException{
		pm.openForReading(datei);
		
		Produkt einProdukt;
		do{
			// Produkt-objekt einlesen...
			einProdukt = pm.ladeProdukt();
			if(einProdukt!=null){
				// Produkt einfuegen...
				try{
				einfuegen(einProdukt);
				}catch(ProduktExistiertBereitsException e){
					
				}
			}
		}while(einProdukt!=null);
		pm.close();
		
	}
	
	/**
	 * Methode zum Speichern von �nderungen im Artikelbestand
	 * @param datei
	 * @throws IOException
	 */
	public void schreibeDaten(String datei) throws IOException  {
		pm.openForWriting(datei);

		if (!produktBestand.isEmpty()) {
			Iterator<Produkt> iter = produktBestand.iterator();
			while (iter.hasNext()) {
				Produkt p = (Produkt) iter.next();
				pm.speichereProdukt(p);				
			}
		}			
		
		// Persistenz-Schnittstelle wieder schlie�en
		pm.close();
	}
	
	/**
	 * Methode zum Einf�gen von neuen und ausverkauften Artikeln
	 * Wirft Exception, wenn einzuf�gender Artikel bereits im Artikelbestand
	 * @param einProdukt
	 * @throws ProduktExistiertBereitsException
	 */
	public void einfuegen(Produkt einProdukt) throws ProduktExistiertBereitsException {
		if (produktBestand.contains(einProdukt))
			throw new ProduktExistiertBereitsException(einProdukt, " - in 'einfuegen()'");

		produktBestand.add(einProdukt);
	}
	
	//Suchmethode
	
	/**
	 * Methode zum Suchen nach Artikeln im Bestand anhand der Artikel ID
	 * Wirft Exception, wenn gesuchter Artikel nicht gefunden
	 * @param id
	 * @exception ArtikelNichtGefundenExcpetion
	 * @return ergebnis
	 */
	public Vector<Produkt> sucheProduktNachID(String id) throws ProduktNichtGefundenException {
		Vector<Produkt> ergebnis = new Vector<Produkt>();
		
		Iterator<Produkt> iter = produktBestand.iterator();
		while (iter.hasNext()) {
			Produkt artikel = (Produkt) iter.next();
			String ID = Integer.toString(artikel.getID());
			if ( ID.equals(id)) {
				ergebnis.add(artikel);
			}
		}
		if(ergebnis.isEmpty()){
			throw new ProduktNichtGefundenException();
		}
		return ergebnis;
	}
	
	
	//GUI-Produkt bearbeiten:
	
	/**
	 * Methode zum Aktualisieren des Artikelbestandes nach �nderungen
	 * @param Produkt, altID, altName, altPreis, altLagerbestand, altMenge, altTyp
	 * @param neuID, neuName, neuPrei, neuLagerbestand, neuMenge, neuTyp
	 */
	public void aktualisiereArtikelDaten(Produkt a, int altID,String altName,float altPreis,int altLager,int altStueck,String altTyp,int neuID,String neuName,float neuPreis,int neuLager,int neuStueck,String neuTyp){
		Iterator<Produkt> iter = produktBestand.iterator();
		while (iter.hasNext()) {
			a = (Produkt) iter.next();
			if (a.getID()==(altID)){
				a.setID(neuID);
			}
			if (a.getProduktName().equals(altName)&& (a.getID()==(altID))||(a.getID()==(neuID))){
				a.setProduktName(neuName);
			}
			if (a.getProduktPreis()==(altPreis)&& (a.getID()==(altID))||(a.getID()==(neuID))){
				a.setProduktPreis(neuPreis);
			}
			if (a.getProduktMenge()==(altLager)&& (a.getID()==(altID))||(a.getID()==(neuID))){
				a.setProduktMenge(neuLager);
			}
			if (a.getProduktinhalt()==(altStueck)&& (a.getID()==(altID))||(a.getID()==(neuID))){
				a.setProduktinhalt(neuStueck);
			}
			if (a.getType().equals(altTyp) && (a.getID()==(altID))||(a.getID()==(neuID))){
				a.setType(neuTyp);
			}				
		}
	}
	
	
	/**
	 * Methode zum Suchen nach Artikeln im Bestand anhand des Artikelnamens
	 * Wirft Exception, wenn gesuchter Artikel nicht gefunden
	 * @param titel
	 * @return ergebnis
	 * @throws ProduktNichtGefundenException
	 */
	public Vector<Produkt> sucheProduktNachName(String titel) throws ProduktNichtGefundenException {
		Vector<Produkt> ergebnis = new Vector<Produkt>();
		
		Iterator<Produkt> iter = produktBestand.iterator();
		while (iter.hasNext()) {
			Produkt produkt = (Produkt) iter.next();
			if ( produkt.getProduktName().toLowerCase().contains(titel.toLowerCase()) ) {
				ergebnis.add(produkt);
			}
		}
		if(ergebnis.isEmpty()){
			throw new ProduktNichtGefundenException();
		}
		return ergebnis;
	}
	
	/**
	 * Methode zum Aufrufen des Artikelbestandes
	 * @return artikelbestand
	 */
	public Vector<Produkt> getProduktBestand() {
		return produktBestand;
	}

	/**
	 * Methode zum Entfernen von Artikeln aus dem Artikelbestand
	 * Wirft Exception, wenn zu entfernender Artikel nicht gefunden
	 * @param produkt
	 * @return
	 * @throws ProduktNichtGefundenException, java.util.ConcurrentModificationException
	 */
	public boolean entfernenProdukt(Produkt produkt) throws ProduktNichtGefundenException, java.util.ConcurrentModificationException {
		try{	
			Iterator<Produkt> iter = produktBestand.iterator();
			while (iter.hasNext()) {
				Produkt pro = (Produkt) iter.next();
				if ( pro.equals(produkt)) {
					produktBestand.remove(pro);
					break;
				}
			}
			schreibeDaten("items.txt");
			return true;
		}catch(Exception e){
			return false;
		}
	}

	// Methode zum Suchen ein Artikel und gibt Artikel zurueck.
	public Produkt suchEineProdukt(String id) throws ProduktNichtGefundenException{
		Iterator<Produkt> iter = produktBestand.iterator();
		while (iter.hasNext()) {
			Produkt produkt = (Produkt) iter.next();
			String Id = Integer.toString(produkt.getID());
			if ( Id.equals(id)) {
				return produkt;
			}		}
		throw new ProduktNichtGefundenException();
	}

	//Entsumme gew�hltes Produkt berechnen
	/**
	 * Methode zum Brechnen der Gesamtkosten f�r gekaufte Artikel
	 * @param produkt
	 * @return summe
	 */
	public float berechnenSumme(Vector<GewaehltesProdukt> produkt){
		float summe = 0;
		Iterator<GewaehltesProdukt> iter = produkt.iterator();
		while(iter.hasNext()){
			GewaehltesProdukt gewaehltesProdukt = (GewaehltesProdukt)iter.next();
			summe = summe + (gewaehltesProdukt.getProduktPreis()*gewaehltesProdukt.getGekaufteAnzahl());
		}
		return summe;
	}

	
	// Methode zum Set die Menge von Artikel neu nach dem Einkauf
	/**
	 * Methode zum Neusetzen der Menge nach Einkauft.
	 * Artikel Bestand wird um Einkaufsmenge reduziert
	 * @param warenkorb
	 * @return true, Produktmenge kann neugesetzt werden
	 */
	public boolean setMengeNachdemEinkauf(Vector<GewaehltesProdukt> warenkorb) {
		Iterator iter = produktBestand.iterator();
		while(iter.hasNext()){
			Produkt produkt = (Produkt)iter.next();
			
			Iterator<GewaehltesProdukt> iter2 = warenkorb.iterator();
			while(iter2.hasNext()){
				GewaehltesProdukt gewaehltesProdukt = (GewaehltesProdukt)iter2.next();
				if(produkt.getID() == gewaehltesProdukt.getID()){
					produkt.setProduktMenge(produkt.getProduktMenge()-gewaehltesProdukt.getGekaufteAnzahl());
				}
			}
		}
		return true;
		
	}

	//Artikel nach ID sortieren.
	/**
	 * Methode zum Sortieren der vorhandenen Artikel nach Artikel-ID
	 * @return Werte werden aufsteigend nach ID sortiert
	 * @exception
	 */
	public boolean sortierenNachID() {
		try{
		Collections.sort(produktBestand, new Comparator() {
			public int compare(Object o1, Object o2) {
				Produkt p1 = (Produkt) o1;
				Produkt p2 = (Produkt) o2;
				return Integer.toString(p1.getID()).compareTo(Integer.toString(p2.getID()));
			}
		});
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	// Sortieren nach Name
	/**
	 * Methode zum Sortieren der vorhandenen Artikel nach Artikelnae
	 * @return Werte werden aufsteigend alphabetisch sortiert
	 * @exception
	 */
	public boolean sortierenNachName() {
		try{
		Collections.sort(produktBestand, new Comparator() {
			public int compare(Object o1, Object o2) {
				Produkt p1 = (Produkt) o1;
				Produkt p2 = (Produkt) o2;
				return p1.getProduktName().compareTo(p2.getProduktName());
			}
		});
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Methode zum Pr�fen anhand ID, ob Artikel im bestand bereits vorhanden oder nicht
	 * @param stringId
	 * @return true, wenn Artiel-ID bereits im Bestand vorhanden
	 * @throws ProduktExistiertBereitsException
	 */
	public boolean pruefeProdukt(String stringId) throws ProduktExistiertBereitsException{
		Iterator<Produkt> iter = produktBestand.iterator();
		boolean b=false;
		while (iter.hasNext()) {
			Produkt produkt = (Produkt) iter.next();
			String Id = Integer.toString(produkt.getID());
			if ( Id.equals(stringId)) {
				b=true;
			}
		}
		return b;
	}



}

