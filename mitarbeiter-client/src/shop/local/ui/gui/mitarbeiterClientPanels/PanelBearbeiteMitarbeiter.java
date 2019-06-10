package shop.local.ui.gui.mitarbeiterClientPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import shop.local.exceptions.ProduktExistiertBereitsException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.ui.gui.MitarbeiterClient;

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

public class PanelBearbeiteMitarbeiter extends javax.swing.JPanel{

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PanelBearbeiteMitarbeiter());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private JLabel labelAlterWert;
	private JLabel labelNeuerWert;
	private JLabel labelMitarbeiterID;
	private JTextField textfieldMitarbeiterID;
	private JTextField textfieldMitarbeiterIDNeu;
	private JLabel labelVorname;
	private JTextField textfieldVorname;
	private JTextField textfieldVornameNeu;
	private JLabel labelNachname;
	private JTextField textfieldNachname;
	private JTextField textfieldNachnameNeu;
	private JLabel labelMail;
	private JTextField textfieldMail;
	private JTextField textfieldMailNeu;
	private JLabel labelWohnort;
	private JTextField textfieldWohnort;
	private JTextField textfieldWohnortNeu;
	private JLabel labelStrasseNr;
	private JTextField textfieldStrasseNr;
	private JTextField textfieldStrasseNrNeu;
	private JLabel labelPassword;
	private JTextField textfieldPassword;
	private JTextField textfieldPasswordNeu;
	private JButton buttonAbbrechen;
	private JButton buttonAktualisieren;
	private Vector<String> mitarbeiterSpaltenNamen;
	private Vector<Mitarbeiter> mitarbeiter;
	private JTable table;
	private JPanel listeZeigen;
	private ListSelectionModel listSelectionModel;

	private Object alterWertBenutzername;
	private Object alterWertMail;
	private Object alterWertVorname;
	private Object alterWertNachname;
	private Object alterWertWohnort;
	private Object alterWertStrasseNr;
	private Object alterWertPassword;
	private String neuerWertBenutzername;
	private String neuerWertMail;
	private String neuerWertVorname;
	private String neuerWertNachname;
	private String neuerWertWohnort;
	private String neuerWertStrasseNr;
	private String neuerWertPassword;
	private JButton buttonLoeschen;
	private JLabel labelPLZ;
	private JTextField textfieldPLZ;
	private JTextField textfieldPLZNeu;
	private Object alterWertPLZ;
	public PanelBearbeiteMitarbeiter() {
		super();
		initGUI();
		try {
			showMitarbeiter();
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
			setPreferredSize(new Dimension(800, 700));
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initRest(){
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
			JPanel pBenutzername = new JPanel();
			this.add(pBenutzername);
			BoxLayout p1Layout = new BoxLayout(pBenutzername, javax.swing.BoxLayout.X_AXIS);
			pBenutzername.setLayout(p1Layout);
			{
				labelMitarbeiterID = new JLabel();
				pBenutzername.add(labelMitarbeiterID);
				labelMitarbeiterID.setText("Mitarbeiter ID");
				labelMitarbeiterID.setAlignmentX(CENTER_ALIGNMENT);
				labelMitarbeiterID.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterID.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterID.setMinimumSize(new Dimension(100,20));		
			}
			{
				textfieldMitarbeiterID = new JTextField();
				pBenutzername.add(textfieldMitarbeiterID);
				textfieldMitarbeiterID.setAlignmentX(CENTER_ALIGNMENT);
				textfieldMitarbeiterID.setPreferredSize(new Dimension(130,20));
				textfieldMitarbeiterID.setMaximumSize(new Dimension(130,20));
				textfieldMitarbeiterID.setMinimumSize(new Dimension(130,20));
				textfieldMitarbeiterID.setEnabled(false);
			}
			{
				pBenutzername.add(Box.createRigidArea(new Dimension(50,20)));
			}

	       	{
	       		textfieldMitarbeiterIDNeu = new JTextField();
	       		pBenutzername.add(textfieldMitarbeiterIDNeu);
	       		textfieldMitarbeiterIDNeu.setPreferredSize(new Dimension(130,20));
	       		textfieldMitarbeiterIDNeu.setMaximumSize(new Dimension(130,20));
	       		textfieldMitarbeiterIDNeu.setMinimumSize(new Dimension(130,20));

	       	}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pVorname = new JPanel();
			this.add(pVorname);
			BoxLayout p2Layout = new BoxLayout(pVorname, javax.swing.BoxLayout.X_AXIS);
			pVorname.setLayout(p2Layout);
	       	{
	       		labelVorname = new JLabel();
	       		pVorname.add(labelVorname);
	       		labelVorname.setText("Vorname");
	       		labelVorname.setPreferredSize(new Dimension(100,20));
	       		labelVorname.setMaximumSize(new Dimension(100,20));
	       		labelVorname.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldVorname = new JTextField();
	       		pVorname.add(textfieldVorname);
	       		textfieldVorname.setPreferredSize(new Dimension(130,20));
	       		textfieldVorname.setMaximumSize(new Dimension(130,20));
	       		textfieldVorname.setMinimumSize(new Dimension(130,20));
	       		textfieldVorname.setEnabled(false);

	       	}
			{
				pVorname.add(Box.createRigidArea(new Dimension(50,20)));
			}
	       	{
	       		textfieldVornameNeu = new JTextField();
	       		pVorname.add(textfieldVornameNeu);
	       		textfieldVornameNeu.setPreferredSize(new Dimension(130,20));
	       		textfieldVornameNeu.setMaximumSize(new Dimension(130,20));
	       		textfieldVornameNeu.setMinimumSize(new Dimension(130,20));

	       	}
	    
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pNachname = new JPanel();
			this.add(pNachname);
			BoxLayout p3Layout = new BoxLayout(pNachname, javax.swing.BoxLayout.X_AXIS);
			pNachname.setLayout(p3Layout);
	       	{
	       		labelNachname = new JLabel();
	       		pNachname.add(labelNachname);
	       		labelNachname.setText("Nachname");
	       		labelNachname.setPreferredSize(new Dimension(100,20));
	       		labelNachname.setMaximumSize(new Dimension(100,20));
	       		labelNachname.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldNachname = new JTextField();
	       		pNachname.add(textfieldNachname);
	       		textfieldNachname.setPreferredSize(new Dimension(130,20));
	       		textfieldNachname.setMaximumSize(new Dimension(130,20));
	       		textfieldNachname.setMinimumSize(new Dimension(130,20));
	       		textfieldNachname.setEnabled(false);
	       	}
			{
				pNachname.add(Box.createRigidArea(new Dimension(50,20)));
			}
	       	{
	       		textfieldNachnameNeu = new JTextField();
	       		pNachname.add(textfieldNachnameNeu);
	       		textfieldNachnameNeu.setPreferredSize(new Dimension(130,20));
	       		textfieldNachnameNeu.setMaximumSize(new Dimension(130,20));
	       		textfieldNachnameNeu.setMinimumSize(new Dimension(130,20));
	       	}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pMail = new JPanel();
			this.add(pMail);
			BoxLayout p5Layout = new BoxLayout(pMail, javax.swing.BoxLayout.X_AXIS);
			pMail.setLayout(p5Layout);
	       	{
	       		labelMail = new JLabel("E-Mail");
	       		pMail.add(labelMail);
	       		labelMail.setPreferredSize(new Dimension(100,20));
	       		labelMail.setMaximumSize(new Dimension(100,20));
	       		labelMail.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldMail = new JTextField();
	       		pMail.add(textfieldMail);
	       		textfieldMail.setPreferredSize(new Dimension(130,20));
	       		textfieldMail.setMaximumSize(new Dimension(130,20));
	       		textfieldMail.setMinimumSize(new Dimension(130,20));
	       		textfieldMail.setEnabled(false);
	       	}
			{
				pMail.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				textfieldMailNeu = new JTextField();
				pMail.add(textfieldMailNeu);
				textfieldMailNeu.setPreferredSize(new Dimension(130,20));
				textfieldMailNeu.setMaximumSize(new Dimension(130,20));
				textfieldMailNeu.setMinimumSize(new Dimension(130,20));
			}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pWohnort = new JPanel();
			this.add(pWohnort);
			BoxLayout p5Layout = new BoxLayout(pWohnort, javax.swing.BoxLayout.X_AXIS);
			pWohnort.setLayout(p5Layout);
	       	{
	       		labelWohnort = new JLabel("Wohnort");
	       		pWohnort.add(labelWohnort);
	       		labelWohnort.setPreferredSize(new Dimension(100,20));
	       		labelWohnort.setMaximumSize(new Dimension(100,20));
	       		labelWohnort.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldWohnort = new JTextField();
	       		pWohnort.add(textfieldWohnort);
	       		textfieldWohnort.setPreferredSize(new Dimension(130,20));
	       		textfieldWohnort.setMaximumSize(new Dimension(130,20));
	       		textfieldWohnort.setMinimumSize(new Dimension(130,20));
	       		textfieldWohnort.setEnabled(false);
	       	}
			{
				pWohnort.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				textfieldWohnortNeu = new JTextField();
				pWohnort.add(textfieldWohnortNeu);
				textfieldWohnortNeu.setPreferredSize(new Dimension(130,20));
	       		textfieldWohnortNeu.setMaximumSize(new Dimension(130,20));
	       		textfieldWohnortNeu.setMinimumSize(new Dimension(130,20));
			}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pPostleitzahl = new JPanel();
			this.add(pPostleitzahl);
			BoxLayout pPLZLayout = new BoxLayout(pPostleitzahl, javax.swing.BoxLayout.X_AXIS);
			pPostleitzahl.setLayout(pPLZLayout);
	       	{
	       		labelPLZ = new JLabel("Plz");
	       		pPostleitzahl.add(labelPLZ);
	       		labelPLZ.setPreferredSize(new Dimension(100,20));
	       		labelPLZ.setMaximumSize(new Dimension(100,20));
	       		labelPLZ.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldPLZ = new JTextField();
	       		pPostleitzahl.add(textfieldPLZ);
	       		textfieldPLZ.setPreferredSize(new Dimension(130,20));
	       		textfieldPLZ.setMaximumSize(new Dimension(130,20));
	       		textfieldPLZ.setMinimumSize(new Dimension(130,20));
	       		textfieldPLZ.setEnabled(false);
	       	}
			{
				pPostleitzahl.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				textfieldPLZNeu = new JTextField();
				pPostleitzahl.add(textfieldPLZNeu);
				textfieldPLZNeu.setPreferredSize(new Dimension(45,20));
				textfieldPLZNeu.setMaximumSize(new Dimension(45,20));
				textfieldPLZNeu.setMinimumSize(new Dimension(45,20));
				textfieldPLZNeu.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
					    char c = e.getKeyChar();
					    if (!((Character.isDigit(c) ||
					    		(c == KeyEvent.VK_BACK_SPACE) ||
					    		(c == KeyEvent.VK_DELETE)))) {
					        	getToolkit().beep();
					        	e.consume();
					    }
					    if (textfieldPLZNeu.getText().length() >= 5) {
					    	c = KeyEvent.VK_BACK_SPACE;
					    	getToolkit().beep();
					    	e.consume();
					    }
					}
				});
				{
					pPostleitzahl.add(Box.createRigidArea(new Dimension(85,20)));
				}
			}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pStrasseNr = new JPanel();
			this.add(pStrasseNr);
			BoxLayout p5Layout = new BoxLayout(pStrasseNr, javax.swing.BoxLayout.X_AXIS);
			pStrasseNr.setLayout(p5Layout);
	       	{
	       		labelStrasseNr = new JLabel("Str./Nr.");
	       		pStrasseNr.add(labelStrasseNr);
	       		labelStrasseNr.setPreferredSize(new Dimension(100,20));
	       		labelStrasseNr.setMaximumSize(new Dimension(100,20));
	       		labelStrasseNr.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldStrasseNr = new JTextField();
	       		pStrasseNr.add(textfieldStrasseNr);
	       		textfieldStrasseNr.setPreferredSize(new Dimension(130,20));
	       		textfieldStrasseNr.setMaximumSize(new Dimension(130,20));
	       		textfieldStrasseNr.setMinimumSize(new Dimension(130,20));
	       		textfieldStrasseNr.setEnabled(false);
	       	}
			{
				pStrasseNr.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				textfieldStrasseNrNeu = new JTextField();
				pStrasseNr.add(textfieldStrasseNrNeu);
				textfieldStrasseNrNeu.setPreferredSize(new Dimension(130,20));
				textfieldStrasseNrNeu.setMaximumSize(new Dimension(130,20));
				textfieldStrasseNrNeu.setMinimumSize(new Dimension(130,20));
			}
	    }
		{
			this.add(Box.createRigidArea(new Dimension(500,8)));
		}
	    {
			JPanel pPasswort = new JPanel();
			this.add(pPasswort);
			BoxLayout p5Layout = new BoxLayout(pPasswort, javax.swing.BoxLayout.X_AXIS);
			pPasswort.setLayout(p5Layout);
	       	{
	       		labelPassword = new JLabel("Password");
	       		pPasswort.add(labelPassword);
	       		labelPassword.setPreferredSize(new Dimension(100,20));
	       		labelPassword.setMaximumSize(new Dimension(100,20));
	       		labelPassword.setMinimumSize(new Dimension(100,20));
	       	}
	       	{
	       		textfieldPassword = new JTextField();
	       		pPasswort.add(textfieldPassword);
	       		textfieldPassword.setPreferredSize(new Dimension(130,20));
	       		textfieldPassword.setMaximumSize(new Dimension(130,20));
	       		textfieldPassword.setMinimumSize(new Dimension(130,20));
	       		textfieldPassword.setEnabled(false);
	       	}
			{
				pPasswort.add(Box.createRigidArea(new Dimension(50,20)));
			}
			{
				textfieldPasswordNeu = new JTextField();
				pPasswort.add(textfieldPasswordNeu);
				textfieldPasswordNeu.setPreferredSize(new Dimension(130,20));
				textfieldPasswordNeu.setMaximumSize(new Dimension(130,20));
				textfieldPasswordNeu.setMinimumSize(new Dimension(130,20));
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
	       		buttonAbbrechen = new JButton(new ImageIcon("iconset/b_icons/delete.png"));
	       		p4.add(buttonAbbrechen);
	       		buttonAbbrechen.setText("Abbrechen");
	       		buttonAbbrechen.setPreferredSize(new Dimension(125,30));
	       		buttonAbbrechen.setMaximumSize(new Dimension(125,30));
	       		buttonAbbrechen.setMinimumSize(new Dimension(125,30));
	       		buttonAbbrechen.addActionListener(new ActionListener() {
	       			public void actionPerformed(ActionEvent e) {
						textfieldMitarbeiterIDNeu.setText("");
						textfieldMailNeu.setText("");
						textfieldVornameNeu.setText("");
						textfieldNachnameNeu.setText("");
						textfieldWohnortNeu.setText("");
						textfieldPLZNeu.setText("");
						textfieldStrasseNrNeu.setText("");
						textfieldPasswordNeu.setText("");
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
					private Component popup;
					private MitarbeiterTableModel mitarbeiterModel;

					public void actionPerformed(ActionEvent e) {
						if(	textfieldMitarbeiterIDNeu.getText().equals("")  ||	
								textfieldMailNeu.getText().equals("") ||
								textfieldVornameNeu.getText().equals("") ||
								textfieldNachnameNeu.getText().equals("") ||
								textfieldPasswordNeu.getText().equals(""))  {	
	    		
							JOptionPane errorMessage = new JOptionPane();
							errorMessage.setSize(400,300);
							JOptionPane.showMessageDialog(popup, "Bitte alle Felder ausfuellen!","", JOptionPane.ERROR_MESSAGE);
						}
						else{
							int choice = JOptionPane.showConfirmDialog (null, "Wollen Sie wirklich diesen Mitarbeiter entfernen?","Achtung", JOptionPane.YES_NO_OPTION);
							if(choice==0){
								//Action Event um Mitarbeiter zu loeschen:
								String benutzernameAlt = textfieldMitarbeiterID.getText();        	   
								String emailAlt = textfieldMail.getText();           	   
								String vornameAlt = textfieldVorname.getText();         	   
								String nachnameAlt = textfieldNachname.getText();  
								String wohnortAlt = textfieldWohnort.getText();  
								String plzAlt = textfieldPLZ.getText();
								String strasseUndNrAlt = textfieldStrasseNr.getText();  
								String passwordAlt = textfieldPassword.getText(); 
								int plzalt = Integer.parseInt(plzAlt);
								
								Mitarbeiter m = new Mitarbeiter(benutzernameAlt,passwordAlt,emailAlt,vornameAlt, nachnameAlt,wohnortAlt,plzalt,strasseUndNrAlt);

								try {
									m = MitarbeiterClient.sf.sucheMitarbeiter(benutzernameAlt);
								} catch (IOException e2) {
									e2.printStackTrace();
								}
								if(m != null){
									try {
										boolean removeOk = MitarbeiterClient.sf.entferneMitarbeiter(m);
										if(removeOk == true){
											
											JOptionPane errorMessage = new JOptionPane();
											errorMessage.setSize(400,300);
											JOptionPane.showMessageDialog(popup, "Der Mitarbeiter "+benutzernameAlt +" wurde erfolgreich entfernt!","", JOptionPane.INFORMATION_MESSAGE);

											//Felder wieder leeren
											textfieldMitarbeiterIDNeu.setText("");	
											textfieldMailNeu.setText("");
											textfieldVornameNeu.setText("");
											textfieldNachnameNeu.setText("");
											textfieldWohnortNeu.setText("");
											textfieldMailNeu.setText("");										
											textfieldStrasseNrNeu.setText("");
											textfieldPLZNeu.setText("");
											textfieldPasswordNeu.setText("");
										}
										else{
											JOptionPane errorMessage = new JOptionPane();
											errorMessage.setSize(400,300);
											JOptionPane.showMessageDialog(popup, "Das Produkt mit der ID "+benutzernameAlt +" wurde nicht gefunden!","", JOptionPane.ERROR_MESSAGE);							
										}
										
									} catch (IOException e2) {
										e2.printStackTrace();
									}
								}

								//Tabelle aktualisieren:
								try {
									mitarbeiter = MitarbeiterClient.sf.gibAlleMitarbeiter();
								} catch (IOException e1) {									e1.printStackTrace();
								}
								try {
									mitarbeiterModel = new MitarbeiterTableModel(mitarbeiter, mitarbeiterSpaltenNamen);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								table.setModel(mitarbeiterModel);
							}
						}
					}
	       		});  		
	       	}
			{
				p4.add(Box.createRigidArea(new Dimension(20,20)));
			}
	       	{
	       		buttonAktualisieren = new JButton(new ImageIcon("iconset/b_icons/refresh.png"));
	       		p4.add(buttonAktualisieren);
	       		buttonAktualisieren.setText("Aktualisieren");
	       		buttonAktualisieren.setPreferredSize(new Dimension(125,30));
	       		buttonAktualisieren.setMaximumSize(new Dimension(125,30));
	       		buttonAktualisieren.setMinimumSize(new Dimension(125,30));
	       		buttonAktualisieren.addActionListener(new ActionListener() {
					private Component popup;
					private Component frame;
					private TableModel kundenModel;

					public void actionPerformed(ActionEvent e) {
						//alte Werte
						String benutzernameAlt = textfieldMitarbeiterID.getText();        	   
						String emailAlt = textfieldMail.getText();           	   
						String vornameAlt = textfieldVorname.getText();         	   
						String nachnameAlt = textfieldNachname.getText();  
						String wohnortAlt = textfieldWohnort.getText(); 
						String plzAlt = textfieldPLZ.getText();
						String strasseUndNrAlt = textfieldStrasseNr.getText();  
						String passwortAlt = textfieldPassword.getText(); 						
						//neue Werte
						String benutzername = textfieldMitarbeiterIDNeu.getText();        	   
						String email = textfieldMailNeu.getText();           	   
						String vorname = textfieldVornameNeu.getText();         	   
						String nachname = textfieldNachnameNeu.getText();  
						String wohnort = textfieldWohnortNeu.getText();  
						String plz = textfieldPLZNeu.getText();  
						String strasseUndNr = textfieldStrasseNrNeu.getText();  
						String passwort = textfieldPasswordNeu.getText();  

						if(	textfieldMitarbeiterID.getText().equals(""))  {	
	    		
							JOptionPane errorMessage = new JOptionPane();
							errorMessage.setSize(400,300);
							JOptionPane.showMessageDialog(popup, "Bitte einen Mitarbeiter auswählen!","", JOptionPane.ERROR_MESSAGE);
						}
						
						else if(	textfieldMitarbeiterIDNeu.getText().equals("")  ||	
								textfieldMailNeu.getText().equals("") ||
								textfieldVornameNeu.getText().equals("") ||
								textfieldNachnameNeu.getText().equals("") ||
								textfieldWohnortNeu.getText().equals("") ||
								textfieldPLZNeu.getText().equals("") ||
								textfieldStrasseNrNeu.getText().equals("") ||
								textfieldPasswordNeu.getText().equals(""))  {	
	    		
							JOptionPane errorMessage = new JOptionPane();
							errorMessage.setSize(400,300);
							JOptionPane.showMessageDialog(popup, "Bitte alle Felder ausfuellen!","", JOptionPane.ERROR_MESSAGE);
						}
						else{
			        		if(validateEmail(email)==false){
					        	JOptionPane errorMessage = new JOptionPane();
					        	errorMessage.setSize(400,300);
					        	JOptionPane.showMessageDialog(frame, 	"Die E-Mail-Adresse ist nicht gültig!\n" +
					        											"Bitte geben Sie eine gültige Adresse ein!","Fehler", JOptionPane.ERROR_MESSAGE);					        		
				        	}
			        		else{
			        			if (textfieldPLZNeu.getText().length() != 5){
						        	JOptionPane errorMessage = new JOptionPane();
						        	errorMessage.setSize(400,300);
						        	JOptionPane.showMessageDialog(frame, 	"Die Postleitzahl muss aus mindestens 5 Ziffern bestehen!","Fehler", JOptionPane.ERROR_MESSAGE);					        									        	
			        			}
			        			else{
									int PLZ = Integer.parseInt(plz);
									int PLZalt = Integer.parseInt(plzAlt);
										Mitarbeiter m = new Mitarbeiter(benutzernameAlt,passwortAlt,emailAlt,nachnameAlt,vornameAlt,wohnortAlt,PLZalt,strasseUndNrAlt);
										
										MitarbeiterClient.sf.aktualisiereMitarbeiterDaten(m, benutzername,passwort, vorname, nachname, email, wohnort,PLZ, strasseUndNr, benutzernameAlt,passwortAlt,emailAlt,nachnameAlt,vornameAlt,wohnortAlt,PLZalt,strasseUndNrAlt);								
							
		
										JOptionPane errorMessage = new JOptionPane();
										errorMessage.setSize(400,300);
										JOptionPane.showMessageDialog(popup, "Der Mitarbeiter wurde erfolgreich aktualisiert!","Erfolg!", JOptionPane.INFORMATION_MESSAGE);
											//Felder wieder leeren
											textfieldMitarbeiterIDNeu.setText("");	
											textfieldMailNeu.setText("");
											textfieldVornameNeu.setText("");
											textfieldNachnameNeu.setText("");
											textfieldWohnortNeu.setText("");
											textfieldMailNeu.setText("");										
											textfieldStrasseNrNeu.setText("");
											textfieldPLZNeu.setText("");
											textfieldPasswordNeu.setText("");
											//Liste aktualisieren
											try {
												mitarbeiter = MitarbeiterClient.sf.gibAlleMitarbeiter();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											try {
												kundenModel = new MitarbeiterTableModel(mitarbeiter, mitarbeiterSpaltenNamen);
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											table.setModel(kundenModel);
			        			}
				        	}
						}
					}
				});	     	    
	       	}
			{
				this.add(Box.createRigidArea(new Dimension(300,20)));
			}
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
		mitarbeiterSpaltenNamen.add("Password");
		mitarbeiter = MitarbeiterClient.sf.gibAlleMitarbeiter();

		table = new JTable(new MitarbeiterTableModel(mitarbeiter, mitarbeiterSpaltenNamen));
		table.setSelectionBackground(new java.awt.Color(124,156,173));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed( false ); 
		table.setAutoCreateRowSorter(true);
		
		//Tabelle erstellen:
		TableModel kundenModel = new MitarbeiterTableModel(mitarbeiter, mitarbeiterSpaltenNamen);
		table.setModel(kundenModel);
		JScrollPane tablePane = new JScrollPane(table);
	
		listeZeigen = new JPanel();
		listeZeigen.add(tablePane);
		tablePane.setPreferredSize(new Dimension(650,350));
		tablePane.setMaximumSize(new Dimension(1000,800));
		tablePane.setMinimumSize(new Dimension(650,350));
       	this.add(listeZeigen, "Mitarbeiterliste");
       	listeZeigen.setPreferredSize(new java.awt.Dimension(650, 350));
       	listeZeigen.setMaximumSize(new Dimension(650,350));
       	listeZeigen.setMinimumSize(new Dimension(650,350));
       	
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
	                    
	                	alterWertBenutzername = table.getValueAt(i,0);
	                	alterWertVorname = 		table.getValueAt(i,1);
	                	alterWertNachname = 	table.getValueAt(i,2);
	                	alterWertMail = 		table.getValueAt(i,3);
	                	alterWertWohnort = 		table.getValueAt(i,4);
	                	alterWertPLZ = 			table.getValueAt(i,5);
	                	alterWertStrasseNr = 	table.getValueAt(i,6);
	                	alterWertPassword = 	table.getValueAt(i,7);
	                	
	             	  
	             	   neuerWertBenutzername = (String) alterWertBenutzername;
	             	   neuerWertMail = (String) alterWertMail;
	             	   neuerWertVorname = (String) alterWertVorname;
	             	   neuerWertNachname = (String) alterWertNachname;
	             	   neuerWertWohnort = (String) alterWertWohnort;
	             	   alterWertPLZ = String.valueOf(alterWertPLZ);
	             	   neuerWertStrasseNr = (String) alterWertStrasseNr;
	             	   neuerWertPassword = (String) alterWertPassword;
	             	      	   
	             	   // linke Textfelder mit altem Wert füllen:
	             	   textfieldMitarbeiterID.setText((String) alterWertBenutzername);          	   
	             	   textfieldMail.setText((String) alterWertMail);          	   
	             	   textfieldVorname.setText((String) alterWertVorname);          	   
	             	   textfieldNachname.setText((String) alterWertNachname);
	             	   textfieldWohnort.setText((String) alterWertWohnort);
	             	   textfieldPLZ.setText(String.valueOf(alterWertPLZ));
	             	   textfieldStrasseNr.setText((String) alterWertStrasseNr);
	             	   textfieldPassword.setText((String) alterWertPassword);   
	             	   
	             	   // rechte Textfelder mit editierbaren Werten füllen:
	             	   textfieldMitarbeiterIDNeu	.setText(neuerWertBenutzername);          	   
	             	   textfieldMailNeu.setText(neuerWertMail);          	   
	             	   textfieldVornameNeu.setText(neuerWertVorname);          	   
	             	   textfieldNachnameNeu.setText(neuerWertNachname);
	             	   textfieldWohnortNeu.setText(neuerWertWohnort);
	             	   textfieldPLZNeu.setText(String.valueOf(alterWertPLZ));
	             	   textfieldStrasseNrNeu.setText(neuerWertStrasseNr);
	             	   textfieldPasswordNeu.setText(neuerWertPassword);   
	                }
	            }
	        }
		}	
	
	
	public boolean validateEmail(String eMail) {
		 Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		 Matcher m = p.matcher(eMail);
		 if(m.matches()) return true;
		        else return false;
	}
}
