package Impedance;


import java.sql.Statement;
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

public class mane {
String s=null;
String FName=null;
String SName=null;
String HospNum_Id =null;
String DOB=null;
String VisitDate=null;
int sharedkey;
String [] arrName;

	public mane(String s) {
	}

	 public void Impmane(String s,String child,String FileCreationDate){

	Pattern goPP = Pattern.compile("Postprandial Data.*(?=Symptom Correlation to Reflux)",Pattern.DOTALL);
	Matcher goPP_pattern = goPP.matcher(s);
	//This splits the original document into the Main stuff and the postprandial stuff so extraction should be more straightforward
	String PPStr="";
				while (goPP_pattern.find()) {
		   for (String df:goPP_pattern.group(0).split("\n")){
				PPStr=PPStr+df+"\n";
				s=s.replace(df, "");
		   }
		 }


				String SumStr="";
	Pattern RflxSumstudy_pattern = Pattern.compile("Reflux Study Summary.*?Impedance-pH Monitoring Details",Pattern.DOTALL);
	Matcher matcherRflxSumstudy_pattern = RflxSumstudy_pattern.matcher(s);
	while (matcherRflxSumstudy_pattern.find()) {
		   for (String dx:matcherRflxSumstudy_pattern.group(0).split("\n")){
			   //s = s.replace(dx.trim(),"");
			SumStr=SumStr+dx+"\n";

		   }
		 }
	s=s.replaceAll("Reflux Study Summary.*?Impedance-pH Monitoring Details", "");
	//System.out.println(SumStr);

 //Do pattern matching for individual tables:

Pattern PtData_pattern = Pattern.compile("Patient Data.*Visit ID:[^\n]*",Pattern.DOTALL);
Pattern Proc_pattern = Pattern.compile("Procedure Data.*Catheter Depth:[^\n]*",Pattern.DOTALL);
Pattern RflxAnalysis_pattern = Pattern.compile("Reflux Analysis Settings.*Analysis Duration Total:[^\n]*",Pattern.DOTALL);
Pattern ProxExtent_pattern = Pattern.compile("Proximal Extent.*?All Reflux[^\n]",Pattern.DOTALL);

//For the Summary extraction
Pattern SumAcidExpo_pattern = Pattern.compile("Acid (Exposure|Reflux).*Composite Score[^\n]*",Pattern.DOTALL|Pattern.MULTILINE);
Pattern SumSxCorrel_pattern = Pattern.compile("Symptom Correlation to Reflux.*?NOTE",Pattern.DOTALL|Pattern.MULTILINE);

Pattern AcidExpos_pattern = Pattern.compile("Impedance-pH Monitoring Details.*{2}^Percent Time[^\n]*",Pattern.DOTALL|Pattern.MULTILINE);
Pattern Stats_pattern = Pattern.compile("Statistics.*{2}Variance[^\n]*",Pattern.DOTALL);
Pattern AcidCompositeScore_pattern = Pattern.compile("Acid Reflux Composite Score Analysis.*Composite Score[^\n]*",Pattern.DOTALL);
Pattern BolusExpo_pattern = Pattern.compile("Bolus Exposure.*?NOTE*",Pattern.DOTALL);
Pattern ReflEpisodeAct_pattern = Pattern.compile("Reflux Episode Activity \\(Impedance\\)\\n.*?All Reflux[^\n]*",Pattern.DOTALL);

Pattern SxCorr_pattern = Pattern.compile("(?s)Symptom Correlation to Reflux((?:(?!Symptom Correlation to Reflux).)*?)Reflux Symptom Index",Pattern.DOTALL);
Pattern RSSI_pattern = Pattern.compile("Reflux Symptom Sensitivity Index \\(Impedance\\).*?Reflux Symptom Association Probability \\(Impedance\\)",Pattern.DOTALL);
Pattern MainRSI_pattern = Pattern.compile("Reflux Symptom Index .*?Reflux Symptom Sensitivity Index|Reflux Symptom Index .*?Reflux Symptom Association Probability",Pattern.DOTALL);
Pattern MainRSAP_pattern = Pattern.compile("Reflux Symptom Association Probability \\(Impedance\\).*?[^\\n]",Pattern.DOTALL);
Pattern Medication_pattern = Pattern.compile("Medications:(.*)?\\n");
/////////////Sort this out//////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////
//Defined according to the presence of other tables- check other reports for this being true


    Matcher matcherMedication_pattern = Medication_pattern.matcher(s);
    Matcher matcherPtData_pattern = PtData_pattern.matcher(s);
    Matcher matcherProc_pattern = Proc_pattern.matcher(s);
	Matcher matcherRflxAnalysis_pattern = RflxAnalysis_pattern.matcher(s);
	Matcher matcherSumSxCorrel_pattern = SumSxCorrel_pattern.matcher(SumStr);
	Matcher matcherSumAcidExpo_pattern = SumAcidExpo_pattern.matcher(SumStr);
	Matcher matcherMonitoringDet_pattern = AcidExpos_pattern.matcher(s);
	Matcher matcherAcidExp_pattern = Stats_pattern.matcher(s);
	Matcher matcherAcidCompositeScore_pattern = AcidCompositeScore_pattern.matcher(s);
	Matcher matcherBolusExpo_pattern = BolusExpo_pattern.matcher(s);
	Matcher matcherReflEpisodeAct_pattern = ReflEpisodeAct_pattern.matcher(s);
	Matcher matcherSxCorr_pattern = SxCorr_pattern.matcher(s);
	Matcher matcherRSSI_pattern = RSSI_pattern.matcher(s);
	Matcher matcherProxExtent_pattern= ProxExtent_pattern.matcher(s);
	Matcher matcherMainRSI_pattern= MainRSI_pattern.matcher(s);
	Matcher matcherMainRSAP_pattern= MainRSAP_pattern.matcher(s);




	ArrayList<String> PtData_arr = new ArrayList<String>();
	ArrayList<String> Proc_arr = new ArrayList<String>();
	ArrayList<String> RflxAnalysis_arr= new ArrayList<String>();
	ArrayList<String> SumSxCorrel_arr= new ArrayList<String>();
	ArrayList<String> SumAcidExpo_arr= new ArrayList<String>();
	ArrayList<String> MonitoringDet_arr= new ArrayList<String>();
	ArrayList<String> AcidExp_pattern_arr= new ArrayList<String>();
	ArrayList<String> AcidCompositeScore_arr= new ArrayList<String>();
	ArrayList<String> BolusExpo_arr= new ArrayList<String>();
	ArrayList<String> ReflEpisodeAct_arr= new ArrayList<String>();
	ArrayList<String> SxCorr_arr= new ArrayList<String>();
	ArrayList<String> RSSI_arr= new ArrayList<String>();
	ArrayList<String> ProxExtent_arr= new ArrayList<String>();
	ArrayList<String> MainRSI_arr= new ArrayList<String>();
	ArrayList<String> MainRSAP_arr= new ArrayList<String>();

	//Finally stick all the HashMaps together...
	Map<String,String> mapImpedanceAll= new LinkedHashMap<String,String>();
	Map<String,String> mapImpedanceSx= new LinkedHashMap<String,String>();


//------------------------------------------------------------------------------------------------------------------------------------


	//Need to take out the PostPrandialData Early on and have this as a separate extraction source


//------------------------------------------------------------------------------------------------------------------------------------
	try {

		Pattern PPBolusExpo_pattern = Pattern.compile("Bolus Exposure \\(Impedance\\).*?Longest Episode[^\n]*",Pattern.DOTALL);
		Matcher matcherPPBolusExpo_pattern = PPBolusExpo_pattern.matcher(PPStr);
		Pattern PPPostprandExpo_pattern = Pattern.compile("Postprandial Analysis Settings.*?Analysis Duration Total[^\n]*",Pattern.DOTALL);
		Matcher matcherPPPostprandExpo_pattern = PPPostprandExpo_pattern.matcher(PPStr);
		ArrayList<String> PPBolusExpo_arr= new ArrayList<String>();

//Now get each table and do what you did before but without worrying about catching other parts of the report.
		//Split up the tables that make up the PostPran table

		//----------------------------------------------------------------

		mapImpedanceAll.put("FileCreationDate", FileCreationDate);
		try {
			while (matcherPPBolusExpo_pattern.find()) {

				PPBolusExpo_arr.add(matcherPPBolusExpo_pattern.group(0));
				 }
			List<String> PPBolusExpo_table=new ArrayList<String>(java.util.Arrays.asList(PPBolusExpo_arr.get(0).split("\n")));

			//This creates an array of arrays
			ArrayList<List<String>> Arr_PPBolusExpo_table2d = new ArrayList<List<String>>();
			for (String str : PPBolusExpo_table){

				ArrayList<String> Arr_PPBolusExpo_table = new ArrayList<String>();
				if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", ":");
				str=str.replace("\t", ":");
				str=str.trim();
				Arr_PPBolusExpo_table.add(str);
				Arr_PPBolusExpo_table2d.add(Arr_PPBolusExpo_table);
				}
			}

			//Tidy up
			 for (int i = 0; i < Arr_PPBolusExpo_table2d.size(); i++) {
				 List<String> stats = Arr_PPBolusExpo_table2d.get(i);
				 String allStats = stats.remove(0);
				 String allStats2=allStats.trim();
				 for (String ss:allStats2.split(":")){

					 if(!ss.trim().equals("")) {
				    	 Collections.addAll(stats, ss);
				    		 }
				}
			 }
			 //Tidy Up
			 Arr_PPBolusExpo_table2d.remove(0);
			 Arr_PPBolusExpo_table2d.remove(0);
			 Map<String,String> mapSx_PPBolusExpo= new LinkedHashMap<String,String>();
			 for (int ff=0;ff<Arr_PPBolusExpo_table2d.size()-1;ff++){
				 mapSx_PPBolusExpo.put("MainPPBolusExpo"+Arr_PPBolusExpo_table2d.get(ff).get(0),Arr_PPBolusExpo_table2d.get(ff).get(1));
					  }
			//System.out.println("HASHmapmap_PPBolusExpos"+mapSx_PPBolusExpo);
			mapImpedanceAll.putAll(mapSx_PPBolusExpo);
		} catch (Exception e) {

			//System.out.println("There's no PPBolus here");
		}




		//----------------------------------------------------------------

		try {
		Pattern PPReflEpisodeAct_pattern = Pattern.compile("Reflux Episode Activity \\(Impedance\\).*?All Reflux[^\n]*",Pattern.DOTALL);
		Matcher matcherPPReflEpisodeActExpo_pattern = PPReflEpisodeAct_pattern.matcher(PPStr);
		ArrayList<String> PPReflEpisodeActExpo_arr= new ArrayList<String>();

		while (matcherPPReflEpisodeActExpo_pattern.find()) {
			PPReflEpisodeActExpo_arr.add(matcherPPReflEpisodeActExpo_pattern.group(0));
			 }
		List<String> PPReflEpisodeActExpo_table=new ArrayList<String>(java.util.Arrays.asList(PPReflEpisodeActExpo_arr.get(0).split("\n")));

		//This creates an array of arrays
		ArrayList<List<String>> Arr_PPReflEpisodeActExpo_table2d = new ArrayList<List<String>>();
		for (String str : PPReflEpisodeActExpo_table){

			ArrayList<String> Arr_PPReflEpisodeActExpo_table = new ArrayList<String>();
			//WORK ON THIS
			if(!str.trim().equals("")) {
			str=str.trim().replace("\t\t", ":");
			str=str.replace("\t", ":");
			str=str.trim();

			Arr_PPReflEpisodeActExpo_table.add(str);
			Arr_PPReflEpisodeActExpo_table2d.add(Arr_PPReflEpisodeActExpo_table);
			}
		}

		//Tidy up

		 for (int i = 0; i < Arr_PPReflEpisodeActExpo_table2d.size(); i++) {
			 List<String> stats = Arr_PPReflEpisodeActExpo_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split(":")){

				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		 }

		 Arr_PPReflEpisodeActExpo_table2d.remove(0);
		 Arr_PPReflEpisodeActExpo_table2d.remove(0);
		 Map<String,String> mapSx_PPReflEpisodeActExpo= new LinkedHashMap<String,String>();
		 for (int ff=0;ff<Arr_PPReflEpisodeActExpo_table2d.size();ff++){
				//Symptom
			 mapSx_PPReflEpisodeActExpo.put("PPReflEpisodeActExpo"+Arr_PPReflEpisodeActExpo_table2d.get(ff).get(0),Arr_PPReflEpisodeActExpo_table2d.get(ff).get(1));
				  }
		 //System.out.println("HASHmapSx_PPReflEpisodeActExpos"+mapSx_PPReflEpisodeActExpo);
					mapImpedanceAll.putAll(mapSx_PPReflEpisodeActExpo);
				} catch (Exception e) {

					//System.out.println("There's no Postprandial string so no PP reflux episode activity");
				}
		//----------------------------------------------------------------

		try {
			ArrayList<String> PPPostprandExpo_arr= new ArrayList<String>();


			while (matcherPPPostprandExpo_pattern.find()) {
				PPPostprandExpo_arr.add(matcherPPPostprandExpo_pattern.group(0));
				 }
			List<String> PPPostprandExpo_table=new ArrayList<String>(java.util.Arrays.asList(PPPostprandExpo_arr.get(0).split("\n")));

			//This creates an array of arrays

			ArrayList<List<String>> Arr_PPPostprandExpo_table2d = new ArrayList<List<String>>();
			for (String str : PPPostprandExpo_table){

				ArrayList<String> Arr_PPPostprandExpo_table = new ArrayList<String>();
				//WORK ON THIS
				if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", "");
				str=str.replace("\t", "_");
				str=str.trim();
				Arr_PPPostprandExpo_table.add(str);
				Arr_PPPostprandExpo_table2d.add(Arr_PPPostprandExpo_table);
				}
			}

			 for (int i = 0; i < Arr_PPPostprandExpo_table2d.size(); i++) {
				 List<String> stats = Arr_PPPostprandExpo_table2d.get(i);
				 String allStats = stats.remove(0);
				 String allStats2=allStats.trim();
				 for (String ss:allStats2.split("_")){

					 if(!ss.trim().equals("")) {
				    	 Collections.addAll(stats, ss);
				    		 }
				}
			 }

			//Tidy Up
			 Arr_PPPostprandExpo_table2d.remove(0);
			 Map<String,String> mapSx_PPPostprandExpo= new LinkedHashMap<String,String>();
			 for (int ff=0;ff<Arr_PPPostprandExpo_table2d.size();ff++){
					//Symptom
				 mapSx_PPPostprandExpo.put("MainPPPostprandExpo"+Arr_PPPostprandExpo_table2d.get(ff).get(0),Arr_PPPostprandExpo_table2d.get(ff).get(1));
					  }
				//System.out.println("HASHmapmapSx_PPPostprandExpos"+mapSx_PPPostprandExpo);
				mapImpedanceAll.putAll(mapSx_PPPostprandExpo);
		} catch (Exception e) {
			//System.out.println("There's no Postprandial string");

		}


	} catch (Exception e) {

		e.printStackTrace();
	}


	//------------------------------------------------------

	try {
		Pattern PPAcidExpo_pattern = Pattern.compile("Acid Exposure.*{2}\tPercent Time[^\n]*",Pattern.DOTALL);
		Matcher matcherPPAcidExpo_pattern = PPAcidExpo_pattern.matcher(PPStr);
		ArrayList<String> PPAcidExpo_arr= new ArrayList<String>();

		while (matcherPPAcidExpo_pattern.find()) {
			PPAcidExpo_arr.add(matcherPPAcidExpo_pattern.group(0));
			 }
		List<String> PPAcidExpo_table=new ArrayList<String>(java.util.Arrays.asList(PPAcidExpo_arr.get(0).split("\n")));

		//This creates an array of arrays
		PPAcidExpo_table=java.util.Arrays.asList(PPAcidExpo_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_PPAcidExpo_table2d = new ArrayList<List<String>>();
		for (String str : PPAcidExpo_table){
			ArrayList<String> Arr_PPAcidExpo_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
					str=str.trim().replace("\t\t", "");
					str=str.replace("\t", ":");
					str=str.trim();
			Arr_PPAcidExpo_table.add(str);

			Arr_PPAcidExpo_table2d.add(Arr_PPAcidExpo_table);
			}
		}
		//Tidy up
		 for (int i = 0; i < Arr_PPAcidExpo_table2d.size(); i++) {
			 List<String> stats = Arr_PPAcidExpo_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split(":"))
				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		 //Tidy Up
		 Map<String,String> mapSx_PPAcidExpo= new LinkedHashMap<String,String>();
		 Arr_PPAcidExpo_table2d.remove(0);
		 Arr_PPAcidExpo_table2d.remove(0);
		 Arr_PPAcidExpo_table2d.remove(0);

		 //System.out.println(Arr_PPAcidExpo_table2d);
		 for (int ff=0;ff<5;ff++){
			 Arr_PPAcidExpo_table2d.get(ff).set(0, "ClearanceChannel_"+Arr_PPAcidExpo_table2d.get(ff).get(0));

		  }
		  for (int ff=6;ff<Arr_PPAcidExpo_table2d.size();ff++){
			  Arr_PPAcidExpo_table2d.get(ff).set(0, "GastricChannel_"+Arr_PPAcidExpo_table2d.get(ff).get(0));
		  }


			 Arr_PPAcidExpo_table2d.remove(5);


		 for (int ff=0;ff<Arr_PPAcidExpo_table2d.size();ff++){
				//Symptom
			 mapSx_PPAcidExpo.put("MainPPAcidExpo"+Arr_PPAcidExpo_table2d.get(ff).get(0),Arr_PPAcidExpo_table2d.get(ff).get(1));

				  }
		//System.out.println("HASHmapmapSx_PPAcidExpos"+mapSx_PPAcidExpo);
		mapImpedanceAll.putAll(mapSx_PPAcidExpo);
	} catch (Exception e1) {

		//System.out.println("There's no Postprandial string so no PP Acid Exposure");

	}



