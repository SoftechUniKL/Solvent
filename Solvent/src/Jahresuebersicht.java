import java.awt.Button;
import java.awt.Canvas;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.text.DecimalFormat;


import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Calendar; 

public class Jahresuebersicht extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtJahresbersicht;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	
	
	
	public Jahresuebersicht()	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 383);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		Button button = new Button("Start");
		menuBar.add(button);
		
		Button button_1 = new Button("Ausgaben");
		menuBar.add(button_1);
		
		Button button_2 = new Button("Einnahmen");
		menuBar.add(button_2);
		
		Button button_3 = new Button("Monats\u00FCbersicht");
		menuBar.add(button_3);
		
		Button button_4 = new Button("Jahres\u00FCbersicht");
		menuBar.add(button_4);
		
		Button button_5 = new Button("Planung");
		menuBar.add(button_5);
		
		Button button_6 = new Button("Sparen");
		menuBar.add(button_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtJahresbersicht = new JTextField();
		txtJahresbersicht.setHorizontalAlignment(SwingConstants.CENTER);
		txtJahresbersicht.setText("Jahres\u00FCbersicht");
		txtJahresbersicht.setBounds(140, 11, 303, 20);
		contentPane.add(txtJahresbersicht);
		txtJahresbersicht.setColumns(10);
		
		JTextPane txtpnJahresgesamteinnahmen = new JTextPane();
		txtpnJahresgesamteinnahmen.setText("Jahresgesamteinnahmen");
		txtpnJahresgesamteinnahmen.setBounds(243, 58, 148, 20);
		contentPane.add(txtpnJahresgesamteinnahmen);
		
		JTextPane txtpnJahresgesamtausgaben = new JTextPane();
		txtpnJahresgesamtausgaben.setText("Jahresgesamtausgaben");
		txtpnJahresgesamtausgaben.setBounds(243, 91, 148, 20);
		contentPane.add(txtpnJahresgesamtausgaben);
		
		textField = new JTextField();
		textField.setBounds(401, 58, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(401, 91, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("\u20AC");
		textPane.setBounds(497, 58, 17, 20);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("\u20AC");
		textPane_1.setBounds(497, 91, 17, 20);
		contentPane.add(textPane_1);
		
		table = new JTable();
		table.setBounds(30, 58, 177, 222);
		contentPane.add(table);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(236, 117, 278, 156);
		contentPane.add(canvas);
	}
	
	
	public String readCSVEinnahmen(int r, int c)	{
		//Datei einlesen und splitten
		FileReader myFile = null;
		BufferedReader buff = null;
		final ArrayList<String> lines = new ArrayList<String>();
		try	{
			myFile = new FileReader("data/einnahmen.csv");
			buff = new BufferedReader(myFile);
			String line;
			while ((line = buff.readLine()) != null)	{
				System.out.println(line);
				lines.add(line);
			}
		} catch(IOException e)	{
			System.err.println("Error2: " + e );
		} finally	{
			 try {
		            buff.close();
		            myFile.close();
			 } catch (IOException e) {
		            System.err.println("Error2 :" + e);
		}
	}
		final String[][] einnahmen = new String[lines.size()][];
		final String[][] DatumEinnahmen = new String[lines.size()][];
	    int cnt = 0;
	    for (final String line : lines) {
	        einnahmen[cnt++] = line.split(",");
	    }
	    for (final String line : lines) {
	        DatumEinnahmen[cnt++] = line.split("/");
	    }
	 
	    // Ausgabe des Array
//	    for (String[] arr : einnahmen) {
//	        System.out.println(Arrays.toString(arr));
//	    }
		return null;
}
	public String readCSVAusgaben(int r, int c)	{
		//Datei einlesen und splitten
		FileReader myFile = null;
		BufferedReader buff = null;
		final ArrayList<String> lines = new ArrayList<String>();
		try	{
			myFile = new FileReader("data/ausgaben.csv");
			buff = new BufferedReader(myFile);
			String line;
			while ((line = buff.readLine()) != null)	{
				System.out.println(line);
				lines.add(line);
			}
		} catch(IOException e)	{
			System.err.println("Error2: " + e );
		} finally	{
			 try {
		            buff.close();
		            myFile.close();
			 } catch (IOException e) {
		            System.err.println("Error2 :" + e);
		}
	}
		final String[][] ausgaben = new String[lines.size()][];
		final String[][] DatumAusgaben = new String[lines.size()][];
	    int cnt = 0;
	    for (final String line : lines) {
	        ausgaben[cnt++] = line.split(",");
	    }
	    for (final String line : lines) {
	        DatumAusgaben[cnt++] = line.split("/");
	    }
	 
	    // Ausgabe des Array
//	    for (String[] arr : ausgaben) {
//	        System.out.println(Arrays.toString(arr));
//	    }
		return null;
	}
}
		
