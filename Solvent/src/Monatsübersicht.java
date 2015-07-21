import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class Monatsübersicht	{
	public static void main(String[]args)	{
		
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
	 