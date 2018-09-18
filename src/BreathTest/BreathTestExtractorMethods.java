package BreathTest;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Overview.VisitDateFormatter;
import org.pmw.tinylog.Logger;

public class BreathTestExtractorMethods {
	 String HospNum=null;
	 String FName=null;
	 String SName=null;
	 String DOB=null;
	 String VisitDate=null;
	 public Map<String,String> mapAllBreathTest= new LinkedHashMap<String,String>();
	 public Map<String,String> mapPtDataBT= new LinkedHashMap<String,String>();
	 public Map<String,String> mapAllFrucBreathTest= new LinkedHashMap<String,String>();
	 public Map<String,String> mapAllUreaBreathTest= new LinkedHashMap<String,String>();
	 public Map<String,String> mapAllLactuloseBreathTest= new LinkedHashMap<String,String>();
	 public Map<String,String> mapAllLactoseBreathTest= new LinkedHashMap<String,String>();
	 public Map<String,String> mapAllGlucoseBreathTest= new LinkedHashMap<String,String>();
	 public Map<String,String> mapAllSucroseBreathTest= new LinkedHashMap<String,String>();
	 static String mynotes=null;
	 String myInterpretation=null;
	 String mySymptoms=null;
	 String lactul=null;
	 String lact=null;
	 String fruct=null;
	 String gluc=null;
	 String sucr=null;
	 String str=null;



	public BreathTestExtractorMethods(String HospNum) {
		this.HospNum=HospNum;
		mapAllBreathTest.clear();
	}

	public String FNameExtractor(String str){

		Pattern match_pattern = Pattern.compile("Patient Name(.*)DOB",Pattern.DOTALL);
		Matcher matchermatch_pattern = match_pattern.matcher(str);
		if (matchermatch_pattern.find()) {
			String[] PtName=matchermatch_pattern.group(1).toString().trim().split("\\s");
			FName=PtName[0].trim();

		}
		return FName;
	}

	public String SNameExtractor(String str){
		Pattern match_pattern = Pattern.compile("Patient Name(.*)DOB",Pattern.DOTALL);
		//String FName1="Patient.*\\n\\n(.*),";
		Matcher matchermatch_pattern = match_pattern.matcher(str);
		if (matchermatch_pattern.find()) {
			String[] PtName=matchermatch_pattern.group(1).toString().trim().split("\\s");
			SName=PtName[1].trim().replace("'", "");
		}
		return SName;
	}


	public String DOBExtractor(String str){
		Pattern match_pattern = Pattern.compile("DOB.*\\n(.*)");
		Matcher matchermatch_pattern = match_pattern.matcher(str);
		if (matchermatch_pattern.find()) {
			String h=matchermatch_pattern.group(1).toString().trim();
			DOB=h;
		}
		return DOB;

	}

	public String VisitDateExtractor(String str) throws ParseException{
		Pattern match_pattern = Pattern.compile("Date\\n(.*)");
		Matcher matchermatch_pattern = match_pattern.matcher(str);
		if (matchermatch_pattern.find()) {

			 VisitDate=matchermatch_pattern.group(1).toString().trim();
			 try {
				VisitDate=VisitDateFormatter.VDFormat(VisitDate);
			} catch (Exception e) {

				Logger.error(e+HospNum+"->From ImpedanceMane Mane");
			}
			mapAllBreathTest.put("VisitDate", VisitDate);

		}
		return VisitDate;

	}

	public Map<String,String> ReqPhysExtractor(String str){
		Pattern match_pattern = Pattern.compile("Requesting  physician(.*)");
		Matcher matchermatch_pattern = match_pattern.matcher(str);
		if (matchermatch_pattern.find()) {
			String h=matchermatch_pattern.group(1).toString().trim();
			mapAllBreathTest.put("ReqPhys", h);
		}
		return mapAllBreathTest;
	}


	public static String NotesExtractor(String str){
		String mynotes=null;

		Pattern Notesmatch_pattern = Pattern.compile("Notes(.*[A-Z].*)Symptom",Pattern.DOTALL);
		Matcher Notesmatchermatch_pattern = Notesmatch_pattern.matcher(str);

		Pattern Notesmatch_pattern2 = Pattern.compile("Notes:.*?[\r|\n].*?[\r|\n].*?[\r|\n].*?[\r|\n].*?[\r|\n]?");
		Matcher Notesmatchermatch_pattern2 = Notesmatch_pattern2.matcher(str);

		if (Notesmatchermatch_pattern.find()) {
		  mynotes = Notesmatchermatch_pattern.group(1).trim();
		  //System.out.println("Mynotes1"+mynotes);
		}

			 if(Notesmatchermatch_pattern2.find()){
			mynotes = Notesmatchermatch_pattern2.group(0).replaceAll("^\\n", "").trim().replace(":", "").replaceAll("\\n", "");

		}


		return mynotes;

	}


