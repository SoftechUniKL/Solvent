import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTable;

import org.junit.Test;


/*Test für das Komplette Interface*/
public class Testerinho {
	
	/*
	
	@Test
	public void testLoeschen(){
		try {
			FileWriter fw = new FileWriter("data/Testfile.csv",true);
			BufferedWriter bw = new BufferedWriter(fw);
				
			bw.write("13/12/2015,Testausgabe,299.90\n");
			bw.write("14/12/2015, Testausgabe2, 20.50\n");
			bw.close();
			} 
		catch (IOException e) {
			System.out.println("Daten konnten nicht gespeichert werden!");
			e.printStackTrace();
			}
		Sparziel testSparziel = new Sparziel();
		String[][] testTabelle = testSparziel.readCSV("data/Testfile.csv");
		MonatsuebersichtGUI newGUI  = new  MonatsuebersichtGUI();
		JTable table = new JTable(testTabelle, new Object[] { "Datum", "Bezeichnung","Betrag (\u20ac)" });
		String ergebnis = newGUI.loeschen("Testfile.", newGUI.btnStart, table);
		
		
		
		
	}
	*/
	
	@Test
	public void testEinnahmenAusgaben(){
		try {
			FileWriter fw = new FileWriter("data/ausgaben.csv");
			BufferedWriter bw = new BufferedWriter(fw);
				
			bw.write("13/12/2015,Testausgabe,2.0\n");
			bw.close();
			} 
		catch (IOException e) {
			System.out.println("Daten konnten nicht gespeichert werden!");
			e.printStackTrace();
			}
		try {
			FileWriter fw = new FileWriter("data/einnahmen.csv");
			BufferedWriter bw = new BufferedWriter(fw);
				
			bw.write("13/12/2015,Testeinnahme,300.0\n");
			bw.close();
			} 
		catch (IOException e) {
			System.out.println("Daten konnten nicht gespeichert werden!");
			e.printStackTrace();
			}
		Sparziel testSparziel = new Sparziel();
		Double testDouble = testSparziel.verrechnen();
		System.out.println(testDouble);
		assertEquals("Falsche Berechnung von Einnahmen-Ausgaben",testDouble, 298.0,0.0 );
		
		
		
		
	}
	
	@Test
	public void testSparziel(){
		try {
			FileWriter fw = new FileWriter("data/ausgaben.csv");
			BufferedWriter bw = new BufferedWriter(fw);
				
			bw.write("13/12/2015,Testausgabe,0.0\n");
			bw.close();
			} 
		catch (IOException e) {
			System.out.println("Daten konnten nicht gespeichert werden!");
			e.printStackTrace();
			}
		try {
			FileWriter fw = new FileWriter("data/einnahmen.csv");
			BufferedWriter bw = new BufferedWriter(fw);
				
			bw.write("13/12/2015,Testeinnahme,300.0\n");
			bw.close();
			}
		catch (IOException e) {
			System.out.println("Daten konnten nicht gespeichert werden!");
			e.printStackTrace();
			}
		try {
			FileWriter fw = new FileWriter("data/sparen.csv");
			BufferedWriter bw = new BufferedWriter(fw);
				
			bw.write("test1,Sparziel,29/07/2015,29/07/2015,200.0");
			bw.close();
			}
		catch (IOException e) {
			System.out.println("Daten konnten nicht gespeichert werden!");
			e.printStackTrace();
			}
		Sparziel testSparziel = new Sparziel();
		Double testDouble = testSparziel.erreicht(0);
		System.out.println(testDouble);
		assertEquals("Falsche Berechnung von Einnahmen-Ausgaben",testDouble, 200.0,0.0 );
		
		
	}
	
	@Test
	public void testCSVReader(){
		try {
			FileWriter fw = new FileWriter("data/Testfile.csv",true);
			BufferedWriter bw = new BufferedWriter(fw);
				
			bw.write("13/12/2015,Testausgabe,299.90\n");
			bw.close();
			} 
		catch (IOException e) {
			System.out.println("Daten konnten nicht gespeichert werden!");
			e.printStackTrace();
			}
		MonatsuebersichtGUI newGUI  = new  MonatsuebersichtGUI();
		ArrayList<Posten> TestPosten = newGUI.CSVReader("data/Testfile.csv");
		assertEquals("Falsch eingelesen",TestPosten.get(0).getBetrag() , 299.90, 0.0);	
		assertEquals("Falsch eingelesen", TestPosten.get(0).getBezeichnung(), "Testausgabe");
		
		
	}
	
	@Test
	public void testRun(){
	
			
				MonatsuebersichtGUI frame = new MonatsuebersichtGUI();
				frame.setVisible(true);
				boolean testvariable = frame.isVisible();
			
		
	assertTrue("Der Mainframe wird nicht dargestellt", testvariable);
	
	}
	}
	