//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Add regex result to the first array
	try {
		while (matcherPtData_pattern.find()) {
			PtData_arr.add(matcherPtData_pattern.group(0));
			//System.out.println("matcherPtData_pattern.group(0)"+matcherPtData_pattern.group(0));
			 }
		List<String> PtData_table=new ArrayList<String>(java.util.Arrays.asList(PtData_arr.get(0).split("\n")));

		//This creates an array of arrays
		PtData_table=java.util.Arrays.asList(PtData_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_PtData_table2d = new ArrayList<List<String>>();
		for (String str : PtData_table){
			ArrayList<String> Arr_PtData_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", "");
				str=str.replace("\t", "_");
				str=str.trim();

				Arr_PtData_table.add(str);
			Arr_PtData_table2d.add(Arr_PtData_table);

		}
		}
		////System.out.println("Arr_PtData_table2d"+Arr_PtData_table2d);

		//Tidy up the data
		//Just create the hash table straight from the unsortedOut array
		Map<String,String> mapPtData= new LinkedHashMap<String,String>();
		 for (int i = 0; i < Arr_PtData_table2d.size(); i++) {
			 List<String> stats = Arr_PtData_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split("_")){

				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
			}
		 Arr_PtData_table2d.remove(0);
		 //System.out.println("Arr_PtData_table2d"+Arr_PtData_table2d);
		 //This gets the patient's ID
		 HospNum_Id = Arr_PtData_table2d.get(1).get(1);
		 mapPtData.put("HospNum_Id", HospNum_Id);
		 arrName=Arr_PtData_table2d.get(0).get(1).trim().split(" ");
		 FName=arrName[0];
		 mapPtData.put("FName",FName);
		 SName=arrName[1].replace("'", "");
		 mapPtData.put("SName",SName);
		 DOB=Arr_PtData_table2d.get(3).get(1);
		 mapPtData.put("DOB",DOB);

		 if (matcherMedication_pattern.find()) {
				mapPtData.put("Meds", matcherMedication_pattern.group(1));
				System.out.println("THE Medications"+matcherMedication_pattern.group(1));
			}

		 Pattern VisitDate_pattern = Pattern.compile("Date of Admission:.*?\t(.*)");
		 Matcher matcherVisitDate_pattern = VisitDate_pattern.matcher(s);
		 while (matcherVisitDate_pattern.find()) {
			 VisitDate=matcherVisitDate_pattern.group(1);
			 VisitDate=VisitDate.replace("\\", "_").replace("/","_");
			 System.out.println("FOUND THE Impedance VisitDate "+VisitDate);
		 			 }
		 try {
			VisitDate=VisitDateFormatter.VDFormat(VisitDate);
		} catch (Exception e) {

			Logger.error(e+HospNum_Id+"->From ImpedanceMane Mane"+child);
		}
		 try {
			VisitDate=VisitDateFormatter.unAmerican(VisitDate);
		} catch (Exception e) {

			Logger.error(e+HospNum_Id+"->From ImpedanceMane unAmerican Mane"+child);
		}
		 mapPtData.put("VisitDate",VisitDate);
		 System.out.println(Arr_PtData_table2d);



		 try {
			mapPtData.put("MainPtData"+Arr_PtData_table2d.get(0).get(0), Arr_PtData_table2d.get(0).get(1));
		} catch (Exception e) {

			e.printStackTrace();
		}
		 try {
			mapPtData.put("MainPtData"+Arr_PtData_table2d.get(1).get(0), Arr_PtData_table2d.get(1).get(1));
		} catch (Exception e) {

			e.printStackTrace();
		}
		 try {
			mapPtData.put("MainPtData"+Arr_PtData_table2d.get(1).get(2), Arr_PtData_table2d.get(1).get(3));
		} catch (Exception e) {

			e.printStackTrace();
		}
		 try {
			mapPtData.put("MainPtData"+Arr_PtData_table2d.get(2).get(0), Arr_PtData_table2d.get(2).get(1));
		} catch (Exception e) {

			e.printStackTrace();
		}
		 try {
			mapPtData.put("MainPtData"+Arr_PtData_table2d.get(3).get(0), Arr_PtData_table2d.get(3).get(1));
		} catch (Exception e) {

			e.printStackTrace();
		}
		 try {
			mapPtData.put("MainPtData"+Arr_PtData_table2d.get(3).get(2), Arr_PtData_table2d.get(3).get(3));
		} catch (Exception e) {

			e.printStackTrace();
		}
		 try {
			mapPtData.put("MainPtData"+Arr_PtData_table2d.get(4).get(0), Arr_PtData_table2d.get(4).get(1));
		} catch (Exception e) {

			e.printStackTrace();
		}
			//System.out.println("HASHmapmapSx_PPPostprandExpos"+Arr_PtData_table2d);
			mapImpedanceAll.putAll(mapPtData);

		//System.out.println("HASHPtData"+mapPtData);
		mapImpedanceAll.putAll(mapPtData);
	} catch (Exception e) {

		e.printStackTrace();
	}
