import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Window.Type;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Canvas;

import javax.swing.JButton;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;






import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar; 
import java.util.Locale;


public class Eingabe extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;

	
	//Kreiert ein Fenster 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Eingabe  frame = new Eingabe ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	//Nennt das Fenster "Neue Buchung" und fügt Buttons und Eingabefelder hinzu
	public Eingabe () {
		setTitle("Neue Buchung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 398, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Datum TT/MM/JJJJ");	
		JLabel lblNewLabel_1 = new JLabel("Bezeichnung");	
		JLabel lblNewLabel_2 = new JLabel("Betrag in €");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);	
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblBuchenAls = new JLabel("Buchen als...");

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Ausgabe");	
		JRadioButton rdbtnEinnahme = new JRadioButton("Einnahme");
		
		//Gruppiert die Buttons "Einnahme" und "Ausgabe", damit jeweils nur einer der beiden ausgewählt werden kann 
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnEinnahme);
		bg.add(rdbtnNewRadioButton);
		
		//Kreiert den Button "Fertig" und die Spinner für das Datum
		JButton btnFertig = new JButton("Fertig");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerListModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerListModel(new String[] {"2015", "2016", "2017", "2018", "2019", "2020"}));

	
		
		//Weist dem Button "Fertig" Aktionen beim Klicken zu 
        btnFertig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	if (textField_3.getText().equals("") ) {
        			JFrame f = new JFrame( "Achtung" );
        		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        		    f.setSize( 250, 100 );
        		    f.add( new JLabel( "Bitte geben Sie eine Bezeichnung an!") );
        		    f.setVisible( true );
        		    
        		    
        		}
        		else if (textField_4.getText().equals("")) {
        			JFrame f = new JFrame( "Achtung" );
        		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        		    f.setSize( 250, 100 );
        		    f.add( new JLabel( "Bitte geben Sie einen Betrag an!") );
        		    f.setVisible( true );
        		}
        		else {
        			if (rdbtnNewRadioButton.isSelected()){
        				writer_ausgaben();
        				}
        			else if (rdbtnEinnahme.isSelected()){
        				writer_einnahmen();
        				}
        			else {
        				JFrame f = new JFrame( "Achtung" );
            		    f.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
            		    f.setSize(470, 100);
            		    f.add( new JLabel( "Bitte wählen Sie aus, ob es sich um eine Einnahme oder eine Ausgabe handelt!") );
            		    f.setVisible( true );
        		}
        		}
        			
            }
            	 
            void writer_einnahmen() {
        		FileWriter fw;
        		try {
        			fw = new FileWriter("data/einnahmen.csv",true);
        			String a =  Double.valueOf(spinner.getValue().toString()).intValue()+ "." + Double.valueOf(spinner_1.getValue().toString()).intValue() + "." + Double.valueOf(spinner_2.getValue().toString()).intValue(); //Datum ist in String format
        			BufferedWriter bw = new BufferedWriter(fw);
        			String test = a + ", " + textField_3.getText() + ", " + textField_4.getText()+ "\n";
        			bw.write(test);
        			
        			bw.close();
        			dispose();
        			} 
        		catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        			}
        		} 
            void writer_ausgaben() {
            		FileWriter fw;
            		try {
            			fw = new FileWriter("data/ausgaben.csv",true);
            			String a =  Double.valueOf(spinner.getValue().toString()).intValue()+ "." + Double.valueOf(spinner_1.getValue().toString()).intValue() + "." + Double.valueOf(spinner_2.getValue().toString()).intValue(); //Datum ist in String format
            			BufferedWriter bw = new BufferedWriter(fw);
            			String test = a + ", " + textField_3.getText() + ", " + textField_4.getText() + "\n";
            			bw.write(test);
            			bw.close();
               			} 
            		catch (IOException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            			}
            	}
            	
            
            }
        );	
		       
        //Modifiziert das Fenster "neue Buchung" und gibt Positionen der Buttons an 
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, Alignment.LEADING)
								.addComponent(lblNewLabel_2, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField_3)
									.addComponent(textField_4)
									.addComponent(btnFertig, Alignment.TRAILING))))
						.addComponent(rdbtnEinnahme)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(lblBuchenAls, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(144, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
	}
}