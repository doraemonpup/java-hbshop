package shop.local.exceptions;

@SuppressWarnings("serial")
public class BenutzerOderPasswordIstFalschException extends Exception {
	
	public BenutzerOderPasswordIstFalschException(){
		super("Der Benutzername oder Passwort ist falsch!!");
	}

}
