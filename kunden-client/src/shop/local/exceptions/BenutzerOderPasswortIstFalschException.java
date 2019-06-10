package shop.local.exceptions;

public class BenutzerOderPasswortIstFalschException extends Exception {
	
	public BenutzerOderPasswortIstFalschException(){
		super("Der Benutzername oder Passwort ist falsch!!");
	}

}
