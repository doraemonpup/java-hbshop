package shop.local.ui.gui.kundenClientPanels;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;

import shop.local.valueobjects.*;


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

public class RechnungAnzeigenFrame extends javax.swing.JFrame {
	
	private JLabel RechnungLabel;
	private JTextPane KundenNameTextPane1;
	private JTextPane SummeTextPane;
	private JTextPane ProduktTextPane;
	private JTextPane ZeitTextPane;
	private Rechnung rechnung;
	private String[] list;

	
	public RechnungAnzeigenFrame(Rechnung r) {
		super();
		rechnung = r;
	    list = new String[rechnung.getWarenkorb().size()];
		Iterator iter = rechnung.getWarenkorb().iterator();
		int i = 0;
		
		while (iter.hasNext()){
			GewaehltesProdukt gp = (GewaehltesProdukt) iter.next();
			list[i] = String.valueOf(gp.getID())+" "+gp.getProduktName()+" "+String.valueOf(gp.getGekaufteAnzahl())+" x "+String.valueOf(gp.getProduktPreis());
			i++;
		}
		initGUI();
	}
	
	public String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());
	}
	
	private void initGUI() {
		try {
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setPreferredSize(new java.awt.Dimension(460, 600));
			
			try
			{
				File f = new File("Rechnung\\"+"Rechnung"+now("yyMMddHHmmss")+".txt");
				FileWriter fw = new FileWriter(f,true);

				
				fw.write(	"############ Rechnung ############"+"\n\n"+
							"erstellt am: 	"+rechnung.getZeit()+"\n\n"	);
				
				fw.write(" Käufer : "+rechnung.getKundeBesitzer().getVorname()+" "+rechnung.getKundeBesitzer().getNachname()+"\n"+"\t\t  "+
						rechnung.getKundeBesitzer().getStrasseUndHausnummer()+"  "+rechnung.getKundeBesitzer().getLieferungPLZ()+"  "+rechnung.getKundeBesitzer().getLieferungWohnort()+"\n\n"
						);
				for(int i=0;i<list.length;i++){
					fw.write(list[i]+"\n");
				}
				fw.write("\n\nSumme  "+rechnung.getSumme()+"  Euro");
				fw.write("\n\n ###### Danke für Ihren Einkauf #######");
				
				fw.flush();
				fw.close();
			}
			catch( IOException e )
			{
				e.printStackTrace();
			}
			
			
			{
				RechnungLabel = new JLabel();
				getContentPane().add(RechnungLabel);
				RechnungLabel.setText("Rechnung");
				RechnungLabel.setBounds(171, 12, 126, 35);
				RechnungLabel.setFont(new java.awt.Font("Segoe UI",1,20));
				RechnungLabel.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				KundenNameTextPane1 = new JTextPane();
				getContentPane().add(KundenNameTextPane1);
				KundenNameTextPane1.setBounds(21, 100, 192, 100);
				KundenNameTextPane1.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				Kunde k = rechnung.getKundeBesitzer();
				KundenNameTextPane1.setText(k.getVorname()+" "+k.getNachname()+"\n"+k.getLieferungStrasseUndHausnummer()+"\n"+k.getLieferungPLZ()+" "+k.getLieferungWohnort());
			}
			{
				ZeitTextPane = new JTextPane();
				getContentPane().add(ZeitTextPane);
				ZeitTextPane.setBounds(213, 65, 208, 23);
				ZeitTextPane.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				ZeitTextPane.setText(rechnung.getZeit());
			}
			{
				ProduktTextPane = new JTextPane();
				getContentPane().add(ProduktTextPane);
				ProduktTextPane.setBounds(21, 223, 400, 204);
				ProduktTextPane.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				Vector warenkorb = rechnung.getWarenkorb();
				Vector<String> v = new Vector();
				
				Iterator<GewaehltesProdukt> iter = warenkorb.iterator();
				while(iter.hasNext()){
					GewaehltesProdukt gewaehltesProdukt = (GewaehltesProdukt)iter.next();					
					v.add(gewaehltesProdukt.getProduktName()+"         "+gewaehltesProdukt.getGekaufteAnzahl()+" x "+gewaehltesProdukt.getProduktPreis());
					
				}
				ProduktTextPane.setText(v.toString());
				
			}
			{
				SummeTextPane = new JTextPane();
				getContentPane().add(SummeTextPane);
				SummeTextPane.setText("Summe  "+ rechnung.getSumme() +" Euro");
				SummeTextPane.setBounds(266, 474, 155, 26);
				SummeTextPane.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			}
			pack();
			this.setSize(464, 600);
			this.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
