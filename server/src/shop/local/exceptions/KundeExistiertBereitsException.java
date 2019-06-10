package shop.local.exceptions;

import shop.local.valueobjects.Kunde;

/**
 * @exception Fehler auffangen - Kunde existiert bereits
 **/

public class KundeExistiertBereitsException extends Exception {
	
	public KundeExistiertBereitsException(Kunde kunde, String zusatzMsg) {
		
		super("Kunden ID " + kunde.getBenutzername() + ", Benutzername " + kunde.getBenutzername()
				+ " existiert bereits" + zusatzMsg);
	}
}