	public static String SymptomsExtractor(String Symptoms){
		String mysymp=null;
		Pattern Symptomsmatch_pattern = Pattern.compile("Symptoms:(.*)");
		Matcher Symptomsmatchermatch_pattern = Symptomsmatch_pattern.matcher(Symptoms);
		if (Symptomsmatchermatch_pattern.find()) {
			mysymp = Symptomsmatchermatch_pattern.group(1).replaceAll("^\\n", "").trim().replace(":", "").replaceAll("\\n", "");
	}
		return mysymp;
	}




	public static String InterpretationExtractor(String Interpretation){
		String myinterp=null;
//System.out.println("Interpretation"+Interpretation);
		Pattern Interpretationmatch_pattern = Pattern.compile("Interpretation:(.*)");
		Matcher Interpretationmatchermatch_pattern = Interpretationmatch_pattern.matcher(Interpretation);
		if (Interpretationmatchermatch_pattern.find()) {
			myinterp = Interpretationmatchermatch_pattern.group(1).replaceAll("^\\n", "").trim().replace(":", "").replaceAll("\\n", "");
	}
		return myinterp;
	}



	public Map<String,String> LactoseTestExtractor(String str){
		Pattern match_pattern = Pattern.compile("Lactose Hydrogen Breath Test(.*?Interpretation[^\\r|^\\n]*)",Pattern.DOTALL);
		Matcher matchermatch_pattern = match_pattern.matcher(str);

		//Pattern match_pattern2 = Pattern.compile("Lactose Hydrogen Breath Test.*?(Notes:.*?$\n[\n]?.*?\n[\n]?)",Pattern.DOTALL);
		Pattern match_pattern2 = Pattern.compile("Lactose Hydrogen Breath Test.*?(Notes:.*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n])",Pattern.DOTALL);
		Matcher matchermatch_pattern2 = match_pattern2.matcher(str);

		if (matchermatch_pattern.find()) {
			lact=matchermatch_pattern.group(1).toString().trim();
			//System.out.println("lact1"+lact);

		}

		else if (matchermatch_pattern2.find()){
			lact=matchermatch_pattern2.group(1).toString().trim();
			//System.out.println("lact2"+lact);

		}

		try {
			if(lact!=null){
				//mapAllLactoseBreathTest.put("LactoseNotes",null);
				String lactNotes=NotesExtractor(lact).trim();
				//System.out.println("MeNotesLactoseNotes"+lactNotes);
				mapAllLactoseBreathTest.put("LactoseNotes",lactNotes);
			}
			else{
				mapAllLactoseBreathTest.put("LactoseNotes",null);
					}
		} catch (Exception e) {

		}

		try {
			if(lact!=null){
				mapAllLactoseBreathTest.put("LactoseSymptoms",SymptomsExtractor(lact).trim());
		}
		else{
			mapAllLactoseBreathTest.put("LactoseSymptoms",null);
				}
		} catch (Exception e) {

			//System.out.println("No Lactose Symptoms");
		}



		try {
			if(lact!=null){
				mapAllLactoseBreathTest.put("LactoseInterpretation",null);
				mapAllLactoseBreathTest.put("LactoseInterpretation",InterpretationExtractor(lact).trim());
		}
			else{
				mapAllLactoseBreathTest.put("LactoseInterpretation",null);
					}
		}
			catch (Exception e) {

			//System.out.println("No Lactose Interpretation");
		}
		//System.out.println("mapAllLactoseBreathTest2"+mapAllLactoseBreathTest);
		return mapAllLactoseBreathTest;
	}







