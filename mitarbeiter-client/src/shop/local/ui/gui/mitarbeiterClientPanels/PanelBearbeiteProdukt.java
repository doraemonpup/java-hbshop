package shop.local.ui.gui.mitarbeiterClientPanels;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.ui.gui.MitarbeiterClient;
import shop.local.valueobjects.Produkt;


public class PanelBearbeiteProdukt extends javax.swing.JPanel {

	private JTable table;
	private ListSelectionModel listSelectionModel;
	private JButton buttonAbbrechen;
	private Component popup;
	private JButton buttonAktualisieren;
	private JLabel labelAlterWert;
	private JLabel labelNeuerWert;
	private JLabel labelID;
	private JTextField textfieldIDneu;
	private JTextField textfieldID;
	private JLabel labelName;
	private JTextField textfieldNameNeu;
	private JTextField textfieldName;
	private JTextField textfieldPreis;
	private JLabel labelPreis;
	private JTextField textfieldPreisNeu;
	private JLabel labelLager;
	private JTextField textfieldLagerNeu;
	private JTextField textfieldLager;
	private JTextField textfieldStueckNeu;
	private JTextField textfieldStueck;
	private Component labelStueck;
	private JTextField textfieldTyp;
	private JLabel labelTyp;
	private Vector<String> produktSpaltenNamen;
	private Vector<Produkt> produkt;
	private ProduktTableModel produktModel;
	private JPanel listeAusgeben;
	
	private Object alterWertID;
	private Object alterWertName;
	private Object alterWertPreis;
	private Object alterWertLager;
	private Object alterWertStueck;
	private Object alterWertTyp;
	private JButton buttonLoeschen;
	
