package shop.local.exceptions;

import shop.local.valueobjects.Produkt;

public class ProduktNichtVorhandenException extends Exception{
	
	public ProduktNichtVorhandenException(Produkt produkt, String zusatzMsg) {
		
		super("Produkt ID " + produkt.getID() + ", Name " + produkt.getProduktName() 
				+ " ist nicht mehr vorhanden!! " + zusatzMsg);
	}
}
