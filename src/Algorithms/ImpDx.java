package Algorithms;

import java.util.Map;
import org.pmw.tinylog.Logger;

public class ImpDx {
	String MainAcidCompositeScorePatientValueUprightTimeInReflux;
	String MainAcidCompositeScorePatientValueRecumbentTimeInReflux;
	String MainAcidCompositeScorePatientValueTotalTimeInReflux;
	String MainAcidCompositeScorePatientValueEpisodesOver5min;
	String MainAcidCompositeScorePatientValueLongestEpisode;
	String MainAcidCompositeScorePatientValueTotalEpisodes;
	String MainAcidCompositeScorePatientScoreTotalEpisodes;
	String Imp_Id;
	String MainPPBolusExpoAcidTime;
	String MainPPBolusExpoAcidPercentTime;
	String MainPPBolusExpoNonacidTime;
	String MainPPBolusExpoNonacidPercentTime;
	String MainPPBolusExpoAllRefluxTime;
	String MainPPBolusExpoAllRefluxPercentTime;
	String MainPPBolusExpoMedianBolusClearanceTime;
	String PPReflEpisodeActExpoAcid;
	String PPReflEpisodeActExpoNonacid;
	String PPReflEpisodeActExpoAllReflux;
	String MainPPPostprandExpoAnalysisDurationUpright;
	String MainPPPostprandExpoAnalysisDurationRecumbent;
	String MainPPPostprandExpoAnalysisDurationTotal;
	String MainPPAcidExpoClearanceChannelNumberofAcidEpisodes;
	String MainPPAcidExpoClearanceChannelTime;
	String MainPPAcidExpoClearanceChannelPercentTime;
	String MainPPAcidExpoClearanceChannelMeanAcidClearanceTime;
	String MainPPAcidExpoClearanceChannelLongestEpisode;
	String MainPPAcidExpoGastricChannelTimepHLessThan4;
	String MainPPAcidExpoGastricChannelPercentTime;
	String MainPtDataPatientName;
	String MainPtDataPatientID;
	String MainPtDataPhysician;
	String MainPtDataPatientSex;
	String MainPtDataDateofBirth;
	String MainPtDataPerformedby;
	String MainPtDataDateofAdmission;
	String MainProcProcedureStart;
	String MainProcProcedureDuration;
	String MainProcCatheterImpedanceSensorPositions;
	String MainProcCatheterpHSensorPosition;
	String MainProcCatheterDepth;
	String MainReflxSettingsAnalysisDurationUpright;
	String MainReflxSettingsAnalysisDurationRecumbent;
	String MainReflxSettingsAnalysisDurationTotal;
	String ProximalMigrationLevel;
	String MainProxExtentUprightUpright;
	String MainProxExtentRecumbentUpright;
	String MainProxExtentTotalUpright;
	String MainProxExtentUprightAcid;
	String MainProxExtentRecumbentAcid;
	String MainProxExtentTotalAcid;
	String MainProxExtentUprightNonacid;
	String MainProxExtentRecumbentNonacid;
	String MainProxExtentTotalNonacid;
	String MainRflxEpisodeUprightAcid;
	String MainRflxEpisodeRecumbentAcid;
	String MainRflxEpisodeTotalAcid;
	String MainRflxEpisodeUprightNonacid;
	String MainRflxEpisodeRecumbentNonacid;
	String MainRflxEpisodeTotalNonacid;
	String MainRflxEpisodeUprightAllReflux;
	String MainRflxEpisodeRecumbentAllReflux;
	String MainRflxEpisodeTotalAllReflux;
	String MainAcidExpUprightClearanceChannelNumberofAcidEpisodes;
	String MainAcidExpRecumbentClearanceChannelNumberofAcidEpisodes;
	String MainAcidExpTotalClearanceChannelNumberofAcidEpisodes;
	String MainAcidExpUprightClearanceChannelTime;
	String MainAcidExpRecumbentClearanceChannelTime;
	String MainAcidExpTotalClearanceChannelTime;
	String MainAcidExpUprightClearanceChannelPercentTime;
	String MainAcidExpRecumbentClearanceChannelPercentTime;
	String MainAcidExpTotalClearanceChannelPercentTime;
	String MainAcidExpUprightClearanceChannelMeanAcidClearanceTime;
	String MainAcidExpRecumbentClearanceChannelMeanAcidClearanceTime;
	String MainAcidExpTotalClearanceChannelMeanAcidClearanceTime;
	String MainAcidExpUprightClearanceChannelLongestEpisode;
	String MainAcidExpRecumbentClearanceChannelLongestEpisode;
	String MainAcidExpTotalClearanceChannelLongestEpisode;
	String MainAcidExpUprightGastricChannelTimepHLessThan40;
	String MainAcidExpRecumbentGastricChannelTimepHLessThan40;
	String MainAcidExpTotalGastricChannelTimepHLessThan40;
	String MainStatsUprightClearanceChannelMinimum;
	String MainStatsRecumbentClearanceChannelMinimum;
	String MainStatsTotalClearanceChannelMinimum;
	String MainStatsUprightClearanceChannelMaximum;
	String MainStatsRecumbentClearanceChannelMaximum;
	String MainStatsTotalClearanceChannelMaximum;
	String MainStatsUprightClearanceChannelMean;
	String MainStatsRecumbentClearanceChannelMean;
	String MainStatsTotalClearanceChannelMean;
	String MainStatsUprightClearanceChannelMedian;
	String MainStatsRecumbentClearanceChannelMedian;
	String MainStatsTotalClearanceChannelMedian;
	String MainStatsUprightClearanceChannelStandardDeviation;
	String MainStatsRecumbentClearanceChannelStandardDeviation;
	String MainStatsTotalClearanceChannelStandardDeviation;
	String MainStatsUprightClearanceChannelVariance;
	String MainStatsRecumbentClearanceChannelVariance;
	String MainStatsTotalClearanceChannelVariance;
	String MainStatsUprightGastricChannelMinimum;
	String MainStatsRecumbentGastricChannelMinimum;
	String MainStatsTotalGastricChannelMinimum;
	String MainStatsUprightGastricChannelMaximum;
	String MainStatsRecumbentGastricChannelMaximum;
	String MainStatsTotalGastricChannelMaximum;
	String MainStatsUprightGastricChannelMean;
	String MainStatsRecumbentGastricChannelMean;
	String MainStatsTotalGastricChannelMean;
	String MainStatsUprightGastricChannelMedian;
	String MainStatsRecumbentGastricChannelMedian;
	String MainStatsTotalGastricChannelMedian;
	String MainStatsUprightGastricChannelStandardDeviation;
	String MainStatsRecumbentGastricChannelStandardDeviation;
	String MainStatsTotalGastricChannelStandardDeviation;
	String MainStatsUprightGastricChannelVariance;
	String MainStatsRecumbentGastricChannelVariance;
	String MainStatsTotalGastricChannelVariance;
	String MainAcidCompositeScorePatientScoreUprightTimeInReflux;
	String MainAcidCompositeScorePatientScoreRecumbentTimeInReflux;
	String MainAcidCompositeScorePatientScoreTotalTimeInReflux;
	String MainAcidCompositeScorePatientScoreEpisodesOver5min;
	String MainAcidCompositeScorePatientScoreLongestEpisode;
	String MainBolusExpoUprightAcidTime;
	String MainBolusExpoRecumbentAcidTime;
	String MainBolusExpoUprightAcidPercentTime;
	String MainBolusExpoRecumbentAcidPercentTime;
	String MainBolusExpoUprightNonacidTime;
	String MainBolusExpoRecumbentNonacidTime;
	String MainBolusExpoUprightNonacidPercentTime;
	String MainBolusExpoRecumbentNonacidPercentTime;
	String MainBolusExpoUprightAllRefluxTime;
	String MainBolusExpoRecumbentAllRefluxTime;
	String MainBolusExpoUprightAllRefluxPercentTime;
	String MainBolusExpoRecumbentAllRefluxPercentTime;
	String MainBolusExpoUprightMedianBolusClearanceTime;
	String MainBolusExpoRecumbentMedianBolusClearanceTime;
	String MainBolusExpoUprightLongestEpisode;
	String MainBolusExpoRecumbentLongestEpisode;
	String HospNum_Id;
	int MainAcidCompositeScorePatientValueUprightTimeInRefluxi;
	int MainAcidCompositeScorePatientValueRecumbentTimeInRefluxi;
	int MainAcidCompositeScorePatientValueTotalTimeInRefluxi;
	int MainAcidCompositeScorePatientValueEpisodesOver5mini;
	int MainAcidCompositeScorePatientValueLongestEpisodei;
	int MainAcidCompositeScorePatientValueTotalEpisodesi;
	int MainAcidCompositeScorePatientScoreTotalEpisodesi;
	int Imp_Idi;
	int MainPPBolusExpoAcidTimei;
	int MainPPBolusExpoAcidPercentTimei;
	int MainPPBolusExpoNonacidTimei;
	int MainPPBolusExpoNonacidPercentTimei;
	int MainPPBolusExpoAllRefluxTimei;
	int MainPPBolusExpoAllRefluxPercentTimei;
	int MainPPBolusExpoMedianBolusClearanceTimei;
	int PPReflEpisodeActExpoAcidi;
	int PPReflEpisodeActExpoNonacidi;
	int PPReflEpisodeActExpoAllRefluxi;
	int MainPPPostprandExpoAnalysisDurationUprighti;
	int MainPPPostprandExpoAnalysisDurationRecumbenti;
	int MainPPPostprandExpoAnalysisDurationTotali;
	int MainPPAcidExpoClearanceChannelNumberofAcidEpisodesi;
	int MainPPAcidExpoClearanceChannelTimei;
	int MainPPAcidExpoClearanceChannelPercentTimei;
	int MainPPAcidExpoClearanceChannelMeanAcidClearanceTimei;
	int MainPPAcidExpoClearanceChannelLongestEpisodei;
	int MainPPAcidExpoGastricChannelTimepHLessThan4i;
	int MainPPAcidExpoGastricChannelPercentTimei;
	int MainPtDataPatientNamei;
	int MainPtDataPatientIDi;
	int MainPtDataPhysiciani;
	int MainPtDataPatientSexi;
	int MainPtDataDateofBirthi;
	int MainPtDataPerformedbyi;
	int MainPtDataDateofAdmissioni;
	int MainProcProcedureStarti;
	int MainProcProcedureDurationi;
	int MainProcCatheterImpedanceSensorPositionsi;
	int MainProcCatheterpHSensorPositioni;
	int MainProcCatheterDepthi;
	int MainReflxSettingsAnalysisDurationUprighti;
	int MainReflxSettingsAnalysisDurationRecumbenti;
	int MainReflxSettingsAnalysisDurationTotali;
	int ProximalMigrationLeveli;
	int MainProxExtentUprightUprighti;
	int MainProxExtentRecumbentUprighti;
	int MainProxExtentTotalUprighti;
	int MainProxExtentUprightAcidi;
	int MainProxExtentRecumbentAcidi;
	int MainProxExtentTotalAcidi;
	int MainProxExtentUprightNonacidi;
	int MainProxExtentRecumbentNonacidi;
	int MainProxExtentTotalNonacidi;
	int MainRflxEpisodeUprightAcidi;
	int MainRflxEpisodeRecumbentAcidi;
	int MainRflxEpisodeTotalAcidi;
	int MainRflxEpisodeUprightNonacidi;
	int MainRflxEpisodeRecumbentNonacidi;
	int MainRflxEpisodeTotalNonacidi;
	int MainRflxEpisodeUprightAllRefluxi;
	int MainRflxEpisodeRecumbentAllRefluxi;
	int MainRflxEpisodeTotalAllRefluxi;
	int MainAcidExpUprightClearanceChannelNumberofAcidEpisodesi;
	int MainAcidExpRecumbentClearanceChannelNumberofAcidEpisodesi;
	int MainAcidExpTotalClearanceChannelNumberofAcidEpisodesi;
	int MainAcidExpUprightClearanceChannelTimei;
	int MainAcidExpRecumbentClearanceChannelTimei;
	int MainAcidExpTotalClearanceChannelTimei;
	int MainAcidExpUprightClearanceChannelPercentTimei;
	int MainAcidExpRecumbentClearanceChannelPercentTimei;
	int MainAcidExpTotalClearanceChannelPercentTimei;
	int MainAcidExpUprightClearanceChannelMeanAcidClearanceTimei;
	int MainAcidExpRecumbentClearanceChannelMeanAcidClearanceTimei;
	int MainAcidExpTotalClearanceChannelMeanAcidClearanceTimei;
	int MainAcidExpUprightClearanceChannelLongestEpisodei;
	int MainAcidExpRecumbentClearanceChannelLongestEpisodei;
	int MainAcidExpTotalClearanceChannelLongestEpisodei;
	int MainAcidExpUprightGastricChannelTimepHLessThan40i;
	int MainAcidExpRecumbentGastricChannelTimepHLessThan40i;
	int MainAcidExpTotalGastricChannelTimepHLessThan40i;
	int MainStatsUprightClearanceChannelMinimumi;
	int MainStatsRecumbentClearanceChannelMinimumi;
	int MainStatsTotalClearanceChannelMinimumi;
	int MainStatsUprightClearanceChannelMaximumi;
	int MainStatsRecumbentClearanceChannelMaximumi;
	int MainStatsTotalClearanceChannelMaximumi;
	int MainStatsUprightClearanceChannelMeani;
	int MainStatsRecumbentClearanceChannelMeani;
	int MainStatsTotalClearanceChannelMeani;
	int MainStatsUprightClearanceChannelMediani;
	int MainStatsRecumbentClearanceChannelMediani;
	int MainStatsTotalClearanceChannelMediani;
	int MainStatsUprightClearanceChannelStandardDeviationi;
	int MainStatsRecumbentClearanceChannelStandardDeviationi;
	int MainStatsTotalClearanceChannelStandardDeviationi;
	int MainStatsUprightClearanceChannelVariancei;
	int MainStatsRecumbentClearanceChannelVariancei;
	int MainStatsTotalClearanceChannelVariancei;
	int MainStatsUprightGastricChannelMinimumi;
	int MainStatsRecumbentGastricChannelMinimumi;
	int MainStatsTotalGastricChannelMinimumi;
	int MainStatsUprightGastricChannelMaximumi;
	int MainStatsRecumbentGastricChannelMaximumi;
	int MainStatsTotalGastricChannelMaximumi;
	int MainStatsUprightGastricChannelMeani;
	int MainStatsRecumbentGastricChannelMeani;
	int MainStatsTotalGastricChannelMeani;
	int MainStatsUprightGastricChannelMediani;
	int MainStatsRecumbentGastricChannelMediani;
	int MainStatsTotalGastricChannelMediani;
	int MainStatsUprightGastricChannelStandardDeviationi;
	int MainStatsRecumbentGastricChannelStandardDeviationi;
	int MainStatsTotalGastricChannelStandardDeviationi;
	int MainStatsUprightGastricChannelVariancei;
	int MainStatsRecumbentGastricChannelVariancei;
	int MainStatsTotalGastricChannelVariancei;
	int MainAcidCompositeScorePatientScoreUprightTimeInRefluxi;
	int MainAcidCompositeScorePatientScoreRecumbentTimeInRefluxi;
	int MainAcidCompositeScorePatientScoreTotalTimeInRefluxi;
	int MainAcidCompositeScorePatientScoreEpisodesOver5mini;
	int MainAcidCompositeScorePatientScoreLongestEpisodei;
	int MainBolusExpoUprightAcidTimei;
	int MainBolusExpoRecumbentAcidTimei;
	int MainBolusExpoUprightAcidPercentTimei;
	int MainBolusExpoRecumbentAcidPercentTimei;
	int MainBolusExpoUprightNonacidTimei;
	int MainBolusExpoRecumbentNonacidTimei;
	int MainBolusExpoUprightNonacidPercentTimei;
	int MainBolusExpoRecumbentNonacidPercentTimei;
	int MainBolusExpoUprightAllRefluxTimei;
	int MainBolusExpoRecumbentAllRefluxTimei;
	int MainBolusExpoUprightAllRefluxPercentTimei;
	int MainBolusExpoRecumbentAllRefluxPercentTimei;
	int MainBolusExpoUprightMedianBolusClearanceTimei;
	int MainBolusExpoRecumbentMedianBolusClearanceTimei;
	int MainBolusExpoUprightLongestEpisodei;
	int MainBolusExpoRecumbentLongestEpisodei;
	int HospNum_Idi;
	int VisitDatei;
	String VisitDate;
	String Diagnosis = null;
	
