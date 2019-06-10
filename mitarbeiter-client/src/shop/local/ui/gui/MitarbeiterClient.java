package shop.local.ui.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import shop.local.client.ShopFassade;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.ProduktNichtGefundenException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.interfaces.EShopInterface;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelProduktAnlegen;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelProduktliste;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelBearbeiteProdukt;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelBearbeiteKunde;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelBearbeiteMitarbeiter;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelKundenliste;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelMitarbeiterErstellen;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelMitarbeiterliste;
import shop.local.ui.gui.mitarbeiterClientPanels.PanelStatistik;
import shop.local.valueobjects.Mitarbeiter;



public class MitarbeiterClient extends JFrame implements ActionListener{
	
	{ 
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static final int DEFAULT_PORT = 6789;

	
	public static EShopInterface sf;
	private String host;
	public static int status=0;

	private JPanel rechtsPanel;
	private JPanel linksPanel;
	private JMenuItem kontakt;
	private JMenuItem menuBeenden;
	private JMenuBar mb;
	private JPanel pNeuerMitarbeiter;
	private JPanel pProduktAnlegen;
	private PanelKundenliste pKundenlisteAnzeigen;
	private JPanel pProduktBearbeiten;
	private JPanel pMitarbeiterBearbeiten;
	private PanelProduktliste pProduktlisteAnzeigen;
	private JPanel pKundenBearbeiten;
	private JPanel pMitarbeiterlisteAnzeigen;
	private JPanel pLogin;
	
	private JButton buttonMitarbeiterAnzeigen;
	private JButton buttonMitarbeiterBearbeiten;
	private JButton buttonProduktAnlegen;
	private JButton buttonProduktAnzeigen;
	private JButton buttonNeuerMitarbeiter;
	private JButton buttonKundeBearbeiten;
	private JButton buttonProduktBearbeiten;
	private JButton buttonProduktHistory;
	private JButton buttonKundenAnzeigen;
	private JButton buttonSpeichernUndBeenden;
	private JButton log;
	
	private JLabel labelHistory;
	private JLabel labelMitarbeiterEinstellungen;
	private JLabel labelKundenEinstellungen;
	private JLabel labelProduktMenu;
	private JLabel labelID;
	private JLabel labelMID;
	private JLabel labelPW;

	public static JPasswordField pwfield;
	public static JTextField textfieldMitarbeiterID;
	
	private int menuRigidAreaHeight 	= 5;
	private int menuRigidAreaWidth 		= 200;
	private int menuButtonWidth 		= 160;
	private int menuButtonHeight 		= 40;
	
	private PanelStatistik pStatistikZeigen;
	private JMenu datei;
	private JMenu hilfe;




	public static void main(String[] args) throws ProduktNichtGefundenException, IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException {
	
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
			host = ia.getHostName();
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

		JFrame mc = new MitarbeiterClient();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (dim.getWidth() - 900);
		int y = (int) (dim.getHeight() - 720);
		x = x / 2;
		y = y / 2;

		mc.setLocation(x,y);
		mc.setVisible(true);
	}
	
	public MitarbeiterClient() throws ProduktNichtGefundenException, IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException {
		super();
		initGUI();		
		login();
	}
	
