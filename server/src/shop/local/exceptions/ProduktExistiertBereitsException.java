package shop.local.exceptions;

import shop.local.valueobjects.Produkt;

/**
 * @exception Produkt ist bereits vorhanden
 */

public class ProduktExistiertBereitsException extends Exception{
	
	public ProduktExistiertBereitsException(Produkt produkt, String zusatzMsg) {
		
		super("Produkt ID " + produkt.getID() + ", Name " + produkt.getProduktName() 
				+ " existiert bereits" + zusatzMsg);
	}
	
	public ProduktExistiertBereitsException(Produkt produkt) {
		
		super("Produkt ID " + produkt.getID() + ", Name " + produkt.getProduktName() 
				+ " existiert bereits" );
	}
}
