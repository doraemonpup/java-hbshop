package shop.local.ui.gui.mitarbeiterClientPanels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.ui.gui.MitarbeiterClient;


public class PanelMitarbeiterliste extends javax.swing.JPanel {

	private Vector<String> mitarbeiterSpaltenNamen;
	private Vector<Mitarbeiter> mitarbeiter = new Vector<Mitarbeiter>();;
	private JTable table;
	private JPanel listeZeigen;
	private MitarbeiterTableModel mitarbeiterModel;
	
	public PanelMitarbeiterliste() {
		super();
		try {
			{
				final BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
				this.setLayout(thisLayout);
				this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			}
			showMitarbeiter();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final ProduktExistiertBereitsException e) {
			e.printStackTrace();
		} catch (final MitarbeiterExistiertBereitsException e) {
			e.printStackTrace();
		}
	}
	
	private void showMitarbeiter() throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException{

		
		mitarbeiterSpaltenNamen = new Vector<String>();
		mitarbeiterSpaltenNamen.add("ID");
		mitarbeiterSpaltenNamen.add("Vorname");
		mitarbeiterSpaltenNamen.add("Nachname");
		mitarbeiterSpaltenNamen.add("E-Mail");
		mitarbeiterSpaltenNamen.add("Wohnort");
		mitarbeiterSpaltenNamen.add("Plz");
		mitarbeiterSpaltenNamen.add("Str./Nr.");
		mitarbeiter = MitarbeiterClient.sf.gibAlleMitarbeiter();

		table = new JTable(new MitarbeiterTableModel(mitarbeiter, mitarbeiterSpaltenNamen));
		table.setSelectionBackground(new java.awt.Color(124,156,173));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed( false ); 
		table.setAutoCreateRowSorter(true);
		
		//Tabelle erstellen:
		mitarbeiterModel = new MitarbeiterTableModel(mitarbeiter, mitarbeiterSpaltenNamen);
		table.setModel(mitarbeiterModel);
		final JScrollPane tablePane = new JScrollPane(table);
	
		listeZeigen = new JPanel();
		listeZeigen.add(tablePane);
		tablePane.setPreferredSize(new java.awt.Dimension(650, 600));
		tablePane.setMaximumSize(new Dimension(1000,1000));
		tablePane.setMinimumSize(new Dimension(550,500));
       	this.add(listeZeigen);
       	
      	final JButton refresh = new JButton(new ImageIcon("iconset/b_icons/refresh.png"));
       	this.add(refresh);
       	refresh.setText("Refresh");
       	refresh.setAlignmentX(CENTER_ALIGNMENT);
       	refresh.setPreferredSize(new Dimension(110,30));
       	refresh.setMaximumSize(new Dimension(110,30));
       	refresh.setMinimumSize(new Dimension(110,30));
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e)  {
				mitarbeiterModel.fireTableDataChanged();
				mitarbeiterModel.fireTableStructureChanged(); 
				try {
					mitarbeiter = MitarbeiterClient.sf.gibAlleMitarbeiter();
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
				try {
					mitarbeiterModel = new MitarbeiterTableModel(mitarbeiter, mitarbeiterSpaltenNamen);
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
				table.setModel(mitarbeiterModel);
				table.repaint();
			}
		});
	}

}
