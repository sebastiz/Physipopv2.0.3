package Overview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pmw.tinylog.Logger;


public class VisitDateFormatter {

	public VisitDateFormatter() {
	}

	public static String VDFormatCleaner(String VisitDate) throws ParseException{
		String cleanYear=null;
		System.out.println(VisitDate+"The start");
		    try {
		    	VisitDate=VisitDate.replaceAll(" ", " ").trim();
				VisitDate = VisitDate.replace("-", "_").trim();
				VisitDate = VisitDate.replace("th", "").trim();
				VisitDate = VisitDate.replace("rd", "").trim();
				VisitDate = VisitDate.replace("of", "").trim();
				VisitDate = VisitDate.replace("/", "_").trim();
				VisitDate = VisitDate.replace(",", "_").trim();
				VisitDate = VisitDate.replace("^\\s+", "_").trim();
				VisitDate = VisitDate.replace(" ", "_").trim();
				VisitDate = VisitDate.replace("__", "_").trim();
				VisitDate = VisitDate.replace(".", "_").trim();
				VisitDate = VisitDate.replaceAll("^\\s+", "").trim();
				cleanYear = VisitDate.replaceAll("$\\s+", "").trim();
				System.out.println(VisitDate+"The end");
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println("String replace FAILVDFormatCleaner");
			}
		    System.out.println(cleanYear+"The start");
	return cleanYear;
	}