//------------------------------------------------------------------------------------------------------------------------------------
	try {
		while (matcherProc_pattern.find()) {
			Proc_arr.add(matcherProc_pattern.group(0));
			 }

		List<String> Proc_table=new ArrayList<String>(java.util.Arrays.asList(Proc_arr.get(0).split("\n")));

		//This creates an array of arrays

		ArrayList<List<String>> Arr_Proc_table2d = new ArrayList<List<String>>();
		for (String str : Proc_table){

			ArrayList<String> Arr_Proc_table = new ArrayList<String>();
			//WORK ON THIS
			if(!str.trim().equals("")) {
			str=str.trim().replace("\t\t", "");
			str=str.replace("\t", "_");
			str=str.trim();

			Arr_Proc_table.add(str);

			Arr_Proc_table2d.add(Arr_Proc_table);
			}
		}
		//System.out.println("Arr_Proc_table2dssssssssssssssssssssssssssss"+Arr_Proc_table2d);
		 for (int i = 0; i < Arr_Proc_table2d.size(); i++) {
			 List<String> stats = Arr_Proc_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split("_")){

				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		 }
		 System.out.println("Arr_Proc_table2dssssssssssssssssssssssssssss"+Arr_Proc_table2d);
		 Arr_Proc_table2d.remove(0);

		 Map<String,String> mapSx_PPPostprandExpo= new LinkedHashMap<String,String>();
		 for (int ff=0;ff<Arr_Proc_table2d.size();ff++){
				//Symptom
			 System.out.println("HASHmapMain_Proc"+mapSx_PPPostprandExpo);
			 try {
				 //Put here so that if array has no value (ie has captured a title for example) then the hash is filled- it will be excluded
				 //anyway when the database is connected to.
				mapSx_PPPostprandExpo.put("MainProc"+Arr_Proc_table2d.get(ff).get(0),Arr_Proc_table2d.get(ff).get(1));
			} catch (Exception e) {

				mapSx_PPPostprandExpo.put("MainProc"+Arr_Proc_table2d.get(ff).get(0),"NoExist");
			}
			 System.out.println("HASHmapMain_Proc"+mapSx_PPPostprandExpo);
				  }

			mapImpedanceAll.putAll(mapSx_PPPostprandExpo);
	} catch (Exception e) {


		e.printStackTrace();
	}

