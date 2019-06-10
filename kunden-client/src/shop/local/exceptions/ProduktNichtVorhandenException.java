package shop.local.exceptions;

import shop.local.valueobjects.Produkt;

public class ProduktNichtVorhandenException extends Exception{
	public ProduktNichtVorhandenException(Produkt artikel, String zusatzMsg) {
		super("Artikel ID " + artikel.getID() + ", Name " + artikel.getProduktName() 
				+ " ist nicht mehr vorhanden!! " + zusatzMsg);
	}
}
