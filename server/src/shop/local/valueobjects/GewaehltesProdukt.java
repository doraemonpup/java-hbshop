package shop.local.valueobjects;

public class GewaehltesProdukt extends Produkt{
	int kaufAnzahl = 0;

	/**
	 * Umgang ueber das gekaufte Produkt, fuer die Methode berechennenSumme()... usw.
	 * @param id
	 * @param name
	 * @param preis
	 * @param menge
	 * @param kaufAnzahl
	 */
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