//------------------------------------------------------------------------------------------------------------------------------------

	try {
		while (matcherRflxAnalysis_pattern.find()) {
			RflxAnalysis_arr.add(matcherRflxAnalysis_pattern.group(0));
			 }
		List<String> RflxAnalysis_table=new ArrayList<String>(java.util.Arrays.asList(RflxAnalysis_arr.get(0).split("\n")));
		//This creates an array of arrays
		RflxAnalysis_table=java.util.Arrays.asList(RflxAnalysis_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_RflxAnalysis_table2d = new ArrayList<List<String>>();
		for (String str : RflxAnalysis_table){
			ArrayList<String> Arr_RflxAnalysis_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", "");
				str=str.replace("\t", "_");
				str=str.trim();

				Arr_RflxAnalysis_table.add(str);

				Arr_RflxAnalysis_table2d.add(Arr_RflxAnalysis_table);
				}
		}
		for (int i = 0; i < Arr_RflxAnalysis_table2d.size(); i++) {
			 List<String> stats = Arr_RflxAnalysis_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split("_")){

				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
			}

		//Tidy up
		Map<String,String> mapRflxAnalysis= new LinkedHashMap<String,String>();
		mapRflxAnalysis.put("MainReflxSettings"+Arr_RflxAnalysis_table2d.get(Arr_RflxAnalysis_table2d.size()-3).get(0), Arr_RflxAnalysis_table2d.get(Arr_RflxAnalysis_table2d.size()-3).get(1));
		mapRflxAnalysis.put("MainReflxSettings"+Arr_RflxAnalysis_table2d.get(Arr_RflxAnalysis_table2d.size()-2).get(0), Arr_RflxAnalysis_table2d.get(Arr_RflxAnalysis_table2d.size()-2).get(1));
		mapRflxAnalysis.put("MainReflxSettings"+Arr_RflxAnalysis_table2d.get(Arr_RflxAnalysis_table2d.size()-1).get(0), Arr_RflxAnalysis_table2d.get(Arr_RflxAnalysis_table2d.size()-1).get(1));
		//System.out.println("HASHRflxAnalysis"+mapRflxAnalysis);
		mapImpedanceAll.putAll(mapRflxAnalysis);
	} catch (Exception e) {

		e.printStackTrace();
	}
//------------------------------------------------------------------------------------------------------------------------------------
	try {
		while (matcherProxExtent_pattern.find()) {
			ProxExtent_arr.add(matcherProxExtent_pattern.group(0));
			 }
		List<String> ProxExtent_table=new ArrayList<String>(java.util.Arrays.asList(ProxExtent_arr.get(0).split("\n")));

		//This creates an array of arrays
		ProxExtent_table=java.util.Arrays.asList(ProxExtent_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_ProxExtent_table2d = new ArrayList<List<String>>();
		for (String str : ProxExtent_table){
			ArrayList<String> Arr_ProxExtent_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
			Arr_ProxExtent_table.add(str);
			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", ":");
				str=str.replace("\t", ":");
				str=str.trim();
				Arr_ProxExtent_table2d.add(Arr_ProxExtent_table);
			}


		}
		}

		Arr_ProxExtent_table2d.remove(0);
		Arr_ProxExtent_table2d.remove(Arr_ProxExtent_table2d.size()-1);

		//Tidy up
		 for (int i = 0; i < Arr_ProxExtent_table2d.size(); i++) {
			 List<String> stats = Arr_ProxExtent_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split("\\t"))
				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		 Map<String,String> mapProxExtent= new LinkedHashMap<String,String>();

			mapProxExtent.put(Arr_ProxExtent_table2d.get(0).get(0), Arr_ProxExtent_table2d.get(0).get(1));
			Arr_ProxExtent_table2d.remove(0);



			for (int ff=1;ff<Arr_ProxExtent_table2d.size();ff++){
				//Upright
			 mapProxExtent.put("MainProxExtent"+Arr_ProxExtent_table2d.get(0).get(0)+"_"+Arr_ProxExtent_table2d.get(ff).get(0),Arr_ProxExtent_table2d.get(ff).get(1));
				//Recumbent
			 mapProxExtent.put("MainProxExtent"+Arr_ProxExtent_table2d.get(0).get(1)+"_"+Arr_ProxExtent_table2d.get(ff).get(0),Arr_ProxExtent_table2d.get(ff).get(2));
				//Total
			 mapProxExtent.put("MainProxExtent"+Arr_ProxExtent_table2d.get(0).get(2)+"_"+Arr_ProxExtent_table2d.get(ff).get(0),Arr_ProxExtent_table2d.get(ff).get(3));
			  }

		 //System.out.println("HASH_ProxExtent_table2d"+mapProxExtent);
		 mapImpedanceAll.putAll(mapProxExtent);
	} catch (Exception e) {

		//System.out.println("There's no Proximal extent here");

	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------


//------------------------------------------------------------------------------------------------------------------------------------
	try {

		while (matcherSumSxCorrel_pattern.find()) {
			SumSxCorrel_arr.add(matcherSumSxCorrel_pattern.group(0));
			 }
		List<String> SumSxCorrel_table=new ArrayList<String>(java.util.Arrays.asList(SumSxCorrel_arr.get(0).split("\n")));


		ArrayList<List<String>> Arr_SumSxCorrel_table2d = new ArrayList<List<String>>();
		for (String str : SumSxCorrel_table){
			//This gets rid of all the extra newline gap things
			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", ":");
				str=str.replace("\t", ":");
				str=str.trim();
			ArrayList<String> Arr_SumSxCorrel_table = new ArrayList<String>();
			Arr_SumSxCorrel_table.add(str);

			Arr_SumSxCorrel_table2d.add(Arr_SumSxCorrel_table);
				}
		}

		//Tidy up
		Arr_SumSxCorrel_table2d.remove(Arr_SumSxCorrel_table2d.size()-1);
		Arr_SumSxCorrel_table2d.remove(Arr_SumSxCorrel_table2d.size()-1);
		Map<String,String> mapSumSx_Correl= new LinkedHashMap<String,String>();
		 for (int i = 0; i < Arr_SumSxCorrel_table2d.size(); i++) {
			 List<String> stats = Arr_SumSxCorrel_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split(":")){

				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		 }

		 //Tidy Up

		 Arr_SumSxCorrel_table2d.remove(0);
		 ////System.out.println("FINALArr_SxCorrel_table2d"+Arr_SumSxCorrel_table2d);


		for (int ff=1;ff<Arr_SumSxCorrel_table2d.get(0).size();ff++){
			mapSumSx_Correl.put("SumSx_Correl"+Arr_SumSxCorrel_table2d.get(0).get(0)+"_"+Arr_SumSxCorrel_table2d.get(ff).get(0),Arr_SumSxCorrel_table2d.get(ff).get(1));
			mapSumSx_Correl.put("SumSx_Correl"+Arr_SumSxCorrel_table2d.get(0).get(1)+"_"+Arr_SumSxCorrel_table2d.get(ff).get(0),Arr_SumSxCorrel_table2d.get(ff).get(2));
			mapSumSx_Correl.put("SumSx_Correl"+Arr_SumSxCorrel_table2d.get(0).get(2)+"_"+Arr_SumSxCorrel_table2d.get(ff).get(0),Arr_SumSxCorrel_table2d.get(ff).get(3));
			mapSumSx_Correl.put("SumSx_Correl"+Arr_SumSxCorrel_table2d.get(0).get(3)+"_"+Arr_SumSxCorrel_table2d.get(ff).get(0),Arr_SumSxCorrel_table2d.get(ff).get(4));
		  }

//System.out.println("HASHmapSumSx_Correl"+mapSumSx_Correl);
mapImpedanceAll.putAll(mapSumSx_Correl);

	} catch (Exception e) {

		//System.out.println("There's no SumCorrSx");

	}
