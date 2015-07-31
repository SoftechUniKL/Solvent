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
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//github.com/SoftechUniKL/Solvent
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import com.opencsv.CSVReader;

public class MonatsuebersichtGUI extends JFrame {
	private JMenuBar menuBar;
	private JPanel contentPane;
	private Sparziel sparziel;
	private Eingabe eingabe;
	private JButton btnSparen;
	static JButton btnStart;
	private JButton btnUebersicht;
	private static JButton btnEinnahmen;
	private static JButton btnAusgaben;
	static JTable Tabelle = new JTable();
	private BufferedImage image;

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
		setBounds(500, 200, 750, 600);
		setTitle("Solvent");
		
		/**
		 * Add Buttons
		 */
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.cyan);
		menuBar.setOpaque(true);
		setJMenuBar(menuBar);
		
		btnStart = new JButton("Start");
		btnStart.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnStart);
		
		btnAusgaben = new JButton("Ausgaben");
		btnAusgaben.setBackground(Color.cyan);
		menuBar.add(btnAusgaben);

		btnEinnahmen = new JButton("Einnahmen");
		btnEinnahmen.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnEinnahmen);

		btnSparen = new JButton("Sparen");
		btnSparen.setBackground(Color.cyan);
		menuBar.add(btnSparen);
		
		btnUebersicht = new JButton ("Uebersicht");
		btnUebersicht.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnUebersicht);

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
				Date date = java.util.Calendar.getInstance().getTime();
				SimpleDateFormat dateFormatter = new SimpleDateFormat("MM");
				int date_int = Integer.parseInt(dateFormatter.format(date));
				JLabel lblHierEinnahmenEinfgen = new JLabel(gesamt_monat ("einnahmen", date_int)+" \u20ac");
				JLabel lblHierAusgabenEinfgen = new JLabel(gesamt_monat ("ausgaben",date_int)+" \u20ac");
				JLabel lblRestbudget = new JLabel("Verbleibendes Budget:");
			
				
				JLabel lblRestbudgetEinfgen = new JLabel(" "+ String.valueOf(Double.parseDouble(gesamt_monat("einnahmen",date_int))-Double.parseDouble(gesamt_monat("ausgaben",date_int)))+" \u20ac");
				lblRestbudgetEinfgen.setOpaque(true);
				if(Double.parseDouble(gesamt_monat("einnahmen", date_int))-Double.parseDouble(gesamt_monat("ausgaben", date_int))<0){
					lblRestbudgetEinfgen.setBackground(Color.RED);
				}
				else {
				lblRestbudgetEinfgen.setBackground(Color.GREEN);
				}
				
				String[] months = {"Januar","Februar","Maerz","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
				JLabel lblNewLabel = new JLabel("Ihre Uebersicht fuer den Monat " + months[date_int-1]);
				/**
				 * Determines Layout of the GUI components
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
	    		contentPane.setBackground(Color.LIGHT_GRAY);
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
					data[i][0] = new SimpleDateFormat("dd/MM/yyyy").format(p.getDatum());
					data[i][1] = p.getBezeichnung();
					data[i][2] = String.format("%.2f", p.getBetrag());
					i++;
				}
				/**
				 * Creates basic GUI components
				 */
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				JLabel lblUebersicht = new JLabel("Uebersicht ihrer gesamten Ausgaben");
				JLabel lblLschen = new JLabel("Zum Loeschen eines Eintrags, klicken Sie bitte auf den entsprechenden Eintrag in der Tabelle");
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportBorder(null);
				scrollPane.setBorder(null);
				scrollPane.setBorder(BorderFactory.createEmptyBorder());
				setContentPane(contentPane);
				
				/**
				 * Creates table
				 */
				JTable table = new JTable(data, new Object[] { "Datum", "Bezeichnung","Betrag (\u20ac)" });
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
			
				/**
				 * Invokes the funciont loeschen
				 */
				loeschen("ausgaben", btnAusgaben, table);
				
				/**
				 * Determines Layout of the GUI components
				 */
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
																.addContainerGap(696, Short.MAX_VALUE)));
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
						.addGap(20)));
				scrollPane.setViewportView(table);
				contentPane.setLayout(gl_contentPane);
				contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblUebersicht}));
			}
			});
		
		/**
		 * Add ActionListener for Button Einnahmen
		 */
		btnEinnahmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Commits filecontents to "data"-array
				 */
				ArrayList<Posten> einnahmen = CSVReader("data/einnahmen.csv");
				Object[][] data = new Object[einnahmen.size()][3];
				int i = 0;
				for (Posten p : einnahmen) {
					data[i][0] = new SimpleDateFormat("dd/MM/yyyy").format(p.getDatum());
					data[i][1] = p.getBezeichnung();
					data[i][2] = String.format("%.2f", p.getBetrag());
					i++;
				}
				/**
				 * Creates basic GUI components
				 */
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				JLabel lblUebersicht = new JLabel("Uebersicht ihrer gesamten Einnahmen");
				JLabel lblLschen = new JLabel("Zum Loeschen eines Eintrags, klicken Sie bitte auf den entsprechenden Eintrag in der Tabelle");
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportBorder(null);
				scrollPane.setBorder(null);
				scrollPane.setBorder(BorderFactory.createEmptyBorder());
				setContentPane(contentPane);
				
				/**
				 * Creates table
				 */
				JTable table = new JTable(data, new Object[] { "Datum", "Bezeichnung","Betrag (\u20ac)" });
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
				for (Posten p : einnahmen) {
					pd.setValue(p.getBezeichnung(), p.getBetrag());
					}
				JFreeChart pie = ChartFactory.createPieChart("Einnahmen", pd);
				ChartPanel piepanel = new ChartPanel(pie);
				piepanel.setPreferredSize(new Dimension(500,500));
			
				/**
				 * Invokes the funciont loeschen
				 */
				loeschen("einnahmen", btnEinnahmen, table);
				
				/**
				 * Determines Layout of the GUI components
				 */
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
		
		/**
		 * Add ActionListener for Button Sparen
		 */
		btnSparen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Commits filecontents to "data"-array
				 */
				String[][] table_array = Sparziel.readCSV("sparen");
				for(int i = 0; i<table_array.length;i++){
					table_array[i][5] = Sparziel.erreicht(i).toString();
					}
				/**
				 * Creates basic GUI components
				 */
				sparziel = new Sparziel();
				contentPane = new JPanel();
				JLabel lblRestbudget = new JLabel("Uebersicht ihrer Schulden und Sparziele");
				JLabel lblLoeschen = new JLabel("Zum Loeschen eines Eintrags, klicken Sie bitte auf den entsprechenden Eintrag in der Tabelle");
				JScrollPane scrollPane = new JScrollPane();
				lblRestbudget.setVerticalAlignment(SwingConstants.TOP);
				scrollPane.setEnabled(true);
				scrollPane.setViewportView(Tabelle);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				contentPane.setBorder(new EmptyBorder(5, 5, 20, 5));
				setContentPane(contentPane);
				
				/**
				 * Modifies table "Tabelle"
				 */
				Tabelle.setEditingColumn(0);
				Tabelle.setRowSelectionAllowed(true);
				Tabelle.setModel(new DefaultTableModel(table_array,new String[] {"Bezeichnung", "Kategorie", "Startdatum", "Zieldatum", "Betrag (\u20ac)", "Bereits erreicht (\u20ac)", "Prozent (%)"}));
				for (int c = 0; c < Tabelle.getColumnCount(); c++){
				    Class<?> col_class = Tabelle.getColumnClass(c);
				    Tabelle.setDefaultEditor(col_class, null);        
					}
				for (int i = 0; i< Tabelle.getRowCount(); i++){
					DecimalFormat f = new DecimalFormat("#0.00");					
					Double err = Double.parseDouble((String) Tabelle.getValueAt(i, 5));
					Double betr = Double.parseDouble((String)Tabelle.getValueAt(i, 4));
					String value = f.format((100*(err/betr)));
					Tabelle.setValueAt(value, i, 6);
				}
				
				/**
				 * Adds MouseListener for a selected row of the table "Tabelle"
				 */
				Tabelle.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int eingabe = JOptionPane.showConfirmDialog(null,"Wollen Sie den ausgewaehlten Eintrag loeschen?","",JOptionPane.YES_NO_OPTION);
						/**
						 * If the Yes-Button has been clicked, the following procedures are performed
						 */
						if (eingabe == JOptionPane.YES_OPTION){
						/**
						 * Deletes every entry of sparen.csv 
						 */
						try{
							BufferedWriter bw_1 = new BufferedWriter(new FileWriter("data/sparen.csv"));
							bw_1.write("");
							bw_1.close();
						}
						catch (Exception z) {
						    z.printStackTrace();
						    System.out.println("Problem beim Loeschen des Inhaltes der CSV-Datei");
						}
						/**
						 * Writes each entry of the table, except the deleted one, in sparen.csv
						 */
						for (int i = 0; i<Tabelle.getRowCount(); i++){
							if (i != Tabelle.getSelectedRow()){
								try{
									FileWriter fw= new FileWriter("data/sparen.csv",true);
									BufferedWriter bw_2 = new BufferedWriter(fw);
									String zeile = Tabelle.getValueAt(i,0) + "," + Tabelle.getValueAt(i,1) + "," + Tabelle.getValueAt(i,2) + "," +Tabelle.getValueAt(i,3) + "," +Tabelle.getValueAt(i,4) + "\n";
									bw_2.write(zeile);
									bw_2.close();
									fw.close();
								}
								catch (Exception z) {
								     System.out.println("Problem beim Ueberschreiben des Arrays");
								}
							}
						}
						JOptionPane.getRootFrame().dispose();
						btnSparen.doClick();
						}
						/**
						 * If the No-Button has been clicked, the message window will be closed
						 */
						else if (eingabe == JOptionPane.NO_OPTION){
							JOptionPane.getRootFrame().dispose();
						}
					}
				});
				
				/**
				 * Determines Layout of the GUI components
				 */
				
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				Tabelle.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
				Tabelle.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
				Tabelle.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				Tabelle.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				Tabelle.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
				Tabelle.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				Tabelle.getColumnModel().getColumn(0).setPreferredWidth(80);
				Tabelle.getColumnModel().getColumn(1).setPreferredWidth(40);
				Tabelle.getColumnModel().getColumn(2).setPreferredWidth(60);
				Tabelle.getColumnModel().getColumn(3).setPreferredWidth(60);
				Tabelle.getColumnModel().getColumn(4).setPreferredWidth(40);
				Tabelle.getColumnModel().getColumn(5).setPreferredWidth(70);
				Tabelle.getColumnModel().getColumn(6).setPreferredWidth(20);
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
		
		/**
		 * Add ActionListener for Button Uebersicht
		 */
		btnUebersicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Creates basic GUI components
				 */
				XYDataset ds = createDataset();
                JFreeChart chart = ChartFactory.createXYLineChart("Uebersicht","Monat", "Betrag", ds, PlotOrientation.VERTICAL, true, true, false);
                ChartPanel chartPanel = new ChartPanel(chart);
                contentPane = new JPanel();
        		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        		setContentPane(contentPane);
        		JLabel lblIhreJahresbersicht = new JLabel("Ihre Jahresuebersicht");
        		GroupLayout gl_contentPane = new GroupLayout(contentPane);
        		gl_contentPane.setHorizontalGroup(
        			gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(chartPanel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblIhreJahresbersicht))
        					.addContainerGap(28, Short.MAX_VALUE))
        		);
        		gl_contentPane.setVerticalGroup(
        			gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblIhreJahresbersicht)
        					.addGap(18)
        					.addComponent(chartPanel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(23, Short.MAX_VALUE))
        		);
        		contentPane.setLayout(gl_contentPane);
			}
			/**
			 * Commits data from CSV-data to the JFreeChart
			 * @return
			 */
			private  XYDataset createDataset() {
				DefaultXYDataset ds = new DefaultXYDataset();      
				double[][] einn = { {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12} , {Double.parseDouble(gesamt_monat("einnahmen", 1)),Double.parseDouble(gesamt_monat("einnahmen", 2)),Double.parseDouble(gesamt_monat("einnahmen", 3)), Double.parseDouble(gesamt_monat("einnahmen", 4)),Double.parseDouble(gesamt_monat("einnahmen", 5)),Double.parseDouble(gesamt_monat("einnahmen", 6)), Double.parseDouble(gesamt_monat("einnahmen", 7)),Double.parseDouble(gesamt_monat("einnahmen", 8)),Double.parseDouble(gesamt_monat("einnahmen", 9)), Double.parseDouble(gesamt_monat("einnahmen", 10)),Double.parseDouble(gesamt_monat("einnahmen", 11)),Double.parseDouble(gesamt_monat("einnahmen", 12))} };
				double[][] ausg = { {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, {Double.parseDouble(gesamt_monat("ausgaben", 1)), Double.parseDouble(gesamt_monat("ausgaben", 2)),Double.parseDouble(gesamt_monat("ausgaben", 3)), Double.parseDouble(gesamt_monat("ausgaben", 4)),Double.parseDouble(gesamt_monat("ausgaben", 5)),Double.parseDouble(gesamt_monat("ausgaben", 6)),Double.parseDouble(gesamt_monat("ausgaben", 7)),Double.parseDouble(gesamt_monat("ausgaben", 8)), Double.parseDouble(gesamt_monat("ausgaben", 9)), Double.parseDouble(gesamt_monat("ausgaben", 10)),Double.parseDouble(gesamt_monat("ausgaben", 11)), Double.parseDouble(gesamt_monat("ausgaben", 12))} };
				ds.addSeries("Einnahmen", einn);
				ds.addSeries("Ausgaben", ausg);
				return ds;}
			});
	}

	public static ArrayList<Posten> CSVReader(String filename) {
		ArrayList<Posten> file_as_array = new ArrayList<Posten>(); 
		try {

			/**
			 * Reader for  "Einnahmen und Ausgabenübersicht" (based on the given Example)
			 */

			CSVReader reader = new CSVReader(new FileReader(filename));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				/**
				 * read date
				 */
				Date datum = df.parse(nextLine[0]);
				/**
				 * read purpose "Bezeichnung"
				 */
				String bezeichnung = nextLine[1];
				/**
				 * read value
				 */
				double betrag = Double.parseDouble(nextLine[2]);
				/**
				 * Information is saved as array for further calculation
				 */
				file_as_array.add(new Posten(datum, bezeichnung, betrag)); 
																			
			}
			reader.close();

			/**
			 * Exceptionhandling des Einlesens der CSV Dateien
			 */
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

	/**
	 * Returns the earnings and the costs for the current month
	 * 
	 * @param filename
	 * @return
	 */
	public static String gesamt_monat(String filename, int month) {
		/**
		 * Variables get initialized
		 */
		int number_of_rows = 0;
		
		/**
		 * Size of the needed array gets determined
		 */
		try {
			java.io.BufferedReader FileReader = new java.io.BufferedReader(
					new java.io.FileReader(new java.io.File("data/" + filename+ ".csv")));
			String zeile = "";
			while (null != (zeile = FileReader.readLine())) {
				number_of_rows++;
			}
			FileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Groesse des Arrays kann nicht festgelegt werden ");
		}
		/**
		 * Array gets filled with data from the CSV-data
		 */
		String[][] file_as_array = new String[number_of_rows][4];
		try {
			java.io.BufferedReader FileReader = new java.io.BufferedReader(
					new java.io.FileReader(new java.io.File("data/" + filename+ ".csv")));
			String zeile = "";
			int i = 0;
			while (null != (zeile = FileReader.readLine())) {
				String[] split = zeile.split(",");
				for (int j = 0; j < split.length; j++) {
					file_as_array[i][j] = split[j];
				}
				i++;
			}
			FileReader.close();
			double gesamt = 0.0;
			for (int k = 0; k < file_as_array.length; k++) {
				if (Integer.parseInt(file_as_array[k][0].substring(3, 5)) == month) {
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

	/**
	 * Creates the function "loeschen" for the CSV-files ausgaben & einnahmen
	 * 
	 * @param filename
	 * @param btnname
	 * @param table
	 * @return
	 */
	public static String loeschen(String filename, JButton btnname, JTable table) {
		/**
		 * Adds MouseListener for a selected row of the table "table"
		 */
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int eingabe = JOptionPane.showConfirmDialog(null,
						"Wollen Sie den ausgewaehlten Eintrag loeschen?", "",
						JOptionPane.YES_NO_OPTION);
				/**
				 * If the Yes-Button has been clicked, the following procedures
				 * are performed
				 */
				if (eingabe == JOptionPane.YES_OPTION) {
					/**
					 * Deletes every entry of the comitted CSV-data
					 */
					try {
						BufferedWriter bw1 = new BufferedWriter(new FileWriter(
								"data/" + filename + ".csv"));
						bw1.write("");
						bw1.close();
					} catch (Exception z) {
						z.printStackTrace();
						System.out
								.println("Problem beim Ueberschreiben des Arrays_1");
					}
					/**
					 * Writes each entry of the table, except the deleted one,
					 * in the comitted CSV-data
					 */
					for (int i = 0; i <= table.getRowCount(); i++) {
						if (i != table.getSelectedRow()) {
							try {
								FileWriter fw = new FileWriter("data/"
										+ filename + ".csv", true);
								BufferedWriter bw = new BufferedWriter(fw);
								String zeile = table.getValueAt(i, 0) + ","
										+ table.getValueAt(i, 1) + ","
										+ table.getValueAt(i, 2) + "\n";
								bw.write(zeile);
								bw.close();
								fw.close();
							} catch (Exception z) {
							}
						}
					}
					JOptionPane.getRootFrame().dispose();
					btnname.doClick();
				}
				/**
				 * If the No-Button has been clicked, the message window will be
				 * closed
				 */
				else if (eingabe == JOptionPane.NO_OPTION) {
					JOptionPane.getRootFrame().dispose();
				}
			}
		});
		return null;
	}
}
