package shop.local.ui.gui.mitarbeiterClientPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

public class PanelMitarbeiterErstellen extends javax.swing.JPanel {
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel labelNeuerMitarbeiter;
	private JLabel labelMitarbeiterID;
	private JButton buttonSubmit;
	private JButton buttonCancel;
	private JTextField textfieldMitarbeiterStrasseNr;
	private JLabel labelMitarbeiterStrasseNummer;
	private JTextField textfieldMitarbeiterWohnort;
	private JLabel labelMitarbeiterWohnort;
	private JPasswordField passwordfieldMitarbeiterPasswordNochMal;
	private JLabel labelMitarbeiterPasswordNochMal;
	private JPasswordField passwordfieldMitarbeiterPassword;
	private JLabel labelMitarbeiterPassword;
	private JTextField textfieldMitarbeiterMail;
	private JLabel labelMitarbeiterMail;
	private JTextField textfieldMitarbeiterNachname;
	private JLabel labelMitarbeiterNachname;
	private JTextField textfieldMitarbeiterVorname;
	private JLabel labelMitarbeiterVorname;
	private JTextField textfieldMitarbeiterID;
	private JLabel pflicht1;
	private JLabel pflicht2;
	private JLabel pflicht3;
	private JLabel pflicht4;
	private JLabel pflicht5;
	private JLabel pflicht6;
	private JLabel platzhalter1;
	private JLabel platzhalter2;
	private JLabel labelMitarbeiterPLZ;
	private JLabel platzhalter3;
	private JTextField textfieldMitarbeiterPLZ;
	private JLabel pflichtFeld;


	
	
