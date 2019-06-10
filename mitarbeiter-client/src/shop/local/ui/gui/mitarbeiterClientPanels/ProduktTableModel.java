package shop.local.ui.gui.mitarbeiterClientPanels;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import shop.local.ui.gui.MitarbeiterClient;
import shop.local.valueobjects.Produkt;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
public class ProduktTableModel extends DefaultTableModel {
	
	
		public ProduktTableModel(List<Produkt> produkt, Vector<String> columnNames) throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException {
			super();
			
			Vector rows = new Vector();
			
			for (Produkt art: produkt) {
				Vector artikelVector = new Vector();
				artikelVector.add(art.getID());
				artikelVector.add(art.getProduktName());
				artikelVector.add(art.getProduktPreis());
				artikelVector.add(art.getProduktMenge());
				artikelVector.add(art.getProduktinhalt());
				artikelVector.add(art.getType());
				rows.add(artikelVector);
			}
			this.setDataVector(rows, columnNames);
			
			addTableModelListener(new MyTableModelListener());
		}
		private class MyTableModelListener implements TableModelListener{
			public void tableChanged(TableModelEvent e) {
			}
		}

}
