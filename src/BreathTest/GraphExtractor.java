package BreathTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFChart;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.Tika;
import org.apache.tika.detect.XmlRootExtractor;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.xmlbeans.XmlException;


public class GraphExtractor {




	public static Map<String,String> ExtractValues(String filename) throws IOException, SQLException, InvalidFormatException, OpenXML4JException, XmlException {
		 Map<String,String> mapAllReport= new LinkedHashMap<String,String>();

		 String fileName = filename;
	     File file = new File(filename);

	     try {
			Tika tika = new Tika();

			 //detecting the file type using detect method
			 String filetype = tika.detect(file);
		} catch (Exception e) {

			e.printStackTrace();
		}
	     System.out.println("The file is here in BT"+filename+"filetype");
	     System.out.println("And goes postInputStream");


	     //Add an either if here so that if the document has an embedded OLE2 object which is excel then it can be extracted otherwise treat as a word document
         //TODO: Make sure that the notes, symptoms and interpreter work
	     //Get the embedded extractor code from here:https://stackoverflow.com/questions/21419783/extract-embedded-files-from-doc-using-java
	  		  InputStream fis = new FileInputStream(filename);
	  		  System.out.println("And goes postInputStream"+filename);


	  		  if(filename.contains("docx")){
	  			Map<String,String> mapAllBreathTestGraphs= new LinkedHashMap<String,String>();
	  			 System.out.println("And goes postInputStream"+filename);
		           mapAllBreathTestGraphs.clear();

		           //For working with docx files use XWPFDocument

	  		  XWPFDocument document = new XWPFDocument(fis);

	  		  //Here you get all the embedded documents (ie all the excel spreadsheets).
	  		  List<PackagePart> embeddedDocs = document.getAllEmbedds();

	  		 if (embeddedDocs != null && !embeddedDocs.isEmpty()) {
	  			System.out.print("pPart.getPartName().toString()");
	  		    Iterator<PackagePart> pIter = embeddedDocs.iterator();
	  		        while (pIter.hasNext()) {

	  		            PackagePart pPart = pIter.next();
	  		            ArrayList<String> Arr_RflxAnalysis_table2d = new ArrayList<String>();

					try {
						//Create the ability to parse the embedded excel spreadsheet
						AutoDetectParser parser = new AutoDetectParser();
					    BodyContentHandler handler = new BodyContentHandler();
					    Metadata metadata = new Metadata();
					    System.out.print("pPart.getPartName().toString()"+pPart.toString());

						XSSFWorkbook workBook2 = new XSSFWorkbook (pPart.getInputStream());
						XSSFSheet    sheet    = workBook2.getSheetAt (0);
						XSSFDrawing draw= sheet.getDrawingPatriarch();
						List<XSSFChart> chrt2=draw.getCharts();


						//Get the chart title out so you know what type of chart it is.
						for (XSSFChart chart: chrt2){
							System.out.println("CHAAAAAARRRRRTTTTT"+chart.getTitle().toString());

							String BT_Type=chart.getTitle().toString();

							if(BT_Type.contains("Lactose")){
								BT_Type="lact";
							}
							else if(BT_Type.contains("Lactulose")){
								BT_Type="lactul";
							}
							else if(BT_Type.contains("Sucrose")){
								BT_Type="sucr";
							}
							else if(BT_Type.contains("Glucose")){
								BT_Type="gluc";
							}
							else if(BT_Type.contains("Fructose")){
								BT_Type="fruc";
							}


							Arr_RflxAnalysis_table2d.add(BT_Type);
						}

						Iterator<Row> rows= sheet.rowIterator();
						while (rows.hasNext ()){
						XSSFRow row = (XSSFRow) rows.next ();
						Iterator<Cell> cells = row.cellIterator ();
                        //Add all the cells to an array. There are always three columns always labelled the same way Time Point, Ch4 and H2 so...
						 while (cells.hasNext ()){
							 XSSFCell cell = (XSSFCell) cells.next();
							 if(cell!=null|| cell.getCellType() == Cell.CELL_TYPE_BLANK) {

								 //Add to array
                                 String t = cell.toString();
         						//Get rid of the headers  Time Point , CH4 and H2
                                 t=t.replaceAll("\\(.*?\\)","").replaceAll("Time Point\\s+\\n","").replaceAll("CH4\\s+\\n", "").replaceAll("H2\\s+\\n", "");

                                 if(!StringUtils.isEmpty(t)){
									Arr_RflxAnalysis_table2d.add(t);
                                 }

							 }
						 }

						}


						 System.out.print("embedxls..........."+Arr_RflxAnalysis_table2d);

						 //Now split up the array table into three
						 //Time Point CH4 H2
						 ArrayList<String> Arr_TimePoint = new ArrayList<String>();
						 Arr_TimePoint.add(Arr_RflxAnalysis_table2d.get(0).toString()+"TimePoint");
						 //Every third element from element 1 onwards to get TimePoint measurements
						 StringBuilder StrTimePoint=new StringBuilder();
						 StrTimePoint.append(Arr_RflxAnalysis_table2d.get(0).toString()+"TimePoint");
						 for (int i = 1; i < Arr_RflxAnalysis_table2d.size(); i=i+3) {
							 //Arr_TimePoint.add(Arr_RflxAnalysis_table2d.get(i));
							  StrTimePoint.append("_"+Arr_RflxAnalysis_table2d.get(i));
						 }
						 mapAllBreathTestGraphs.put(Arr_RflxAnalysis_table2d.get(0).toString()+"TimePoint",StrTimePoint.toString());



						 ArrayList<String> Arr_CH4 = new ArrayList<String>();
						 Arr_CH4.add(Arr_RflxAnalysis_table2d.get(0).toString()+"CH4");
						 //Every third element from element 2 onwards to get CH4 measurements
						 StringBuilder StrCH4=new StringBuilder();
						 StrCH4.append(Arr_RflxAnalysis_table2d.get(0).toString()+"CH4");
						 for (int i = 2; i < Arr_RflxAnalysis_table2d.size(); i=i+3) {
							 //Arr_CH4.add(Arr_RflxAnalysis_table2d.get(i));
							 StrCH4.append("_"+Arr_RflxAnalysis_table2d.get(i));
						 }
						 mapAllBreathTestGraphs.put(Arr_RflxAnalysis_table2d.get(0).toString()+"CH4",StrCH4.toString());


						 ArrayList<String> Arr_H2 = new ArrayList<String>();
						 Arr_H2.add(Arr_RflxAnalysis_table2d.get(0).toString()+"H2");
						 //Every third element from element 3 onwards to get H2 measurements
						 //Or add as a Stringbuilder element˙
						 StringBuilder StrH2=new StringBuilder();
						 StrH2.append(Arr_RflxAnalysis_table2d.get(0).toString()+"H2");
						 for (int i = 3; i < Arr_RflxAnalysis_table2d.size(); i=i+3) {
							 //Arr_H2.add(Arr_RflxAnalysis_table2d.get(i));
							 StrH2.append("_"+Arr_RflxAnalysis_table2d.get(i));
						 }
						 mapAllBreathTestGraphs.put(Arr_RflxAnalysis_table2d.get(0).toString()+"H2",StrH2.toString());





					} catch (Exception e) {

						e.printStackTrace();
					}


	  		        }
	  		    }
	  		 return mapAllBreathTestGraphs;

	  		}

	  		  else{
	  		   POIFSFileSystem fs = new POIFSFileSystem(fis);
	           HWPFDocument doc = new HWPFDocument(fs);
	           Map<String,String> mapAllBreathTestGraphs= new LinkedHashMap<String,String>();
	           mapAllBreathTestGraphs.clear();
	           System.out.println("WERE IN THE EXTRACTVALUES");
	          try {
	        	  for (int i=0; i<doc.getRange().numSections(); i++){
	        	  if(doc.getRange().getSection(i).text().toString().contains("Lactulose Hydrogen Breath Test")){
	        		  String lactul="lactul";
	        		  mapAllBreathTestGraphs.putAll(ValueMapper(doc,i,lactul));
	        	  }
	        	   if(doc.getRange().getSection(i).text().toString().contains("Lactose Hydrogen Breath Test")){
	        		  String lact="lact";
	        		  mapAllBreathTestGraphs.putAll(ValueMapper(doc,i,lact));
	        	  }
	        	   if(doc.getRange().getSection(i).text().toString().contains("Sucrose Hydrogen Breath Test")){
	        		  String sucr="sucr";
	        		  mapAllBreathTestGraphs.putAll(ValueMapper(doc,i,sucr));
	        	  }
	        	   if(doc.getRange().getSection(i).text().toString().contains("Glucose Hydrogen Breath Test")){
	        		  String gluc="gluc";
	        		  mapAllBreathTestGraphs.putAll(ValueMapper(doc,i,gluc));
	        	  }
	        	   if(doc.getRange().getSection(i).text().toString().contains("Fructose Hydrogen Breath Test")){
	        		  String fruc="fruc";
	        		  mapAllBreathTestGraphs.putAll(ValueMapper(doc,i,fruc));
	        	  }
	        	  }

	        	  } catch (Exception e) {

				e.printStackTrace();
			}
	          return mapAllBreathTestGraphs;
	  		  }
	      }

