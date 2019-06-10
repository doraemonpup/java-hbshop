package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import shop.local.client.ShopFassade;
import shop.local.interfaces.EShopInterface;

import shop.local.ui.gui.kundenClientPanels.PanelProduktliste;
import shop.local.ui.gui.kundenClientPanels.RechnungAnzeigenFrame;

import shop.local.ui.gui.kundenClientPanels.WarenkorbTableModel;

import shop.local.valueobjects.Produkt;
import shop.local.valueobjects.GewaehltesProdukt;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Rechnung;



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

public class KundenClient extends JFrame {
	
	
	public static final int DEFAULT_PORT = 6789;
	public static EShopInterface sf;
	private String host;
	public static int status=0;

	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuItem exitItem;
	private JPasswordField jPasswordField1;
	private JLabel BackgroundLabel;
	private JButton JetztRegistrierenButton;
	private JPanel LabelUndTextFieldPanel;
	private JLabel RegistrierenLabel1;
	private JLabel VornameLabel;
	private JLabel NachnameLabel1;
	private JLabel BenutzernameLabel1;
	private JLabel PasswordLabel1;
	private JLabel EmailLabel;
	private JTextField VornameTextField;
	private JTextField NachnameTextField1;
	private JPasswordField PasswordField1;
	private JTextField BenutzernameTextField;
	private JTextField EmailTextField;
	private JLabel WarenkorbLabel;
	private JPanel AnmeldenPanel;
	private JPanel WarenkorbPanel;
	private JButton RegistrierenButton1;
	private JLabel NameKundeLabel;
	private JLabel WillkommenLabel1;
	private JPanel WillkommenPanel;
	private JTextField PasswordTextField;
	private JLabel PasswordLabel;
	private JTextField BenutzerNameTextField1;
	private JLabel BenutzerNameLabel1;
	private JPanel LoginPanel;
	private JTextPane SummeTextPane;
	private JTextPane SummeTextPane2;
	private JLabel SummeLabel;
	private JTable WarenkorbTable;
	private JTable ProduktListeJTable;
	private JButton WarenkorblegenButton1;
	private JTextField MengeTextField1;
	private JLabel MengeLabel1;
	private JPanel SouthPanel3;
	private JPanel NorthPanel3;
	private JButton LoginButton;
	private JButton zurKasseButton;
	private JButton BearbeitenButton;
	private JButton KundenDatenButton;
	private JMenuItem saveItem;
	private JTable ProduktListeTable;
	private JButton SuchenButton;
	private JButton ProduktAnzeigenButton;
	private JPanel EastPanel1;
	private JPanel WestPanel1;
	private JPanel NorthPanel1;
	private JMenuBar leiste;
	private JTextField SuchTextField;
	
	
	private JScrollPane ProduktListeScrollPane;
	private JScrollPane WarenkorbListeScrollPane;
	
	private JLabel LogoLabel;
	private JPanel SouthPanel2;
	private JPanel NorthPanel2;
	Vector<String> spaltenName;
	private float gesamteSumme ;
	
	private static Kunde benutzer = null;
	
	private static Vector<GewaehltesProdukt> warenkorb = new Vector<GewaehltesProdukt>();
	
