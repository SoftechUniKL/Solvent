import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class csvWriter {
	public void writer() {

		FileWriter fw;
		try {
			fw = new FileWriter("data/testbudget.csv");

			BufferedWriter bw = new BufferedWriter(fw);
			String test = "13.12.2015,Testausgabe, 299.90";
			bw.write(test);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}