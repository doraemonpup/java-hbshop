package shop.local.ui.gui.mitarbeiterClientPanels;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableModel;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.ui.gui.MitarbeiterClient;
import shop.local.valueobjects.Kunde;


public class PanelKundenliste extends javax.swing.JPanel {


	private Vector<String> kundenSpaltenNamen;
	private JTable table;
	private Vector<Kunde> kunden;
	private JPanel listeZeigen;
	
	public PanelKundenliste() throws IOException{
		super();

		try {
			{
				this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			}
			showKunden();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProduktExistiertBereitsException e) {
			e.printStackTrace();
		} catch (MitarbeiterExistiertBereitsException e) {
			e.printStackTrace();
		}
	}
	
	
	private void showKunden() throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException{
		kunden = new Vector<Kunde>();
		
		kundenSpaltenNamen = new Vector<String>();
		kundenSpaltenNamen.add("Benutzername");
		kundenSpaltenNamen.add("Vorname");
		kundenSpaltenNamen.add("Nachname");
		kundenSpaltenNamen.add("E-Mail");
		kundenSpaltenNamen.add("Wohnort");
		kundenSpaltenNamen.add("Plz");
		kundenSpaltenNamen.add("Str./Nr.");
		kundenSpaltenNamen.add("Umsatz");
		kundenSpaltenNamen.add("Password");
		kunden = MitarbeiterClient.sf.gibAlleKunden();

		table = new JTable(new KundenTableModel(kunden, kundenSpaltenNamen));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed( false ); 
		table.setAutoCreateRowSorter(true);
		
		
		//Tabelle erstellen:
		TableModel kundenModel = new KundenTableModel(kunden, kundenSpaltenNamen);
		table.setModel(kundenModel);

		JScrollPane tablePane = new JScrollPane(table);
	
		listeZeigen = new JPanel();
		listeZeigen.add(tablePane);
		
		tablePane.setPreferredSize(new java.awt.Dimension(650, 600));
		tablePane.setMaximumSize(new Dimension(1000,1000));
		tablePane.setMinimumSize(new Dimension(550,500));
       	this.add(listeZeigen, "Kundenliste");
	}

}
