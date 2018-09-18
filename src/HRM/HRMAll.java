package HRM;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Overview.Checkers;
import Overview.DBConnectorForAll;
import Overview.Searcher;
import Overview.VisitDateFormatter;
import org.pmw.tinylog.Logger;

public class HRMAll {
	static String HospNum=null;
	static String FName=null;
	static String SName=null;
	static String DOB=null;
	static String VisitDate=null;
	static String Gender =null;
	static URL filename;
	static Map<String,String>  mapAllReport = new LinkedHashMap<String,String>();
	static Map<String,String> mapSwallow= new LinkedHashMap<String,String>();
	static Path p;
	String s=null;

	public HRMAll() {
	}
	public static void mane(String s,String child,String FileCreationDate) throws IOException, SQLException, ParseException, URISyntaxException {

		s=s.replaceAll(" ", " ").trim();
		s=s.replaceAll("^\\s", "").replace(" 	     ", "").replaceAll("\n\\t", " ");

		System.out.println("Pre replaceAll"+s);
//Replace (cm/s) with cms
		s=s.replaceAll("    ", "\n").replaceAll("^ ", "").replaceAll("^ ", "").replaceAll("\\(cm\\/s\\)","").replaceAll("\\(Chicago Classification\\)","Chicago Classification").replaceAll("(\\(.*)\\n(.*\\)).*\\n\\n(.*)", "$1$2 $3");
		//System.out.println("Pre find and replace"+s);

		s=Checkers.FindNReplace(s);

		 //System.out.println("Post find and replace"+s);
		 s=s.replaceAll("(\\(cm\\)\\s?){2,}","\\(cm\\) ").replaceAll("(?:\\(cm\\/s\\)\\s?){2,}","\\(cm\\/s\\)").replaceAll("(\\(mmHg\\)\\s?){2,}","\\(mmHg\\)");
		 System.out.println("I'm in HRM");
		 //Map<String,String> mapAllReport= new LinkedHashMap<String,String>();
		 mapAllReport.clear();
		 mapSwallow.clear();
		 filename = HRMAll.class.getResource("/HRMFields.txt");

//Hospital Number


			 mapAllReport=MainHRMExtractor(s,child);
			 mapAllReport.put("FileCreationDate", FileCreationDate);
			 mapSwallow=SwallowExtractor(s);

		        String tab="HRMImportMain";
		        String table="HRMImportSwallows";
		        int sharedkey;
		        String third;
		        String fourth;

			    DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
			    ////System.out.println("mapSwallowBeforeDBConnection"+mapSwallow);
				Statement st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);
				String first=ConnectMeUp.StringInsertKeyPreparer(st,mapAllReport,tab);
				String second=ConnectMeUp.StringInsertValuePreparer(st,mapAllReport,tab);


