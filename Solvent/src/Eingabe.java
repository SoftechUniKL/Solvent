import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar; 


	public class Eingabe extends javax.swing.JFrame{
		
		
		public Eingabe(){
		/**
		 * Generierung eines Meldungsfensters namens "Neue Buchung" und eines Inhaltsfeldes mit Eingabefeldern und deren Bezeichnungen
		 */
		final JPanel contentPane;
		final JTextField textField_bezeichnung;
		final JTextField textField_betrag;
		
		setTitle("Neue Buchung");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(300, 200, 398, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Datum");	
		JLabel lblNewLabel_1 = new JLabel("Bezeichnung");	
		JLabel lblNewLabel_2 = new JLabel("Betrag in €");
		
		textField_bezeichnung = new JTextField();
		textField_bezeichnung.setColumns(10);	
		textField_betrag = new JTextField();
		
		textField_betrag.setColumns(10);
		
		JLabel lblBuchenAls = new JLabel("Buchen als...");

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Ausgabe");	
		JRadioButton rdbtnEinnahme = new JRadioButton("Einnahme");
		/**
		 * Gruppiert die Buttons "Einnahme" und "Ausgabe", damit jeweils nur einer der beiden ausgewählt werden kann 
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnEinnahme);
		bg.add(rdbtnNewRadioButton);
		/**
		 * Kreiert den Button "Fertig" und die Spinner für das Datum
		 */
		JButton btnFertig = new JButton("Fertig");
		
		long now = (new Date()).getTime();
		long tagMillis = 24*60*60*1000;
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(now), new Date(now-tagMillis*356),new Date(now+tagMillis*356),Calendar.DATE));
		spinner.setEditor( new JSpinner.DateEditor(spinner, "dd/MM/yyyy" ) );
		
		
		       
        /**
         * Modifiziert das Fenster "neue Buchung" und gibt die Positionen der Eingabefelder, Buttons und Spinner an 
         */
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 82, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, Alignment.LEADING)
								.addComponent(lblNewLabel_2, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(spinner, 0, 0, Short.MAX_VALUE)
								.addComponent(textField_bezeichnung)
								.addComponent(textField_betrag)
								.addComponent(btnFertig, Alignment.TRAILING)))
						.addComponent(rdbtnEinnahme)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(lblBuchenAls, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(200, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_bezeichnung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_betrag, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(lblBuchenAls)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(btnFertig))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnEinnahme)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	
		
		
		btnFertig.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
        	/**
        	 * Wenn in das Textfeld Bezeichnung nichts eingegeben wurde, wird eine Fehlermeldung ausgeworfen
        	 */
        	if (textField_bezeichnung.getText().equals("") ) {
    			JFrame f = new JFrame( "Achtung" );
    		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
    		    f.setSize( 250, 100 );
    		    f.add( new JLabel( "Bitte geben Sie eine Bezeichnung an!") );
    		    f.setVisible( true );   
        	}
        	/**
        	 * Wenn in das Textfeld Betrag nichts eingegeben wurde, wird eine Fehlermeldung ausgeworfen
        	 */
    		else if (textField_betrag.getText().equals("")) {
    			JFrame f = new JFrame( "Achtung" );
    		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
    		    f.setSize( 250, 100 );
    		    f.add( new JLabel( "Bitte geben Sie einen Betrag an!") );
    		    f.setVisible( true ); 
    		}
    		else  {
    			try {
            		Double.parseDouble(textField_betrag.getText());
            		/**
            		 * Wenn einer der beiden Buttons "Einnahme" oder "Ausgabe" angeklickt wurde, werden die eingegebenen 
            		 * Informationen in der entsprechenden CSV Datei gespeichert, andernfalls wird eine Felhermeldung ausgegeben
            		 */
            		if (rdbtnNewRadioButton.isSelected()){
        				speichern("ausgaben");
        				dispose();
        				}
        			else if (rdbtnEinnahme.isSelected()){
        				speichern("einnahmen");
        				dispose();
        				}
        			else {
        				JFrame f = new JFrame( "Achtung" );
            		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
            		    f.setSize(470, 100);
            		    f.add( new JLabel( "Bitte wählen Sie aus, ob es sich um eine Einnahme oder eine Ausgabe handelt!") );
            		    f.setVisible( true );
        		}
            		} 
    			/**
    			 * Handelt es sich, bei dem für den Betrag eingegebenen Wert, nicht um eine Zahl, wird eine Fehlermeldung ausgegeben
    			 */
    			catch (NumberFormatException ex) {
    				JFrame f = new JFrame( "Achtung" );
        		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        		    f.setSize(470, 100);
        		    f.add( new JLabel( "Bitte geben sie für den Betrag eine Zahl ein") );
        		    f.setVisible( true );
            		}
    		}

        } 
        /**
         * Generieren der Funktion speichern, welche die zuvor ausgewählten Informationen abhängig von ihrer Herkunft "Einnahmen" oder "Ausgaben"
         * in 2 gleichnamige CSV-Dateien speichert
         */
        public void speichern(String n) {
    		FileWriter fw;
    		try {
    			fw = new FileWriter("data/"+n+".csv",true);
    			String a = new SimpleDateFormat("dd/MM/yyyy").format(spinner.getValue());
    			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    			String uhrzeit = sdf.format(new Date());
    			BufferedWriter bw = new BufferedWriter(fw);
    			String test = a + "," + textField_bezeichnung.getText() + "," + Double.parseDouble(textField_betrag.getText())+ ","+ uhrzeit +"\n";
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
}
	}
