import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class Monatsübersicht	{
	public static void main(String[]args)	{
		
		//int[] Monat = {1,2,3,4,5,6,7,8,9,10,11,12};
		//ArrayList<String> EinnahmenJanuar = new ArrayList<String>();
		//ArrayList<String> EinnahmenFebruar = new ArrayList<String>();
		//ArrayList<String> EinnahmenMärz = new ArrayList<String>();
		//ArrayList<String> EinnahmenApril = new ArrayList<String>();
		//ArrayList<String> EinnahmenMai = new ArrayList<String>();
		//ArrayList<String> EinnahmenJuni = new ArrayList<String>();
		//ArrayList<String> EinnahmenJuli = new ArrayList<String>();
		//ArrayList<String> EinnahmenAugust = new ArrayList<String>();
		//ArrayList<String> EinnahmenSeptember = new ArrayList<String>();
		//ArrayList<String> EinnahmenOktober = new ArrayList<String>();
		//ArrayList<String> EinnahmenNovember = new ArrayList<String>();
		//ArrayList<String> EinnahmenDezember = new ArrayList<String>();
		
		
		//Datei einlesen und splitten
		FileReader myFile = null;
		BufferedReader buff = null;
		final ArrayList<String> einnahmen = new ArrayList<String>();
		try	{
			myFile = new FileReader("data/einnahmen.csv");
			buff = new BufferedReader(myFile);
			String einnahme;
			while ((einnahme = buff.readLine()) != null)	{
				System.out.println(einnahme);
				einnahmen.add(einnahme);
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
		final String[][] Budget = new String[einnahmen.size()][];
	    int cnt = 0;
	    for (final String einnahme : einnahmen) {
	        Budget[cnt++] = einnahme.split(",");
	    }
	 
	    // Ausgabe des Array
	    for (String[] arr : Budget) {
	        System.out.println(Arrays.toString(arr));
	    }

	}
	
}
	 