//------------------------------------------------------------------------------------------------------------------------------------
try {
		while (matcherSumAcidExpo_pattern.find()) {
			SumAcidExpo_arr.add(matcherSumAcidExpo_pattern.group(0));
			 }
		List<String> SumAcidExpo_table=new ArrayList<String>(java.util.Arrays.asList(SumAcidExpo_arr.get(0).split("\n")));
		ArrayList<List<String>> Arr_SumAcidExpo_table2d = new ArrayList<List<String>>();
		for (String str : SumAcidExpo_table){
			//This gets rid of all the extra newline gap things
				if(!str.trim().equals("")) {
			ArrayList<String> Arr_SumAcidExpo_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", ":");
				str=str.replace("\t", ":");
				str=str.trim();
			Arr_SumAcidExpo_table.add(str);

			Arr_SumAcidExpo_table2d.add(Arr_SumAcidExpo_table);
				}
		}
		}

		//Tidy up
		Map<String,String> mapSx_Correl= new LinkedHashMap<String,String>();
		 for (int i = 0; i < Arr_SumAcidExpo_table2d.size(); i++) {
			 List<String> stats = Arr_SumAcidExpo_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split(":")){

				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		}

		 //Tidy Up
		 Arr_SumAcidExpo_table2d.get(0).remove(0);
		 Arr_SumAcidExpo_table2d.remove(1);


		for (int ff=1;ff<Arr_SumAcidExpo_table2d.get(0).size();ff++){
			mapSx_Correl.put("SumAcidExpo"+Arr_SumAcidExpo_table2d.get(0).get(0)+"_"+Arr_SumAcidExpo_table2d.get(ff).get(0),Arr_SumAcidExpo_table2d.get(ff).get(1));
			mapSx_Correl.put("SumAcidExpo"+Arr_SumAcidExpo_table2d.get(0).get(1)+"_"+Arr_SumAcidExpo_table2d.get(ff).get(0),Arr_SumAcidExpo_table2d.get(ff).get(2));
			mapSx_Correl.put("SumAcidExpo"+Arr_SumAcidExpo_table2d.get(0).get(2)+"_"+Arr_SumAcidExpo_table2d.get(ff).get(0),Arr_SumAcidExpo_table2d.get(ff).get(3));
		  }

//System.out.println("HASHSumAcidExpo"+mapSx_Correl);
mapImpedanceAll.putAll(mapSx_Correl);
	} catch (Exception e) {
		//System.out.println("There's no SumSxCorr");

	}



//------------------------------------------------------------------------------------------------------------------------------------





	try {
while (matcherReflEpisodeAct_pattern.find()) {
ReflEpisodeAct_arr.add(matcherReflEpisodeAct_pattern.group(0));
System.out.println("ReflEpisodeAct_arr.add(matcherReflEpisodeAct_pattern.group(0)"+matcherReflEpisodeAct_pattern.group(0));
 }
List<String> RflxEpisode_table=new ArrayList<String>(java.util.Arrays.asList(ReflEpisodeAct_arr.get(0).split("\n")));

//This creates an array of arrays
//RflxEpisode_table=java.util.Arrays.asList(ReflEpisodeAct_arr.get(0).split("\n"));
ArrayList<List<String>> Arr_RflxEpisode_table2d = new ArrayList<List<String>>();
for (String str : RflxEpisode_table){
	System.out.println("STR"+str);
if(!str.trim().equals("")) {
	str=str.trim().replace("\t\t", "");
	str=str.replace("\t", "_");
	str=str.trim();

ArrayList<String> Arr_RflxEpisode_table = new ArrayList<String>();
Arr_RflxEpisode_table.add(str);
Arr_RflxEpisode_table2d.add(Arr_RflxEpisode_table);
}
}
//Tidy up

Arr_RflxEpisode_table2d.remove(0);

Map<String,String> mapRflxEpisode= new LinkedHashMap<String,String>();
for (int i = 0; i < Arr_RflxEpisode_table2d.size(); i++) {
 List<String> stats = Arr_RflxEpisode_table2d.get(i);
 String allStats = stats.remove(0);
 String allStats2=allStats.trim();
 for (String ss:allStats2.split("_")){

	 if(!ss.trim().equals("")) {
    	 Collections.addAll(stats, ss);
    		 }
}
}
System.out.println("Arr_RflxEpisode_table2d  "+Arr_RflxEpisode_table2d);
Arr_RflxEpisode_table2d.get(0).remove(3);
for (int ff=1;ff<Arr_RflxEpisode_table2d.size();ff++){
	//Upright
 mapRflxEpisode.put("Main_RflxEpisode"+Arr_RflxEpisode_table2d.get(0).get(0)+"_"+Arr_RflxEpisode_table2d.get(ff).get(0),Arr_RflxEpisode_table2d.get(ff).get(1));
	//Recumbent
 mapRflxEpisode.put("Main_RflxEpisode"+Arr_RflxEpisode_table2d.get(0).get(1)+"_"+Arr_RflxEpisode_table2d.get(ff).get(0),Arr_RflxEpisode_table2d.get(ff).get(2));
	//Total
 mapRflxEpisode.put("Main_RflxEpisode"+Arr_RflxEpisode_table2d.get(0).get(2)+"_"+Arr_RflxEpisode_table2d.get(ff).get(0),Arr_RflxEpisode_table2d.get(ff).get(3));
  }
System.out.println("HASHSumRefluxEpisodes"+mapRflxEpisode);
mapImpedanceAll.putAll(mapRflxEpisode);
} catch (Exception e) {
e.printStackTrace();
}