	public Map<String,String> LactuloseTestExtractor(String str){

		//mapAllLactuloseBreathTest.clear();
		//System.out.println("mapAllLactuloseBreathTest"+mapAllLactuloseBreathTest);
		Pattern match_pattern = Pattern.compile("Lactulose Hydrogen Breath Test(.*?Interpretation[^\\r|^\\n]*)",Pattern.DOTALL);
		Matcher matchermatch_pattern = match_pattern.matcher(str);

		Pattern match_pattern2 = Pattern.compile("Lactulose Hydrogen Breath Test.*?(Notes:.*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n])",Pattern.DOTALL);
		Matcher matchermatch_pattern2 = match_pattern2.matcher(str);

		if (matchermatch_pattern.find()) {
			lactul=matchermatch_pattern.group(1).toString().trim();
		}

		else if (matchermatch_pattern2.find()){
			lactul=matchermatch_pattern2.group(1).toString().trim();
		}

		try {
			if(lactul!=null){
				mapAllLactuloseBreathTest.put("LactuloseNotes",null);
				mapAllLactuloseBreathTest.put("LactuloseNotes",NotesExtractor(lactul).trim());
			}
			else{
				mapAllLactuloseBreathTest.put("LactuloseNotes",null);
					}
		} catch (Exception e) {

			//System.out.println("No Lactulose Notes");
		}

		try {
			if(lactul!=null){
				mapAllLactuloseBreathTest.put("LactuloseSymptoms",null);
				mapAllLactuloseBreathTest.put("LactuloseSymptoms",SymptomsExtractor(lactul).trim());
		}
		else{
			mapAllLactuloseBreathTest.put("LactuloseSymptoms",null);
				}
		} catch (Exception e) {

			//System.out.println("No Lactulose Symptoms");
		}



		try {
			if(lactul!=null){
				mapAllLactuloseBreathTest.put("LactuloseInterpretation",null);
				mapAllLactuloseBreathTest.put("LactuloseInterpretation",InterpretationExtractor(lactul).trim());
		}
			else{
				mapAllLactuloseBreathTest.put("LactuloseInterpretation",null);
					}
		}
			catch (Exception e) {

			//System.out.println("No Lactulose Interpretation");
		}
		//System.out.println("mapAllLactuloseBreathTest2"+mapAllLactuloseBreathTest);
		return mapAllLactuloseBreathTest;
	}




	public Map<String,String> SucroseTestExtractor(String str){
		String sucr=null;
		//mapAllSucroseBreathTest.clear();
		//System.out.println("mapAllSucroseBreathTest"+mapAllSucroseBreathTest);
		Pattern match_pattern = Pattern.compile("Sucrose Hydrogen Breath Test(.*?Interpretation[^\\r|^\\n]*)",Pattern.DOTALL);
		Matcher matchermatch_pattern = match_pattern.matcher(str);

		Pattern match_pattern2 = Pattern.compile("Sucrose Hydrogen Breath Test.*?(Notes:.*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n])",Pattern.DOTALL);
		Matcher matchermatch_pattern2 = match_pattern2.matcher(str);

		if (matchermatch_pattern.find()) {
			sucr=matchermatch_pattern.group(1).toString().trim();
		}

		else if (matchermatch_pattern2.find()){
			sucr=matchermatch_pattern2.group(1).toString().trim();
		}

		try {
			if(sucr!=null){
				mapAllSucroseBreathTest.put("SucroseNotes",null);
				mapAllSucroseBreathTest.put("SucroseNotes",NotesExtractor(sucr).trim());
			}
			else{
				mapAllSucroseBreathTest.put("SucroseNotes",null);
					}
		} catch (Exception e) {

			//System.out.println("No Sucrose Notes");
		}

		try {
			if(sucr!=null){
				mapAllSucroseBreathTest.put("SucroseSymptoms",null);
				mapAllSucroseBreathTest.put("SucroseSymptoms",SymptomsExtractor(sucr).trim());
		}
		else{
			mapAllSucroseBreathTest.put("SucroseSymptoms",null);
				}
		} catch (Exception e) {

			//System.out.println("No Sucrose Symptoms");
		}



		try {
			if(sucr!=null){
				mapAllSucroseBreathTest.put("SucroseInterpretation",null);
				mapAllSucroseBreathTest.put("SucroseInterpretation",InterpretationExtractor(sucr).trim());
		}
			else{
				mapAllSucroseBreathTest.put("SucroseInterpretation",null);
					}
		}
			catch (Exception e) {

			//System.out.println("No Sucrose Interpretation");
		}
		//System.out.println("mapAllSucroseBreathTest2"+mapAllSucroseBreathTest);
		return mapAllSucroseBreathTest;
	}


	public Map<String,String> UreaTestExtractor(String str){
		String ureaseEx=null;
		String ureaseRes=null;
		Pattern match_pattern = Pattern.compile("Excess(.*?)",Pattern.DOTALL);
		Matcher matchermatch_pattern = match_pattern.matcher(str);
		if (matchermatch_pattern.find()) {
			ureaseEx=matchermatch_pattern.group(1).toString().trim();
			}
			mapAllBreathTest.put("UreaseExcess", ureaseEx);

			Pattern matchRes_pattern = Pattern.compile("Result(.*?)",Pattern.DOTALL);
			Matcher matchermatchRes_pattern = matchRes_pattern.matcher(str);
			if (matchermatchRes_pattern.find()) {
				ureaseEx=matchermatchRes_pattern.group(1).toString().trim();
				}
				mapAllBreathTest.put("UreaseResult", ureaseRes);
			return mapAllBreathTest;
	}


