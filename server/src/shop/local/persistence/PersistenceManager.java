package shop.local.persistence;

import java.io.IOException;
import java.util.LinkedHashMap;

import shop.local.valueobjects.*;


public interface PersistenceManager {
	
	public boolean close();

	public void openForReading(String datenquelle) throws IOException;
	
	public void openForWriting(String datenquelle) throws IOException;
	

	
	public Mitarbeiter ladeMitarbeiter() throws IOException;
	
	public boolean speichereKunde(Kunde k) throws IOException;
	
	public boolean speichereMitarbeiter(Mitarbeiter a) throws IOException;
	
	public boolean speichereProdukt(Produkt a) throws IOException;
	
	public void speichereMitLogFile(String s) throws IOException;

	public void speichereKunLogFile(String s);

	public Produkt ladeProdukt() throws IOException;
	
	public Kunde ladeKunde() throws IOException;

	public String ladeMitLogFile() throws IOException;
	
	public LinkedHashMap<String, String> getDatenAusLogfile(int produktID);
	
	public void loescheLogfile(int produktID);

	public void schreibeProduktLog(int proID, String produktName, int anz);
	
	public void aktualisiereLogfile(int produktID, String produktName, int neueAnzahl);
	
	public void renameLogfile(int alteID, int neueID);
	
	


	

}
