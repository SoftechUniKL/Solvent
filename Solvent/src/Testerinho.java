import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
	
	
	
}
