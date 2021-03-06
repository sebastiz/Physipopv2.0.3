package Histopath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
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
		String tabEoE="";
		String FName=null;
		String SName=null;
		String HospNum=null;
		String DOB=null;
		String VisitDate=null;
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

//pattern for Histol---- this makes sure I'm only getting the histology pattern:

            Pattern Histol_pattern = Pattern.compile("\\b(NATURE|Nature)\\b.*\\b(Diagnosis|DIAGNOSIS)\\b.*?(?:osinoph|Barrett)",Pattern.DOTALL);
			 while (cells.hasNext ()){
				 XSSFCell cell = (XSSFCell) cells.next ();

				 Matcher matcherHistol_pattern = Histol_pattern.matcher(cell.toString());

				 //Change this so its just adding the ones that have pathology////I think contains doesn't take regexes??
				  if (matcherHistol_pattern.find()) {
					  System.out.println("Im in the Histology section postWhile"+cell.toString());
					  filteredRows.add(row);
					  break;
				 }
			  }
			 }

			for (XSSFRow n:filteredRows){
				 System.out.println("THE ROW is here");
				Iterator<Cell> cells = n.cellIterator ();
				 ArrayList<String> in =new ArrayList<String>();
				 while (cells.hasNext ()){

					 XSSFCell cell = (XSSFCell) cells.next ();
					//in.add(cell.toString());

					 //This comes from another cell so we will see if it can be maintained for the row . Perhaps clear map at the end of the row iteration?
					   Pattern HospitalNumpattern = Pattern.compile("Hospital Number(.*)");
					    Matcher matcherHospitalNumpattern = HospitalNumpattern.matcher(cell.toString());
					    Pattern ResultEntered_pattern = Pattern.compile("(?<!Birth:)\\s*((?:\\d{2}/\\d{2}/\\d{4}))");
						Matcher matcherResultEntered_pattern = ResultEntered_pattern.matcher(cell.toString());

					    System.out.println("THE CELL is here"+cell.toString());
				    	if (matcherHospitalNumpattern.find()) {
						    System.out.println("THE HospNum"+matcherHospitalNumpattern.group(1));
				    		try {
								mapPathBarr.put("HospNum_Id",matcherHospitalNumpattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replaceAll(":", ""));
							} catch (Exception e) {
								
								e.printStackTrace();
							}
				    	}

						if (matcherResultEntered_pattern.find()) {
							//System.out.println("THE VisitDate"+matcherVisitDate_pattern.group(1));
							try {
								mapPathBarr.put("ResultEntered",matcherResultEntered_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
								mapPathBarr.put("VisitDate",matcherResultEntered_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));

							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}
					            Pattern BarrClinDetPath_pattern = Pattern.compile("(Clinical Information.*?)Macroscopic Description",Pattern.DOTALL);
				                Matcher matcherBarrClinDetPath_pattern = BarrClinDetPath_pattern.matcher(cell.toString());

				                Pattern BarrClinDetPath_pattern2 = Pattern.compile("(CLINICAL DETAILS.*?)MACROSCOPICAL DESCRIPTION",Pattern.DOTALL);
				                Matcher matcherBarrClinDetPath_pattern2 = BarrClinDetPath_pattern2.matcher(cell.toString());

						        if (matcherBarrClinDetPath_pattern.find()) {
						    	    //in.add(matcherBarrClinDetPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
						    	   try {
									mapPathBarr.put("ClinDetails", matcherBarrClinDetPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
									System.out.println("THE ClinDetails"+matcherBarrClinDetPath_pattern.group(1));
						    	   } catch (Exception e) {
									
									e.printStackTrace();
								}
						    	    ////
						        }
						        else if  (matcherBarrClinDetPath_pattern2.find()) {
							    	   mapPathBarr.put("ClinDetails", matcherBarrClinDetPath_pattern2.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));

						        }
						        Pattern BarrMacroDescPath_pattern = Pattern.compile("(Macroscopic Description.*?)Microscopic Description",Pattern.DOTALL);
							    Matcher matcherBarrMacroDescPath_pattern = BarrMacroDescPath_pattern.matcher(cell.toString());

							    Pattern BarrMacroDescPath_pattern2 = Pattern.compile("(MACROSCOPICAL DESCRIPTION.*?)HISTOLOGY",Pattern.DOTALL);
							    Matcher matcherBarrMacroDescPath_pattern2 = BarrMacroDescPath_pattern2.matcher(cell.toString());

						    	if (matcherBarrMacroDescPath_pattern.find()) {
								    //in.add(matcherBarrMacroDescPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
								    try {
										mapPathBarr.put("NatureOfSpec",matcherBarrMacroDescPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
									} catch (Exception e) {
										
										e.printStackTrace();
									}
								    ////System.out.println("THE NatureOfSpec"+matcherBarrMacroDescPath_pattern.group(1));
						    	}
						    	else if (matcherBarrMacroDescPath_pattern2.find()){
								    mapPathBarr.put("NatureOfSpec",matcherBarrMacroDescPath_pattern2.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));

						    	}

							    Pattern BarrHistolPath_pattern = Pattern.compile("(Microscopic Description.*?)Diagnosis",Pattern.DOTALL);
								Matcher matcherBarrHistolPath_pattern = BarrHistolPath_pattern.matcher(cell.toString());

								Pattern BarrHistolPath_pattern2 = Pattern.compile("(HISTOLOGY.*?)DIAGNOSIS",Pattern.DOTALL);
								Matcher matcherBarrHistolPath_pattern2 = BarrHistolPath_pattern2.matcher(cell.toString());

								if  (matcherBarrHistolPath_pattern.find()) {
									//in.add(matcherBarrHistolPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
									try {
										mapPathBarr.put("Histology",matcherBarrHistolPath_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
									} catch (Exception e) {
										
										e.printStackTrace();
									}
									////System.out.println("THE Histology"+matcherBarrHistolPath_pattern.group(1));
								}
								else if (matcherBarrHistolPath_pattern2.find()){
									mapPathBarr.put("Histology",matcherBarrHistolPath_pattern2.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));

								}
							    Pattern BarrDx_pattern = Pattern.compile("(Diagnosis.*)",Pattern.DOTALL);
							    Matcher matcherBarrDx_pattern = BarrDx_pattern.matcher(cell.toString());

							    Pattern BarrDx_pattern2 = Pattern.compile("(DIAGNOSIS.*)",Pattern.DOTALL);
							    Matcher matcherBarrDx_pattern2 = BarrDx_pattern2.matcher(cell.toString());

								if (matcherBarrDx_pattern.find()) {
									//in.add(matcherBarrDx_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
									try {
										mapPathBarr.put("Diagnosis", matcherBarrDx_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
									} catch (Exception e) {
										
										e.printStackTrace();
									}
									////System.out.println("THE Diagnosis"+matcherBarrDx_pattern.group(1));
								}
								else if (matcherBarrDx_pattern2.find()){
									try {
										mapPathBarr.put("Diagnosis", matcherBarrDx_pattern2.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
									} catch (Exception e) {
										
										e.printStackTrace();
									}

								}
								try {
									FName=Searcher.FName_searcher(cell.toString());
									if (FName!=null){
										mapPathBarr.put("FName",FName);
									}


								} catch (Exception e1) {
									
									e1.printStackTrace();
								}
								try {
									SName=Searcher.SName_searcher(cell.toString());
									if (SName!=null){
										mapPathBarr.put("SName",SName);
									}


								} catch (Exception e1) {
									
									e1.printStackTrace();
								}

				 }

					 try {
							DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
							try {
								FName=mapPathBarr.get("FName").toString().trim();
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							System.out.println("mapPathBarr"+mapPathBarr);
							 try {
								SName=mapPathBarr.get("SName").toString().trim();
							} catch (Exception e) {
								
								e.printStackTrace();
							}


							 try {
								 HospNum=mapPathBarr.get("HospNum_Id").toString().trim();
								} catch (Exception e) {
									
									e.printStackTrace();
								}
							 try {
								 VisitDate=mapPathBarr.get("ResultEntered").toString().trim();
								 VisitDate=VisitDate.replace("\\", "_").replace("/","_");
								 VisitDate=VisitDateFormatter.VDFormat(VisitDate);
								} catch (Exception e) {
									
									e.printStackTrace();
								}
							 tab="Histology";
							 tabEoE="EoEClinDet";


							 st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);
							 //Going to have to extract into a new map here from mapPathBarr to get the HistolDet table I think
							 //Extract the NatureOfSpec and then do a find on that

							 first=ConnectMeUp.StringInsertKeyPreparer(st,mapPathBarr,tab);
							 second=ConnectMeUp.StringInsertValuePreparer(st,mapPathBarr,tab);
							 //VisitDate=ResultPerformed;
							//System.out.println("Checkers.VisitDateChecker "+Checkers.VisitDateChecker(st,tab,HospNum)+" VS VisitDate "+VisitDate);
							 if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate)){
								 int sharedkey=ConnectMeUp.Inserter(st,HospNum,first,second,tab,filepath);

								 //This gets the EoE hospital numbers so they are part of EoEClinDet main table if eosinophilia is present
								 String DiagEoE=null;
								 DiagEoE=mapPathBarr.get("Diagnosis");
								 Pattern EoEPath_pattern = Pattern.compile(".*osinoph.*",Pattern.DOTALL);
								 Matcher matcherEoEPath_pattern = EoEPath_pattern.matcher(DiagEoE);
								 if (matcherEoEPath_pattern.find()) {
									 String EoEHospNum=null;
									 EoEHospNum=mapPathBarr.get("HospNum_Id");
									 if (Checkers.HospNumChecker(st,tabEoE,EoEHospNum)==null){

											String stg3;
											try {
												 stg3 = "INSERT INTO "+tabEoE+" (HospNum_Id) VALUES('"+EoEHospNum+"')";
System.out.println("This is stg2"+stg3);
											try {
												st.execute(stg3);
											} catch (Exception e) {
												
												Logger.error(e+"No process"+":"+stg3);
											}

											System.out.println("Successful execution innit");

									 } catch (Exception e) {

										}
									 }
									 //Check to see if this already exists in that table
								 }
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
											
											Logger.error(e+HospNum+"->From HistopathNew"+filepath);
										}
								    	 //st.close();
								    	  }
									    }
								}
							 System.gc();
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					 //To make sure Hospital NUmber doesn't get misallocated to the next rows results
			mapPathBarr.clear();
}
			workBook.close();
	}
					 }


