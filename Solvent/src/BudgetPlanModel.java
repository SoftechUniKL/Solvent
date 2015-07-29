import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVReader;

/**
 * Datenmodell des Budgetplaners
 * 
 * Die Daten werden in der Datei data/budget.csv abgespeichert als CSV-Datei.
 * 
 */
/*public class BudgetPlanModel {
	List<Posten> ausgaben;

	public BudgetPlanModel() {
		this.ausgaben = new ArrayList<Posten>(); //Initialisieren der Ausgaben als Array mit Posten
		try {
			
		
			// Zeilenweises Einlesen der Daten
			CSVReader reader = new CSVReader(new FileReader("data/ausgaben.csv"));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN); //Erzeugen eines Parse Objekts
				Date datum = df.parse(nextLine[0]); //Einlesen Datum und Parsing als Datum
				String bezeichnung = nextLine[1]; //Einlesen der Bezeichnung
				double betrag = Double.parseDouble(nextLine[2]); //Einlesen des Betrags
				ausgaben.add(new Posten(datum, bezeichnung, betrag)); //Posten dem Ausgabenarray Hinzufügen
			}
			reader.close();

		} catch (FileNotFoundException e) {
			System.err
					.println("Die Datei data/budget.csv wurde nicht gefunden!");
			System.exit(1);
		} catch (IOException e) {
			System.err
					.println("Probleme beim Oeffnen der Datei data/budget.csv!");
			System.exit(1);
		} catch (ParseException e) {
			System.err
					.println("Formatfehler: Die Datei konnte nicht eingelesen werden!");
			System.exit(1);
		}
	}
}*/
