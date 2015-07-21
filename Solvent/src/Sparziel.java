import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.text.DecimalFormat;


import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar; 


public class Sparziel extends JFrame {

	private JPanel contentPane;
	private JTextField textField_bezeichnung;
	private JTextField textField_betrag = new JFormattedTextField(new DecimalFormat("#,###") );

	
	//Nennt das Fenster "Neue Buchung" und f�gt Buttons und Eingabefelder hinzu
public Sparziel () {
		
		
		
		setTitle("Neues Ziel setzen");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(300, 200, 571, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Datum bis wann das Ziel erreicht werden soll:");	
		JLabel lblNewLabel_1 = new JLabel("Bezeichnung:");	
		JLabel lblNewLabel_2 = new JLabel("Betrag in \u20AC:");
		
		textField_bezeichnung = new JTextField();
		textField_bezeichnung.setColumns(10);	
		textField_betrag = new JTextField();
		textField_betrag.setColumns(10);
		
		JLabel lblBuchenAls = new JLabel("Buchen als...");

		JRadioButton rdbtnSparziel = new JRadioButton("Sparziel");	
		JRadioButton rdbtnSchulden = new JRadioButton("Schulden");
		
		//Gruppiert die Buttons "Einnahme" und "Ausgabe", damit jeweils nur einer der beiden ausgew�hlt werden kann 
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnSchulden);
		bg.add(rdbtnSparziel);
		
		//Kreiert den Button "Fertig" und die Spinner f�r das Datum
		JButton btnFertig = new JButton("Fertig");
		
		long now = (new Date()).getTime();
		long tagMillis = 24*60*60*1000;
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(now), new Date(now-tagMillis*356),new Date(now+tagMillis*356),Calendar.DATE));
		spinner.setEditor( new JSpinner.DateEditor(spinner, "dd/MM/yyyy" ) );
	
		
		//Weist dem Button "Fertig" Aktionen beim Klicken zu 
        btnFertig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	if (textField_bezeichnung.getText().equals("") ) {
        			JFrame f = new JFrame( "Achtung" );
        		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        		    f.setSize( 250, 100 );
        		    f.getContentPane().add( new JLabel( "Bitte geben Sie eine Bezeichnung an!") );
        		    f.setVisible( true );
        		    
        		    
        		}
        		else if (textField_betrag.getText().equals("")) {
        			JFrame f = new JFrame( "Achtung" );
        		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        		    f.setSize( 250, 100 );
        		    f.getContentPane().add( new JLabel( "Bitte geben Sie einen Betrag an!") );
        		    f.setVisible( true );
        		    
        		}
            	

            	
        		else {
        			if (rdbtnSparziel.isSelected()){
        				speichern("sparziel");
        				dispose();
        				}
        			else if (rdbtnSchulden.isSelected()){
        				speichern("schuld");
        				dispose();
        				}
        			else {
        				JFrame f = new JFrame( "Achtung" );
            		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
            		    f.setSize(470, 100);
            		    f.getContentPane().add( new JLabel( "Bitte w�hlen Sie aus, ob es sich um ein Sparziel oder eine Schuldenposition handelt!") );
            		    f.setVisible( true );
        		}
        		}
        			
            }
            	 
            public void speichern(String n) {
        		FileWriter fw;
        		try {
        			fw = new FileWriter("data/sparen.csv",true);
        			String a = new SimpleDateFormat("dd/MM/yyyy").format(spinner.getValue());
        			BufferedWriter bw = new BufferedWriter(fw);
        			String test = a + ", " + textField_bezeichnung.getText() + ", " + textField_betrag.getText()+", "+n+ "\n";
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





public String readCSV(int r, int c) {
	String[][] erg = new String[5][6];
    try {
        java.io.BufferedReader FileReader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("data/sparen.csv")));
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
        return erg[r][c];
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Daten k�nnen nicht aufgerufen werden");
    }
    return null;
}
	
public int erreicht(){
for(int i = 0; i < 5; i++){
    if (readCSV(i,1) == "Schuld"){
    System.out.println("Treffer");
		
	}
}
int i =0;
return i;
}



}