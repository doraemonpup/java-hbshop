package shop.local.valueobjects;

public class Kunde {
	
	private String vorname;
	private String nachname;
	private String benutzername;
	private String password;
	private String email;
	private String wohnort = "-";
	private int plz;
	private String strasseUndHausnummer = "-";
	private float umsatz = 0;
	
	private String lieferungVorname;
	private String lieferungNachname;
	private String lieferungStrasseUndHausnummer;
	private int lieferungPlz;
	private String lieferungWohnort;
	
	public Kunde(){
		
	}
	
	
	public Kunde(String benutzername,String password,String email,String nachname,String vorname, String wohnort,int plz, String strasseNr){

		this.benutzername = benutzername;
		this.password = password;
		this.email = email;
		this.nachname = nachname;
		this.vorname = vorname;	
		this.wohnort = wohnort;
		this.strasseUndHausnummer = strasseNr;
	}

	
	
	public Kunde(String benutzername,String password,String email,String nachname,String vorname){
		
		this.benutzername = benutzername;
		this.password = password;
		this.email = email;
		this.nachname = nachname;
		this.vorname = vorname;	
	}

	
	
	public Kunde(String benutzername,String password,String email,String nachname,String vorname,String wohnort,int plz,String strUndHausnr,float umsatz){
			this.benutzername = benutzername;
			this.password = password;
			this.email = email;
			this.nachname = nachname;
			this.vorname = vorname;	
			this.wohnort = wohnort;
			this.plz=plz;
			this.strasseUndHausnummer = strUndHausnr;
			this.umsatz = umsatz;
			
			this.lieferungVorname = vorname;
			this.lieferungNachname = nachname;
			this.lieferungStrasseUndHausnummer = strUndHausnr;
			this.lieferungPlz = plz;
			this.lieferungWohnort = wohnort;
			
		}
	
	
	public Kunde(String benutzername,String password,String email,String vorname,String nachname,String wohnort,int plz,String strUndHausnr,
			String lvorname,String lnachname,String lstrNr, int lplz, String lwohnort){
		
		this.benutzername = benutzername;
		this.password = password;
		this.email = email;
		this.nachname = nachname;
		this.vorname = vorname;	
		this.wohnort = wohnort;
		this.plz=plz;
		this.strasseUndHausnummer = strUndHausnr;
		
		this.lieferungVorname = lvorname;
		this.lieferungNachname = lnachname;
		this.lieferungStrasseUndHausnummer = lstrNr;
		this.lieferungPlz = lplz;
		this.lieferungWohnort = lwohnort;
		
	}
	
	public String getBenutzername(){
		return benutzername;
	}
	public void setBenutzername(String b){
		this.benutzername = b;
	}
	
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String pass){
		this.password = pass;
	}
	
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
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
	
	
	public String getStrasseUndHausnummer(){
		return strasseUndHausnummer;
	}
	public void setStrasseUndHausnummer(String strasseUndHausnr){
		this.strasseUndHausnummer = strasseUndHausnr;
	}
	
	
	public float getUmsatz(){
		return umsatz;
	}
	public void setUmsatz(float umsatz){
		this.umsatz += umsatz;
	}
	
	
	
	public void setLieferungVorname(String vorname){
		this.lieferungVorname = vorname;
	}
	public void setLieferungNachname(String nachname){
		this.lieferungNachname = nachname;
	}
	public void setLieferungStrasseUndHausnummer(String strasseUndHausnr){
		this.lieferungStrasseUndHausnummer = strasseUndHausnr;
	}
	public void setLieferungPLZ(int plz){
		this.lieferungPlz = plz;
	}
	public void setLieferungWohnort(String wohnort){
		this.lieferungWohnort = wohnort;
	}
	
	public String getLieferungVorname(){
		return lieferungVorname ;
	}
	public String getLieferungNachname(){
		return lieferungNachname;
	}
	public String getLieferungStrasseUndHausnummer(){
		return lieferungStrasseUndHausnummer;
	}
	public int getLieferungPLZ(){
		return lieferungPlz;
	}
	public String getLieferungWohnort(){
		return lieferungWohnort;
	}
	
	
	
	public String toString(){
		return ("Benutzername: " + benutzername + " / Vorname: " + vorname + " / Nachname: " + nachname );
	}
	
}
