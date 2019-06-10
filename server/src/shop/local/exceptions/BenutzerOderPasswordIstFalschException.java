package shop.local.exceptions;

/**
 * @exception, wenn die Eingabe falsch ist, z.B. Wegen int, String usw.
 */

public class BenutzerOderPasswordIstFalschException extends Exception {

	public BenutzerOderPasswordIstFalschException(){
		
		super("Der Benutzername oder Password ist falsch!!");
	}

}
