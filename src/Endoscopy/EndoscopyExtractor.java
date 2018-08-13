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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Overview.Checkers;
import Overview.DBConnectorForAll;
import Overview.Searcher;

import org.pmw.tinylog.Logger;

public class EndoscopyExtractor {

	public EndoscopyExtractor() {
	}

	@SuppressWarnings("resource")
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
		    //Take the spreadsheet and look at the visitDate
			//Then take the pathology spreadsheet and look at the sample taken date
			//Then add the array together for the ones where the Hospital Number and VisitDate match
			//Then you need to add the pathology reports together and the endoscopies together so that the next iteration of endoscopies have no loose ends of pathology

		    //System.out.println("Yes I am over here in the Endoscopy Section"+filepath);
		    Map<String,String> mapEndoscBarr= new LinkedHashMap<String,String>();
		    //System.out.println("Yes I am overMap here in the Endoscopy Section"+filepath);
			FileInputStream fis = new FileInputStream(new File(filepath));
			//System.out.println("Yes I am overFileInputStream here in the Endoscopy Section"+filepath+" "+fis);

				XSSFWorkbook      workBook2 = new XSSFWorkbook (fis);
			XSSFSheet         sheet    = workBook2.getSheetAt (0);
			List<XSSFRow> filteredRows = new ArrayList<XSSFRow>();

