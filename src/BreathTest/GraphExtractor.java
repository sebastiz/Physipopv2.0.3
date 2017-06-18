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

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.tika.Tika;


public class GraphExtractor {




	public static Map<String,String> ExtractValues(String filename) throws IOException, SQLException {
		 Map<String,String> mapAllReport= new LinkedHashMap<String,String>();

		 String fileName = filename;
	     File file = new File(filename);

	     try {
			Tika tika = new Tika();

			 //detecting the file type using detect method
			 String filetype = tika.detect(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println("The file is here"+filename+"filetype");

	/*     if (filetype.contains("pdf")){
	    	  try {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }*/

	  		  InputStream fis = new FileInputStream(filename);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          return mapAllBreathTestGraphs;
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

	              for (int j=0; j<tableHoNo1.getRow(i).numCells(); j++){

	   if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("oint")){

		   //System.out.println("tableHoNo1.getRow("+i+").getCell("+j+").getParagraph(0).text()"+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+"oint");
		   oint.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
		   //mapTable.put(keyStem+"TimePoint"+i, tableHoNo1.getRow(i).getCell(j).getParagraph(0).text());
	   }
	   else if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("Time")&&!tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("oint")){
		  //System.out.println("tableHoNo1.getRow("+i+").getCell("+j+").getParagraph(0).text()"+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+"Time");
		   Time.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
		   //mapTable.put(keyStem+"Time"+i, tableHoNo1.getRow(i).getCell(j).getParagraph(0).text());
	   }
	   else if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("CH")){
		   //System.out.println("tableHoNo1.getRow("+i+").getCell("+j+").getParagraph(0).text()"+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+"CH4");
		   CH.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
		   //mapTable.put(keyStem+"CH4"+i, tableHoNo1.getRow(i).getCell(j).getParagraph(0).text());
	   }
	   else  if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("H")&!tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("CH")){
		   //System.out.println("tableHoNo1.getRow("+i+").getCell("+j+").getParagraph(0).text()"+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+"H PPM");

		   System.out.println("THIS IS CH  "+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
		   System.out.println("THIS IS H2  "+H2);

		   H2.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
		   //mapTable.put(keyStem+"H PPM"+i, tableHoNo1.getRow(i).getCell(j).getParagraph(0).text());
	   }
	        	  					}
                       }



              }
     	mapTable.put(keyStem+"TimePoint",oint.toString().trim());
       	mapTable.put(keyStem+"Time",Time.toString().trim());
       	mapTable.put(keyStem+"H2",H2.toString().trim());
       	mapTable.put(keyStem+"CH4",CH.toString().trim());
		System.out.println("THIS IS MAPTABLE"+mapTable);
		return mapTable;


	}
      }