	private void initGUI() {
		try {
			
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setTitle("Mitarbeiter Zone");
			setPreferredSize(new java.awt.Dimension(900, 720));
			setMaximumSize(new java.awt.Dimension(1600, 1200));
			setMinimumSize(new java.awt.Dimension(900, 720));
			BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS);
			getContentPane().setLayout(thisLayout);
			{
				linksPanel = new JPanel();
				BoxLayout linksPanelLayout = new BoxLayout(linksPanel, javax.swing.BoxLayout.Y_AXIS);
				linksPanel.setLayout(linksPanelLayout);
				getContentPane().add(linksPanel);
				linksPanel.setBackground(new java.awt.Color(124,156,173));
				linksPanel.setPreferredSize(new java.awt.Dimension(200, 800));
				linksPanel.setMaximumSize(new java.awt.Dimension(200, 5000));
				linksPanel.setMinimumSize(new java.awt.Dimension(200, 800));
				linksPanel.setSize(200, 800);
				linksPanel.setVisible(false);
				{	
					{
						mb = new JMenuBar();
						setJMenuBar(mb);
						{
							datei = new JMenu();
							mb.add(datei);
							datei.setText("Datei");
							{
								menuBeenden = new JMenuItem(new ImageIcon("iconset/menubarIcons/power_off.png"));
								datei.add(menuBeenden);
								menuBeenden.setText("Beenden");
								menuBeenden.addActionListener(new ActionListener() {
							         public void actionPerformed(ActionEvent e) {
									    try {
											sf.disconnect();
										} catch (IOException e1) {											e1.printStackTrace();
										}
							        	System.exit(0);
							         }
							    });
							}
						}						
						{
							hilfe = new JMenu();
							mb.add(hilfe);
							hilfe.setText("Hilfe");
							{
								kontakt = new JMenuItem(new ImageIcon("iconset/menubarIcons/email.png"));
								hilfe.add(kontakt);
								kontakt.setText("Kontakt");
							}
						}
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(5,2)));
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,30)));
					}
					{
						labelProduktMenu = new JLabel();
						linksPanel.add(labelProduktMenu);
						labelProduktMenu.setText("Produkt Einstellungen");
						labelProduktMenu.setAlignmentX(CENTER_ALIGNMENT);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonProduktAnzeigen = new JButton(new ImageIcon("iconset/32x32/note_book.png"));
						linksPanel.add(buttonProduktAnzeigen);
						buttonProduktAnzeigen.setAlignmentX(CENTER_ALIGNMENT);
						buttonProduktAnzeigen.setText("Produkt anzeigen");
						buttonProduktAnzeigen.setToolTipText("Produktliste anzeigen");
						buttonProduktAnzeigen.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktAnzeigen.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktAnzeigen.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktAnzeigen.addActionListener(this);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonProduktAnlegen = new JButton(new ImageIcon("iconset/32x32/add_pages.png"));
						linksPanel.add(buttonProduktAnlegen);
						buttonProduktAnlegen.setText("Produkt anlegen");
						buttonProduktAnlegen.setAlignmentX(CENTER_ALIGNMENT);
						buttonProduktAnlegen.setToolTipText("Ein neues Produkt erstellen");
						buttonProduktAnlegen.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktAnlegen.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktAnlegen.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktAnlegen.addActionListener(this);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonProduktBearbeiten = new JButton(new ImageIcon("iconset/32x32/tools.png"));
						linksPanel.add(buttonProduktBearbeiten);
						buttonProduktBearbeiten.setText("Produkt bearbeiten");
						buttonProduktBearbeiten.setAlignmentX(CENTER_ALIGNMENT);
						buttonProduktBearbeiten.setToolTipText("Produktdaten bearbeiten");
						buttonProduktBearbeiten.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktBearbeiten.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktBearbeiten.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktBearbeiten.addActionListener(this);
					}

					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,30)));
					}
					{
						labelKundenEinstellungen = new JLabel();
						linksPanel.add(labelKundenEinstellungen);
						labelKundenEinstellungen.setText("Kunden Einstellungen");
						labelKundenEinstellungen.setAlignmentX(CENTER_ALIGNMENT);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonKundenAnzeigen = new JButton(new ImageIcon("iconset/32x32/id_card.png"));
						linksPanel.add(buttonKundenAnzeigen);
						buttonKundenAnzeigen.setText("Kunden anzeigen");
						buttonKundenAnzeigen.setAlignmentX(CENTER_ALIGNMENT);
						buttonKundenAnzeigen.setToolTipText("Alle Kunden anzeigen");
						buttonKundenAnzeigen.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonKundenAnzeigen.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonKundenAnzeigen.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonKundenAnzeigen.addActionListener(this);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonKundeBearbeiten = new JButton(new ImageIcon("iconset/32x32/tools.png"));
						linksPanel.add(buttonKundeBearbeiten);
						buttonKundeBearbeiten.setText("Kunden bearbeiten");
						buttonKundeBearbeiten.setAlignmentX(CENTER_ALIGNMENT);
						buttonKundeBearbeiten.setToolTipText("Kundendaten bearbeiten");
						buttonKundeBearbeiten.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonKundeBearbeiten.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonKundeBearbeiten.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonKundeBearbeiten.addActionListener(this);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,30)));
					}
					{
						labelHistory = new JLabel();
						linksPanel.add(labelHistory);
						labelHistory.setText("Statistik");
						labelHistory.setAlignmentX(CENTER_ALIGNMENT);						
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonProduktHistory = new JButton(new ImageIcon("iconset/32x32/report.png"));
						linksPanel.add(buttonProduktHistory);
						buttonProduktHistory.setText("Statistk anzeigen");
						buttonProduktHistory.setAlignmentX(CENTER_ALIGNMENT);
						buttonProduktHistory.setToolTipText("Statistik der Produkte anzeigen");
						buttonProduktHistory.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktHistory.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktHistory.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonProduktHistory.addActionListener(this);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,30)));
					}
					{
						labelMitarbeiterEinstellungen = new JLabel();
						linksPanel.add(labelMitarbeiterEinstellungen);
						labelMitarbeiterEinstellungen.setText("Mitarbeiter Einstellungen");
						labelMitarbeiterEinstellungen.setAlignmentX(CENTER_ALIGNMENT);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonMitarbeiterAnzeigen = new JButton(new ImageIcon("iconset/32x32/image.png"));
						linksPanel.add(buttonMitarbeiterAnzeigen);
						buttonMitarbeiterAnzeigen.setText("Mitarbeiter anzeigen");
						buttonMitarbeiterAnzeigen.setAlignmentX(CENTER_ALIGNMENT);
						buttonMitarbeiterAnzeigen.setToolTipText("Alle Mitarbeiter anzeigen");
						buttonMitarbeiterAnzeigen.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonMitarbeiterAnzeigen.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonMitarbeiterAnzeigen.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonMitarbeiterAnzeigen.addActionListener(this);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonNeuerMitarbeiter = new JButton(new ImageIcon("iconset/32x32/add_image.png"));
						linksPanel.add(buttonNeuerMitarbeiter);
						buttonNeuerMitarbeiter.setText("Mitarbeiter anlegen");
						buttonNeuerMitarbeiter.setAlignmentX(CENTER_ALIGNMENT);
						buttonNeuerMitarbeiter.setToolTipText("Einen neuen Mitarbeiter anlegen");
						buttonNeuerMitarbeiter.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonNeuerMitarbeiter.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonNeuerMitarbeiter.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonNeuerMitarbeiter.addActionListener(this);
					}
					{
						linksPanel.add(Box.createRigidArea(new Dimension(menuRigidAreaWidth,menuRigidAreaHeight)));
					}
					{
						buttonMitarbeiterBearbeiten = new JButton(new ImageIcon("iconset/32x32/tools.png"));
						linksPanel.add(buttonMitarbeiterBearbeiten);
						buttonMitarbeiterBearbeiten.setText("Mitarbeiter bearbeiten");
						buttonMitarbeiterBearbeiten.setAlignmentX(CENTER_ALIGNMENT);
						buttonMitarbeiterBearbeiten.setToolTipText("Mitarbeiterdaten bearbeiten");
						buttonMitarbeiterBearbeiten.setPreferredSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonMitarbeiterBearbeiten.setMaximumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonMitarbeiterBearbeiten.setMinimumSize(new Dimension(menuButtonWidth,menuButtonHeight));
						buttonMitarbeiterBearbeiten.addActionListener(this);
					}

					{
						linksPanel.add(Box.createRigidArea(new Dimension(10,30)));
					}
					{
						buttonSpeichernUndBeenden = new JButton();
						linksPanel.add(buttonSpeichernUndBeenden);
						buttonSpeichernUndBeenden.setText("Ausloggen");
						buttonSpeichernUndBeenden.setToolTipText("Speichern und ausloggen");
						buttonSpeichernUndBeenden.setAlignmentX(CENTER_ALIGNMENT);
						buttonSpeichernUndBeenden.addActionListener(this);
					}

				}
			}
			{
				rechtsPanel = new JPanel();
				CardLayout rechtsPanelLayout = new CardLayout();
				getContentPane().add(rechtsPanel);
				rechtsPanel.setLayout(rechtsPanelLayout);
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void login(){

		{
			pLogin = new JPanel();
			BoxLayout pLoginLayout = new BoxLayout(pLogin, javax.swing.BoxLayout.Y_AXIS);
			pLogin.setLayout(pLoginLayout);
			rechtsPanel.add(pLogin, "Einloggen");
			pLogin.setVisible(true);
		}
		{
			pLogin.add(Box.createRigidArea(new Dimension(10,250)));
		}
		{
			labelID = new JLabel();
			pLogin.add(labelID);
			labelID.setText("Login");
			labelID.setAlignmentX(CENTER_ALIGNMENT);
			labelID.setPreferredSize(new Dimension(200,30));
			labelID.setMaximumSize(new Dimension(200,30));
			labelID.setMinimumSize(new Dimension(200,30));
			labelID.setFont(new java.awt.Font("SansSerif",1,20));
		}
		{
			pLogin.add(Box.createRigidArea(new Dimension(100,30)));
		}
		{
			JPanel p1 = new JPanel();
			pLogin.add(p1);
			BoxLayout p1Layout = new BoxLayout(p1, javax.swing.BoxLayout.X_AXIS);
			p1.setLayout(p1Layout);
			{
				labelMID = new JLabel();
				p1.add(labelMID);
				labelMID.setText("Mitarbeiter ID");
				labelMID.setAlignmentX(CENTER_ALIGNMENT);
				labelMID.setPreferredSize(new Dimension(100,20));
				labelMID.setMaximumSize(new Dimension(100,20));
				labelMID.setMinimumSize(new Dimension(100,20));
			}
			{
				textfieldMitarbeiterID = new JTextField();
				p1.add(textfieldMitarbeiterID);
				textfieldMitarbeiterID.setAlignmentX(CENTER_ALIGNMENT);
				textfieldMitarbeiterID.setPreferredSize(new Dimension(100,20));
				textfieldMitarbeiterID.setMaximumSize(new Dimension(100,20));
				textfieldMitarbeiterID.setMinimumSize(new Dimension(100,20));
			}
		}
		{
			pLogin.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p2 = new JPanel();
			pLogin.add(p2);
			BoxLayout p1Layout = new BoxLayout(p2, javax.swing.BoxLayout.X_AXIS);
			p2.setLayout(p1Layout);
			{
				labelPW = new JLabel();
				p2.add(labelPW);
				labelPW.setText("Password");
				labelPW.setAlignmentX(CENTER_ALIGNMENT);
				labelPW.setPreferredSize(new Dimension(100,20));
				labelPW.setMaximumSize(new Dimension(100,20));
				labelPW.setMinimumSize(new Dimension(100,20));
			}
			{
				pwfield = new JPasswordField();
				p2.add(pwfield);
				pwfield.setText("");
				pwfield.setAlignmentX(CENTER_ALIGNMENT);
				pwfield.setPreferredSize(new Dimension(100,20));
				pwfield.setMaximumSize(new Dimension(100,20));
				pwfield.setMinimumSize(new Dimension(100,20));
			}
		}
		{
			pLogin.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p3 = new JPanel();
			pLogin.add(p3);
			BoxLayout p1Layout = new BoxLayout(p3, javax.swing.BoxLayout.X_AXIS);
			p3.setLayout(p1Layout);
			{
				log = new JButton(new ImageIcon("iconset/b_icons/login.png"));
				p3.add(log);
				log.setText("Connect");
				log.setAlignmentX(CENTER_ALIGNMENT);
				log.setPreferredSize(new Dimension(130,30));
				log.setMaximumSize(new Dimension(130,30));
				log.setMinimumSize(new Dimension(130,30));
				log.addActionListener(new ActionListener() {
			         private Component popup;

					public void actionPerformed(ActionEvent e) {
			        	 try {
			     			sf = new ShopFassade(host, DEFAULT_PORT);
			     			if(status==1){
			     				Mitarbeiter m = sf.mitarbeiterLogin(textfieldMitarbeiterID.getText(), pwfield.getText());
								if(m !=null){
										
										anderePanelsInitialisieren();
								    	JOptionPane successMessage = new JOptionPane();
								    	successMessage.setSize(400,300);
								    	JOptionPane.showMessageDialog(popup, "Willkommen "+m.getVorname()+" "+m.getNachname()+" !","Erfolgreich eingeloggt", JOptionPane.INFORMATION_MESSAGE);
										textfieldMitarbeiterID.setText("");
										pwfield.setText("");
										pLogin.setVisible(false);
										linksPanel.setVisible(true);
										pLogin.setVisible(false);
										pProduktlisteAnzeigen.setVisible(true);
								 }
								 else{
								    	JOptionPane errorMessage = new JOptionPane();
								    	errorMessage.setSize(400,300);
								    	JOptionPane.showMessageDialog(popup, "ID oder Password falsch... \nBitte versuchen Sie noch mal!","", JOptionPane.INFORMATION_MESSAGE);
								    	sf.disconnect();
								 }
			     			}
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			         }
			     });
			}
		}
	}

	
	public void anderePanelsInitialisieren() throws IOException, ProduktExistiertBereitsException, MitarbeiterExistiertBereitsException, ProduktNichtGefundenException{
		//	Andere Panel laden und einstellen:
		{
			pNeuerMitarbeiter = new PanelMitarbeiterErstellen();
			rechtsPanel.add(pNeuerMitarbeiter, "pNeuerMitarbeiter");
			pNeuerMitarbeiter.setVisible(false);
		}
		{
			pProduktAnlegen = new PanelProduktAnlegen();
			rechtsPanel.add(pProduktAnlegen, "Produkt neu erstellen");///////
			pProduktAnlegen.setVisible(false);
		}
		{
			pKundenlisteAnzeigen = new PanelKundenliste();
			rechtsPanel.add(pKundenlisteAnzeigen, "Kundenliste");
			pKundenlisteAnzeigen.setVisible(false);
		}
		{
			pProduktlisteAnzeigen = new PanelProduktliste();
			rechtsPanel.add(pProduktlisteAnzeigen, "Produktliste");///////////
			pProduktlisteAnzeigen.setVisible(false);
		}
		{
			pKundenBearbeiten = new PanelBearbeiteKunde();
			rechtsPanel.add(pKundenBearbeiten, "Kunden bearbeiten");
			pKundenBearbeiten.setVisible(false);
		}
		{
			pProduktBearbeiten = new PanelBearbeiteProdukt();
			rechtsPanel.add(pProduktBearbeiten, "Produkt bearbeiten");///////////
			pProduktBearbeiten.setVisible(false);
		}
		{
			pMitarbeiterlisteAnzeigen = new PanelMitarbeiterliste();
			rechtsPanel.add(pMitarbeiterlisteAnzeigen, "Mitarbeiterliste");
			pMitarbeiterlisteAnzeigen.setVisible(false);
		}
		{
			pMitarbeiterBearbeiten = new PanelBearbeiteMitarbeiter();
			rechtsPanel.add(pMitarbeiterBearbeiten, "Mitarbeiter bearbeiten");
			pMitarbeiterBearbeiten.setVisible(false);
		}
		{
			pStatistikZeigen = new PanelStatistik();
			rechtsPanel.add(pStatistikZeigen, "Produktstatistik");//////////////
			pStatistikZeigen.setVisible(false);
		}


	}
	
	//Button-ActionListener
	@Override
	public void actionPerformed(ActionEvent ae) {
		//Produkt anzeigen Button
		if (ae.getSource().equals(buttonProduktAnzeigen)) {	
			pProduktlisteAnzeigen.aktualisiereTable();
			pProduktlisteAnzeigen.setVisible(true);
			pKundenlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(false);
			pKundenBearbeiten.setVisible(false);
			pProduktBearbeiten.setVisible(false);
			pStatistikZeigen.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(false);
			pLogin.setVisible(false);
		} 
		//Produkt anlegen Button
		if (ae.getSource().equals(buttonProduktAnlegen)) {
			pProduktlisteAnzeigen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(true);
			pKundenBearbeiten.setVisible(false);
			pProduktBearbeiten.setVisible(false);
			pStatistikZeigen.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(false);
			pLogin.setVisible(false);
		}
		if (ae.getSource().equals(buttonProduktBearbeiten)) {
			pProduktlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(false);
			pKundenBearbeiten.setVisible(false);
			pProduktBearbeiten.setVisible(true);
			pStatistikZeigen.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(false);
			pLogin.setVisible(false);
		} 
		//Kunden anzeigen Button
		if (ae.getSource().equals(buttonKundenAnzeigen)) {
			pProduktlisteAnzeigen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(true);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(false);
			pKundenBearbeiten.setVisible(false);
			pProduktBearbeiten.setVisible(false);
			pStatistikZeigen.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(false);
			pLogin.setVisible(false);
		} 
		if (ae.getSource().equals(buttonKundeBearbeiten)) {
			pProduktlisteAnzeigen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(false);
			pKundenBearbeiten.setVisible(true);
			pProduktBearbeiten.setVisible(false);
			pStatistikZeigen.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(false);
			pLogin.setVisible(false);
		} 
		if (ae.getSource().equals(buttonProduktHistory)) {
			pProduktlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(false);
			pKundenBearbeiten.setVisible(false);
			pLogin.setVisible(false);
			pProduktBearbeiten.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(false);
			pStatistikZeigen.setVisible(true);
		}  
		if (ae.getSource().equals(buttonMitarbeiterAnzeigen)) {
			pProduktlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(false);
			pKundenBearbeiten.setVisible(false);
			pProduktBearbeiten.setVisible(false);
			pStatistikZeigen.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(true);
			pMitarbeiterBearbeiten.setVisible(false);
			pLogin.setVisible(false);
		}
		if (ae.getSource().equals(buttonNeuerMitarbeiter)) {	
			pProduktlisteAnzeigen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(true);
			pProduktAnlegen.setVisible(false);
			pKundenBearbeiten.setVisible(false);
			pProduktBearbeiten.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pStatistikZeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(false);
			pLogin.setVisible(false);
		} 
		if (ae.getSource().equals(buttonMitarbeiterBearbeiten)) {
			pProduktlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(false);
			pKundenBearbeiten.setVisible(false);
			pProduktBearbeiten.setVisible(false);
			pStatistikZeigen.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(true);
			pLogin.setVisible(false);
		}
		if (ae.getSource().equals(buttonSpeichernUndBeenden)) {

			pProduktlisteAnzeigen.setVisible(false);
			pNeuerMitarbeiter.setVisible(false);
			pProduktAnlegen.setVisible(false);
			pKundenlisteAnzeigen.setVisible(false);
			pKundenBearbeiten.setVisible(false);
			pProduktBearbeiten.setVisible(false);
			pMitarbeiterlisteAnzeigen.setVisible(false);
			pMitarbeiterBearbeiten.setVisible(false);
			pStatistikZeigen.setVisible(false);
			linksPanel.setVisible(false);
			pLogin.setVisible(true);
	    	try {
				sf.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}
}


