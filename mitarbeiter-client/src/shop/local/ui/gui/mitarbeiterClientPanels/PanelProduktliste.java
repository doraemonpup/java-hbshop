package shop.local.ui.gui.mitarbeiterClientPanels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JFrame;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.ui.gui.MitarbeiterClient;
import shop.local.valueobjects.Produkt;


public class PanelProduktliste extends JPanel implements ActionListener{

	public static void main(String[] args) throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException {
		JFrame frame = new javax.swing.JFrame();
		new BoxLayout(frame.getContentPane(), javax.swing.BoxLayout.Y_AXIS);
		frame.getContentPane().add(new PanelProduktliste());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private JTable table;
	private JPanel listeAusgeben;
	private Vector<String> produktSpaltenNamen;
	private Vector<Produkt> produkt;
	private ProduktTableModel produktModel;
	private TableModelListener f;
	private JComboBox sort;	
	public PanelProduktliste(){
		
		super();
		try {
			{
				BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
				this.setLayout(thisLayout);
				this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			}
			showProdukt();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProduktExistiertBereitsException e) {
			e.printStackTrace();
		} catch (MitarbeiterExistiertBereitsException e) {
			e.printStackTrace();
		}
	}
	
	
	private void showProdukt() throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException{

		produktSpaltenNamen = new Vector<String>();
		produktSpaltenNamen.add("ID");
		produktSpaltenNamen.add("Name");
		produktSpaltenNamen.add("Preis");
		produktSpaltenNamen.add("Menge");
		produktSpaltenNamen.add("Stueck");
		produktSpaltenNamen.add("Typ");
		produkt = MitarbeiterClient.sf.gibAlleProdukt();

		table = new JTable(new ProduktTableModel(produkt, produktSpaltenNamen));
				
		table.setAutoCreateRowSorter(true);
		//GANZ WICHTIG!!! Sonst funktioniert Artikel-Bearbeiten nicht mehr:
		table.getTableHeader().setReorderingAllowed( false ); 
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new java.awt.Color(124,156,173));	
		
		table.getModel().addTableModelListener(new TableModelListener() {

		      public void tableChanged(TableModelEvent e) {
			         System.out.println(e);
			         System.out.println("change");
		      }
		});
		
		produktModel = new ProduktTableModel(produkt, produktSpaltenNamen);
		produktModel.addTableModelListener(f);
		table.setModel(produktModel);
		table.setMinimumSize(new java.awt.Dimension(550, 550));
		table.setMaximumSize(new java.awt.Dimension(5000, 550));
		table.setRequestFocusEnabled(false);
		JScrollPane tablePane = new JScrollPane(table);
	
		listeAusgeben = new JPanel();
		listeAusgeben.add(tablePane);
		tablePane.setPreferredSize(new java.awt.Dimension(650, 600));
		tablePane.setMaximumSize(new Dimension(1000,1000));
		tablePane.setMinimumSize(new Dimension(550,500));
       	this.add(listeAusgeben);
       	
       	JPanel p = new JPanel();
       	{
	       	JButton refresh = new JButton(new ImageIcon("iconset/b_icons/refresh.png"));
	       	p.add(refresh);
	       	refresh.setText("Refresh");
	       	refresh.setAlignmentX(CENTER_ALIGNMENT);
	       	refresh.setPreferredSize(new Dimension(110,30));
	       	refresh.setMaximumSize(new Dimension(110,30));
	       	refresh.setMinimumSize(new Dimension(110,30));
			refresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
					produktModel.fireTableDataChanged();
					try {
						produkt = MitarbeiterClient.sf.gibAlleProdukt();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					try {
						produktModel = new ProduktTableModel(produkt, produktSpaltenNamen);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ProduktExistiertBereitsException e1) {
						e1.printStackTrace();
					} catch (MitarbeiterExistiertBereitsException e1) {
						e1.printStackTrace();
					}
					table.setModel(produktModel);
				}
			});
       	}
		{
			p.add(Box.createRigidArea(new Dimension(40,15)));
		}
       	{
       		this.add(p);
       	}
		

		
	}
	
	public void aktualisiereTable(){
		produktModel.fireTableDataChanged();
		
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
		produktModel.setDataVector(rows, produktSpaltenNamen);
		table.setModel(produktModel);
		produktModel.fireTableDataChanged();

		}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(sort)) {
			if(sort.getSelectedIndex() == 1){
				System.out.println("Pow");
			}
			if(sort.getSelectedIndex() != 1){
				System.out.println("Nich Pow");
			}
		}
		
	}


	

}
