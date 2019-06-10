package shop.local.valueobjects;

public class GewaehltesProdukt extends Produkt{
	int kaufAnzahl = 0;

	public GewaehltesProdukt(int id, String name, float preis, int menge,int kaufAnzahl) {
		super(id, name, preis, menge);
		this.kaufAnzahl = kaufAnzahl;
	}
	
	public int getGekaufteAnzahl(){
		return kaufAnzahl;
	}
	
	public void setGekaufteAnzahl(int anzahl){
		this.kaufAnzahl = anzahl;
	}

	public String toString() {
		return ("ID: " + produktID + " / Name: " + produktName + " / Preis: " + produktPreis +"/ Menge: "+kaufAnzahl);
	}
}