//------------------------------------------------------------------------------------------------------------------------------------

	try {
		while (matcherMonitoringDet_pattern.find()) {
			MonitoringDet_arr.add(matcherMonitoringDet_pattern.group(0));
			System.out.println("MonitoringDet_arr.add(matcherMonitoringDet_pattern.group(0)"+matcherMonitoringDet_pattern.group(0));
			 }
		List<String> MonitoringDet_table=new ArrayList<String>(java.util.Arrays.asList(MonitoringDet_arr.get(0).split("\n")));

		//This creates an array of arrays
		MonitoringDet_table=java.util.Arrays.asList(MonitoringDet_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_MonitoringDet_table2d = new ArrayList<List<String>>();

		for (String str : MonitoringDet_table){
			ArrayList<String> Arr_MonitoringDet_table = new ArrayList<String>();

			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", ":");
				str=str.replace("\t", ":");
				str=str.trim();
			Arr_MonitoringDet_table.add(str);
			Arr_MonitoringDet_table2d.add(Arr_MonitoringDet_table);

		}
		}
		//Tidy up
		Map<String,String> mapSx_MonitoringDet= new LinkedHashMap<String,String>();

		 for (int i = 0; i < Arr_MonitoringDet_table2d.size(); i++) {
			 List<String> stats = Arr_MonitoringDet_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split(":")){

				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
			}
		 //Tidy up
		 Arr_MonitoringDet_table2d.remove(0);
		 Arr_MonitoringDet_table2d.remove(0);
		 Arr_MonitoringDet_table2d.remove(1);
		 Arr_MonitoringDet_table2d.remove(1);
		 Arr_MonitoringDet_table2d.get(0).remove(0);
		 Arr_MonitoringDet_table2d.get(3).remove(2);
		 Arr_MonitoringDet_table2d.get(3).remove(3);
		 Arr_MonitoringDet_table2d.get(3).remove(4);
		 Arr_MonitoringDet_table2d.remove(6);


		 for (int ff=1;ff<6;ff++){
			 Arr_MonitoringDet_table2d.get(ff).set(0, "ClearanceChannel_"+Arr_MonitoringDet_table2d.get(ff).get(0));

		  }
		  for (int ff=6;ff<Arr_MonitoringDet_table2d.size();ff++){
			  Arr_MonitoringDet_table2d.get(ff).set(0, "GastricChannel_"+Arr_MonitoringDet_table2d.get(ff).get(0));
		  }

			for (int ff=1;ff<Arr_MonitoringDet_table2d.size()-1;ff++){
				mapSx_MonitoringDet.put("MainAcidExp"+Arr_MonitoringDet_table2d.get(0).get(0)+"_"+Arr_MonitoringDet_table2d.get(ff).get(0),Arr_MonitoringDet_table2d.get(ff).get(1));
				mapSx_MonitoringDet.put("MainAcidExp"+Arr_MonitoringDet_table2d.get(0).get(1)+"_"+Arr_MonitoringDet_table2d.get(ff).get(0),Arr_MonitoringDet_table2d.get(ff).get(2));
				mapSx_MonitoringDet.put("MainAcidExp"+Arr_MonitoringDet_table2d.get(0).get(2)+"_"+Arr_MonitoringDet_table2d.get(ff).get(0),Arr_MonitoringDet_table2d.get(ff).get(3));
				  }

		//System.out.println("HASH_MonitoringDet"+mapSx_MonitoringDet);
		mapImpedanceAll.putAll(mapSx_MonitoringDet);
	} catch (Exception e) {
		e.printStackTrace();
	}

//------------------------------------------------------------------------------------------------------------------------------------

	try {
		while (matcherAcidExp_pattern.find()) {
			AcidExp_pattern_arr.add(matcherAcidExp_pattern.group(0));
			 }
		List<String> AcidExp_pattern_table=new ArrayList<String>(java.util.Arrays.asList(AcidExp_pattern_arr.get(0).split("\n")));

		//This creates an array of arrays
		AcidExp_pattern_table=java.util.Arrays.asList(AcidExp_pattern_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_AcidExp_pattern_table2d = new ArrayList<List<String>>();
		for (String str : AcidExp_pattern_table){
			ArrayList<String> Arr_AcidExp_pattern_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", ":");
				str=str.replace("\t", ":");
				str=str.trim();
			Arr_AcidExp_pattern_table.add(str);
			Arr_AcidExp_pattern_table2d.add(Arr_AcidExp_pattern_table);
			}
		}
		//Tidy up

		Map<String,String> mapSx_AcidExp_pattern= new LinkedHashMap<String,String>();
		 for (int i = 0; i < Arr_AcidExp_pattern_table2d.size(); i++) {
			 List<String> stats = Arr_AcidExp_pattern_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split(":")){

				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		 }
System.out.println("Arr_AcidExp_pattern_table2d"+Arr_AcidExp_pattern_table2d);
		//Post tidy up
		 Arr_AcidExp_pattern_table2d.get(0).remove(0);
		 Arr_AcidExp_pattern_table2d.remove(1);
		 Arr_AcidExp_pattern_table2d.remove(1);
		 for (int ff=1;ff<8;ff++){
			 Arr_AcidExp_pattern_table2d.get(ff).set(0, "ClearanceChannel_"+Arr_AcidExp_pattern_table2d.get(ff).get(0));
		  }
		 Arr_AcidExp_pattern_table2d.remove(7);
		  for (int ff=7;ff<Arr_AcidExp_pattern_table2d.size();ff++){
			  Arr_AcidExp_pattern_table2d.get(ff).set(0, "GastricChannel_"+Arr_AcidExp_pattern_table2d.get(ff).get(0));
		  }



			 for (int ff=1;ff<Arr_AcidExp_pattern_table2d.size();ff++){
					//Symptom
				 mapSx_AcidExp_pattern.put("MainStats"+Arr_AcidExp_pattern_table2d.get(0).get(0)+"_"+Arr_AcidExp_pattern_table2d.get(ff).get(0),Arr_AcidExp_pattern_table2d.get(ff).get(1));
					//Recumbent
				 mapSx_AcidExp_pattern.put("MainStats"+Arr_AcidExp_pattern_table2d.get(0).get(1)+"_"+Arr_AcidExp_pattern_table2d.get(ff).get(0),Arr_AcidExp_pattern_table2d.get(ff).get(2));
					//Total
				 mapSx_AcidExp_pattern.put("MainStats"+Arr_AcidExp_pattern_table2d.get(0).get(2)+"_"+Arr_AcidExp_pattern_table2d.get(ff).get(0),Arr_AcidExp_pattern_table2d.get(ff).get(3));
					//
					  }
		 //System.out.println("HASHmapSx_AcidExp_pattern"+mapSx_AcidExp_pattern);
		 mapImpedanceAll.putAll(mapSx_AcidExp_pattern);
	} catch (Exception e) {
		//System.out.println("There ain't no stats in this one");
	}
//------------------------------------------------------------------------------------------------------------------------------------

	try {
		while (matcherAcidCompositeScore_pattern.find()) {
			AcidCompositeScore_arr.add(matcherAcidCompositeScore_pattern.group(0));
			 }
		List<String> AcidCompositeScore_table=new ArrayList<String>(java.util.Arrays.asList(AcidCompositeScore_arr.get(0).split("\n")));

		//This creates an array of arrays
		AcidCompositeScore_table=java.util.Arrays.asList(AcidCompositeScore_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_AcidCompositeScore_table2d = new ArrayList<List<String>>();
		for (String str : AcidCompositeScore_table){
			ArrayList<String> Arr_AcidCompositeScore_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
			Arr_AcidCompositeScore_table.add(str);
			Arr_AcidCompositeScore_table2d.add(Arr_AcidCompositeScore_table);
			}
		}

		//Tidy up
		Arr_AcidCompositeScore_table2d.remove(Arr_AcidCompositeScore_table2d.size()-1);

		 for (int i = 0; i < Arr_AcidCompositeScore_table2d.size(); i++) {
			 List<String> stats = Arr_AcidCompositeScore_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 Collections.addAll(stats, allStats2.split("\\t"));
			}
		 //Tidy Up
		 Arr_AcidCompositeScore_table2d.remove(0);
		 Arr_AcidCompositeScore_table2d.remove(Arr_AcidCompositeScore_table2d.size()-1);

		 Map<String,String> mapSx_AcidCompositeScore= new LinkedHashMap<String,String>();
		//System.out.println(Arr_AcidCompositeScore_table2d);
		 for (int ff=1;ff<Arr_AcidCompositeScore_table2d.size();ff++){
			 mapSx_AcidCompositeScore.put("MainAcidCompositeScore"+Arr_AcidCompositeScore_table2d.get(0).get(0)+"_"+Arr_AcidCompositeScore_table2d.get(ff).get(0),Arr_AcidCompositeScore_table2d.get(ff).get(1));
			 mapSx_AcidCompositeScore.put("MainAcidCompositeScore"+Arr_AcidCompositeScore_table2d.get(0).get(1)+"_"+Arr_AcidCompositeScore_table2d.get(ff).get(0),Arr_AcidCompositeScore_table2d.get(ff).get(2));
			 mapSx_AcidCompositeScore.put("MainAcidCompositeScore"+Arr_AcidCompositeScore_table2d.get(0).get(2)+"_"+Arr_AcidCompositeScore_table2d.get(ff).get(0),Arr_AcidCompositeScore_table2d.get(ff).get(3));
				  }

		//System.out.println("HASHmap_AcidCompositeScore"+mapSx_AcidCompositeScore);
		mapImpedanceAll.putAll(mapSx_AcidCompositeScore);
	} catch (Exception e) {
		Logger.error(e+HospNum_Id+"There is no Acid composite score here"+child);
	}
