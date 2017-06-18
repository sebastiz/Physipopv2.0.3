package Algorithms;

import java.util.Map;
import org.pmw.tinylog.Logger;

public class HRMDx {
	String LESmidpointfromnarescm;
	Double LESmidpointfromnarescmi;
	String ProximalLESfromnarescm;
	Double ProximalLESfromnarescmi;
	String DistalLESfromnarescm;
	Double DistalLESfromnarescmi;
	String LESlengthcm;
	Double LESlengthcmi;
	String EsophageallengthLESUEScenterscm;
	Double EsophageallengthLESUEScenterscmi;
	String PIPfromnarescm;
	Double PIPfromnarescmi;
	String IntraabdominalLESlengthcm;
	Double IntraabdominalLESlengthcmi;
	String Hiatalhernia;
	String BasalrespiratoryminmmHg;
	Double BasalrespiratoryminmmHgi;
	String UESMeanResidLocationcenterfrnarescm;
	Double UESMeanResidLocationcenterfrnarescmi;
	String Numberofswallowsevaluated;
	Double Numberofswallowsevaluatedi	;
	String DistalcontractileintegralhighestmmHgcms;
	Double DistalcontractileintegralhighestmmHgcmsi;
	static String Contractilefrontvelocitycms;
	static double Contractilefrontvelocitycmsi;
	String IntraboluspressureATLESRmmHg;
	Double IntraboluspressureATLESRmmHgi;
    static String failedChicagoClassification;
	static double failedChicagoClassificationi;
	String panesophagealpressurization;
	Double panesophagealpressurizationi;
	String prematurecontraction;
	Double prematurecontractioni;
	String rapidcontraction;
	Double rapidcontractioni;
	static String largebreaks;
	static double largebreaksi;
	static double smallbreaks;
	static double smallbreaksi;
	String HRM_Id;
	String BasalrespiratorymeanmmHg;
	Double BasalrespiratorymeanmmHgi;
	static String ResidualmeanmmHg;
	static double ResidualmeanmmHgi;
	String ResidMeanbasalpressuremmHg;
	Double ResidMeanbasalpressuremmHgi;
	String ResidMeanresidualpressuremmHg;
	Double ResidMeanresidualpressuremmHgi;
	static String Distallatency;
	static double Distallatencyi;
	static String DistalcontractileintegralmeanmmHgcms;
	static double DistalcontractileintegralmeanmmHgcmsi;
	String VisitDate;
	static String Diagnosis = null;
	public HRMDx() {
		// TODO Auto-generated constructor stub
	}

	public static String HRMDiag(Map<String,Object> DCI){


		for (Map.Entry<String, Object> entry : DCI.entrySet()){

				try {
					if(entry.getValue()!=null){
 if(entry.getKey().contains("ResidualmeanmmHg")){
					 ResidualmeanmmHgi=Double.parseDouble(entry.getValue().toString().trim());
 }
 if(entry.getKey().contains("failedChicagoClassification")){
					 failedChicagoClassificationi=Double.parseDouble(entry.getValue().toString().trim());
 }
 if(entry.getKey().contains("Distallatency")){
					 Distallatencyi=Double.parseDouble(entry.getValue().toString().trim());
 }
 if(entry.getKey().contains("DistalcontractileintegralmeanmmHgcms")){
					 DistalcontractileintegralmeanmmHgcmsi=Double.parseDouble(entry.getValue().toString().trim());
 }
 if(entry.getKey().contains("largebreaks")){
					 largebreaksi=Double.parseDouble(entry.getValue().toString().trim());
 }
 if(entry.getKey().contains("smallbreaks")){
					 smallbreaks=Double.parseDouble(entry.getValue().toString().trim());
 }
					}

				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		 if(ResidualmeanmmHgi>15&&failedChicagoClassificationi==100){

				Diagnosis="The diagnosis is achalasia";
			}
		 else if(ResidualmeanmmHgi>15&&failedChicagoClassificationi<100){

				Diagnosis="The diagnosis is Oesophago-gastric outflow obstruction";
			}
		 else if(ResidualmeanmmHgi<15&&Distallatencyi<4.5){

				Diagnosis="The diagnosis is Diffuse oesophageal spasm";
			}
		 else if(ResidualmeanmmHgi<15&&DistalcontractileintegralmeanmmHgcmsi>8000){

				Diagnosis="The diagnosis is Nutcracker oesophagus";
			}
		 else if(ResidualmeanmmHgi<15&&largebreaksi<20&&smallbreaksi>30){

				Diagnosis="The diagnosis is Weak persitalsis";
			}
		 else if(ResidualmeanmmHgi<15&&Contractilefrontvelocitycmsi<9&&Distallatencyi>6.5){

				Diagnosis="The diagnosis is frequent failed peristalsis";
			}
		 else if(ResidualmeanmmHgi<15&&DistalcontractileintegralmeanmmHgcmsi<9&&Distallatencyi>8000){

				Diagnosis="The diagnosis is frequent failed peristalsis";
			}
		 else{
			 Diagnosis="This is a normal study as per the Chicago Classification";
		 }


			return Diagnosis;
}

}
