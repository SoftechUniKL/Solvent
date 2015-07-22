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
import java.util.Arrays;
import java.util.Date;
import java.util.Calendar; 

public class Monatsuebersicht extends JFrame {
/*	public static void main(String[]args)	{
		// Ausgabe des Array
	    for (String[] arr : einnahmen) {
	        System.out.println(Arrays.toString(arr));
	}
*/	
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