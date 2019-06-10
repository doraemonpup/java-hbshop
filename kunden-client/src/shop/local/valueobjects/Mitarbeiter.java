package shop.local.valueobjects;

public class Mitarbeiter {
	
	private String mitarbeiterID;
	private String password;
	private String email;
	private String vorname = "";
	private String nachname = "";
	private String wohnort = "-";
	private int plz;
	private String strasseUndHausnummer = "-";
	
	
	public Mitarbeiter() {
	
		}

	
	public Mitarbeiter(String id,String passwort, String email, String vorname, String nachname){
		this.mitarbeiterID = id;
		this.password = passwort;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
	}
	
	
	public Mitarbeiter(String id,String passwort, String email, String vorname, String nachname,String wohnort,int plz,String strUndHausNr){
		this.mitarbeiterID = id;
		this.password = passwort;
		this.email = email;
		this.vorname = vorname;
		this.nachname = nachname;
		this.wohnort = wohnort;
		this.plz = plz;
		this.strasseUndHausnummer = strUndHausNr;
	}
	
	
	
	//get/set-Methode
	public String getMitarbeiterID(){
		return mitarbeiterID;
	}
	public void setMitarbeiterID(String id){
		this.mitarbeiterID = id;
	}
	
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String passwort){
		this.password = passwort;
	}
	
	
	public String getVorname(){
		return vorname;
	}
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	
	public String getNachname(){
		return nachname;
	}
	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return email;
	}
	
	
	public String getWohnort(){
		return wohnort;
	}
	public void setWohnort(String wohnort){
		this.wohnort = wohnort;
	}
	
	
	public int getPLZ(){
		return plz;
	}
	public void setPLZ(int plz){
		this.plz = plz;
	}
	
	
	public void setStrasseUndHausnummer(String strasseUndHausnummer){
		this.strasseUndHausnummer = strasseUndHausnummer;
	}
	public String getStrasseUndHausnummer(){
		return strasseUndHausnummer;
	}
	
	

	public String toString(){
		return ("ID: " + mitarbeiterID + " / Vorname: " + vorname + " / Nachname: " + nachname + " / E-Mail:" + email );
	}
	
}
