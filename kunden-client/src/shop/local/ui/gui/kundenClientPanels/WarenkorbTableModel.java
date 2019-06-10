package shop.local.ui.gui.kundenClientPanels;

import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import shop.local.valueobjects.Produkt;
import shop.local.valueobjects.GewaehltesProdukt;

public class WarenkorbTableModel extends DefaultTableModel {
	
	public WarenkorbTableModel(List<GewaehltesProdukt> produkt, Vector<String> columnNames) {
		super();

		Vector rows = new Vector();
		
		for (GewaehltesProdukt art: produkt) {
			Vector artikelVector = new Vector();
			artikelVector.add(art.getID());
			artikelVector.add(art.getProduktName());
			artikelVector.add(art.getProduktPreis());
			artikelVector.add(art.getGekaufteAnzahl());

		}
		this.setDataVector(rows, columnNames);
	}
}
