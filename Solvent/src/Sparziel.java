import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;


public class Sparziel extends JFrame {

	private JPanel contentPane;
	private JTextField textField_bezeichnung;
	private JTextField textField_betrag = new JFormattedTextField(new DecimalFormat("#,###") );
	

public Sparziel () {
		
	/**
		 * Creates userdialog and its components for adding new debts and saving targets
		 */
		setTitle("Neues Ziel setzen");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(300, 200, 571, 270);
		contentPane = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		JLabel lblNewLabel = new JLabel("Datum bis wann das Ziel erreicht werden soll:");	
		JLabel lblNewLabel_1 = new JLabel("Bezeichnung:");	
		JLabel lblNewLabel_2 = new JLabel("Betrag in \u20AC:");
		JLabel lblBuchenAls = new JLabel("Buchen als...");
		JRadioButton rdbtnSparziel = new JRadioButton("Sparziel");	
		JRadioButton rdbtnSchulden = new JRadioButton("Schulden");
		JButton btnFertig = new JButton("Fertig");
		textField_bezeichnung = new JTextField();
		textField_bezeichnung.setColumns(10);	
		textField_betrag = new JTextField();
		textField_betrag.setColumns(10);
		bg.add(rdbtnSchulden);
		bg.add(rdbtnSparziel);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		long now = (new Date()).getTime();
		long tagMillis = 24*60*60*1000;
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(now), new Date(now-tagMillis*356),new Date(now+tagMillis*356),Calendar.DATE));
		spinner.setEditor( new JSpinner.DateEditor(spinner, "dd/MM/yyyy" ) );
	
		/**
		 * Adds ActionListener for button "btnFertig"
		 */
        btnFertig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	/**
            	 * Exception handling for wrong inputs
            	 */
            	if (textField_bezeichnung.getText().equals("") ) {
        			JFrame f = new JFrame( "Achtung" );
        		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        		    f.setSize( 250, 100 );
        		    f.getContentPane().add( new JLabel( "Bitte geben Sie eine Bezeichnung an!") );
        		    f.setVisible(true);
        		}
        		else if (textField_betrag.getText().equals("")) {
        			JFrame f = new JFrame( "Achtung" );
        		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        		    f.setSize( 250, 100 );
        		    f.getContentPane().add( new JLabel( "Bitte geben Sie einen Betrag an!") );
        		    f.setVisible(true);
        		}
        		else {
        			/**
        			 * Tries to get a double value from the textfield "textField_betrag"
        			 */
        			try {
                		Double.parseDouble(textField_betrag.getText());
                		/**
                		 * Saves the input data depending on their type 
                		 */
                		if (rdbtnSparziel.isSelected()){
            				speichern("Sparziel");
            				dispose();
            				}
            			else if (rdbtnSchulden.isSelected()){
            				speichern("Schuld");
            				dispose();
            				}
            			else {
            				/**
            				 * Opens message box when there is no type selected
            				 */
            				JFrame f = new JFrame( "Achtung" );
                		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
                		    f.setSize(470, 100);
                		    f.getContentPane().add( new JLabel( "Bitte waehlen Sie aus, ob es sich um ein Sparziel oder eine Schuldenposition handelt!") );
                		    f.setVisible( true );
            			}
                	} 
        			/**
        			 * Catches NumberFormatException
        			 */
        			catch (NumberFormatException ex) {
        				JFrame f = new JFrame( "Achtung" );
            		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
            		    f.setSize(470, 100);
            		    f.add( new JLabel( "Bitte geben sie fuer den Betrag eine Zahl ein") );
            		    f.setVisible( true );
                		}
        		}
            }
            	 
            /**
             * Function to save the data in sparen.csv
             * @param n
             */
            public void speichern(String n) {
        		FileWriter fw;
        		try {
        			fw = new FileWriter("data/sparen.csv",true);
        			String a = new SimpleDateFormat("dd/MM/yyyy").format(spinner.getValue());
        			Date date = java.util.Calendar.getInstance().getTime();
        			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        			String dateToday = dateFormatter.format(date);
        			BufferedWriter bw = new BufferedWriter(fw);
        			String test = textField_bezeichnung.getText() + ","+ n + "," +dateToday+ "," + a + "," + Double.parseDouble(textField_betrag.getText())+  "\n";
        			bw.write(test);
        			bw.close();
        			} 
        		catch (IOException e) {
        			System.out.println("Daten konnten nicht gespeichert werden!");
        			e.printStackTrace();
        			}
            }      
            }
        );
		       
        //Modifiziert das Fenster "neue Buchung" und gibt Positionen der Buttons an 
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
									.addGap(22))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblBuchenAls, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(133)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_bezeichnung, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
								.addComponent(textField_betrag)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnSparziel)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(rdbtnSchulden)
									.addGap(61))))
						.addComponent(btnFertig))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_bezeichnung, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_betrag, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBuchenAls)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnSparziel)
							.addComponent(rdbtnSchulden)))
					.addGap(26)
					.addComponent(btnFertig)
					.addContainerGap(180, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}




