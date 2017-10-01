package Overview;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Logger;

public class Searcher {

	public Searcher() {
		// TODO Auto-generated constructor stub
	}
	public static String searcher(String s,String searchPattern) throws IOException, SQLException {
		String match=null;

		try {
			Pattern match_patternDoc = Pattern.compile(searchPattern);
			Matcher matchermatch_patternDoc = match_patternDoc.matcher(s);
			  if (matchermatch_patternDoc.find()) {
					match=matchermatch_patternDoc.group(1).trim();
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("The searcher couldn't find this"+e);
			//Logger.error(e+"The searcher couldn't find this "+searchPattern);
		}
		return match;
	}


	public static String multiline_searcher(String s,String searchPattern) throws IOException, SQLException {
		String match=null;
		//System.out.println("THIS IS THE searchPattern"+searchPattern);
		Pattern match_patternDoc = Pattern.compile(searchPattern,Pattern.DOTALL);
		Matcher matchermatch_patternDoc = match_patternDoc.matcher(s);
		  if (matchermatch_patternDoc.find()) {
				match=matchermatch_patternDoc.group(1);

			}
		 //System.out.println("THIS IS THE MATCH"+match);
		return match;

	}

	//Gives either or matches for the search string
	public static String threeOption_searcher(String s,String opt1,String opt2, String opt3) throws IOException, SQLException {
		String match=null;
		match=Searcher.searcher(s,opt1);
 		if(match==null){
 			match=Searcher.searcher(s,opt2);
 			if(match==null){
 				match=Searcher.searcher(s,opt3);
 				}
 		}
		return match;

	}

	public static String HospNo_searcher(String s) throws IOException, SQLException {
		String HospNo=null;
		String HospNumGen1="(\\d{6,9}(?:[A-Z]|[a-z]))";
		String HospNumGen2="((?:[A-Z]|[a-z])\\d{6,7})";
		String HospNumGen3="(\\d{10})";
		 try {
			HospNo=Searcher.threeOption_searcher(s,HospNumGen1,HospNumGen2,HospNumGen3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HospNo;

	}


	public static String VisitDate_searcher(String s) throws IOException, SQLException {
		String VisitDate=null;
		String VisitDate1="Examination [Dd]ate:\\n\\t(.*)";
 		String VisitDate2="Examination [Dd]ate:(.*)";
 		String VisitDate3="Study Date:.*(\\d+\\/\\d+\\/\\d+)";
 		String VisitDate4="Study Date: (.*?)";
 		String VisitDate5="Study Date:.*\n(.*?)";
 		String VisitDate6="Examination Date.*?\\d{2}.?{2}?.\\d{4}";
 		String VisitDate7="Examination.*?[Dd]ate.+?(\\d{2}.\\d{2}.\\d{4})";
 		String VisitDate8="Report.*?[Dd]ate.+?(\\d{2}.\\d{2}.\\d{4})";
 		String VisitDate9="Report:.+?(\\d{2}.\\d{2}.\\d{4})";

 		try {
 			VisitDate=Searcher.threeOption_searcher(s,VisitDate1,VisitDate2,VisitDate3);
 			if(VisitDate==null|StringUtils.isEmpty(VisitDate)){
 				VisitDate=Searcher.threeOption_searcher(s,VisitDate4,VisitDate5,VisitDate6);
 				if(VisitDate==null|StringUtils.isEmpty(VisitDate)){
 					VisitDate=Searcher.multiline_searcher(s,VisitDate7);
 					if(VisitDate==null|StringUtils.isEmpty(VisitDate)){
 	 					VisitDate=Searcher.multiline_searcher(s,VisitDate8);
 	 					if(VisitDate==null|StringUtils.isEmpty(VisitDate)){
 	 	 					VisitDate=Searcher.multiline_searcher(s,VisitDate9);
 	 	 				}
 	 				}
 				}
 			}
		} catch (Exception e3) {
		}

 		VisitDate=VisitDate.replace("\\", "_").replace("/","_");
		return VisitDate;
	}

	public static String FName_searcher(String s) throws IOException, SQLException {
	String FName=null;
	String FName1="Patient?:.*?\\s(.*)?\\s";
	String FName2="Patient.*\\n\\n(.*),";
		String FName3="Patient.*\\n(.*),";
		String FName4="Motility Study\\n\\n(.*?)\\s";
		String FName5="Re:.*?([A-Za-z]+)";
		 //String FName6="Patient.*\\n\\n(.*),";
 		 String FName6="Patient.*\\n(.*),";
 		String FName7="Patient Name:[A-Z]+?\\s(.*)?\\s.*";
		try {
		FName=Searcher.searcher(s,FName1);
		if(FName==null){
			FName=Searcher.searcher(s,FName2);
			if(FName==null){
				FName=Searcher.searcher(s,FName3);
				if(FName==null){
					FName=Searcher.searcher(s,FName4);
					if(FName==null){
						FName=Searcher.searcher(s,FName5);
						if(FName==null){
							FName=Searcher.searcher(s,FName6);
							if(FName==null){
								FName=Searcher.searcher(s,FName7);
								if(FName==null){
									FName=null;

								}
							}
						}
					}
				}
			}
		}
	} catch (Exception e1) {
		// TODO Auto-generated catch block
	}
		return FName;
	}

	public static String SName_searcher(String s) throws IOException, SQLException {
		String SName=null;
		String SName1="Patient?:.*?\\s.*?\\s(.*)?\\s";
 		String SName2="Patient.*?\\n\\n.*?,(.*)";
 		String SName3="Patient.*?\\n.*,(.*)";
 		String SName4="Motility Study\\n\\n.*?\\s(.*?)\\n";
 		String SName5="Re:.* ([A-Za-z]+)?,";
 		String SName6="Patient.*\\\n\\\n.*,(.*)";
 		String SName7="Patient.*\\\n.*,(.*)";
 		String SName8="Patient Name:[A-Z]+?\\s.*?\\s(.*)";
 		//String SName8="Patient Name:.*\\\n.*,(.*)";
 		//System.out.println("Here");
          try{
			SName=Searcher.searcher(s,SName1);
			if(SName==null|SName==""){
				SName=Searcher.searcher(s,SName2);
				if(SName==null|SName==""){
					SName=Searcher.searcher(s,SName3);
					if(SName==null|SName==""){
						SName=Searcher.searcher(s,SName4);
						if(SName==null|SName==""){
							SName=Searcher.searcher(s,SName5);
							//System.out.println("MADE IT DOWN HERE SNAME"+SName);
							if(SName==null|SName==""){
								SName=Searcher.searcher(s,SName6);
								//System.out.println("MADE IT DOWN HERE SNAME"+SName);
								if(SName==null|SName==""){
									SName=Searcher.searcher(s,SName7);
									if(SName==null|SName==""){
										SName=Searcher.searcher(s,SName8);
										if(SName==null|SName==""){
											SName=null;
										}
									}
									//System.out.println("MADE IT DOWN HERE SNAME"+SName);
								}
							}
						}
					}
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
			return SName;
		}

	public static String DOB_searcher(String s) throws IOException, SQLException {
		String DOB=null;
		String DOB1="DOB?\\s/?\\sAge:\\n\\t(.*)";
 		String DOB2="DOB?\\s/?\\sAge:.+?(\\d{2}.\\d{2}.\\d{4})";
 		String DOB3="DOB?\\s/?\\sAge:\\n(.*)";
 		String DOB4="D\\.O\\.B(.*)?,";
 		String DOB5="DOB?\\s/?\\sAge: (.*)Physiologist";
 		String DOB6="DOB?\\s/?\\sAge: (.*)Technician";
 		String DOB7="DOB(?:_|:)(.*)";
 		String DOB8="Birth Date:.+?(\\d{2}.\\d{2}.\\d{4})";
 		String DOB9="Birth Date:(.*)Technician";
 		String DOB10="Birth Date:\\n(.*)Technician";
 		String DOB11="D\\.O\\.B:(.+?(\\d{2}.\\d{2}.\\d{4}))";
 		String DOB12="DOB?\\s/?\\sAge:\\t(.*)";
 		String DOB13="DOB?\\s/?\\sAge:(.*)[A-Z]";
 		String DOB14="Date of Birth:.+?(\\d{2}.\\d{2}.\\d{4})";
 		String DOB15="DOB?\\s/?\\sAge:.+?(\\d{2}.\\d{2}.\\d{4})";

 		DOB=Searcher.searcher(s,DOB1);
 		if(DOB==null){
 			DOB=Searcher.searcher(s,DOB2);
 			if(DOB==null){
	 			DOB=Searcher.searcher(s,DOB3);
	 			if(DOB==null){
		 			DOB=Searcher.searcher(s,DOB4);
		 			if(DOB==null){
		 				DOB=Searcher.searcher(s,DOB5);
		 				if(DOB==null){
			 				DOB=Searcher.searcher(s,DOB6);
			 				if(DOB==null){
			 					DOB=Searcher.searcher(s,DOB7);
				 				if(DOB==null){
				 					DOB=Searcher.searcher(s,DOB8);
					 				if(DOB==null){
					 					DOB=Searcher.searcher(s,DOB9);
						 				if(DOB==null){
							 				DOB=Searcher.searcher(s,DOB10);
								 		    if(DOB==null){
								 		    	DOB=Searcher.searcher(s,DOB11);
								 		    	 if(DOB==null){
								 		    		DOB=Searcher.searcher(s,DOB12);
									 		    	 if(DOB==null){
										 		    	DOB=Searcher.searcher(s,DOB13);
											 		       if(DOB==null){
											 		    	  DOB=Searcher.searcher(s,DOB14);
											 		    	 if(DOB==null){
												 		    	  DOB=Searcher.searcher(s,DOB15);
											 		    	 if(DOB==null){
											 		    		 DOB=null;
											 		    	 }
											 		    	     }
											 		    	   }
											 		    	 }
									 		    	    }
										 			}
								 		    	 }
									 		}
							 			}
						 			}
					 			}
				 			}
			 			}
		 			}
	 			}

		return DOB;
	}
}
