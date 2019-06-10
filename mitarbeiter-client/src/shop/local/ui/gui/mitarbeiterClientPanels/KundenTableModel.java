package shop.local.ui.gui.mitarbeiterClientPanels;

import javax.swing.table.DefaultTableModel;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import shop.local.ui.gui.MitarbeiterClient;
import shop.local.valueobjects.Kunde;

public class KundenTableModel extends DefaultTableModel {

	public KundenTableModel(List<Kunde> kunde, Vector columnNames) throws IOException {
		super();

		Vector rows = new Vector();
		
		for (Kunde kun: kunde) {
			Vector kundenVector = new Vector();
			kundenVector.add(kun.getBenutzername());
			kundenVector.add(kun.getVorname());
			kundenVector.add(kun.getNachname());
			kundenVector.add(kun.getEmail());
			kundenVector.add(kun.getWohnort());
			kundenVector.add(kun.getPLZ());
			kundenVector.add(kun.getStrasseUndHausnummer());
			kundenVector.add(kun.getUmsatz());
			kundenVector.add(kun.getPassword());
			
			rows.add(kundenVector);
		}
		this.setDataVector(rows, columnNames);
	}
}
