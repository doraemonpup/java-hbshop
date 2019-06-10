package shop.local.ui.gui.mitarbeiterClientPanels;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;


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

public class PanelBearbeiteLogfiles extends JFrame {
	
   private JTextArea _resultArea = new JTextArea(35, 30);
   private int logfile;
   private Component popup;
   private JMenuItem itemBeenden;
   private JMenuItem itemSpeichern;
   private JMenuBar mb;
   private JMenu menu;

    public PanelBearbeiteLogfiles(int ID) throws IOException {
        BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS);
        getContentPane().setLayout(thisLayout);
    	{
	    	_resultArea.setPreferredSize(new java.awt.Dimension(250, 400));
    	}
        JScrollPane scrollingArea = new JScrollPane(_resultArea);
        
        logfile = ID;
        JPanel content = new JPanel();
        content.setLayout(null);
        content.add(scrollingArea, "Center");
        scrollingArea.setBounds(0, 0, 292, 436);

        this.setSize(300, 500);
        this.setPreferredSize(new Dimension(300, 500));
        this.setContentPane(content);
        
        this.setTitle("Logfile "+logfile);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        {
        	mb = new JMenuBar();
        	setJMenuBar(mb);
        	{
        		menu = new JMenu();
        		mb.add(menu);
        		menu.setText("Datei");
        		{
        			itemSpeichern = new JMenuItem(new ImageIcon("iconset/menubarIcons/save.png"));
        			menu.add(itemSpeichern);
        			itemSpeichern.setText("Speichern");
        			itemSpeichern.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent e) {
                			schreibeArtikelLog();
                		}
                	});
        		}
        		{
        			itemBeenden = new JMenuItem(new ImageIcon("iconset/menubarIcons/power_off.png"));
        			menu.add(itemBeenden);
        			itemBeenden.setText("Beenden");
        			itemBeenden.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent e) {
                			dispose();
                		}
                	});
        		}
        		{
        			
        		}
        	}
        }
        this.pack();
        this.setResizable(false);
        ladeLogfile();

    }
    public static void main(String[] args) throws IOException {
        JFrame win = new PanelBearbeiteLogfiles(0);
        win.setVisible(true);
        
    }
	private void ladeLogfile() throws IOException{
		try {
			BufferedReader in = new BufferedReader(new FileReader("ProduktLogFiles\\"+logfile+".log"));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				_resultArea.append(zeile+"\n");
			}
		} catch (IOException e) {
			JOptionPane errorMessage = new JOptionPane();
            errorMessage.setSize(400,300);
            JOptionPane.showMessageDialog(popup, "Logfile existiert nicht!","Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void schreibeArtikelLog(){
		String logfileName = "ProduktLogFiles\\"+logfile+".log";
        
		try
		{
			FileWriter fw = new FileWriter(logfileName);

			
			fw.write(_resultArea.getText());
			
			fw.flush();
			fw.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
}
	



