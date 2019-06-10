package shop.local.exceptions;

import shop.local.valueobjects.Mitarbeiter;

/**
 * @exception Fehler auffangen - Mitarbeiter ist bereits vorhanden
 **/

public class MitarbeiterExistiertBereitsException extends Exception{
	
	public MitarbeiterExistiertBereitsException (Mitarbeiter mitarbeiter, String zusatzMsg) {
		
		super("Mitarbeiter mit ID " + mitarbeiter.getMitarbeiterID() + " und Name " + mitarbeiter.getNachname() 
				+ " existiert bereits" + zusatzMsg);
	}

}