//------------------------------------------------------------------------------------------------------------------------------------

	try {
		while (matcherBolusExpo_pattern.find()) {
			BolusExpo_arr.add(matcherBolusExpo_pattern.group(0));
			 }

		List<String> BolusExpo_table=new ArrayList<String>(java.util.Arrays.asList(BolusExpo_arr.get(0).split("\n")));

		//This creates an array of arrays
		BolusExpo_table=java.util.Arrays.asList(BolusExpo_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_BolusExpo_table2d = new ArrayList<List<String>>();
		for (String str : BolusExpo_table){
			ArrayList<String> Arr_BolusExpo_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
			Arr_BolusExpo_table.add(str);
			Arr_BolusExpo_table2d.add(Arr_BolusExpo_table);
			}

		}
		//Tidy up

		 for (int i = 0; i < Arr_BolusExpo_table2d.size(); i++) {
			 List<String> stats = Arr_BolusExpo_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split("\\t"))
				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		 //Tidy Up
		 Arr_BolusExpo_table2d.remove(0);

		 Map<String,String> mapSx_BolusExpo= new LinkedHashMap<String,String>();
		 for (int ff=1;ff<Arr_BolusExpo_table2d.size()-1;ff++){
				//Symptom
			 mapSx_BolusExpo.put("MainBolusExpo"+Arr_BolusExpo_table2d.get(0).get(0)+"_"+Arr_BolusExpo_table2d.get(ff).get(0),Arr_BolusExpo_table2d.get(ff).get(1));
				//Recumbent
			 mapSx_BolusExpo.put("MainBolusExpo"+Arr_BolusExpo_table2d.get(0).get(1)+"_"+Arr_BolusExpo_table2d.get(ff).get(0),Arr_BolusExpo_table2d.get(ff).get(2));
				//Total
			 mapSx_BolusExpo.put("MainBolusExpo"+Arr_BolusExpo_table2d.get(0).get(2)+"_"+Arr_BolusExpo_table2d.get(ff).get(0),Arr_BolusExpo_table2d.get(ff).get(3));
				//
				  }
		//System.out.println("HASHmapmapSx_BolusExpos"+mapSx_BolusExpo);
		mapImpedanceAll.putAll(mapSx_BolusExpo);
	} catch (Exception e) {
	}

//------------------------------------------------------------------------------------------------------------------------------------

	try {
		while (matcherSxCorr_pattern.find()) {
			SxCorr_arr.add(matcherSxCorr_pattern.group(1));
			 }
		List<String> SxCorr_table=new ArrayList<String>(java.util.Arrays.asList(SxCorr_arr.get(0).split("\n")));

		//This creates an array of arrays
		SxCorr_table=java.util.Arrays.asList(SxCorr_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_SxCorr_table2d = new ArrayList<List<String>>();
		for (String str : SxCorr_table){
			ArrayList<String> Arr_SxCorr_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", ":");
				str=str.replace("\t", ":");
				str=str.trim();
			Arr_SxCorr_table.add(str);
			Arr_SxCorr_table2d.add(Arr_SxCorr_table);

			}
		}

		 for (int i = 1; i < Arr_SxCorr_table2d.size(); i++) {
			 List<String> stats = Arr_SxCorr_table2d.get(i);
			 String allStats = stats.remove(0);
			 String allStats2=allStats.trim();
			 for (String ss:allStats2.split(":"))
				 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			}
		 System.out.println("Arr_SxCorr_table2d"+Arr_SxCorr_table2d);
		 Arr_SxCorr_table2d.remove(0);
		 Arr_SxCorr_table2d.remove(0);
		 Arr_SxCorr_table2d.get(0).set(2, "Acid_rel");
		 Arr_SxCorr_table2d.get(0).set(3, "NonAcid_rel");
		 Arr_SxCorr_table2d.get(0).set(4, "AllReflux_rel");
		 Arr_SxCorr_table2d.get(0).set(5, "Sx-Index");
		 System.out.println("Arr_SxCorr_table2dAGAIN"+Arr_SxCorr_table2d);
		    //Get in shape for HASH
				 Map<String,String> mapSx_Corr= new LinkedHashMap<String,String>();
			    for (int ff=1;ff<Arr_SxCorr_table2d.size();ff++){
			    	mapSx_Corr.put("MainSx_Corr_"+Arr_SxCorr_table2d.get(0).get(0)+Arr_SxCorr_table2d.get(0).get(1)+"_"+Arr_SxCorr_table2d.get(ff).get(0),Arr_SxCorr_table2d.get(ff).get(1));
			    	mapSx_Corr.put("MainSx_Corr_"+Arr_SxCorr_table2d.get(0).get(0)+Arr_SxCorr_table2d.get(0).get(2)+"_"+Arr_SxCorr_table2d.get(ff).get(0),Arr_SxCorr_table2d.get(ff).get(2));
			    	mapSx_Corr.put("MainSx_Corr_"+Arr_SxCorr_table2d.get(0).get(0)+Arr_SxCorr_table2d.get(0).get(3)+"_"+Arr_SxCorr_table2d.get(ff).get(0),Arr_SxCorr_table2d.get(ff).get(3));
			    	mapSx_Corr.put("MainSx_Corr_"+Arr_SxCorr_table2d.get(0).get(0)+Arr_SxCorr_table2d.get(0).get(4)+"_"+Arr_SxCorr_table2d.get(ff).get(0),Arr_SxCorr_table2d.get(ff).get(4));
			    	mapSx_Corr.put("MainSx_Corr_"+Arr_SxCorr_table2d.get(0).get(0)+Arr_SxCorr_table2d.get(0).get(5)+"_"+Arr_SxCorr_table2d.get(ff).get(0),Arr_SxCorr_table2d.get(ff).get(5));
			    }
			    System.out.println("HASHmapSx_Corr"+mapSx_Corr);
			    mapImpedanceSx.putAll(mapSx_Corr);
	} catch (Exception e) {

		Logger.error(e+HospNum_Id+"There is no MainSz_Corr here"+child);
	}
//------------------------------------------------------------------------------------------------------------------------------------

	try {
		while (matcherRSSI_pattern.find()) {
			RSSI_arr.add(matcherRSSI_pattern.group(0));
			 }
		//Split up the array by line
		List<String> RSI_table=new ArrayList<String>(java.util.Arrays.asList(RSSI_arr.get(0).split("\n")));

		//This creates an array of arrays
		RSI_table=java.util.Arrays.asList(RSSI_arr.get(0).split("\n"));
		ArrayList<List<String>> Arr_RSI_table2d = new ArrayList<List<String>>();
		for (String str : RSI_table){
			ArrayList<String> Arr_RSI_table = new ArrayList<String>();
			if(!str.trim().equals("")) {
				str=str.trim().replace("\t\t", ":");
				str=str.replace("\t", ":");
				str=str.trim();
			Arr_RSI_table.add(str);
			Arr_RSI_table2d.add(Arr_RSI_table);
		}
		}

		//Now do array tidy up
		Arr_RSI_table2d.remove(Arr_RSI_table2d.size()-1);
		Arr_RSI_table2d.remove(Arr_RSI_table2d.size()-1);

       //Split the inner arrays into comma separated
		    for (int i = 1; i < Arr_RSI_table2d.size(); i++) {
		    	 List<String> stats = Arr_RSI_table2d.get(i);
		    	 String allStats = stats.remove(0);
		    	 String allStats2=allStats.trim();
		    	 for (String ss:allStats2.split(":")){

					 if(!ss.trim().equals("")) {
				    	 Collections.addAll(stats, ss);
				    		 }
		    	}
		    }

		    Arr_RSI_table2d.remove(0);
		    	 //System.out.println("Arr_RSI_table2d"+Arr_RSI_table2d);
		    //Further post replacement tidy up
			 Map<String,String> mapRSI= new LinkedHashMap<String,String>();
		    for (int ff=1;ff<Arr_RSI_table2d.size();ff++){
				//Acid
		    	mapRSI.put("MainSxRSSI"+Arr_RSI_table2d.get(0).get(0)+Arr_RSI_table2d.get(0).get(1)+"_"+Arr_RSI_table2d.get(ff).get(0),Arr_RSI_table2d.get(ff).get(1));
				//Recumbent
		    	mapRSI.put("MainSxRSSI"+Arr_RSI_table2d.get(0).get(0)+Arr_RSI_table2d.get(0).get(2)+"_"+Arr_RSI_table2d.get(ff).get(0),Arr_RSI_table2d.get(ff).get(2));
				//Total
		    	mapRSI.put("MainSxRSSI"+Arr_RSI_table2d.get(0).get(0)+Arr_RSI_table2d.get(0).get(3)+"_"+Arr_RSI_table2d.get(ff).get(0),Arr_RSI_table2d.get(ff).get(3));
		    }
		    //System.out.println("HASHMainSxRSSI" + mapRSI);
		    mapImpedanceSx.putAll(mapRSI);

	} catch (Exception e) {

		//System.out.println("There ain't no RSSI here");
	}


