package Bravo;


import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Overview.Checkers;
import Overview.DBConnectorForAll;
import Overview.VisitDateFormatter;
import org.pmw.tinylog.Logger;


public class maneOld {
	static String FName;
	static String SName;
	static String DOB;
	static String HospNum;
	static String VisitDate;
	String s=null;

	public maneOld() {
	}
	public void Extract(String s,String child,String FileCreationDate) throws SQLException, ParseException {


		System.out.println("S frmo BRAVO OLD: "+s);

		//If there is a double table then get rid of the second column

		//Get rid of the double tables right at the beginning- shouldnt affect the other tables only the SI and SAP tables
		 /*Pattern DoubleTable_pattern = Pattern.compile("Date of Test.*?(\\d.*?)Ref",Pattern.DOTALL);
	       Matcher matcherDoubleTable_pattern = DoubleTable_pattern.matcher(s);
	       while(matcherDoubleTable_pattern.find()){
	    	   s=matcherHospNum_pattern.group(1);
	    	   s=

	       }*/

		//What's this replaceALl for?- it's chopping the last value off the tables and that ain't good
		//s=s.replaceAll("(\\d+\\.\\d+|n\\/a)\\s+(\\d+\\.\\d+|n\\/a)", "$1");
		//System.out.println("This is the original S  "+s);

		 Map<String,String> mapWholeBRAVO= new LinkedHashMap<String,String>();
	       Map<String,String> mapWholeBRAVOTotal= new LinkedHashMap<String,String>();
	       Map<String,String> mapWholeBRAVO3And4= new LinkedHashMap<String,String>();

	       //Basic demographics
	       Pattern TestDate_pattern = Pattern.compile("Date of Test.*?(\\d.*?)Ref",Pattern.DOTALL);
	       Matcher matcherTestDat_pattern = TestDate_pattern.matcher(s);
		   ArrayList<String> TestDate_arr = new ArrayList<String>();

			if (matcherTestDat_pattern.find()) {
				TestDate_arr.add(matcherTestDat_pattern.group(1));
				VisitDate=matcherTestDat_pattern.group(1).trim();
				//System.out.println("VisitDateConfirm1"+VisitDate);
				try {
					VisitDate=VisitDateFormatter.VDFormat(VisitDate);
				} catch (Exception e) {
					
					Logger.error(e+HospNum+"->From Bravo ManeOld"+child);
				}
				mapWholeBRAVO.put("VisitDate", VisitDate);
				//System.out.println("VisitDateConfirm"+VisitDate);
				}

		   Pattern DOB_pattern = Pattern.compile("Date of Birth(.*?)Patient",Pattern.DOTALL);
		   Matcher matcherDOB_pattern = DOB_pattern.matcher(s);
		   ArrayList<String> DOB_pattern_arr = new ArrayList<String>();

		   while (matcherDOB_pattern.find()) {
			   DOB_pattern_arr.add(matcherDOB_pattern.group(1));
			   DOB=matcherDOB_pattern.group(1);
			   mapWholeBRAVO.put("DOB", DOB);
			   //System.out.println("DOB"+DOB);
				 }
		   Pattern HospNum_pattern = Pattern.compile("Patient ID:(.*)(?=Patient)");
		   Matcher matcherHospNum_pattern = HospNum_pattern.matcher(s);
		   Pattern HospNum_pattern2 = Pattern.compile("Patient ID: (.*)?\\s");
		   Matcher matcherHospNum_pattern2 = HospNum_pattern2.matcher(s);

		   ArrayList<String> HospNum_pattern_arr = new ArrayList<String>();
		   if (matcherHospNum_pattern.find()) {
			   HospNum_pattern_arr.add(matcherHospNum_pattern.group(1));
			   			 }

		   else if(matcherHospNum_pattern2.find()) {
			   HospNum_pattern_arr.add(matcherHospNum_pattern2.group(1));
 			 }
		    HospNum=HospNum_pattern_arr.get(0);
		    HospNum=HospNum.replace("Patient ID:", "").trim();
		    //System.out.println("HospNum"+HospNum);

		   Pattern FName_pattern = Pattern.compile("Name:(.*),.*");
		   Matcher matcherHospFName_pattern = FName_pattern.matcher(s);
		   ArrayList<String> FName_pattern_arr = new ArrayList<String>();

		   while (matcherHospFName_pattern.find()) {
			   FName_pattern_arr.add(matcherHospFName_pattern.group(1));
			   FName=matcherHospFName_pattern.group(1);
			   mapWholeBRAVO.put("FName", FName);
			   //FName_pattern_arr=null;
			   SName=matcherHospFName_pattern.group(1).replace("'", "");

				 }

		   Pattern SName_pattern = Pattern.compile("Name:.*,(.*)");
		   Matcher matcherHospSName_pattern = SName_pattern.matcher(s);
		   ArrayList<String> SName_pattern_arr = new ArrayList<String>();

		   while (matcherHospSName_pattern.find()) {
			   SName_pattern_arr.add(matcherHospSName_pattern.group(1));
			   SName=matcherHospSName_pattern.group(1);
			   mapWholeBRAVO.put("SName", SName);
			   //System.out.println("SName"+SName);
				 }


	  //---------------------------------------------SAP Day 1---------------------------------------------------------

	       //Table 1


	      //System.out.println("END THIS");
	       Pattern SAPDay1_pattern = Pattern.compile("SAP Table *- *Day1\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
	       Matcher matcherSAPDay1_pattern = SAPDay1_pattern.matcher(s);
			ArrayList<String> SAPDay1_arr = new ArrayList<String>();

			try {
				 Pattern SAPDay1_Doublepattern = Pattern.compile("SAP Table *- *Day1\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
			      Matcher matcherSAPDay1_Doublepattern = SAPDay1_Doublepattern.matcher(s);
			      String match = null;
			      if(matcherSAPDay1_Doublepattern.find()){
			    	  match=matcherSAPDay1_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
			    	  System.out.println("THIS IS THE DOUBLECATCHER_SAPDay1"+match);
			    	  SAPDay1_arr.add(match);
			      }
			      else if (matcherSAPDay1_pattern.find()) {
				      //if there are double values or even two spaces
				    	  match=matcherSAPDay1_pattern.group(0);
				    	  SAPDay1_arr.add(match);
				      }


				System.out.println("SAPDay1_arr"+SAPDay1_arr);
				List<String> SAPDay1_table=new ArrayList<String>(java.util.Arrays.asList(SAPDay1_arr.get(0).split("\n")));

				//This creates an array of arrays

				ArrayList<List<String>> Arr_SAPDay1_table2d = new ArrayList<List<String>>();
				for (String str : SAPDay1_table){

					ArrayList<String> Arr_SAPDay1_table = new ArrayList<String>();
					if(!str.trim().equals("")) {
					str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
					str=str.trim();
					Arr_SAPDay1_table.add(str);
					Arr_SAPDay1_table2d.add(Arr_SAPDay1_table);
					//Arr_SAPDay1_table=null;
					}

				}

				//Tidy up

				 for (int i = 0; i < Arr_SAPDay1_table2d.size(); i++) {
					 List<String> stats = Arr_SAPDay1_table2d.get(i);
					 String allStats = stats.remove(0);
					 String allStats2=allStats.trim();
					 for (String ss:allStats2.split(":")){

						 if(!ss.trim().equals("")) {
					    	 Collections.addAll(stats, ss);
					    		 }
					}
				 }

				 //Tidy Up
				 Arr_SAPDay1_table2d.remove(0);
				 Arr_SAPDay1_table2d.remove(0);
				 Map<String,String> mapSAPDay1= new LinkedHashMap<String,String>();

				 for (int ff=0;ff<Arr_SAPDay1_table2d.size();ff++){
					 mapSAPDay1.put("SAP_Day1"+Arr_SAPDay1_table2d.get(ff).get(0),Arr_SAPDay1_table2d.get(ff).get(1));
						  }
				//System.out.println("HASHmapSAPDay1"+mapSAPDay1);
				mapWholeBRAVO.putAll(mapSAPDay1);
				//Arr_SAPDay1_table2d=null;
				//mapSAPDay1=null;
			} catch (Exception e) {
				//System.out.println("There's no SAP Day1 here");
			}



			  //----------------------------------------SI Day 1-----------------------------------------------------------------











			  //---------------------------------------------For the double value tables--------------------------------------------
			 /*Pattern SIDay1_Doublepattern = Pattern.compile("SI Table *- *Day1\\s+Total.*?(?:\n\\n).*(?:\\n\\n)");
		       Matcher matcherSIDay1_Doublepattern = SIDay1_Doublepattern.matcher(s);
				ArrayList<String> SIDay1_Doublearr = new ArrayList<String>();
//If Total and then another word after the first match:
				try {
					while (matcherSIDay1_Doublepattern.find()) {
System.out.println("HIHIHIH"+matcherSIDay1_Doublepattern.group(0));
SIDay1_Doublearr.add(matcherSIDay1_Doublepattern.group(0));
						 }*/









			  //----------------------------------------SI Day 1-----------------------------------------------------------------

		        Pattern SIDay1_pattern = Pattern.compile("SI Table *- *Day1\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
		        Matcher matcherSIDay1_pattern = SIDay1_pattern.matcher(s);
				ArrayList<String> SIDay1_arr = new ArrayList<String>();

				try {
					Pattern SIDay1_Doublepattern = Pattern.compile("SI Table *- *Day1\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
				    Matcher matcherSIDay1_Doublepattern = SIDay1_Doublepattern.matcher(s);
				    String match = null;
				    if(matcherSIDay1_Doublepattern.find()){
				    	  match=matcherSIDay1_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
				    	  System.out.println("THIS IS THE DOUBLECATCHER_SISay1"+match);
				    	  SIDay1_arr.add(match);
				      }
				    else if (matcherSIDay1_pattern.find()) {
				    	match=matcherSIDay1_pattern.group(0);
				    	SIDay1_arr.add(match);
				    }



					List<String> SIDay1_table=new ArrayList<String>(java.util.Arrays.asList(SIDay1_arr.get(0).split("\n")));

					//This creates an array of arrays

					ArrayList<List<String>> Arr_SIDay1_table2d = new ArrayList<List<String>>();
					for (String str : SIDay1_table){

						ArrayList<String> Arr_SIDay1_table = new ArrayList<String>();
						if(!str.trim().equals("")) {
						str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
						str=str.trim();
						Arr_SIDay1_table.add(str);
						Arr_SIDay1_table2d.add(Arr_SIDay1_table);
						//Arr_SIDay1_table=null;
						}
					}

					//Tidy up

					 for (int i = 0; i < Arr_SIDay1_table2d.size(); i++) {
						 List<String> stats = Arr_SIDay1_table2d.get(i);
						 String allStats = stats.remove(0);
						 String allStats2=allStats.trim();
						 for (String ss:allStats2.split(":")){

							 if(!ss.trim().equals("")) {
						    	 Collections.addAll(stats, ss);
						    		 }
						}
					 }
					 //Tidy Up
					 Arr_SIDay1_table2d.remove(0);
					 Arr_SIDay1_table2d.remove(0);
					 Map<String,String> mapSIDay1= new LinkedHashMap<String,String>();

					 for (int ff=0;ff<Arr_SIDay1_table2d.size();ff++){
						 mapSIDay1.put("SI_Day1"+Arr_SIDay1_table2d.get(ff).get(0),Arr_SIDay1_table2d.get(ff).get(1));
							  }
					//System.out.println("HASHmapSIDay1"+mapSIDay1);
					mapWholeBRAVO.putAll(mapSIDay1);

				} catch (Exception e) {
				}

				 //----------------------------------------SI Day 2-----------------------------------------------------------------
			      //TODO Also need to do a SI Day 2 extraction

				       Pattern SIDay2_pattern = Pattern.compile("SI Table *- *Day 2\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
				       Matcher matcherSIDay2_pattern = SIDay2_pattern.matcher(s);
						ArrayList<String> SIDay2_arr = new ArrayList<String>();

						try {
							Pattern SIDay2_Doublepattern = Pattern.compile("SI Table *- *Day 2\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
						      Matcher matcherSIDay2_Doublepattern = SIDay2_Doublepattern.matcher(s);
						      String match=null;
						      if(matcherSIDay2_Doublepattern.find()){
						    	  match=matcherSIDay2_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
						    	  System.out.println("THIS IS THE DOUBLECATCHER_SIDay2"+match);
						    	  SIDay2_arr.add(match);
						      }
						      else if (matcherSIDay2_pattern.find()) {
									match=matcherSIDay2_pattern.group(0);
									SIDay2_arr.add(match);

						      }



							List<String> SIDay2_table=new ArrayList<String>(java.util.Arrays.asList(SIDay2_arr.get(0).split("\n")));

							//This creates an array of arrays

							ArrayList<List<String>> Arr_SIDay2_table2d = new ArrayList<List<String>>();
							for (String str : SIDay2_table){

								ArrayList<String> Arr_SIDay2_table = new ArrayList<String>();
								if(!str.trim().equals("")) {
								str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
								str=str.trim();
								Arr_SIDay2_table.add(str);
								Arr_SIDay2_table2d.add(Arr_SIDay2_table);
								}
							}

							//Tidy up

							 for (int i = 0; i < Arr_SIDay2_table2d.size(); i++) {
								 List<String> stats = Arr_SIDay2_table2d.get(i);
								 String allStats = stats.remove(0);
								 String allStats2=allStats.trim();
								 for (String ss:allStats2.split(":")){

									 if(!ss.trim().equals("")) {
								    	 Collections.addAll(stats, ss);
								    		 }
								}
							 }
							 //Tidy Up
							 Arr_SIDay2_table2d.remove(0);
							 Arr_SIDay2_table2d.remove(0);
							 Map<String,String> mapSIDay2= new LinkedHashMap<String,String>();

							 for (int ff=0;ff<Arr_SIDay2_table2d.size();ff++){
								 mapSIDay2.put("SI_Day2"+Arr_SIDay2_table2d.get(ff).get(0),Arr_SIDay2_table2d.get(ff).get(1));
									  }
							//System.out.println("HASHmapSIDay2"+mapSIDay2);
							mapWholeBRAVO.putAll(mapSIDay2);
						} catch (Exception e) {
							//System.out.println("There's no SI Day 2 here");


						}

						 //---------------------------------------------SI Day 3---------------------------------------------------------
						//TODO Also need to do a SI Day 2 extraction

					       Pattern SIDay3_pattern = Pattern.compile("SI Table *- *Day 3\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
					       Matcher matcherSIDay3_pattern = SIDay3_pattern.matcher(s);
							ArrayList<String> SIDay3_arr = new ArrayList<String>();

							try {
								Pattern SIDay3_Doublepattern = Pattern.compile("SI Table *- Day 3\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
							      Matcher matcherSIDay3_Doublepattern = SIDay3_Doublepattern.matcher(s);
							      String match=null;
							      if(matcherSIDay3_Doublepattern.find()){
							    	  match=matcherSIDay3_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
							    	  System.out.println("THIS IS THE DOUBLECATCHER_SIDay3"+match);
							    	  SIDay3_arr.add(match);
							      }
							      else if (matcherSIDay3_pattern.find()) {

										match=matcherSIDay3_pattern.group(0);
										SIDay3_arr.add(match);
							      }



								List<String> SIDay3_table=new ArrayList<String>(java.util.Arrays.asList(SIDay3_arr.get(0).split("\n")));

								//This creates an array of arrays

								ArrayList<List<String>> Arr_SIDay3_table2d = new ArrayList<List<String>>();
								for (String str : SIDay3_table){

									ArrayList<String> Arr_SIDay3_table = new ArrayList<String>();
									if(!str.trim().equals("")) {
									str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
									str=str.trim();
									Arr_SIDay3_table.add(str);
									Arr_SIDay3_table2d.add(Arr_SIDay3_table);
									}
								}

								//Tidy up

								 for (int i = 0; i < Arr_SIDay3_table2d.size(); i++) {
									 List<String> stats = Arr_SIDay3_table2d.get(i);
									 String allStats = stats.remove(0);
									 String allStats2=allStats.trim();
									 for (String ss:allStats2.split(":")){

										 if(!ss.trim().equals("")) {
									    	 Collections.addAll(stats, ss);
									    		 }
									}
								 }
								 //Tidy Up
								 Arr_SIDay3_table2d.remove(0);
								 Arr_SIDay3_table2d.remove(0);
								 Map<String,String> mapSIDay3= new LinkedHashMap<String,String>();

								 for (int ff=0;ff<Arr_SIDay3_table2d.size();ff++){
									 mapSIDay3.put("SI_Day3"+Arr_SIDay3_table2d.get(ff).get(0),Arr_SIDay3_table2d.get(ff).get(1));
										  }
								//System.out.println("HASHmapSIDay3"+mapSIDay3);
								mapWholeBRAVO3And4.putAll(mapSIDay3);
							} catch (Exception e) {
								//System.out.println("There's no SI Day 2 here");
							}
						 //---------------------------------------------SI Day 4---------------------------------------------------------

							//TODO Also need to do a SI Day 2 extraction

						       Pattern SIDay4_pattern = Pattern.compile("SI Table *- *Day 4\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
						       Matcher matcherSIDay4_pattern = SIDay4_pattern.matcher(s);
								ArrayList<String> SIDay4_arr = new ArrayList<String>();

								try {
									Pattern SIDay4_Doublepattern = Pattern.compile("SI Table *- Day 4\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
								      Matcher matcherSIDay4_Doublepattern = SIDay4_Doublepattern.matcher(s);
								      String match=null;
								      if(matcherSIDay4_Doublepattern.find()){
								    	  match=matcherSIDay4_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
								    	  System.out.println("THIS IS THE DOUBLECATCHER_SIDay4"+match);
								    	  SIDay4_arr.add(match);
								      }
								      else if (matcherSIDay4_pattern.find()) {
											match=matcherSIDay4_pattern.group(0);
											SIDay4_arr.add(match);
										}



									List<String> SIDay4_table=new ArrayList<String>(java.util.Arrays.asList(SIDay4_arr.get(0).split("\n")));

									//This creates an array of arrays

									ArrayList<List<String>> Arr_SIDay4_table2d = new ArrayList<List<String>>();
									for (String str : SIDay4_table){

										ArrayList<String> Arr_SIDay4_table = new ArrayList<String>();
										if(!str.trim().equals("")) {
										str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
										str=str.trim();
										Arr_SIDay4_table.add(str);
										Arr_SIDay4_table2d.add(Arr_SIDay4_table);
										}
									}

									//Tidy up

									 for (int i = 0; i < Arr_SIDay4_table2d.size(); i++) {
										 List<String> stats = Arr_SIDay4_table2d.get(i);
										 String allStats = stats.remove(0);
										 String allStats2=allStats.trim();
										 for (String ss:allStats2.split(":")){

											 if(!ss.trim().equals("")) {
										    	 Collections.addAll(stats, ss);
										    		 }
										}
									 }
									 //Tidy Up
									 Arr_SIDay4_table2d.remove(0);
									 Arr_SIDay4_table2d.remove(0);
									 Map<String,String> mapSIDay4= new LinkedHashMap<String,String>();

									 for (int ff=0;ff<Arr_SIDay4_table2d.size();ff++){
										 mapSIDay4.put("SI_Day4"+Arr_SIDay4_table2d.get(ff).get(0),Arr_SIDay4_table2d.get(ff).get(1));
											  }
									//System.out.println("HASHmapSIDay4"+mapSIDay4);
									mapWholeBRAVO3And4.putAll(mapSIDay4);
								} catch (Exception e) {
									//System.out.println("There's no SI Day 2 here");
								}

				  //---------------------------------------------SAP Day 2---------------------------------------------------------




				//Table 1
			       Pattern SAPDay2_pattern = Pattern.compile("SAP Table *- *Day 2\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
			       Matcher matcherSAPDay2_pattern = SAPDay2_pattern.matcher(s);
					ArrayList<String> SAPDay2_arr = new ArrayList<String>();

					try {
						Pattern SAPDay2_Doublepattern = Pattern.compile("SAP Table *- *Day 2\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
					      Matcher matcherSAPDay2_Doublepattern = SAPDay2_Doublepattern.matcher(s);
					      String match=null;
					      if(matcherSAPDay2_Doublepattern.find()){
					    	  match=matcherSAPDay2_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
					    	  System.out.println("THIS IS THE DOUBLECATCHER_SAPDay2"+match);
					    	  SAPDay2_arr.add(match);
					      }
					      else if (matcherSAPDay2_pattern.find()) {
								match=matcherSAPDay2_pattern.group(0);
								 SAPDay2_arr.add(match);
							 }

						List<String> SAPDay2_table=new ArrayList<String>(java.util.Arrays.asList(SAPDay2_arr.get(0).split("\n")));

						//This creates an array of arrays

						ArrayList<List<String>> Arr_SAPDay2_table2d = new ArrayList<List<String>>();
						for (String str : SAPDay2_table){

							ArrayList<String> Arr_SAPDay2_table = new ArrayList<String>();
							if(!str.trim().equals("")) {
								str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
							str=str.trim();
							Arr_SAPDay2_table.add(str);
							Arr_SAPDay2_table2d.add(Arr_SAPDay2_table);
							}
						}
						//Tidy up

						 for (int i = 0; i < Arr_SAPDay2_table2d.size(); i++) {
							 List<String> stats = Arr_SAPDay2_table2d.get(i);
							 String allStats = stats.remove(0);
							 String allStats2=allStats.trim();
							 for (String ss:allStats2.split(":")){

								 if(!ss.trim().equals("")) {
							    	 Collections.addAll(stats, ss);
							    		 }
							}
						 }
						// //System.out.println("Arr_SAPDay2_table2d"+Arr_SAPDay2_table2d);
						 //Tidy Up
						 Arr_SAPDay2_table2d.remove(0);
						 Arr_SAPDay2_table2d.remove(0);
						 Map<String,String> mapSAPDay2= new LinkedHashMap<String,String>();

						 for (int ff=0;ff<Arr_SAPDay2_table2d.size();ff++){
							 mapSAPDay2.put("SAP_Day2"+Arr_SAPDay2_table2d.get(ff).get(0),Arr_SAPDay2_table2d.get(ff).get(1));
								  }
						////System.out.println("HASHmapSAPDay2"+mapSAPDay2);
						mapWholeBRAVO.putAll(mapSAPDay2);
					} catch (Exception e) {
						//System.out.println("There's no SAP Day2 here");
					}
					//--------------------------------------------SAP Day 3--------------------------------------------------------

					//Table 1
				       Pattern SAPDay3_pattern = Pattern.compile("SAP Table *- *Day 3\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
				       Matcher matcherSAPDay3_pattern = SAPDay3_pattern.matcher(s);
						ArrayList<String> SAPDay3_arr = new ArrayList<String>();

						try {
							Pattern SAPDay3_Doublepattern = Pattern.compile("SAP Table *- *Day 3\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
						      Matcher matcherSAPDay3_Doublepattern = SAPDay3_Doublepattern.matcher(s);
						      String match=null;
						      if(matcherSAPDay3_Doublepattern.find()){
						    	  match=matcherSAPDay3_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
						    	  System.out.println("THIS IS THE DOUBLECATCHER_SAPDay3"+match);
						    	  SAPDay3_arr.add(match);
						      }
						      else if (matcherSAPDay3_pattern.find()) {
									match=matcherSAPDay3_pattern.group(0);
									 SAPDay3_arr.add(match);
								 }

							List<String> SAPDay3_table=new ArrayList<String>(java.util.Arrays.asList(SAPDay3_arr.get(0).split("\n")));

							//This creates an array of arrays

							ArrayList<List<String>> Arr_SAPDay3_table2d = new ArrayList<List<String>>();
							for (String str : SAPDay3_table){

								ArrayList<String> Arr_SAPDay3_table = new ArrayList<String>();
								if(!str.trim().equals("")) {
									str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
								str=str.trim();
								Arr_SAPDay3_table.add(str);
								Arr_SAPDay3_table2d.add(Arr_SAPDay3_table);
								}
							}
							//Tidy up

							 for (int i = 0; i < Arr_SAPDay3_table2d.size(); i++) {
								 List<String> stats = Arr_SAPDay3_table2d.get(i);
								 String allStats = stats.remove(0);
								 String allStats2=allStats.trim();
								 for (String ss:allStats2.split(":")){

									 if(!ss.trim().equals("")) {
								    	 Collections.addAll(stats, ss);
								    		 }
								}
							 }
							// //System.out.println("Arr_SAPDay3_table2d"+Arr_SAPDay3_table2d);
							 //Tidy Up
							 Arr_SAPDay3_table2d.remove(0);
							 Arr_SAPDay3_table2d.remove(0);
							 Map<String,String> mapSAPDay3= new LinkedHashMap<String,String>();

							 for (int ff=0;ff<Arr_SAPDay3_table2d.size();ff++){
								 mapSAPDay3.put("SAP_Day3"+Arr_SAPDay3_table2d.get(ff).get(0),Arr_SAPDay3_table2d.get(ff).get(1));
									  }
							////System.out.println("HASHmapSAPDay3"+mapSAPDay3);
							 mapWholeBRAVO3And4.putAll(mapSAPDay3);
						} catch (Exception e) {
							//System.out.println("There's no SAP Day3 here");
						}

						//----------------------------------------SAP Day 4 ----------------------------------------------------------------------
						//Table 1
					       Pattern SAPDay4_pattern = Pattern.compile("SAP Table *- *Day 2\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
					       Matcher matcherSAPDay4_pattern = SAPDay4_pattern.matcher(s);
							ArrayList<String> SAPDay4_arr = new ArrayList<String>();

							try {
								Pattern SIDay4_Doublepattern = Pattern.compile("SI Table *- *Day 4\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
							      Matcher matcherSIDay4_Doublepattern = SIDay4_Doublepattern.matcher(s);
							      String match = null;
							      if(matcherSIDay4_Doublepattern.find()){
							    	  match=matcherSIDay4_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
							    	  System.out.println("THIS IS THE DOUBLECATCHER_SIDay4"+match);
							    	   SAPDay4_arr.add(match);
							      }
							      else if (matcherSAPDay4_pattern.find()) {
										match=matcherSAPDay4_pattern.group(0);
										   SAPDay4_arr.add(match);
									 }

								List<String> SAPDay4_table=new ArrayList<String>(java.util.Arrays.asList(SAPDay4_arr.get(0).split("\n")));

								//This creates an array of arrays

								ArrayList<List<String>> Arr_SAPDay4_table2d = new ArrayList<List<String>>();
								for (String str : SAPDay4_table){

									ArrayList<String> Arr_SAPDay4_table = new ArrayList<String>();
									if(!str.trim().equals("")) {
										str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
									str=str.trim();
									Arr_SAPDay4_table.add(str);
									Arr_SAPDay4_table2d.add(Arr_SAPDay4_table);
									}
								}
								//Tidy up

								 for (int i = 0; i < Arr_SAPDay4_table2d.size(); i++) {
									 List<String> stats = Arr_SAPDay4_table2d.get(i);
									 String allStats = stats.remove(0);
									 String allStats2=allStats.trim();
									 for (String ss:allStats2.split(":")){

										 if(!ss.trim().equals("")) {
									    	 Collections.addAll(stats, ss);
									    		 }
									}
								 }
								// //System.out.println("Arr_SAPDay4_table2d"+Arr_SAPDay4_table2d);
								 //Tidy Up
								 Arr_SAPDay4_table2d.remove(0);
								 Arr_SAPDay4_table2d.remove(0);
								 Map<String,String> mapSAPDay4= new LinkedHashMap<String,String>();

								 for (int ff=0;ff<Arr_SAPDay4_table2d.size();ff++){
									 mapSAPDay4.put("SAP_Day4"+Arr_SAPDay4_table2d.get(ff).get(0),Arr_SAPDay4_table2d.get(ff).get(1));
										  }
								////System.out.println("HASHmapSAPDay4"+mapSAPDay4);
								 mapWholeBRAVO3And4.putAll(mapSAPDay4);
							} catch (Exception e) {
								//System.out.println("There's no SAP Day4 here");
							}
					  //---------------------------------------------SAP Total---------------------------------------------------------

					//Table 1
				       Pattern SAPTotal_pattern = Pattern.compile("SAP Table *- *Total\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
				       Matcher matcherSAPTotal_pattern = SAPTotal_pattern.matcher(s);
						ArrayList<String> SAPTotal_arr = new ArrayList<String>();

						try {
							Pattern SAPTotal_Doublepattern = Pattern.compile("SAP Table *- *Total\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
						      Matcher matcherSAPTotal_Doublepattern = SAPTotal_Doublepattern.matcher(s);
						      String match = null;
						      if(matcherSAPTotal_Doublepattern.find()){
						    	  match=matcherSAPTotal_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
						    	  System.out.println("THIS IS THE DOUBLECATCHER_SAPTotal"+match);
						    	  SAPTotal_arr.add(match);
						      }
						      else if (matcherSAPTotal_pattern.find()) {

							    	  match=matcherSAPTotal_pattern.group(0);
							    	  SAPTotal_arr.add(match);
							      }



							List<String> SAPTotal_table=new ArrayList<String>(java.util.Arrays.asList(SAPTotal_arr.get(0).split("\n")));

							//This creates an array of arrays

							ArrayList<List<String>> Arr_SAPTotal_table2d = new ArrayList<List<String>>();
							for (String str : SAPTotal_table){

								ArrayList<String> Arr_SAPTotal_table = new ArrayList<String>();
								if(!str.trim().equals("")) {
									str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", "_");
								str=str.trim();
								Arr_SAPTotal_table.add(str);
								Arr_SAPTotal_table2d.add(Arr_SAPTotal_table);
								}

							}


							//Tidy up

							 for (int i = 0; i < Arr_SAPTotal_table2d.size(); i++) {
								 List<String> stats = Arr_SAPTotal_table2d.get(i);
								 String allStats = stats.remove(0);
								 String allStats2=allStats.trim();
								 for (String ss:allStats2.split("_")){

									 if(!ss.trim().equals("")) {
								    	 Collections.addAll(stats, ss);
								    		 }
								}
							 }
							 //System.out.println("Arr_SAPTotal_table2d"+Arr_SAPTotal_table2d);
							 //Tidy Up
							 Arr_SAPTotal_table2d.remove(0);
							 Arr_SAPTotal_table2d.remove(0);
							 Map<String,String> mapSAPTotal= new LinkedHashMap<String,String>();

							 for (int ff=0;ff<Arr_SAPTotal_table2d.size();ff++){
								 mapSAPTotal.put("SAP_Total"+Arr_SAPTotal_table2d.get(ff).get(0),Arr_SAPTotal_table2d.get(ff).get(1));
									  }
							////System.out.println("HASHmapSAPTotal"+mapSAPTotal);
							mapWholeBRAVOTotal.putAll(mapSAPTotal);
						} catch (Exception e) {
							//System.out.println("There's no SAP Total here");
						}


						  //---------------------------------------------SI Total---------------------------------------------------------


						//Table 1
					       Pattern SITotal_pattern = Pattern.compile("SI Table *- *Total\\s+Total.*\\n+(?:\\n[A-Za-z]+\\s+(?:\\d+(?:\\.\\d+)?|n\\/a))+");
					       Matcher matcherSITotal_pattern = SITotal_pattern.matcher(s);
							ArrayList<String> SITotal_arr = new ArrayList<String>();

							try {
								Pattern SITotal_Doublepattern = Pattern.compile("SI Table *- *Total\\s+Total.*?(?:\n\\n).*?(?:\\n\\n)",Pattern.DOTALL);
							      Matcher matcherSITotal_Doublepattern = SITotal_Doublepattern.matcher(s);
							      String match = null;
							      if(matcherSITotal_Doublepattern.find()){
							    	  match=matcherSITotal_Doublepattern.group(0).replaceAll("((?:n\\/a|\\d.\\d))\\s+(?:n\\/a|\\d.\\d)\\n", "$1\n");
							    	  System.out.println("THIS IS THE DOUBLECATCHER_SITotal"+match);
							    		SITotal_arr.add(match);
							      }
							      else if (matcherSITotal_pattern.find()) {
										match=matcherSITotal_pattern.group(0);
										SITotal_arr.add(match);
									}


								List<String> SITotal_table=new ArrayList<String>(java.util.Arrays.asList(SITotal_arr.get(0).split("\n")));

								//This creates an array of arrays

								ArrayList<List<String>> Arr_SITotal_table2d = new ArrayList<List<String>>();
								for (String str : SITotal_table){

									ArrayList<String> Arr_SITotal_table = new ArrayList<String>();
									if(!str.trim().equals("")) {
										str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", ":");
									str=str.trim();
									Arr_SITotal_table.add(str);
									Arr_SITotal_table2d.add(Arr_SITotal_table);
									}

								}


								//Tidy up

								 for (int i = 0; i < Arr_SITotal_table2d.size(); i++) {
									 List<String> stats = Arr_SITotal_table2d.get(i);
									 String allStats = stats.remove(0);
									 String allStats2=allStats.trim();
									 for (String ss:allStats2.split(":")){

										 if(!ss.trim().equals("")) {
									    	 Collections.addAll(stats, ss);
									    		 }
									}
								 }
								// //System.out.println("Arr_SITotal_table2d"+Arr_SITotal_table2d);
								 //Tidy Up
								 Arr_SITotal_table2d.remove(0);
								 Arr_SITotal_table2d.remove(0);
								 Map<String,String> mapSITotal= new LinkedHashMap<String,String>();

								 for (int ff=0;ff<Arr_SITotal_table2d.size();ff++){
									 mapSITotal.put("SI_Total"+Arr_SITotal_table2d.get(ff).get(0),Arr_SITotal_table2d.get(ff).get(1));
										  }
							//	//System.out.println("HASHmapSITotal"+mapSITotal);
								mapWholeBRAVOTotal.putAll(mapSITotal);
							} catch (Exception e) {

								//System.out.println("There's no SITotal here");
							}

							  //---------------------------------------------Reflux Day1---------------------------------------------------------
							Pattern ReflDay1_pattern = Pattern.compile("Reflux Table - Day1 .*?Fraction Time[^\n]*",Pattern.DOTALL);
						       Matcher matcherReflDay1_pattern = ReflDay1_pattern.matcher(s);

								ArrayList<String> ReflDay1_arr = new ArrayList<String>();

								try {
									while (matcherReflDay1_pattern.find()) {
										ReflDay1_arr.add(matcherReflDay1_pattern.group(0));
										//System.out.println("matcherReflDay1_pattern.group(0)"+matcherReflDay1_pattern.group(0));
										 }
									List<String> ReflDay1_table=new ArrayList<String>(java.util.Arrays.asList(ReflDay1_arr.get(0).split("\n")));

									//This creates an array of arrays

									ArrayList<List<String>> Arr_ReflDay1_table2d = new ArrayList<List<String>>();

									for (String str : ReflDay1_table){

										ArrayList<String> Arr_ReflDay1_table = new ArrayList<String>();
										if(!str.trim().equals("")) {
											str=str.trim().replaceAll("\\(d,hh: mm\\)","");
											str=str.trim().replaceAll("\\d(?=d)d,","");
											str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", "_");
										str=str.trim();
										Arr_ReflDay1_table.add(str);
										Arr_ReflDay1_table2d.add(Arr_ReflDay1_table);
										}

									}
									Arr_ReflDay1_table2d.get(1).add(0, "Metric");


									//Tidy up

									 for (int i = 0; i < Arr_ReflDay1_table2d.size(); i++) {
										 List<String> stats = Arr_ReflDay1_table2d.get(i);
										 String allStats = stats.remove(0);
										 String allStats2=allStats.trim();
										 for (String ss:allStats2.split("_")){

											 if(!ss.trim().equals("")) {
										    	 Collections.addAll(stats, ss);
										    		 }
										}
									 }

									 //Splits up the first column in the reflux table
									 for (int i = 0; i < 2; i++) {
										 List<String> stats = Arr_ReflDay1_table2d.get(i);
										 String allStats = stats.remove(0);
										 String allStats2=allStats.trim();
										 for (String ss:allStats2.split("\\s")){

											 if(!ss.trim().equals("")) {
										    	 Collections.addAll(stats, ss);
										    		 }
										}
									 }


									 ////System.out.println("Arr_ReflDay1_table2d"+Arr_ReflDay1_table2d);

									 //Tidy Up
									 Arr_ReflDay1_table2d.remove(0);
									 Arr_ReflDay1_table2d.get(0).get(0).split("\\w");
									 Map<String,String> mapReflDay1= new LinkedHashMap<String,String>();
									   for (int col=1;col<Arr_ReflDay1_table2d.size();col++){
							               for (int row=1;row<Arr_ReflDay1_table2d.get(col).size();row++){
					                           mapReflDay1.put("ReflDay1"+Arr_ReflDay1_table2d.get(col).get(0)+"_"+Arr_ReflDay1_table2d.get(0).get(row),Arr_ReflDay1_table2d.get(col).get(row));
							        //}
							                }
							                      }

									////System.out.println("HASHmapReflDay1"+mapReflDay1);
									mapWholeBRAVO.putAll(mapReflDay1);
								} catch (Exception e) {
									e.printStackTrace();
								}



							  //---------------------------------------------Reflux Day2---------------------------------------------------------

								Pattern ReflDay2_pattern = Pattern.compile("Reflux Table - Day 2.*?Fraction Time[^\n]*",Pattern.DOTALL);
							       Matcher matcherReflDay2_pattern = ReflDay2_pattern.matcher(s);
									ArrayList<String> ReflDay2_arr = new ArrayList<String>();

									try {
										while (matcherReflDay2_pattern.find()) {
											ReflDay2_arr.add(matcherReflDay2_pattern.group(0));
											 }
										List<String> ReflDay2_table=new ArrayList<String>(java.util.Arrays.asList(ReflDay2_arr.get(0).split("\n")));

										//This creates an array of arrays

										ArrayList<List<String>> Arr_ReflDay2_table2d = new ArrayList<List<String>>();

										for (String str : ReflDay2_table){

											ArrayList<String> Arr_ReflDay2_table = new ArrayList<String>();
											if(!str.trim().equals("")) {
												str=str.trim().replaceAll("\\(d,hh: mm\\)","");
												str=str.trim().replaceAll("\\d(?=d)d,","");
												str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", "_");
											str=str.trim();
											Arr_ReflDay2_table.add(str);
											Arr_ReflDay2_table2d.add(Arr_ReflDay2_table);
											}

										}
										Arr_ReflDay2_table2d.get(1).add(0, "Metric");


										//Tidy up

										 for (int i = 0; i < Arr_ReflDay2_table2d.size(); i++) {
											 List<String> stats = Arr_ReflDay2_table2d.get(i);
											 String allStats = stats.remove(0);
											 String allStats2=allStats.trim();
											 for (String ss:allStats2.split("_")){

												 if(!ss.trim().equals("")) {
											    	 Collections.addAll(stats, ss);
											    		 }
											}
										 }

										 //Splits up the first column in the reflux table
										 for (int i = 0; i < 2; i++) {
											 List<String> stats = Arr_ReflDay2_table2d.get(i);
											 String allStats = stats.remove(0);
											 String allStats2=allStats.trim();
											 for (String ss:allStats2.split("\\s")){

												 if(!ss.trim().equals("")) {
											    	 Collections.addAll(stats, ss);
											    		 }
											}
										 }


										 ////System.out.println("Arr_ReflDay2_table2d"+Arr_ReflDay2_table2d);

										 //Tidy Up
										 Arr_ReflDay2_table2d.remove(0);
										 Arr_ReflDay2_table2d.get(0).get(0).split("\\w");
										 Map<String,String> mapReflDay2= new LinkedHashMap<String,String>();
										 //Arr_ReflDay2_table2d.get(0).set(0, "Metric");
										// //System.out.println("Arr_ReflDay2_table2d"+Arr_ReflDay2_table2d.size());
										   for (int col=1;col<Arr_ReflDay2_table2d.size();col++){
								               for (int row=1;row<Arr_ReflDay2_table2d.get(col).size();row++){
								            	   ////System.out.println("HI"+Arr_ReflDay2_table2d.get(col).get(0)+"_"+Arr_ReflDay2_table2d.get(0).get(row)+","+Arr_ReflDay2_table2d.get(col).get(row));
								                           mapReflDay2.put("ReflDay2"+Arr_ReflDay2_table2d.get(col).get(0)+"_"+Arr_ReflDay2_table2d.get(0).get(row),Arr_ReflDay2_table2d.get(col).get(row));
								        //}
								                }
								                      }
										 ////System.out.println(Arr_ReflDay2_table2d.get(0).size());

										////System.out.println("HASHmapReflDay2"+mapReflDay2);
										mapWholeBRAVO.putAll(mapReflDay2);
									} catch (Exception e) {

										e.printStackTrace();
									}








							//----------------------------------------------Reflux Day3---------------------------------------------------------


									Pattern ReflDay3_pattern = Pattern.compile("Reflux Table - Day 2.*?Fraction Time[^\n]*",Pattern.DOTALL);
								       Matcher matcherReflDay3_pattern = ReflDay3_pattern.matcher(s);
										ArrayList<String> ReflDay3_arr = new ArrayList<String>();

										try {
											while (matcherReflDay3_pattern.find()) {
												ReflDay3_arr.add(matcherReflDay3_pattern.group(0));
												 }
											List<String> ReflDay3_table=new ArrayList<String>(java.util.Arrays.asList(ReflDay3_arr.get(0).split("\n")));

											//This creates an array of arrays

											ArrayList<List<String>> Arr_ReflDay3_table2d = new ArrayList<List<String>>();

											for (String str : ReflDay3_table){

												ArrayList<String> Arr_ReflDay3_table = new ArrayList<String>();
												if(!str.trim().equals("")) {
													str=str.trim().replaceAll("\\(d,hh: mm\\)","");
													str=str.trim().replaceAll("\\d(?=d)d,","");
													str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", "_");
												str=str.trim();
												Arr_ReflDay3_table.add(str);
												Arr_ReflDay3_table2d.add(Arr_ReflDay3_table);
												}

											}
											Arr_ReflDay3_table2d.get(1).add(0, "Metric");


											//Tidy up

											 for (int i = 0; i < Arr_ReflDay3_table2d.size(); i++) {
												 List<String> stats = Arr_ReflDay3_table2d.get(i);
												 String allStats = stats.remove(0);
												 String allStats2=allStats.trim();
												 for (String ss:allStats2.split("_")){

													 if(!ss.trim().equals("")) {
												    	 Collections.addAll(stats, ss);
												    		 }
												}
											 }

											 //Splits up the first column in the reflux table
											 for (int i = 0; i < 2; i++) {
												 List<String> stats = Arr_ReflDay3_table2d.get(i);
												 String allStats = stats.remove(0);
												 String allStats2=allStats.trim();
												 for (String ss:allStats2.split("\\s")){

													 if(!ss.trim().equals("")) {
												    	 Collections.addAll(stats, ss);
												    		 }
												}
											 }


											 ////System.out.println("Arr_ReflDay3_table2d"+Arr_ReflDay3_table2d);

											 //Tidy Up
											 Arr_ReflDay3_table2d.remove(0);
											 Arr_ReflDay3_table2d.get(0).get(0).split("\\w");
											 Map<String,String> mapReflDay3= new LinkedHashMap<String,String>();
											 //Arr_ReflDay3_table2d.get(0).set(0, "Metric");
											// //System.out.println("Arr_ReflDay3_table2d"+Arr_ReflDay3_table2d.size());
											   for (int col=1;col<Arr_ReflDay3_table2d.size();col++){
									               for (int row=1;row<Arr_ReflDay3_table2d.get(col).size();row++){
									            	   ////System.out.println("HI"+Arr_ReflDay3_table2d.get(col).get(0)+"_"+Arr_ReflDay3_table2d.get(0).get(row)+","+Arr_ReflDay3_table2d.get(col).get(row));
									                           mapReflDay3.put("ReflDay3"+Arr_ReflDay3_table2d.get(col).get(0)+"_"+Arr_ReflDay3_table2d.get(0).get(row),Arr_ReflDay3_table2d.get(col).get(row));
									        //}
									                }
									                      }
											 ////System.out.println(Arr_ReflDay3_table2d.get(0).size());

											////System.out.println("HASHmapReflDay3"+mapReflDay3);
											   mapWholeBRAVO3And4.putAll(mapReflDay3);
										} catch (Exception e) {

											e.printStackTrace();
										}


										//----------------------------------------------Reflux Day4---------------------------------------------------------




										Pattern ReflDay4_pattern = Pattern.compile("Reflux Table - Day 2.*?Fraction Time[^\n]*",Pattern.DOTALL);
									       Matcher matcherReflDay4_pattern = ReflDay4_pattern.matcher(s);
											ArrayList<String> ReflDay4_arr = new ArrayList<String>();

											try {
												while (matcherReflDay4_pattern.find()) {
													ReflDay4_arr.add(matcherReflDay4_pattern.group(0));
													 }
												List<String> ReflDay4_table=new ArrayList<String>(java.util.Arrays.asList(ReflDay4_arr.get(0).split("\n")));

												//This creates an array of arrays

												ArrayList<List<String>> Arr_ReflDay4_table2d = new ArrayList<List<String>>();

												for (String str : ReflDay4_table){

													ArrayList<String> Arr_ReflDay4_table = new ArrayList<String>();
													if(!str.trim().equals("")) {
														str=str.trim().replaceAll("\\(d,hh: mm\\)","");
														str=str.trim().replaceAll("\\d(?=d)d,","");
														str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", "_");
													str=str.trim();
													Arr_ReflDay4_table.add(str);
													Arr_ReflDay4_table2d.add(Arr_ReflDay4_table);
													}

												}
												Arr_ReflDay4_table2d.get(1).add(0, "Metric");


												//Tidy up

												 for (int i = 0; i < Arr_ReflDay4_table2d.size(); i++) {
													 List<String> stats = Arr_ReflDay4_table2d.get(i);
													 String allStats = stats.remove(0);
													 String allStats2=allStats.trim();
													 for (String ss:allStats2.split("_")){

														 if(!ss.trim().equals("")) {
													    	 Collections.addAll(stats, ss);
													    		 }
													}
												 }

												 //Splits up the first column in the reflux table
												 for (int i = 0; i < 2; i++) {
													 List<String> stats = Arr_ReflDay4_table2d.get(i);
													 String allStats = stats.remove(0);
													 String allStats2=allStats.trim();
													 for (String ss:allStats2.split("\\s")){

														 if(!ss.trim().equals("")) {
													    	 Collections.addAll(stats, ss);
													    		 }
													}
												 }


												 //Tidy Up
												 Arr_ReflDay4_table2d.remove(0);
												 Arr_ReflDay4_table2d.get(0).get(0).split("\\w");
												 Map<String,String> mapReflDay4= new LinkedHashMap<String,String>();
												   for (int col=1;col<Arr_ReflDay4_table2d.size();col++){
										               for (int row=1;row<Arr_ReflDay4_table2d.get(col).size();row++){
										                           mapReflDay4.put("ReflDay4"+Arr_ReflDay4_table2d.get(col).get(0)+"_"+Arr_ReflDay4_table2d.get(0).get(row),Arr_ReflDay4_table2d.get(col).get(row));
										                }
										                      }
												   mapWholeBRAVO3And4.putAll(mapReflDay4);
											} catch (Exception e) {

												e.printStackTrace();
											}

							  //---------------------------------------------Reflux Total--------------------------------------------------------
									Pattern ReflDayTotal_pattern = Pattern.compile("Reflux Table - Total.*?Fraction Time[^\n]*",Pattern.DOTALL);
								       Matcher matcherReflDayTotal_pattern = ReflDayTotal_pattern.matcher(s);
										ArrayList<String> ReflDayTotal_arr = new ArrayList<String>();

										try {
											while (matcherReflDayTotal_pattern.find()) {
												ReflDayTotal_arr.add(matcherReflDayTotal_pattern.group(0));
												 }
											List<String> ReflDayTotal_table=new ArrayList<String>(java.util.Arrays.asList(ReflDayTotal_arr.get(0).split("\n")));
											//This creates an array of arrays

											ArrayList<List<String>> Arr_ReflDayTotal_table2d = new ArrayList<List<String>>();

											for (String str : ReflDayTotal_table){

												ArrayList<String> Arr_ReflDayTotal_table = new ArrayList<String>();
												if(!str.trim().equals("")) {
													str=str.trim().replaceAll("\\(d,hh: mm\\)","");
													str=str.trim().replaceAll("\\d(?=d)d,","");
													str=str.trim().replaceAll("\\s(?=\\d)|\\s(?=n\\/a)", "_");
												str=str.trim();
												Arr_ReflDayTotal_table.add(str);
												Arr_ReflDayTotal_table2d.add(Arr_ReflDayTotal_table);
												}

											}
											Arr_ReflDayTotal_table2d.get(1).add(0, "Metric");


											//Tidy up

											 for (int i = 0; i < Arr_ReflDayTotal_table2d.size(); i++) {
												 List<String> stats = Arr_ReflDayTotal_table2d.get(i);
												 String allStats = stats.remove(0);
												 String allStats2=allStats.trim();
												 for (String ss:allStats2.split("_")){

													 if(!ss.trim().equals("")) {
												    	 Collections.addAll(stats, ss);
												    		 }
												}
											 }

											 //Splits up the first column in the reflux table
											 for (int i = 0; i < 2; i++) {
												 List<String> stats = Arr_ReflDayTotal_table2d.get(i);
												 String allStats = stats.remove(0);
												 String allStats2=allStats.trim();
												 for (String ss:allStats2.split("\\s")){

													 if(!ss.trim().equals("")) {
												    	 Collections.addAll(stats, ss);
												    		 }
												}
											 }

											 //Tidy Up
											 Arr_ReflDayTotal_table2d.remove(0);
											 Arr_ReflDayTotal_table2d.get(0).get(0).split("\\w");
											 Map<String,String> mapReflDayTotal= new LinkedHashMap<String,String>();
											   for (int col=1;col<Arr_ReflDayTotal_table2d.size();col++){
									               for (int row=1;row<Arr_ReflDayTotal_table2d.get(col).size();row++){
									                           mapReflDayTotal.put("ReflDayTotal"+Arr_ReflDayTotal_table2d.get(col).get(0)+"_"+Arr_ReflDayTotal_table2d.get(0).get(row),Arr_ReflDayTotal_table2d.get(col).get(row));
									                }
									                      }
											mapWholeBRAVOTotal.putAll(mapReflDayTotal);
										} catch (Exception e) {
											e.printStackTrace();
										}

										//Get the DeMeester scores

										Pattern DeMeesterDay1_pattern = Pattern.compile("DeMeester Score-Day\\s*1.*?Total score = (.*?),",Pattern.DOTALL);
										   Matcher matcherDeMeesterDay1_pattern = DeMeesterDay1_pattern.matcher(s);
										   String DeMeesterDay1;

										   while (matcherDeMeesterDay1_pattern.find()) {
											   DeMeesterDay1=matcherDeMeesterDay1_pattern.group(1);
											   mapWholeBRAVO.put("DeMeesterDay1", DeMeesterDay1);
												 }

										   Pattern DeMeesterDay2_pattern = Pattern.compile("DeMeester Score-Day 2.*?Total score = (.*?),",Pattern.DOTALL);
										   Matcher matcherDeMeesterDay2_pattern = DeMeesterDay2_pattern.matcher(s);
										   String DeMeesterDay2;

										   if (matcherDeMeesterDay2_pattern.find()) {
											   DeMeesterDay2=matcherDeMeesterDay2_pattern.group(1);
											   mapWholeBRAVO.put("DeMeesterDay2", DeMeesterDay2);
												 }
										   else{
											   mapWholeBRAVO.put("DeMeesterDay2", null);
										   }
							 //---------------------------------------------Connecting to the Database--------------------------------------------------------

										    String tab="BravoDay1And2";
										    String tabMore="BravoDay3And4";
										    DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
											Statement st;

												st = ConnectMeUp.Connector(HospNum,FName,SName,DOB);

											String first=ConnectMeUp.StringInsertKeyPreparer(st,mapWholeBRAVO,tab);
											String second=ConnectMeUp.StringInsertValuePreparer(st,mapWholeBRAVO,tab);
											//for (String date:Checkers.VisitDateChecker(st,tab,HospNum)){
											if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate)){
												//ConnectMeUp.Inserter(st,HospNum,first,second,tab);
											try {
												int sharedkey=ConnectMeUp.Inserter(st,HospNum,first,second,tab,child);
												    String table="BravoTotal";
												    String third=ConnectMeUp.StringInsertKeyPreparer(st,mapWholeBRAVOTotal,table);
													String fourth=ConnectMeUp.StringInsertValuePreparer(st,mapWholeBRAVOTotal,table);
													String FKFieldName="BravoId";
													ConnectMeUp.Inserter2(st,third,fourth,table,sharedkey,FKFieldName,child);
													String fifth=ConnectMeUp.StringInsertKeyPreparer(st,mapWholeBRAVO3And4,table);
													String sixth=ConnectMeUp.StringInsertValuePreparer(st,mapWholeBRAVO3And4,table);
													/*if(s.contains("Day 3")){
													ConnectMeUp.Inserter2(st,fifth,sixth,tabMore,sharedkey,FKFieldName,child);
													}*/
													mapWholeBRAVO= null;
												    mapWholeBRAVOTotal= null;
												    mapWholeBRAVO3And4= null;
													//st.close();
											} catch (Exception e) {
												
												Logger.error(e+HospNum+"->From BravoManeOld"+child);
											}
											}
							}
	}





