package shop.local.exceptions;

import shop.local.valueobjects.Produkt;

public class ProduktNichtGefundenException extends Exception{
	public ProduktNichtGefundenException() {
		super("Der Artikel ist nicht gefunden!!");
	}
}
