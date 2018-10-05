package Bravo;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
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


public class mane {
	String doc=null;
	String docSlim=null;
	String day=null;
	String docOverall=null;
	ArrayList<List<String>>  Total=null;
	ArrayList<List<String>>  Upright=null;
	ArrayList<List<String>>  Supine=null;
	ArrayList<List<String>>  Postprandial=null;
	ArrayList<List<String>>  Overall=null;
	ArrayList<List<String>>  Duration=null;
	String FName=null;
	String SName=null;
	String HospNum=null;
	String DOB=null;
	String VisitDate=null;
	String s=null;
	ArrayList<List<String>>  Symptoms=null;
	int sharedkey;
	String first=null;
	String second=null;
	String third="";
	String fourth=null;
	String fifth=null;
	String sixth=null;
	Statement st;
	String table=null;
	String tab=null;
	String tabMore=null;
	String child=null;

	ArrayList<String> Day_arr = new ArrayList<String>();
	ArrayList<List<String>> Day_table2d = new ArrayList<List<String>>();
	String [] seTab3Landmarks=null;
	Map<String,String> mapReflDayOneandTwo= new LinkedHashMap<String,String>();
	Map<String,String> mapReflDay= new LinkedHashMap<String,String>();

	public ArrayList<List<String>>  ExtractNew(String doc,String child,String FileCreationDate) throws SQLException, IOException {
		mapReflDayOneandTwo.put("FileCreationDate", FileCreationDate);
		try {
			//System.out.println("doc"+doc);
			VisitDate=VisitDate(doc);
			VisitDate=VisitDate.replace("\\", "").replace("/","");


			try {
				VisitDate=VisitDateFormatter.VDFormat(VisitDate);
			} catch (Exception e) {

				Logger.error(e+HospNum+"->From ExtractNew Mane");
			}
			VisitDate=VisitDateFormatter.unAmerican(VisitDate);
			mapReflDayOneandTwo.put("VisitDate", VisitDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			DOB=DOB(doc).trim();
		} catch (Exception e) {
			//
			//System.out.println("ERROR No DOB here in the Bravo Mane DOB call: "+HospNum);

		}
		try {
			FName=FName(doc).trim();
		} catch (Exception e2) {
			//System.out.println("ERROR No FNAME here");
			e2.printStackTrace();
		}
		try {
			SName=SName(doc).trim().replace("'", "");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			HospNum=HospNum(doc).trim();
		} catch (Exception e) {
			Logger.error(e+"ERROR No HospNum here"+child.toString());
			//System.out.println("ERROR No HospNum here"+child.toString());
			e.printStackTrace();
		}
		Pattern NumDays_pattern = Pattern.compile("Day \\d");
		Matcher matcherNumDays_pattern = NumDays_pattern.matcher(doc);

		 int i=0;
		  while (matcherNumDays_pattern.find()){
			  i= i+1;
			 //System.out.println("MY DAYS"+i);
			  try {
				if (i>0){
		day=Integer.toString(i);
		Pattern Tab3Landmarks_pattern = Pattern.compile("Day "+day+"(.*?)Probability that symptom and reflux",Pattern.DOTALL);
		Matcher matcherTab3Landmarks_pattern = Tab3Landmarks_pattern.matcher(doc);

		while (matcherTab3Landmarks_pattern.find()) {
			docSlim=matcherTab3Landmarks_pattern.group(1);
			seTab3Landmarks=matcherTab3Landmarks_pattern.group(1).split("\\n|\\r");
		}

		 ExtractTotal(docSlim,day);
		System.out.println("Returned Total"+Total);
		 for (int ff=0;ff<Total.size();ff++){
			 mapReflDayOneandTwo.put(Total.get(ff).get(0),Total.get(ff).get(1));
			 //Total=null;
				  }
		ExtractUpright(docSlim,day);
		for (int ff=0;ff<Upright.size();ff++){
			//Symptom
		 mapReflDayOneandTwo.put(Upright.get(ff).get(0),Upright.get(ff).get(1));
		 //Upright=null;
			  }

		ExtractSupine(docSlim,day);
		for (int ff=0;ff<Supine.size();ff++){
			//Symptom
		 mapReflDayOneandTwo.put(Supine.get(ff).get(0),Supine.get(ff).get(1));
			  }

		ExtractPostprandial(docSlim,day);
		for (int ff=0;ff<Postprandial.size();ff++){
			//Symptom
		 mapReflDayOneandTwo.put(Postprandial.get(ff).get(0),Postprandial.get(ff).get(1));
		 //Postprandial=null;
			  }
		//System.out.println("docSlim"+docSlim);
		ExtractSymptomsTable(docSlim,day);
		//System.out.println("SymptomsIteration"+Symptoms);
		  for (int ih = 1; ih < Symptoms.get(0).size(); ih++) {
		        for (int j = 1; j < Symptoms.size(); j ++) {
					System.out.println("SYMP "+Symptoms.get(j).get(0) +"  "+ Symptoms.get(0).get(ih)+"     "+ Symptoms.get(j).get(ih));

		       /*     try {
		            	System.out.println("Symptoms.get(j).get(ih + 1));"+Symptoms.get(j).get(ih + 1));
						mapReflDayOneandTwo.put(Symptoms.get(j).get(0) + Symptoms.get(0).get(ih),
						     Symptoms.get(j).get(ih + 1));
						System.out.println("SYMP "+Symptoms.get(j).get(0) +"  "+ Symptoms.get(0).get(ih)+"     "+ Symptoms.get(j).get(ih + 1));

		        } catch (Exception e) {

		        	Logger.error(e+"  "+child.toString());
				}*/
					try {
							mapReflDayOneandTwo.put(Symptoms.get(j).get(0) + Symptoms.get(0).get(ih),
								     Symptoms.get(j).get(ih));
							System.out.println("SYMP2 "+Symptoms.get(j).get(0) +"  "+ Symptoms.get(0).get(ih)+"     "+ Symptoms.get(j).get(ih));

					} catch (Exception e) {

						Logger.error(e+"  "+child.toString());
						System.out.println("Here is the ExtractNew problem: " + e);
					}
		            //Symptoms=null;
		        }
		   }
		  System.out.println("mapReflDayOneandTwo dddd"+mapReflDayOneandTwo);

		ExtractDurations(docSlim,day);
		for (int ff=0;ff<Duration.size();ff++){
			//Symptom
			mapReflDayOneandTwo.put(Duration.get(ff).get(0),Duration.get(ff).get(1));
			//Duration=null;
			  }



		Pattern Postprandial_pattern = Pattern.compile("(Acid Reflux.*?)Probability that symptom and reflux",Pattern.DOTALL);
		Matcher matcherPostprandial_pattern = Postprandial_pattern.matcher(doc);

		if (matcherPostprandial_pattern.find()) {
			docOverall=matcherPostprandial_pattern.group(1);
			seTab3Landmarks=matcherPostprandial_pattern.group(1).split("\\n|\\r");
		}

		 day="Total";
		ExtractTotal(docOverall,day);
		for (int ff=0;ff<Total.size();ff++){
			//Symptom
			mapReflDay.put(Total.get(ff).get(0),Total.get(ff).get(1));
			//Total=null;
			  }
		ExtractUpright(docOverall,day);
		for (int ff=0;ff<Upright.size();ff++){
			//Symptom
			mapReflDay.put(Upright.get(ff).get(0),Upright.get(ff).get(1));
			//Upright=null;
			  }
		ExtractSupine(docOverall,day);
		for (int ff=0;ff<Supine.size();ff++){
			//Symptom
			mapReflDay.put(Supine.get(ff).get(0),Supine.get(ff).get(1));
			//Supine=null;
			  }
		ExtractPostprandial(docOverall,day);
		for (int ff=0;ff<Postprandial.size();ff++){
			//Symptom
			mapReflDay.put(Postprandial.get(ff).get(0),Postprandial.get(ff).get(1));
			//Postprandial=null;
			  }

		 ExtractDurations(docOverall,day);
			for (int ff=0;ff<Duration.size();ff++){
				mapReflDay.put(Duration.get(ff).get(0),Duration.get(ff).get(1));
				  }


		ExtractSymptomsTable(docOverall,day);
		 for (int ih = 0; ih < Symptoms.get(0).size(); ih++) {
		        for (int j = 1; j < Symptoms.size(); j ++) {
		        	try {
						mapReflDay.put(Symptoms.get(j).get(0) + Symptoms.get(0).get(ih),
						     Symptoms.get(j).get(ih));
					} catch (Exception e) {

						e.printStackTrace();
					}
		        	try {
						mapReflDay.put(Symptoms.get(j).get(0) + Symptoms.get(0).get(ih),
						     Symptoms.get(j).get(ih));
					} catch (Exception e) {

						e.printStackTrace();
					}
		        }

		   }
		 //Symptoms=null;
				}
			  } catch (Exception e) {
					e.printStackTrace();
				}
		  }

			//Get the DeMeester scores

			Pattern DeMeesterDay1_pattern = Pattern.compile("Day 1.*?percentile(.*?\n)",Pattern.DOTALL);

			   Matcher matcherDeMeesterDay1_pattern = DeMeesterDay1_pattern.matcher(doc);
			   ArrayList<String> DeMeesterDay1_pattern_arr = new ArrayList<String>();
			   String DeMeesterDay1=null;


			   while (matcherDeMeesterDay1_pattern.find()) {
				   DeMeesterDay1_pattern_arr.add(matcherDeMeesterDay1_pattern.group(1));
				   DeMeesterDay1=matcherDeMeesterDay1_pattern.group(1);
				   //System.out.println("DeMeesterDay1::::"+DeMeesterDay1);
				   mapReflDayOneandTwo.put("DeMeesterDay1", DeMeesterDay1);
					 }

			   Pattern DeMeesterDay2_pattern = Pattern.compile("Day 2.*?percentile(.*?\n)",Pattern.DOTALL);
			   Matcher matcherDeMeesterDay2_pattern = DeMeesterDay2_pattern.matcher(doc);
			   ArrayList<String> DeMeesterDay2_pattern_arr = new ArrayList<String>();
			   String DeMeesterDay2=null;


			   while (matcherDeMeesterDay2_pattern.find()) {
				   DeMeesterDay2_pattern_arr.add(matcherDeMeesterDay2_pattern.group(1));
				   DeMeesterDay2=matcherDeMeesterDay2_pattern.group(1);
				   //System.out.println("DeMeesterDay2::::"+DeMeesterDay2);
				   mapReflDayOneandTwo.put("DeMeesterDay2", DeMeesterDay2);
					 }




		tab="BravoDay1And2";
		tabMore="BravoDay3And4";
		//System.out.println("mapReflDayOneandTwoJustBeforeDBConnection"+mapReflDayOneandTwo);
		//System.out.println("mapReflDayJustBeforeDBConnection"+mapReflDay);

		DBConnectorForAll ConnectMeUp = new DBConnectorForAll();

		try {
			//System.out.print("DOBConnect"+DOB);
			st=ConnectMeUp.Connector(HospNum,FName,SName,DOB);

		} catch (Exception e) {

			Logger.error(e+HospNum+"->From BravoMane"+child);
		}
		first=ConnectMeUp.StringInsertKeyPreparer(st,mapReflDayOneandTwo,tab);
		second=ConnectMeUp.StringInsertValuePreparer(st,mapReflDayOneandTwo,tab);



		if (!Checkers.VisitDateChecker(st,tab,HospNum).contains(VisitDate)){
			//System.out.println("ENTER THIS");
		try {
			sharedkey=ConnectMeUp.Inserter(st,HospNum,first,second,tab,child);
		} catch (Exception e) {

			Logger.error(e+HospNum+"->From BravoManeOld"+child);
		}
	    table="BravoTotal";

		third=ConnectMeUp.StringInsertKeyPreparer(st,mapReflDay,table);
		fourth=ConnectMeUp.StringInsertValuePreparer(st,mapReflDay,table);
		String FKFieldName="BravoId";
		//ConnectMeUp.Inserter(st,HospNum,first,second,tab);
		try {
			ConnectMeUp.Inserter2(st,third,fourth,table,sharedkey,FKFieldName,child);
		} catch (Exception e) {

			Logger.error(e+HospNum+"->From BravoManeOld"+child);
		}
		fifth=ConnectMeUp.StringInsertKeyPreparer(st,mapReflDayOneandTwo,tabMore);
		sixth=ConnectMeUp.StringInsertValuePreparer(st,mapReflDayOneandTwo,tabMore);
		/*try {
			if(doc.contains("Day 3")){
			ConnectMeUp.Inserter2(st,fifth,sixth,tabMore,sharedkey,FKFieldName,child);
			}
		} catch (Exception e) {

			Logger.error(e+HospNum+"-There is no Bravo day 3 and 4 here"+child);
		}*/
		}


		ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();


		return Arr2d;
	}






	//-----------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------
	//-------------------------------------------The subpatterns that get called from code above-----------------------------
	//-----------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------










	public ArrayList<List<String>> ExtractDurations(String docSlim,String day) {
		Pattern Total_pattern = Pattern.compile("Study Durations\\(HH:MM\\) Time(.*?(?=\\n\\n))", Pattern.DOTALL);
		Matcher matcherTotal_pattern = Total_pattern.matcher(docSlim);

		ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
		while (matcherTotal_pattern.find()) {
			String match2=matcherTotal_pattern.group(1).replaceAll("\\n\\n", "\n");
			seTab3Landmarks=match2.split("\\n|\\r");
		}
		for (String n:seTab3Landmarks){
			s=NameChangeMapping(n);
			if (s!=null){
				s="Refl"+"Day"+day+"DurationofPeriod"+s.trim();
				ArrayList<String> Arr=new ArrayList<String>();
				String [] nd=s.split("\\s+");
				for (int i=0;i<nd.length;i++){
					Arr.add(nd[i].trim());
				}
				Arr2d.add(Arr);
				//Arr=null;
				}
		}
		Arr2d.remove(0);
		//System.out.println("Rename this"+Duration);
		//Arr2d.remove(2);
		//seTab3Landmarks=null;
		Duration=Arr2d;

		//Arr2d=null;
		return Duration;

	}











	public ArrayList<List<String>> ExtractTotal(String docSlim,String day) {
		Pattern Total_pattern = Pattern.compile("Acid Reflux Analysis.*\n\\s*Total(.*?Longest reflux[^\n]*)",Pattern.DOTALL);
		Matcher matcherTotal_pattern = Total_pattern.matcher(docSlim);
		ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
		while (matcherTotal_pattern.find()) {
			//This gets rid of empty strings from the find so not included in the array after the split
			String match2=matcherTotal_pattern.group(1).replaceAll("\\n\\n", "\n");
			seTab3Landmarks=match2.split("\\n|\\r");
		}



		for (String n:seTab3Landmarks){
			//System.out.println("NNNNNN"+n);
			if(n.contains("Number of refluxes per hour")){
				n=n.replaceAll("Number of refluxes per hour.*","");
			}
			s=NameChangeMapping(n);
			s=s.replaceAll("^\\n", "");
			if (s!=null){
				s=s.replaceAll("_", "Total_");
				s="Refl"+"Day"+day+s.trim();
				//System.out.println("THIS IS S"+s);
				ArrayList<String> Arr=new ArrayList<String>();
				String [] nd=s.split("_");
				for (int i=0;i<nd.length;i++){
					Arr.add(nd[i].trim());
				}
				Arr2d.add(Arr);
				//seTab3Landmarks=null;
				//Arr=null;
				}
		}
		//System.out.println("THIS IS THE Arr2d "+Arr2d);
		Arr2d.remove(0);
		Arr2d.remove(2);
		Total=Arr2d;

		//System.out.println("THIS IS THE Method TOTAL "+Total);
		return Total;

	}











	public ArrayList<List<String>> ExtractUpright(String docSlim,String day) {
		Pattern Upright_pattern = Pattern.compile("Upright(.*?Longest reflux[^\n]*)",Pattern.DOTALL);
		Matcher matcherUpright_pattern = Upright_pattern.matcher(docSlim);
		ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();

		while (matcherUpright_pattern.find()) {
			String match2=matcherUpright_pattern.group(1).replaceAll("\\n\\n", "\n");
			seTab3Landmarks=match2.split("\\n|\\r");
		}
		for (String n:seTab3Landmarks){
			if(n.contains("Number of refluxes per hour")){
				n=n.replaceAll("Number of refluxes per hour.*","");
			}
			s=NameChangeMapping(n);

			if (s!=null){
				s=s.replaceAll("_", "Upright_");
				s="Refl"+"Day"+day+s.trim();
				ArrayList<String> Arr=new ArrayList<String>();
				String [] nd=s.split("_");
				for (int i=0;i<nd.length;i++){
					Arr.add(nd[i].trim());
				}
				Arr2d.add(Arr);
				//Arr=null;
				}
			}
		Arr2d.remove(0);
		Arr2d.remove(2);
		Upright=Arr2d;
		//Arr2d=null;
		//seTab3Landmarks=null;

		return Upright;
	}













	public ArrayList<List<String>> ExtractSupine(String docSlim,String day) {
		Pattern Supine_pattern = Pattern.compile("Supine(.*?Longest reflux[^\n]*)",Pattern.DOTALL);
		Matcher matcherSupine_pattern = Supine_pattern.matcher(docSlim);
		ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();

		while (matcherSupine_pattern.find()) {
			String match2=matcherSupine_pattern.group(1).replaceAll("\\n\\n", "\n");
			seTab3Landmarks=match2.split("\\n|\\r");

		}
		for (String n:seTab3Landmarks){
			if(n.contains("Number of refluxes per hour")){
				n=n.replaceAll("Number of refluxes per hour.*","");
			}

			s=NameChangeMapping(n);
			if (s!=null){

				s=s.replaceAll("_", "Supine_");
				s="Refl"+"Day"+day+s.trim();
				ArrayList<String> Arr=new ArrayList<String>();

				String [] nd=s.split("_");
				for (int i=0;i<nd.length;i++){
					Arr.add(nd[i].trim());
				}
				Arr2d.add(Arr);
				//Arr=null;
				}
		}
		Arr2d.remove(0);
		Arr2d.remove(2);
		Supine=Arr2d;
		//Arr2d=null;
		//seTab3Landmarks=null;
		//docSlim=null;
		return Supine;
	}









	public ArrayList<List<String>> ExtractPostprandial(String docSlim,String day) {
		//System.out.println("DOCSLIM "+docSlim);
		Pattern Postprandial_pattern = Pattern.compile("PostPr(.*?Longest reflux[^\n]*)",Pattern.DOTALL);
		Matcher matcherPostprandial_pattern = Postprandial_pattern.matcher(docSlim);
		seTab3Landmarks=null;
		ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
		while (matcherPostprandial_pattern.find()) {
			String match2=matcherPostprandial_pattern.group(1).replaceAll("\\n\\n", "\n");
			seTab3Landmarks=match2.split("\\n|\\r");
			//System.out.println("AM I JUST STEALING STUFF FROM SOMEONE ELSE? " );
		}
		for (String n:seTab3Landmarks){
			if(n.contains("Number of refluxes per hour")){
				//System.out.println("NNNNNNNN"+n);
				n=n.replaceAll("Number of refluxes per hour.*","");
			}
			s=NameChangeMapping(n);

			if (s!=null){

				s=s.replaceAll("_", "Meal_");
			s="ReflDay"+day+s.trim();

				ArrayList<String> Arr=new ArrayList<String>();
			String [] nd=s.split("_");
			for (int i=0;i<nd.length;i++){
				Arr.add(nd[i].trim());
			}
			Arr2d.add(Arr);
			//Arr=null;
			}

			}

		Arr2d.remove(0);
		Arr2d.remove(2);
		Postprandial=Arr2d;
		//Arr2d=null;
		//docSlim=null;
		//seTab3Landmarks=null;
		return Postprandial;
	}




	public ArrayList<List<String>> ExtractSymptomsTable(String docSlim,String day) {
		////System.out.println("docSlim"+docSlim);
		Pattern SymptomsTable_pattern = Pattern.compile("Symptom Analysis \\(pH\\).*?(\\b.*\\b?)(No\\. of symptoms analyzed.*?Symptom association[^\n]*)",Pattern.DOTALL);
		Matcher matcherSymptomsTable_pattern = SymptomsTable_pattern.matcher(docSlim);
		ArrayList<List<String>> Arr2d=new ArrayList<List<String>>();
		ArrayList<String> mashup = new ArrayList<String>();
		List<String> temp=new ArrayList<String>();
		String [] bilbo=null;


		//Horrible bit of code to get the symptoms in order
		if (matcherSymptomsTable_pattern.find()) {
			String g="Symptom "+matcherSymptomsTable_pattern.group(1);
			//String match2=matcherTotal_pattern.group(1).replaceAll("\\n\\n", "\n");

			System.out.println("LOOK AT G1"+g);
			g=g.replaceAll("(Symptom.*)\\n.*", "$1").replaceAll("\\(.*\\)", "").replaceAll("\\n", "").replaceAll("\\s{2,}", " ").replaceAll("[Bb]loated", "").replaceAll("[Tt]rapped", "").replaceAll("Symptom", "");
//
			//g=g.replaceAll("\\(.*\\)", "").replaceAll("\\n", "").replaceAll("\\s{2,}", " ").replaceAll("[Bb]loated", "").replaceAll("[Tt]rapped", "");
			System.out.println("LOOK AT G"+g);
			bilbo=g.split("\\s+");


		}
		else {


		}
			for (String d:bilbo){
				if(d!=""){
					temp.add(d);
					//System.out.println("LOOK AT bilbo"+d);
			}
			}

			Arr2d.add(temp);
		//Add the rest of the match to a simple ArrayList
			for(String n:matcherSymptomsTable_pattern.group(2).replaceAll("\\n\\n", "\n").split("\\n|\\r")){

				mashup.add(n.trim());
				}
			//System.out.println("mashup"+mashup);

		for (String n:mashup){
			if(n.contains("Symptom Analysis")){
			n=n.replaceAll("Symptom Analysis \\(pH\\)", "").trim();
			n=n.replaceAll("\\s+", "_");
			}
		    n=n.replace("*", "").trim();

			s=NameChangeMapping(n);
			//n=null;
			 //System.out.println("HERE IS THE N SHAPED"+s);
			if (s!=null){
				s=s.replaceAll("\\s(\\d)", "_$1").trim();
			    s="Day"+day+s.trim();
			  // System.out.println("HERE IS THE S SHAPED"+s);
				ArrayList<String> Arr=new ArrayList<String>();
			    String [] nd=s.split("_");


			for (int i=0;i<nd.length;i++){
				if(nd[i].contains("SAP")){
					nd[i]=nd[i].replaceAll("(.*)(SAP)", "$2$1");
				}
				if(nd[i].contains("SI")){
					nd[i]=nd[i].replaceAll("(.*)(SI)", "$2$1");
				}
				if(nd[i].contains("DayTotal")){
					nd[i]=nd[i].replaceAll("DayTotal", "Total");
				}
				if(nd[i].contains("NumberofRefluxes")){
					nd[i]=nd[i].replaceAll("(.*NumberofRefluxes)", "Refl$1");
				}
				Arr.add(nd[i].trim());
			}
			Arr2d.add(Arr);
			//System.out.println("Arr2dPreREMOVALS"+Arr2d);
			//nd=null;
			}

			}

		//System.out.println("Arr2dSymptoms"+Symptoms);
		Arr2d.remove(1);
		Arr2d.remove(1);
		Arr2d.remove(2);
		Arr2d.remove(2);
		Arr2d.remove(3);
		//System.out.println("Symptoms"+Symptoms);
		Symptoms=Arr2d;
		//Arr2d=null;
		//docSlim=null;
				return Symptoms;
	}

	////Field mapping to the other report (and therefore to the database as well.
	public String NameChangeMapping(String n) {
		if(n.contains("% Time spent in reflux")){
			n=n.replace("% Time spent in reflux","FractionTimepHLessThan4_");
			n=n.replace("% ", "");
			return n;
		}

		if(n.contains("Time spent in reflux")){
			n=n.replaceAll("Time spent in reflux", "TimepHLessThan4min_");
			n=n.replaceAll("\\(HH:MM\\)", "");
			//return n;
		}

		if(n.contains("Number of refluxes ")){
			n=n.replace("Number of refluxes", "NumberofRefluxes_");
			//return n;
		}
		if(n.contains("Number of long refluxes")){
			n=n.replace("Number of long refluxes","NumberofLongRefluxesMoreThan5min_");
			//return n;
		}
		if(n.contains("Longest reflux")){
			n=n.replaceAll("Longest reflux \\(HH:MM\\)","Durationoflongestrefluxmin_");
			//return n;
		}
		if(n.contains("No. of symptoms related to reflux")){
			n=n.replaceAll("No\\. of symptoms related to reflux","NumberofRefluxes");
			//return n;
		}
		if(n.contains("Symptom index for reflux")){
			n=n.replaceAll("Symptom index for reflux \\(SI\\)","SI");
			//return n;
		}
		if(n.contains("Symptom association prob.")){
			n=n.replaceAll("Symptom association prob\\..*\\(SAP\\)","SAP");
			//return n;
		}
		if(n.contains("No. of symptoms analyzed")){
			n=n.replaceAll("No. of symptoms analyzed.*","").trim();
			//return n;
		}
		if(n.contains("Channel")){
			n=n.replaceAll("Channel.*","").trim();
			//return n;
		}
		if(n.contains("No. of symptoms not related to Reflux")){
			n=n.replaceAll("No\\. of symptoms not related to Reflux.*","").trim();
			//return n;
		}
		if(n.contains("No. of reflux periods")){
			n=n.replaceAll("No\\. of reflux periods.*","").trim();
			//return n;
		}
		if(n.contains("Symptom sensitivity index")){
			n=n.replaceAll("Symptom sensitivity index.*","").trim();
			//return n;
		}
		return n;

	}




	public String HospNum(String doc) throws IOException, SQLException {
		try {
        HospNum=Overview.Searcher.HospNo_searcher(doc);
        if(HospNum==null||HospNum==""||HospNum.isEmpty()||HospNum.equals("0207188419")){
        	HospNum=Overview.Searcher.HospNo_searcher(child.toString());
			 }
			} catch (Exception e2) {

				Logger.error(e2+"Couldn't get the hospital number"+child);
			}
	return HospNum;
	}



	//For the FName Extraction
	public String FName(String doc) throws IOException, SQLException{
	 		try {
				FName=Overview.Searcher.FName_searcher(doc);
			} catch (Exception e) {

				Logger.error(e+HospNum+child);
			}
			return FName;

	}



	//For the SName Extraction
	public String SName(String doc) throws IOException, SQLException{

	 		try {
	 			SName=Overview.Searcher.SName_searcher(doc);
			} catch (Exception e) {

				Logger.error(e+HospNum+child);
			}
			return SName;
	}





	public String VisitDate(String doc) throws IOException, SQLException, ParseException{
		VisitDate=Overview.Searcher.VisitDate_searcher(doc);
	 		try {
				VisitDate=VisitDateFormatter.VDFormat(VisitDate);
			} catch (Exception e) {

				Logger.error(e+HospNum+"->From ImpedanceMane Mane"+child);
			}
			return VisitDate;
	}




	public String DOB(String doc) throws IOException, SQLException{
	 		try {
	 			DOB=Overview.Searcher.DOB_searcher(doc);
	 			System.out.println("DOBmane"+DOB);
			} catch (Exception e) {
				Logger.error(e+HospNum+child);
			}
		    return DOB;

	}

}
