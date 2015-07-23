import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


/*Test für das Komplette Interface*/
public class Testerinho {
	
	@Test
	public void testCSVReader(){
		MonatsuebersichtGUI newGUI  = new  MonatsuebersichtGUI();
	ArrayList<Posten> TestPosten = newGUI.CSVReader("data/ausgaben.csv");
	assertEquals("Falsch eingelesen",TestPosten.get(0).getBetrag() , 299.90, 0.0);	
	assertEquals("Falsch eingelesen", TestPosten.get(0).getBezeichnung(), "Testausgabe");
	}
	
	
	
}