	public PanelMitarbeiterErstellen() {
		super();
		initGUI();
		init();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 500));
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private void init(){
		{
			pflicht1 = new JLabel("*");
			pflicht1.setForeground(new java.awt.Color(255,0,0));
			pflicht2 = new JLabel("*");
			pflicht2.setForeground(new java.awt.Color(255,0,0));
			pflicht3 = new JLabel("*");
			pflicht3.setForeground(new java.awt.Color(255,0,0));
			pflicht4 = new JLabel("*");
			pflicht4.setForeground(new java.awt.Color(255,0,0));
			pflicht5 = new JLabel("*");
			pflicht5.setForeground(new java.awt.Color(255,0,0));			
			pflicht6 = new JLabel("*");
			pflicht6.setForeground(new java.awt.Color(255,0,0));
			
			platzhalter1 = new JLabel("  ");
			platzhalter2 = new JLabel("  ");
			platzhalter3 = new JLabel("  ");
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,30)));
		}
		{
			labelNeuerMitarbeiter = new JLabel();
			this.add(labelNeuerMitarbeiter);
			labelNeuerMitarbeiter.setText("Neuer Mitarbeiter");
			labelNeuerMitarbeiter.setAlignmentX(CENTER_ALIGNMENT);
			labelNeuerMitarbeiter.setPreferredSize(new Dimension(200,20));
			labelNeuerMitarbeiter.setMaximumSize(new Dimension(200,20));
			labelNeuerMitarbeiter.setMinimumSize(new Dimension(200,20));
			labelNeuerMitarbeiter.setFont(new java.awt.Font("SansSerif",1,20));
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p1 = new JPanel();
			this.add(p1);
			BoxLayout p1Layout = new BoxLayout(p1, javax.swing.BoxLayout.X_AXIS);
			p1.setLayout(p1Layout);
			this.add(p1);
			{
				labelMitarbeiterID = new JLabel();
				p1.add(labelMitarbeiterID);
				labelMitarbeiterID.setText("Mitarbeiter ID");
				labelMitarbeiterID.setAlignmentX(CENTER_ALIGNMENT);
				labelMitarbeiterID.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterID.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterID.setMinimumSize(new Dimension(100,20));
				p1.add(pflicht1);
			}
			{
				textfieldMitarbeiterID = new JTextField();
				p1.add(textfieldMitarbeiterID);
				textfieldMitarbeiterID.setAlignmentX(CENTER_ALIGNMENT);
				textfieldMitarbeiterID.setPreferredSize(new Dimension(180,20));
				textfieldMitarbeiterID.setMaximumSize(new Dimension(180,20));
				textfieldMitarbeiterID.setMinimumSize(new Dimension(180,20));
			}
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p2 = new JPanel();
			this.add(p2);
			BoxLayout p1Layout = new BoxLayout(p2, javax.swing.BoxLayout.X_AXIS);
			p2.setLayout(p1Layout);
			this.add(p2);
			{
				labelMitarbeiterVorname = new JLabel();
				p2.add(labelMitarbeiterVorname);
				labelMitarbeiterVorname.setText("Vorname");
				labelMitarbeiterVorname.setAlignmentX(CENTER_ALIGNMENT);
				labelMitarbeiterVorname.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterVorname.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterVorname.setMinimumSize(new Dimension(100,20));
				p2.add(pflicht2);
			}
			{
				textfieldMitarbeiterVorname = new JTextField();
				p2.add(textfieldMitarbeiterVorname);
				textfieldMitarbeiterVorname.setText("");
				textfieldMitarbeiterVorname.setAlignmentX(CENTER_ALIGNMENT);
				textfieldMitarbeiterVorname.setPreferredSize(new Dimension(180,20));
				textfieldMitarbeiterVorname.setMaximumSize(new Dimension(180,20));
				textfieldMitarbeiterVorname.setMinimumSize(new Dimension(180,20));
			}
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p3 = new JPanel();
			this.add(p3);
			BoxLayout p3Layout = new BoxLayout(p3, javax.swing.BoxLayout.X_AXIS);
			p3.setLayout(p3Layout);
			this.add(p3);
			{
				labelMitarbeiterNachname = new JLabel();
				p3.add(labelMitarbeiterNachname);
				labelMitarbeiterNachname.setText("Nachname");
				labelMitarbeiterNachname.setAlignmentX(CENTER_ALIGNMENT);
				labelMitarbeiterNachname.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterNachname.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterNachname.setMinimumSize(new Dimension(100,20));
				p3.add(pflicht3);
			}
			{
				textfieldMitarbeiterNachname = new JTextField();
				p3.add(textfieldMitarbeiterNachname);
				textfieldMitarbeiterNachname.setText("");
				textfieldMitarbeiterNachname.setAlignmentX(CENTER_ALIGNMENT);
				textfieldMitarbeiterNachname.setPreferredSize(new Dimension(180,20));
				textfieldMitarbeiterNachname.setMaximumSize(new Dimension(180,20));
				textfieldMitarbeiterNachname.setMinimumSize(new Dimension(180,20));
			}
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p4 = new JPanel();
			this.add(p4);
			BoxLayout p4Layout = new BoxLayout(p4, javax.swing.BoxLayout.X_AXIS);
			p4.setLayout(p4Layout);
			this.add(p4);
			{
				labelMitarbeiterMail = new JLabel();
				p4.add(labelMitarbeiterMail);
				labelMitarbeiterMail.setText("E-Mail");
				labelMitarbeiterMail.setAlignmentX(CENTER_ALIGNMENT);
				labelMitarbeiterMail.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterMail.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterMail.setMinimumSize(new Dimension(100,20));
				p4.add(pflicht4);
			}
			{
				textfieldMitarbeiterMail = new JTextField();
				p4.add(textfieldMitarbeiterMail);
				textfieldMitarbeiterMail.setAlignmentX(CENTER_ALIGNMENT);
				textfieldMitarbeiterMail.setPreferredSize(new Dimension(180,20));
				textfieldMitarbeiterMail.setMaximumSize(new Dimension(180,20));
				textfieldMitarbeiterMail.setMinimumSize(new Dimension(180,20));
			}
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p5 = new JPanel();
			this.add(p5);
			BoxLayout p5Layout = new BoxLayout(p5, javax.swing.BoxLayout.X_AXIS);
			p5.setLayout(p5Layout);
			this.add(p5);
			{
				labelMitarbeiterPassword = new JLabel();
				p5.add(labelMitarbeiterPassword);
				labelMitarbeiterPassword.setText("Password");
				labelMitarbeiterPassword.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterPassword.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterPassword.setMinimumSize(new Dimension(100,20));
				p5.add(pflicht5);
			}
			{
				passwordfieldMitarbeiterPassword = new JPasswordField();
				p5.add(passwordfieldMitarbeiterPassword);
				passwordfieldMitarbeiterPassword.setPreferredSize(new Dimension(180,20));
				passwordfieldMitarbeiterPassword.setMaximumSize(new Dimension(180,20));
				passwordfieldMitarbeiterPassword.setMinimumSize(new Dimension(180,20));
			}
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p6 = new JPanel();
			this.add(p6);
			BoxLayout p6Layout = new BoxLayout(p6, javax.swing.BoxLayout.X_AXIS);
			p6.setLayout(p6Layout);
			this.add(p6);
			{
				labelMitarbeiterPasswordNochMal = new JLabel();
				p6.add(labelMitarbeiterPasswordNochMal);
				labelMitarbeiterPasswordNochMal.setText("Password (Repeat)");
				labelMitarbeiterPasswordNochMal.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterPasswordNochMal.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterPasswordNochMal.setMinimumSize(new Dimension(100,20));
				p6.add(pflicht6);
			}
			{
				passwordfieldMitarbeiterPasswordNochMal = new JPasswordField();
				p6.add(passwordfieldMitarbeiterPasswordNochMal);
				passwordfieldMitarbeiterPasswordNochMal.setText("");
				passwordfieldMitarbeiterPasswordNochMal.setPreferredSize(new Dimension(180,20));
				passwordfieldMitarbeiterPasswordNochMal.setMaximumSize(new Dimension(180,20));
				passwordfieldMitarbeiterPasswordNochMal.setMinimumSize(new Dimension(180,20));
			}
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p7 = new JPanel();
			this.add(p7);
			BoxLayout p7Layout = new BoxLayout(p7, javax.swing.BoxLayout.X_AXIS);
			p7.setLayout(p7Layout);
			this.add(p7);
			{
				labelMitarbeiterWohnort = new JLabel();
				p7.add(labelMitarbeiterWohnort);
				labelMitarbeiterWohnort.setText("Wohnort");
				labelMitarbeiterWohnort.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterWohnort.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterWohnort.setMinimumSize(new Dimension(100,20));
				p7.add(platzhalter1);

			}
			{
				textfieldMitarbeiterWohnort = new JTextField();
				p7.add(textfieldMitarbeiterWohnort);
				textfieldMitarbeiterWohnort.setPreferredSize(new Dimension(180,20));
				textfieldMitarbeiterWohnort.setMaximumSize(new Dimension(180,20));
				textfieldMitarbeiterWohnort.setMinimumSize(new Dimension(180,20));
			}
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p7 = new JPanel();
			this.add(p7);
			BoxLayout p7Layout = new BoxLayout(p7, javax.swing.BoxLayout.X_AXIS);
			p7.setLayout(p7Layout);
			this.add(p7);
			{
				labelMitarbeiterPLZ = new JLabel();
				p7.add(labelMitarbeiterPLZ);
				labelMitarbeiterPLZ.setText("Plz");
				labelMitarbeiterPLZ.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterPLZ.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterPLZ.setMinimumSize(new Dimension(100,20));
				p7.add(platzhalter3);

			}
			{
				textfieldMitarbeiterPLZ = new JTextField();
				p7.add(textfieldMitarbeiterPLZ);
				textfieldMitarbeiterPLZ.setPreferredSize(new Dimension(45,20));
				textfieldMitarbeiterPLZ.setMaximumSize(new Dimension(45,20));
				textfieldMitarbeiterPLZ.setMinimumSize(new Dimension(45,20));
				textfieldMitarbeiterPLZ.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
					    char c = e.getKeyChar();
					    if (!((Character.isDigit(c) ||
					    		(c == KeyEvent.VK_BACK_SPACE) ||
					    		(c == KeyEvent.VK_DELETE)))) {
					        	getToolkit().beep();
					        	e.consume();
					    }
					    if (textfieldMitarbeiterPLZ.getText().length() >= 5) {
					    	c = KeyEvent.VK_BACK_SPACE;
					    	getToolkit().beep();
					    	e.consume();
					    }
					}
				});
			}
			{
				p7.add(Box.createRigidArea(new Dimension(135,20)));
			}
			
		}
		{
			this.add(Box.createRigidArea(new Dimension(100,15)));
		}
		{
			JPanel p8 = new JPanel();
			this.add(p8);
			BoxLayout p8Layout = new BoxLayout(p8, javax.swing.BoxLayout.X_AXIS);
			p8.setLayout(p8Layout);
			this.add(p8);
			{
				labelMitarbeiterStrasseNummer = new JLabel();
				p8.add(labelMitarbeiterStrasseNummer);
				labelMitarbeiterStrasseNummer.setText("Str./Nr.");
				labelMitarbeiterStrasseNummer.setPreferredSize(new Dimension(100,20));
				labelMitarbeiterStrasseNummer.setMaximumSize(new Dimension(100,20));
				labelMitarbeiterStrasseNummer.setMinimumSize(new Dimension(100,20));
				p8.add(platzhalter2);

			}
			{
				textfieldMitarbeiterStrasseNr = new JTextField();
				p8.add(textfieldMitarbeiterStrasseNr);
				textfieldMitarbeiterStrasseNr.setText("");
				textfieldMitarbeiterStrasseNr.setPreferredSize(new Dimension(180,20));
				textfieldMitarbeiterStrasseNr.setMaximumSize(new Dimension(180,20));
				textfieldMitarbeiterStrasseNr.setMinimumSize(new Dimension(180,20));
			}
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			{
				JPanel p9 = new JPanel();
				this.add(p9);
				BoxLayout p9Layout = new BoxLayout(p9, javax.swing.BoxLayout.X_AXIS);
				p9.setLayout(p9Layout);
				this.add(p9);
				{
					buttonCancel = new JButton(new ImageIcon("iconset/b_icons/delete.png"));
					p9.add(buttonCancel);
					buttonCancel.setText("Abbrechen");
					buttonCancel.setPreferredSize(new Dimension(110,30));
					buttonCancel.setMaximumSize(new Dimension(110,30));
					buttonCancel.setMinimumSize(new Dimension(110,30));
					buttonCancel.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
					        	textfieldMitarbeiterID.setText("");
					        	textfieldMitarbeiterVorname.setText("");
					        	textfieldMitarbeiterNachname.setText("");
					        	textfieldMitarbeiterMail.setText("");
					        	passwordfieldMitarbeiterPassword.setText("");
					        	passwordfieldMitarbeiterPasswordNochMal.setText("");
					        	textfieldMitarbeiterWohnort.setText("");
					        	textfieldMitarbeiterPLZ.setText("");
					        	textfieldMitarbeiterStrasseNr.setText("");
				         }
				    });
				}
				{
					p9.add(Box.createRigidArea(new Dimension(20,0)));
				}
				{
					buttonSubmit = new JButton(new ImageIcon("iconset/b_icons/add.png"));
					p9.add(buttonSubmit);
					buttonSubmit.setText("Einfügen");
					buttonSubmit.setPreferredSize(new Dimension(110,30));
					buttonSubmit.setMaximumSize(new Dimension(110,30));
					buttonSubmit.setMinimumSize(new Dimension(110,30));
					buttonSubmit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				 			
				        	String mitarbeiterID = textfieldMitarbeiterID.getText();
							String mitarbeiterVorname = textfieldMitarbeiterVorname.getText();
							String mitarbeiterNachname = textfieldMitarbeiterNachname.getText();
							String mitarbeiterMail = textfieldMitarbeiterMail.getText();
							char[] mitarbeiterPasswort = passwordfieldMitarbeiterPassword.getPassword();
							char[] mitarbeiterPasswortWdhg = passwordfieldMitarbeiterPasswordNochMal.getPassword();
							String mitarbeiterWohnort = textfieldMitarbeiterWohnort.getText();
							String mitarbeiterPLZ = textfieldMitarbeiterPLZ.getText();
							String mitarbeiterStrasseNr = textfieldMitarbeiterStrasseNr.getText();
							
							String mitPass = new String(mitarbeiterPasswort);
							String mitPassWdhg = new String(mitarbeiterPasswortWdhg);							
							
							//Wenn nicht alle Felder ausgefüllt: Error-Message
				        	Component frame = null;
							if(		textfieldMitarbeiterID.getText().equals("")  ||	
				        			textfieldMitarbeiterVorname.getText().equals("") ||
				        			textfieldMitarbeiterNachname.getText().equals("") ||
        							textfieldMitarbeiterMail.getText().equals("") ||
        							passwordfieldMitarbeiterPassword.getPassword().equals("") ||
        							passwordfieldMitarbeiterPasswordNochMal.getPassword().equals("")){	
				        		
					        	JOptionPane errorMessage = new JOptionPane();
					        	errorMessage.setSize(400,300);
					        	JOptionPane.showMessageDialog(frame, "Bitte alle Pflichtfelder ausfuellen!","Fehler", JOptionPane.ERROR_MESSAGE);					        		
				        	}

				        	else{	
				        		if(validateEmail(mitarbeiterMail)==false){
						        	JOptionPane errorMessage = new JOptionPane();
						        	errorMessage.setSize(400,300);
						        	JOptionPane.showMessageDialog(frame, 	"Die eingegebene E-Mail-Adresse ist nicht gültig!\n" +
						        											"Bitte geben Sie eine gültige Adresse ein!","Fehler", JOptionPane.ERROR_MESSAGE);					        		
					        	}				        		
				        		else{
				        			if (!(textfieldMitarbeiterPLZ.equals(""))){
					        			if (textfieldMitarbeiterPLZ.getText().length() != 5){
								        	JOptionPane errorMessage = new JOptionPane();
								        	errorMessage.setSize(400,300);
								        	JOptionPane.showMessageDialog(frame, 	"Die Postleitzahl muss aus mindestens 5 Ziffern bestehen!","Fehler", JOptionPane.ERROR_MESSAGE);					        									        	
					        			}
					        			else{
							        		if(mitPass.equals(mitPassWdhg)){
							        			int plz=0;
							        			if(textfieldMitarbeiterWohnort.equals("")){
							        				mitarbeiterWohnort="-";	
							        			}
							        			if(textfieldMitarbeiterStrasseNr.equals("")){
							        				mitarbeiterStrasseNr="-";	
							        			}
							        			if(!(textfieldMitarbeiterPLZ.getText().equals(""))){					        			
								        			plz = Integer.parseInt(mitarbeiterPLZ);
							        			}
							        			
							        			try {
													MitarbeiterClient.sf.fuegeMitarbeiterEin(mitarbeiterID, mitPass, mitarbeiterMail, mitarbeiterVorname, mitarbeiterNachname, mitarbeiterWohnort,plz, mitarbeiterStrasseNr );
												} catch (IOException e2) {
													e2.printStackTrace();
												}
												JOptionPane successMessage = new JOptionPane();
												successMessage.setSize(400,300);
												JOptionPane.showMessageDialog(frame, "Mitarbeiter "+mitarbeiterVorname +" "+mitarbeiterNachname+" wurde erfolgreich eingefuegt!");
												//Felder wieder leeren:
									        	textfieldMitarbeiterID.setText("");
									        	textfieldMitarbeiterVorname.setText("");
									        	textfieldMitarbeiterNachname.setText("");
									        	textfieldMitarbeiterMail.setText("");
									        	passwordfieldMitarbeiterPassword.setText("");
									        	passwordfieldMitarbeiterPasswordNochMal.setText("");
									        	textfieldMitarbeiterWohnort.setText("");
									        	textfieldMitarbeiterPLZ.setText("");
									        	textfieldMitarbeiterStrasseNr.setText("");
							        		}
							        		else{
									        	JOptionPane errorMessage = new JOptionPane();
									        	errorMessage.setSize(400,300);
									        	JOptionPane.showMessageDialog(frame, "Passwoerter stimmen nicht überein!","Fehler", JOptionPane.ERROR_MESSAGE);
							        		}
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
	public boolean validateEmail(String eMail) {
		 Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		 Matcher m = p.matcher(eMail);
		 if(m.matches()) return true;
		        else return false;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PanelMitarbeiterErstellen());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