					if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate.replace("/", "_"))){
						//System.out.println("THE mapSwallow is"+mapSwallow);
						try {
							sharedkey=ConnectMeUp.Inserter(st,HospNum,first,second,tab,child);
							//System.out.println("mapSwallowBeforeDBConnection21"+mapSwallow);
							third=ConnectMeUp.StringInsertKeyPreparer(st,mapSwallow,table);
							fourth=ConnectMeUp.StringInsertValuePreparer(st,mapSwallow,table);
							String FKFieldName="HRM_Id";
							////System.out.println("THE mapSwallow size is"+mapSwallow.size());

							if(mapSwallow.size()>3){
							ConnectMeUp.Inserter2(st,third,fourth,table,sharedkey,FKFieldName,child);
							}
						} catch (Exception e1) {

							Logger.error(e1+HospNum+"->From HRMAll"+child);
						}

				}
				}

	public static Map<String, String> MainHRMExtractor(String s,String child) throws IOException, SQLException{
		try {
	         HospNum=Overview.Searcher.HospNo_searcher(s);
	         if(HospNum==null||HospNum==""||HospNum.isEmpty()||HospNum.equals("0207188419")){
               	HospNum=Overview.Searcher.HospNo_searcher(child.toString());
					 }
			} catch (Exception e3) {

				Logger.error(e3+"Couldn't get the hospital number"+child);
			}
	          ////System.out.println("HospNumHRMALL"+ HospNum);
	          mapAllReport.put("HospNum_Id", HospNum);


//For the FName Extraction
	          try {
				FName=Overview.Searcher.FName_searcher(s);
			} catch (Exception e4) {

				 Logger.error("FNAME ERROR HRMALL"+e4+HospNum+child);
			}

	 		mapAllReport.put("FName", FName);


//For the SName Extraction
	 		try{
	 			SName=Overview.Searcher.SName_searcher(s);
			} catch (Exception e1) {
				Logger.error(e1+HospNum+child);
			}
	 				SName=SName.replace("'", "");
	 		mapAllReport.put("SName", SName);


//VisitDate
	 		try {
				VisitDate=Overview.Searcher.VisitDate_searcher(s);
			} catch (Exception e3) {
				Logger.error(e3+VisitDate+"->From HRMAll-No VisitDate"+child);
			}

	 		try {
	 			VisitDate=VisitDate.trim();
	 			VisitDate=VisitDate.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
	 			System.out.println("VisitDateWithtrim+HRMAll"+ VisitDate);
				VisitDate=VisitDateFormatter.VDFormat(VisitDate);
				System.out.println("VisitDate+HRMAll"+ VisitDate);
			} catch (Exception e2) {

				Logger.error(e2+HospNum+"->From HRMAll-VDFormat issue"+child);
			}
	 		mapAllReport.put("VisitDate", VisitDate);

//DOB
	 		try {
				DOB=Overview.Searcher.DOB_searcher(s);
			} catch (Exception e2) {
				Logger.error(e2+HospNum+"->From Diag Mane No DOB"+DOB+child);
			}
	 		mapAllReport.put("DOBAge", DOB);
//Gender
	 		String Gender1="Gender:.*\\n\\t(.*)";
	 		String Gender2="Gender:(.*)Physician|Physiologist";
	 		String Gender3="Gender:\\s*(.*)";
	 		String Gender = Searcher.threeOption_searcher(s,Gender1,Gender2,Gender3);
	 		mapAllReport.put("Gender", Gender);
//Ref Phys
	 		String RefPhys1="Referring [Pp]hysician:.*\\n\\t(.*)";
	 		String RefPhys2="Referring [Pp]hysician:(.*)";
	 		String RefPhys3="Referring [Pp]hysician:\\s*(.*)";
	 		String RefPhys = Searcher.threeOption_searcher(s,RefPhys1,RefPhys2,RefPhys3);

	 		mapAllReport.put("ReferringPhysician", RefPhys);
//Phys
	 		String Phys1="Physician:.*\\n\\t(.*)";
	 		String Phys2="Physician:(.*)";
	 		String Phys3="Physician:\\s*(.*)";
	 		String Phys = Searcher.threeOption_searcher(s,Phys1,Phys2,Phys3);
	 		mapAllReport.put("Physician", Phys);
//Height
	 		//String Height1="Height:.*\\s+in";
	 		String Height1="Height:(.*)in\\s*";
	 		String Height2="Height:(.*)?in.{1}\\s*";
	 		String Height3="Height:(.*)?cm.{1}\\s*";
	 		//////System.out.println("THIS IS THE HEIGHT "+s);
	 		String Height4="";
	 		String Height5="Height:(.*?[\\n].*?in)";
           String Height;
	 		try {
				Height=Searcher.threeOption_searcher(s,Height1,Height2,Height3);
				if(Height==null)
				{
					Height=Searcher.multiline_searcher(s,Height4);
					if(Height==null)
					{
						Height=Searcher.multiline_searcher(s,Height5);
					}
				}
				mapAllReport.put("Height", Height);
			} catch (Exception e1) {

				//Logger.error(e1+"No height here"+HospNum+child);
				////System.out.println("ERROR no HEIGHT HERE for: "+HospNum);
			}
//Operator
	 		String Operator1="Physiologist|Operator:.*\\n\\t(.*)";
	 		String Operator2="Physiologist|Operator:(.*)";
	 		String Operator3="Physiologist|Operator:\\s*(.*)";
	 		String Operator = Searcher.threeOption_searcher(s,Operator1,Operator2,Operator3);
	 		mapAllReport.put("Operator", Operator);

			 String line = null;
			 String [] t = null;
			 BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.class.getResourceAsStream("/Files/HRMFields.txt")),2048);

			 while ((line = reader.readLine()) != null)
				 //Reads the lines and then adds it straight to the map for that patient
			 {
				 try {
					t=stringFlag(line,s);

				} catch (Exception e) {
					e.getMessage();
				}

				 try {
					 if(t!=null){
					mapAllReport.put(t[0],t[1]);
					 }

				} catch (Exception e) {
				}

			 }
			 reader.close();
		return mapAllReport;

	}

	public static Map<String, String> SwallowExtractor(String s){
		//mapSwallow=null;

		Pattern match_patternSwallow = Pattern.compile("\t    .*[a-z](.*)\\n(\\t\\n)?(?:\t.+\\n)(\\t\\d+\\.\\d\\n|\\tN\\/A\\n|\\t\\d*\\n){4,50}");
		Pattern match_patternSwallow2= Pattern.compile("(?m)^\\W*([a-zA-Z].*?)\\s*((?:(?:\\d+(?:\\.\\d+)?|N/A)\\s*)*)$");

		 Matcher matchermatch_patternSwallow = match_patternSwallow.matcher(s);
		 Matcher matchermatch_patternSwallow2 = match_patternSwallow2.matcher(s);
		 //Then need to split the pattern into ArrayList
		 while (matchermatch_patternSwallow.find()){

			 String found=matchermatch_patternSwallow.group(0).replaceAll("\\t","").trim();

			 List<String> myList = new ArrayList<String>(Arrays.asList(found.split("\n")));
			 myList.remove(1);

			 for (int ff=1;ff<myList.size();ff++){
					//Symptom

				 mapSwallow.put(myList.get(0).replaceAll(" 480-", "").replaceAll(" 500-", "").replaceAll(" 43-", "").replaceAll(" 74-", "").replaceAll(" 259-", "").replace("-", "").replaceAll("\\s+", "").replaceAll("<", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("nadirmsec", "nadir")+"MapSwallowsNum"+ff,myList.get(ff));
						 //.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("43-","").replace("-", "").replaceAll("\\/", "")+"MapSwallowsNum"+ff,myList.get(ff));
					  }
		 }

			 //Then need to split the pattern into ArrayList
				while (matchermatch_patternSwallow2.find()){
		            String[] myList = matchermatch_patternSwallow2.group(2).split("\\s+");
		            String p1 = matchermatch_patternSwallow2.group(1).replaceAll(" 480-", "").replaceAll(" 500-", "").replaceAll(" 43-", "").replaceAll(" 74-", "").replaceAll(" 259-", "").replace("-", "").replaceAll("\\s+", "").replaceAll("<", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("nadirmsec", "nadir");

		            int line = 1;
		            for (String p2s: myList){
		            	//System.out.println("The Swallow symptom here: "+p1);
		        	    mapSwallow.put(p1+"MapSwallowsNum"+line, p2s);
		        	    line++;
		            }
		         }

System.out.println("MYSWALLOW"+mapSwallow);
		 return mapSwallow;
	}

	public static String[] stringFlag(String start,String s) throws IOException, SQLException {
		//This takes the start string eg CFV and the whole string  and creates a pattern using that as the start for extraction of the key and value into an Array
		 String [] seTab3_HRM=null;
		 //Had to do a dirty mini find and replace as the main dictionary would end up duplicating cms where it wasnt present
		 s=s.replaceAll(" :\\n", "").replaceAll("\\):", "");


		 Pattern match_patternWhole = Pattern.compile("("+start.trim()+"?\\s*)(\\-?\\d+\\.\\d+|[Nn]\\/[Aa]|\\d+|Yes|No)?",Pattern.DOTALL);
		 Matcher matchermatch_patternWhole = match_patternWhole.matcher(s);
			Pattern match_patternWhole2 = Pattern.compile("      ("+start.trim()+"\\s*)(\\S+)",Pattern.DOTALL);
			Matcher matchermatch_patternWhole2 = match_patternWhole2.matcher(s);
    			Pattern match_patternWhole3 = Pattern.compile("("+start.trim()+".*\\n.*)(\\-?\\d+\\.\\d+|[Nn\\/Aa])");
		    	Matcher matchermatch_patternWhole3 = match_patternWhole3.matcher(s);
					if (matchermatch_patternWhole.find()) {
						String match;
						String match2;
						String match3;
						String all =matchermatch_patternWhole.group(0).trim();
						match=matchermatch_patternWhole.group(1).replaceAll("\\n", "").replaceAll("\\s+","").trim().replaceAll("LES-UES","LESUES");
						match2=matchermatch_patternWhole.group(2).replaceAll("\\n", "").trim();
								match3=match+"   "+match2;
						seTab3_HRM=match3.split("\\s+(?=\\S*$)");
					}
					else if(matchermatch_patternWhole2.find()){
						String match2;
						match2=matchermatch_patternWhole2.group(1).replaceAll("\\n", "").trim();
						seTab3_HRM=match2.split("\\s+(?=\\S*$)");
					}
					else if(matchermatch_patternWhole3.find()){
						String match3;
						match3=matchermatch_patternWhole3.group(0).replaceAll("\\n", "").trim();
						seTab3_HRM=match3.split("\\s+(?=\\S*$)");
					}
			return seTab3_HRM;
	}
}
