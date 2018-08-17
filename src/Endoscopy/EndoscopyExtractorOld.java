
package Endoscopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import Overview.Checkers;
import Overview.DBConnectorForAll;
import org.pmw.tinylog.Logger;

public class EndoscopyExtractorOld {

	public EndoscopyExtractorOld() {
	}

	public static void Endoscopy_mane(String filepath) throws IOException, SQLException {
		String first="";
		String second="";
		Statement st;
		String tab="";
		String FName=null;
		String SName=null;
		String HospNum=null;
		String DOB=null;
		String VisitDate=null;
		String ResultPerformed=null;
		    //Take the spreadsheet and look at the visitDate
			//Then take the pathology spreadsheet and look at the sample taken date
			//Then add the array togather for the ones where the Hospital NUmber and VisitDate match
			//Then you need to add the pathology reports together and the endoscopies together so that the next iteration of endoscopies have no loose ends of pathology

		    ////System.out.println("Yes I am over here in the Endoscopy Section");
		    Map<String,String> mapEndoscBarr= new LinkedHashMap<String,String>();
			FileInputStream fis = new FileInputStream(new File(filepath));
			HSSFWorkbook      workBook = new HSSFWorkbook (fis);
			HSSFSheet         sheet    = workBook.getSheetAt (0);

			List<HSSFRow> filteredRows = new ArrayList<HSSFRow>();

			//Filter by pathology from what I am given
			Iterator<Row> rows= sheet.rowIterator();
			while (rows.hasNext ()){
			HSSFRow row = (HSSFRow) rows.next ();
			Iterator<Cell> cells = row.cellIterator ();
			 while (cells.hasNext ()){
				 HSSFCell cell = (HSSFCell) cells.next ();
				  if (cell.toString().contains("Barrett")) {
					  filteredRows.add(row);
					  break;
			        }
			 }
			}

			////System.out.println("filteredRowsSIZE"+filteredRows.size());
			ArrayList<List<String>> out = new ArrayList<List<String>>();
			for (HSSFRow n:filteredRows){

				Iterator<Cell> cells = n.cellIterator ();
				 ArrayList<String> in =new ArrayList<String>();
				 while (cells.hasNext ()){

					 HSSFCell cell = (HSSFCell) cells.next ();
					 in.add(cell.toString());

				 }

				 out.add(in);
			}

			workBook.close();

			//Add each array to a HashMap
		     ////System.out.println("OUTSIZE"+out.size());
			int adder=0;
			for (List<String> n:out){
				adder++;
				if(adder % 5 == 0){
					//System.out.println("Divisible by 2");
					try {
						//System.gc();
					Thread.sleep(500);

					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
				}
				mapEndoscBarr.clear();
				////System.out.println("NNNNNNNNNNN"+n);
					try {
						 HospNum=n.get(0).trim();
						 mapEndoscBarr.put("HospNum_Id", n.get(1).trim());

					} catch (Exception e10) {

						e10.printStackTrace();
					}
					try {
						FName=n.get(2).trim();
						mapEndoscBarr.put("FIRSTNAME", n.get(2).trim().replaceAll("'", ""));
					} catch (Exception e9) {

						e9.printStackTrace();
					}
					try {
						SName=n.get(3).trim();
						mapEndoscBarr.put("LASTNAME", n.get(3).trim().replaceAll("'", ""));
					} catch (Exception e8) {

						e8.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ENDOSCOPIST", n.get(4).trim().replaceAll("'", ""));
					} catch (Exception e7) {

						e7.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ENDOTWO", n.get(5).trim());
					} catch (Exception e6) {

						e6.printStackTrace();
					}
					try {
						mapEndoscBarr.put("TRAINEE", n.get(6).trim());
					} catch (Exception e5) {

						e5.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ER_PROCEDUREPERFORMED", n.get(7).trim());
					} catch (Exception e) {
						e.printStackTrace();
					}
				    try {
					mapEndoscBarr.put("VisitDate", n.get(8).trim());
					VisitDate=n.get(8).trim();
					} catch (Exception e4) {

						e4.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ROOM", n.get(9).trim().replaceAll("'", ""));
					} catch (Exception e3) {

						e3.printStackTrace();
					}
					try {
						mapEndoscBarr.put("INDICATIONS", n.get(10).trim().replaceAll("'", ""));
					} catch (Exception e2) {

						e2.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ER_EXTENTOFEXAM", n.get(11).trim().replaceAll("'", ""));
					} catch (Exception e1) {

						e1.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ER_FINDINGS_STR", n.get(12).trim().replaceAll("'", ""));
					} catch (Exception e) {

						e.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ER_DIAGNOSIS_STR", n.get(13).trim().replaceAll("'", ""));
					} catch (Exception e) {

						e.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ER_RECOMMENDATIONS", n.get(14).trim().replaceAll("'", ""));
					} catch (Exception e) {

						e.printStackTrace();
					}
					try {
						mapEndoscBarr.put("ER_RECALLREASON1", n.get(15).trim().replaceAll("'", ""));
					} catch (Exception e) {

						e.printStackTrace();
					}
					try {
						Pattern CStage_pattern = Pattern.compile("(C(\\s|=)*\\d+)",Pattern.DOTALL);
						Matcher matcherC12Stage_pattern = CStage_pattern.matcher(n.get(12).toString().trim());
						Matcher matcherC13Stage_pattern = CStage_pattern.matcher(n.get(13).toString().trim());
						if (matcherC12Stage_pattern.find()) {
							mapEndoscBarr.put("C_Stage",matcherC12Stage_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replaceAll("=", "").replaceAll("C", ""));
							//System.out.println("GOT THE C 12");
						}
						else if (matcherC13Stage_pattern.find()) {
							mapEndoscBarr.put("C_Stage",matcherC13Stage_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replaceAll("=", "").replaceAll("M", ""));
							//System.out.println("GOT THE C 13");
						}
					} catch (Exception e) {
						
						e.printStackTrace();
					}


					try {
						Pattern MStage_pattern = Pattern.compile("(M(\\s|=)*\\d+)",Pattern.DOTALL);
						Matcher matcherM12Stage_pattern = MStage_pattern.matcher(n.get(12).toString().trim());
						Matcher matcherM13Stage_pattern = MStage_pattern.matcher(n.get(13).toString().trim());
						if (matcherM12Stage_pattern.find()) {
							mapEndoscBarr.put("M_Stage",matcherM12Stage_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replaceAll("C", ""));
							//System.out.println("GOT THE M 12");
						}
						else if (matcherM13Stage_pattern.find()) {
							//System.out.println("GOT THE M 13");
							mapEndoscBarr.put("M_Stage",matcherM13Stage_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replaceAll("C", ""));
				        }
					} catch (Exception e) {
						
						e.printStackTrace();
					}


					try {
						DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
						 tab="Endoscopy";
						 st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);
						 first=ConnectMeUp.StringInsertKeyPreparer(st,mapEndoscBarr,tab);
						 second=ConnectMeUp.StringInsertValuePreparer(st,mapEndoscBarr,tab);
						 VisitDate=ResultPerformed;
						 if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate)){
							ConnectMeUp.Inserter(st,HospNum,first,second,tab,filepath);
						 }
						 System.gc();
					} catch (Exception e) {
						
						Logger.error(e+HospNum+"->From EndoscopyOld"+filepath);
					}

			            }
	}
}