	public KundenClient(String head){
		super(head);
		
		try {
			sf = new ShopFassade(host,DEFAULT_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		initGUI();
		setBesitzer();
		
	}
	
	
	private void setBesitzer() {
		if(benutzer==null){
			System.out.println("nicht eingeloggt!!");
			RegistrierenButton1.setVisible(true);
		}
		else{
			LoginButton.setText("Ausloggen");
			RegistrierenButton1.setVisible(false);
			LoginPanel.setVisible(false);
			KundenDatenButton.setVisible(true);
			WillkommenPanel.setVisible(true);
			benutzer.toString();
			NameKundeLabel.setText(benutzer.getVorname()+" "+benutzer.getNachname());
		}
	}
	
	public void setEingelogterKunde(Kunde k){
		benutzer = k;
	}
	
	public boolean validateEmail(String eMail) {
		 Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		 Matcher m = p.matcher(eMail);
		 if(m.matches()) return true;
		        else return false;
	}

	public boolean validateFloat(String floatString) {
		 Pattern p = Pattern.compile("d+(,d+)?");
		 Matcher m = p.matcher(floatString);
		 if(m.matches()) return true;
		        else return false;
	}
	
	
	private void initGUI() {
		try {
	
			WindowListener wl = new shop.local.common.WindowCloser(sf);
			this.addWindowListener(wl);
			this.setResizable(false);

			this.setVisible(true);
			this.setTitle("Bremer Shop");
			MenuActionListener mListener = new MenuActionListener();
			ButtonActionListener bListener = new ButtonActionListener();
			
			
			{
				NorthPanel1 = new JPanel();
				BorderLayout NorthPanel1Layout = new BorderLayout();
				getContentPane().add(NorthPanel1, BorderLayout.NORTH);
				NorthPanel1.setPreferredSize(new java.awt.Dimension(904, 136));
				NorthPanel1.setLayout(NorthPanel1Layout);
				NorthPanel1.setBackground(new java.awt.Color(206,206,206));
				NorthPanel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
				{
					NorthPanel2 = new JPanel();
					NorthPanel1.add(NorthPanel2, BorderLayout.NORTH);
					NorthPanel2.setPreferredSize(new java.awt.Dimension(902, 81));
					NorthPanel2.setBackground(new java.awt.Color(255,255,255));
					NorthPanel2.setLayout(null);
					{
						LogoLabel = new JLabel(new ImageIcon("Iconset/Kwick eShop.png"));
						NorthPanel2.add(LogoLabel);
						LogoLabel.setBounds(6, 6, 509, 69);
						LogoLabel.setFont(new java.awt.Font("Castellar",0,24));
					}
					{
						LoginPanel = new JPanel();
						NorthPanel2.add(LoginPanel);
						LoginPanel.setBounds(553, 18, 343, 51);
						LoginPanel.setLayout(null);
						LoginPanel.setBackground(new java.awt.Color(255,255,255));
						LoginPanel.setBorder(BorderFactory.createTitledBorder(""));
						{
							BenutzerNameLabel1 = new JLabel();
							LoginPanel.add(BenutzerNameLabel1);
							BenutzerNameLabel1.setText("Benutzer");
							BenutzerNameLabel1.setBounds(6, 15, 56, 23);
						}
						{
							BenutzerNameTextField1 = new JTextField();
							LoginPanel.add(BenutzerNameTextField1);
							BenutzerNameTextField1.setBounds(61, 17, 91, 18);
							BenutzerNameTextField1.setSize(98, 20);
						}
						{
							PasswordLabel = new JLabel();
							LoginPanel.add(PasswordLabel);
							PasswordLabel.setText("Password");
							PasswordLabel.setBounds(162, 20, 58, 17);
						}
						{
							PasswordTextField = new JPasswordField();
							LoginPanel.add(PasswordTextField);
							PasswordTextField.setBounds(225, 17, 98, 20);
							PasswordTextField.setPreferredSize(new java.awt.Dimension(98, 20));
						}
					}
					{
						WillkommenPanel = new JPanel();
						NorthPanel2.add(WillkommenPanel);
						WillkommenPanel.setBounds(554, 18, 340, 49);
						WillkommenPanel.setLayout(null);
						WillkommenPanel.setVisible(false);
						WillkommenPanel.setBackground(new java.awt.Color(255,255,255));
						{
							WillkommenLabel1 = new JLabel();
							WillkommenPanel.add(WillkommenLabel1);
							WillkommenLabel1.setText("Willkommen,   ");
							WillkommenLabel1.setBounds(23, 12, 108, 21);
							WillkommenLabel1.setFont(new java.awt.Font("Segoe UI",1,12));
						}
						{
							NameKundeLabel = new JLabel();
							WillkommenPanel.add(NameKundeLabel);
							NameKundeLabel.setBounds(107, 11, 195, 21);
							NameKundeLabel.setBackground(new java.awt.Color(234,234,234));
							NameKundeLabel.setFont(new java.awt.Font("Segoe UI",0,14));
						}
					}
					{
						BackgroundLabel = new JLabel(new ImageIcon("iconset/Background.jpg"));
						NorthPanel2.add(BackgroundLabel);
						BackgroundLabel.setPreferredSize(new java.awt.Dimension(902, 81));
						BackgroundLabel.setSize(902, 81);
					}
				}
				
				{
					SouthPanel2 = new JPanel();
					NorthPanel1.add(SouthPanel2, BorderLayout.SOUTH);
					SouthPanel2.setPreferredSize(new java.awt.Dimension(902, 52));
					SouthPanel2.setLayout(null);
					{
						ProduktAnzeigenButton = new JButton();
						SouthPanel2.add(ProduktAnzeigenButton);
						ProduktAnzeigenButton.setText("Produkt");
						ProduktAnzeigenButton.setBounds(18, 12, 83, 30);
						ProduktAnzeigenButton.setToolTipText("Alle Produkte anzeigen");
						ProduktAnzeigenButton.addActionListener(bListener);
					}

					{
						SuchTextField = new JTextField();
						SouthPanel2.add(SuchTextField);
						SuchTextField.setText("Suchen");
						SuchTextField.setBounds(112, 12, 251, 30);
						SuchTextField.addKeyListener(new KeyListener(){
							 

							public void keyPressed(KeyEvent arg0) {
								
							}

							public void keyReleased(KeyEvent arg0) {
								Vector<Produkt> produkt = null;
								 String suchbegriff = SuchTextField.getText();
								if (suchbegriff.equals("")) {
								try {
									produkt = sf.gibAlleProdukt();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							else {
								// Suchbegriff verwenden
									try {
									produkt = sf.sucheProduktNachName(suchbegriff);
										} catch (IOException e) {
										e.printStackTrace();
									}
								}
					 		if(produkt != null){
						 
								((PanelProduktliste) ProduktListeJTable).ErstelltTableModel(produkt);
								JScrollPane gesuchtesProdukt = ((PanelProduktliste) ProduktListeJTable).getScollPanel();
								NorthPanel3.removeAll();
								NorthPanel3.add(gesuchtesProdukt);								
					 		}
						}

							public void keyTyped(KeyEvent arg0) {
								
							}
							
							
						});
					}
					{
						SuchenButton = new JButton(new ImageIcon("iconset/shopIcons/search.png"));
						SouthPanel2.add(SuchenButton);
						GridLayout SuchenButtonLayout = new GridLayout(1, 1);
						SuchenButtonLayout.setColumns(1);
						SuchenButtonLayout.setHgap(5);
						SuchenButtonLayout.setVgap(5);
						SuchenButton.setText("Suchen");
						SuchenButton.setLayout(null);
						SuchenButton.setBounds(375, 12, 116, 30);
						SuchenButton.addActionListener(bListener);
					}
					{
						KundenDatenButton = new JButton();
						SouthPanel2.add(KundenDatenButton);
						KundenDatenButton.setText("Meine Daten");
						KundenDatenButton.setBounds(503, 12, 104, 29);
						KundenDatenButton.addActionListener(bListener);
						KundenDatenButton.setVisible(false);
					}
					{
						LoginButton = new JButton(new ImageIcon("iconset/shopIcons/key.png"));
						SouthPanel2.add(LoginButton);
						LoginButton.setText("Login");
						LoginButton.setBounds(759, 12, 127, 30);
						LoginButton.addActionListener(bListener);
					}
					{
						RegistrierenButton1 = new JButton();
						SouthPanel2.add(RegistrierenButton1);
						RegistrierenButton1.setText("Registrieren");
						RegistrierenButton1.setBounds(621, 12, 127, 30);
						RegistrierenButton1.addActionListener(bListener);
					}
				}
			
			}
			{
				EastPanel1 = new JPanel();
				getContentPane().add(EastPanel1, BorderLayout.EAST);
				EastPanel1.setLayout(null);
				EastPanel1.setPreferredSize(new java.awt.Dimension(205, 540));
				EastPanel1.setBackground(new java.awt.Color(202,255,228));
				EastPanel1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				{
					WarenkorbPanel = new JPanel();
					EastPanel1.add(WarenkorbPanel);
					WarenkorbPanel.setLayout(null);
					WarenkorbPanel.setBounds(3, 0, 199, 500);
					{
						BearbeitenButton = new JButton();
						WarenkorbPanel.add(BearbeitenButton);
						BearbeitenButton.setText("Bearbeiten");
						BearbeitenButton.setBounds(35, 344, 145, 22);
						BearbeitenButton.addActionListener(bListener);
					}
					{
						zurKasseButton = new JButton(new ImageIcon("Iconset/kasse.png"));
						WarenkorbPanel.add(zurKasseButton);
						zurKasseButton.setText("Zur Kasse");
						zurKasseButton.setBounds(35, 405, 145, 77);
						zurKasseButton.addActionListener(bListener);
					}
					{
						WarenkorbLabel = new JLabel();
						WarenkorbPanel.add(WarenkorbLabel);
						WarenkorbLabel.setText("     Warenkorb");
						WarenkorbLabel.setBounds(12, 15, 178, 28);
						WarenkorbLabel.setFont(new java.awt.Font("Copperplate Gothic Bold",0,20));
						WarenkorbLabel.setBackground(new java.awt.Color(255,255,255));
						WarenkorbLabel.setForeground(new java.awt.Color(0,0,0));
					}
					{
						spaltenName = new Vector();
						spaltenName.add("Id");
						spaltenName.add("Name");
						spaltenName.add("Preis");
						spaltenName.add("Menge");
						
						WarenkorbTable = new JTable(new WarenkorbTableModel(new Vector(),spaltenName));
						WarenkorbListeScrollPane = new JScrollPane(WarenkorbTable);
						WarenkorbPanel.add(WarenkorbListeScrollPane);

						WarenkorbListeScrollPane.setBounds(12, 49, 179, 246);
						
					}
					{
						SummeLabel = new JLabel();
						WarenkorbPanel.add(SummeLabel);
						FlowLayout SummeLabelLayout = new FlowLayout();
						SummeLabel.setText("Summe ");
						SummeLabel.setLayout(null);
						SummeLabel.setBounds(17, 300, 71, 27);
						SummeLabel.setFont(new java.awt.Font("Segoe UI",1,16));
					}
					{
						SummeTextPane = new JTextPane();
						WarenkorbPanel.add(SummeTextPane);
						SummeTextPane.setText("0");
						SummeTextPane.setBounds(93, 301, 77, 23);
						SummeTextPane.setEditable(false);
					}
				}
				{
					AnmeldenPanel = new JPanel();
					EastPanel1.add(AnmeldenPanel);
					AnmeldenPanel.setLayout(null);
					AnmeldenPanel.setBounds(4, 0, 198, 500);
					AnmeldenPanel.setVisible(false);
					{
						RegistrierenLabel1 = new JLabel();
						AnmeldenPanel.add(RegistrierenLabel1);
						RegistrierenLabel1.setText("Registrieren");
						RegistrierenLabel1.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
						RegistrierenLabel1.setBounds(23, 17, 157, 43);
						RegistrierenLabel1.setHorizontalAlignment(SwingConstants.CENTER);
						RegistrierenLabel1.setFont(new java.awt.Font("Tahoma",0,22));
					}
					{
						LabelUndTextFieldPanel = new JPanel();
						AnmeldenPanel.add(LabelUndTextFieldPanel);
						LabelUndTextFieldPanel.setLayout(null);
						LabelUndTextFieldPanel.setBounds(4, 83, 190, 184);
						LabelUndTextFieldPanel.setBackground(new java.awt.Color(255,187,255));
						{
							EmailTextField = new JTextField();
							LabelUndTextFieldPanel.add(EmailTextField);
							EmailTextField.setBounds(83, 149, 100, 23);
						}
						{
							PasswordField1 = new JPasswordField();
							LabelUndTextFieldPanel.add(PasswordField1);
							PasswordField1.setBounds(83, 115, 100, 23);
						}
						{
							BenutzernameTextField = new JTextField();
							LabelUndTextFieldPanel.add(BenutzernameTextField);
							BenutzernameTextField.setBounds(83, 81, 101, 23);
						}
						{
							NachnameTextField1 = new JTextField();
							LabelUndTextFieldPanel.add(NachnameTextField1);
							NachnameTextField1.setBounds(82, 46, 102, 24);
						}
						{
							VornameTextField = new JTextField();
							LabelUndTextFieldPanel.add(VornameTextField);
							VornameTextField.setBounds(82, 11, 103, 24);
						}
						{
							EmailLabel = new JLabel();
							LabelUndTextFieldPanel.add(EmailLabel);
							EmailLabel.setText("E-mail");
							EmailLabel.setBounds(8, 142, 61, 30);
						}
						{
							PasswordLabel1 = new JLabel();
							LabelUndTextFieldPanel.add(PasswordLabel1);
							PasswordLabel1.setText("Password");
							PasswordLabel1.setBounds(6, 109, 66, 30);
						}
						{
							BenutzernameLabel1 = new JLabel();
							LabelUndTextFieldPanel.add(BenutzernameLabel1);
							BenutzernameLabel1.setText("Benutzername");
							BenutzernameLabel1.setBounds(6, 76, 77, 30);
						}
						{
							NachnameLabel1 = new JLabel();
							LabelUndTextFieldPanel.add(NachnameLabel1);
							NachnameLabel1.setText("Nachname");
							NachnameLabel1.setBounds(6, 43, 71, 26);
						}
						{
							VornameLabel = new JLabel();
							LabelUndTextFieldPanel.add(VornameLabel);
							VornameLabel.setText("Vorname");
							VornameLabel.setBounds(6, 8, 71, 26);
						}
					}
					{
						JetztRegistrierenButton = new JButton();
						AnmeldenPanel.add(JetztRegistrierenButton);
						JetztRegistrierenButton.setText("Jetzt Registrieren");
						JetztRegistrierenButton.setBounds(23, 279, 157, 24);
						JetztRegistrierenButton.addActionListener(bListener);
					}
				}
			}
			{
				WestPanel1 = new JPanel();
				getContentPane().add(WestPanel1, BorderLayout.WEST);
				BorderLayout WestPanel1Layout = new BorderLayout();
				WestPanel1.setLayout(WestPanel1Layout);
				WestPanel1.setPreferredSize(new java.awt.Dimension(695, 530));
				{
					{
						NorthPanel3 = new JPanel();
						WestPanel1.add(NorthPanel3, BorderLayout.NORTH);
						NorthPanel3.setPreferredSize(new java.awt.Dimension(692, 445));
					}
					{
						ProduktListeJTable = new PanelProduktliste();	
						Vector<Produkt> alleProdukt = sf.gibAlleProdukt();
					    ((PanelProduktliste) ProduktListeJTable).ErstelltTableModel(alleProdukt);
						ProduktListeScrollPane = ((PanelProduktliste) ProduktListeJTable).getScollPanel();
						NorthPanel3.add(ProduktListeScrollPane, BorderLayout.NORTH);
						ProduktListeJTable.setSize(690, 440);
						NorthPanel3.setVisible(true);
						WestPanel1.setVisible(true);
					
					}
					
								
				}
				{
					SouthPanel3 = new JPanel();
					WestPanel1.add(SouthPanel3, BorderLayout.SOUTH);
					SouthPanel3.setPreferredSize(new java.awt.Dimension(695, 55));
					SouthPanel3.setBackground(new java.awt.Color(234,234,234));
					SouthPanel3.setLayout(null);
					{
						MengeLabel1 = new JLabel();
						SouthPanel3.add(MengeLabel1);
						MengeLabel1.setText("Anzahl");
						MengeLabel1.setBounds(411, 17, 47, 30);
						MengeLabel1.setFont(new java.awt.Font("Arial",0,14));
					}
					{
						MengeTextField1 = new JTextField();
						SouthPanel3.add(MengeTextField1);
						MengeTextField1.setText("1");
						MengeTextField1.setBounds(473, 15, 40, 30);
					}
					{
						WarenkorblegenButton1 = new JButton(new ImageIcon("iconset/shopIcons/cart_add.png"));
						SouthPanel3.add(WarenkorblegenButton1);
						WarenkorblegenButton1.setText("In Warenkorb");
						WarenkorblegenButton1.setBounds(525, 15, 159, 30);
						WarenkorblegenButton1.addActionListener(bListener);
						
					}
				}									
			}
			this.pack();
			this.setSize(920, 700);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initMenu(){
		MenuActionListener mListener = new MenuActionListener();
		leiste = new JMenuBar();
		this.setJMenuBar(leiste);
		
			fileMenu = new JMenu("File");
			leiste.add(fileMenu);
			helpMenu = new JMenu("Help");
			leiste.add(helpMenu);
			
			saveItem = new JMenuItem("Save");
				fileMenu.add(saveItem);
			exitItem = new JMenuItem("Exit");
				fileMenu.add(exitItem);
				exitItem.addActionListener(mListener);
	}
	
	// Die Funktion zum Aktualisieren von Warenkorb
	public void WarenkorbAktualisieren() {
		 TableModel warenkorbTable = new WarenkorbTableModel((List<GewaehltesProdukt>) warenkorb,spaltenName);
		 WarenkorbTable.setModel(warenkorbTable);
		 gesamteSumme = sf.berechnenSumme(warenkorb);
		 String Summe = Float.toString(gesamteSumme);
		 SummeTextPane.setText(Summe);
	}
	
	class ButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource().equals(ProduktAnzeigenButton)) {
				
					try {
						((PanelProduktliste) ProduktListeJTable).ErstelltTableModel(sf.gibAlleProdukt());
					} catch (IOException e) {
						e.printStackTrace();
					}
					JScrollPane alleProdukt = ((PanelProduktliste) ProduktListeJTable).getScollPanel();
					
					// zuerste loeschen alle Component, dann hinfuegen
					NorthPanel3.removeAll();
					NorthPanel3.add(alleProdukt);
	 
				}
				 
				 else if (ae.getSource().equals(SuchenButton)) {
					 Vector<Produkt> produkt = null;
					 String suchbegriff = SuchTextField.getText();
					 if (suchbegriff.equals("")) {
	
							try {
								produkt = sf.gibAlleProdukt();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						else {
							// Suchbegriff verwenden
							try {
								produkt = sf.sucheProduktNachName(suchbegriff);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					 if(produkt != null){
						 
						 ((PanelProduktliste) ProduktListeJTable).ErstelltTableModel(produkt);
							JScrollPane gesuchtesProdukt = ((PanelProduktliste) ProduktListeJTable).getScollPanel();
							NorthPanel3.removeAll();
							NorthPanel3.add(gesuchtesProdukt);								
					 }
					 else{
						 Component frame = null;
						 JOptionPane.showMessageDialog(frame, "Das Produkt ist nicht gefunden!!");
					 }
				}
				 
				 else if (ae.getSource().equals(WarenkorblegenButton1)){
					 Produkt p = ((PanelProduktliste) ProduktListeJTable).getGewaehltesProdukt();
					 if(p !=null){
							String menge = MengeTextField1.getText();
							int mengen = Integer.parseInt(menge);
							
							if(mengen>p.getProduktMenge()){
								Component errorMessage = null;
								JOptionPane.showMessageDialog(errorMessage, "Gew�nschte Menge nicht verf�gbar!!");
							}
							else{
								warenkorb.add(new GewaehltesProdukt(p.getID(),p.getProduktName(),p.getProduktPreis(),p.getProduktMenge(),mengen));
								Component errorMessage = null;
								JOptionPane.showMessageDialog(errorMessage, "Gewไhlte Ware ist im Warenkorb!!");
								TableModel warenkorbTable = new WarenkorbTableModel((List<GewaehltesProdukt>) warenkorb,spaltenName);
								WarenkorbTable.setModel(warenkorbTable);
								gesamteSumme = sf.berechnenSumme(warenkorb);
								// wandeln in String
								String Summe = Float.toString(gesamteSumme);	
								// Set Summe
								SummeTextPane.setText(Summe);
								
							}
						}
						else{
							Component errorMessage = null;
							JOptionPane.showMessageDialog(errorMessage, "Sie haben kein Produkt gewไhlt!!");
						}
					 
				 }
			
				 else if (ae.getActionCommand().equals("Bearbeiten")){
					 WarenkorbBearbeitenFrame warenkorbFrame = new WarenkorbBearbeitenFrame(warenkorb);
					 warenkorbFrame.setVisible(true);
					 warenkorbFrame.setLocationRelativeTo(null);
					 warenkorb = warenkorbFrame.getBearbeitetenWarenkorb();
					 WarenkorbAktualisieren();
					
					 
				 }
			
				 else if (ae.getActionCommand().equals("Zur Kasse")){
					 if(benutzer == null){
						Component errorMessage = null;
						JOptionPane.showMessageDialog(errorMessage, "Sie haben sich noch nicht eingeloggt!!");
						 
					 }
					 else{
						 if(warenkorb.isEmpty()){
							 Component errorMessage = null;
								JOptionPane.showMessageDialog(errorMessage, "Der Warenkorb ist leer!!");
						 }
						 else{
							 BezahlenFrame b = new BezahlenFrame(gesamteSumme);
							 b.setVisible(true);
						 }
					 }
				 }
			
				 else if (ae.getActionCommand().equals("Login")){

					Kunde k = null;
					try {
						k = sf.KundenLogin(BenutzerNameTextField1.getText(),PasswordTextField.getText());
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					 
					if(k== null){
						Component errorMessage = null;
						JOptionPane.showMessageDialog(errorMessage, "Benutzername oder password ist falsch!!");
					}
					else{
						benutzer = k;
						AnmeldenPanel.setVisible(false);
						WarenkorbPanel.setVisible(true);
						RegistrierenButton1.setVisible(false);
						RegistrierenButton1.setText("Registrieren");
						setBesitzer();
						KundenDatenButton.setVisible(true);
					}
				 }
			
			
				 else if (ae.getActionCommand().equals("Ausloggen")){
					 benutzer = null;
					 WillkommenPanel.setVisible(false);
					 RegistrierenButton1.setVisible(true);
					 KundenDatenButton.setVisible(false);
					 LoginPanel.setVisible(true);
					 LoginButton.setText("Login");
				 }
			
				 else if (ae.getActionCommand().equals("Jetzt Registrieren")){
					 
					 
					    String vorname = VornameTextField.getText();
						String nachname = NachnameTextField1.getText();
						String benutzername = BenutzernameTextField.getText();
						String pw = PasswordField1.getText();
						String email = EmailTextField.getText();
						
						
						Component frame = null;
						if(vorname.equals("")|| nachname.equals("")||benutzername.equals("")||pw.equals("")||email.equals("")){
							JOptionPane successMessage = new JOptionPane();
					    	successMessage.setSize(400,300);
					    	JOptionPane.showMessageDialog(frame, "Bitte alle Felder ausf�llen!!");
						}
						
						else{
							if(validateEmail(email)== false){
					   
								EmailTextField.setBackground(Color.RED);
					    	}
							else{
							boolean ok = false;
							EmailTextField.setBackground(Color.WHITE);
							VornameTextField.setText("");
							NachnameTextField1.setText("");
							BenutzernameTextField.setText("");
							PasswordField1.setText("");
							EmailTextField.setText("");
							RegistrierenButton1.setText("Registrieren");
							AnmeldenPanel.setVisible(false);
							WarenkorbPanel.setVisible(true);
							
							try {
								ok = sf.fuegeKundeEin(benutzername, pw, email, vorname, nachname);
							} catch (IOException e1) {
	
								e1.printStackTrace();
							}
							if(ok){
								JOptionPane successMessage = new JOptionPane();
						    	successMessage.setSize(400,300);
						    							  
									JOptionPane.showMessageDialog(frame, "Registrierung erfolgreich!!");																										
								
							
						    	successMessage.setSize(400,300);
						    	JOptionPane.showMessageDialog(frame, "Sie k๖nnen sich nun einloggen");
								
							}
							else{
								JOptionPane successMessage = new JOptionPane();
						    	successMessage.setSize(400,300);
						    	JOptionPane.showMessageDialog(frame, "Fehler beim Registrieren!!");
							}
						}
					}
				 }
			
			 
			
				 else if (ae.getActionCommand().equals("Registrieren")){
					 RegistrierenButton1.setText("Warenkorb");
					 WarenkorbPanel.setVisible(false);
					 AnmeldenPanel.setVisible(true);
				 }
				 else if (ae.getActionCommand().equals("Warenkorb")){
					 RegistrierenButton1.setText("Registrieren");
					 WarenkorbPanel.setVisible(true);
					 AnmeldenPanel.setVisible(false);
				 }
			
				 else if (ae.getSource().equals(KundenDatenButton)){
					 KundenDatenFrame kdf = new KundenDatenFrame();
					 kdf.setVisible(true);
				 }
			
		}		 
	}
	
	
	class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			 if (ae.getActionCommand().equals("Exit")) {
				
				setVisible(false);
				dispose();
			} 
		}
	}
	
	
	
	

 /////////////Panel Warenkorbbearbeiten 
	
	class WarenkorbBearbeitenFrame extends JFrame {
		
		private JPanel NorthPanel;
		private JLabel HeadLabel;
		private JButton ProduktEntfernenButton;
		private JButton MengeAendernButton;
		private JLabel cartPic;
		private JButton OkButton;
		private JTextField NeueMengeTextField;
		private JButton SchliessenButton;
		private JScrollPane WarenkorbScrollPane;
		private JPanel NorthPanel2;
		private JPanel SouthPanel;
		private JTable WarenkorbTable;
		private Vector spaltenName;
		private Vector<GewaehltesProdukt> warenkorb;
		
		Object WertName ;
		Object WertPreis ;
		Object WertAnzahl ;
		Object WertID;
		
		private String ID;
		private String Name;
		private String Preis;
		private String Menge;
		
		private int id;
		private String name;
		private Float preis;
		private int menge;
		
		private ListSelectionModel listSelectionModel;
		
		public WarenkorbBearbeitenFrame(Vector<GewaehltesProdukt> warenkorb) {
			super();
			this.warenkorb = warenkorb;
			initGUI();
			
		}
		
		private void initGUI() {
			try {
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				this.setTitle("Warenkorb bearbeiten");
				this.setLocationRelativeTo(null);
				ButtonActionListener bListener = new ButtonActionListener();
				{
					NorthPanel = new JPanel();
					BorderLayout NorthPanelLayout = new BorderLayout();
					NorthPanel.setLayout(NorthPanelLayout);
					getContentPane().add(NorthPanel, BorderLayout.NORTH);
					NorthPanel.setPreferredSize(new java.awt.Dimension(784, 494));
					{
						NorthPanel2 = new JPanel();
						NorthPanel.add(NorthPanel2, BorderLayout.NORTH);
						NorthPanel2.setPreferredSize(new java.awt.Dimension(784, 63));
						NorthPanel2.setLayout(null);
						NorthPanel2.setBackground(new java.awt.Color(192,192,192));
						{
							HeadLabel = new JLabel();
							NorthPanel2.add(HeadLabel);
							HeadLabel.setText("Mein Warenkorb");
							HeadLabel.setBounds(26, 12, 182, 23);
							HeadLabel.setFont(new java.awt.Font("Segoe UI",1,14));
						}
						{
							cartPic = new JLabel(new ImageIcon("iconset/cart/shopcart_48x48.png"));
							NorthPanel2.add(cartPic);
							cartPic.setBounds(694, 5, 48, 48);
						}
					}
					{
						spaltenName = new Vector();
						spaltenName.add("ID");
						spaltenName.add("Name");
						spaltenName.add("Preis");
						spaltenName.add("Menge");
						
						WarenkorbTable = new JTable(new WarenkorbTableModel(warenkorb,spaltenName));
						WarenkorbScrollPane = new JScrollPane(WarenkorbTable);
						
						// Selection Listener
						listSelectionModel = WarenkorbTable.getSelectionModel();
				        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
				        WarenkorbTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
						NorthPanel.add(WarenkorbScrollPane, BorderLayout.CENTER);
						WarenkorbScrollPane.setPreferredSize(new java.awt.Dimension(784, 438));
					}
				}
				{
					SouthPanel = new JPanel();
					getContentPane().add(SouthPanel, BorderLayout.SOUTH);
					SouthPanel.setPreferredSize(new java.awt.Dimension(784, 66));
					SouthPanel.setBackground(new java.awt.Color(255,191,255));
					SouthPanel.setLayout(null);
					{
						MengeAendernButton = new JButton(new ImageIcon("iconset/shopIcons/cart.png"));
						SouthPanel.add(MengeAendernButton);
						MengeAendernButton.setText("Menge bearbeiten");
						MengeAendernButton.setBounds(12, 19, 150, 30);
						MengeAendernButton.setEnabled(false);
						MengeAendernButton.addActionListener(bListener);
						
					}
					{
						ProduktEntfernenButton = new JButton(new ImageIcon("iconset/shopIcons/cart_remove.png"));
						SouthPanel.add(ProduktEntfernenButton);
						ProduktEntfernenButton.setText("Produkt entfernen");
						ProduktEntfernenButton.setBounds(187, 19, 150, 30);
						ProduktEntfernenButton.setEnabled(false);
						ProduktEntfernenButton.addActionListener(bListener);
					}
					{
						SchliessenButton = new JButton(new ImageIcon("iconset/shopIcons/ok.png"));
						SouthPanel.add(SchliessenButton);
						SchliessenButton.setText("Schliessen");
						SchliessenButton.setBounds(635, 19, 129, 30);
						SchliessenButton.addActionListener(bListener);
					}
					{
						NeueMengeTextField = new JTextField();
						SouthPanel.add(NeueMengeTextField);
						NeueMengeTextField.setBounds(13, 19, 40, 30);
						NeueMengeTextField.setEnabled(false);
						NeueMengeTextField.setVisible(false);
					}
					{
						OkButton = new JButton(new ImageIcon("iconset/b_icons/refresh.png"));
						SouthPanel.add(OkButton);
						OkButton.setText("OK");
						OkButton.setBounds(67, 19, 93, 30);
						OkButton.setEnabled(false);
						OkButton.setVisible(false);

						OkButton.addActionListener(bListener);
					}
				}
				pack();
				this.setSize(800, 600);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public Vector<GewaehltesProdukt> getBearbeitetenWarenkorb() {
			return warenkorb;
		}
		
		
		class ButtonActionListener implements ActionListener{

			private String neueZahl;
			private int Zahl;
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource().equals(MengeAendernButton)){
					MengeAendernButton.setVisible(false);
					NeueMengeTextField.setVisible(true);
					OkButton.setVisible(true);
					if(name == null){
						System.out.println("Kein Auswahler");
					}
					else{
						NeueMengeTextField.setEnabled(true);
						OkButton.setEnabled(true);
						
					}
					
				}
				else if(ae.getSource().equals(OkButton)){
					Iterator<GewaehltesProdukt> iter = warenkorb.iterator();
					while (iter.hasNext()) {
						GewaehltesProdukt g = (GewaehltesProdukt) iter.next();
						
						if ( g.getID() == id) {
							
							neueZahl = NeueMengeTextField.getText();
							Zahl = Integer.parseInt(neueZahl);
							if(Zahl> g.getProduktMenge()){
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "Gew�nschte Menge nicht verf�gbar!!");
							}
							else{
								g.setGekaufteAnzahl(Zahl);
														
							}
							TableModel warenkorbTableModel = new WarenkorbTableModel(warenkorb,spaltenName);
							WarenkorbTable.setModel(warenkorbTableModel);
						}
					}
					name = null;
					NeueMengeTextField.setEnabled(false);
					OkButton.setEnabled(false);
					ProduktEntfernenButton.setEnabled(false);
					MengeAendernButton.setEnabled(false);
					NeueMengeTextField.setVisible(false);
					OkButton.setVisible(false);
					MengeAendernButton.setVisible(true);

					
				}
				
				else if(ae.getSource().equals(ProduktEntfernenButton)){
					int choice = JOptionPane.showConfirmDialog (null, "Wollen Sie wirklich diesen Artikel entfernen?","Achtung", JOptionPane.YES_NO_OPTION);
					if(choice==0){
						Iterator<GewaehltesProdukt> iter = warenkorb.iterator();
						Boolean nochNichtEntfernt = true;
						while (iter.hasNext()&& nochNichtEntfernt) {
							GewaehltesProdukt g = (GewaehltesProdukt) iter.next();
							
							if ( g.getProduktName().equals(name)) {
								System.out.println(name);
								warenkorb.removeElement(g);	
								nochNichtEntfernt = false;
							}
						
						}
						TableModel warenkorbTableModel = new WarenkorbTableModel(warenkorb,spaltenName);
						WarenkorbTable.setModel(warenkorbTableModel);
						ProduktEntfernenButton.setEnabled(false);
						MengeAendernButton.setEnabled(false);
					}
					else{				
					}		
				}
				else if(ae.getSource().equals(SchliessenButton)){
					WarenkorbAktualisieren();
					dispose();
				}	
			}	
		}
		
		
		
		
		class SharedListSelectionHandler implements ListSelectionListener {


			public void valueChanged(ListSelectionEvent e) { 
		 	   
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

		            int minIndex = lsm.getMinSelectionIndex();
		            int maxIndex = lsm.getMaxSelectionIndex();
		            for (int i = minIndex; i <= maxIndex; i++) {
		                if (lsm.isSelectedIndex(i)) {
		                    
		                	WertID = 	    WarenkorbTable.getValueAt(i,0);
		                	WertName = 	    WarenkorbTable.getValueAt(i,1);
		                	WertPreis = 	WarenkorbTable.getValueAt(i,2);
		                	WertAnzahl =    WarenkorbTable.getValueAt(i,3);
		        
		             	   
		             	   // wandeln in String um
		             	    ID = String.valueOf( WertID);
		             	    Name = String.valueOf(WertName);
		             	    Preis = String.valueOf(WertPreis);
		             	    Menge = String.valueOf(WertAnzahl);
		             
		             	    
		             	    NeueMengeTextField.setText(Menge);
		             	    id = Integer.parseInt(ID);
		             	    name = Name;
		             	    preis = Float.parseFloat(Preis);
		             	    menge = Integer.parseInt(Menge);
		             	    
		             	   MengeAendernButton.setEnabled(true);
		             	  ProduktEntfernenButton.setEnabled(true);
		             	 
		             	    	        
		                }
		            }
		     }
			
		}

	}
	
	
	
	//// Panel KundenDaten Frame

	
	class KundenDatenFrame extends javax.swing.JFrame {
		private JLabel KundendatenLabel;
		private JLabel VornameLabel;
		private JLabel NachnameLabel;
		private JLabel PasswordLabel;
		private JLabel EmailLabel;
		private JButton SchliessenButton;
		private JButton SpeichernButton;
		private JButton BearbeitenButton;
		private JTextField WohnortTextField;
		private JTextField PostleitzahlTextField;
		private JTextField VornameTextField;
		private JTextField NachnameTextField;
		private JTextField StrasseUndHausnummerTextField;
		private JTextField EmailTextField;
		private JTextField PasswordTextField;
		private JLabel StrasseUndHausnummerLabel;
		private JLabel PlzLabel;
		private JLabel WohnortLabel;
		
		
		private String vorname;
		private String nachname;
		private String password;
		private String email;
		private String strundhausnummer;
		private String plz;
		private String wohnort;

	
		
		public KundenDatenFrame() {
			
		
			initGUI();
		}
		
		private void initGUI() {
			try {
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				getContentPane().setLayout(null);
				this.setPreferredSize(new java.awt.Dimension(500, 500));
				this.setTitle("Kundendaten");
				
				ButtonActionListener bListener = new ButtonActionListener();
				{
					KundendatenLabel = new JLabel();
					getContentPane().add(KundendatenLabel);
					KundendatenLabel.setText("Kundendaten");
					KundendatenLabel.setBounds(56, 22, 197, 51);
					KundendatenLabel.setFont(new java.awt.Font("Tahoma",1,18));
					KundendatenLabel.setHorizontalAlignment(SwingConstants.CENTER);
					KundendatenLabel.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					VornameLabel = new JLabel();
					getContentPane().add(VornameLabel);
					VornameLabel.setText("Vorname");
					VornameLabel.setBounds(56, 91, 95, 29);
				}
				{
					NachnameLabel = new JLabel();
					getContentPane().add(NachnameLabel);
					NachnameLabel.setText("Nachname");
					NachnameLabel.setBounds(56, 132, 83, 23);
				}
				{
					PasswordLabel = new JLabel();
					getContentPane().add(PasswordLabel);
					PasswordLabel.setText("Password");
					PasswordLabel.setBounds(56, 167, 71, 27);
				}
				{
					EmailLabel = new JLabel();
					getContentPane().add(EmailLabel);
					EmailLabel.setText("E-mail");
					EmailLabel.setBounds(57, 202, 63, 23);
				}
				{
					WohnortLabel = new JLabel();
					getContentPane().add(WohnortLabel);
					WohnortLabel.setText("Wohnort");
					WohnortLabel.setBounds(56, 319, 76, 23);
				}
				{
					PlzLabel = new JLabel();
					getContentPane().add(PlzLabel);
					PlzLabel.setText("Postleitzahl");
					PlzLabel.setBounds(55, 281, 71, 22);
				}
				{
					StrasseUndHausnummerLabel = new JLabel();
					getContentPane().add(StrasseUndHausnummerLabel);
					StrasseUndHausnummerLabel.setText("Str./Nr.");
					StrasseUndHausnummerLabel.setBounds(55, 241, 113, 24);
				}
				{
					VornameTextField = new JTextField();
					getContentPane().add(VornameTextField);
					VornameTextField.setBounds(169, 94, 156, 25);
					VornameTextField.setEditable(false);
					VornameTextField.setText(benutzer.getVorname());
				}
				{
					NachnameTextField = new JTextField();
					getContentPane().add(NachnameTextField);
					NachnameTextField.setBounds(169, 131, 156, 25);
					NachnameTextField.setEditable(false);
					NachnameTextField.setText(benutzer.getNachname());
				}
				{
					PasswordTextField = new JPasswordField();
					getContentPane().add(PasswordTextField);
					PasswordTextField.setBounds(169, 169, 156, 24);
					PasswordTextField.setEditable(false);
					PasswordTextField.setText(benutzer.getPassword());
				}
				{
					EmailTextField = new JTextField();
					getContentPane().add(EmailTextField);
					EmailTextField.setBounds(169, 205, 156, 25);
					EmailTextField.setEditable(false);
					EmailTextField.setText(benutzer.getEmail());
				}
				{
					StrasseUndHausnummerTextField = new JTextField();
					getContentPane().add(StrasseUndHausnummerTextField);
					StrasseUndHausnummerTextField.setBounds(169, 243, 156, 26);
					StrasseUndHausnummerTextField.setEditable(false);
					StrasseUndHausnummerTextField.setText(benutzer.getStrasseUndHausnummer());
				}
				{
					PostleitzahlTextField = new JTextField();
					getContentPane().add(PostleitzahlTextField);
					PostleitzahlTextField.setBounds(169, 282, 156, 24);
					PostleitzahlTextField.setEditable(false);
					PostleitzahlTextField.setText(Integer.toString(benutzer.getPLZ()));
				}
				{
					WohnortTextField = new JTextField();
					getContentPane().add(WohnortTextField);
					WohnortTextField.setBounds(169, 319, 156, 25);
					WohnortTextField.setEditable(false);
					WohnortTextField.setText(benutzer.getWohnort());
				}
				{
					BearbeitenButton = new JButton();
					getContentPane().add(BearbeitenButton);
					BearbeitenButton.setText("Bearbeiten");
					BearbeitenButton.setBounds(55, 374, 114, 36);
					BearbeitenButton.addActionListener(bListener);
				}
				{
					SpeichernButton = new JButton();
					getContentPane().add(SpeichernButton);
					SpeichernButton.setText("Speichern");
					SpeichernButton.setBounds(192, 374, 117, 37);
					SpeichernButton.setToolTipText("ฤnderungen �bernehmen");
					SpeichernButton.addActionListener(bListener);
				}
				{
					SchliessenButton = new JButton();
					getContentPane().add(SchliessenButton);
					SchliessenButton.setText("Schliessen");
					SchliessenButton.setBounds(333, 374, 117, 37);
					SchliessenButton.addActionListener(bListener);
				}
				pack();
				this.setSize(500, 500);
				this.setLocationRelativeTo(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		
		class ButtonActionListener implements ActionListener{

			
			@Override
			
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource().equals(BearbeitenButton)){
					VornameTextField.setEditable(true);
					NachnameTextField.setEditable(true);
					PasswordTextField.setEditable(true);
					EmailTextField.setEditable(true);
					StrasseUndHausnummerTextField.setEditable(true);
					PostleitzahlTextField.setEditable(true);
					WohnortTextField.setEditable(true);
				}
				if (ae.getSource().equals(SpeichernButton)){
					vorname = VornameTextField.getText();
					nachname = NachnameTextField.getText();
					password = PasswordTextField.getText();
					email = EmailTextField.getText();
					strundhausnummer = StrasseUndHausnummerTextField.getText();
					plz = PostleitzahlTextField.getText();
					wohnort = WohnortTextField.getText();
					
					benutzer.setVorname(vorname);
					benutzer.setNachname(nachname);
					benutzer.setPassword(password);
					benutzer.setEmail(email);
					benutzer.setStrasseUndHausnummer(strundhausnummer);
					benutzer.setPLZ(Integer.parseInt(plz));
					benutzer.setWohnort(wohnort);
					benutzer.setLieferungVorname(vorname);
					benutzer.setLieferungNachname(nachname);
					benutzer.setLieferungStrasseUndHausnummer(strundhausnummer);
					benutzer.setPLZ(Integer.parseInt(plz));
					benutzer.setWohnort(wohnort);
					
					sf.setKundendaten(benutzer.getBenutzername(),vorname,nachname,password,email,strundhausnummer,plz,wohnort);
					
					
					VornameTextField.setEditable(false);
					NachnameTextField.setEditable(false);
					PasswordTextField.setEditable(false);
					EmailTextField.setEditable(false);
					StrasseUndHausnummerTextField.setEditable(false);
					PostleitzahlTextField.setEditable(false);
					WohnortTextField.setEditable(false);
					
					try {
						sf.schreibeKunde();
					} catch (IOException e1) {
						e1.printStackTrace();
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Fehler beim Speichern!!");
					}
					
					Component frame = null;
					JOptionPane.showMessageDialog(frame, "Ihre Daten wurden gespeichert!!");
				}
				
				
				if (ae.getSource().equals(SchliessenButton)){
					vorname = VornameTextField.getText();
					nachname = NachnameTextField.getText();
					password = PasswordTextField.getText();
					email = EmailTextField.getText();
					strundhausnummer = StrasseUndHausnummerTextField.getText();
					plz = PostleitzahlTextField.getText();
					wohnort = WohnortTextField.getText();
					
					try {
						Kunde k = sf.KundenLogin(benutzer.getBenutzername(), password);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
				}
				
			}
		}	
			
	}	
	
	
		
 ////////////////// Bezahlen /////////////////		
			
			class BezahlenFrame extends javax.swing.JFrame {
				private JPanel NorthPanel;
				private JTextField VornameTextField;
				private JLabel NachNameLabel;
				private JTextField StrasseUndHausNummerTextField;
				private JLabel StrasseUndHausNummerLabel;
				private JTextField NachnameTextField;
				private JLabel VorNameLabel;
				private JButton SpeichernButton;
				private JButton ZurueckButton1;
				private JButton BezahlenButton;
				private JScrollPane WarenkorbScrollPane;
				private JPanel BearbeitenAddressePanel;
				private JLabel LieferungAddressLabel;
				private JTextField WohnortTextField;
				private JLabel WohnortLabel;
				private JTextField PlzTextField;
				private JLabel PlzLabel;
				private JButton BearbeitenButton;
				private JTextPane AddressTextPane;
				private JPanel CenterPanel;
				private JTextField SummeTextField;
				private JLabel SummeLabel;
				private JLabel EuroLabel;
			
				private float gesamteSumme;
				private Vector spaltenName;
				private JTable WarenkorbTable;

					
				public BezahlenFrame(float summe) {
					super();
					this.gesamteSumme = summe;
					initGUI();
				}
				
				@SuppressWarnings("deprecation")
				private void initGUI() {
					try {
						setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						ButtonActionListener bListener = new ButtonActionListener();
						this.setTitle("Kasse");
						{
							NorthPanel = new JPanel();
							getContentPane().add(NorthPanel, BorderLayout.NORTH);
							NorthPanel.setPreferredSize(new java.awt.Dimension(784, 194));
							NorthPanel.setBackground(new java.awt.Color(128,255,255));
							NorthPanel.setLayout(null);
							{
								AddressTextPane = new JTextPane();
								NorthPanel.add(AddressTextPane);
								AddressTextPane.setBounds(33, 50, 270, 102);
								zeigeLieferungAddresse();
								AddressTextPane.setEditable(false);
							}
							{
								BearbeitenButton = new JButton(new ImageIcon("iconset/shopIcons/ok.png"));
								NorthPanel.add(BearbeitenButton);
								BearbeitenButton.setText("Bearbeiten");
								BearbeitenButton.setBounds(177, 161, 126, 30);
								BearbeitenButton.addActionListener(bListener);
							}
							{
								LieferungAddressLabel = new JLabel();
								NorthPanel.add(LieferungAddressLabel);
								LieferungAddressLabel.setText("    Lieferungsaddresse");
								LieferungAddressLabel.setBounds(33, 12, 180, 32);
								LieferungAddressLabel.setFont(new java.awt.Font("Segoe UI",1,14));
								LieferungAddressLabel.setBackground(new java.awt.Color(64,0,64));
								LieferungAddressLabel.setBorder(BorderFactory.createTitledBorder(""));
							}
							{
								BearbeitenAddressePanel = new JPanel();
								NorthPanel.add(BearbeitenAddressePanel);
								BearbeitenAddressePanel.setBounds(367, 12, 391, 169);
								BearbeitenAddressePanel.setLayout(null);
								BearbeitenAddressePanel.setVisible(false);
								{
									SpeichernButton = new JButton();
									BearbeitenAddressePanel.add(SpeichernButton);
									SpeichernButton.setText("Speichern");
									SpeichernButton.setBounds(285, 130, 86, 22);
									SpeichernButton.addActionListener(bListener);
								}
								{
									VornameTextField = new JTextField();
									BearbeitenAddressePanel.add(VornameTextField);
									VornameTextField.setBounds(109, 12, 140, 23);
								}
								{
									VorNameLabel = new JLabel();
									BearbeitenAddressePanel.add(VorNameLabel);
									VorNameLabel.setText("Vorname");
									VorNameLabel.setBounds(13, 15, 57, 13);
								}
								{
									NachNameLabel = new JLabel();
									BearbeitenAddressePanel.add(NachNameLabel);
									NachNameLabel.setText("Nachname");
									NachNameLabel.setBounds(11, 45, 74, 15);
								}
								{
									NachnameTextField = new JTextField();
									BearbeitenAddressePanel.add(NachnameTextField);
									NachnameTextField.setBounds(109, 41, 140, 23);
								}
								{
									StrasseUndHausNummerLabel = new JLabel();
									BearbeitenAddressePanel.add(StrasseUndHausNummerLabel);
									StrasseUndHausNummerLabel.setText("Str./Nr.");
									StrasseUndHausNummerLabel.setBounds(10, 72, 75, 18);
								}
								{
									StrasseUndHausNummerTextField = new JTextField();
									BearbeitenAddressePanel.add(StrasseUndHausNummerTextField);
									StrasseUndHausNummerTextField.setBounds(109, 70, 139, 24);
								}
								{
									PlzLabel = new JLabel();
									BearbeitenAddressePanel.add(PlzLabel);
									PlzLabel.setText("Plz");
									PlzLabel.setBounds(11, 102, 80, 16);
								}
								{
									PlzTextField = new JTextField();
									BearbeitenAddressePanel.add(PlzTextField);
									PlzTextField.setBounds(109, 100, 138, 24);
								}
								{
									WohnortLabel = new JLabel();
									BearbeitenAddressePanel.add(WohnortLabel);
									WohnortLabel.setText("Wohnort");
									WohnortLabel.setBounds(12, 132, 60, 16);
								}
								{
									WohnortTextField = new JTextField();
									BearbeitenAddressePanel.add(WohnortTextField);
									WohnortTextField.setBounds(109, 130, 138, 22);
								}
							}
						}
						{
							CenterPanel = new JPanel();
							getContentPane().add(CenterPanel, BorderLayout.SOUTH);
							CenterPanel.setPreferredSize(new java.awt.Dimension(784, 365));
							CenterPanel.setBackground(new java.awt.Color(255,255,255));
							CenterPanel.setLayout(null);
							{
								spaltenName = new Vector();
								spaltenName.add("Id");
								spaltenName.add("Name");
								spaltenName.add("Preis");
								spaltenName.add("Menge");
								
								WarenkorbTable = new JTable(new WarenkorbTableModel(warenkorb,spaltenName));
								WarenkorbScrollPane = new JScrollPane(WarenkorbTable);
								CenterPanel.add(WarenkorbScrollPane);
								WarenkorbScrollPane.setBounds(0, 0, 784, 289);
							}
							{
								BezahlenButton = new JButton(new ImageIcon("iconset/shopIcons/credit_card.png"));
								CenterPanel.add(BezahlenButton);
								BezahlenButton.setText("Bezahlen");
								BezahlenButton.setBounds(660, 301, 107, 48);
								BezahlenButton.addActionListener(bListener);
							}
							{
								ZurueckButton1 = new JButton(new ImageIcon("iconset/shopIcons/cancel.png"));
								CenterPanel.add(ZurueckButton1);
								ZurueckButton1.setText("Zurueck");
								ZurueckButton1.setBounds(37, 312, 183, 30);
								ZurueckButton1.addActionListener(bListener);
							}
							
							{
								SummeLabel = new JLabel();
								CenterPanel.add(SummeLabel);
								SummeLabel.setText("Summe");
								SummeLabel.setBounds(410, 313, 66, 27);
								SummeLabel.setFont(new java.awt.Font("Segoe UI",1,14));
							}
							{
								SummeTextField = new JTextField();
								CenterPanel.add(SummeTextField);
								SummeTextField.setBounds(483, 315, 87, 24);
								SummeTextField.setText(Float.toString(gesamteSumme));
								SummeTextField.setEditable(false);
							}
							{
								EuroLabel = new JLabel();
								CenterPanel.add(EuroLabel);
								EuroLabel.setText("Euro");
								EuroLabel.setBounds(588, 317, 45, 22);
							}
							
						}
						pack();
						this.setSize(800, 600);
						this.setLocationRelativeTo(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
				private void zeigeLieferungAddresse() {
					AddressTextPane.setText(benutzer.getLieferungVorname()+" " +benutzer.getLieferungNachname()+"\n"
					+benutzer.getLieferungStrasseUndHausnummer()+"\n"+
					Integer.toString(benutzer.getLieferungPLZ())+"\n"+
					benutzer.getLieferungWohnort());
				}


				class ButtonActionListener implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent ae) {
						if (ae.getSource().equals(BearbeitenButton)){
							BearbeitenAddressePanel.setVisible(true);
							VornameTextField.setText(benutzer.getLieferungVorname());
							NachnameTextField.setText(benutzer.getLieferungNachname());
							StrasseUndHausNummerTextField.setText(benutzer.getLieferungStrasseUndHausnummer());
							PlzTextField.setText(Integer.toString(benutzer.getLieferungPLZ()));
							WohnortTextField.setText(benutzer.getLieferungWohnort());
						}
						
						else if (ae.getSource().equals(SpeichernButton)){
							
							if(VornameTextField.getText().equals("")|| NachnameTextField.getText().equals("") ||
							  StrasseUndHausNummerTextField.getText().equals("")|| PlzTextField.getText().equals("")||
							  WohnortTextField.getText().equals("")){
								
								Component errorMessage = null;
								JOptionPane.showMessageDialog(errorMessage, "Bitte alle Felden erfuellen!!");
							}
							else{
								benutzer.setLieferungVorname(VornameTextField.getText());
								benutzer.setLieferungNachname(NachnameTextField.getText());
								benutzer.setLieferungStrasseUndHausnummer(StrasseUndHausNummerTextField.getText()) ;
								benutzer.setLieferungPLZ(Integer.parseInt(PlzTextField.getText()));
								benutzer.setLieferungWohnort(WohnortTextField.getText());
								zeigeLieferungAddresse();
								BearbeitenAddressePanel.setVisible(false);
							}
								
							
						}
						
						else if (ae.getSource().equals(ZurueckButton1)){
							dispose();
						}
						
						else if (ae.getSource().equals(BezahlenButton)){
							int choice = JOptionPane.showConfirmDialog (null, "Wollen Sie schon bezahlen?","Achtung", JOptionPane.YES_NO_OPTION);
							if(choice == 0){
								Rechnung rechnung = null;
								try {
									rechnung = sf.erstellenNeueRechnung(warenkorb,gesamteSumme,benutzer);
								} catch (IOException e2) {
									e2.printStackTrace();
								}
								RechnungAnzeigenFrame raf = new RechnungAnzeigenFrame(rechnung);
								raf.setVisible(true);
								
									try {
										sf.setProduktMenge(warenkorb);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								
								
								dispose();
								// leeren Warenkorb
								warenkorb.clear();
								
								// Aktualisieren Artikelliste
								try {
									((PanelProduktliste) ProduktListeJTable).ErstelltTableModel(sf.gibAlleProdukt());
								} catch (IOException e) {
									e.printStackTrace();
								}
								JScrollPane alleArtikel = ((PanelProduktliste) ProduktListeJTable).getScollPanel();
								NorthPanel3.removeAll();
								NorthPanel3.add(alleArtikel);
								
								// Aktualisieren Warenkorbliste 
								WarenkorbAktualisieren();
							}
							else{
								
							}
						}
						
					}
					
				}

			}
			
			

			public static void main(String[] args) {

				int port = 0;
				String host = null;
				InetAddress ia = null;

				if (args.length > 2) {
					System.out.println("Aufruf: java <Klassenname> [<hostname> [<port>]]");
					System.exit(0);
				}
				if (args.length == 0) {
					try {
						ia = InetAddress.getLocalHost();
					} catch (Exception e) {
						System.out.println("XXX InetAdress-Fehler: " + e);
						System.exit(0);
					}
					host = ia.getHostName(); // host ist lokale Maschine "192.168.159.129";
					port = DEFAULT_PORT;
				}
				if (args.length == 1) {
					port = DEFAULT_PORT;
					host = args[0];
				}

				if (args.length == 2) {
					host = args[0];
					try {
						port = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
						System.out.println("Aufruf: java BibClientGUI [<hostname> [<port>]]");
						System.exit(0);
					}
				}
				
						JFrame kundenClientFrame = new KundenClient("Kunden Interface");
						kundenClientFrame.setLocationRelativeTo(null);
			}
			
			
		
	

}
