import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;


/*Test für das Komplette Interface*/
public class Testerinho {
	
	
	
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
		
			try {
				MonatsuebersichtGUI frame = new MonatsuebersichtGUI();
				frame.setVisible(true);
				boolean testvariable = frame.isVisible();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	assertEquals("Der Mainframe wird nicht dargestellt", frame.isVisible(), true);
	
	}
	}
	

