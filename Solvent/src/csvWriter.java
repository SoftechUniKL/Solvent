import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class csvWriter {
	int i = 0;
	public static void main(String[] Args){
		csvWriter test = new csvWriter();
		test.writer();
		System.out.println("Ich funktioniere!");
	}
	public void writer() { //Hier Übergabe der Eingaben... Posten aus Eingabe erstellen, oder erst beim Einlesen?
		
		FileWriter fw;
		try {
			fw = new FileWriter("data/ausgaben.csv",true);
		
			
			BufferedWriter bw = new BufferedWriter(fw);
			String test = "13.12.2015,Testausgabe"+i+",299.90\n";
			bw.write(test);
			i++;
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}