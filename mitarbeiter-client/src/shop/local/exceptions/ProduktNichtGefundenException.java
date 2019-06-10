package shop.local.exceptions;


@SuppressWarnings("serial")
public class ProduktNichtGefundenException extends Exception{
	public ProduktNichtGefundenException() {
		super("Der Artikel ist nicht gefunden!!");
	}
}
