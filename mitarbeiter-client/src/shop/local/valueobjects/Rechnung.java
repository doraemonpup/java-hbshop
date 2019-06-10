package shop.local.valueobjects;

import java.util.Iterator;
import java.util.Vector;

public class Rechnung {
	Vector<GekauftesProdukt> warenkorb ;
	float summe  = 0;
	Kunde kundeBesitzer;
	String zeit;
	
	public Rechnung(Vector<GekauftesProdukt> warenkorb, float gesamteSumme, Kunde kundeBesitzer, String zeit) {
		this.warenkorb = warenkorb;
		this.summe = gesamteSumme;
		this.kundeBesitzer = kundeBesitzer;
		this.zeit = zeit;
	}
	
	public void PrintRechnung(){
		
		System.out.print("###### Rechnung ######\n\n");
		System.out.print(zeit+"  "+kundeBesitzer.getVorname()+" "+kundeBesitzer.getNachname()+"\n\n");
		Iterator<GekauftesProdukt> iter = warenkorb.iterator();
		int i = 1;
		while(iter.hasNext()){
			GekauftesProdukt gekaufteartikel = (GekauftesProdukt)iter.next();
			System.out.print(i+". ");
			System.out.print(gekaufteartikel.toString()+"\n");
			i++;
		}
		System.out.print("\n=================================\n");
		System.out.print("Summe      =      "+summe+"\n");
		System.out.print("=================================\n");
		System.out.print("Danke fuer Ihren Einkauf\n\n");
	}

}
