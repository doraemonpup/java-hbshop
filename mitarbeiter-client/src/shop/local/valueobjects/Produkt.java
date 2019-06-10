package shop.local.valueobjects;

public class Produkt {
	protected int produktID;
	protected String produktName;
	protected float produktPreis;
	protected int produktMenge;
	protected int produktinhalt;
	private String type;
	
	public Produkt(int id,String name,float preis,int menge,int produktinhalt,String type){
		this.produktID = id;
		this.produktName = name;
		this.produktPreis = preis;
		this.produktMenge = menge;
		this.produktinhalt = produktinhalt;
		this.type = type;
	}
	public Produkt(int id,String name,float preis,int menge){
		this.produktID = id;
		this.produktName = name;
		this.produktPreis = preis;
		this.produktMenge = menge;
	}
	public int getID(){
		return produktID;
	}
	public void setID(int neuId){
		this.produktID = neuId;
	}
	
	public String getProduktName(){
		return produktName;
	}
	public void setProduktName(String name){
		this.produktName = name;
	}
	
	public float getProduktPreis(){
		return produktPreis;
	}
	public void setProduktPreis(float neuPreis){
		this.produktPreis = neuPreis;
	}
	
	public int getProduktMenge(){
		return produktMenge;
	}
	public void setProduktMenge(int menge){
		this.produktMenge = menge;
	}
	
	public String getType(){
		return type;
	}
	public void setType(String neuType){
		type = neuType;
	}
	
	public int getProduktinhalt(){
		return produktinhalt;
	}
	public void setProduktinhalt(int neuInhalt){
		produktinhalt = neuInhalt;
	}

	
	public String toString() {
		return ("ID: " + produktID + " / Name: " + produktName + " / Preis: " + produktPreis +"/ Menge: "+produktMenge);
	}
}
