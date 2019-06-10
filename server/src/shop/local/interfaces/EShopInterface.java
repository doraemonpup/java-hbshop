package shop.local.interfaces;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Vector;

import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.ProduktNichtGefundenException;
import shop.local.valueobjects.*;


public interface EShopInterface {

	public abstract void schreibeKunde() throws IOException;

	public abstract boolean fuegeCUIMitarbeiterEin(String mitarbeiterID,
			String password, String email, String vorname, String nachname);

	public abstract boolean fuegeMitarbeiterEin(String mitarbeiterID,
			String password, String email, String vorname, String nachname,
			String wohnort, int plz, String strasseNr) throws IOException;

	public abstract boolean fuegeKundeEin(String benutzername, String password,
			String email, String vorname, String nachname) throws IOException;

	public abstract Kunde KundenLogin(String b, String p);

	public abstract Mitarbeiter mitarbeiterLogin(String id, String pw)
			throws Exception;

	public abstract void fuegeNeueKundeEin() throws IOException;
	
	public abstract boolean fuegeProduktEin(int id, String name, float preis,
			int menge, int produktinhalt, String type)
			throws ProduktExistiertBereitsException;

	public abstract Vector<Produkt> gibAlleProdukt();

	public abstract Vector<Kunde> gibAlleKunden();

	public abstract Vector<Mitarbeiter> gibAlleMitarbeiter();

	public abstract boolean entfernenProdukt(Produkt a);

	public abstract boolean entferneMitarbeiter(Mitarbeiter m);

	public abstract void schreibeProdukt() throws IOException;

	public abstract void schreibeMitarbeiter() throws IOException;
	
	public abstract Vector<Produkt> sucheNachID(String id) throws ProduktNichtGefundenException;

	public abstract Vector<Produkt> sucheNachName(String name) throws ProduktNichtGefundenException;

	public abstract Produkt sucheProdukt(String id) throws ProduktNichtGefundenException;

	public abstract Mitarbeiter sucheMitarbeiter(String suchbegriff);

	public abstract void printRechnung(Vector<GewaehltesProdukt> warenkorb,
			float summe);

	public void aktualisiereKundenDaten(Kunde k, String benutzername,
			String password, String vorname, String nachname, String mail,
			String wohnort, int plz, String strasseUndNr,
			String benutzernameAlt, String passwordAlt, String emailAlt,
			String nachnameAlt, String vornameAlt, String wohnortAlt,
			int plzalt, String strasseUndNrAlt);

	public void aktualisiereProduktDaten(Produkt a, int altID,
			String altName, float altPreis, int altLager, int altStueck,
			String altTyp, int neuID, String neuName, float neuPreis,
			int neuLager, int neuStueck, String neuTyp);

	public void aktualisiereMitarbeiterDaten(Mitarbeiter m,
			String benutzername, String password, String vorname,
			String nachname, String mail, String wohnort, int plz,
			String strasseUndNr, String benutzernameAlt, String passwordAlt,
			String emailAlt, String nachnameAlt, String vornameAlt,
			String wohnortAlt, int zalt, String strasseUndNrAlt);

	public abstract void setzeBesitzer(Kunde k);

	public abstract void setzeMitarbeiter(Mitarbeiter m);

	public abstract void setProduktMenge(Vector<GewaehltesProdukt> warenkorb)
			throws Exception;

	public abstract void produktSortierenNachID() throws IOException;

	public abstract void schreibeLogFile(String s) throws IOException;

	public abstract void setProduktMenge(Produkt suchArtikel, int i)
			throws IOException;

	public abstract void addKunLogfile(Vector<GewaehltesProdukt> warenkorb);

	public abstract boolean pruefeProdukt(String stringId) throws ProduktExistiertBereitsException;

	public abstract boolean validateEmail(String eMail);

	public abstract boolean validateFloat(String floatString);

	public abstract void schreibeProduktLogfile(int proID, String produktName,
			int anz);

	public abstract void aktualisiereLogfile(int produktID, String produktName,
			int neueAnzahl);

	public abstract void renameLogfile(int alteID, int neueID);

	public abstract LinkedHashMap<String, String> getDatenAusLogfile(
			int produktID);

	public abstract void loescheLogfile(int produktID);
	
	public abstract Vector<Produkt> sucheProduktNachName(String suchbegriff);

	public abstract void setKundendaten(String benutzername, String vorname, String nachname,
			String password, String email, String strundhausnummer, String plz,
			String wohnort);
	
	public abstract Rechnung erstellenNeueRechnung(Vector warenkorb, float gesamteSumme,Kunde benutzer);

	public abstract float berechnenSumme(Vector<GewaehltesProdukt> warenkorb);

	public abstract String now(String string);

	public abstract void setUmsatz(Kunde k, float gesamteSumme);

}