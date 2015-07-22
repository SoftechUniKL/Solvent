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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Calendar; 

public class Monatsuebersicht extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtJahresbersicht;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	
	public Monatsuebersicht()	{
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
		String [][] einnahmen = new String [12][3];
		String [][] datum = new String[13][3];
		
		//Datei einlesen und splitten
		try	{
			java.io.BufferedReader FileReader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("data/einnahmen.csv")));
			String zeile = "";
			//String für neue Zeile im Array
			
			int i = 0;
			while((zeile = FileReader.readLine())!=null)	{
				String[] split1  = zeile.split(",");
				for (int j = 0; j < split1.length; j++)	{
					einnahmen[i][j] = split1[j];
				}
				i++;
				while((zeile = FileReader.readLine())!= null)	{
					String[] split2 = zeile.split("/");
					for (int j = 0; j < split2.length; j++)	{
						einnahmen[i][j] = split2[j];
					}
					i++;
					
				}
			}
			
			FileReader.close();
			return einnahmen[r][c];
		} catch (Exception e)	{
			e.printStackTrace();
			System.out.println("Daten können nicht aufgerufen werden.");
		}
		
		return null;
		
	}
/*	public String readCSVAusgaben(int r, int c)	{
		String [][] ausgaben = new String [12][4];
		try	{
			java.io.BufferedReader FileReader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("data/ausgaben.csv")));
			String zeile = "";
			int i = 0;
			while((zeile = FileReader.readLine())!=null)	{
				String[] split  = zeile.split(",");
				for (int j = 0; j < split.length; j++)	{
					ausgaben[i][j] = split[j];
				}
				i++;
			}
			
			FileReader.close();
			return ausgaben[r][c];
		} catch (Exception e)	{
			e.printStackTrace();
			System.out.println("Ausgaben können nicht aufgerufen werden.");
		}
		return null;
		
	}*/
}



/*import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class Monatsuebersicht	{
	public static void main(String[]args)	{
		
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
		final String[][] valuesArray = new String[lines.size()][];
	    int cnt = 0;
	    for (final String line : lines) {
	        valuesArray[cnt++] = line.split(",");
	    }
	 
	    // Ausgabe des Array
	    for (String[] arr : valuesArray) {
	        System.out.println(Arrays.toString(arr));
	    }
	}
}
	 */