//------------------------------------------------------------------------------------------------------------------------------------



	    try {
			while (matcherMainRSI_pattern.find()) {
				MainRSI_arr.add(matcherMainRSI_pattern.group(0));
				 }
			List<String> MainRSI_table=new ArrayList<String>(java.util.Arrays.asList(MainRSI_arr.get(0).split("\n")));

			//This creates an array of arrays
			MainRSI_table=java.util.Arrays.asList(MainRSI_arr.get(0).split("\n"));
			ArrayList<List<String>> Arr_MainRSI_table2d = new ArrayList<List<String>>();
			for (String str : MainRSI_table){
				////System.out.println("MainRSI_table"+str);
				ArrayList<String> Arr_MainRSI_table = new ArrayList<String>();
				if(!str.trim().equals("")) {
				Arr_MainRSI_table.add(str);
				Arr_MainRSI_table2d.add(Arr_MainRSI_table);
				}
			}


			//Tidy up
			Arr_MainRSI_table2d.remove(Arr_MainRSI_table2d.size()-1);
			Arr_MainRSI_table2d.remove(0);
			Map<String,String> mapMainRSI= new LinkedHashMap<String,String>();
			 for (int i = 0; i < Arr_MainRSI_table2d.size(); i++) {
				 List<String> stats = Arr_MainRSI_table2d.get(i);
				 String allStats = stats.remove(0);
				 String allStats2=allStats.trim();
				 for (String ss:allStats2.split("\\t"))
		    		 if(!ss.trim().equals("")) {
		    	 Collections.addAll(stats, ss);
		    		 }
				}

			 for (int ff=1;ff<Arr_MainRSI_table2d.size();ff++){
					//Acid
				 mapMainRSI.put("MainSxRSI"+Arr_MainRSI_table2d.get(0).get(1)+"_"+Arr_MainRSI_table2d.get(ff).get(0),Arr_MainRSI_table2d.get(ff).get(1));
					//Recumbent
				 mapMainRSI.put("MainSxRSI"+Arr_MainRSI_table2d.get(0).get(2)+"_"+Arr_MainRSI_table2d.get(ff).get(0),Arr_MainRSI_table2d.get(ff).get(2));
					//Total
				 mapMainRSI.put("MainSxRSI"+Arr_MainRSI_table2d.get(0).get(3)+"_"+Arr_MainRSI_table2d.get(ff).get(0),Arr_MainRSI_table2d.get(ff).get(3));
			    }
			 //System.out.println("HASHSxmapMainRSI"+mapMainRSI);
			 mapImpedanceSx.putAll(mapMainRSI);

		} catch (Exception e) {

			e.printStackTrace();
		}

//------------------------------------------------------------------------------------------------------------------------------------


		 try {
			while (matcherMainRSAP_pattern.find()) {
					MainRSAP_arr.add(matcherMainRSAP_pattern.group(0));
					 }
				List<String> MainRSAP_table=new ArrayList<String>(java.util.Arrays.asList(MainRSAP_arr.get(0).split("\n")));

				//This creates an array of arrays
				MainRSAP_table=java.util.Arrays.asList(MainRSAP_arr.get(0).split("\n"));
				ArrayList<List<String>> Arr_MainRSAP_table2d = new ArrayList<List<String>>();
				for (String str : MainRSAP_table){
					ArrayList<String> Arr_MainRSAP_table = new ArrayList<String>();
					if(!str.trim().equals("")) {
					Arr_MainRSAP_table.add(str);
					Arr_MainRSAP_table2d.add(Arr_MainRSAP_table);
					}

				}

				//Tidy up

				Map<String,String> mapMainRSAP= new LinkedHashMap<String,String>();
				 for (int i = 0; i < Arr_MainRSAP_table2d.size(); i++) {
			    	 List<String> stats = Arr_MainRSAP_table2d.get(i);
			    	 String allStats = stats.remove(0);
			    	 String allStats2=allStats.trim();
			    	 for (String ss:allStats2.split("\\t"))
			    		 if(!ss.trim().equals("")) {
			    	 Collections.addAll(stats, ss);
			    		 }
			    	}

				 Arr_MainRSAP_table2d.remove(0);
				 Arr_MainRSAP_table2d.remove(Arr_MainRSAP_table2d.size()-1);

				 for (int ff=1;ff<Arr_MainRSAP_table2d.size();ff++){
						//Acid
					 mapMainRSAP.put("SxMainRSAP"+Arr_MainRSAP_table2d.get(0).get(1)+"_"+Arr_MainRSAP_table2d.get(ff).get(0),Arr_MainRSAP_table2d.get(ff).get(1));
						//Recumbent
					 mapMainRSAP.put("SxMainRSAP"+Arr_MainRSAP_table2d.get(0).get(2)+"_"+Arr_MainRSAP_table2d.get(ff).get(0),Arr_MainRSAP_table2d.get(ff).get(2));
						//Total
					 mapMainRSAP.put("SxMainRSAP"+Arr_MainRSAP_table2d.get(0).get(3)+"_"+Arr_MainRSAP_table2d.get(ff).get(0),Arr_MainRSAP_table2d.get(ff).get(3));
				    }
				 //System.out.println("HASHSxmapMainRSAP"+mapMainRSAP);
				 mapImpedanceSx.putAll(mapMainRSAP);


		} catch (Exception e) {

			Logger.error(e+"Theres no Main RSAP here"+child);
		}

		 //-----------------------CONNECT TO THE DATABASE---------------------------------------------------------------


				//Get the patient hospital number from the PtData table so it can be used as the Primary key

						 try {
	//----------------------------   Get the HospNum_Id column from the database   -----------------------------------------



					        DBConnectorForAll ConnectMeUp = new DBConnectorForAll();
					        String tab="Impedance2";

					        Statement st=ConnectMeUp.Connector(HospNum_Id,FName,SName,DOB);
					        String first=ConnectMeUp.StringInsertKeyPreparer(st,mapImpedanceAll,tab);
					    	String second=ConnectMeUp.StringInsertValuePreparer(st,mapImpedanceAll,tab);

							//System.out.println("CHECKers VisitDate"+Checkers.VisitDateChecker(st,tab,HospNum_Id)+"The method VisitDate"+VisitDate);
							System.out.println("mapImpedanceSx"+mapImpedanceSx);
					    		if (!Checkers.VisitDateChecker(st,tab,HospNum_Id).contains(VisitDate.replace("/", "_"))){

					    			System.out.println("Newline");
					    	sharedkey=ConnectMeUp.Inserter(st,HospNum_Id,first,second,tab,child);
					    	String table="Imp_Symp";

					    	String third=ConnectMeUp.StringInsertKeyPreparer(st,mapImpedanceSx,table);
					    	String fourth=ConnectMeUp.StringInsertValuePreparer(st,mapImpedanceSx,table);

					    	String FKFieldName="Imp_Id";
					    	System.out.println("sharedkey"+sharedkey);
					    	ConnectMeUp.Inserter2(st,third,fourth,table,sharedkey,FKFieldName,child);
					    	}



						} catch (Exception e) {
							Logger.error(e+HospNum_Id+"->From Impedance"+child);

						}
				}
}

