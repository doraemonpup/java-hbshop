package shop.local.exceptions;

import shop.local.valueobjects.Produkt;

/**
 * @Exception Fehler, wenn man ein Produkt gesucht, und nicht gefunden hat
 */

public class ProduktNichtGefundenException extends Exception{
	
	public ProduktNichtGefundenException() {
		
		super("Das Produkt ist nicht gefunden!!");
	}
}
