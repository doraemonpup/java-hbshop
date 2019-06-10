package shop.local.valueobjects;

import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * Klasse Rechnung, beinhaltet die Attribute fuer die Rechnung
 *
 */

public class Rechnung {
	
	Vector warenkorb ;
	float summe  = 0;
	Kunde kundeBesitzer;
	String zeit;
	
	
	/**
	 * Rechnung-Attibute zu representieren
	 * @param warenkorb
	 * @param gesamteSumme
	 * @param kundeBesitzer
	 * @param zeit
	 */
	public Rechnung(Vector<GewaehltesProdukt> warenkorb, float gesamteSumme, Kunde kundeBesitzer, String zeit) {
		this.warenkorb = warenkorb;
		this.summe = gesamteSumme;
		this.kundeBesitzer = kundeBesitzer;
		this.zeit = zeit;
	}
	

	public Vector getWarenkorb(){
		return warenkorb;
	}
	
	public float getSumme(){
		return summe;
	}
	
	public Kunde getKundeBesitzer(){
		return kundeBesitzer;
	}
	
	public String getZeit(){
		return zeit;
	}
	
	public void PrintRechnung(){
		
	}

}
