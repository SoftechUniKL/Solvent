import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MonatsuebersichtGUI extends JFrame {

	private JPanel contentPane;
	private final JButton btnSparen = new JButton("Sparen");
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonatsuebersichtGUI frame = new MonatsuebersichtGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MonatsuebersichtGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//Menüband am oberen Bildschirm
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		setJMenuBar(menuBar);
		
		JButton btnAusgaben = new JButton("Ausgaben");
		btnAusgaben.setBackground(Color.GRAY);
		menuBar.add(btnAusgaben);
		
		JButton btnEinnahmen = new JButton("Einnahmen");
		btnEinnahmen.setBackground(Color.GRAY);
		menuBar.add(btnEinnahmen);
		
		JButton btnTbersicht = new JButton("T-\u00DCbersicht");
		btnTbersicht.setBackground(Color.GRAY);
		menuBar.add(btnTbersicht);
		
		JButton btnMonatsbersicht = new JButton("Monats\u00FCbersicht");
		btnMonatsbersicht.setBackground(Color.GRAY);
		menuBar.add(btnMonatsbersicht);
		
		JButton btnPlanung = new JButton("Planung");
		btnPlanung.setBackground(Color.GRAY);
		menuBar.add(btnPlanung);
		btnSparen.setBackground(Color.GRAY);
		menuBar.add(btnSparen);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Dem Benutzer die Möglichkeit hoch bzw runter zu scrollen
		JScrollBar scrollBar = new JScrollBar();
		contentPane.add(scrollBar, BorderLayout.EAST);
		
		//Überschrift
		JLabel lblMonatsbersicht = new JLabel("Monats\u00FCbersicht");
		lblMonatsbersicht.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMonatsbersicht, BorderLayout.NORTH);
		
		//Tabelle mit einer Monatsübersicht
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Monat", "Saldo"},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		contentPane.add(table, BorderLayout.WEST);
		
		//Graphische Darstellung
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		JFreeChart chart = ChartFactory.createLineChart("Übersicht", "Monat", "Ausgaben", dataset);
		ChartPanel chartPanel = new ChartPanel(chart );
		getContentPane().add(chartPanel, BorderLayout.CENTER);
		chartPanel.setBackground(Color.LIGHT_GRAY);

	}
	

}

