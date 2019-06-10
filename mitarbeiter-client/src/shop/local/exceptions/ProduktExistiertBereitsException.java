package shop.local.exceptions;

import shop.local.valueobjects.Produkt;

/**
 * @exception Fehler auffangen - Artikel ist bereits vorhanden
 **/
@SuppressWarnings("serial")
public class ProduktExistiertBereitsException extends Exception{
	
	public ProduktExistiertBereitsException(Produkt artikel, String zusatzMsg) {
		super("Artikel ID " + artikel.getID() + ", Name " + artikel.getProduktName() 
				+ " existiert bereits" + zusatzMsg);
	}
	
	public ProduktExistiertBereitsException(Produkt artikel) {
		super("Artikel ID " + artikel.getID() + ", Name " + artikel.getProduktName() 
				+ " existiert bereits" );
	}
}
