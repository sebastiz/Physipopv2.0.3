package Console;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.EventListenerList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import javax.swing.JFrame;
import javax.script.*;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import Algorithms.HRMDx;
import BreathTest.BTAlgos;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.tree.TreeModel;

import org.apache.commons.math.MathException;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math.stat.inference.TTest;
import org.apache.commons.math.stat.inference.TTestImpl;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.tika.exception.TikaException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;

import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;

import Overview.DBConnectorForAll;
import Overview.Iterator;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.EtchedBorder;
import org.pmw.tinylog.Logger;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JTree;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.NSOption;
import org.fit.cssbox.swingbox.BrowserPane;
import org.fit.cssbox.*;
import org.fit.cssbox.layout.BrowserCanvas;
import org.w3c.dom.Element;
import org.fit.cssbox.css.DOMAnalyzer;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class TBB_SQLBuilder {

    private JFrame frame;
    private JTable table;
    private JTable table_1;
    private JTable table_2;
    private JTextField textField_Mean;
    private JTextField textField_Min;
    private JTextField textField_Max;
    private JTextField textField_StDev;
    private JTextField textField_Count;
    private JTextField textField;
    private JTextField textField_16;
    private JTextField txtPathForHRM;
    private JTextField txtPathForImpedance;
    private JTextField txtPathForDiagnosis;
    private JTextField txtPathForBRAVO;
    private JTextField txtPathForBreath;
    ListSelectionModel listSelectionModel;
    private JTextField textField_Regex;
    public static Preferences prefs;

    private JTextField txtFromDate;
    private JTextField txtToDate;
    private JTextField txtForEndoscopy;
    private JTextField txtForHistology;
    private JTextField txtForDB;
    private JTextField textForMyCSVOutput;
    private JTextField txtForLogger;
    private JTextField txtDBBackup;
    private JTextField txtPathToR;
    private JTextField txtSummary;
    private JTextField txtForError;
    private JTable table_ROutput;
    private JTextField textForReports_1;
    private JTextField textField_html;
    private JTextField textForExtra1;
    private JTextField textForExtra2;
    private JTextField textForExtra3;
    private JTextField textForExtra4;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

    	prefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);

        EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
                try {
                    TBB_SQLBuilder window = new TBB_SQLBuilder();
                    window.frame.setVisible(true);
                    System.gc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public TBB_SQLBuilder() throws IOException {
        initialize();

    }


    private void initialize() throws IOException {


        /*InputStream imgStream = TBB_SQLBuilder.class.getResourceAsStream("/Files/PhysipopIcon.png" );
        BufferedImage myImg = ImageIO.read(imgStream);
        imgStream.close();
        if (!SystemTray.isSupported()) {

            return;
          }

          SystemTray tray = SystemTray.getSystemTray();
          Toolkit toolkit = Toolkit.getDefaultToolkit();

          //Image image = new ImageIcon(this.getClass().getResource("/Files/PhysipopIcon.png")).getImage()
        		  Image image = toolkit.getImage(this.getClass().getResource("/Files/PhysipopIcon.png"));


          PopupMenu menu = new PopupMenu();

          MenuItem messageItem = new MenuItem("Show Message");
          messageItem.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
              JOptionPane.showMessageDialog(null, "Copyright Sebastian Zeki 2016");
            }
          });
          menu.add(messageItem);

          MenuItem closeItem = new MenuItem("Close");
          closeItem.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
          });
          menu.add(closeItem);
          TrayIcon icon = new TrayIcon(image, "SystemTray Demo", menu);
          icon.setImageAutoSize(true);

          try {
			tray.add(icon);
		} catch (AWTException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}*/

        // use icon here
        frame = new JFrame();
        //frame.setIconImage(myImg);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setTitle("PhysPop v1.0 Copyright Sebastian Zeki 2016");
        frame.setBounds(100, 100, 1250, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table = new JTable();
        table.setEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table_1 = new JTable();
        table_1.setColumnSelectionAllowed(true);
        table_1.setRowSelectionAllowed(false);
        table_2 = new JTable();
        table_2.setColumnSelectionAllowed(true);
        table_2.setRowSelectionAllowed(false);
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        tabbedPane.setBackground(Color.ORANGE);
        JScrollPane scrollPane = new JScrollPane(table);
        JScrollPane scrollPane1 = new JScrollPane(table_1);
        JScrollPane scrollPane2 = new JScrollPane(table_2);


     // Force the scrollbars to always be displayed

     scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
     scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

     scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
         scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

         scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
             scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1244, Short.MAX_VALUE)
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 848, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(330, Short.MAX_VALUE))
        );
        JPanel panel_Question = new JPanel();
        panel_Question.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Question", null, panel_Question, null);
        JButton btnExecute_2 = new JButton("Execute");




        table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);

        tca1.setColumnHeaderIncluded(true);
        tca1.setColumnDataIncluded(true);
        tca1.setOnlyAdjustLarger( true );
        tca1.setDynamicAdjustment( true );
        tca1.adjustColumns();
        table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca2 = new TableColumnAdjuster(table_2);
        tca2.setColumnHeaderIncluded(true);
        tca2.setColumnDataIncluded(true);
        tca2.setOnlyAdjustLarger( true );
        tca2.setDynamicAdjustment( true );
        tca2.adjustColumns();



        JTextArea textArea = new JTextArea();
        JScrollPane scrollQuesTextArea = new JScrollPane(textArea);
        textArea.setLineWrap(true);

        JButton btnExportToCsv = new JButton("Export to CSV");


        btnExportToCsv.addActionListener(new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent ae){
            	try {
					toExcel(table);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            }
        });

        textField_Mean = new JTextField();
        textField_Mean.setColumns(10);

        textField_Min = new JTextField();
        textField_Min.setColumns(10);

        textField_Max = new JTextField();
        textField_Max.setColumns(10);

        textField_StDev = new JTextField();
        textField_StDev.setColumns(10);

        textField_Count = new JTextField();
        textField_Count.setColumns(10);

        JLabel lblMean = new JLabel("Mean");

        JLabel lblMin = new JLabel("Min");

        JLabel lblMax = new JLabel("Max");

        JLabel lblCount = new JLabel("Count");

        JLabel lblStDev = new JLabel("St Dev");

        JLabel lblPickAColumn = new JLabel("For numeric columns only");
        lblPickAColumn.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

        JList<String> list = new JList<String>();
        list.setModel(new AbstractListModel<String>() {
        	/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"All HRM Main table", "All HRM Swallow Table", "All Bravo Days 1 and 2", "All Bravo Total Table", "All Impedance Main Table", "All Impedance Symptoms", "All Breath Tests", "All Diagnoses"};
        	@Override
			public int getSize() {
        		return values.length;
        	}
        	@Override
			public String getElementAt(int index) {
        		return values[index];
        	}
        });



        TableColumnModel columnModelMain = table.getColumnModel();



        columnModelMain.addColumnModelListener(new TableColumnModelListener() {
        	@Override
			public void columnSelectionChanged(ListSelectionEvent e) {
                Object[] data = columnToArray(table,table.getSelectedColumn());
                DescriptiveStatistics stats = new DescriptiveStatistics();

                DecimalFormat df = new DecimalFormat("#.00");
              //System.out.println("MY data length"+data.length);
              try {
            	  //Add the column array to the descriptive stats
                  for(int i = 0; i < data.length; i++){
                      if (data[i]!=null){
                      try {
                    	  stats.addValue(Double.parseDouble((data[i]).toString().trim()));
                      } catch (Exception e1) {
                          //System.out.println("nought to add here");
                      }
                      }
                  }
                  //System.out.println("The mean is"+stats.getMean());
                  textField_Mean.setText(df.format(stats.getMean()));
                  textField_Min.setText(df.format(stats.getMin()));
                  textField_Max.setText(df.format(stats.getMax()));
                  textField_StDev.setText(df.format(stats.getStandardDeviation()));
                  textField_Count.setText(df.format(stats.getN()));
              } catch (NumberFormatException e1) {
                  //System.out.println("You haven't selected a numeric column. Please select again");
              }
        	}
			@Override
			public void columnAdded(TableColumnModelEvent e) {
			}

			@Override
			public void columnRemoved(TableColumnModelEvent e) {
			}

			@Override
			public void columnMoved(TableColumnModelEvent e) {
			}

			@Override
			public void columnMarginChanged(ChangeEvent e) {
			}
        });







        // this runs during construction time, long before statsCompareL is updated

        JButton testButton = new JButton("Test ArrayList");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // this runs after the button is clicked, so it will see the changes statsCompareL
            }
        });













        JScrollPane scrollpanejlist = new JScrollPane(list);

        	list.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {

					//System.out.println("THE LIST"+list.getSelectedValue());
					if(list.getSelectedValue().toString()=="All HRM Main table"){
					textArea.setText("* from HRMImportMain");
					}
					else if(list.getSelectedValue().toString()=="All HRM Swallow Table"){
						textArea.setText("* from HRMImportSwallows");
						}
					else if(list.getSelectedValue().toString()=="All Bravo Days 1 and 2"){
						textArea.setText("* from BravoDay1And2");
						}
					else if(list.getSelectedValue().toString()=="All Bravo Total Table"){
						textArea.setText("* from BravoTotal");
						}
					else if(list.getSelectedValue().toString()=="All Impedance Main Table"){
						textArea.setText("* from Impedance2");
						}
					else if(list.getSelectedValue().toString()=="All Impedance Symptoms"){
						textArea.setText("* from Imp_Symp");
						}
					else if(list.getSelectedValue().toString()=="All Breath Tests"){
						textArea.setText("* from BreathTests");
						}
					else if(list.getSelectedValue().toString()=="All Diagnoses"){
						textArea.setText("* from Diag");
						}
				}

        });


        JButton btnPostbox_2 = new JButton("Postbox");
    	btnPostbox_2.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			String mylist=null;
    			try {
    				mylist=pbox();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
    			textArea.append(" Where "+mylist);
    	      }
    	    });


        JLabel lblManometryQueries = new JLabel("Whole Table queries");
        lblManometryQueries.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

        JList<String> listImp = new JList<String>();
        listImp.setModel(new AbstractListModel<String>() {
        	/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Achalasia Type 1", "Achalasia Type 2", "Achalasia Type 3", "Oesophageal outflow obstruction", "Nutcracker Oesophagus", "Aperistaltic oesophagus", "Diffuse oesophageal spasm","Failed-IOM","All GORD","Supine GORD","Upight GORD", "Night time GORD"};
        	@Override
			public int getSize() {
        		return values.length;
        	}
        	@Override
			public String getElementAt(int index) {
        		return values[index];
        	}
        });

        JLabel lblDiagnosisQueries = new JLabel("Diagnosis queries");
        lblDiagnosisQueries.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

        JScrollPane scrollpaneImpjlist = new JScrollPane(listImp);

    	listImp.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if(listImp.getSelectedValue().toString()=="Achalasia Type 1"){
				textArea.setText("* from HRMImportMain Where (ResidualmeanmmHg > 15 and failedChicagoClassification =100)");
				}
				else if(listImp.getSelectedValue().toString()=="Achalasia Type 2"){
					textArea.setText("* from HRMImportMain Where (ResidualmeanmmHg > 15 and failedChicagoClassification =100)");
					}
				else if(listImp.getSelectedValue().toString()=="Achalasia Type 3"){
					textArea.setText("* from HRMImportMain Where (ResidualmeanmmHg > 15 and failedChicagoClassification =100)");
					}
				else if(listImp.getSelectedValue().toString()=="Oesophageal outflow obstruction"){
					textArea.setText("* from HRMImportMain Where (ResidualmeanmmHg > 15 and failedChicagoClassification <100)");
					}
				else if(listImp.getSelectedValue().toString()=="Nutcracker Oesophagus"){
					textArea.setText("* from HRMImportMain Where (DistalcontractileintegralhighestmmHgcms > 8000)");
					}
				else if(listImp.getSelectedValue().toString()=="Aperistaltic oesophagus"){
					textArea.setText("* from HRMImportMain Where (ResidualmeanmmHg < 15 and failedChicagoClassification =100)");
					}
				else if(listImp.getSelectedValue().toString()=="Diffuse oesophageal spasm"){
					textArea.setText("* from HRMImportMain Where (DistalcontractileintegralhighestmmHgcms > 4999)");
					}
				else if(listImp.getSelectedValue().toString()=="Failed-IOM"){
					textArea.setText("* from HRMImportMain Where ( ResidualmeanmmHg < 15 and failedChicagoClassification>50 and failedChicagoClassification<100)");
					}

				else if(listImp.getSelectedValue().toString()=="All GORD"){
					textArea.setText("* from Diag");
					}
				else if(listImp.getSelectedValue().toString()=="Supine GORD"){
					textArea.setText("* from Diag");
					}
				else if(listImp.getSelectedValue().toString()=="Upright GORD"){
					textArea.setText("* from Diag");
					}
				else if(listImp.getSelectedValue().toString()=="Night time GORD"){
					textArea.setText("* from Diag");
					}
			}
    });

        JLabel lblManagementQuesries = new JLabel("Management Queries");
        lblManagementQuesries.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

        JList<String> list_1 = new JList<String>();
        list_1.setModel(new AbstractListModel<String>() {
        	/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"How many Impedance catheters", "How many Bravo capsules", "How many HRM"};
        	@Override
			public int getSize() {
        		return values.length;
        	}
        	@Override
			public String getElementAt(int index) {
        		return values[index];
        	}
        });

        JList<String> list_2 = new JList<String>();
        list_2.setModel(new AbstractListModel<String>() {
        	/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Cough", "Belch", "Heartburn", "Throat", "Regurgitation", "Nausea", "Vomiting", "Chest Pain"};
        	@Override
			public int getSize() {
        		return values.length;
        	}
        	@Override
			public String getElementAt(int index) {
        		return values[index];
        	}
        });

        JLabel lblSymptomQueries = new JLabel("Symptom queries");
        lblSymptomQueries.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));


        JList<String> list_5 = new JList<String>();
        list_5.setModel(new AbstractListModel<String>() {
        	/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Search for individual patient endoscopy and histology","Intestinal metaplasia lookup", "All Barretts Patients", "Barretts Surveillance Patients", "Barrett's therapeutics", "Biopsy size and number by endoscopist audit","Prague Classification usage audit", "Adenocarcinomas", "Endoscopy with histopath","PathQry_Size","Patients who have had RFA","Barretts Audit Generator"};
        	@Override
			public int getSize() {
        		return values.length;
        	}
        	@Override
			public String getElementAt(int index) {
        		return values[index];
        	}
        });
        JLabel lblHistopathologyQueries = new JLabel("Histo/Endo Queries");
        lblHistopathologyQueries.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

        JScrollPane scrollpaneHistop = new JScrollPane(list_5);

         	list_5.addListSelectionListener(new ListSelectionListener() {

     			@Override
     			public void valueChanged(ListSelectionEvent e) {
     				if(list_5.getSelectedValue().toString()=="Search for individual patient endoscopy and histology"){
         				textArea.setText("Endoscopy.*,Histology.* from Histology INNER JOIN Endoscopy ON Endoscopy.HospNum_Id=Histology.HospNum_Id where Endoscopy.HospNum_Id=''");
         				}
     				else if(list_5.getSelectedValue().toString()=="Intestinal metaplasia lookup"){
     				textArea.setText("* from Histology where diagnosis LIKE '%ntestinal metaplasia%'");
     				}
     				else if(list_5.getSelectedValue().toString()=="All Barretts Patients"){
     					textArea.setText("* from Endoscopy where ERFINDINGSSTR LIKE '%Barrett%'OR ERDIAGNOSISSTR LIKE '%Barrett%'");
     					}


     				else if(list_5.getSelectedValue().toString()=="Barretts Surveillance Patients"){
     					textArea.setText("* from Endoscopy where INDICATIONS LIKE '%Surveillance- Barretts%'");
     					}
     				else if(list_5.getSelectedValue().toString()=="Barretts EMRs"){
     					textArea.setText("* from Histology where NatureOfSpec LIKE '%EMR%'or NatureOfSpec LIKE '%esection%'");
     					}
     				else if(list_5.getSelectedValue().toString()=="Adenocarcinomas"){
     					textArea.setText("* from Histology where Diagnosis LIKE '%denocarcinoma%'");
     					}
     				else if(list_5.getSelectedValue().toString()=="Endoscopy with histopath"){
     					textArea.setText("Endoscopy.*,Histology.Diagnosis from Endoscopy  JOIN PatientData ON Endoscopy.HospNum_Id=PatientData.HospNum_Id  JOIN Histology ON Histology.HospNum_Id=PatientData.HospNum_Id WHERE histology.VisitDate = endoscopy.VisitDate");
     					}
     				else if(list_5.getSelectedValue().toString()=="Barrett's therapeutics"){
     					textArea.setText("* from Endoscopy where ERPROCEDUREPERFORMED LIKE '%HALO%' OR  ERPROCEDUREPERFORMED  LIKE '%APC%' OR ERPROCEDUREPERFORMED  LIKE '%EMR%'");
     					}
     				else if(list_5.getSelectedValue().toString()=="Prague Classification usage audit"){
     					textArea.setText("* from Endoscopy where ERDIAGNOSISSTR LIKE '%RFA' OR ERDIAGNOSISSTR LIKE '%adio%' OR ERDIAGNOSISSTR LIKE '%HALO%' OR ERFINDINGSSTR LIKE '%RFA' OR ERFINDINGSSTR LIKE '%adio%' OR ERFINDINGSSTR LIKE '%HALO%'");
     					}
     				else if(list_5.getSelectedValue().toString()=="Biopsy size and number by endoscopist audit"){
     					textArea.setText("Endoscopy.Endoscopist, HistolDetatils.MeasurementLargest, HistolDetatils.NumberBx from Endoscopy  INNER JOIN PatientData ON Endoscopy.HospNum_Id=PatientData.HospNum_Id  INNER JOIN Histology ON Histology.HospNum_Id=PatientData.HospNum_Id INNER Join HistolDetatils ON Histology.Histology_Id=HistolDetatils.Histology_Id WHERE histology.VisitDate = endoscopy.VisitDate AND  Endoscopy.ERPROCEDUREPERFORMED NOT LIKE '%EMR%' AND  Endoscopy.ERPROCEDUREPERFORMED NOT LIKE '%Endoscopic Ultrasound%' AND  Endoscopy.ERPROCEDUREPERFORMED NOT LIKE '%Therapeutic%' AND Endoscopy. INDICATIONS LIKE '%Surveillance%'");
     					}
     				else if(list_5.getSelectedValue().toString()=="Barretts Audit Generator"){
     					Map<String,String> AuditMap= new LinkedHashMap<String,String>();
     					String Audit1="* from HRMImportMain";
     					AuditMap.put("Audit1.csv", Audit1);
     					String Audit2="* from Endoscopy where ERDIAGNOSISSTR LIKE '%RFA' OR ERDIAGNOSISSTR LIKE '%adio%' OR ERDIAGNOSISSTR LIKE '%HALO%' OR ERFINDINGSSTR LIKE '%RFA' OR ERFINDINGSSTR LIKE '%adio%' OR ERFINDINGSSTR LIKE '%HALO%'";
     					AuditMap.put("Audit2.csv", Audit2);
     					String Audit3="* from Endoscopy where ERDIAGNOSISSTR LIKE '%RFA' OR ERDIAGNOSISSTR LIKE '%adio%' OR ERDIAGNOSISSTR LIKE '%HALO%' OR ERFINDINGSSTR LIKE '%RFA' OR ERFINDINGSSTR LIKE '%adio%' OR ERFINDINGSSTR LIKE '%HALO%'";
     					AuditMap.put("Audit3.csv", Audit3);
     					String Audit4="* from Endoscopy where ERDIAGNOSISSTR LIKE '%RFA' OR ERDIAGNOSISSTR LIKE '%adio%' OR ERDIAGNOSISSTR LIKE '%HALO%' OR ERFINDINGSSTR LIKE '%RFA' OR ERFINDINGSSTR LIKE '%adio%' OR ERFINDINGSSTR LIKE '%HALO%'";
     					AuditMap.put("Audit4.csv", Audit4);
     					String Audit5="* from Endoscopy where ERDIAGNOSISSTR LIKE '%RFA' OR ERDIAGNOSISSTR LIKE '%adio%' OR ERDIAGNOSISSTR LIKE '%HALO%' OR ERFINDINGSSTR LIKE '%RFA' OR ERFINDINGSSTR LIKE '%adio%' OR ERFINDINGSSTR LIKE '%HALO%'";
     					AuditMap.put("Audit5.csv", Audit5);
     					String Audit6="* from Endoscopy where ERDIAGNOSISSTR LIKE '%RFA' OR ERDIAGNOSISSTR LIKE '%adio%' OR ERDIAGNOSISSTR LIKE '%HALO%' OR ERFINDINGSSTR LIKE '%RFA' OR ERFINDINGSSTR LIKE '%adio%' OR ERFINDINGSSTR LIKE '%HALO%'";
     					AuditMap.put("Audit6.csv", Audit6);
     					String Auditfolder="/Users/sebastianzeki/Documents/PhysJava/TBBConsoleExportPhys/";

     					for (Entry<String, String> entry : AuditMap.entrySet()) {
     						try {
								HookMeUpAudit(table,entry.getValue(),entry.getKey(),Auditfolder);
							} catch (ClassNotFoundException e1) {

								e1.printStackTrace();
							} catch (IOException e1) {

								e1.printStackTrace();
							} catch (SQLException e1) {

								e1.printStackTrace();
							} catch (ParseException e1) {

								e1.printStackTrace();
							}
     					 }
     					AuditMap=null;
     				}
     			}
         });

        textField_Regex = new JTextField();
        textField_Regex.setColumns(10);

        JButton btnRegex = new JButton("Fine filter");

        btnRegex.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		 TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        		 table.setRowSorter(sorter);
        		String expr = textField_Regex.getText();
                sorter.setRowFilter(RowFilter.regexFilter(expr));
                sorter.setSortKeys(null);

        	}
        });


        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    DefaultTableModel newModel = new DefaultTableModel();
                    String rowName = "Row: " + selectedRow;
                    newModel.setColumnIdentifiers(new Object[]{rowName});
                    for (int i = 0; i < table.getModel().getColumnCount(); i++) {
                        newModel.addRow(new Object[]{table.getModel().getValueAt(selectedRow, i)});
                    }
                    JTable newTable = new JTable(newModel) {
                        /**
						 *
						 */
						private static final long serialVersionUID = 1L;

						@Override
                        public Dimension getPreferredScrollableViewportSize() {
                            return new Dimension(140, 240);
                        }
                    };
                    newTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    newTable.setRowHeight(14, 30);
                    newTable.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());

                    TableColumnAdjuster tcanewTable = new TableColumnAdjuster(newTable);
                    tcanewTable.setColumnHeaderIncluded(true);
                    tcanewTable.setColumnDataIncluded(true);
                    tcanewTable.setOnlyAdjustLarger( true );
                    tcanewTable.setDynamicAdjustment( true );
                    tcanewTable.adjustColumns();


                    // Apply any custom renderers and editors
                    JOptionPane.showMessageDialog(frame, new JScrollPane(newTable),rowName, JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        JList<String> list_6 = new JList<String>();
        list_6.setModel(new AbstractListModel<String>() {
        	/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Barretts HRMMainTable", "Barretts Impedance", "Endoscopy and Impedance", "Endoscopy and HRM"};
        	@Override
			public int getSize() {
        		return values.length;
        	}
        	@Override
			public String getElementAt(int index) {
        		return values[index];
        	}
        });

        JLabel lblUpperGiCross = new JLabel("Upper GI Cross Table queries");
        lblUpperGiCross.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(TBB_SQLBuilder.class.getResource("/Files/PhysipopIcon.png")));


        JButton btnDaterange = new JButton("DateRange");
        btnDaterange.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		 Date startDate = null;
        		 Date endDate = null;
        		// if (startDate!=null&&endDate!=null){
        		 TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());


        		 table.setRowSorter(sorter);
        	/*	String expr = textField_Regex.getText();
                sorter.setRowFilter(RowFilter.regexFilter(expr));
                sorter.setSortKeys(null);

        		 */

        		List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
        		//Convert string to date for the inupt fields
        		//JFormattedTextField is a subclass of JTextField.

        		String expectedPattern = "dd/MM/yyyy";
                SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
                //ArrayList<RowFilter<Object, Object>> andFilters = new ArrayList<RowFilter<Object, Object>>();

                  // (2) give the formatter a String that matches the SimpleDateFormat pattern

				try {
					startDate = formatter.parse(txtFromDate.getText().trim());
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

				try {
					endDate = formatter.parse(txtToDate.getText().trim());
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
        		filters.add( RowFilter.dateFilter(ComparisonType.AFTER,startDate));
        		filters.add( RowFilter.dateFilter(ComparisonType.BEFORE, endDate));
        		RowFilter<Object,Object> rf = RowFilter.andFilter(filters);
        		sorter.setRowFilter(rf);
        	//	 }
        	}
        });

        txtFromDate = new JTextField();
        txtFromDate.setText("From dd/mm/yyyy");
        txtFromDate.setColumns(10);

        txtToDate = new JTextField();
        txtToDate.setText("To dd/mm/yyyy");
        txtToDate.setColumns(10);
        JFrame mainFrame = new JFrame("Choose a File");
        mainFrame.setSize(400,400);






        JButton btnOutputter = new JButton("Outputter");
        btnOutputter.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		StringBuilder  strb = new StringBuilder();

        	        	                Object[] data_L = columnToArray(table,table.getSelectedColumn());
        	        	                ////System.out.println(Arrays.toString(data_L));
        	        	                //To print the thing out to a blank form so you can just cut n paste it
        	        	                PrintWriter pr;
										try {
											pr = new PrintWriter("/Users/sebastianzeki/Desktop/filename.txt");
											strb.append("Select p1.procedurenumber,p1.Instruments,p1.patientrecordnumber,pt.firstname,pt.lastname,p1.ENDOSCOPIST,p1.endoscopist1 as EndoTwo,p1.resident asTrainee,p1.er_procedureperformed,p1.dateofprocedure,p1.room,p1.indications,p1.er_extentofexam,p1.er_findings_str,p1.er_diagnosis_str,p1.er_recommendations,p1.er_recallreason1,p1.er_limitations,p1.er_complications,p1.er_comments,g.SEDATIONSCORE_STR,g.painlevelscore_str,p1.biopsyobtained From PROCEDURES p1 left join GRS g on g.procedurenumber=p1.procedurenumber left join patients pt on pt.recordnumber=p1.patientrecordnumber where p1.er_procedureperformed is not null andp1.er_extentofexam is not null and");
											pr.println("Select p1.procedurenumber,p1.Instruments,p1.patientrecordnumber,pt.firstname,pt.lastname,p1.ENDOSCOPIST,p1.endoscopist1 as EndoTwo,p1.resident asTrainee,p1.er_procedureperformed,p1.dateofprocedure,p1.room,p1.indications,p1.er_extentofexam,p1.er_findings_str,p1.er_diagnosis_str,p1.er_recommendations,p1.er_recallreason1,p1.er_limitations,p1.er_complications,p1.er_comments,g.SEDATIONSCORE_STR,g.painlevelscore_str,p1.biopsyobtained From PROCEDURES p1 left join GRS g on g.procedurenumber=p1.procedurenumber left join patients pt on pt.recordnumber=p1.patientrecordnumber where p1.er_procedureperformed is not null andp1.er_extentofexam is not null and");
											 for(Object n: data_L) {
		        	        	                    pr.println("p1.patientrecordnumber ="+n+" "+"or ");
		        	        	                    strb.append("p1.patientrecordnumber ='"+n+"' "+"or ");
		        	        	                    //Print out all of the lines into a text block
		        	        	                    //Then add to the query text block from Endosoft
		        	        	                    //Then save as normal text file for copy paste
		        	        	                }
											 pr.println("p1.DATEOFPROCEDURE>= :startdate and p1.DATEOFPROCEDURE<= :enddate +1");
											 strb.append("p1.DATEOFPROCEDURE>= :startdate and p1.DATEOFPROCEDURE<= :enddate +1");

											 JTextArea mytext = new JTextArea();
											 mytext.setText("mytextline1\nmytextline2\nmytextline3\nmytextline4\nmytextline5\nmytextline6");
											 mytext.setRows(5);
											 mytext.setColumns(10);
											 mytext.setEditable(true);
											 JOptionPane.showInputDialog(null, "This is the Endosoft Query", strb);
		        	        	                pr.close();
										} catch (FileNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

        	}
        });



        GroupLayout gl_panel_Question = new GroupLayout(panel_Question);
        gl_panel_Question.setHorizontalGroup(
        	gl_panel_Question.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_Question.createSequentialGroup()
        			.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel_Question.createSequentialGroup()
        					.addGap(15)
        					.addComponent(lblPickAColumn))
        				.addGroup(gl_panel_Question.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel_Question.createSequentialGroup()
        							.addComponent(txtFromDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(txtToDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl_panel_Question.createSequentialGroup()
        							.addComponent(btnExportToCsv)
        							.addGap(272)
        							.addComponent(btnOutputter))
        						.addGroup(gl_panel_Question.createSequentialGroup()
        							.addGroup(gl_panel_Question.createParallelGroup(Alignment.TRAILING)
        								.addGroup(gl_panel_Question.createSequentialGroup()
        									.addGroup(gl_panel_Question.createParallelGroup(Alignment.TRAILING)
        										.addComponent(lblStDev)
        										.addComponent(lblMin)
        										.addComponent(lblMax)
        										.addComponent(lblCount))
        									.addGap(6))
        								.addComponent(lblMean))
        							.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        								.addComponent(textField_Max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(textField_StDev, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(textField_Min, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(textField_Mean, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(textField_Count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(gl_panel_Question.createParallelGroup(Alignment.TRAILING, false)
        							.addComponent(scrollQuesTextArea, Alignment.LEADING)
        							.addComponent(textField_Regex, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel_Question.createSequentialGroup()
        							.addGap(28)
        							.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblDiagnosisQueries, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
        								.addGroup(gl_panel_Question.createSequentialGroup()
        									.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        										.addGroup(gl_panel_Question.createParallelGroup(Alignment.TRAILING)
        											.addComponent(scrollpaneImpjlist, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
        											.addComponent(scrollpanejlist, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
        										.addComponent(lblManometryQueries))
        									.addGap(22)
        									.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        										.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
        										.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
        										.addComponent(lblSymptomQueries)
        										.addComponent(lblManagementQuesries))
        									.addGap(34)
        									.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        										.addComponent(scrollpaneHistop, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
        										.addComponent(lblUpperGiCross)
        										.addComponent(list_6, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
        										.addComponent(lblHistopathologyQueries)))))
        						.addGroup(gl_panel_Question.createSequentialGroup()
        							.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(btnDaterange, 0, 0, Short.MAX_VALUE)
        								.addComponent(btnPostbox_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(btnExecute_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(btnRegex, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addGap(173)
        							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(gl_panel_Question.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1177, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(46, Short.MAX_VALUE))
        );
        gl_panel_Question.setVerticalGroup(
        	gl_panel_Question.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_Question.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel_Question.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panel_Question.createSequentialGroup()
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel_Question.createSequentialGroup()
        							.addComponent(btnExecute_2)
        							.addGap(4)
        							.addComponent(btnPostbox_2))
        						.addComponent(scrollQuesTextArea, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        						.addComponent(textField_Regex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnRegex))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnDaterange)
        						.addComponent(txtToDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtFromDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(18))
        				.addGroup(gl_panel_Question.createSequentialGroup()
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        					.addGap(27)))
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_Question.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(gl_panel_Question.createSequentialGroup()
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnExportToCsv)
        						.addComponent(btnOutputter))
        					.addGap(7)
        					.addComponent(lblPickAColumn)
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel_Question.createSequentialGroup()
        							.addGap(129)
        							.addComponent(lblStDev))
        						.addGroup(gl_panel_Question.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        								.addComponent(textField_Mean, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblMean))
        							.addGap(16)
        							.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        								.addComponent(textField_Min, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblMin))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        								.addComponent(textField_Max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblMax))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(textField_StDev, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        								.addComponent(textField_Count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblCount))))
        					.addGap(7))
        				.addGroup(gl_panel_Question.createSequentialGroup()
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblManagementQuesries)
        						.addComponent(lblManometryQueries)
        						.addComponent(lblHistopathologyQueries))
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        						.addComponent(scrollpanejlist, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
        						.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
        						.addComponent(scrollpaneHistop, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
        					.addGap(4)
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblDiagnosisQueries)
        						.addComponent(lblSymptomQueries)
        						.addComponent(lblUpperGiCross))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_panel_Question.createParallelGroup(Alignment.BASELINE)
        						.addComponent(scrollpaneImpjlist, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
        						.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
        						.addComponent(list_6, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))))
        			.addGap(56))
        );

        btnExecute_2.addActionListener(new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent ae){

               String getValue = textArea.getText();
                              try {
								HookMeUp(table,getValue);
								System.gc();
								 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							        TableColumnAdjuster tca = new TableColumnAdjuster(table);
							        tca.setColumnHeaderIncluded(true);
							        tca.setColumnDataIncluded(true);
							        tca.setOnlyAdjustLarger( true );
							        tca.setDynamicAdjustment( true );
							        tca.adjustColumns();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							} catch (ParseException e) {

								e.printStackTrace();
							}
            }
         });
        panel_Question.setLayout(gl_panel_Question);

        JPanel panel_RptMaker = new JPanel();
        panel_RptMaker.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("ReportMaker", null, panel_RptMaker, null);

        textField_16 = new JTextField();
        textField_16.setColumns(10);
        JList<String> list_3 = new JList<String>();
        list_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

        DefaultListModel<String>  modelHRM= new DefaultListModel<String>();
    	DefaultListModel<String>  modelImp= new DefaultListModel<String>();
    	DefaultListModel<String>  modelBravo= new DefaultListModel<String>();
    	DefaultListModel<String>  modelBT= new DefaultListModel<String>();
    	DefaultListModel<String>  modelDiag= new DefaultListModel<String>();
    	DefaultListModel<String>  modelEndoscopy= new DefaultListModel<String>();
    	DefaultListModel<String>  modelHistology= new DefaultListModel<String>();

        JList<String> list_4 = new JList<String>();
        list_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        JButton btnSearchHospitalNumber = new JButton("Search Hospital Number");

  JLabel lblHrmReports = new JLabel("HRM Reports");

        JLabel lblImpedanceReports = new JLabel("Impedance Reports");

        JList<String> list_BreathTest = new JList<String>();
        list_BreathTest.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

        JLabel lblBreathTest = new JLabel("Breath Test");

        JList<String> list_Bravos = new JList<String>();
        list_Bravos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

        JLabel lblBravos = new JLabel("Bravos");

        JList<String> list_Diag = new JList<String>();
        list_Diag.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

    JLabel lblReportSummary = new JLabel("Report Summary");

        JList list_Endoscopy = new JList();
        list_Endoscopy.setBackground(Color.YELLOW);

        JList list_Histology = new JList();
        list_Histology.setBackground(Color.YELLOW);

        JLabel lblEndoscopy = new JLabel("Endoscopy");

        JLabel lblHistology = new JLabel("Histology");


        JLabel lblFinalReports = new JLabel("Final Reports");

        JEditorPane editorPane_2 = new JEditorPane();
        JScrollPane scrollPanePrev = new JScrollPane(editorPane_2);
        scrollPanePrev.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPanePrev.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel lblPreviewPane = new JLabel("Preview pane");


        //This adds the reports to the list on the swing form:
        btnSearchHospitalNumber.addActionListener(new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent ae){


            	//model1.removeAllElements();
            	String HospNum=null;
            	HospNum=textField_16.getText();
            	//Connect to the database
            	DBConnectorForAll Connect=new DBConnectorForAll();

            	try {
            		Statement st;
					st=Connect.ConnectorNoInsert(HospNum,null,null,null);
					String tableHRM="HRMImportMain";
					String tableImp="Impedance2";
					String tableBravo="BravoDay1And2";
					String tableBT="BreathTests";
					String tableDiag="Diag";
					String tableEndoscopy="Endoscopy";
					String tableHistology="Histology";


					for(String val : Connect.Selector(st, HospNum, tableHRM,"VisitDate")){
						//System.out.println("val"+val);
						modelHRM.addElement(val);
				         list_3.setModel(modelHRM);
				         //System.out.println("list_3"+list_3);

					}
					for(String val : Connect.Selector(st, HospNum, tableImp,"VisitDate")){
						//System.out.println("list_4a"+list_4);
						modelImp.addElement(val);
						 list_4.setModel(modelImp);
						 //System.out.println("list_4"+list_4);
					}
					for(String val : Connect.Selector(st, HospNum, tableBT,"VisitDate")){
						//System.out.println("list_4a"+list_4);
						modelBT.addElement(val);
						list_BreathTest.setModel(modelBT);
						 //System.out.println("list_4"+list_BreathTest);
					}
					for(String val : Connect.Selector(st, HospNum, tableBravo,"VisitDate")){
						//System.out.println("list_4a"+list_Bravos);
						modelBravo.addElement(val);
						list_Bravos.setModel(modelBravo);
						 //System.out.println("list_4"+list_Bravos);
					}
					for(String val : Connect.Selector(st, HospNum, tableDiag,"VisitDate")){
						//System.out.println("list_4a"+list_Diag);
						modelDiag.addElement(val);
						list_Diag.setModel(modelDiag);
						 //System.out.println("list_4"+list_Diag);
					}
					for(String val : Connect.Selector(st, HospNum, tableEndoscopy,"VisitDate")){
						//System.out.println("list_4a"+list_Diag);
						modelEndoscopy.addElement(val);
						list_Endoscopy.setModel(modelEndoscopy);
						 //System.out.println("list_4"+list_Diag);
					}

					for(String val : Connect.Selector(st, HospNum, tableHistology,"VisitDate")){
						//System.out.println("list_4a"+list_Diag);
						modelHistology.addElement(val);
						list_Histology.setModel(modelHistology);
						 //System.out.println("list_4"+list_Diag);
					}


				      //st.close();

				} catch (SQLException e) {

					e.printStackTrace();
				}
            }
            });


      //This is for the selected report so that you can get all the values from the database:
        list_3.addListSelectionListener(new ListSelectionListener() {
            @Override
			public void valueChanged(ListSelectionEvent e) {
            	 Map<String,Object> SelectResult= new LinkedHashMap<String,Object>();

            	 if (!e.getValueIsAdjusting()){
                     JList source = (JList)e.getSource();
                     String selected = source.getSelectedValue().toString();
                     //System.out.println("selected"+selected);
                     String HospNum=null;
                 	HospNum=textField_16.getText();
                 	//Connect to the database
                 	DBConnectorForAll Connect=new DBConnectorForAll();
                     Statement st = null;

 					try {

 						st=Connect.ConnectorNoInsert(HospNum,null,null,null);

 					String tableHRM="HRMImportMain";
                    	 SelectResult=Connect.CompositeSelector(st, HospNum, tableHRM,selected);
                    	 StringBuilder  strb2 = new StringBuilder();
	                   	 for (Map.Entry<String, Object> entry : SelectResult.entrySet())
	                   	 {
                          strb2.append((entry.getKey() + ":               " + entry.getValue()));
	                   	  strb2.append("\n");
	                   	 }

	                   	editorPane_2.removeAll();
	                   	editorPane_2.setText(strb2.toString());

	                   	//Implement the algorithm assessor
	                   	if(strb2.toString().contains("ResidualmeanmmHg")){
	                   		String algo=Algorithms.HRMDx.HRMDiag(SelectResult);
	                   		//System.out.println("ALGO"+algo);
	                   		txtSummary.setText(algo);


	                   	}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 }

            }
        });

        list_4.addListSelectionListener(new ListSelectionListener() {
            @Override
			public void valueChanged(ListSelectionEvent e) {
            	 Map<String,Object> SelectResult= new LinkedHashMap<String,Object>();
            	 if (!e.getValueIsAdjusting()){
                     JList source = (JList)e.getSource();
                     String selected = source.getSelectedValue().toString();
                     //System.out.println("selected"+selected);
                     String HospNum=null;
                 	HospNum=textField_16.getText();
                 	//Connect to the database
                 	DBConnectorForAll Connect=new DBConnectorForAll();
                     Statement st = null;
 					try {
						st=Connect.ConnectorNoInsert(HospNum,null,null,null);
						//st.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
 					String tableImp="Impedance2";

                     try {
                    	 SelectResult=Connect.CompositeSelector(st, HospNum, tableImp,selected);
						 StringBuilder  strb2 = new StringBuilder();


	                   	 for (Map.Entry<String, Object> entry : SelectResult.entrySet())
	                   	 {

	                   		strb2.append((entry.getKey() + ":               " + entry.getValue()));
	                   		strb2.append("\n");
	                   	 }
	                   	 SelectResult=null;
	                   	 editorPane_2.setText(strb2.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 }
            }
        });

        list_Bravos.addListSelectionListener(new ListSelectionListener() {
            @Override
			public void valueChanged(ListSelectionEvent e) {
            	 Map<String,Object> SelectResult= new LinkedHashMap<String,Object>();
            	 if (!e.getValueIsAdjusting()){
                     JList source = (JList)e.getSource();
                     String selected = source.getSelectedValue().toString();
                     //System.out.println("selected"+selected);
                     String HospNum=null;
                 	HospNum=textField_16.getText();
                 	//Connect to the database
                 	DBConnectorForAll Connect=new DBConnectorForAll();
                     Statement st = null;
 					try {
						st=Connect.ConnectorNoInsert(HospNum,null,null,null);
						//st.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
 					String tableBravo="BravoDay1And2";

                     try {
                    	 SelectResult=Connect.CompositeSelector(st, HospNum, tableBravo,selected);
                    	 StringBuilder  strb2 = new StringBuilder();


                       	 for (Map.Entry<String, Object> entry : SelectResult.entrySet())
                       	 {
                       	     //System.out.println(entry.getKey() + "/" + entry.getValue());
                       	  strb2.append((entry.getKey() + ":               " + entry.getValue()));
                       	strb2.append("\n");
                       	 }
                       	 SelectResult=null;
                       	 editorPane_2.setText(strb2.toString());

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 }
            }
        });

        list_BreathTest.addListSelectionListener(new ListSelectionListener() {
            @Override
			public void valueChanged(ListSelectionEvent e) {
            	 Map<String,Object> SelectResult= new LinkedHashMap<String,Object>();
            	 if (!e.getValueIsAdjusting()){
                     JList source = (JList)e.getSource();
                     String selected = source.getSelectedValue().toString();
                     //System.out.println("selected"+selected);
                     String HospNum=null;
                 	HospNum=textField_16.getText();
                 	//Connect to the database
                 	DBConnectorForAll Connect=new DBConnectorForAll();
                     Statement st = null;
 					try {
						st=Connect.ConnectorNoInsert(HospNum,null,null,null);
						//st.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
 					String tableBT="BreathTests";
                     try {
                    	 SelectResult=Connect.CompositeSelector(st, HospNum, tableBT,selected);
                   	 StringBuilder  strb2 = new StringBuilder();


                   	 for (Entry<String, Object> entry : SelectResult.entrySet())
                   	 {
                   		 //Just build Stringbuilder in order to get it into the text pane
                   		strb2.append((entry.getKey() + ":               " + entry.getValue()));
                   		strb2.append("\n");


                   	 }
                   	BreathTest.BTAlgos fd = new BreathTest.BTAlgos();
                   	fd.HashMaker(SelectResult);

                   		//Also build the hashes for the breath test algorithm








                   	 SelectResult=null;
                   	 editorPane_2.setText(strb2.toString());
						//}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                 }

            }
        });

        list_Diag.addListSelectionListener(new ListSelectionListener() {
            @Override
			public void valueChanged(ListSelectionEvent e) {
            	 Map<String,Object> SelectResult= new LinkedHashMap<String,Object>();
            	 if (!e.getValueIsAdjusting()){
                     JList source = (JList)e.getSource();
                     String selected = source.getSelectedValue().toString();
                     //System.out.println("selected"+selected);
                     String HospNum=null;
                 	HospNum=textField_16.getText();
                 	//Connect to the database
                 	DBConnectorForAll Connect=new DBConnectorForAll();
                     Statement st = null;
 					try {
						st=Connect.ConnectorNoInsert(HospNum,null,null,null);
						//st.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
 					String tableDiag="Diag";



                     try {
                    	 SelectResult=Connect.CompositeSelector(st, HospNum, tableDiag,selected);
                    	 StringBuilder  strb2 = new StringBuilder();


                    	 for (Map.Entry<String, Object> entry : SelectResult.entrySet())
                    	 {
                    		 strb2.append((entry.getKey() + ":               " + entry.getValue()));
                    		 strb2.append("\n");

                    	 }
                    	 SelectResult=null;
                    	 editorPane_2.setText(strb2.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	 }
            }
        });


        list_Endoscopy.addListSelectionListener(new ListSelectionListener() {
            @Override
			public void valueChanged(ListSelectionEvent e) {
            	 Map<String,Object> SelectResult= new LinkedHashMap<String,Object>();
            	 if (!e.getValueIsAdjusting()){
                     JList source = (JList)e.getSource();
                     String selected = source.getSelectedValue().toString();
                     //System.out.println("selected"+selected);
                     String HospNum=null;
                 	HospNum=textField_16.getText();
                 	//Connect to the database
                 	DBConnectorForAll Connect=new DBConnectorForAll();
                     Statement st = null;
 					try {
						st=Connect.ConnectorNoInsert(HospNum,null,null,null);
						//st.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
 					String tableEndoscopy="Endoscopy";

                     try {
                    	 SelectResult=Connect.CompositeSelector(st, HospNum, tableEndoscopy,selected);
                    	 StringBuilder  strb2 = new StringBuilder();


                    	 for (Map.Entry<String, Object> entry : SelectResult.entrySet())
                    	 {
                    		 strb2.append((entry.getKey() + ":               " + entry.getValue()));
                    		 strb2.append("\n");

                    	 }
                    	 SelectResult=null;
                    	 editorPane_2.setText(strb2.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	 }
            }
        });

        list_Histology.addListSelectionListener(new ListSelectionListener() {
            @Override
			public void valueChanged(ListSelectionEvent e) {
            	 Map<String,Object> SelectResult= new LinkedHashMap<String,Object>();
            	 if (!e.getValueIsAdjusting()){
                     JList source = (JList)e.getSource();
                     String selected = source.getSelectedValue().toString();
                     //System.out.println("selected"+selected);
                     String HospNum=null;
                 	HospNum=textField_16.getText();
                 	//Connect to the database
                 	DBConnectorForAll Connect=new DBConnectorForAll();
                     Statement st = null;
 					try {
						st=Connect.ConnectorNoInsert(HospNum,null,null,null);
						//st.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
 					String tableHistology="Histology";



                     try {
                    	 SelectResult=Connect.CompositeSelector(st, HospNum, tableHistology,selected);
                    	 StringBuilder  strb2 = new StringBuilder();


                    	 for (Map.Entry<String, Object> entry : SelectResult.entrySet())
                    	 {
                    		 strb2.append((entry.getKey() + ":               " + entry.getValue()));
                    		 strb2.append("\n");

                    	 }
                    	 SelectResult=null;
                    	 editorPane_2.setText(strb2.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	 }
            }
        });

        JButton btnClearAll = new JButton("Clear all");
        btnClearAll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modelHRM.removeAllElements();
            	modelDiag.removeAllElements();
            	modelImp.removeAllElements();
            	modelBravo.removeAllElements();
            	modelBT.removeAllElements();
            	modelEndoscopy.removeAllElements();
            	modelHistology.removeAllElements();

            	editorPane_2.setText("");
            	txtSummary.setText("");

            	list_3.setListData(new Vector());
            	list_4.setListData(new Vector());
            	list_BreathTest.setListData(new Vector());
                list_Bravos.setListData(new Vector());
                list_Diag.setListData(new Vector());
                list_Endoscopy.setListData(new Vector());
                list_Histology.setListData(new Vector());
        	}
        });

        txtSummary = new JTextField();
        txtSummary.setColumns(10);









        GroupLayout gl_panel_RptMaker = new GroupLayout(panel_RptMaker);
        gl_panel_RptMaker.setHorizontalGroup(
        	gl_panel_RptMaker.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_RptMaker.createSequentialGroup()
        			.addGap(378)
        			.addComponent(btnClearAll)
        			.addContainerGap(756, Short.MAX_VALUE))
        		.addGroup(gl_panel_RptMaker.createSequentialGroup()
        			.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panel_RptMaker.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(scrollPanePrev, GroupLayout.PREFERRED_SIZE, 1118, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel_RptMaker.createSequentialGroup()
        					.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        						.addGroup(Alignment.TRAILING, gl_panel_RptMaker.createSequentialGroup()
        							.addGap(65)
        							.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        								.addGroup(gl_panel_RptMaker.createSequentialGroup()
        									.addComponent(lblPreviewPane)
        									.addPreferredGap(ComponentPlacement.RELATED, 763, Short.MAX_VALUE))
        								.addComponent(txtSummary, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
        								.addComponent(lblReportSummary))
        							.addGap(81)
        							.addComponent(lblEndoscopy))
        						.addGroup(gl_panel_RptMaker.createSequentialGroup()
        							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.TRAILING)
        								.addGroup(gl_panel_RptMaker.createSequentialGroup()
        									.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        										.addComponent(lblHrmReports)
        										.addComponent(list_3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        									.addGap(31)
        									.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        										.addComponent(lblImpedanceReports)
        										.addComponent(list_4, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
        									.addGap(18)
        									.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        										.addComponent(lblBreathTest)
        										.addComponent(list_BreathTest, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
        									.addPreferredGap(ComponentPlacement.RELATED))
        								.addGroup(gl_panel_RptMaker.createSequentialGroup()
        									.addComponent(btnSearchHospitalNumber)
        									.addGap(31)))
        							.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        								.addGroup(gl_panel_RptMaker.createSequentialGroup()
        									.addGap(19)
        									.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        										.addComponent(lblBravos)
        										.addComponent(list_Bravos, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
        									.addGap(31)
        									.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        										.addGroup(gl_panel_RptMaker.createSequentialGroup()
        											.addComponent(list_Diag, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
        											.addGap(18)
        											.addComponent(list_Endoscopy, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
        										.addComponent(lblFinalReports)))
        								.addGroup(gl_panel_RptMaker.createSequentialGroup()
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
        							.addPreferredGap(ComponentPlacement.RELATED)))
        					.addGap(23)
        					.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblHistology)
        						.addComponent(list_Histology, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))))
        			.addGap(46))
        );
        gl_panel_RptMaker.setVerticalGroup(
        	gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_RptMaker.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnSearchHospitalNumber, Alignment.TRAILING)
        				.addComponent(textField_16, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(12)
        			.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblHrmReports)
        				.addComponent(lblImpedanceReports)
        				.addComponent(lblBreathTest)
        				.addComponent(lblBravos)
        				.addComponent(lblFinalReports)
        				.addComponent(lblEndoscopy)
        				.addComponent(lblHistology))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(list_Histology, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addGroup(gl_panel_RptMaker.createParallelGroup(Alignment.BASELINE)
        					.addComponent(list_3, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        					.addComponent(list_4, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        					.addComponent(list_BreathTest, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        					.addComponent(list_Bravos, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        					.addComponent(list_Diag, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        					.addComponent(list_Endoscopy, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
        			.addGap(44)
        			.addComponent(btnClearAll)
        			.addGap(19)
        			.addComponent(lblPreviewPane)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPanePrev, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
        			.addComponent(lblReportSummary)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtSummary, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        			.addGap(44))
        );
        panel_RptMaker.setLayout(gl_panel_RptMaker);

        JPanel panel_Compare = new JPanel();
        panel_Compare.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Compare", null, panel_Compare, null);
        JTextArea textArea_1 = new JTextArea();
        JTextArea textArea_2 = new JTextArea();
        textArea_2.setForeground(new Color(0, 0, 0));
        textArea_2.setBackground(new Color(255, 250, 205));
        JButton btnExecute = new JButton("Execute");
        btnExecute.addActionListener(new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent ae){
               String getValue = textArea_1.getText();
                              try {
								HookMeUp(table_1,getValue);
							} catch (ClassNotFoundException e) {

								e.printStackTrace();
							} catch (IOException e) {

								e.printStackTrace();
							} catch (SQLException e) {

								e.printStackTrace();
							} catch (ParseException e) {

								e.printStackTrace();
							}
            }
         });


        JButton btnPostbox = new JButton("Postbox");

    	btnPostbox.addActionListener(new ActionListener() {

    		@Override
			public void actionPerformed(ActionEvent e) {
    			String mylist=null;
    			try {

    				mylist=pbox();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
    			textArea_1.append(" Where "+mylist);
    	      }
    	    });

        JButton btnExecute_1 = new JButton("Execute");
        btnExecute_1.addActionListener(new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent ae){
               String getValue = textArea_2.getText();
                              try {
								HookMeUp(table_2,getValue);
							} catch (ClassNotFoundException e) {

								e.printStackTrace();
							} catch (IOException e) {

								e.printStackTrace();
							} catch (SQLException e) {

								e.printStackTrace();
							} catch (ParseException e) {

								e.printStackTrace();
							}
            }
         });

        JButton btnPostbox_1 = new JButton("Postbox");
        btnPostbox_1.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			String mylist=null;
    			try {
    				mylist=pbox();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
    			textArea_2.append(" Where "+mylist);
    	      }
    	    });

        JLabel lblTtestComparison = new JLabel("t-test comparison");

        textField = new JTextField();
        textField.setForeground(Color.BLACK);
        textField.setColumns(10);


        JButton btnCompare = new JButton("Compare");

        btnCompare.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {

        	                Object[] data_L = columnToArray(table_1,table_1.getSelectedColumn());
        	                DescriptiveStatistics stats_L = new DescriptiveStatistics();
        	                ArrayList<Double> weights_L =new ArrayList<Double>();
        	                ArrayList<Double> weights_R =new ArrayList<Double>();
        	                Object [] data_R = columnToArray(table_2,table_2.getSelectedColumn());


        	             // DecimalFormat df = new DecimalFormat("#.00");
        	              //System.out.println("MY data length"+data_L.length);
        	              try {
        	            	  //Add the column array to the descriptive stats
        	                  for(int i = 0; i < data_L.length; i++){
        	                      if (data_L[i]!=null){
        	                      try {
        	                    	  stats_L.addValue(Double.parseDouble((data_L[i]).toString().trim()));
        	                    	  weights_L.add(Double.parseDouble((data_L[i]).toString().trim()));

        	                      } catch (Exception e1) {
        	                          //System.out.println("nought to add here");
        	                      }
        	                      }
        	                  }


        	                  for(int i = 0; i < data_R.length; i++){
        	                      if (data_R[i]!=null){
        	                      try {
        	                    	  stats_L.addValue(Double.parseDouble((data_R[i]).toString().trim()));
        	                          weights_R.add(Double.parseDouble((data_R[i]).toString().trim()));
        	                      } catch (Exception e1) {
        	                          //System.out.println("nought to add here");
        	                      }
        	                      }
        	                  }

        	                  //This is for the compare button and produces a t-test as well as a box and whisker plot
        	                  TTest studentTest = new TTestImpl();


        	   final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        	   double[] weights_LArr = new double[weights_L.size()];
        	                  List<List<Double>> Overall = new ArrayList<List<Double>>();
        	                  List<Double> listL = new ArrayList<Double>();


        	                  for ( int i = 0; i < weights_LArr.length; i++) {

        	                	  weights_LArr[i] = weights_L.get(i).doubleValue();  // java 1.4 style
        	                     // or:
        	                	  weights_LArr[i] = weights_L.get(i);                // java 1.5+ style (outboxing)
        	                	  listL.add(weights_L.get(i));


        	                  }
        	                  dataset.add(listL, "1 ", " Number ");

        	                  double[] weights_RArr = new double[weights_R.size()];
        	                  List<Double> listR = new ArrayList<Double>();
        	                  for ( int i = 0; i < weights_RArr.length; i++) {

        	                	  weights_RArr[i] = weights_R.get(i).doubleValue();  // java 1.4 style
        	                     // or:
        	                	  weights_RArr[i] = weights_R.get(i);
        	                	  listR.add(weights_R.get(i));    // java 1.5+ style (outboxing)


        	                  }
        	                dataset.add(listR, "2 ", " Number ");
          	                double testResult = studentTest.tTest(weights_LArr, weights_RArr);
          	                textField.setText(Double.toString(testResult));




          	            try {
							final BoxAndWhiskerCategoryDataset datasetBW = dataset;
							final CategoryAxis xAxis = new CategoryAxis("Type");
							final NumberAxis yAxis = new NumberAxis("Value");
							yAxis.setAutoRangeIncludesZero(false);
							final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
							renderer.setFillBox(false);
							//renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
							final CategoryPlot plot = new CategoryPlot(datasetBW, xAxis, yAxis, renderer);

							final JFreeChart chart = new JFreeChart(
							    "PhysiPop Comparator",
							    new Font("SansSerif", Font.BOLD, 14),
							    plot,
							    true
							);
							final ChartPanel chartPanel = new ChartPanel(chart);
     	         // ChartPanel chartpanel = new ChartPanel(chart);
     	        chartPanel.setDomainZoomable(true);

     	        JPanel jPanel4 = new JPanel();
     	        jPanel4.setLayout(new BorderLayout());
     	        jPanel4.add(chartPanel, BorderLayout.NORTH);

     	        JFrame frame = new JFrame();
     	        frame.getContentPane().add(jPanel4);
     	        frame.pack();
     	        frame.setVisible(true);
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

        	              } catch (NumberFormatException e1) {
        	                  //System.out.println("You haven't selected a numeric column. Please select again");
        	              } catch (IllegalArgumentException e1) {

							e1.printStackTrace();
						} catch (MathException e1) {

							e1.printStackTrace();
						}


        	}

        });



               GroupLayout gl_panel_Compare = new GroupLayout(panel_Compare);
               gl_panel_Compare.setHorizontalGroup(
               	gl_panel_Compare.createParallelGroup(Alignment.LEADING)
               		.addGroup(gl_panel_Compare.createSequentialGroup()
               			.addGap(37)
               			.addGroup(gl_panel_Compare.createParallelGroup(Alignment.TRAILING)
               				.addGroup(gl_panel_Compare.createSequentialGroup()
               					.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
               					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
               					.addGroup(gl_panel_Compare.createParallelGroup(Alignment.LEADING)
               						.addComponent(btnExecute)
               						.addComponent(btnPostbox)))
               				.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE))
               			.addPreferredGap(ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
               			.addGroup(gl_panel_Compare.createParallelGroup(Alignment.TRAILING, false)
               				.addGroup(gl_panel_Compare.createSequentialGroup()
               					.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
               					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               					.addGroup(gl_panel_Compare.createParallelGroup(Alignment.LEADING)
               						.addComponent(btnExecute_1)
               						.addComponent(btnPostbox_1)))
               				.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE))
               			.addGap(31))
               		.addGroup(gl_panel_Compare.createSequentialGroup()
               			.addGap(461)
               			.addComponent(btnCompare)
               			.addPreferredGap(ComponentPlacement.UNRELATED)
               			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               			.addContainerGap(522, Short.MAX_VALUE))
               		.addGroup(gl_panel_Compare.createSequentialGroup()
               			.addGap(545)
               			.addComponent(lblTtestComparison)
               			.addContainerGap(569, Short.MAX_VALUE))
               );
               gl_panel_Compare.setVerticalGroup(
               	gl_panel_Compare.createParallelGroup(Alignment.LEADING)
               		.addGroup(gl_panel_Compare.createSequentialGroup()
               			.addGap(9)
               			.addGroup(gl_panel_Compare.createParallelGroup(Alignment.LEADING, false)
               				.addGroup(gl_panel_Compare.createSequentialGroup()
               					.addGap(6)
               					.addComponent(btnExecute)
               					.addPreferredGap(ComponentPlacement.RELATED)
               					.addComponent(btnPostbox)
               					.addGap(30)
               					.addGroup(gl_panel_Compare.createParallelGroup(Alignment.BASELINE)
               						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 611, GroupLayout.PREFERRED_SIZE)
               						.addComponent(scrollPane2, 0, 0, Short.MAX_VALUE)))
               				.addGroup(gl_panel_Compare.createParallelGroup(Alignment.BASELINE)
               					.addGroup(gl_panel_Compare.createSequentialGroup()
               						.addComponent(btnExecute_1)
               						.addPreferredGap(ComponentPlacement.RELATED)
               						.addComponent(btnPostbox_1))
               					.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
               					.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
               			.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
               			.addComponent(lblTtestComparison)
               			.addGap(18)
               			.addGroup(gl_panel_Compare.createParallelGroup(Alignment.BASELINE)
               				.addComponent(btnCompare)
               				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
               			.addGap(20))
               );
        panel_Compare.setLayout(gl_panel_Compare);



        JPanel panel_OtherReports = new JPanel();
        tabbedPane.addTab("Reports", null, panel_OtherReports, null);

            	Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
            	File root=new File(".");

try {
	if (userPrefs.get("PathForGRS",null).contains("/")||userPrefs.get("PathForGRS",null).contains("\\")){
		 root = new File(userPrefs.get("PathForGRS",null));
	}
	else{
		 root=new File(System.getProperty("user.dir"));
		 System.out.println("Yes mate noGRS innit");
	}
} catch (Exception e3) {
	// TODO Auto-generated catch block
	e3.printStackTrace();
}

                {
                    JFrame f = new JFrame(root.toString());
                    f.addWindowListener(new WindowAdapter()
                    {
                        public void windowClosing(WindowEvent e)
                        {
                            System.exit(0);
                        }
                    });
                }

                JFXPanel panel_WebViewer = new JFXPanel();
                JLabel lblFinalTable = new JLabel("Final Table");
                lblFinalTable.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
                JTree treeDir = new JTree(new FileTreeModel(root));
                JScrollPane scrollTree = new JScrollPane(treeDir);
                scrollTree.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollTree.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                                treeDir.addMouseListener(new MouseAdapter() {
                                	@Override
                                	public void mouseClicked(MouseEvent e) {
                                        		String filetoOpen= new String();
                                        		//File starting = new File(userPrefs.get("PathForGRS", null).toString().trim());
                                                File fto =new File(treeDir.getSelectionPath().getLastPathComponent().toString().trim());
                                        		//System.out.println("FTO"+fto);



                                                Object[] columnnames;
                                        		CSVReader CSVFileReader = null;
                                        		try {
                                        			open(fto);
                        							CSVFileReader = new CSVReader(new FileReader(userPrefs.get("PathForMyCSV", null)));
                        							try {
                        								File ftohtml=new File(userPrefs.get("PathForHTML", null));

																 Platform.runLater(() -> {
													                    WebView webView = new WebView();
													                    panel_WebViewer.setScene(new Scene(webView));
													                    try {
																			webView.getEngine().load(ftohtml.toURI().toURL().toString());
																		} catch (Exception e1) {
																			// TODO Auto-generated catch block
																			e1.printStackTrace();
																		}
													                });




													} catch (Exception e1) {
														e1.printStackTrace();
													}

                        						} catch (IOException | ScriptException | InterruptedException e2) {
                        							e2.printStackTrace();
                        						}
                                        		 List myEntries = null;
                        							try {
                        								myEntries = CSVFileReader.readAll();

                        							} catch (IOException e1) {
                        								e1.printStackTrace();
                        							}
                                     		      columnnames = (String[]) myEntries.get(0);
                                     		      DefaultTableModel tableModel = new DefaultTableModel(columnnames, myEntries.size()-1);
                                     		       int rowcount = tableModel.getRowCount();

                                     		      for (int x = 0; x<rowcount+1; x++)
                                   		       {
                                   		          int columnnumber = 0;
                                   		         // if x = 0 this is the first row...skip it... data used for columnnames
                                   		         if (x>0)
                                   		         {
                                   		       for (String thiscellvalue : (String[])myEntries.get(x))
                                   		       {
                                   		    	   //System.out.println(thiscellvalue);
                                   		           tableModel.setValueAt(thiscellvalue, x-1, columnnumber);
                                   		          columnnumber++;
                                   		       }
                                   		         }
                                   		       }

                                	}
                                });





                JScrollPane scrollWebview = new JScrollPane(panel_WebViewer);
                scrollWebview.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollWebview.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                JButton btnEmailTheReport = new JButton("Email the Report");

                JTextPane totextPane_ROutput = new JTextPane();




                GroupLayout gl_panel_OtherReports = new GroupLayout(panel_OtherReports);
                gl_panel_OtherReports.setHorizontalGroup(
                	gl_panel_OtherReports.createParallelGroup(Alignment.TRAILING)
                		.addGroup(gl_panel_OtherReports.createSequentialGroup()
                			.addGroup(gl_panel_OtherReports.createParallelGroup(Alignment.LEADING)
                				.addGroup(gl_panel_OtherReports.createSequentialGroup()
                					.addGap(71)
                					.addComponent(lblFinalTable))
                				.addGroup(gl_panel_OtherReports.createSequentialGroup()
                					.addGap(16)
                					.addGroup(gl_panel_OtherReports.createParallelGroup(Alignment.LEADING)
                						.addComponent(btnEmailTheReport)
                						.addGroup(gl_panel_OtherReports.createSequentialGroup()
                							.addComponent(scrollWebview, GroupLayout.PREFERRED_SIZE, 966, GroupLayout.PREFERRED_SIZE)
                							.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                							.addGroup(gl_panel_OtherReports.createParallelGroup(Alignment.TRAILING)
                								.addComponent(scrollTree, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                								.addComponent(totextPane_ROutput, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))))))
                			.addContainerGap())
                );
                gl_panel_OtherReports.setVerticalGroup(
                	gl_panel_OtherReports.createParallelGroup(Alignment.TRAILING)
                		.addGroup(gl_panel_OtherReports.createSequentialGroup()
                			.addContainerGap()
                			.addComponent(lblFinalTable)
                			.addGap(21)
                			.addGroup(gl_panel_OtherReports.createParallelGroup(Alignment.LEADING)
                				.addGroup(gl_panel_OtherReports.createSequentialGroup()
                					.addComponent(scrollTree, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                					.addGap(42)
                					.addComponent(totextPane_ROutput, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
                				.addComponent(scrollWebview, GroupLayout.PREFERRED_SIZE, 693, GroupLayout.PREFERRED_SIZE))
                			.addGap(32)
                			.addComponent(btnEmailTheReport)
                			.addGap(436))
                );
                panel_OtherReports.setLayout(gl_panel_OtherReports);

        JPanel panel_Settings = new JPanel();
        panel_Settings.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Settings", null, panel_Settings, null);

        txtPathForHRM = new JTextField();
        txtPathForHRM.setText(prefs.get("PathForHRM", "NOPE"));
        txtPathForHRM.setColumns(10);

        txtPathForImpedance = new JTextField();
        txtPathForImpedance.setText(prefs.get("PathForImpedance", "NOPE"));
        txtPathForImpedance.setColumns(10);

        txtPathForDiagnosis = new JTextField();
        txtPathForDiagnosis.setText(prefs.get("PathForDiagnosis", "NOPE"));
        txtPathForDiagnosis.setColumns(10);

        txtPathForBRAVO = new JTextField();
        txtPathForBRAVO.setText(prefs.get("PathForBRAVO", "NOPE"));
        txtPathForBRAVO.setColumns(10);

        txtPathForBreath = new JTextField();
        txtPathForBreath.setText(prefs.get("PathForBreath", "NOPE"));
        txtPathForBreath.setColumns(10);



        JButton btnRefreshFileIndexing = new JButton("Refresh File Indexing");
        btnRefreshFileIndexing.setBackground(new Color(255, 255, 102));
        btnRefreshFileIndexing.setForeground(Color.RED);
        btnRefreshFileIndexing.addActionListener(new ActionListener() {

        	// PrintStream printStream = new PrintStream(new CustomOutputStream(textArea_3));
        	 @Override
			public void actionPerformed(ActionEvent e) {
        		try {
        			System.out.println("Hi frmo the console");
        			//System.setOut(printStream);
      		       // System.setErr(printStream);

        			Iterator.main(null);
        			System.out.println("Over the iterator");
        			PDFont.clearResources();
        			System.gc();

        			} catch (IOException e1) {

					e1.printStackTrace();
				} catch (SAXException e1) {

					e1.printStackTrace();
				} catch (TikaException e1) {

					e1.printStackTrace();
				} catch (SQLException e1) {

					e1.printStackTrace();
				} catch (ParseException e1) {

					e1.printStackTrace();
				} catch (URISyntaxException e1) {

					e1.printStackTrace();
				} catch (BackingStoreException e1) {

					e1.printStackTrace();
				}
        		PDFont.clearResources();
        		System.gc();
        	}
        });

        JLabel lblFileLocationsFor = new JLabel("File locations for indexing");
        lblFileLocationsFor.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

        txtForEndoscopy = new JTextField();
        txtForEndoscopy.setColumns(10);

                txtForHistology = new JTextField();
                txtForHistology.setColumns(10);

                txtForDB = new JTextField();
                txtForDB.setText(prefs.get("PathForDB", null));
                txtForDB.setColumns(10);

                JLabel lblLocationOfDatabase = new JLabel("Location of Database");
                lblLocationOfDatabase.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

                textForMyCSVOutput = new JTextField();
                textForMyCSVOutput.setText(prefs.get("PathForMyCSV", null));
                textForMyCSVOutput.setColumns(10);

                txtForLogger = new JTextField();
                txtForLogger.setText(prefs.get("PathForLogger", null));
                txtForLogger.setColumns(10);
//System.out.println("The Logger path is"+prefs.get("PathForLogger", null));


                //txtPathToR


                JLabel lblLocationOfCsv = new JLabel("Location of CSV output");
                lblLocationOfCsv.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

                JButton btnRememberNewSettings = new JButton("Remember new settings");
                btnRememberNewSettings.setBackground(new Color(255, 255, 102));
                btnRememberNewSettings.setForeground(Color.RED);
                btnRememberNewSettings.addActionListener(new ActionListener() {
                	@Override
					public void actionPerformed(ActionEvent e) {
                		System.out.println("HERE IT IS"+getClass().getClassLoader().getResource("logging.properties"));
                    	if(txtPathForBRAVO!=null){
                    		prefs.put("PathForBRAVO", txtPathForBRAVO.getText());
                    	}
                    	if(txtPathForImpedance!=null){
                    		prefs.put("PathForImpedance", txtPathForImpedance.getText());
                    	}
                    	if(txtPathForHRM!=null){
                    		prefs.put("PathForHRM", txtPathForHRM.getText());
                    	}
                    	if(txtPathForDiagnosis!=null){
                    		prefs.put("PathForDiagnosis", txtPathForDiagnosis.getText());
                    	}
                    	if(txtPathForBreath!=null){
                    		prefs.put("PathForBreath", txtPathForBreath.getText());
                    		//System.out.println("The output"+txtPathForBreath.getText());
                    	}
                    	if(txtForEndoscopy!=null){
                    		prefs.put("PathForEndocopy", txtForEndoscopy.getText());
                    		//System.out.println("The output"+txtForEndoscopy.getText());
                    	}
                    	if(txtForHistology!=null){
                    		prefs.put("PathForHistology", txtForHistology.getText());
                    		//System.out.println("The output"+txtForHistology.getText());
                    	}
                    	if(txtForDB!=null){
                    		prefs.put("PathForDB", txtForDB.getText());
                    		//System.out.println("The output"+txtForDB.getText());
                    	}
                    	if(textForMyCSVOutput!=null){
                    		prefs.put("PathForMyCSV", textForMyCSVOutput.getText());
                    		//System.out.println("The output"+textForMyCSVOutput.getText());
                    	}
                    	if(txtForLogger!=null){
                    		prefs.put("PathForLogger", txtForLogger.getText());

                    	}
                    	if(txtPathToR!=null){
                    		//Removed as it was getting too many files for some reason
                    		//prefs.put("PathToR", txtPathToR.getText());
                    		//System.out.println("The output"+txtForLogger.getText());
                    	}
                    	if(txtForError!=null){
                    		prefs.put("PathForError", txtForError.getText());
                    		System.out.println("The output"+txtForLogger.getText());
                    	}
                    	if(textForReports_1!=null){
                    		prefs.put("PathForGRS", textForReports_1.getText());
                    	}
                    	if(textField_html!=null){
                    		prefs.put("PathForHTML", textField_html.getText());
                    	}
                    	if(textForExtra1!=null){
                    		prefs.put("PathForExtra1", textForExtra1.getText());
                    	}
                    	if(textForExtra2!=null){
                    		prefs.put("PathForExtra2", textForExtra2.getText());
                    	}
                    	if(textForExtra3!=null){
                    		prefs.put("PathForExtra3", textForExtra3.getText());
                    	}
                    	if(textForExtra4!=null){
                    		prefs.put("PathForExtra4", textForExtra4.getText());
                    	}


                	}
                });




                JLabel lblLocationOfFile = new JLabel("Location of Logger");
                lblLocationOfFile.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

                JButton HRMFileChooser = new JButton("HRM");
                HRMFileChooser.addActionListener(new ActionListener() {
                	@Override
					public void actionPerformed(ActionEvent e) {
                		JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                          File selectedFile = fileChooser.getSelectedFile();
                          txtPathForHRM.setText(fileChooser.getCurrentDirectory().toString());
                          //System.out.println(selectedFile.getName());
                        }
                	}
                });

                JButton btnBravo = new JButton("BRAVO");
                btnBravo.addActionListener(new ActionListener() {
                	@Override
					public void actionPerformed(ActionEvent e) {
                		JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                          File selectedFile = fileChooser.getSelectedFile();
                          txtPathForBRAVO.setText(fileChooser.getCurrentDirectory().toString());
                          //System.out.println(selectedFile.getName());
                        }
                	}
                });

                JButton btnImpedance = new JButton("Impedance");
                btnImpedance.addActionListener(new ActionListener() {
                	@Override
					public void actionPerformed(ActionEvent e) {
                		JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                          File selectedFile = fileChooser.getSelectedFile();
                          txtPathForImpedance.setText(fileChooser.getCurrentDirectory().toString());
                          //System.out.println(selectedFile.getName());
                        }
                	}
                });

                JButton btnDiagnoses = new JButton("Diagnoses");
                btnDiagnoses.addActionListener(new ActionListener() {
                	@Override
					public void actionPerformed(ActionEvent e) {
                		JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                          File selectedFile = fileChooser.getSelectedFile();
                          txtPathForDiagnosis.setText(fileChooser.getCurrentDirectory().toString());
                          //System.out.println(selectedFile.getName());
                        }
                	}
                });
                JButton btnBreathTests = new JButton("Breath Tests");
                btnBreathTests.addActionListener(new ActionListener() {
                	@Override
					public void actionPerformed(ActionEvent e) {
                		JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                          File selectedFile = fileChooser.getSelectedFile();
                          txtPathForBreath.setText(fileChooser.getCurrentDirectory().toString());
                          //System.out.println(selectedFile.getName());
                        }
                	}
                });

                JLabel lblLocationOfDatabase_1 = new JLabel("Location of DB backup");
                lblLocationOfDatabase_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

                txtDBBackup = new JTextField();
                txtDBBackup.setColumns(10);

                JButton btnManualDbBackup = new JButton("Manual DB Backup");
                btnManualDbBackup.setForeground(new Color(255, 0, 0));

                JLabel lblLocationOfR = new JLabel("Location of R");
                lblLocationOfR.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

                txtPathToR = new JTextField();
                txtPathToR.setColumns(10);
                txtPathToR.setText(prefs.get("PathToR", null));

                JLabel lblLocationOfError = new JLabel("Location of error logs");

                txtForError = new JTextField();
                txtForError.setColumns(10);
                txtForError.setText(prefs.get("PathForError", null));

                JButton btnError = new JButton("Error log");

                btnError.addActionListener(new ActionListener() {
                	@Override
					public void actionPerformed(ActionEvent e) {
                		JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                          File selectedFile = fileChooser.getSelectedFile();
                          txtForError.setText(fileChooser.getCurrentDirectory().toString());
                          //System.out.println(selectedFile.getName());
                        }
                	}
                });

                textForReports_1 = new JTextField();
                textForReports_1.setColumns(10);
                textForReports_1.setText(prefs.get("PathForGRS",null));


                JButton btnScripts = new JButton("Scripts");
                btnScripts.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                          File selectedFile = fileChooser.getSelectedFile();
                          textForReports_1.setText(fileChooser.getCurrentDirectory().toString());
                          //System.out.println(selectedFile.getName());
                        }

                	}
                });

                JLabel lblLocationOfHtml = new JLabel("Location of html output");
                lblLocationOfHtml.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));

                textField_html = new JTextField();
                textField_html.setColumns(10);
                textField_html.setText(prefs.get("PathForHTML", "NOPE"));

                JLabel lblExpungersdevtOnly = new JLabel("Expungers-devt only");

                JButton btnDeletebt = new JButton("DeleteBT");
                btnDeletebt.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		String getValue="BreathTests";
							try {
								HookMeUpExpunger(getValue);
								RemoveLineInFile takeit =new RemoveLineInFile();
								takeit.removeLineFromFile(" LL ");
							} catch (ClassNotFoundException | IOException | SQLException | ParseException e1) {
								e1.printStackTrace();
							}
                	}
                });

                JButton btnDeletebravo = new JButton("DeleteBravo");
                btnDeletebravo.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		try {
                			String getValue1="BravoDay1And2";
                			String getValue2="BravoTotal";
							HookMeUpExpunger(getValue1);
							HookMeUpExpunger(getValue2);
							RemoveLineInFile takeit =new RemoveLineInFile();
							takeit.removeLineFromFile("*.[Bb][Rr][Aa][Vv][Oo].*");
						} catch (ClassNotFoundException | IOException | SQLException | ParseException e1) {
							e1.printStackTrace();
						}

                	}
                });


                JButton btnDeleteimp = new JButton("DeleteImp");
                btnDeleteimp.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		try {
                			String getValue1="Impedance2";
                			String getValue2="Imp_Symp";
							HookMeUpExpunger(getValue1);
							HookMeUpExpunger(getValue2);
							RemoveLineInFile takeit =new RemoveLineInFile();
							takeit.removeLineFromFile("*.[Ii][Mm][Pp].*");
						} catch (ClassNotFoundException | IOException | SQLException | ParseException e1) {
							e1.printStackTrace();
						}

                	}
                });

                JButton btnDeletehrm = new JButton("DeleteHRM");
                btnDeletehrm.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		try {
                			String getValue1="HRMImportMain";
                			String getValue2="HRMImportSwallows";
							HookMeUpExpunger(getValue1);
							HookMeUpExpunger(getValue2);
							RemoveLineInFile.removeLineFromFile("*.[Hh][Rr][Mm].*");
						} catch (ClassNotFoundException | IOException | SQLException | ParseException e1) {
							e1.printStackTrace();
						}
                	}
                });

                JButton btnDeletediag = new JButton("DeleteDiag");
                btnDeletediag.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		try {
                			String getValue="Diag";
							HookMeUpExpunger(getValue);
							RemoveLineInFile takeit =new RemoveLineInFile();
							takeit.removeLineFromFile(".*[Ff][Ii][Nn][Aa][Ll].*");
						} catch (ClassNotFoundException | IOException | SQLException | ParseException e1) {
							e1.printStackTrace();
						}
                	}
                });

                textForExtra1 = new JTextField();
                textForExtra1.setColumns(10);
                textForExtra1.setText(prefs.get("PathForExtra1", null));
                textForExtra2 = new JTextField();
                textForExtra2.setColumns(10);
                textForExtra2.setText(prefs.get("PathForExtra2", null));
                textForExtra3 = new JTextField();
                textForExtra3.setColumns(10);
                textForExtra3.setText(prefs.get("PathForExtra3", null));
                textForExtra4 = new JTextField();
                textForExtra4.setColumns(10);
                textForExtra4.setText(prefs.get("PathForExtra4", null));
                GroupLayout gl_panel_Settings = new GroupLayout(panel_Settings);
                gl_panel_Settings.setHorizontalGroup(
                	gl_panel_Settings.createParallelGroup(Alignment.TRAILING)
                		.addGroup(gl_panel_Settings.createSequentialGroup()
                			.addContainerGap()
                			.addGroup(gl_panel_Settings.createParallelGroup(Alignment.TRAILING)
                				.addGroup(gl_panel_Settings.createSequentialGroup()
                					.addComponent(lblFileLocationsFor)
                					.addGap(58))
                				.addGroup(gl_panel_Settings.createSequentialGroup()
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.TRAILING, false)
                						.addComponent(btnBreathTests, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                						.addComponent(btnBravo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                						.addComponent(btnDiagnoses, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                						.addComponent(btnImpedance, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                						.addComponent(HRMFileChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                					.addGap(27)
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING)
                						.addGroup(gl_panel_Settings.createParallelGroup(Alignment.TRAILING, false)
                							.addComponent(txtForHistology, Alignment.LEADING)
                							.addComponent(txtForEndoscopy, Alignment.LEADING)
                							.addComponent(txtPathForDiagnosis, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                							.addComponent(txtPathForHRM, Alignment.LEADING)
                							.addComponent(txtPathForImpedance, Alignment.LEADING)
                							.addComponent(txtPathForBRAVO, Alignment.LEADING))
                						.addComponent(txtPathForBreath, 185, 185, 185)))
                				.addGroup(gl_panel_Settings.createSequentialGroup()
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING, false)
                						.addComponent(btnRememberNewSettings, GroupLayout.PREFERRED_SIZE, 183, Short.MAX_VALUE)
                						.addComponent(btnRefreshFileIndexing)
                						.addComponent(textForExtra4)
                						.addComponent(textForExtra1, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                						.addComponent(textForExtra2)
                						.addComponent(textForExtra3))
                					.addPreferredGap(ComponentPlacement.RELATED)))
                			.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING)
                				.addGroup(gl_panel_Settings.createSequentialGroup()
                					.addGap(216)
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING)
                						.addComponent(textField_html, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                						.addComponent(lblLocationOfR)
                						.addGroup(gl_panel_Settings.createSequentialGroup()
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING)
                								.addComponent(lblLocationOfDatabase)
                								.addComponent(txtForDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(lblLocationOfCsv)
                								.addComponent(textForMyCSVOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(lblLocationOfFile)
                								.addComponent(txtForLogger, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(txtDBBackup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(lblLocationOfDatabase_1)
                								.addComponent(txtPathToR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(lblLocationOfHtml))
                							.addPreferredGap(ComponentPlacement.RELATED)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING)
                								.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING, false)
                									.addGroup(gl_panel_Settings.createSequentialGroup()
                										.addComponent(btnError)
                										.addPreferredGap(ComponentPlacement.RELATED)
                										.addGroup(gl_panel_Settings.createParallelGroup(Alignment.TRAILING)
                											.addComponent(txtForError, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                											.addComponent(lblLocationOfError)))
                									.addGroup(gl_panel_Settings.createSequentialGroup()
                										.addComponent(btnScripts)
                										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                										.addComponent(textForReports_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                								.addGroup(gl_panel_Settings.createSequentialGroup()
                									.addGap(264)
                									.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING)
                										.addComponent(btnDeletebt)
                										.addComponent(btnDeleteimp)
                										.addComponent(btnDeletehrm)
                										.addComponent(lblExpungersdevtOnly)
                										.addComponent(btnDeletebravo)
                										.addComponent(btnDeletediag))))))
                					.addGap(140))
                				.addGroup(Alignment.TRAILING, gl_panel_Settings.createSequentialGroup()
                					.addGap(615)
                					.addComponent(btnManualDbBackup)
                					.addGap(136))))
                );
                gl_panel_Settings.setVerticalGroup(
                	gl_panel_Settings.createParallelGroup(Alignment.TRAILING)
                		.addGroup(gl_panel_Settings.createSequentialGroup()
                			.addContainerGap(6, Short.MAX_VALUE)
                			.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING)
                				.addGroup(gl_panel_Settings.createSequentialGroup()
                					.addPreferredGap(ComponentPlacement.RELATED)
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.TRAILING)
                						.addGroup(gl_panel_Settings.createSequentialGroup()
                							.addComponent(lblFileLocationsFor)
                							.addGap(18)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(txtPathForHRM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(HRMFileChooser))
                							.addGap(19)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(txtPathForImpedance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(btnImpedance))
                							.addGap(18)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(txtPathForDiagnosis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(btnDiagnoses))
                							.addGap(18)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(txtPathForBRAVO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(btnBravo))
                							.addGap(18)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(txtPathForBreath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(btnBreathTests)))
                						.addGroup(gl_panel_Settings.createSequentialGroup()
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(lblLocationOfDatabase)
                								.addComponent(lblLocationOfError))
                							.addPreferredGap(ComponentPlacement.RELATED)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(txtForError, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(btnError))
                							.addGap(18)
                							.addComponent(lblLocationOfCsv)
                							.addPreferredGap(ComponentPlacement.RELATED)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(textForMyCSVOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                								.addComponent(btnScripts)
                								.addComponent(textForReports_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                							.addGap(18)
                							.addComponent(lblLocationOfFile)
                							.addPreferredGap(ComponentPlacement.RELATED)
                							.addComponent(txtForLogger, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                					.addGap(18)
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING)
                						.addGroup(gl_panel_Settings.createSequentialGroup()
                							.addComponent(txtForEndoscopy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                							.addGap(18)
                							.addComponent(txtForHistology, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                						.addGroup(gl_panel_Settings.createSequentialGroup()
                							.addComponent(lblLocationOfDatabase_1)
                							.addPreferredGap(ComponentPlacement.RELATED)
                							.addComponent(txtDBBackup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                							.addGap(18)
                							.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                								.addComponent(lblLocationOfR)
                								.addComponent(lblExpungersdevtOnly)))))
                				.addGroup(gl_panel_Settings.createSequentialGroup()
                					.addGap(99)
                					.addComponent(txtForDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                			.addGap(10)
                			.addGroup(gl_panel_Settings.createParallelGroup(Alignment.LEADING, false)
                				.addGroup(Alignment.TRAILING, gl_panel_Settings.createSequentialGroup()
                					.addComponent(btnDeletebt)
                					.addPreferredGap(ComponentPlacement.RELATED)
                					.addComponent(btnDeletebravo)
                					.addPreferredGap(ComponentPlacement.RELATED)
                					.addComponent(btnDeletediag)
                					.addGap(6)
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                						.addComponent(btnDeleteimp)
                						.addComponent(textForExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                					.addPreferredGap(ComponentPlacement.RELATED)
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                						.addComponent(btnDeletehrm)
                						.addComponent(textForExtra4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                				.addGroup(gl_panel_Settings.createSequentialGroup()
                					.addGap(8)
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                						.addComponent(txtPathToR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                						.addComponent(textForExtra1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                					.addGap(18)
                					.addGroup(gl_panel_Settings.createParallelGroup(Alignment.TRAILING)
                						.addComponent(textForExtra2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                						.addGroup(gl_panel_Settings.createSequentialGroup()
                							.addComponent(lblLocationOfHtml)
                							.addPreferredGap(ComponentPlacement.RELATED)
                							.addComponent(textField_html, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                							.addGap(49)))))
                			.addPreferredGap(ComponentPlacement.RELATED)
                			.addComponent(btnRememberNewSettings)
                			.addGap(15)
                			.addGroup(gl_panel_Settings.createParallelGroup(Alignment.BASELINE)
                				.addComponent(btnManualDbBackup)
                				.addComponent(btnRefreshFileIndexing))
                			.addGap(536))
                );
                panel_Settings.setLayout(gl_panel_Settings);
                mainFrame.setSize(400,400);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("JComboBox Test");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame frame2 = new JFrame("JComboBox Test");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setLayout(groupLayout);
        System.gc();
    }





    public static DefaultTableModel buildTableModel(ResultSet rs1)
            throws SQLException, ParseException {
        ResultSetMetaData metaData = rs1.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));

        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        while (rs1.next()) {
            Vector<Object> vector = new Vector<Object>();
            try {
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					//System.out.println("metaData.getColumnName(columnIndex)"+metaData.getColumnName(columnIndex));
				    //If the column is called VisitDate then format the data, which is rs1.getObject(columnIndex), as a date from a string from the SimpleDateFormatter
				    if(!metaData.getColumnName(columnIndex).toString().trim().equals("VisitDate")){
				    	vector.add(rs1.getObject(columnIndex));
				    }
				    else if(metaData.getColumnName(columnIndex).toString().trim().equals("VisitDate")){
				    	//int colNo = 0;
				    	if(rs1.getObject(columnIndex).toString().trim()!=null&rs1.getObject(columnIndex).toString().trim().contains("_")){

				    	try {
				    		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
				    		Date date = formatter.parse(rs1.getObject(columnIndex).toString());
								vector.add(date);

						} catch (Exception e) {
							e.printStackTrace();
						}
				    	}
				    	else{
				    		try {
								SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
								Date date = formatter.parse(rs1.getObject(columnIndex).toString());
								vector.add(date);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
				    					    }
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

    public void toExcel(JTable table){
        try{
        	Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
            TableModel model = table.getModel();
            File fileTo = new File(userPrefs.get("PathForMyCSV", null));

            JFrame parentFrame = new JFrame();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(fileTo);
            fileChooser.setDialogTitle("Save As: ");

            int userSelection = fileChooser.showSaveDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                System.out.println("Save as file: " + file.getAbsolutePath());


            FileWriter excel = new FileWriter(file);

            for(int i = 0; i < model.getColumnCount(); i++){
                excel.write(model.getColumnName(i) + "\t");
            }

            excel.write("\n");

            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i,j)+"\t");
                }
                excel.write("\n");
            }

            excel.close();
            }

        }
        catch(IOException e)
        {
        	}
        }



    public void writeCSVfile(JTable table) throws IOException, ClassNotFoundException, SQLException{

		Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
		//System.out.println("USERPREFS"+userPrefs.get("PathForMyCSV", null));
        Writer writer = null;
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount();
        int nCol = dtm.getColumnCount();
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userPrefs.get("PathForMyCSV", null)), "utf-8"));

            //write the header information
            StringBuffer bufferHeader = new StringBuffer();
            for (int j = 0; j < nCol; j++) {
                bufferHeader.append(dtm.getColumnName(j));
                if (j!=nCol) bufferHeader.append("	,");
            }
            writer.write(bufferHeader.toString() + "\r\n");

           //write row information
            for (int i = 0 ; i < nRow ; i++){
                 StringBuffer buffer = new StringBuffer();
                for (int j = 0 ; j < nCol ; j++){
                    buffer.append(dtm.getValueAt(i,j));
                    if (j!=nCol) buffer.append("	,");
                }
                writer.write(buffer.toString() + "\r\n");
            }
        } finally {
              writer.close();
        }
    }




    public void HookMeUp(JTable table,String getValue) throws IOException, ClassNotFoundException, SQLException, ParseException{

    	Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
    	String connectDB ="jdbc:ucanaccess://"+userPrefs.get("PathForDB", null);
    Connection conn;
    try {
        conn = DriverManager.getConnection(connectDB);
        Statement st =conn.createStatement();
        String select="Select ";
        ResultSet rsHNum = st.executeQuery(select+getValue);
         table.setModel(buildTableModel(rsHNum));
            ((DefaultTableModel)table.getModel()).fireTableDataChanged(); // show changes
//st.close();
rsHNum.close();
//conn.close();
System.gc();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void HookMeUpExpunger(String table) throws IOException, ClassNotFoundException, SQLException, ParseException{

    	Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
    	String connectDB ="jdbc:ucanaccess://"+userPrefs.get("PathForDB", null);
    Connection conn;
    try {
        conn = DriverManager.getConnection(connectDB);
        Statement st =conn.createStatement();
        String select="Delete FROM "+table;
        st.execute(select);
        System.gc();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }



    public void HookMeUpAudit(JTable table,String getValue,String title,String folder) throws IOException, ClassNotFoundException, SQLException, ParseException{
    	Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
    	String connectDB ="jdbc:ucanaccess://"+userPrefs.get("PathForDB", null);
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectDB);
            Statement st =conn.createStatement();
            ResultSet rsHNum = st.executeQuery(getValue);
             table.setModel(buildTableModel(rsHNum));

             //get the dataset and save it to a new file in a named folder where I collect all the audit table results and which R will use as its Audit source folder
                ((DefaultTableModel)table.getModel()).fireTableDataChanged(); // show changes ---not sure I want this one

                Writer writer = null;
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                int nRow = dtm.getRowCount();
                int nCol = dtm.getColumnCount();
                try {
                	///Users/sebastianzeki/Documents/PhysJava/TBBConsoleExportPhys/mydata.csv
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folder+title), "utf-8"),2048);

                    //write the header information
                    StringBuffer bufferHeader = new StringBuffer();
                    for (int j = 0; j < nCol; j++) {
                        bufferHeader.append(dtm.getColumnName(j));
                        if (j!=nCol) bufferHeader.append(", ");
                    }
                    writer.write(bufferHeader.toString() + "\r\n");

                   //write row information
                    for (int i = 0 ; i < nRow ; i++){
                         StringBuffer buffer = new StringBuffer();
                        for (int j = 0 ; j < nCol ; j++){
                            buffer.append(dtm.getValueAt(i,j));
                            if (j!=nCol) buffer.append(", ");
                        }
                        writer.write(buffer.toString() + "\r\n");
                    }
                } finally {
                	//conn.close();
                      writer.close();
                      rsHNum.close();
                      //st.close();
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }



    public Object[] columnToArray(JTable table, int columnIndex){
        // get the row count
        int rowCount = table.getModel().getRowCount();
        // declare the array
        Object [] data = new Object[rowCount];
        // fetch the data
        for(int i = 1; i < rowCount; i++){
            data[i] = table.getModel().getValueAt(i, columnIndex);
        }
        return(data);
    }



    public String pbox() throws IOException{
    	JFileChooser fileChooser = new JFileChooser();
    	String HospNumAdder=null;
        // For File
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int rVal = fileChooser.showOpenDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {
				for (String line : Files.readAllLines(Paths.get(fileChooser.getSelectedFile().getPath()))){
					//myPBInput.add(line);
					if(line!=null){
					HospNumAdder=HospNumAdder+"(HospNum_Id= "+"'"+line+"') OR";
					}
				}
				HospNumAdder=HospNumAdder.replaceAll("OR$", "").replace("null", "");
        }
        return HospNumAdder;
    }



    public String[] getColumnNames(JTable table) {
        String[] columnNames = new String[table.getColumnCount()];
        for (int i = 0, columnCount = table.getColumnCount(); i < columnCount; i++) {
            columnNames[i] = table.getColumnName(i);
        }
        return columnNames;
    }

    public void open(File document) throws IOException, ScriptException, InterruptedException {
    	Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
    	//Runtime.getRuntime().exec(userPrefs.get("PathForGRS",null));
    	String pt=document.getAbsolutePath().toString().trim();

    	Process process = new ProcessBuilder(userPrefs.get("PathToR",null),pt).start();

    	///////////////////////////////////////////////////////

    	  // Your script
        String script = "#!/usr/bin/env Rscript\n" +
                "\n" +
                "sayHello <- function() {\n" +
                "    print('hello')\n" +
                "}\n" +
                "\n" +
                "sayHello()\n";

        // create a temp file and write your script to it
        File tempScript = File.createTempFile("test_r_scripts_", "");
        try(OutputStream output = new FileOutputStream(tempScript)) {
            output.write(script.getBytes());
        }

        // build the process object and start it
        List<String> commandList = new ArrayList<>();
        commandList.add(userPrefs.get("PathToR",null));
        commandList.add(pt);
        ProcessBuilder builder = new ProcessBuilder(commandList);
        builder.redirectErrorStream(true);
        Process shell = builder.start();

        // read the output and show it
        StringBuilder totextPane_ROutput=new StringBuilder();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(shell.getInputStream()))) {
            String line;
            while((line = reader.readLine()) != null) {
                //System.out.println(line);
                totextPane_ROutput.append(line+"\n");

            }
        }
        //Iterate through folder and find out.html if present then show it


        // wait for the process to finish
        int exitCode = shell.waitFor();

        // delete your temp file
        tempScript.delete();

        // check the exit code (exit code = 0 usually means "executed ok")
        System.out.println("EXIT CODE: " + exitCode);

        ////////////////////////////////////////////////

        System.out.println("THIS IS RUNpt "+pt+"");
    System.out.println("THIS IS RUN "+userPrefs.get("PathToR", null)+" " +pt+"");
	//return totextPane_ROutput.toString();
	}
  }
