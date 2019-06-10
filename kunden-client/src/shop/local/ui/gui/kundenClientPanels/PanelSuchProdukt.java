package shop.local.ui.gui.kundenClientPanels;


import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import shop.local.valueobjects.Produkt;

public class PanelSuchProdukt extends JPanel implements TableModelListener {

	private JTable table;

	private JPanel listeAusgeben;
	private Vector<String> produktSpaltenNamen;
	private Vector<Produkt> produkt;
	private Vector<Produkt> neueProdukt;
	protected TableModel produktModel;
	
	@Override
	public void tableChanged(TableModelEvent arg0) {

	}
	
	public PanelSuchProdukt(Vector produkt){
		super();
		
		produktSpaltenNamen = new Vector<String>();
		produktSpaltenNamen.add("ID");
		produktSpaltenNamen.add("Name");
		produktSpaltenNamen.add("Preis");
		produktSpaltenNamen.add("im Lager");
		produktSpaltenNamen.add("Stueck/Pack");
		produktSpaltenNamen.add("Typ");
		this.produkt = produkt;
		
		table = new JTable(new ProduktTableModel(produkt, produktSpaltenNamen));
	
		table.getModel().addTableModelListener(this);
		table.setSelectionBackground(new java.awt.Color(124,156,173));
		
		//Tabelle erstellen:
		produktModel = new ProduktTableModel(produkt, produktSpaltenNamen);
		produktModel.addTableModelListener(table);
		table.setModel(produktModel);
		JScrollPane tablePane = new JScrollPane(table);
	
		listeAusgeben = new JPanel();
		listeAusgeben.add(tablePane);
		tablePane.setPreferredSize(new Dimension(690, 540));
		tablePane.setMaximumSize(new Dimension(540,750));
		tablePane.setMinimumSize(new Dimension(550,550));
		tablePane.setSize(690, 540);
       	this.add(listeAusgeben, "Artikelliste");
	}
	
	
	
	
	

}
