package Diag;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import Overview.Checkers;
import Overview.DBConnectorForAll;
import Overview.Searcher;
import Overview.VisitDateFormatter;
import org.pmw.tinylog.Logger;


public class mane {

static String FName;
static String SName;
static String HospNum;
static String DOB;
static String VisitDate;
static String PPI;
static String IndicANDHx;
static String RDQScore=null;
static String HODQScore=null;
String s=null;
static Map<String,String> mapAllDiag= new LinkedHashMap<String,String>();

	public mane(String s) {
	}
	public static Map<String, String> Dimane(String s,String child,String FileCreationDate) throws IOException, SAXException, TikaException, SQLException, ParseException {
		s=s.replaceAll(" ", " ").trim();
		System.out.println("Im in Diag.mane.Dimane");

		mapAllDiag.clear();
		 try {
	         HospNum=Overview.Searcher.HospNo_searcher(s);
                if(HospNum==""|HospNum==null|HospNum.equals("0207188419")){
                	HospNum=Overview.Searcher.HospNo_searcher(child.toString());
					 }
					} catch (Exception e2) {
						Logger.error(e2+"Couldn't get the hospital number"+child);
					}
				mapAllDiag.put("HospNum_Id", HospNum);
				mapAllDiag.put("FileCreationDate", FileCreationDate);




				try {
					FName=Overview.Searcher.FName_searcher(s);
				} catch (Exception e4) {
					// TODO Auto-generated catch block
					 Logger.error("FNAME ERROR HRMALL"+e4+HospNum+child);
				}
		 		mapAllDiag.put("FName", FName);

		 		try{
		 			SName=Overview.Searcher.SName_searcher(s);
				} catch (Exception e1) {
					Logger.error(e1+HospNum+child);
				}
		 		//System.out.println("SName"+ SName);
		 		if(SName!=null){
		 			SName=SName.replace("'", "");
		 			mapAllDiag.put("SName", SName);
		 		}


//VisitDate
		 		try {
					VisitDate=Overview.Searcher.VisitDate_searcher(s);
				} catch (Exception e3) {
					Logger.error(e3+VisitDate+"->From Diag-No VisitDate"+child);
				}

		 		try {
					VisitDate=VisitDateFormatter.VDFormat(VisitDate);
					//System.out.println("VisitDate+Diag"+ VisitDate);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					Logger.error(e2+HospNum+"->From HRMAll-VDFormat issue"+child);
				}
		 		if(VisitDate!=null){
		 		//System.out.println("VisitDate"+ VisitDate);
		 		mapAllDiag.put("VisitDate", VisitDate);
		 		}

		 		try {
					DOB=Overview.Searcher.DOB_searcher(s);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					Logger.error(e2+HospNum+"->From Diag Mane No DOB"+DOB+child);
				}
		 		if(DOB!=null){
		 			//System.out.println("DOB"+ DOB);
		 			mapAllDiag.put("DOB", DOB);
		 		}

		 		String Indic1="Summary of findings(.*).*?\\n";
				String Indic2="Indications and history(.*)Summary of findings:";
				String Indic3="Indication(.*)?Anggiansah";
				String Indic4="Indication(.*)SUMMARY";
				String Indic5="Indication(.*)^Performed";
				String Indic6="Indication(.*)?Jafar";
				String Indic7="Indication(.*)?Conclusion";
				String Indic8="Indication(.*)?pH-impedance result";
				String Indic9="Indication(.*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n])";


				//Add in the PPI string searcher in here

				try {
					IndicANDHx=Searcher.multiline_searcher(s,Indic1);
					mapAllDiag.put("IndicANDHx", IndicANDHx);
					if(IndicANDHx==null){
						IndicANDHx=Searcher.multiline_searcher(s,Indic2);
						////System.out.println("Indic2"+Indic2);
						if(IndicANDHx==null){
							IndicANDHx=Searcher.multiline_searcher(s,Indic3);
							////System.out.println("Indic3"+Indic3);
							if(IndicANDHx==null){
								IndicANDHx=Searcher.multiline_searcher(s,Indic4);
								////System.out.println("Indic4"+Indic4);
								if(IndicANDHx==null){
									IndicANDHx=Searcher.multiline_searcher(s,Indic5);
									if(IndicANDHx==null){
										IndicANDHx=Searcher.multiline_searcher(s,Indic6);
										if(IndicANDHx==null){
											IndicANDHx=Searcher.multiline_searcher(s,Indic7);
											if(IndicANDHx==null){
												IndicANDHx=Searcher.multiline_searcher(s,Indic8);
												if(IndicANDHx==null){
													IndicANDHx=Searcher.multiline_searcher(s,Indic9);
													if(IndicANDHx==null){
														IndicANDHx="Nil found";
													}

												}
											}
										}
									}
								}
							}
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					Logger.error(e1+"NoIdxAndHx"+HospNum+child);
				}
				//System.out.println("IndicAndHxHERE"+IndicANDHx);
				if(IndicANDHx!=null){
				IndicANDHx=IndicANDHx.replaceAll("\\(", "~").replaceAll("\\)", "~").replaceAll("'", "");
				mapAllDiag.put("IndicANDHx", IndicANDHx);
				}


				String PPI1="(?:^| )[Oo][Nn] (.*(?=PPI)).*?Indications and|(?:^| )[Oo][Ff]{2} (.*(?=PPI)).*?Indications and";
				String PPI2="Monitoring( .*?PPI)";

				try{
					PPI=null;
					PPI=Searcher.multiline_searcher(s,PPI1);
					if(PPI==null){
						PPI=Searcher.searcher(s,PPI2);
						if(PPI==null){
							PPI="Nil found";
						}
					}
			       } catch (Exception e1) {
		// TODO Auto-generated catch block
		Logger.error(e1+"PPI "+HospNum+child);
	}
				 if(PPI!=null){
				mapAllDiag.put("PPI", PPI);
				 }
				//

				String RDQ="RDQ \\(reflux\\) score:(.*)\\/\\d\\s+";
                try {
                	RDQScore=null;
					RDQScore=Searcher.searcher(s,RDQ);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					Logger.error(e1+HospNum+child);
				}

                //System.out.println("RDQScore"+RDQScore);
                if(RDQScore!=null){
				mapAllDiag.put("RDQScore", RDQScore);
                }


				//TODO this HODQ score in Diag mane doesnt work.
				String HODQ="HODQ \\(dysphagia\\) score:(.*)?\\(";
				////System.out.println("HODQScore"+HODQScore);
				try {
					HODQScore=Searcher.searcher(s,HODQ);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					Logger.error(e1+HospNum+child);
				}
				if(HODQScore!=null){
				mapAllDiag.put("HODQScore", HODQScore);
				}

				System.out.println("mapAllDiag"+mapAllDiag);
								  try {
									String tab="Diag";
									    DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
										Statement st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);
										String first=ConnectMeUp.StringInsertKeyPreparer(st,mapAllDiag,tab);
										String second=ConnectMeUp.StringInsertValuePreparer(st,mapAllDiag,tab);
											if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate.replace("/", "_"))){
										ConnectMeUp.Inserter(st,HospNum,first,second,tab,child);
										//mapAllDiag=null;
										}
								} catch (Exception e) {
									Logger.error(e+HospNum+"->From Diag"+child);
								}
								return mapAllDiag;
	}

}