			//Filter by pathology from what I am given
			Iterator<Row> rows= sheet.rowIterator();
			while (rows.hasNext ()){
			XSSFRow row = (XSSFRow) rows.next ();

			Iterator<Cell> cells = row.cellIterator ();
			 while (cells.hasNext ()){
				 XSSFCell cell = (XSSFCell) cells.next ();

                   //Adds whole endoscopy if any cell contains the term Barrett's RFA or APC or EMR
				 if(cell.toString().contains("Endoscopist")&&cell.toString().contains("Gastroscopy")&&(cell.toString().contains("Barrett")||cell.toString().contains("osinoph")||cell.toString().matches(".*osinoph.*"))) {
					 //System.out.println("Barrett's endoscopy detected");
					 filteredRows.add(row);
					  break;

				 }
				 else if
				 //Convoluted as originally was picking up a lot of APC only. RFA and HALO sometimes dont't mention Barrett's being present
					 ((cell.toString().contains("Endoscopist")&&cell.toString().contains("Gastroscopy"))&&((cell.toString().contains("RFA"))|((cell.toString().contains("APC"))&&(cell.toString().contains("Barrett")))|(cell.toString().contains("HALO")))){
					  filteredRows.add(row);
					  break;
				 }
				 //Need to sort this one out so it extracts the eosinophilic endoscopies but figure out whether the EoE storage will mess up the Barrett's endoscopies
				 else if
				 (cell.toString().matches(".*osinoph.*")){
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
					mapEndoscBarr.clear();



					            Pattern DOB_pattern = Pattern.compile("Date of Birth:(.*)");
				                Matcher matcherDOB_pattern = DOB_pattern.matcher(cell.toString());

						        if (matcherDOB_pattern.find()) {
						    	    DOB=matcherDOB_pattern.group(1);
						    	    mapEndoscBarr.put("DOB",DOB);
						        }
						        Pattern HospitalNumpattern = Pattern.compile("Hospital Number(.*)");
							    Matcher matcherHospitalNumpattern = HospitalNumpattern.matcher(cell.toString());

						    	if (matcherHospitalNumpattern.find()) {
								    mapEndoscBarr.put("HospNum_Id",matcherHospitalNumpattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replaceAll(":", ""));
						    	}

							    Pattern VisitDate_pattern = Pattern.compile("Date of Procedure:(.*)");
								Matcher matcherVisitDate_pattern = VisitDate_pattern.matcher(cell.toString());

								if (matcherVisitDate_pattern.find()) {
									mapEndoscBarr.put("VisitDate",matcherVisitDate_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));

								}
							    Pattern Endoscopist_pattern = Pattern.compile("Endoscopist:(.*)");
							    Matcher matcherEndoscopist_pattern = Endoscopist_pattern.matcher(cell.toString());

								if (matcherEndoscopist_pattern.find()) {

									mapEndoscBarr.put("ENDOSCOPIST",matcherEndoscopist_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
								}
								 Pattern Endoscopist2_pattern = Pattern.compile("2nd Endoscopist:(.*)");
								    Matcher matcherEndoscopist2_pattern = Endoscopist2_pattern.matcher(cell.toString());

									if (matcherEndoscopist2_pattern.find()) {
										mapEndoscBarr.put("ENDOTWO",matcherEndoscopist2_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
									}
									 Pattern Trainee_pattern = Pattern.compile("Trainee:(.*)");
									    Matcher matcherTrainee_pattern = Trainee_pattern.matcher(cell.toString());

										if (matcherTrainee_pattern.find()) {
											mapEndoscBarr.put("TRAINEE",matcherTrainee_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
										}

											Pattern Instrument_pattern = Pattern.compile("Instrument:(.*)");
										    Matcher matcherInstrument_pattern = Instrument_pattern.matcher(cell.toString());

											if (matcherInstrument_pattern.find()) {

											}
											Pattern Indication_pattern = Pattern.compile("INDICATIONS FOR EXAMINATION(.*)PROCEDURE PERFORMED",Pattern.DOTALL);
										    Matcher matcherIndication_pattern = Indication_pattern.matcher(cell.toString());

											if (matcherIndication_pattern.find()) {
												mapEndoscBarr.put("INDICATIONS",matcherIndication_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
											}
											Pattern ProcPerf_pattern = Pattern.compile("PROCEDURE PERFORMED(.*)FINDINGS",Pattern.DOTALL);
										    Matcher matcherProcPerf_pattern = ProcPerf_pattern.matcher(cell.toString());

											if (matcherProcPerf_pattern.find()) {
												mapEndoscBarr.put("ERPROCEDUREPERFORMED",matcherProcPerf_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
											}
											Pattern Findings_pattern = Pattern.compile("FINDINGS(.*)ENDOSCOPIC DIAGNOSIS",Pattern.DOTALL);
										    Matcher matcherFindings_pattern = Findings_pattern.matcher(cell.toString());

											if (matcherFindings_pattern.find()) {
												mapEndoscBarr.put("ERFINDINGSSTR",matcherFindings_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));

											}
											Pattern EndDx_pattern = Pattern.compile("ENDOSCOPIC DIAGNOSIS(.*)RECOMMENDATIONS",Pattern.DOTALL);
										    Matcher matcherEndDx_pattern = EndDx_pattern.matcher(cell.toString());

											if (matcherEndDx_pattern.find()) {
												mapEndoscBarr.put("ERDIAGNOSISSTR",matcherEndDx_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));

											}
											Pattern Recommen_pattern = Pattern.compile("RECOMMENDATIONS(.*)COMMENTS",Pattern.DOTALL);
										    Matcher matcherRecommen_pattern = Recommen_pattern.matcher(cell.toString());

											if (matcherRecommen_pattern.find()) {

												mapEndoscBarr.put("ERRECOMMENDATIONS",matcherRecommen_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));

											}
											Pattern Comment_pattern = Pattern.compile("COMMENTS(.*)FOLLOW UP ",Pattern.DOTALL);
										    Matcher matcherComment_pattern = Comment_pattern.matcher(cell.toString());

											if (matcherComment_pattern.find()) {
												in.add(matcherComment_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
												mapEndoscBarr.put("ERRECOMMENDATIONS",matcherComment_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", "").replace("'", ""));
											}
											Pattern FU_pattern = Pattern.compile("FOLLOW UP(.*)OPCS4 Code: ",Pattern.DOTALL);
										    Matcher matcherFU_pattern = FU_pattern.matcher(cell.toString());

											if (matcherFU_pattern.find()) {
												in.add(matcherFU_pattern.group(1).replaceAll("\\n^$", "").replaceAll("  ", "").replaceAll("\n", "").replaceAll("\\t", ""));
											}

											try {
												FName=Searcher.FName_searcher(cell.toString());
												if (FName!=null){
													mapEndoscBarr.put("FIRSTNAME",FName);
												}


											} catch (Exception e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											try {
												SName=Searcher.SName_searcher(cell.toString());
												if (SName!=null){
													mapEndoscBarr.put("LASTNAME",SName);
												}


											} catch (Exception e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}




											 if(!mapEndoscBarr.isEmpty()){
											 DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
											 tab="Endoscopy";
												System.out.println("mapEndoscBarrDBConnector     "+mapEndoscBarr);
												 //System.out.println("mapEndoscBarr.get(FName).toString()"+mapEndoscBarr.get("FName").toString());
												try {
													FName=mapEndoscBarr.get("FIRSTNAME").toString().trim();
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												System.out.println("mapEndoscBarr"+mapEndoscBarr);
												 try {
													SName=mapEndoscBarr.get("LASTNAME").toString().trim();
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												 try {
													DOB=mapEndoscBarr.get("DOB").toString().trim();
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												 try {
													 HospNum=mapEndoscBarr.get("HospNum_Id").toString().trim();
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												 try {
													 VisitDate=mapEndoscBarr.get("VisitDate").toString().trim();
													 VisitDate=VisitDate.replace("\\", "_").replace("/","_");
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}

												 tab="Endoscopy";

													 try {
														st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);
														 first=ConnectMeUp.StringInsertKeyPreparer(st,mapEndoscBarr,tab);
														 second=ConnectMeUp.StringInsertValuePreparer(st,mapEndoscBarr,tab);
														 if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate)){
															ConnectMeUp.Inserter(st,HospNum,first,second,tab,filepath);

														    }
														 System.gc();
													} catch (Exception e) {
														// TODO Auto-generated catch block
														Logger.error(e+HospNum+"->From EndoscopyNew"+filepath);
													}
											 }
				 }
			}

			workBook2.close();

	}
}