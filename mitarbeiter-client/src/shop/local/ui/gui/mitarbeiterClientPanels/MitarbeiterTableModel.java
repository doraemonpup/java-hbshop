package shop.local.ui.gui.mitarbeiterClientPanels;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import shop.local.ui.gui.MitarbeiterClient;
import shop.local.valueobjects.Mitarbeiter;

public class MitarbeiterTableModel extends DefaultTableModel {
	
		public MitarbeiterTableModel(List<Mitarbeiter> mitarbeiter, Vector columnNames) throws IOException {
			super();

			Vector rows = new Vector();
			
			for (Mitarbeiter mit: mitarbeiter) {
				Vector mitarbeiterVector = new Vector();
				mitarbeiterVector.add(mit.getMitarbeiterID());
				mitarbeiterVector.add(mit.getVorname());
				mitarbeiterVector.add(mit.getNachname());
				mitarbeiterVector.add(mit.getEmail());
				mitarbeiterVector.add(mit.getWohnort());
				mitarbeiterVector.add(mit.getPLZ());
				mitarbeiterVector.add(mit.getStrasseUndHausnummer());
				mitarbeiterVector.add(mit.getPassword());
				rows.add(mitarbeiterVector);
			}
			this.setDataVector(rows, columnNames);
		}
}
