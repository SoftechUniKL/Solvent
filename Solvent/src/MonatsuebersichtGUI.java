import java.awt.BorderLayout;
import java.awt.Color;
//github.com/SoftechUniKL/Solvent
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//github.com/SoftechUniKL/Solvent
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
//github.com/SoftechUniKL/Solvent
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.opencsv.CSVReader;

public class MonatsuebersichtGUI extends JFrame {
	private JMenuBar menuBar;
	private JPanel contentPane;
	private Sparziel sparziel;
	private Eingabe eingabe;
	private JButton btnSparen;
	static JButton btnStart ;
	private static JButton btnEinnahmen;
	private static JButton btnAusgaben;
	
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
	 * Create the frame
	 */
	public MonatsuebersichtGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 700);
		/**
		 * Add Buttons
		 */
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		setJMenuBar(menuBar);

		btnStart = new JButton("Start");
		btnStart.setBackground(Color.GRAY);
		menuBar.add(btnStart);
		
		btnAusgaben = new JButton("Ausgaben");
		btnAusgaben.setBackground(Color.GRAY);
		menuBar.add(btnAusgaben);

		btnEinnahmen = new JButton("Einnahmen");
		btnEinnahmen.setBackground(Color.GRAY);
		menuBar.add(btnEinnahmen);

		JButton btnTbersicht = new JButton("Monatsuebersicht");
		btnTbersicht.setBackground(Color.GRAY);
		menuBar.add(btnTbersicht);

		JButton btnMonatsbersicht = new JButton("Jahresuebersicht");
		btnMonatsbersicht.setBackground(Color.GRAY);
		menuBar.add(btnMonatsbersicht);

		JButton btnPlanung = new JButton("Planung");
		btnPlanung.setBackground(Color.GRAY);
		menuBar.add(btnPlanung);

		btnSparen = new JButton("Sparen");
		btnSparen.setBackground(Color.GRAY);
		menuBar.add(btnSparen);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/**
		 * Add ActionListener for Button Start
		 */
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Creates Labels and Buttons
				 */
				JButton btnSparziel = new JButton("Neues Sparziel/Schulden hinzufuegen");
				JButton btnHinzufuegen = new JButton("Neue Buchung hinzufuegen");
				JLabel lblMonatsbersicht = new JLabel("Monatsuebersicht");
				JLabel lblNewLabel_1 = new JLabel("Einnahmen:");
				JLabel lblNewLabel_2 = new JLabel("Ausgaben:");
				JLabel lblHierEinnahmenEinfgen = new JLabel(funktion ("einnahmen"));
				JLabel lblHierAusgabenEinfgen = new JLabel(funktion ("ausgaben"));
				JLabel lblRestbudget = new JLabel("Verbleibendes Budget:");
				JLabel lblRestbudgetEinfgen = new JLabel(String.valueOf(Double.parseDouble(funktion("einnahmen"))-Double.parseDouble(funktion("ausgaben"))));
				
				String[] months = {"Januar","Februar","M�rz","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
				Date date = java.util.Calendar.getInstance().getTime();
				SimpleDateFormat dateFormatter = new SimpleDateFormat("MM");
				int dateToday = Integer.parseInt(dateFormatter.format(date));
				JLabel lblNewLabel = new JLabel("Ihre Uebersicht fuer den Monat " + months[dateToday-1]);

				/**
				 * Detremines Layout of the GUI components
				 */
				lblMonatsbersicht.setHorizontalAlignment(SwingConstants.CENTER);
				contentPane.add(lblMonatsbersicht, BorderLayout.NORTH);GroupLayout gl_contentPane = new GroupLayout(contentPane);
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
	    		
	    		/**
	    		 * Creates ActionListener for the Buttons "Buchung hinzuf�gen" & "Sparziel hinzuf�gen"
	    		 */
				btnHinzufuegen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eingabe = new Eingabe();
						eingabe.setVisible(true);
					}
				});
				btnSparziel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sparziel = new Sparziel();
						sparziel.setVisible(true);
					}
				});	
			}
		});

		/**
		 * Add ActionListener for Button Ausgaben
		 */
		btnAusgaben.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Commits filecontents to "data"-array
				 */
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
				/**
				 * Creates basic GUI components
				 */
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				JLabel lblUebersicht = new JLabel("Uebersicht ihrer Ausgaben");
				JLabel lblLschen = new JLabel("Um einen Eintrag zu entfernen, klicken Sie bitte auf den entsprechenden Eintrag in der Tabelle");
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportBorder(null);
				scrollPane.setBorder(null);
				setContentPane(contentPane);
				
				/**
				 * Creates table
				 */
				JTable table = new JTable(data, new Object[] { "Datum", "Bezeichnung","Betrag" });
				table.setPreferredSize(new Dimension(300,500));
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				
				/**
				 * Creates piechart
				 */
				DefaultPieDataset pd = new DefaultPieDataset();
				for (Posten p : ausgaben) {
					pd.setValue(p.getBezeichnung(), p.getBetrag());
					}
				JFreeChart pie = ChartFactory.createPieChart("Ausgaben", pd);
				ChartPanel piepanel = new ChartPanel(pie);
				piepanel.setPreferredSize(new Dimension(500,500));
			
				
				
				
				JScrollPane scrollpane = new JScrollPane(table);
				scrollpane.setBorder(BorderFactory.createEmptyBorder());
				loeschen("einnahmen", btnEinnahmen, table);
	
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				gl_contentPane.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblUebersicht, GroupLayout.PREFERRED_SIZE, 690, GroupLayout.PREFERRED_SIZE)
												.addContainerGap(696, Short.MAX_VALUE))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(scrollPane)
														.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
														.addComponent(piepanel)
														.addGap(20))))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblLschen, GroupLayout.PREFERRED_SIZE, 690, GroupLayout.PREFERRED_SIZE)
																.addContainerGap(696, Short.MAX_VALUE))
						);
				gl_contentPane.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(20)
								.addComponent(lblUebersicht)
								.addGap(20)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPane)
										.addComponent(piepanel))
										.addGap(20)
										.addComponent(lblLschen)
						.addGap(20))
						);
				scrollPane.setViewportView(table);
				contentPane.setLayout(gl_contentPane);
				contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblUebersicht}));
			}
			
			
		

			});
		
		btnEinnahmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			JLabel lblUebersicht = new JLabel("Uebersicht ihrer Einnahmen");
		
			JTable table = new JTable();
			ArrayList<Posten> einnahmen = CSVReader("data/einnahmen.csv");
				
			Object[][] data = new Object[einnahmen.size()][3];
			int i = 0;
			for (Posten p : einnahmen) {
				data[i][0] = new SimpleDateFormat("dd/MM/yyyy")
							.format(p.getDatum());
				data[i][1] = p.getBezeichnung();
				data[i][2] = String.format("%.2f", p.getBetrag());
				i++;
				
			}
			table.setModel(new DefaultTableModel(data,new String[] {"Datum", "Bezeichnung","Betrag"}));
			
			table.setEditingColumn(0);
			table.setRowSelectionAllowed(true);
			
			
			JScrollPane scrollpane = new JScrollPane();
			scrollpane.setEnabled(true);
			scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollpane.setViewportView(table);
			table.setPreferredSize(new Dimension(300,500));
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		
			
			// Kreisdiagramm
			DefaultPieDataset pd = new DefaultPieDataset();
			for (Posten p : einnahmen) {
				pd.setValue(p.getBezeichnung(), p.getBetrag());
			}
			JFreeChart pie = ChartFactory.createPieChart("Einnahmen", pd);
			ChartPanel piepanel = new ChartPanel(pie);
			piepanel.setPreferredSize(new Dimension(500,500));
			
			JLabel lblLschen = new JLabel("Um einen Eintrag zu entfernen, klicken Sie bitte auf den entsprechenden Eintrag in der Tabelle");
			
			loeschen("einnahmen", btnEinnahmen, table);
	
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblUebersicht, GroupLayout.PREFERRED_SIZE, 690, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(696, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollpane)
								.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
								.addComponent(piepanel)
								.addGap(20))))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblLschen, GroupLayout.PREFERRED_SIZE, 690, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(696, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(20)
						.addComponent(lblUebersicht)
						.addGap(20)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollpane)
							.addComponent(piepanel))
						.addGap(20)
						.addComponent(lblLschen)
						.addGap(20))
			);
			scrollpane.setViewportView(table);
			contentPane.setLayout(gl_contentPane);
			contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblUebersicht}));
		}
	
			
		

			});
		
		
		/**
		 * Fuegt dem Button Sparen eine Aktion beim Klicken hinzu
		 */
		btnSparen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sparziel = new Sparziel();
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 20, 5));
				setContentPane(contentPane);

				JLabel lblRestbudget = new JLabel("Uebersicht ihrer Schulden und Sparziele");
				lblRestbudget.setVerticalAlignment(SwingConstants.TOP);
				
				JLabel lblLoeschen = new JLabel("Um einen Eintrag zu entfernen, klicken Sie bitte auf den entsprechenden Eintrag in der Tabelle");
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setEnabled(true);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				

				String[][] table_array = Sparziel.readCSV("sparen");
				for(int i = 0; i<table_array.length;i++){
					table_array[i][5] = Sparziel.erreicht(i).toString();
					}
				
				JTable Tabelle = new JTable();
				scrollPane.setViewportView(Tabelle);
				Tabelle.setEditingColumn(0);

				Tabelle.setRowSelectionAllowed(true);
				Tabelle.setModel(new DefaultTableModel(
						table_array,
					new String[] {"Bezeichnung", "Kategorie", "Startdatum", "Zieldatum", "Betrag", "Bereits erreicht"}
				));
				
				for (int c = 0; c < Tabelle.getColumnCount(); c++)
				{
				    Class<?> col_class = Tabelle.getColumnClass(c);
				    Tabelle.setDefaultEditor(col_class, null);        // remove editor
				}
				
				Tabelle.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int eingabe = JOptionPane.showConfirmDialog(null,"Wollen Sie den ausgew�hlten Eintrag l�schen?","",JOptionPane.YES_NO_OPTION);
						if (eingabe == JOptionPane.YES_OPTION){
						try{
							BufferedWriter bw1 = new BufferedWriter(new FileWriter("data/sparen.csv"));
							bw1.write("");
							bw1.close();
						}catch (Exception z) {
						    z.printStackTrace();
						    System.out.println("Problem beim �berschreiben des Arrays_1");
						}
						for (int i = 0; i<=Tabelle.getRowCount(); i++){
							if (i != Tabelle.getSelectedRow()){
								try{
									FileWriter fw= new FileWriter("data/sparen.csv",true);
									BufferedWriter bw = new BufferedWriter(fw);
									String zeile = Tabelle.getValueAt(i, 0) + "," + Tabelle.getValueAt(i,1) + "," + Tabelle.getValueAt(i,2) + "," +Tabelle.getValueAt(i,3) + "," +Tabelle.getValueAt(i,4) + "\n";
									bw.write(zeile);
									bw.close();
									fw.close();
								}catch (Exception z) {
								     System.out.println("Problem beim �berschreiben des Arrays_2");
								}
							}
						}
						JOptionPane.getRootFrame().dispose();
						btnSparen.doClick();
						}
						else if (eingabe == JOptionPane.NO_OPTION){
							JOptionPane.getRootFrame().dispose();
						}
						
					}
				});
				
			
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				Tabelle.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
				Tabelle.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				Tabelle.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				Tabelle.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
				Tabelle.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				
				Tabelle.getColumnModel().getColumn(0).setPreferredWidth(80);
				Tabelle.getColumnModel().getColumn(1).setPreferredWidth(60);
				Tabelle.getColumnModel().getColumn(2).setPreferredWidth(60);
				Tabelle.getColumnModel().getColumn(3).setPreferredWidth(60);
				Tabelle.getColumnModel().getColumn(4).setPreferredWidth(50);
				Tabelle.getColumnModel().getColumn(5).setPreferredWidth(50);
				Tabelle.setToolTipText("");
				contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblRestbudget, scrollPane, Tabelle}));
				
				
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRestbudget)
								.addComponent(lblLoeschen))
							.addContainerGap(252, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(lblRestbudget)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblLoeschen)
							.addContainerGap(366, Short.MAX_VALUE))
				);
				contentPane.setLayout(gl_contentPane);
			}	
			
		});
		btnStart.doClick();
	}
	
	public static ArrayList<Posten> CSVReader(String filename) {
		ArrayList<Posten> file_as_array = new ArrayList<Posten>(); //Initialisieren der Ausgaben als Array mit Posten
		try {
			// Zeilenweises Einlesen der Daten
			CSVReader reader = new CSVReader(new FileReader(filename));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				
				//DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN); //Erzeugen eines Parse Objekts
				Date datum = df.parse(nextLine[0]); //Einlesen Datum und Parsing als Datum
				String bezeichnung = nextLine[1]; //Einlesen der Bezeichnung
				double betrag = Double.parseDouble(nextLine[2]); //Einlesen des Betrags
				file_as_array.add(new Posten(datum, bezeichnung, betrag)); //Posten dem Ausgabenarray Hinzufuegen
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Die Datei wurde nicht gefunden!");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Probleme beim Oeffnen der Datei!");
			System.exit(1);
		} catch (ParseException e) {
			System.err.println("Die Datei konnte nicht eingelesen werden!");
			System.exit(1);
		}
	return file_as_array;
	}
	
	public static String funktion (String filename){
		int number_of_rows = 0;
		Date date = java.util.Calendar.getInstance().getTime();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM");
		String dateToday = dateFormatter.format(date);
		try {
	        java.io.BufferedReader FileReader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("data/"+filename+".csv")));
	        String zeile="";
	        while(null!=(zeile=FileReader.readLine())){         
	        	number_of_rows++; 
	        }
	        FileReader.close();    
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Groesse des Arrays kann nicht festgelegt werden ");
	    }
		
		String[][] file_as_array = new String[number_of_rows][4];
	    try {
	        java.io.BufferedReader FileReader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File("data/"+filename+".csv")));
	        String zeile="";
	        int i = 0;
	        while(null!=(zeile=FileReader.readLine())){         
	            String[] split=zeile.split(",");
	            
	            for(int j = 0; j<split.length;j++){
	            	file_as_array[i][j] = split[j];
	            }
	            i++;  
	        }
	        FileReader.close();
	        double gesamt = 0.0;
	        
	        for (int k = 0; k<file_as_array.length;k++ ){
	        	if (Double.parseDouble(file_as_array[k][0].substring(3,5)) == Double.parseDouble(dateToday)){
	        		gesamt = gesamt + Double.parseDouble(file_as_array[k][2]);
	        	}
	        }
	        return String.valueOf(gesamt);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Daten koennen nicht aufgerufen werden");
	    }
	    return null;
	}
	
	
	public static String loeschen (String filename, JButton btnname, JTable table){
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int eingabe = JOptionPane.showConfirmDialog(null,"Wollen Sie den ausgew�hlten Eintrag l�schen?","",JOptionPane.YES_NO_OPTION);
				
				if (eingabe == JOptionPane.YES_OPTION){
				try{
					BufferedWriter bw1 = new BufferedWriter(new FileWriter("data/"+filename+".csv"));
					bw1.write("");
					bw1.close();
				}catch (Exception z) {
				    z.printStackTrace();
				    System.out.println("Problem beim �berschreiben des Arrays_1");
				}
				for (int i = 0; i<=table.getRowCount(); i++){
					if (i != table.getSelectedRow()){
						try{
							FileWriter fw= new FileWriter("data/"+filename+".csv",true);
							BufferedWriter bw = new BufferedWriter(fw);
							String zeile = table.getValueAt(i, 0) + "," + table.getValueAt(i,1) + "," + table.getValueAt(i,2) + "\n";
							bw.write(zeile);
							bw.close();
							fw.close();
						}catch (Exception z) {
						}
					}
				}
				JOptionPane.getRootFrame().dispose();
				btnname.doClick();
				}
				else if (eingabe == JOptionPane.NO_OPTION){
					JOptionPane.getRootFrame().dispose();
				}
				
			}
		});
		return null;
	}
}