public static String[][] readCSV(String filename) {
	int number_of_rows = 0;
	try {
        java.io.BufferedReader FileReader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("data/"+filename+".csv")));
        String zeile="";
        while(null!=(zeile=FileReader.readLine())){         
        	number_of_rows++; 
        }
        FileReader.close();
        
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Groesse des Arrays kann nicht festgelegt werden ");
    }
	
	String[][] erg = new String[number_of_rows][6];
    try {
        java.io.BufferedReader FileReader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("data/"+filename+".csv")));
        String zeile="";
        int i = 0;
        while(null!=(zeile=FileReader.readLine())){         
            String[] split=zeile.split(",");
            for(int j = 0; j<split.length;j++){
            	erg[i][j] = split[j];
            }
            	i++;    
        }
        FileReader.close();
        return erg;
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Daten koennen nicht aufgerufen werden");
    }
    return null;
}

public static Double verrechnen(){
	double ein_gesamt = 0.0;
	double aus_gesamt = 0.0;
	for (int i = 0; i<readCSV("einnahmen").length; i++){
		ein_gesamt = ein_gesamt + Double.parseDouble(readCSV("einnahmen")[i][2]);
	}
	for (int i = 0; i<readCSV("ausgaben").length; i++){
		aus_gesamt = aus_gesamt + Double.parseDouble(readCSV("ausgaben")[i][2]);
	}
	return ein_gesamt-aus_gesamt;
	
}

	
public static Double erreicht(int zeile){
	double einnahmen = verrechnen();
	double alle_schulden_bis_aktuelle_position = 0;
	double einnahmen_minus_schulden_bis_aktuelle_pos = 0;
	double gesamtschulden = 0;
	double alle_sparziele_bis_aktuelle_pos = 0;
	double einnahmen_minus_schulden_und_sz_bis_akt_pos = 0;
	String[][] table = readCSV("sparen");
	try {
	if (table[zeile][1].equals("Schuld")){
		for(int i = 0; i < zeile; i++){
			if(table[i][1].equals("Schuld")){
				alle_schulden_bis_aktuelle_position = alle_schulden_bis_aktuelle_position + Double.parseDouble(table[i][4]);
			}
		}
		einnahmen_minus_schulden_bis_aktuelle_pos = einnahmen - alle_schulden_bis_aktuelle_position;
		if (einnahmen_minus_schulden_bis_aktuelle_pos >= Double.parseDouble(table[zeile][4])){
			return Double.parseDouble(table[zeile][4]);
		}
		else if (einnahmen_minus_schulden_bis_aktuelle_pos < Double.parseDouble(table[zeile][4])){
			if(einnahmen_minus_schulden_bis_aktuelle_pos < 0){
				return 0.0;
			}
			else{
			return einnahmen_minus_schulden_bis_aktuelle_pos;
			}
		}
	}
	else if (table[zeile][1].equals("Sparziel")){
		for(int i = 0; i<table.length; i++){
			if (table[i][1].equals("Schuld")){
				gesamtschulden = gesamtschulden + Double.parseDouble((table[i][4]));
				}
			
		}
		for(int i = 0; i < zeile; i++){
			if(table[i][1].equals("Sparziel")){
				alle_sparziele_bis_aktuelle_pos = alle_sparziele_bis_aktuelle_pos + Double.parseDouble(table[i][4]);
			}
		}
		einnahmen_minus_schulden_und_sz_bis_akt_pos = einnahmen - gesamtschulden - alle_sparziele_bis_aktuelle_pos;
		if (einnahmen_minus_schulden_und_sz_bis_akt_pos >= Double.parseDouble(table[zeile][4])){
			return Double.parseDouble(table[zeile][4]);
		}
		else if(einnahmen_minus_schulden_und_sz_bis_akt_pos < Double.parseDouble(table[zeile][4])){
			if(einnahmen_minus_schulden_und_sz_bis_akt_pos < 0){
				return 0.0;
			}
			else{
				return einnahmen_minus_schulden_und_sz_bis_akt_pos;
			}
		}
	}
	}catch (Exception e) {
        e.printStackTrace();
        System.out.println("Fehler beim Verarbeiten der Daten!");
    }
	return null;
}
}
	



