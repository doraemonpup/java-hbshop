package shop.local.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

import shop.local.valueobjects.*;


public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;
	private static Calendar cal;
	private String[] result;
	private int aktuellerTag;
	private int aktuellerMonat;
	private int aktuellesJahr;
	
	
	public boolean close() {
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				
				return false;
			}
		}

		return true;
	}

	public void openForReading(String datei) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(datei));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(new FileWriter(datei));
	}
	

	public Produkt ladeProdukt() throws IOException {
		String StringId = leseDaten();
		if (StringId == null) {
			return null;
		}
		// Umwandeln in int
		int id = Integer.parseInt(StringId);
		String name = leseDaten();
		
		String StringPreis = leseDaten();
		float preis = Float.parseFloat(StringPreis);
		
		String StringMenge = leseDaten();
		int menge = Integer.parseInt(StringMenge);
		
		String StringProduktinhalt = leseDaten();
		int produktinhalt = Integer.parseInt(StringProduktinhalt);
		
		String type = leseDaten();
		// neues Produkt-Objekt anlegen und zurückgeben
		return new Produkt(id, name, preis, menge,produktinhalt, type);
	}
	

	/**
	 * Methode zum Schreiben der Produktdaten in eine Datenquelle.
	 * @param a Produkt-Objekt
	 * @return true, wenn Schreibvorgang erfolgreich, false sonst
	 */
	public boolean speichereProdukt(Produkt a) throws IOException {
		try{
			schreibeDaten(new Integer(a.getID()).toString());
			schreibeDaten(a.getProduktName());
			schreibeDaten(new Float(a.getProduktPreis()).toString());
			schreibeDaten(new Integer(a.getProduktMenge()).toString());
			schreibeDaten(new Integer(a.getProduktinhalt()).toString());
			schreibeDaten(a.getType());
			return true;
		}catch(Exception e){ 
			e.printStackTrace();
			return false;}
	}
	
	
	public Mitarbeiter ladeMitarbeiter() throws IOException{
		String MitId = leseDaten();
		if(MitId == null){
			return null;
		}

        String password = leseDaten();
        String email = leseDaten();
        String vorname = leseDaten();
        String nachname = leseDaten();
        String wohnort = leseDaten();
		int plz = Integer.parseInt(leseDaten());
        String strUndHausNr = leseDaten();
        
        return new Mitarbeiter(MitId,password,email,vorname,nachname,wohnort,plz,strUndHausNr);
	}

	
	
//###################KUNDEN#############################################################

	public Kunde ladeKunde() throws IOException {
		String benutzername = leseDaten();
		if(benutzername == null){
			return null;
		}
		String password = leseDaten();
		String email = leseDaten();
		String vorname = leseDaten();
		String nachname = leseDaten();
		String wohnort = leseDaten();
		int plz = Integer.parseInt(leseDaten());
		String strUndHausNr = leseDaten();
		float umsatz = Float.parseFloat(leseDaten());
		
		return new Kunde(benutzername,password,email,vorname,nachname,wohnort,plz,strUndHausNr,umsatz);
	}

	public boolean speichereKunde(Kunde k) throws IOException {
		
		schreibeDaten(k.getBenutzername());
		schreibeDaten(k.getPassword());
		schreibeDaten(k.getEmail());
		schreibeDaten(k.getVorname());
		schreibeDaten(k.getNachname());
		schreibeDaten(k.getWohnort());
		schreibeDaten(new Integer (k.getPLZ()).toString());
		schreibeDaten(k.getStrasseUndHausnummer());
		schreibeDaten(new Float(k.getUmsatz()).toString());
		
		return true;
	}

	

	public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException {
		schreibeDaten(m.getMitarbeiterID());
		schreibeDaten(m.getPassword());
		schreibeDaten(m.getEmail());
		schreibeDaten(m.getVorname());
		schreibeDaten(m.getNachname());
		schreibeDaten(m.getWohnort());
		schreibeDaten(new Integer (m.getPLZ()).toString());
		schreibeDaten(m.getStrasseUndHausnummer());
		
		return true;
	}

	
	private void schreibeDaten(String daten) {
		if (writer != null)
			writer.println(daten);
	}

	public String ladeMitLogFile() throws IOException {
		String einLogFile = leseDaten();
		if(einLogFile == null){
			return null;
		}
		else return einLogFile;
	}
	
	private String leseDaten() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	
	@Override
	public void speichereKunLogFile(String s) {
		schreibeDaten(s);
	}
	
	
	public void speichereMitLogFile(String s) {
		schreibeDaten(s);
	}

	
	public void aktualisiereLogfile(int produktID, String produktName, int neueAnzahl){
		String logfileName = "ProduktLogFiles\\"+produktID+".log";
		Calendar cal = Calendar.getInstance();
		int aktuellerTag = cal.get(Calendar.DAY_OF_MONTH); 

		try
		{
			FileWriter fw = new FileWriter(logfileName, true);

			fw.write("\n\n"+heute()+" "+neueAnzahl);
			
			fw.flush();
			fw.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	
	public void schreibeProduktLog(int produktID, String produktName, int produktAnzahl){
		String logfileName = "ProduktLogFiles\\"+produktID+".log";
        
		try
		{
			FileWriter fw = new FileWriter(logfileName);

			
			fw.write(	"Produkt-ID: 	"+produktID+"\n"+
						"Produktname: 	"+produktName+"\n"+
						"erstellt am:	"+heute()+"\n"+"\n"+"\n"+
						"+++Statistik+++"+"\n"+
						"Tag  / Anzahl"+"\n"+
						heute()+" "+produktAnzahl
					);
			
			fw.flush();
			fw.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	
	
	public void renameLogfile(int alteID, int neueID){
	    File f = new File("ProduktLogFiles\\"+alteID+".log"); 
	    f.renameTo(new File("ProduktLogFiles\\"+neueID+".log"));
	}
	
	public LinkedHashMap<String, String> getDatenAusLogfile(int produktID){
		String logfileName = "ProduktLogFiles\\"+produktID+".log";
		LinkedHashMap<String, String> daten = new LinkedHashMap<String, String>(); 
		String d;
		try
		{
		    FileReader fr = new FileReader(logfileName);
		    BufferedReader br = new BufferedReader(fr);

		    int zeilenStart=6; 
			for (int i=0;i<zeilenStart;i++){
				br.readLine();
			}	
			int anzahlZeilen = logFileZeilenlaenge(produktID);
			result = new String[anzahlZeilen -6];
			while (br.readLine() != null){
				d = br.readLine();
				StringTokenizer st = new StringTokenizer(d);
				while(st.hasMoreTokens()){
					String a = st.nextToken();
					String b = st.nextToken();
					daten.put(a, b);
				}
			}
		    
		} catch (Exception exp) {}

		return daten;
			
	}
	public int logFileZeilenlaenge(int produktID){
		String logfileName = "ProduktLogFiles\\"+produktID+".log";
		int anzZeilen = 0;
		try{
	    FileReader fr = new FileReader(logfileName);
	    BufferedReader br = new BufferedReader(fr);
		while (br.readLine() != null){
			anzZeilen++;
		}
		} catch (Exception exp) {}
		return anzZeilen;
	}
	
	public void loescheLogfile(int produktID){
	    File f = new File("ProduktLogFiles\\"+produktID+".log"); 
		if(f.exists()){
			f.delete();
		}
	}

	
	public static String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());
	}
	
    private String heute(){
    	Date dt = new Date();    	
    	SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
    	String today = format.format( dt ) ;
        return today;
    }

}