	public ImpDx() {
		// TODO Auto-generated constructor stub
	}

	public String ImpDiag(String DCI){
	
		/*for (Map.Entry<String, Object> entry : DCI.entrySet()){
			
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
			System.out.println("DUNIT");
			Diagnosis="The diagnosis is achalasia";				
		}
	 else if(ResidualmeanmmHgi>15&&failedChicagoClassificationi<100){
			System.out.println("DUNIT");
			Diagnosis="The diagnosis is Oesophago-gastric outflow obstruction";				
		}
	 else if(ResidualmeanmmHgi<15&&Distallatencyi<4.5){
			System.out.println("DUNIT");
			Diagnosis="The diagnosis is Diffuse oesophageal spasm";				
		}
	 else if(ResidualmeanmmHgi<15&&DistalcontractileintegralmeanmmHgcmsi>8000){
			System.out.println("DUNIT");
			Diagnosis="The diagnosis is Nutcracker oesophagus";		
		}
	 else if(ResidualmeanmmHgi<15&&largebreaksi<20&&smallbreaksi>30){
			System.out.println("DUNIT");
			Diagnosis="The diagnosis is Weak persitalsis";			
		}
	 else if(ResidualmeanmmHgi<15&&Contractilefrontvelocitycmsi<9&&Distallatencyi>6.5){
			System.out.println("DUNIT");
			Diagnosis="The diagnosis is frequent failed peristalsis";				
		}
	 else if(ResidualmeanmmHgi<15&&DistalcontractileintegralmeanmmHgcmsi<9&&Distallatencyi>8000){
			System.out.println("DUNIT");
			Diagnosis="The diagnosis is frequent failed peristalsis";				
		}
	 else{
		 Diagnosis="This is a normal study as per the Chicago Classification";
	 }*/
	return Diagnosis;

}
}

