package shop.local.valueobjects;

import java.util.Iterator;
import java.util.Vector;

public class Rechnung {
	Vector warenkorb ;
	float summe  = 0;
	Kunde kundeBesitzer;
	String zeit;
	
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
		
	/*	System.out.print("###### Rechnung ######\n\n");
		System.out.print(zeit+"  "+kundeBesitzer.getVorname()+" "+kundeBesitzer.getNachname()+"\n\n");
		Iterator iter = warenkorb.iterator();
		int i = 1;
		while(iter.hasNext()){
			GekaufteArtikel gekaufteartikel = (GekaufteArtikel)iter.next();
			System.out.print(i+". ");
			System.out.print(gekaufteartikel.toString()+"\n");
			i++;
		}
		System.out.print("\n=================================\n");
		System.out.print("Summe      =      "+summe+"\n");
		System.out.print("=================================\n");
		System.out.print("Danke fuer Ihren Einkauf\n\n");*/
	}
	

}
