package shop.local.ui.gui.mitarbeiterClientPanels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.Map.Entry;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import shop.local.ui.gui.MitarbeiterClient;
import shop.local.valueobjects.Produkt;
import javax.swing.Box;
import javax.swing.WindowConstants;
import javax.swing.JFrame;


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
public class PanelStatistik extends javax.swing.JPanel implements ActionListener {

	private int aktuellerTag;
	private JComboBox auswahlBox;
	private Vector<Produkt> produkt;
	private ChartPanel chartPanel;
	private JFreeChart chart;
	private XYDataset dataset;
	private int aktuellerMonat;
	private JButton buttonClear;
	private int aktuellesJahr;
	private String produktName;
	private LinkedHashMap<String, String> daten;
	private LinkedHashMap<String, Float> values;
	private TimeSeries series;
	private JButton buttonZeigeLogfile;
	private int id;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PanelStatistik());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	
	public PanelStatistik() {
		super();

		berechneMonat();
		initChart();
		initGUI();
		berechneMonat();	
	}
	
	private void initGUI() {
		daten = new LinkedHashMap<String, String>();
		values = new LinkedHashMap<String, Float>();

		try {
			setPreferredSize(new Dimension(400, 300));
	        BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.Y_AXIS);
			this.setBackground(new java.awt.Color(124,156,173));
	        this.setLayout(thisLayout);
	        this.setPreferredSize(new Dimension(800,600));
	        this.add(chartPanel);
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			//Fuelle ComboBox mit allen Artikeln
			produkt = MitarbeiterClient.sf.gibAlleProdukt();
	    	Vector<String> pd = new Vector<String>();
	    	for (Produkt pro: produkt) {
				Vector<String> produktVector = new Vector<String>();
				produktVector.add(pro.getProduktName());
				pd.addAll(produktVector);
			}
	    	JPanel p = new JPanel();
	    	BoxLayout pLayout = new BoxLayout(p, javax.swing.BoxLayout.X_AXIS);
			p.setBackground(new java.awt.Color(124,156,173));
	    	p.setLayout(pLayout);
			{
				auswahlBox = new JComboBox(pd);
				auswahlBox.setPreferredSize(new Dimension(160,40));
				auswahlBox.setMaximumSize(new Dimension(160,40));
				auswahlBox.setMinimumSize(new Dimension(160,40));
				auswahlBox.addActionListener(this);
				p.add(auswahlBox);	

			}
			{
				p.add(Box.createRigidArea(new Dimension(50,15)));
			}
			{
				buttonClear = new JButton(new ImageIcon("iconset/32x32/process_warning.png"));
				buttonClear.setText("   Clear Chart");
				buttonClear.setPreferredSize(new Dimension(160,40));
				buttonClear.setMaximumSize(new Dimension(160,40));
				buttonClear.setMinimumSize(new Dimension(160,40));
				buttonClear.addActionListener(this);
				p.add(buttonClear);
			}
			{
				p.add(Box.createRigidArea(new Dimension(50,15)));
			}
			{
				buttonZeigeLogfile = new JButton(new ImageIcon("iconset/32x32/process_info.png"));
				buttonZeigeLogfile.setText("   Show Logfile");
				buttonZeigeLogfile.setPreferredSize(new Dimension(160,40));
				buttonZeigeLogfile.setMaximumSize(new Dimension(160,40));
				buttonZeigeLogfile.setMinimumSize(new Dimension(160,40));
				p.add(buttonZeigeLogfile);
				buttonZeigeLogfile.setEnabled(false);
				buttonZeigeLogfile.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {					        
			    
							JFrame logs;
							try {
								logs = new PanelBearbeiteLogfiles(id);
								logs.setVisible(true);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	
			         }
			    });
			}
			{
				this.add(p);
			}
			{
				this.add(Box.createRigidArea(new Dimension(100,15)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initChart(){
        dataset = createDataset();
        chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        
	}
	
    private XYDataset createDataset() {
        
        series = new TimeSeries("Statistik");
        Day current = new Day(aktuellerTag,aktuellerMonat,aktuellesJahr);
        if(values == null){
            double value = 00.0;
            for (int i = 0; i < 31; i++) {
                try {
                    series.add(current, new Double(value));
                    current = (Day) current.next();
                }
                catch (SeriesException e) {
                    System.err.println("Error");
                }
            }
        }
        else{  
        	float letzteAnzahl = 0;
            for (int i = 0; i < 31; i++) {
 		        String day = current.toString();
                try {
                	Float value = values.get(day);
                	if(value != null){
                		series.add(current, value);
                		letzteAnzahl = value;
                	}
                	else if(value == null){
                    	series.add(current, letzteAnzahl);
                	}
                }
                catch (SeriesException e) {
                    System.err.println("Error");
                }
 		    	current = (Day) current.next();
            }
        }

        return new TimeSeriesCollection(series);
    }

    private JFreeChart createChart(final XYDataset dataset) {
        return ChartFactory.createTimeSeriesChart(
            produktName, 
            "Tage", 
            "Anzahl der Produkt", //
            dataset,
            false, 
            false, 
            false
        );
    }
    
    private void berechneMonat(){
    	Calendar cal = Calendar.getInstance();
        aktuellerTag = cal.get(Calendar.DAY_OF_MONTH);     
        aktuellerMonat = cal.get(Calendar.MONTH);
        aktuellesJahr = cal.get(Calendar.YEAR);
    }
	
    //Button-ActionListener
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(auswahlBox)) {	
			 
			String wahl = (String)auswahlBox.getSelectedItem();
        	 int suche = 0;
			try {
				suche = MitarbeiterClient.sf.sucheNachName(wahl);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
        	 if(suche!=0){
        		id = suche;
        		try {
					daten = MitarbeiterClient.sf.getDatenAusLogfile(suche);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
     		    for ( Entry<String, String> e : daten.entrySet() ){
     		    	try {
						values.put(e.getKey(), Float.parseFloat(e.getValue()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}
     		    } 
     		    
        		updateChart();
        	 }
		}
		if (ae.getSource().equals(buttonClear)) {	
			clear();
		}
	}
	

	
	public void updateChart(){
		produktName = (String) auswahlBox.getSelectedItem()+"("+id+")"+" - 30 Tage Übersicht";
    	this.chart = createChart(createDataset());
        this.chart.fireChartChanged();
        this.chartPanel.setChart(this.chart);
        this.chartPanel.updateUI();
    }
	public void clear() {
			values.clear();
			series.clear();
			this.chartPanel.setChart(null);
			this.series.fireSeriesChanged();
	        this.chartPanel.setChart(this.chart);
	        this.chartPanel.updateUI();
 	}

}
