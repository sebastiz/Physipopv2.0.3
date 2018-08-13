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

import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hssf.extractor.ExcelExtractor;
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
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.Tika;
import org.apache.tika.detect.XmlRootExtractor;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println("The file is here in BT"+filename+"filetype");
	     System.out.println("And goes postInputStream");


	     //TODO: Add an either if here so that if the document has an embedded OLE2 object which is excel then it can be extracted otherwise treat as a word document
         //Get the embedded extractor code from here:https://stackoverflow.com/questions/21419783/extract-embedded-files-from-doc-using-java
	  		  InputStream fis = new FileInputStream(filename);
	  		  System.out.println("And goes postInputStream"+filename);


	  		  if(filename.contains("docx")){
	  			Map<String,String> mapAllBreathTestGraphs= new LinkedHashMap<String,String>();
	  			 System.out.println("And goes postInputStream"+filename);
		           mapAllBreathTestGraphs.clear();
	  			XWPFDocument document = new XWPFDocument(fis);
	  		  List<PackagePart> embeddedDocs = document.getAllEmbedds();
	  		 if (embeddedDocs != null && !embeddedDocs.isEmpty()) {
	  		        Iterator<PackagePart> pIter = embeddedDocs.iterator();
	  		        while (pIter.hasNext()) {
	  		            PackagePart pPart = pIter.next();
	  		            System.out.print(pPart.getPartName()+", ");
	  		            System.out.print("Hi pprt"+pPart.getContentType()+", ");
	  		            pPart.

	  		        }
	  		    }
	  		 return mapAllBreathTestGraphs;

	  		}





	  		  else{
	  		  POIFSFileSystem fs = new POIFSFileSystem(fis);
	  		  POIOLE2TextExtractor oleTextExtractor =
	        		   ExtractorFactory.createExtractor(fs);
	          POITextExtractor[] embeddedExtractors =
	        		   ExtractorFactory.getEmbededDocsTextExtractors(oleTextExtractor);
	        		for (POITextExtractor textExtractor : embeddedExtractors) {
	        		   // If the embedded object was an Excel spreadsheet.
	        		   if (textExtractor instanceof ExcelExtractor) {
	        		      ExcelExtractor excelExtractor = (ExcelExtractor) textExtractor;
	        		      System.out.println("excelExtractor"+excelExtractor.getText());
	        		   }
	        		}


	           System.out.println("And goes postPOIFStream");
	           HWPFDocument doc = new HWPFDocument(fs);
			   System.out.println("doc.getObjectsPool()"+doc.getObjectsPool().toString());



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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   else if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("Time")&&!tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("oint")){

		   System.out.println("Time:::"+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");

		   Time.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
	   }
	   else if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("CH")){
		   System.out.println("CH:::"+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
		   CH.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
	   }
	   else if(tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("H")&!tableHoNo1.getRow(0).getCell(j).getParagraph(0).text().contains("CH")){

		   System.out.println("H:::"+tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
		   H2.append(tableHoNo1.getRow(i).getCell(j).getParagraph(0).text()+":");
	   }
	        	  					}
                       }
              }
     	mapTable.put(keyStem+"TimePoint",oint.toString().replaceAll("\\P{Print}", "").trim());
       	mapTable.put(keyStem+"Time",Time.toString().replaceAll("\\P{Print}", "").trim());
       	mapTable.put(keyStem+"H2",H2.toString().replaceAll("\\P{Print}", "").trim());
       	mapTable.put(keyStem+"CH4",CH.toString().replaceAll("\\P{Print}", "").trim());
		System.out.println("THIS IS MAPTABLE"+mapTable);
		return mapTable;


	}
      }


