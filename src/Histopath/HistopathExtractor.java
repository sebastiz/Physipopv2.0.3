package Histopath;

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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Overview.Checkers;
import Overview.DBConnectorForAll;
import Overview.Searcher;
import Overview.VisitDateFormatter;
import org.pmw.tinylog.Logger;

public class HistopathExtractor {

	public HistopathExtractor() {
	}
	@SuppressWarnings("resource")
	public static void Histology_mane(String filepath) throws IOException, SQLException {
		String first="";
		String second="";
		Statement st;
		String tab="";
		String FName=null;
		String SName=null;
		String HospNum=null;
		String DOB=null;
		String VisitDate=null;
		String ResultEntered=null;
		//Add each array to a HashMap
	       Map<String,String> mapPathBarr= new LinkedHashMap<String,String>();
		//Take the spreadsheet and look at the visitDate
			//Then take the pathology spreadsheet and look at the sample taken date
			//Then add the array together for the ones where the Hospital NUmber and VisitDate match
	       System.out.println("Im in the Histology section prefis");
			//Ten you need to add the pathology reports together and the endoscopies together so that the next iteration of endoscopies have no loose ends of pathology
			////System.out.println("You're in the first class lounge of the Histopath section");
			FileInputStream fis = new FileInputStream(new File(filepath));
			XSSFWorkbook      workBook = new XSSFWorkbook (fis);
			XSSFSheet         sheet    = workBook.getSheetAt (0);
			List<XSSFRow> filteredRows = new ArrayList<XSSFRow>();
			Iterator<Row> rows= sheet.rowIterator();


			/////Adds the relevant rows to an array to be searched
			while (rows.hasNext ()){
				 //System.out.println("Im in the Histology section preWhile");
			XSSFRow row = (XSSFRow) rows.next ();
			Iterator<Cell> cells = row.cellIterator ();



            Pattern Histol_pattern = Pattern.compile("\\b(Diagnosis|DIAGNOSIS)\\b.*?(osinoph|Barrett.*)",Pattern.DOTALL);
			 while (cells.hasNext ()){
				 XSSFCell cell = (XSSFCell) cells.next ();
				// System.out.println("Im in the Histology section postWhile");
				 Matcher matcherHistol_pattern = Histol_pattern.matcher(cell.toString());

				 //Change this so its just adding the ones that have pathology////I think contains doesn't take regexes??
				  if (matcherHistol_pattern.find()) {
					 //System.out.println("MMMMMAAAAAAATTTCHHHHHH Histol");

					  filteredRows.add(row);
					  break;
				 }
			  }
			 }


			for (XSSFRow n:filteredRows){
				Iterator<Cell> cells = n.cellIterator ();
				 ArrayList<String> in =new ArrayList<String>();
				 while (cells.hasNext ()){
					 XSSFCell cell = (XSSFCell) cells.next ();
					//in.add(cell.toString());

					 //This comes from another cell so we will see if it can be maintained for the row . Perhaps clear map at the end of the row iteration?
					 Pattern HospitalNumpattern = Pattern.compile("Hospital Number(.*)");
					    Matcher matcherHospitalNumpattern = HospitalNumpattern.matcher(cell.toString());

				    	if (matcherHospitalNumpattern.find()) {
						    System.out.println("THE HospNum"+matcherHospitalNumpattern.group(1));
				    		mapPathBarr.put("HospNum_Id",matcherHospitalNumpattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replaceAll(":", ""));
				    	}

					    Pattern VisitDate_pattern = Pattern.compile("Date of Procedure:(.*)");
						Matcher matcherVisitDate_pattern = VisitDate_pattern.matcher(cell.toString());

						if (matcherVisitDate_pattern.find()) {
							//System.out.println("THE VisitDate"+matcherVisitDate_pattern.group(1));
							mapPathBarr.put("VisitDate",matcherVisitDate_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));

						}


					            Pattern BarrClinDetPath_pattern = Pattern.compile("(Clinical Information.*?)Macroscopic Description",Pattern.DOTALL);
				                Matcher matcherBarrClinDetPath_pattern = BarrClinDetPath_pattern.matcher(cell.toString());

						        if (matcherBarrClinDetPath_pattern.find()) {
						    	    //in.add(matcherBarrClinDetPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
						    	   mapPathBarr.put("ClinDetails", matcherBarrClinDetPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
						    	    ////System.out.println("THE ClinDetails"+matcherBarrClinDetPath_pattern.group(1));
						        }
						        Pattern BarrMacroDescPath_pattern = Pattern.compile("(Macroscopic Description.*?)Microscopic Description",Pattern.DOTALL);
							    Matcher matcherBarrMacroDescPath_pattern = BarrMacroDescPath_pattern.matcher(cell.toString());

						    	if (matcherBarrMacroDescPath_pattern.find()) {
								    //in.add(matcherBarrMacroDescPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
								    mapPathBarr.put("NatureOfSpec",matcherBarrMacroDescPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
								    ////System.out.println("THE NatureOfSpec"+matcherBarrMacroDescPath_pattern.group(1));
						    	}

							    Pattern BarrHistolPath_pattern = Pattern.compile("(Microscopic Description.*?)Diagnosis",Pattern.DOTALL);
								Matcher matcherBarrHistolPath_pattern = BarrHistolPath_pattern.matcher(cell.toString());

								if (matcherBarrHistolPath_pattern.find()) {
									//in.add(matcherBarrHistolPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
									mapPathBarr.put("Histology",matcherBarrHistolPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
									////System.out.println("THE Histology"+matcherBarrHistolPath_pattern.group(1));
								}
							    Pattern BarrDx_pattern = Pattern.compile("(Diagnosis.*Electronically)",Pattern.DOTALL);
							    Matcher matcherBarrDx_pattern = BarrDx_pattern.matcher(cell.toString());

								if (matcherBarrDx_pattern.find()) {
									//in.add(matcherBarrDx_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
									mapPathBarr.put("Diagnosis", matcherBarrDx_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
									////System.out.println("THE Diagnosis"+matcherBarrDx_pattern.group(1));
								}
								try {
									FName=Searcher.FName_searcher(cell.toString());
									if (FName!=null){
										mapPathBarr.put("FName",FName);
									}


								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									SName=Searcher.SName_searcher(cell.toString());
									if (SName!=null){
										mapPathBarr.put("SName",SName);
									}


								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

				 }

					 try {
							DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
							try {
								FName=mapPathBarr.get("FName").toString().trim();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("mapPathBarr"+mapPathBarr);
							 try {
								SName=mapPathBarr.get("SName").toString().trim();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}


							 try {
								 HospNum=mapPathBarr.get("HospNum_Id").toString().trim();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							 try {
								 VisitDate=mapPathBarr.get("VisitDate").toString().trim();
								 VisitDate=VisitDate.replace("\\", "_").replace("/","_");
								 VisitDate=VisitDateFormatter.VDFormat(VisitDate);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							 tab="Histology";


							 st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);
							 //Going to have to extract into a new map here from mapPathBarr to get the HistolDet table I think
							 //Extract the NatureOfSpec and then do a find on that






							 first=ConnectMeUp.StringInsertKeyPreparer(st,mapPathBarr,tab);
							 second=ConnectMeUp.StringInsertValuePreparer(st,mapPathBarr,tab);
							 //VisitDate=ResultPerformed;
							 ////System.out.println("Checkers.VisitDateChecker "+Checkers.VisitDateChecker(st,tab,HospNum)+" VS VisitDate "+VisitDate);
							 if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate)){
								 int sharedkey=ConnectMeUp.Inserter(st,HospNum,first,second,tab,filepath);


//Now have to iterate through the matches here to add to the one to many table for HistolDet
							 String h=null;
							 h=mapPathBarr.get("NatureOfSpec");

								 Pattern HistolDetailsPath_pattern = Pattern.compile("([A-Za-z]*|[0-9]) piece.*?(([0-9]).*?x.*?([0-9]).*?x.*?([0-9])).*?([a-z]\\.)",Pattern.DOTALL);
								    Matcher matcherHistolDetailsPath_pattern = HistolDetailsPath_pattern.matcher(h);
								    ArrayList<List<String>> HistolDetOut=new  ArrayList<List<String>>();
								    while (matcherHistolDetailsPath_pattern.find()) {
								    	Map<String,String> mapHistolDet = new LinkedHashMap<String,String>();
								    	List<String> HistolDetIn=new  ArrayList<String>();
								    	if(matcherHistolDetailsPath_pattern.group(0)!=null){
								    	HistolDetIn.add(matcherHistolDetailsPath_pattern.group(0).replaceAll("\\n", ""));
								    	  }
								    	if(matcherHistolDetailsPath_pattern.group(1)!=null){
									    HistolDetIn.add(matcherHistolDetailsPath_pattern.group(1).replaceAll("[Ss]ingle", "1").replaceAll("[Oo]ne", "1").replaceAll("[Tt]wo", "2").replaceAll("[Tt]hree", "3").replaceAll("[Ff]our", "4").replaceAll("[Ff]ive", "5").replaceAll("[Ss]ix", "6").replaceAll("[Ss]even", "7").replaceAll("[Ee]ight", "8").replaceAll("[Nn]ine", "9").replaceAll("[Tt]en", "10").replaceAll("[Ee]leven", "11").replaceAll("[Tt]welve", "12"));
									      }
								    	if(matcherHistolDetailsPath_pattern.group(2)!=null){
										int Size= Integer.parseInt(matcherHistolDetailsPath_pattern.group(3))*Integer.parseInt(matcherHistolDetailsPath_pattern.group(4))*Integer.parseInt(matcherHistolDetailsPath_pattern.group(5));
										HistolDetIn.add(String.valueOf(Size));
								    	  }
								    	if(!HistolDetIn.isEmpty()){
								    	HistolDetOut.add(HistolDetIn);
								    	mapHistolDet.put("Description", HistolDetIn.get(0).trim());
								    	mapHistolDet.put("NumberBx", HistolDetIn.get(1).trim());
								    	mapHistolDet.put("MeasurementLargest", HistolDetIn.get(2).trim());
								    	 ////System.out.println("mapHistolDet"+mapHistolDet);
								    	 try {
											    String table="HistolDetatils";
											    String third=ConnectMeUp.StringInsertKeyPreparer(st,mapHistolDet,table);
												String fourth=ConnectMeUp.StringInsertValuePreparer(st,mapHistolDet,table);
												String FKFieldName="Histology_Id";
												ConnectMeUp.InserterMany2Many(st,third,fourth,table,sharedkey,FKFieldName);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											Logger.error(e+HospNum+"->From HistopathNew"+filepath);
										}
								    	 //st.close();
								    	  }
									    }
								}
							 System.gc();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 //To make sure Hospital NUmber doesn't get misallocated to the next rows results
					 mapPathBarr.clear();
}
			workBook.close();
	}
					 }


