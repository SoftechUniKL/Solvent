import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.opencsv.CSVReader;

public class MonatsuebersichtGUI extends JFrame {

	private JTable Tabelle;	
	private JMenuBar menuBar;
	private JPanel contentPane;
	
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
		setBounds(100, 100, 700, 700);

		// Men�band am oberen Bildschirm
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		setJMenuBar(menuBar);

		JButton btnStart = new JButton("Start");
		btnStart.setBackground(Color.GRAY);
		menuBar.add(btnStart);
		
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

		JButton btnSparen = new JButton("Sparen");
		btnSparen.setBackground(Color.GRAY);
		menuBar.add(btnSparen);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		// Dem Benutzer die M�glichkeit hoch bzw runter zu scrollen
		JScrollBar scrollBar = new JScrollBar();
		contentPane.add(scrollBar, BorderLayout.EAST);

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �berschrift
				JLabel lblMonatsbersicht = new JLabel("Monats\u00FCbersicht");
				lblMonatsbersicht.setHorizontalAlignment(SwingConstants.CENTER);
				contentPane.add(lblMonatsbersicht, BorderLayout.NORTH);

				JLabel lblNewLabel = new JLabel(
						"Ihre \u00DCbersicht f\u00FCr diesen Monat");
				JLabel lblNewLabel_1 = new JLabel("Einnahmen:");
				JLabel lblNewLabel_2 = new JLabel("Ausgaben:");

				JButton btnSparziel = new JButton(
						"Neues Sparziel/Schulden hinzuf�gen");
				JButton btnHinzufuegen = new JButton(
						"Neue Buchung hinzuf\u00FCgen");
				JLabel lblHierEinnahmenEinfgen = new JLabel(
						"Hier Einnahmen einf\u00FCgen");
				JLabel lblHierAusgabenEinfgen = new JLabel(
						"Hier Ausgaben einf\u00FCgen");
				JLabel lblRestbudget = new JLabel("Verbleibendes Budget:");
				JLabel lblRestbudgetEinfgen = new JLabel(
						"Restbudget einf\u00FCgen");