	public Map<String,String> GlucoseTestExtractor(String str){
		Pattern match_pattern = Pattern.compile("Glucose Hydrogen Breath Test(.*?Interpretation[^\\r|^\\n]*)",Pattern.DOTALL);
		Matcher matchermatch_pattern = match_pattern.matcher(str);

		//Pattern match_pattern2 = Pattern.compile("Lactose Hydrogen Breath Test.*?(Notes:.*?$\n[\n]?.*?\n[\n]?)",Pattern.DOTALL);
		Pattern match_pattern2 = Pattern.compile("Glucose Hydrogen Breath Test.*?(Notes:.*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n])",Pattern.DOTALL);
		Matcher matchermatch_pattern2 = match_pattern2.matcher(str);

		if (matchermatch_pattern.find()) {
			gluc=matchermatch_pattern.group(1).toString().trim();

		}

		else if (matchermatch_pattern2.find()){
			gluc=matchermatch_pattern2.group(1).toString().trim();

		}

		try {
			if(gluc!=null){
				String glucNotes=NotesExtractor(gluc).trim();
				mapAllGlucoseBreathTest.put("GlucoseNotes",glucNotes);
			}
			else{
				mapAllGlucoseBreathTest.put("GlucoseNotes",null);
					}
		} catch (Exception e) {

		}

		try {
			if(gluc!=null){
				mapAllGlucoseBreathTest.put("GlucoseSymptoms",SymptomsExtractor(gluc).trim());
		}
		else{
			mapAllGlucoseBreathTest.put("GlucoseSymptoms",null);
				}
		} catch (Exception e) {

			//System.out.println("No Lactose Symptoms");
		}



		try {
			if(gluc!=null){
				mapAllGlucoseBreathTest.put("GlucoseInterpretation",null);
				mapAllGlucoseBreathTest.put("GlucoseInterpretation",InterpretationExtractor(gluc).trim());
		}
			else{
				mapAllGlucoseBreathTest.put("GlucoseInterpretation",null);
					}
		}
			catch (Exception e) {

			//System.out.println("No Lactose Interpretation");
		}
		//System.out.println("mapAllLactoseBreathTest2"+mapAllLactoseBreathTest);
		return mapAllGlucoseBreathTest;
	}






	public Map<String,String> FructoseTestExtractor(String str){
		//String fruct=null;
		//mapAllFrucBreathTest.clear();
		//System.out.println("mapAllFrucBreathTest"+mapAllFrucBreathTest);
		Pattern match_pattern = Pattern.compile("Fructose Hydrogen Breath Test(.*?Interpretation[^\\r|^\\n]*)",Pattern.DOTALL);
		Matcher matchermatch_pattern = match_pattern.matcher(str);

		Pattern match_pattern2 = Pattern.compile("Fructose Hydrogen Breath Test.*?(Notes:.*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n].*?[\\r|\\n])",Pattern.DOTALL);
		Matcher matchermatch_pattern2 = match_pattern2.matcher(str);

		if (matchermatch_pattern.find()) {
			fruct=matchermatch_pattern.group(1).toString().trim();
			//System.out.println("FRUCT1"+fruct);
		}

		else if (matchermatch_pattern2.find()){
			fruct=matchermatch_pattern2.group(1).toString().trim();
			//System.out.println("FRUCT2"+fruct);
		}

		try {
			if(fruct!=null){
				mapAllFrucBreathTest.put("FrucNotes",null);
				mapAllFrucBreathTest.put("FrucNotes",NotesExtractor(fruct).trim());
			}
			else{
				mapAllFrucBreathTest.put("FrucNotes",null);
					}
		} catch (Exception e) {

			//System.out.println("No Fructose Notes");
		}

		try {
			if(fruct!=null){
				mapAllFrucBreathTest.put("FrucSymptoms",null);
				mapAllFrucBreathTest.put("FrucSymptoms",SymptomsExtractor(fruct).trim());
		}
		else{
			mapAllFrucBreathTest.put("FrucSymptoms",null);
				}
		} catch (Exception e) {

		}



		try {
			if(fruct!=null){
				mapAllFrucBreathTest.put("FructInterpret",null);
				mapAllFrucBreathTest.put("FructInterpret",InterpretationExtractor(fruct).trim());
		}
			else{
				mapAllFrucBreathTest.put("FructInterpret",null);
					}
		}
			catch (Exception e) {

		}
				return mapAllFrucBreathTest;
	}




}
