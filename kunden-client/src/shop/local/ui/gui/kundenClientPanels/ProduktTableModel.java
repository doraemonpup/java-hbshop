package shop.local.ui.gui.kundenClientPanels;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import shop.local.valueobjects.Produkt;

public class ProduktTableModel extends DefaultTableModel {
	
		public ProduktTableModel(List<Produkt> produkt, Vector<String> columnNames) {
			super();

			Vector rows = new Vector();
			
			for (Produkt art: produkt) {
				Vector artikelVector = new Vector();
				artikelVector.add(art.getID());
				artikelVector.add(art.getProduktName());
				artikelVector.add(art.getProduktPreis());
				artikelVector.add(art.getProduktMenge());
				artikelVector.add(art.getArtikelinhalt());
				artikelVector.add(art.getType());
				rows.add(artikelVector);
			}
			this.setDataVector(rows, columnNames);
		}
		
		
}