	private String idAlt;
	private String altName;
	private String preisAlt;
	private String lagerAlt;
	private String stueckAlt;
	private String altTyp;
	private JComboBox produktTypen;
	private boolean preis = true;

	
	public PanelBearbeiteProdukt() {
		super();
		initGUI();
		try {
			showProdukt();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProduktExistiertBereitsException e) {
			e.printStackTrace();
		} catch (MitarbeiterExistiertBereitsException e) {
			e.printStackTrace();
		}
		initRest();

	}

	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(800, 600));
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initRest() throws java.lang.NumberFormatException {
		{
			this.add(Box.createRigidArea(new Dimension(200,20)));
		}
		{
			JPanel pNeuAlt = new JPanel();
			this.add(pNeuAlt);
			BoxLayout p1Layout = new BoxLayout(pNeuAlt, javax.swing.BoxLayout.X_AXIS);
			pNeuAlt.setLayout(p1Layout);
			{
				pNeuAlt.add(Box.createRigidArea(new Dimension(100,20)));
			}
			{
				labelAlterWert = new JLabel();
				pNeuAlt.add(labelAlterWert);
				labelAlterWert.setText("Alte Werte");
				labelAlterWert.setAlignmentX(CENTER_ALIGNMENT);
				labelAlterWert.setPreferredSize(new Dimension(130,20));
				labelAlterWert.setMaximumSize(new Dimension(130,20));
				labelAlterWert.setMinimumSize(new Dimension(130,20));
				labelAlterWert.setFont(new java.awt.Font("Arial",1,12));
			}
			{
				pNeuAlt.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				labelNeuerWert = new JLabel();
				pNeuAlt.add(labelNeuerWert);
				labelNeuerWert.setText("Neue Werte");
				labelNeuerWert.setAlignmentX(CENTER_ALIGNMENT);
				labelNeuerWert.setPreferredSize(new Dimension(130,20));
				labelNeuerWert.setMaximumSize(new Dimension(130,20));
				labelNeuerWert.setMinimumSize(new Dimension(130,20));
				labelNeuerWert.setFont(new java.awt.Font("Arial",1,12));
			}

		}
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pArtikelID = new JPanel();
			this.add(pArtikelID);
			BoxLayout p1Layout = new BoxLayout(pArtikelID, javax.swing.BoxLayout.X_AXIS);
			pArtikelID.setLayout(p1Layout);
			{
				labelID = new JLabel();
				pArtikelID.add(labelID);
				labelID.setText("Produkt ID");
				labelID.setAlignmentX(CENTER_ALIGNMENT);
				labelID.setPreferredSize(new Dimension(100,20));
				labelID.setMaximumSize(new Dimension(100,20));
				labelID.setMinimumSize(new Dimension(100,20));		
			}
			{
				textfieldID = new JTextField();
				pArtikelID.add(textfieldID);
				textfieldID.setAlignmentX(CENTER_ALIGNMENT);
				textfieldID.setPreferredSize(new Dimension(130,20));
				textfieldID.setMaximumSize(new Dimension(130,20));
				textfieldID.setMinimumSize(new Dimension(130,20));
				textfieldID.setEnabled(false);
			}
			{
				pArtikelID.add(Box.createRigidArea(new Dimension(50,20)));
			}

	       	{
	       		textfieldIDneu = new JTextField();
	       		pArtikelID.add(textfieldIDneu);
	       		textfieldIDneu.setPreferredSize(new Dimension(130,20));
	       		textfieldIDneu.setMaximumSize(new Dimension(130,20));
	       		textfieldIDneu.setMinimumSize(new Dimension(130,20));
	       		textfieldIDneu.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
					    char c = e.getKeyChar();
					    if (!((Character.isDigit(c) ||
					    		(c == KeyEvent.VK_BACK_SPACE) ||
					    		(c == KeyEvent.VK_DELETE)))) {
					        	getToolkit().beep();
					        	e.consume();
					    }
					    if (textfieldIDneu.getText().length() >= 4) {
					    	c = KeyEvent.VK_BACK_SPACE;
					    	getToolkit().beep();
					    	e.consume();
					    }

					}
				});
	       	}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pArtikelName = new JPanel();
			this.add(pArtikelName);
			BoxLayout p2Layout = new BoxLayout(pArtikelName, javax.swing.BoxLayout.X_AXIS);
			pArtikelName.setLayout(p2Layout);
	       	{
	       		labelName = new JLabel();
	       		pArtikelName.add(labelName);
	       		labelName.setText("Name");
	       		labelName.setPreferredSize(new Dimension(100,20));
	       		labelName.setMaximumSize(new Dimension(100,20));
	       		labelName.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldName = new JTextField();
	       		pArtikelName.add(textfieldName);
	       		textfieldName.setPreferredSize(new Dimension(130,20));
	       		textfieldName.setMaximumSize(new Dimension(130,20));
	       		textfieldName.setMinimumSize(new Dimension(130,20));
	       		textfieldName.setEnabled(false);

	       	}
			{
				pArtikelName.add(Box.createRigidArea(new Dimension(50,20)));
			}
	       	{
	       		textfieldNameNeu = new JTextField();
	       		pArtikelName.add(textfieldNameNeu);
	       		textfieldNameNeu.setPreferredSize(new Dimension(130,20));
	       		textfieldNameNeu.setMaximumSize(new Dimension(130,20));
	       		textfieldNameNeu.setMinimumSize(new Dimension(130,20));

	       	}
	    
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pPreis = new JPanel();
			this.add(pPreis);
			BoxLayout p3Layout = new BoxLayout(pPreis, javax.swing.BoxLayout.X_AXIS);
			pPreis.setLayout(p3Layout);
	       	{
	       		labelPreis = new JLabel();
	       		pPreis.add(labelPreis);
	       		labelPreis.setText("Preis");
	       		labelPreis.setPreferredSize(new Dimension(100,20));
	       		labelPreis.setMaximumSize(new Dimension(100,20));
	       		labelPreis.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldPreis = new JTextField();
	       		pPreis.add(textfieldPreis);
	       		textfieldPreis.setPreferredSize(new Dimension(130,20));
	       		textfieldPreis.setMaximumSize(new Dimension(130,20));
	       		textfieldPreis.setMinimumSize(new Dimension(130,20));
	       		textfieldPreis.setEnabled(false);
	       	}
			{
				pPreis.add(Box.createRigidArea(new Dimension(50,20)));
			}
	       	{
	       		textfieldPreisNeu = new JTextField();
	       		pPreis.add(textfieldPreisNeu);
	       		textfieldPreisNeu.setPreferredSize(new Dimension(130,20));
	       		textfieldPreisNeu.setMaximumSize(new Dimension(130,20));
	       		textfieldPreisNeu.setMinimumSize(new Dimension(130,20));
	       		textfieldPreisNeu.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
					    char c = e.getKeyChar();
						    if (!((Character.isDigit(c) ||
						    		(c == KeyEvent.VK_BACK_SPACE) ||
						    		(c == KeyEvent.VK_PERIOD) ||
						    		(c == KeyEvent.VK_DELETE)))) {
						        	getToolkit().beep();
						        	e.consume();
						    }		
					}
				});
	       	}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pImLager = new JPanel();
			this.add(pImLager);
			BoxLayout p5Layout = new BoxLayout(pImLager, javax.swing.BoxLayout.X_AXIS);
			pImLager.setLayout(p5Layout);
	       	{
	       		labelLager = new JLabel("Im Lager");
	       		pImLager.add(labelLager);
	       		labelLager.setPreferredSize(new Dimension(100,20));
	       		labelLager.setMaximumSize(new Dimension(100,20));
	       		labelLager.setMinimumSize(new Dimension(100,20));
	       		
	       	}
	       	{
	       		textfieldLager = new JTextField();
	       		pImLager.add(textfieldLager);
	       		textfieldLager.setPreferredSize(new Dimension(130,20));
	       		textfieldLager.setMaximumSize(new Dimension(130,20));
	       		textfieldLager.setMinimumSize(new Dimension(130,20));
	       		textfieldLager.setEnabled(false);
	       		
	       	}
			{
				pImLager.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				textfieldLagerNeu = new JTextField();
				pImLager.add(textfieldLagerNeu);
				textfieldLagerNeu.setPreferredSize(new Dimension(130,20));
				textfieldLagerNeu.setMaximumSize(new Dimension(130,20));
				textfieldLagerNeu.setMinimumSize(new Dimension(130,20));
				textfieldLagerNeu.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
					    char c = e.getKeyChar();
					    if (!((Character.isDigit(c) ||
					    		(c == KeyEvent.VK_BACK_SPACE) ||
					    		(c == KeyEvent.VK_DELETE)))) {
					        	getToolkit().beep();
					        	e.consume();
					    }
					}
				});
			}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pStueck = new JPanel();
			this.add(pStueck);
			BoxLayout p5Layout = new BoxLayout(pStueck, javax.swing.BoxLayout.X_AXIS);
			pStueck.setLayout(p5Layout);
	       	{
	       		labelStueck = new JLabel("Stueck/Pack");
	       		pStueck.add(labelStueck);
	       		labelStueck.setPreferredSize(new Dimension(100,20));
	       		labelStueck.setMaximumSize(new Dimension(100,20));
	       		labelStueck.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldStueck = new JTextField();
	       		pStueck.add(textfieldStueck);
	       		textfieldStueck.setPreferredSize(new Dimension(130,20));
	       		textfieldStueck.setMaximumSize(new Dimension(130,20));
	       		textfieldStueck.setMinimumSize(new Dimension(130,20));
	       		textfieldStueck.setEnabled(false);
	       	}
			{
				pStueck.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				textfieldStueckNeu = new JTextField();
				pStueck.add(textfieldStueckNeu);
				textfieldStueckNeu.setPreferredSize(new Dimension(130,20));
				textfieldStueckNeu.setMaximumSize(new Dimension(130,20));
	       		textfieldStueckNeu.setMinimumSize(new Dimension(130,20));
	       		textfieldStueckNeu.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
					    char c = e.getKeyChar();
					    if (!((Character.isDigit(c) ||
					    		(c == KeyEvent.VK_BACK_SPACE) ||
					    		(c == KeyEvent.VK_DELETE)))) {
					        	getToolkit().beep();
					        	e.consume();
					    }

					}
				});
			}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pTyp = new JPanel();
			this.add(pTyp);
			BoxLayout p5Layout = new BoxLayout(pTyp, javax.swing.BoxLayout.X_AXIS);
			pTyp.setLayout(p5Layout);
	       	{
	       		labelTyp = new JLabel("Typ");
	       		pTyp.add(labelTyp);
	       		labelTyp.setPreferredSize(new Dimension(100,20));
	       		labelTyp.setMaximumSize(new Dimension(100,20));
	       		labelTyp.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldTyp = new JTextField();
	       		pTyp.add(textfieldTyp);
	       		textfieldTyp.setPreferredSize(new Dimension(130,20));
	       		textfieldTyp.setMaximumSize(new Dimension(130,20));
	       		textfieldTyp.setMinimumSize(new Dimension(130,20));
	       		textfieldTyp.setEnabled(false);
	       	}
			{
				pTyp.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				String[] artikelStrings = {"Bücher", "Musik, DVD & Games", "Computer & Büro", "Elektronik & Foto", "Küche, Haus & Garten", "Baumarkt & Auto", "Drogerie & Parfümerie", "Spielzeug & Baby", "Kleidung, Schuhe & Uhren", "Sport & Freizeit" };
				produktTypen = new JComboBox(artikelStrings);
				produktTypen.setSelectedIndex(0);
				pTyp.add(produktTypen);
				produktTypen.setPreferredSize(new Dimension(130,20));
				produktTypen.setMaximumSize(new Dimension(130,20));
				produktTypen.setMinimumSize(new Dimension(130,20));
			}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,12)));
		}
		{
			JPanel p4 = new JPanel();
			this.add(p4);
			BoxLayout p3Layout = new BoxLayout(p4, javax.swing.BoxLayout.X_AXIS);
			p4.setLayout(p3Layout);
	       	{
	       		buttonAktualisieren = new JButton(new ImageIcon("iconset/b_icons/refresh.png"));
	       		p4.add(buttonAktualisieren);
	       		buttonAktualisieren.setText("Aktualisieren");
	       		buttonAktualisieren.setPreferredSize(new Dimension(125,30));
	       		buttonAktualisieren.setMaximumSize(new Dimension(125,30));
	       		buttonAktualisieren.setMinimumSize(new Dimension(125,30));
	       		buttonAktualisieren.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						//alte Werte
						 idAlt = textfieldID.getText();        	   
						 altName = textfieldName.getText();           	   
						 preisAlt = textfieldPreis.getText();         	   
						 lagerAlt = textfieldLager.getText();  
						 stueckAlt = textfieldStueck.getText();  
						 altTyp = textfieldTyp.getText();  
						//neue Werte
						String idNeu = textfieldIDneu.getText();        	   
						String neuName = textfieldNameNeu.getText();           	   
						String preisNeu = textfieldPreisNeu.getText();  
						String lagerNeu = textfieldLagerNeu.getText();  
						String stueckNeu = textfieldStueckNeu.getText();  
						String neuTyp = (String) produktTypen.getSelectedItem();  				
						
						if(		textfieldID.getText().equals("")  ||	
								textfieldName.getText().equals("") ||
								textfieldPreis.getText().equals("") ||
								textfieldLager.getText().equals("") ||
								textfieldStueck.getText().equals("") )  {	
	    		
							JOptionPane errorMessage = new JOptionPane();
							errorMessage.setSize(400,300);
							JOptionPane.showMessageDialog(popup, "Bitte ein Produkt auswählen!","", JOptionPane.ERROR_MESSAGE);
						}
						
						else if(textfieldIDneu.getText().equals("")  ||	
								textfieldNameNeu.getText().equals("") ||
								textfieldPreisNeu.getText().equals("") ||
								textfieldLagerNeu.getText().equals("") ||
								textfieldStueckNeu.getText().equals("") )  {	
	    		
							JOptionPane errorMessage = new JOptionPane();
							errorMessage.setSize(400,300);
							JOptionPane.showMessageDialog(popup, "Bitte alle Felder ausfuellen!","", JOptionPane.ERROR_MESSAGE);
						}
						else {	
								boolean derPreis = true;
								float neuPreis ;
								try {
									neuPreis = Float.parseFloat(preisNeu);
								} catch (java.lang.NumberFormatException e1) {
									derPreis = false;
								}
								if(derPreis==false){
									JOptionPane errorMessage = new JOptionPane();
									errorMessage.setSize(400,300);
									JOptionPane.showMessageDialog(popup, "Der Preis muss eine ganze Zahl oder eine Kommazahl mit Punkt sein!","Fehler", JOptionPane.ERROR_MESSAGE);
								}
								else {
									int choice = JOptionPane.showConfirmDialog (null, "Wollen Sie das Produkt "+textfieldName.getText()+" wirklich aktualisieren?","Achtung", JOptionPane.YES_NO_OPTION);
									if(choice==0){
										int neuID = Integer.parseInt (idNeu);
										neuPreis = Float.parseFloat(preisNeu);
										int neuLager = Integer.parseInt (lagerNeu);
										int neuStueck = Integer.parseInt (stueckNeu);
										
										float altPreis = Float.parseFloat(preisAlt);
										int altID = Integer.parseInt (idAlt);
										int altLager = Integer.parseInt (lagerAlt);
										int altStueck = Integer.parseInt (stueckAlt);
										
										Produkt a = new Produkt(altID,altName,altPreis,altLager,altStueck,altTyp);
		
										MitarbeiterClient.sf.aktualisiereProduktDaten(a,  altID, altName, altPreis, altLager, altStueck, altTyp, neuID, neuName, neuPreis, neuLager, neuStueck, neuTyp);
		
										JOptionPane errorMessage = new JOptionPane();
										errorMessage.setSize(400,300);
										JOptionPane.showMessageDialog(popup, "Das Produkt wurde erfolgreich aktualisiert","Erfolg!", JOptionPane.INFORMATION_MESSAGE);
											//Felder wieder leeren
											textfieldIDneu.setText("");	
											textfieldNameNeu.setText("");
											textfieldPreisNeu.setText("");
											textfieldLagerNeu.setText("");
											textfieldStueckNeu.setText("");
											produktTypen.setSelectedIndex(0);
											//Liste aktualisieren
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
								}
						}
					}
				});	     	    
	       	}
			{
				p4.add(Box.createRigidArea(new Dimension(20,20)));
			}
	       	{
	       		buttonAbbrechen = new JButton(new ImageIcon("iconset/b_icons/delete.png"));
	       		p4.add(buttonAbbrechen);
	       		buttonAbbrechen.setText("Abbrechen");
	       		buttonAbbrechen.setPreferredSize(new Dimension(125,30));
	       		buttonAbbrechen.setMaximumSize(new Dimension(125,30));
	       		buttonAbbrechen.setMinimumSize(new Dimension(125,30));
	       		buttonAbbrechen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textfieldIDneu.setText("");	
						textfieldNameNeu.setText("");
						textfieldPreisNeu.setText("");
						textfieldLagerNeu.setText("");
						textfieldStueckNeu.setText("");
						produktTypen.setSelectedIndex(0);
					}
	       		}); 
	       	}

			{
				p4.add(Box.createRigidArea(new Dimension(20,20)));
			}
	       	{
	       		buttonLoeschen = new JButton(new ImageIcon("iconset/b_icons/remove.png"));
	       		p4.add(buttonLoeschen);
	       		buttonLoeschen.setText("Löschen");
	       		buttonLoeschen.setPreferredSize(new Dimension(125,30));
	       		buttonLoeschen.setMaximumSize(new Dimension(125,30));
	       		buttonLoeschen.setMinimumSize(new Dimension(125,30));
	       		buttonLoeschen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Action Event um Produkt zu loeschen:
						if(		textfieldIDneu.getText().equals("")  ||	
								textfieldNameNeu.getText().equals("") ||
								textfieldPreisNeu.getText().equals("") ||
								textfieldLagerNeu.getText().equals("") ||
								textfieldStueckNeu.getText().equals("") )  {	
	    		
							JOptionPane errorMessage = new JOptionPane();
							errorMessage.setSize(400,300);
							JOptionPane.showMessageDialog(popup, "Bitte alle Felder ausfuellen!","", JOptionPane.ERROR_MESSAGE);
						}
						else{
							int choice = JOptionPane.showConfirmDialog (null, "Wollen Sie wirklich das Produkt entfernen?","Achtung", JOptionPane.YES_NO_OPTION);
							if(choice==0){
								int altID = Integer.parseInt(textfieldID.getText());
								String altName = textfieldName.getText();
								float altPreis = Float.parseFloat(textfieldPreisNeu.getText());
								int altLager = Integer.parseInt (textfieldLager.getText());
								int altStueck = Integer.parseInt (textfieldStueck.getText());
								String altTyp = textfieldTyp.getText();
								
								Produkt a = new Produkt(altID,altName,altPreis,altLager,altStueck,altTyp);
								
									try {
										a = MitarbeiterClient.sf.sucheProdukt(textfieldID.getText());
									} catch (IOException e2) {
										e2.printStackTrace();
									}
									if(a != null){
										boolean removeOk = false;
										try {
											removeOk = MitarbeiterClient.sf.entfernenProdukt(a);
										} catch (IOException e2) {
											e2.printStackTrace();
										}
										if(removeOk == true){

										JOptionPane errorMessage = new JOptionPane();
										errorMessage.setSize(400,300);
										JOptionPane.showMessageDialog(popup, "Das Produkt "+altName +" wurde erfolgreich entfernt!","", JOptionPane.INFORMATION_MESSAGE);

										textfieldIDneu.setText("");	
										textfieldNameNeu.setText("");
										textfieldPreisNeu.setText("");
										textfieldLagerNeu.setText("");
										textfieldStueckNeu.setText("");
										produktTypen.setSelectedIndex(0);
									}
									else{
										JOptionPane errorMessage = new JOptionPane();
										errorMessage.setSize(400,300);
										JOptionPane.showMessageDialog(popup, "Das Produkt mit der ID "+altID +" wurde nicht gefunden!","", JOptionPane.ERROR_MESSAGE);	
										textfieldIDneu.setText("");	
										textfieldNameNeu.setText("");
										textfieldPreisNeu.setText("");
										textfieldLagerNeu.setText("");
										textfieldStueckNeu.setText("");
										produktTypen.setSelectedIndex(0);
									}
								}
							}
						}
						//Tabelle aktualisieren:
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
				this.add(Box.createRigidArea(new Dimension(300,20)));
			}
		}

	}
	
	private void showProdukt() throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException{

		produktSpaltenNamen = new Vector<String>();
		produktSpaltenNamen.add("ID");
		produktSpaltenNamen.add("Name");
		produktSpaltenNamen.add("Preis");
		produktSpaltenNamen.add("im Lager");
		produktSpaltenNamen.add("Stueck/Pack");
		produktSpaltenNamen.add("Typ");
		produkt = MitarbeiterClient.sf.gibAlleProdukt();

		table = new JTable(new ProduktTableModel(produkt, produktSpaltenNamen));
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed( false ); 
		table.setSelectionBackground(new java.awt.Color(124,156,173));
		
		//Tabelle erstellen:
		produktModel = new ProduktTableModel(produkt, produktSpaltenNamen);
		table.setModel(produktModel);
		JScrollPane tablePane = new JScrollPane(table);
	
		listeAusgeben = new JPanel();
		listeAusgeben.setPreferredSize(new java.awt.Dimension(650, 350));
		listeAusgeben.setMinimumSize(new Dimension(650,350));
		listeAusgeben.setMaximumSize(new Dimension(650, 350));
		listeAusgeben.add(tablePane);
		
		tablePane.setPreferredSize(new java.awt.Dimension(650, 350));
		tablePane.setMaximumSize(new Dimension(1000,1000));
		tablePane.setMinimumSize(new Dimension(650,350));
       	this.add(listeAusgeben, "Artikelliste");/////////////////////////////////////////////

        listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
	}
	

	class SharedListSelectionHandler implements ListSelectionListener {


	public void valueChanged(ListSelectionEvent e) { 
 	   
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    
                	alterWertID = 		table.getValueAt(i,0);
                	alterWertName = 	table.getValueAt(i,1);
                	alterWertPreis = 	table.getValueAt(i,2);
                	alterWertLager = 	table.getValueAt(i,3);
                	alterWertStueck = 	table.getValueAt(i,4);
                	alterWertTyp = 		table.getValueAt(i,5);
             	   
             	   // linke Textfelder mit altem Wert füllen:
             	   String altID = String.valueOf(alterWertID);
             	   String altName = String.valueOf(alterWertName);
             	   String altPreis = String.valueOf(alterWertPreis);
             	   String altLager = String.valueOf(alterWertLager);
             	   String altStueck = String.valueOf(alterWertStueck);
             	   String altTyp = String.valueOf(alterWertTyp);            	   
             	 
             	   textfieldID.setText(altID);          	   
             	   textfieldName.setText(altName);          	   
             	   textfieldPreis.setText(altPreis);          	   
             	   textfieldLager.setText(altLager);
             	   textfieldStueck.setText(altStueck);
             	   textfieldTyp.setText(altTyp);
             	   
             	   int indexTyp = 0;
             	   if(altTyp.equals("Bücher"))					{indexTyp=0;}
             	   if(altTyp.equals("Musik, DVD & Games"))		{indexTyp=1;}
             	   if(altTyp.equals("Computer & Büro"))			{indexTyp=2;}
             	   if(altTyp.equals("Elektronik & Foto"))		{indexTyp=3;}
             	   if(altTyp.equals("Küche, Haus & Garten"))	{indexTyp=4;}
             	   if(altTyp.equals("Baumarkt & Auto"))			{indexTyp=5;}
             	   if(altTyp.equals("Drogerie & Parfümerie"))	{indexTyp=6;}
             	   if(altTyp.equals("Spielzeug & Baby"))		{indexTyp=7;}
             	   if(altTyp.equals("Kleidung, Schuhe & Uhren")){indexTyp=8;}
             	   if(altTyp.equals("Sport & Freizeit"))		{indexTyp=9;}
             	   
             	   // rechte Textfelder mit editierbaren Werten füllen:
             	   textfieldIDneu.setText(altID);          	   
             	   textfieldNameNeu.setText(altName);          	   
             	   textfieldPreisNeu.setText(altPreis);          	   
             	   textfieldLagerNeu.setText(altLager);
             	   textfieldStueckNeu.setText(altStueck);
             	   produktTypen.setSelectedIndex(indexTyp);;
                }
            }
        }
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PanelBearbeiteProdukt());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}


}
