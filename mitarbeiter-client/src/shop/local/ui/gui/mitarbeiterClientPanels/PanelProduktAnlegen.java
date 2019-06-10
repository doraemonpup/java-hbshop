package shop.local.ui.gui.mitarbeiterClientPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.ui.gui.MitarbeiterClient;


public class PanelProduktAnlegen extends javax.swing.JPanel {

	public static void main(String[] args) throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PanelProduktAnlegen());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JScrollPane scrollPane = new JScrollPane(frame);
		scrollPane.setPreferredSize(new Dimension(550,550));
		scrollPane.setMaximumSize(new Dimension(550,550));
		scrollPane.setMinimumSize(new Dimension(550,550));
		frame.pack();
		frame.setVisible(true);
	}

	private JLabel labelProduktAnlegen;
	private JLabel labelProduktID;
	private JTextField textfieldProduktID;
	private JLabel labelProduktName;
	private JTextField textfieldProduktName;
	private JTextField textfieldProduktInhalt;
	private JLabel labelProduktInhalt;
	private JLabel labelProduktPreis;
	private JTextField textfieldProduktPreis;
	private JLabel labelProduktAnzahl;
	private JTextField textfieldProduktAnzahl;
	private JLabel labelProduktTyp;
	private JButton buttonCancelProduktEingabe;
	private JButton buttonSubmitProduktEingabe;
	private JComboBox produktTypen;

	
	public PanelProduktAnlegen() throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException {
		super();
		initGUI();
		produktAnlegen();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(550,550));
			setMaximumSize(new Dimension(550,550));
			setMinimumSize(new Dimension(550,550));
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void produktAnlegen() throws IOException, MitarbeiterExistiertBereitsException, java.lang.NumberFormatException{
		{
			
			BoxLayout panelProduktAnlegenLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(panelProduktAnlegenLayout);
			{
				this.add(Box.createRigidArea(new Dimension(100,30)));
			}
			{
				labelProduktAnlegen = new JLabel();
				this.add(labelProduktAnlegen);
				labelProduktAnlegen.setText("Produkt anlegen");
				labelProduktAnlegen.setFont(new java.awt.Font("SansSerif",1,20));
				labelProduktAnlegen.setAlignmentX(CENTER_ALIGNMENT);
			}
			{
				this.add(Box.createRigidArea(new Dimension(100,30)));
			}
			{
				JPanel p1 = new JPanel();
				BoxLayout p1Layout = new BoxLayout(p1, javax.swing.BoxLayout.X_AXIS);
				p1.setLayout(p1Layout);
				this.add(p1);
				{
					labelProduktID = new JLabel();
					p1.add(labelProduktID);
					labelProduktID.setText("Produkt ID");				////////ProduktID
					labelProduktID.setAlignmentX(CENTER_ALIGNMENT);
					labelProduktID.setPreferredSize(new Dimension(100,20));
					labelProduktID.setMaximumSize(new Dimension(100,20));
					labelProduktID.setMinimumSize(new Dimension(100,20));
				}
				{
					textfieldProduktID = new JTextField();
					p1.add(textfieldProduktID);
					textfieldProduktID.setAlignmentX(CENTER_ALIGNMENT);
					textfieldProduktID.setPreferredSize(new Dimension(180,20));
					textfieldProduktID.setMaximumSize(new Dimension(180,20));
					textfieldProduktID.setMinimumSize(new Dimension(180,20));
					textfieldProduktID.addKeyListener(new KeyAdapter() {
						public void keyTyped(KeyEvent e) {
						    char c = e.getKeyChar();
						    if (!((Character.isDigit(c) ||
						    		(c == KeyEvent.VK_BACK_SPACE) ||
						    		(c == KeyEvent.VK_DELETE)))) {
						        	getToolkit().beep();
						        	e.consume();
						    }
						    if (textfieldProduktID.getText().length() >= 4) {
						    	c = KeyEvent.VK_BACK_SPACE;
						    	getToolkit().beep();
						    	e.consume();
						    }

						}
					});
				}
				{
					p1.add(Box.createRigidArea(new Dimension(20,15)));
				}
				{
					JButton random = new JButton(new ImageIcon("iconset/b_icons/refresh.png"));
					random.setPreferredSize(new Dimension(20,20));
					random.setMaximumSize(new Dimension(20,20));
					random.setMinimumSize(new Dimension(20,20));
					random.setToolTipText("Random ID!"); ////////
					p1.add(random);
					random.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {					        
				        	 int randomID = 500;
				        	 while(randomID < 1000){
				        	 randomID = (int) ((Math.random() * 9999)+1);
				        	 String id = String.valueOf(randomID);
					        	 try {
									if(MitarbeiterClient.sf.pruefeProdukt(id)==false){
										 textfieldProduktID.setText(id);
									 }
								} catch (IOException e1) {
									e1.printStackTrace();
								}
				        	 }
				         }
				    });
				}

			}
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			{
				JPanel p2 = new JPanel();
				BoxLayout p2Layout = new BoxLayout(p2, javax.swing.BoxLayout.X_AXIS);
				p2.setLayout(p2Layout);
				this.add(p2);
				{
					labelProduktName = new JLabel();
					p2.add(labelProduktName);
					labelProduktName.setText("Produkt Name");
					labelProduktName.setAlignmentX(CENTER_ALIGNMENT);
					labelProduktName.setPreferredSize(new Dimension(100,20));
					labelProduktName.setMaximumSize(new Dimension(100,20));
					labelProduktName.setMinimumSize(new Dimension(100,20));
				}
				{
					textfieldProduktName = new JTextField();
					p2.add(textfieldProduktName);
					textfieldProduktName.setAlignmentX(CENTER_ALIGNMENT);
					textfieldProduktName.setPreferredSize(new Dimension(180,20));
					textfieldProduktName.setMaximumSize(new Dimension(180,20));
					textfieldProduktName.setMinimumSize(new Dimension(180,20));
				}
				{
					p2.add(Box.createRigidArea(new Dimension(40,15)));
				}
			}
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			{
				JPanel p3 = new JPanel();
				BoxLayout p3Layout = new BoxLayout(p3, javax.swing.BoxLayout.X_AXIS);
				p3.setLayout(p3Layout);
				this.add(p3);
				{
					labelProduktPreis = new JLabel();
					p3.add(labelProduktPreis);
					labelProduktPreis.setText("Produkt Preis");
					labelProduktPreis.setPreferredSize(new Dimension(100,20));
					labelProduktPreis.setMaximumSize(new Dimension(100,20));
					labelProduktPreis.setMinimumSize(new Dimension(100,20));
				}
				{
					textfieldProduktPreis = new JTextField();
					p3.add(textfieldProduktPreis);
					textfieldProduktPreis.setPreferredSize(new Dimension(180,20));
					textfieldProduktPreis.setMaximumSize(new Dimension(180,20));
					textfieldProduktPreis.setMinimumSize(new Dimension(180,20));
					textfieldProduktPreis.addKeyListener(new KeyAdapter() {
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
				{
					p3.add(Box.createRigidArea(new Dimension(40,15)));
				}
			}
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			{
				JPanel p4 = new JPanel();
				BoxLayout p4Layout = new BoxLayout(p4, javax.swing.BoxLayout.X_AXIS);
				p4.setLayout(p4Layout);
				this.add(p4);
				{
					labelProduktAnzahl = new JLabel();
					p4.add(labelProduktAnzahl);
					labelProduktAnzahl.setText("Produkt Menge");
					labelProduktAnzahl.setPreferredSize(new Dimension(100,20));
					labelProduktAnzahl.setMaximumSize(new Dimension(100,20));
					labelProduktAnzahl.setMinimumSize(new Dimension(100,20));
				}
				{
					textfieldProduktAnzahl = new JTextField();
					p4.add(textfieldProduktAnzahl);
					textfieldProduktAnzahl.setPreferredSize(new Dimension(180,20));
					textfieldProduktAnzahl.setMaximumSize(new Dimension(180,20));
					textfieldProduktAnzahl.setMinimumSize(new Dimension(180,20));
					textfieldProduktAnzahl.addKeyListener(new KeyAdapter() {
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
				{
					p4.add(Box.createRigidArea(new Dimension(40,15)));
				}
			}
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			{
				JPanel p9 = new JPanel();
				BoxLayout p9Layout = new BoxLayout(p9, javax.swing.BoxLayout.X_AXIS);
				p9.setLayout(p9Layout);
				this.add(p9);
				{
					labelProduktInhalt = new JLabel();
					p9.add(labelProduktInhalt);
					labelProduktInhalt.setText("Inhalt/Packet");
					labelProduktInhalt.setPreferredSize(new Dimension(100,20));
					labelProduktInhalt.setMaximumSize(new Dimension(100,20));
					labelProduktInhalt.setMinimumSize(new Dimension(100,20));
				}
				{
					textfieldProduktInhalt = new JTextField();
					textfieldProduktInhalt.setText("1");	//Defautlt-Wert
					p9.add(textfieldProduktInhalt);
					textfieldProduktInhalt.setPreferredSize(new Dimension(180,20));
					textfieldProduktInhalt.setMaximumSize(new Dimension(180,20));
					textfieldProduktInhalt.setMinimumSize(new Dimension(180,20));
					textfieldProduktInhalt.addKeyListener(new KeyAdapter() {
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
				{
					p9.add(Box.createRigidArea(new Dimension(40,15)));
				}
			}			
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			{
				JPanel p5 = new JPanel();
				BoxLayout p5Layout = new BoxLayout(p5, javax.swing.BoxLayout.X_AXIS);
				p5.setLayout(p5Layout);
				this.add(p5);
				{
					labelProduktTyp = new JLabel();
					p5.add(labelProduktTyp);
					labelProduktTyp.setText("Produkt Typ");
					labelProduktTyp.setPreferredSize(new Dimension(100,20));
					labelProduktTyp.setMaximumSize(new Dimension(100,20));
					labelProduktTyp.setMinimumSize(new Dimension(100,20));
				}
				{
					String[] artikelStrings = { "", "Bücher", "Musik, DVD & Games", "Computer & Büro", "Elektronik & Foto", "Küche, Haus & Garten", "Baumarkt & Auto", "Drogerie & Parfümerie", "Spielzeug & Baby", "Kleidung, Schuhe & Uhren", "Sport & Freizeit" };
					produktTypen = new JComboBox(artikelStrings);
					produktTypen.setSelectedIndex(0);
					p5.add(produktTypen);
					produktTypen.setPreferredSize(new Dimension(180,20));
					produktTypen.setMaximumSize(new Dimension(180,20));
					produktTypen.setMinimumSize(new Dimension(180,20));
				}
				{
					p5.add(Box.createRigidArea(new Dimension(40,15)));
				}
			}
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			{
				JPanel p6 = new JPanel();
				BoxLayout p6Layout = new BoxLayout(p6, javax.swing.BoxLayout.X_AXIS);
				p6.setLayout(p6Layout);
				this.add(p6);
				{
					buttonCancelProduktEingabe = new JButton(new ImageIcon("iconset/b_icons/delete.png"));
					p6.add(buttonCancelProduktEingabe);
					buttonCancelProduktEingabe.setText("Abbrechen");
					buttonCancelProduktEingabe.setPreferredSize(new Dimension(110,30));
					buttonCancelProduktEingabe.setMaximumSize(new Dimension(110,30));
					buttonCancelProduktEingabe.setMinimumSize(new Dimension(110,30));
					buttonCancelProduktEingabe.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {					        
				        	 	textfieldProduktID.setText("");
					        	textfieldProduktName.setText("");
					        	textfieldProduktPreis.setText("");
					        	textfieldProduktAnzahl.setText("");
					        	produktTypen.setSelectedIndex(0);
				         }
				    });
				}
				{
					p6.add(Box.createRigidArea(new Dimension(20,0)));
				}
				{
					buttonSubmitProduktEingabe = new JButton(new ImageIcon("iconset/b_icons/add.png"));
					p6.add(buttonSubmitProduktEingabe);
					buttonSubmitProduktEingabe.setText("Einfügen");
					buttonSubmitProduktEingabe.setPreferredSize(new Dimension(110,30));
					buttonSubmitProduktEingabe.setMaximumSize(new Dimension(110,30));
					buttonSubmitProduktEingabe.setMinimumSize(new Dimension(110,30));
					buttonSubmitProduktEingabe.addActionListener(new ActionListener() {
				        private Component popup;
						private Component frame;

						public void actionPerformed(ActionEvent e)  {
				 			
				        	String produktID = textfieldProduktID.getText();
							String produktName = textfieldProduktName.getText();
							String produktPreis = textfieldProduktPreis.getText();
							String produktAnzahl = textfieldProduktAnzahl.getText();
							String produktInhalt = textfieldProduktInhalt.getText();
							String produktTyp = (String) produktTypen.getSelectedItem();
							
				        	if(		textfieldProduktID.getText().equals("")  ||	
				        			textfieldProduktName.getText().equals("") ||
				        			textfieldProduktPreis.getText().equals("") ||
				        			textfieldProduktAnzahl.getText().equals("") ||
				        			textfieldProduktInhalt.getText().equals("") ||
				        			produktTypen.getSelectedItem().equals("")){	
				        		
					        	JOptionPane errorMessage = new JOptionPane();
					        	errorMessage.setSize(400,300);
					        	JOptionPane.showMessageDialog(popup, "Bitte alle Felder ausfuellen!","Fehler", JOptionPane.ERROR_MESSAGE);					        		
				        	}				        		
				        	
				        	else{
				        		
				        		boolean derPreis = true;
								try {
									float preis = Float.parseFloat(produktPreis);
								} catch (java.lang.NumberFormatException e1) {
									derPreis = false;
								}
				        		
				        		
				        		
				        		if (textfieldProduktID.getText().length() != 4){
									JOptionPane errorMessage = new JOptionPane();
									errorMessage.setSize(400,300);
									JOptionPane.showMessageDialog(popup, "Die Produkt-ID muss eine vierstellige Zahl sein!","Fehler", JOptionPane.ERROR_MESSAGE);
								}
				        		else{ 
									
				       				if(derPreis==false){
				       					JOptionPane errorMessage = new JOptionPane();
										errorMessage.setSize(400,300);
										JOptionPane.showMessageDialog(popup, "Der Preis muss eine ganze Zahl oder eine Kommazahl mit Punkt sein!","Fehler", JOptionPane.ERROR_MESSAGE);
									}
				        			else{
						        		//Wenn alle Felder ausgefuellt, caste Werte:
										int proID = Integer.parseInt (produktID);
										float preis = Float.parseFloat(produktPreis);
										int anz = Integer.parseInt(produktAnzahl);
										int inhalt = Integer.parseInt(produktInhalt);
										
										
										//Wenn Produkt Id in Liste schom vorhanden: Fehler	
										try {						/////////////
											if(MitarbeiterClient.sf.pruefeProdukt(produktID)==true){
												JOptionPane errorMessage = new JOptionPane();
												errorMessage.setSize(400,300);
												JOptionPane.showMessageDialog(popup, "Ein Produkt mit dieser ID ist schon vorhanden...\nBitte aendern Sie die ID !","Fehler", JOptionPane.ERROR_MESSAGE);
											}
											
											else{
												try {						//////////
													MitarbeiterClient.sf.fuegeProduktEin(proID, produktName, preis, anz, inhalt, produktTyp);

												} catch (ProduktExistiertBereitsException e2) {
													e2.printStackTrace();
												}								
	
																		
												JOptionPane successMessage = new JOptionPane();
												successMessage.setSize(400,300);
												JOptionPane.showMessageDialog(popup, "Produkt "+produktName +" wurde erfolgreich eingefuegt!");
												//Felder wieder leeren:
												textfieldProduktID.setText("");
												textfieldProduktName.setText("");
												textfieldProduktPreis.setText("");
												textfieldProduktAnzahl.setText("");
												textfieldProduktInhalt.setText("1");
												produktTypen.setSelectedIndex(0);
											}
										} catch (HeadlessException e1) {
											e1.printStackTrace();
										} catch (IOException e1) {
											e1.printStackTrace();
										}	
					        		}
				        		}
				        	}									         
				         }
				     });				         
				}
				
			}
		}
	}


}
