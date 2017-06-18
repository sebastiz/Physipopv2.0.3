package Surgery;

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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.pmw.tinylog.Logger;

import Overview.Checkers;
import Overview.DBConnectorForAll;

public class Procedures {

	public static void Sugery_mane(String filepath) throws IOException, SQLException {

		String first="";
		String second="";
		Statement st;
		String tab="";
		String SName=null;
		String FName=null;
		String HospNum=null;
		String DOB = null;
		String VisitDate=null;
		String Consultant=null;

		  Map<String,String> mapSurgBarr= new LinkedHashMap<String,String>();
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
					  filteredRows.add(row);
					  break;

			 }
			}


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
		     //System.out.println("OUTSIZE"+out.size());
			int adder=0;
			for (List<String> n:out){
				adder++;
				if(adder % 5 == 0){
					System.out.println("Divisible by 2");
					try {
						System.gc();
					Thread.sleep(500);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}







				mapSurgBarr.clear();
					try {
						 HospNum=n.get(1).trim();
						 mapSurgBarr.put("HospNum_Id", n.get(1).trim());

					} catch (Exception e10) {

						e10.printStackTrace();
					}

					try {
						SName=n.get(2).trim();
						mapSurgBarr.put("Surname", n.get(2).trim().replaceAll("'", ""));
					} catch (Exception e8) {

						e8.printStackTrace();
					}

				    try {
					mapSurgBarr.put("VisitDate", n.get(0).trim());
					VisitDate=n.get(0).trim();
					} catch (Exception e4) {

						e4.printStackTrace();
					}

				    try {
				    	DOB=n.get(3).trim();
						mapSurgBarr.put("DOB", n.get(3).trim());

						} catch (Exception e4) {

							e4.printStackTrace();
						}
				    try {
				    	Consultant=n.get(4).trim();
						mapSurgBarr.put("Consultant", n.get(4).trim());

						} catch (Exception e4) {

							e4.printStackTrace();
						}
				    try {

						mapSurgBarr.put("Procedure", n.get(7).trim());

						} catch (Exception e4) {

							e4.printStackTrace();
						}







			System.out.println("mapSurgBarr:     "+mapSurgBarr);
			try {
				DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
				 tab="Surgery";
				 FName="";
				 st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);
				 first=ConnectMeUp.StringInsertKeyPreparer(st,mapSurgBarr,tab);
				 second=ConnectMeUp.StringInsertValuePreparer(st,mapSurgBarr,tab);
				 if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate)){
					ConnectMeUp.Inserter(st,HospNum,first,second,tab,filepath);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Logger.error(e+HospNum+"->From EndoscopyOld"+filepath);
			}
			}

	}

}