				   //Modifiziert das Fenster "neue Buchung" und gibt Positionen der Buttons an 
	    		GroupLayout gl_contentPane = new GroupLayout(contentPane);
	    		gl_contentPane.setHorizontalGroup(
	    			gl_contentPane.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_contentPane.createSequentialGroup()
	    							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    								.addComponent(lblRestbudget)
	    								.addComponent(lblNewLabel_1)
	    								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
	    							.addPreferredGap(ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
	    							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    									.addComponent(lblRestbudgetEinfgen)
	    									.addGroup(gl_contentPane.createSequentialGroup()
	    										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    											.addComponent(lblHierEinnahmenEinfgen)
	    											.addComponent(lblHierAusgabenEinfgen))
	    										.addGap(184)))
	    								.addContainerGap(191, Short.MAX_VALUE))
	    							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
	    							.addGroup(gl_contentPane.createSequentialGroup()
	    								.addComponent(btnHinzufuegen)
	    								.addGap(45)
	    								.addComponent(btnSparziel)
	    								.addContainerGap())))
	    		);
	    		gl_contentPane.setVerticalGroup(
	    			gl_contentPane.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
	    					.addGap(14)
	    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    						.addComponent(lblNewLabel_1)
	    						.addComponent(lblHierEinnahmenEinfgen))
	    					.addGap(24)
	    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    						.addComponent(lblNewLabel_2)
	    						.addComponent(lblHierAusgabenEinfgen))
	    					.addGap(33)
	    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    						.addComponent(lblRestbudget)
	    						.addComponent(lblRestbudgetEinfgen))
	    					.addGap(22)
	    					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    						.addComponent(btnHinzufuegen)
	    						.addComponent(btnSparziel))
	    					.addContainerGap(20, Short.MAX_VALUE))
	    			);
	    		contentPane.setLayout(gl_contentPane);
	    		contentPane.removeAll();
	    	

				btnHinzufuegen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Eingabe obj = new Eingabe();
						obj.setVisible(true);

					}
				});

				btnSparziel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Sparziel obj = new Sparziel();
						obj.setVisible(true);
					}
				});
			}
		});

		
		btnTbersicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				System.out.println("Ich funktioniere!");
				// Tabelle mit einer Monats�bersicht
				JTable table;
				table = new JTable();
				table.setVisible(true);
				table.setBackground(Color.BLUE);
				table.setModel(new DefaultTableModel(new Object[][] {
						{ "Monat", "Saldo" }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, }, new String[] {
						"New column", "New column" }));
				contentPane.add(table, BorderLayout.WEST);

				// Graphische Darstellung
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				JFreeChart chart = ChartFactory.createLineChart("�bersicht","Monat", "Ausgaben", dataset);
				ChartPanel chartPanel = new ChartPanel(chart);
				getContentPane().add(chartPanel, BorderLayout.CENTER);
				chartPanel.setBackground(Color.BLUE);
				
				
			}
		});
		btnAusgaben.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Test");
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				JLabel lblUebersicht = new JLabel("\u00DCbersicht ihrer Schulden und Sparziele");
			
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportBorder(null);
				scrollPane.setBorder(null);
				     
				
				
		        //Modifiziert das Fenster "neue Buchung" und gibt Positionen der Buttons an 
				
				
				ArrayList<Posten> ausgaben = CSVReader("data/ausgaben.csv");
				
				Object[][] data = new Object[ausgaben.size()][3];
				int i = 0;
				for (Posten p : ausgaben) {
					data[i][0] = new SimpleDateFormat("dd/MM/yyyy")
							.format(p.getDatum());
					data[i][1] = p.getBezeichnung();
					data[i][2] = String.format("%.2f", p.getBetrag());
					i++;
				}
				
			JTable table = new JTable(data, new Object[] { "Datum", "Bezeichnung",
				"Betrag" });
			JScrollPane scrollpane = new JScrollPane(table);

			// Kreisdiagramm
			DefaultPieDataset pd = new DefaultPieDataset();
			for (Posten p : ausgaben) {
				pd.setValue(p.getBezeichnung(), p.getBetrag());
			}
			JFreeChart pie = ChartFactory.createPieChart("Ausgaben", pd);
			ChartPanel panel = new ChartPanel(pie);

	
			// Elemente dem Fenster hinzufuegen:
			//getContentPane().add(scrollpane);
			//getContentPane().add(panel);
			// Berechnet Layout mit geringstem Platzbedarf
			//pack();
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup()
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblUebersicht)
					.addGroup(gl_contentPane.createParallelGroup())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING))
							.addComponent(scrollpane))
							.addComponent(panel)

						.addContainerGap(39, Short.MAX_VALUE))
			);
		}

			});
		
		btnEinnahmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Test");
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				JLabel lblUebersicht = new JLabel("\u00DCbersicht ihrer Schulden und Sparziele");
			
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportBorder(null);
				scrollPane.setBorder(null);
				     
				
				
		        //Modifiziert das Fenster "neue Buchung" und gibt Positionen der Buttons an 
				
				
				ArrayList<Posten> ausgaben = CSVReader("data/einnahmen.csv");
				
				Object[][] data = new Object[ausgaben.size()][3];
				int i = 0;
				for (Posten p : ausgaben) {
					data[i][0] = new SimpleDateFormat("dd/MM/yyyy")
							.format(p.getDatum());
					data[i][1] = p.getBezeichnung();
					data[i][2] = String.format("%.2f", p.getBetrag());
					i++;
				}
				
			JTable table = new JTable(data, new Object[] { "Datum", "Bezeichnung",
				"Betrag" });
			JScrollPane scrollpane = new JScrollPane(table);

			// Kreisdiagramm
			DefaultPieDataset pd = new DefaultPieDataset();
			for (Posten p : ausgaben) {
				pd.setValue(p.getBezeichnung(), p.getBetrag());
			}
			JFreeChart pie = ChartFactory.createPieChart("Ausgaben", pd);
			ChartPanel panel = new ChartPanel(pie);

	
			// Elemente dem Fenster hinzufuegen:
			//getContentPane().add(scrollpane);
			//getContentPane().add(panel);
			// Berechnet Layout mit geringstem Platzbedarf
			//pack();
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup()
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblUebersicht)
					.addGroup(gl_contentPane.createParallelGroup())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING))
							.addComponent(scrollpane))
							.addComponent(panel)

						.addContainerGap(39, Short.MAX_VALUE))
			);
		}

			});
		
		
		
		btnSparen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Test");
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				JLabel lblUebersicht = new JLabel("\u00DCbersicht ihrer Ausgaben");
			
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportBorder(null);
				scrollPane.setBorder(null);
				     
				
				
		        //Modifiziert das Fenster "neue Buchung" und gibt Positionen der Buttons an 
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUebersicht)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(39, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblUebersicht, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(149))
				);
				
				Tabelle = new JTable();
				scrollPane.setViewportView(Tabelle);
				Tabelle.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null},
						{null, null, null, null, null, null},
						{null, null, null, null, null, null},
						{null, null, null, null, null, null},
						{null, null, null, null, null, null},
					},
					new String[] {"Bezeichnung", "Kategorie", "Startdatum", "Zieldatum", "Betrag", "Bereits erreicht"}
				));
				Tabelle.getColumnModel().getColumn(0).setPreferredWidth(141);
				Tabelle.getColumnModel().getColumn(1).setPreferredWidth(83);
				Tabelle.getColumnModel().getColumn(5).setPreferredWidth(93);
				Tabelle.setToolTipText("");
				contentPane.setLayout(gl_contentPane);
			}
				
			
		});
		
		btnStart.doClick();
	}
	
	public ArrayList<Posten> CSVReader(String filename) {
		ArrayList<Posten> ausgaben = new ArrayList<Posten>(); //Initialisieren der Ausgaben als Array mit Posten
		try {
			
		
			// Zeilenweises Einlesen der Daten
			CSVReader reader = new CSVReader(new FileReader(filename));
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
	return ausgaben;
	}
		
}
