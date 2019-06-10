package shop.local.ui.gui.kundenClientPanels;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JFrame;
import javax.swing.table.TableModel;

import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.valueobjects.Produkt;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PanelProduktliste extends JTable implements TableModelListener {


	private JTable table;
	private JPanel listeAusgeben;
	private Vector<String> produktSpaltenNamen;
	private Vector<Produkt> produkt;
	protected TableModel produktModel;
	private JScrollPane tablePane;
	
	private Object WertID;
	private Object WertName;
	private Object WertPreis;
	private Object WertLager;
	private Object WertStueck;
	private Object WertTyp;
	
	private String ID ;
	private String Name ;
	private String Preis ;
	private String Lager ;
	private String Stueck ;
	private String Typ ;
	
	private static int id = 0;
	private static String name;
	private static float preis;
	private static int lager;
	private static int stueck;
	private static String typ;
	
	
	
	private ListSelectionModel listSelectionModel;
	
	public PanelProduktliste(){
		
		produktSpaltenNamen = new Vector<String>();
		produktSpaltenNamen.add("ID");
		produktSpaltenNamen.add("Name");
		produktSpaltenNamen.add("Preis (€)");
		produktSpaltenNamen.add("Im Lager");
		produktSpaltenNamen.add("Menge");
		produktSpaltenNamen.add("Kategorien");
	
	}
	
	
	public void ErstelltTableModel(Vector<Produkt> a){
		
		// Tabelle erstellen mit eingegebenen Vecktor und mit Spaltennamen 
		table = new JTable(new ProduktTableModel(a, produktSpaltenNamen));
		// automatisch sortieren 
		table.setAutoCreateRowSorter(true);
		table.getModel().addTableModelListener(this);
		table.setSelectionBackground(new java.awt.Color(124,156,173));
		
		produktModel = new ProduktTableModel(a, produktSpaltenNamen);
		produktModel.addTableModelListener(table);
		table.setModel(produktModel);
		table.setSize(690, 445);
	    tablePane = new JScrollPane(table);
	
		tablePane.setPreferredSize(new java.awt.Dimension(690, 445));
		tablePane.setMaximumSize(new Dimension(540,455));
		tablePane.setMinimumSize(new Dimension(550,445));
		tablePane.setSize(690, 445);
    
       	
       	listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
	}
	
	public JScrollPane getScollPanel(){
		return tablePane;
	}


	
	class SharedListSelectionHandler implements ListSelectionListener {


		public void valueChanged(ListSelectionEvent e) { 
	 	   
	        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

	            int minIndex = lsm.getMinSelectionIndex();
	            int maxIndex = lsm.getMaxSelectionIndex();
	            for (int i = minIndex; i <= maxIndex; i++) {
	                if (lsm.isSelectedIndex(i)) {
	                    
	                	WertID = 	    table.getValueAt(i,0);
	                	WertName = 	    table.getValueAt(i,1);
	                	WertPreis = 	table.getValueAt(i,2);
	                	WertLager = 	table.getValueAt(i,3);
	                	WertStueck = 	table.getValueAt(i,4);
	                	WertTyp = 		table.getValueAt(i,5);
	             	   
	             	   // wandeln in String um
	             	    ID = String.valueOf( WertID);
	             	    Name = String.valueOf(WertName);
	             	    Preis = String.valueOf(WertPreis);
	             	    Lager = String.valueOf(WertLager);
	             	    Stueck = String.valueOf(WertStueck);
	             	    Typ = String.valueOf(WertTyp);            	   
	             	 	
	             	    id = Integer.parseInt(ID);
	             	    name = Name;
	             	    preis = Float.parseFloat(Preis);
	             	    lager = Integer.parseInt(Lager);
	             	    stueck = Integer.parseInt(Stueck);
	             	    typ = Typ;
	        			
	                }
	            }
	     }
		
	}
	
	public Produkt getGewaehltesProdukt() {
		if(id == 0){
			return null;
		}
		else
		return new Produkt(id, name, preis, lager, stueck, typ);
	}



}