	public static  Map<String,String> ValueMapper(HWPFDocument doc,int section,String keyStem) throws IOException, SQLException {

		System.out.println("WERE IN THE VALUEMAPPER"+doc.getRange().numSections());
		 TableIterator itr2 = new TableIterator(doc.getRange().getSection(section));
		 ArrayList<Table> places = new ArrayList<Table>();
         while(itr2.hasNext()){
             Table table = itr2.next();
             places.add(table);
         }



         Table tableHoNo1 =places.get(1);
         Map<String,String> mapTable= new LinkedHashMap<String,String>();
         mapTable.clear();
         StringBuilder oint = new StringBuilder();
    	 StringBuilder Time = new StringBuilder();
    	 StringBuilder CH = new StringBuilder();
    	 StringBuilder H2 = new StringBuilder();

         for (int i=0; i<tableHoNo1.numRows(); i++){
       	  if(!tableHoNo1.getRow(i).getCell(1).getParagraph(0).text().trim().isEmpty()){
       		 //You are going to need to determine the value of the last column with
       		 //text in it and then use that as the maximum that j cycles to which is always 4
	              for (int j=0; j<5; j++){

	            System.out.println("THIS IS row i:  "+i+','+"j:"+j+" value: "+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");

	   if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("oint")){

		   try {
			System.out.println("oint:::"+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
			oint.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
		} catch (Exception e) {

			e.printStackTrace();
		}
	   }
	   else if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("Time")&&!tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("oint")){
		   Time.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
	   }
	   else if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("CH")){
		   CH.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
	   }
	   else if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("H")&!tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("CH")){

		   H2.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
	   }
	        	  					}
                       }
              }
     	mapTable.put(keyStem+"TimePoint",oint.toString().replaceAll("\\P{Print}", "").trim());
       	mapTable.put(keyStem+"Time",Time.toString().replaceAll("\\P{Print}", "").trim());
       	mapTable.put(keyStem+"H2",H2.toString().replaceAll("\\P{Print}", "").trim());
       	mapTable.put(keyStem+"CH4",CH.toString().replaceAll("\\P{Print}", "").trim());
		return mapTable;


	}
      }