	public static String VDFormat(String VisitDate) throws ParseException{

		//if pattern matches 20dd.*then do the swap otherwise don't

		 Pattern match_YearAtFront = Pattern.compile("^\\d{4}");
		 Matcher matchermatch_YearAtFront = match_YearAtFront.matcher(VisitDate);

		 if (matchermatch_YearAtFront.find()){
			 System.out.println("THis is the start VisitDate year at front");
			 VisitDate=VDFormatCleaner(VisitDate);
			 VisitDate=VisitDate.replaceAll("(\\d{4})(.)(.*)", "$3$2$1").trim();
		 }
		 else{
			 System.out.println("THis is the start VisitDate year at back");
			 VisitDate=VDFormatCleaner(VisitDate);
		 }
            //This moves the year to the back if it's in the front but shouldn't be done if the year isn't at the beginning
		    //This part reformats if words in the dates- this is usually in the Breath Test results
		    VisitDate=VisitDate.trim();
		    //System.out.println("The date befoer s parsing"+VisitDate);


	    	//System.out.println("The date after s parsing"+VisitDate+VisitDate.getClass());
		    if (VisitDate.matches(".*[a-z].*")){
		    	//System.out.println("MATCHES");
		    SimpleDateFormat changeDate = new SimpleDateFormat("dd_MMM_yy", Locale.UK);
		    //Convert the string to a date
		    //System.out.println("MATCHES2");

		    try {
		    	 //System.out.println("MATCHES3"+VisitDate);
		    Date date = changeDate.parse(VisitDate);
		    //System.out.println("MATCHES4");
		    //System.out.println("THE DATE IS"+date);


		    //Reformat the date the way I like it
		    SimpleDateFormat dt1 = new SimpleDateFormat("dd_MM_yyyy");
		   // System.out.println("The last date to be done1"+VisitDate);
		    //Convert back into a string

				VisitDate=dt1.format(date);
				//System.out.println("The last date to be done_VisitDate_after try"+VisitDate);


				/*if(VisitDate==null){
					System.out.println("The last date to be done2"+VisitDate);
					SimpleDateFormat dt2 = new SimpleDateFormat("dd MM yy");
				    //Convert back into a string
				    VisitDate=dt2.format(date);
				    if(VisitDate==null){
				    	System.out.println("The last date to be done3"+VisitDate);
				    	SimpleDateFormat dt3 = new SimpleDateFormat("dd MMMM yy");
					    //Convert back into a string
				        VisitDate=dt3.format(date);
				        if(VisitDate==null){
				        	System.out.println("The last date to be done4"+VisitDate);
				        	SimpleDateFormat dt4 = new SimpleDateFormat("d_MMM_yy");
						    //Convert back into a string
					        VisitDate=dt4.format(date);
					        if(VisitDate==null){
					        	System.out.println("The last date to be done5"+VisitDate);
					        	SimpleDateFormat dt5 = new SimpleDateFormat("d_MMMM_yy");
							    //Convert back into a string
						        VisitDate=dt5.format(date);
						        if(VisitDate==null){
						        	System.out.println("The last date to be done6"+VisitDate);
						        	SimpleDateFormat dt6 = new SimpleDateFormat("dd_MMM_yy");
								    //Convert back into a string
							        VisitDate=dt6.format(date);
							        if(VisitDate==null){
							        	System.out.println("The last date to be done7"+VisitDate);
							        	SimpleDateFormat dt7 = new SimpleDateFormat("dd_MMM_yyyy");
									    //Convert back into a string
								        VisitDate=dt7.format(date);
								        if(VisitDate==null){
								        	System.out.println("The last date to be done8"+VisitDate);
								        	SimpleDateFormat dt8 = new SimpleDateFormat("dd_MMMM_yyyy");
										    //Convert back into a string
									        VisitDate=dt8.format(date);
									        if(VisitDate==null){
									        	SimpleDateFormat dt9 = new SimpleDateFormat("dd MMMM yyyy");
											    //Convert back into a string
										        VisitDate=dt9.format(date);
										        if(VisitDate==null){
										        	SimpleDateFormat dt10 = new SimpleDateFormat("MMMM_dd_yyyy");
												    //Convert back into a string
											        VisitDate=dt10.format(date);
										        }
									        }
								        }
							        }
						        }
					        }
				        }
				    }
				}*/
			} catch (Exception e) {

			}
		    }

		    if (VisitDate.matches("[A-Z|a-z].*")){
		    	SimpleDateFormat changeDate2 = new SimpleDateFormat("MMM_dd_yyyy", Locale.UK);
			    //Convert the string to a date

			    try {
					Date date = changeDate2.parse(VisitDate);
					 SimpleDateFormat dt2 = new SimpleDateFormat("dd_MM_yyyy");
					    //System.out.println("The last date to be done1"+VisitDate);
					    //Convert back into a string
							VisitDate=dt2.format(date);
					//System.out.println("MATCHES4"+date+ VisitDate);
				} catch (Exception e) {
					
					e.printStackTrace();
				}

		    }
		return VisitDate;

	}

/*	public static String VDFormatDOB(String VisitDate) throws ParseException{


	    //System.out.println("YEAR IN FRONT"+VisitDate);
		 Pattern match_YearAtFront = Pattern.compile("^\\d{4}");
		 Matcher matchermatch_YearAtFront = match_YearAtFront.matcher(VisitDate);

		 if (matchermatch_YearAtFront.find()){
			 VisitDate=VDFormatCleaner(VisitDate);
			 VisitDate=VisitDate.replaceAll("(\\d{4})(.)(.*)", "$3$2$1").trim().replaceAll("^:_", "");
		 }
		 else{
			 VisitDate=VDFormatCleaner(VisitDate);
		 }

	    //System.out.println("YEAR IN FROMT"+VisitDate);

		 try {
	    //This part reformats if words in the dates- this is usually in the Breath Test results
	    if (VisitDate.matches(".*[a-z].*")){
	    SimpleDateFormat changeDate = new SimpleDateFormat("dd_MMM_yy", Locale.UK);
	    //Convert the string to a date
	    Date date = changeDate.parse(VisitDate);
	    //Reformat the date the way I like it
	    SimpleDateFormat dt1 = new SimpleDateFormat("dd_MM_yy");
	    //Convert back into a string
        VisitDate=dt1.format(date);
        if(VisitDate==null){
        	SimpleDateFormat dt2 = new SimpleDateFormat("dd MM yy");
		    //Convert back into a string
	        VisitDate=dt2.format(date);
        }
	    }
	    //If there are only two digits at the end of the string then add 20
	    if (VisitDate.matches("(\\d*)_(\\d*)_(\\d{2})")){

	    	VisitDate=VisitDate.replaceAll("(\\d*)_(\\d*)_(\\d{2})","$1_$2_19$3");
	    	//System.out.println(VisitDate);
	    }
	    } catch (Exception e) {
			
			System.out.println("ERROR: I can't get the DOB to format: VDFormatDOB");
		}
	return VisitDate;

}*/



	public static String unAmerican(String VisitDate){
		VisitDate=VisitDate.replaceAll("(\\d+)_(\\d+)_(\\d{4})","$2_$1_$3");
		return VisitDate;
	}

}
