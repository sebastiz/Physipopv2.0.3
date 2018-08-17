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
import Overview.VisitDateFormatter;
import org.pmw.tinylog.Logger;

public class HistopathExtraxtorOld {

	public HistopathExtraxtorOld() {
	}
	public static void Histology_mane(String filepath) throws IOException, SQLException {

		String first="";
		String second="";
		Statement st;
		String tab="";
		String FName=null;
		String SName = null;
		String HospNum=null;
		String DOB=null;
		String VisitDate=null;
		String ResultEntered=null;
		//Add each array to a HashMap
	       Map<String,String> mapPathBarr= new LinkedHashMap<String,String>();
		//Take the spreadsheet and look at the visitDate
			//Then take the pathology spreadsheet and look at the sample taken date
			//Then add the array together for the ones where the Hospital NUmber and VisitDate match

			//THen you need to add the pathology reports together and the endoscopies together so that the next iteration of endoscopies have no loose ends of pathology
			//System.out.println("You're in the first class lounge of the Histopath section");
			FileInputStream fis = new FileInputStream(new File(filepath));
			XSSFWorkbook      workBook = new XSSFWorkbook (fis);
			XSSFSheet         sheet    = workBook.getSheetAt (0);

			List<XSSFRow> filteredRows = new ArrayList<XSSFRow>();
			int rowadder=0;
			Iterator<Row> rows= sheet.rowIterator();
			while (rows.hasNext ()){
			XSSFRow row = (XSSFRow) rows.next ();
			Iterator<Cell> cells = row.cellIterator ();
			 while (cells.hasNext ()){
				 XSSFCell cell = (XSSFCell) cells.next ();
				  if (cell.toString().contains("Barrett")) {

					  System.out.println("Im in old Histopath");
					  filteredRows.add(row);
			        }
			  }
			 }

			ArrayList<List<String>> out = new ArrayList<List<String>>();
			for (XSSFRow n:filteredRows){
				Iterator<Cell> cells = n.cellIterator ();
				 ArrayList<String> in =new ArrayList<String>();
				 while (cells.hasNext ()){
					 XSSFCell cell = (XSSFCell) cells.next ();
					in.add(cell.toString());

					            Pattern BarrClinDetPath_pattern = Pattern.compile("(CLINICAL DETAILS.*?)MACROSCOPICAL DESCRIPTION",Pattern.DOTALL);
				                Matcher matcherBarrClinDetPath_pattern = BarrClinDetPath_pattern.matcher(cell.toString());

						        if (matcherBarrClinDetPath_pattern.find()) {
						    	    in.add(matcherBarrClinDetPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
						        }
						        Pattern BarrMacroDescPath_pattern = Pattern.compile("(MACROSCOPICAL DESCRIPTION.*?)HISTOLOGY",Pattern.DOTALL);
							    Matcher matcherBarrMacroDescPath_pattern = BarrMacroDescPath_pattern.matcher(cell.toString());

						    	if (matcherBarrMacroDescPath_pattern.find()) {
								    in.add(matcherBarrMacroDescPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
                                }

							    Pattern BarrHistolPath_pattern = Pattern.compile("(HISTOLOGY.*?)DIAGNOSIS",Pattern.DOTALL);
								Matcher matcherBarrHistolPath_pattern = BarrHistolPath_pattern.matcher(cell.toString());

								if (matcherBarrHistolPath_pattern.find()) {
									in.add(matcherBarrHistolPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
								}
							    Pattern BarrDx_pattern = Pattern.compile("(DIAGNOSIS.*)",Pattern.DOTALL);
							    Matcher matcherBarrDx_pattern = BarrDx_pattern.matcher(cell.toString());

								if (matcherBarrDx_pattern.find()) {
									in.add(matcherBarrDx_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
								 }

				 }
				 out.add(in);


			}


			workBook.close();

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

				try {
					mapPathBarr.put("DOB", n.get(3).toString().trim().replace(".0", "")+"/"+n.get(4).toString().trim().replace(".0", "")+"/"+n.get(5).toString().trim().replace(".0", ""));
					DOB=n.get(3).toString().trim().replace(".0", "")+"/"+n.get(4).toString().trim().replace(".0", "")+"/"+n.get(5).toString().trim().replace(".0", "");
				} catch (Exception e10) {
					e10.printStackTrace();
				}try {
					mapPathBarr.put("FName", n.get(2).trim());
					FName=n.get(2).toString().trim();
				} catch (Exception e10) {
					e10.printStackTrace();
				}try {
					mapPathBarr.put("SName", n.get(3).trim());
					SName=n.get(2).toString().trim();
				} catch (Exception e10) {
					e10.printStackTrace();
				}
					try {
						mapPathBarr.put("HospNum_Id", n.get(0).trim());
						HospNum=n.get(0).trim();
					} catch (Exception e10) {
						e10.printStackTrace();
					}
					try {
						mapPathBarr.put("ResultName", n.get(6).trim().replaceAll("'", ""));
					} catch (Exception e9) {

						e9.printStackTrace();
					}



					try {
						VisitDate=n.get(7).trim();
						////System.out.println("THIS IS THE VISITDATE"+n.get(7).trim());
						try {
							VisitDate=VisitDateFormatter.VDFormat(VisitDate);
						} catch (Exception e) {
							
							Logger.error(e+HospNum+"->From HistopathExtractorOld Mane"+filepath);
						}
						////System.out.println("THIS IS THE format VISITDATE"+VisitDate);
						//ResultPerformed is used as the VisitDate here
						mapPathBarr.put("VisitDate", VisitDate.replaceAll("'", ""));
					} catch (Exception e8) {

						e8.printStackTrace();
					}


					try {
						 ResultEntered=n.get(8).trim();
						ResultEntered=VisitDateFormatter.VDFormat(ResultEntered);
						mapPathBarr.put("ResultEntered", ResultEntered.replaceAll("'", ""));
					} catch (Exception e7) {

						e7.printStackTrace();
					}


					try {
					mapPathBarr.put("ClinDetails", n.get(10).trim().replaceAll("'", ""));
			        } catch (Exception e7) {

				    e7.printStackTrace();
			        }

				    try {
			        mapPathBarr.put("NatureOfSpec", n.get(11).trim().replaceAll("'", ""));
	                } catch (Exception e7) {

		             e7.printStackTrace();
	                }

				  try {
	              mapPathBarr.put("Histology", n.get(12).trim().replaceAll("'", ""));
                  } catch (Exception e7) {
	              e7.printStackTrace();
                   }

				  try {
		              mapPathBarr.put("Diagnosis", n.get(13).trim().replaceAll("'", ""));
	                  } catch (Exception e7) {
		              e7.printStackTrace();
	                   }

					 try {
						 ////System.out.println("mapPathBarr"+mapPathBarr);
							DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
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
											
											Logger.error(e+HospNum+"->From HistopathOld"+filepath);
										}
								    	 //st.close();
								    	  }
									    }
								}
							 System.gc();
						} catch (Exception e) {
							
							e.printStackTrace();
						}
}
					 }
}